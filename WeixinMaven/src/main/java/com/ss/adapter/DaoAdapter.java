package com.ss.adapter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.log4j.Logger;
import com.ss.Interface.Adaptable;
import com.ss.entity.pojo.GoodUser;
import com.ss.util.DB;
import com.ss.util.LoggerUtil;

public abstract class DaoAdapter<T> implements Adaptable<T> {
	protected static Logger logger = LoggerUtil.getInstance();
	
	//已改为PrepareStatement
	@Override
	public List<T> getEntitys(Class<T> c) {
		List<T> entitys = new ArrayList<T>(); 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			//获取类名
			String tableName = getTableName(c);
			String sql = "select * from " + tableName;
			logger.error("----------------取所有-" + sql + "--------------------");
			pstmt = DB.prepareStmt(conn, sql);
			rs = DB.executeQuery(pstmt);
			//初始化对象，并添加在List中
			try {
				while(rs.next()) {
					T t = c.newInstance();
					initEntityFromRs(t, rs);
					entitys.add(t);
				}
			} catch (Exception e) {
				logger.error("getEntitys反射过程出错，Exception为：" + e.getClass().getName());
				e.printStackTrace();
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
			DB.close(conn);
		}
		return entitys;
	}
	@Override
	public boolean add(T t) {
		return false;
	}
	//已进行第一步优化
	/*@Override
	public boolean add(T t) {
		//通过对属性进行循环，反射得到属性值，并构造sql语句（前提是属性值都为String或基础数据类型）
		Field[] fields = t.getClass().getDeclaredFields();
		logger.error("DaoAdapter中的fields的长度:" + fields.length);
		String str = "";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		try {
			//设定循环属性的下标值，第一个为1。
			int index = 1;
			for (Field f: fields) {
				String MethodName = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				Method m = t.getClass().getDeclaredMethod(MethodName, null);
				Object fieldObject = m.invoke(t, null);
				String fieldValue = null;
				if(fieldObject == null || fieldObject.equals("") || fieldObject.equals(0)) {
					fieldValue = "null";
				} else {
					fieldValue = "?,";
					parameters.put(index, fieldObject);
					index++;
				}
				str = str + fieldValue;
			}
		} catch (Exception e) {
			logger.error("add反射过程出错，Exception为：" + e.getClass().getName());
			return false;
		}
		
		if(!str.equals("")) {
			String tableName = getTableName((Class<T>)t.getClass());
			if(tableName == null) {
				return false;
			}
			String sql = "insert into " + tableName + " values (" + str.substring(0, str.length()-1) + ")";
			logger.error("-------------------添加-" + sql + "-------------------");
			if(parameters.isEmpty()) {
				parameters = null;
			}
			if(executeUpdate(sql, parameters)) {
				logger.error("添加实体类成功");
				return true;
			} else {
				logger.error("添加实体类失败");
				return false;
			}
		}
		logger.error("添加实体类失败");
		return false;
	}*/
	//第一个是主键，不参与属性循环
	//已进行第一步优化
	@Override
	public boolean update(T t) {
		//通过对属性进行循环，反射得到属性值，并构造sql语句（前提是属性值都为String或基础数据类型）
		Field[] fields = t.getClass().getDeclaredFields();
		logger.error("DaoAdapter中的fields的长度:" + fields.length);
		String str = "";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		//定义主键值
		String priFieldValue = "";
		try {
			for (int i=0; i<fields.length; i++) {
				String MethodName = "get" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
				Method m = t.getClass().getDeclaredMethod(MethodName, null);
				String fieldValue = m.invoke(t, null).toString();
				if(fieldValue == null || fieldValue.equals("") || fieldValue.equals(0)) {
					fieldValue = "null";
				}
				//取得主键值
				if(i == 0) {
					priFieldValue = fieldValue;
				} else {
					str = str + fields[i].getName() + "='" + fieldValue + "',";
				}
			}
		} catch (Exception e) {
			logger.error("add反射过程出错，Exception为：" + e.getClass().getName());
		}
		
		if(!str.equals("")) {
			String tableName = getTableName((Class<T>)t.getClass());
			String sql = "update " + tableName + " set " + str + " where " + fields[0].getName() + "='" + priFieldValue + "'";
			logger.error("-------------------更新-" + sql + "-------------------");
			if(executeUpdate(sql, null)) {
				logger.error("更新实体类成功");
				return true;
			} else {
				logger.error("更新实体类失败");
				return false;
			}
		}
		logger.error("更新实体类失败");
		return false;
	}
	//已改为PreparedStatement
	@Override
	public T getEntityByPK(Class<T> c, Object pk) {
		boolean getEntity = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			Field[] fields = c.getDeclaredFields();
			String pkName = fields[0].getName();
			//
			String tableName = getTableName(c);
			String sql = "select * from " + tableName + " where " + pkName + "='" + pk.toString() + "'";
			logger.error("---------------取单个-" + sql + "-----------------");
			pstmt = DB.prepareStmt(conn, sql);
			rs = DB.executeQuery(pstmt);
			T t1 = null;
			//初始化对象
			try {
				t1 = c.newInstance();
				while(rs.next()) {
					initEntityFromRs(t1, rs);
					getEntity = true;
				}
			} catch (Exception e) {
				logger.error("getEntityByPK反射过程出错，Exception为：" + e.getClass().getName());
				e.printStackTrace();
			}
			conn.setAutoCommit(autoCommit);
			if(getEntity) {
				return t1;
			}
		} catch (SQLException e) {
			logger.error("------------------sqlException-------------");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
			DB.close(conn);
		}
		return null;
	}
	/**
	 * 根据sql语句查询单个实体类。
	 * @param sql sql语句
	 * @param c 实体类的Class类
	 * @return 查到的实体类，若没有查到实体类，则返回null。
	 */
	public T getEntityFromSql(String sql, Class<T> c) {
		boolean getEntity = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			logger.error("---------------取单个-" + sql + "-----------------");
			pstmt = DB.prepareStmt(conn, sql);
			rs = DB.executeQuery(pstmt);
			T t1 = null;
			//初始化对象
			try {
				t1 = c.newInstance();
				while(rs.next()) {
					initEntityFromRs(t1, rs);
					getEntity = true;
				}
			} catch (Exception e) {
				logger.error("getEntityByPK反射过程出错，Exception为：" + e.getClass().getName());
				e.printStackTrace();
			}
			conn.setAutoCommit(autoCommit);
			if(getEntity) {
				return t1;
			}
		} catch (SQLException e) {
			logger.error("------------------sqlException-------------");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
			DB.close(conn);
		}
		return null;
	}
	/**
	 * 根据sql语句查询实体类的集合。
	 * @param sql sql语句
	 * @param c 实体类的Class类
	 * @return 装有实体类集合的List
	 */
	public List<Object> getEntitysFromSql(String sql, Class c) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> result = new ArrayList<Object>();
		try {
			conn = DB.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			logger.error("---------------取多个-" + sql + "-----------------");
			pstmt = DB.prepareStmt(conn, sql);
			rs = DB.executeQuery(pstmt);
			//初始化对象
			try {
				while(rs.next()) {
					Object o = c.newInstance();
					Method method = c.getDeclaredMethod("initFromRs", ResultSet.class);
					//反射执行该对象的initFromRs方法
					method.invoke(o, rs);
					result.add(o);
				}
			} catch (Exception e) {
				logger.error("getEntityByPK反射过程出错，Exception为：" + e.getClass().getName());
				e.printStackTrace();
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			logger.error("------------------sqlException-------------");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
			DB.close(conn);
		}
		return result;
	}
	//第一个是主键，根据主键删除
	//已进行第一步优化。
	@Override
	public boolean delete(T t) {
		Field[] fields = t.getClass().getDeclaredFields();
		logger.error("DaoAdapter中的fields的长度:" + fields.length);
		String str = "";
		//定义主键值
		String priFieldValue = "";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		try {
				String MethodName = "get" + fields[0].getName().substring(0, 1).toUpperCase() + fields[0].getName().substring(1);
				Method m = t.getClass().getDeclaredMethod(MethodName, null);
				String fieldValue = m.invoke(t, null).toString();
				if(fieldValue == null || fieldValue.equals("") || fieldValue.equals(0)) {
					fieldValue = "null";
				}
				//取得主键值
				priFieldValue = fieldValue;
				parameters.put(1, priFieldValue);
		} catch (Exception e) {
			logger.error("add反射过程出错，Exception为：" + e.getClass().getName());
		}
		
		if(!str.equals("")) {
			String tableName = getTableName((Class<T>)t.getClass());
			String sql = "delete from " + tableName + " where " + fields[0].getName() + "= ?";
			logger.error("-------------------删除-" + sql + "-------------------");
			if(executeUpdate(sql, parameters)) {
				logger.error("删除实体类成功");
				return true;
			} else {
				logger.error("删除实体类失败");
				return false;
			}
		}
		logger.error("删除实体类失败");
		return false;
	}

	@Override
	public void deleteEntitys(Object[] pks) {
		// TODO Auto-generated method stub
		
	}
	//View要建立一个方法，getNameInDB()
	//已改为PreparedStatement
	/**
	 * 获取实体类的所有对象，不添加判断条件。
	 * @param c 实体类Class的对象
	 * @return 装有实体类对象的List容器。
	 */
	public List<T> getViewEntitys(Class<T> c) {
		List<T> entitys = new ArrayList<T>(); 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			//获取getNameInDB返回的数据库中表名。
			try {
				String tableName = getTableName(c);
				String sql = "select * from " + tableName;
				logger.error("----------------取所有-" + sql + "--------------------");
				pstmt = DB.prepareStmt(conn, sql);
				rs = DB.executeQuery(pstmt);
			
				while(rs.next()) {
					T t1 = c.newInstance();
					initEntityFromRs(t1, rs);
					entitys.add(t1);
				}
			} catch (Exception e) {
				logger.error("getEntitys反射过程出错，Exception为：" + e.getClass().getName());
				e.printStackTrace();
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
			DB.close(conn);
		}
		return entitys;
	}
	//已改为PreparedStatement
	/**
	 * 根据sql语句来取实体类的对象。
	 * @param c Class对象。
	 * @param whereSql 发出的判断语句。
	 * @return 装有实体类的List
	 */
	public List<T> getViewEntitys(Class<T> c, String sql) {
		List<T> entitys = new ArrayList<T>(); 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			//获取getNameInDB返回的数据库中表名。
			try {
				logger.error("----------------取所有-" + sql + "--------------------");
				pstmt = DB.prepareStmt(conn, sql);
				rs = DB.executeQuery(pstmt);
			
				while(rs.next()) {
					T t1 = c.newInstance();
					initEntityFromRs(t1, rs);
					entitys.add(t1);
				}
			} catch (Exception e) {
				logger.error("getEntitys反射过程出错，Exception为：" + e.getClass().getName());
				e.printStackTrace();
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
			DB.close(conn);
		}
		return entitys;
	}
	//已改为PreparedStatement
	/**
	 * 查找该Entity的总的个数，没有过滤条件。
	 * @param c Entity的Class对象
	 * @return 查找到的Entity的总的个数
	 */
	public int getEntitysCount(Class<T> c) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String tableName = getTableName(c);
			String sql = "select count(*) from " + tableName + ";";
			pstmt = DB.prepareStmt(conn, sql);
			logger.info(sql);
			rs = DB.executeQuery(pstmt);
			while(rs.next()) {
				logger.info("rs:" + rs.getInt(0));
				return rs.getInt(0);
			}
		} catch (SQLException e) {
			logger.error("------------------sqlException-------------");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
			DB.close(conn);
		}
		return 0;		
	}
	//已改为PreparedStatement
	/**
	 * 通过sql语句取单个数量值。
	 * @param sql sql语句。
	 * @return 数量值。
	 */
	public BigDecimal getCountFromSql(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			logger.error(sql);
			pstmt = DB.prepareStmt(conn, sql);
			rs = DB.executeQuery(pstmt);
			while(rs.next()) {
				return rs.getBigDecimal(1);
			}
		} catch (SQLException e) {
			logger.error("------------------sqlException-------------");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
			DB.close(conn);
		}
		return null;	
	}
	/**
	 * 通过PreparedStatement预编译进行增删改的操作。
	 * @param sql 预编译的sql语句。
	 * @param parameters Map<Integer, Object>类型，设定各个参数所在占位符位置和参数值。若无，可设为null。
	 * @return 若执行成功，返回true；否则返回false。
	 */
	public boolean executeUpdate(String sql, Map<Integer, Object> parameters) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Set<Entry<Integer, Object>> parameterEntrys = null;
		try {
			conn = DB.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			pstmt = DB.prepareStmt(conn, sql);
			if(parameters != null) {
				parameterEntrys = parameters.entrySet();
				for(Entry entry : parameterEntrys) {
					if(entry.getValue() instanceof String) {
						pstmt.setString((Integer)entry.getKey(), entry.getValue().toString());
					} else if (entry.getValue() instanceof Integer) {
						pstmt.setInt((Integer)entry.getKey(), (Integer)entry.getValue());
					} else if(entry.getValue() instanceof Double) {
						pstmt.setDouble((Integer)entry.getKey(), (Double)entry.getValue());
					} else if(entry.getValue() instanceof java.util.Date) {
						java.util.Date d = (java.util.Date)entry.getValue();
						java.sql.Timestamp timestamp = new java.sql.Timestamp(d.getTime());
						pstmt.setTimestamp((Integer)entry.getKey(), timestamp);
					} else if(entry.getValue() instanceof java.sql.Date) {
						java.sql.Date d = (java.sql.Date)entry.getValue();
						java.sql.Timestamp timestamp = new java.sql.Timestamp(d.getTime());
						pstmt.setTimestamp((Integer)entry.getKey(), timestamp);
					}
				}
			}
			if(DB.executeUpdate(pstmt)) {
				logger.error("执行成功");
				conn.setAutoCommit(autoCommit);
				return true;
			} else {
				logger.error("执行失败");
				conn.setAutoCommit(autoCommit);
				return false;
			}
		} catch (SQLException e) {
			logger.error("------------------sqlException-------------");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
			DB.close(conn);
		}		
		return false;
	}
	/**
	 * 通过sql语句查询某个变量分组后的数量值。
	 * @param sql sql语句。
	 * @param parameterNames 数量值count的参数名数组。
	 * @return 返回Map<String, Integer>类，key是参数名(和传入的参数名相同或和数据库字段名相同)，value是数量值。
	 */
	public Map<String, BigDecimal> executeQuery(String sql, String[] parameterNames) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		try {
			conn = DB.getConn();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			pstmt = DB.prepareStmt(conn, sql);
			rs = DB.executeQuery(pstmt);
			while(rs.next()) {
				String key = rs.getString(parameterNames[0]);
				BigDecimal value = rs.getBigDecimal(parameterNames[1]);
				result.put(key, value);
			}
		} catch (SQLException e) {
			logger.error("------------------sqlException-------------");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
			DB.close(conn);
		}		
		return result;
	}
	/**
	 * 通过存储过程查询。
	 * @param sql 调用存储过程的预编译的sql语句。
	 * @param returns Map<Integer, Object>类型，设定返回值所在占位符位置和返回值类型。若无，可设为null。
	 * @param parameters Map<Integer, Object>类型，设定各个参数所在占位符位置和参数值。若无，可设为null。
	 * @return 返回查询到的结果，结果装在一个Map里，key是该返回值所在的占位符位置，value是该返回值的值;若没有结果或执行错误，返回null。
	 */
	public Map<Integer, Object> executeProc(String sql, Map<Integer, Object> returns, Map<Integer, Object> parameters) {
		Connection conn = null;
		CallableStatement proc = null;
		Map<Integer, Object> results = new HashMap<Integer, Object>();
		Set<Entry<Integer, Object>> returnEntrys = null;
		Set<Entry<Integer, Object>> parameterEntrys = null;
		try {
			conn = DB.getConn();
			proc = DB.prepareCall(conn, sql);
			if(returns != null) {
				returnEntrys = returns.entrySet();
				for(Entry entry : returnEntrys) {
					if(entry.getValue().equals(String.class)) {
						proc.registerOutParameter((Integer)entry.getKey(), Types.VARCHAR);
					} else if (entry.getValue().equals(Integer.class)) {
						proc.registerOutParameter((Integer)entry.getKey(), Types.INTEGER);
					} else if(entry.getValue().equals(Double.class)) {
						proc.registerOutParameter((Integer)entry.getKey(), Types.DOUBLE);
					} else if (entry.getValue().equals(java.util.Date.class)) {
						proc.registerOutParameter((Integer)entry.getKey(), Types.TIMESTAMP);
					}
				}
			}
			if(parameters != null) {
				parameterEntrys = parameters.entrySet();
				for(Entry entry : parameterEntrys) {
					if(entry.getValue() instanceof String) {
						proc.setString((Integer)entry.getKey(), entry.getValue().toString());
					} else if (entry.getValue() instanceof Integer) {
						proc.setInt((Integer)entry.getKey(), (Integer)entry.getValue());
					} else if(entry.getValue() instanceof Double) {
						proc.setDouble((Integer)entry.getKey(), (Double)entry.getValue());
					} else if(entry.getValue() instanceof java.util.Date) {
						java.util.Date d = (java.util.Date)entry.getValue();
						java.sql.Timestamp timestamp = new java.sql.Timestamp(d.getTime());
						proc.setTimestamp((Integer)entry.getKey(), timestamp);
					} else if(entry.getValue() instanceof java.sql.Date) {
						java.sql.Date d = (java.sql.Date)entry.getValue();
						java.sql.Timestamp timestamp = new java.sql.Timestamp(d.getTime());
						proc.setTimestamp((Integer)entry.getKey(), timestamp);
					}
				}
			}
			if(DB.execute(proc)) {
				if(returnEntrys != null) {
					for(Entry entry : returnEntrys) {
						if(entry.getValue().equals(String.class)) {
							String result = proc.getString((Integer)entry.getKey());
							results.put((Integer)entry.getKey(), result);
						} else if (entry.getValue().equals(Integer.class)) {
							int result = proc.getInt((Integer)entry.getKey());
							results.put((Integer)entry.getKey(), result);
						} else if(entry.getValue().equals(Double.class)) {
							double result = proc.getDouble((Integer)entry.getKey());
							results.put((Integer)entry.getKey(), result);
						} else if (entry.getValue().equals(java.util.Date.class)) {
							java.sql.Date d = proc.getDate((Integer)entry.getKey());
							java.util.Date result = new java.util.Date(d.getTime());
							results.put((Integer)entry.getKey(), result);
						}
					}
					logger.info("proc获得返回值returns");
					return results;
				}
				logger.info("proc执行成功");
			} else {
				logger.info("proc执行错误");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(proc);
			DB.close(conn);
		}
		return null;
	}
	/**
	 * 通过sql语句和相关参数通过调用存储过程获得相关对象。
	 * @param sql sql语句
	 * @param parameters Map<Integer, Object>类型，设定返回值所在占位符位置和返回值类型。若无，可设为null。
	 * @param c 获得的类的Class。
	 * @return 返回获得的装有类的对象的List容器。
	 */
	public List<Object> executeProc(String sql, Map<Integer, Object> parameters, Class c) {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		List<Object> result = new ArrayList<Object>();
		Set<Entry<Integer, Object>> parameterEntrys = null;
		try {
			conn = DB.getConn();
			proc = DB.prepareCall(conn, sql);
			if(parameters != null) {
				parameterEntrys = parameters.entrySet();
				for(Entry entry : parameterEntrys) {
					if(entry.getValue() instanceof String) {
						proc.setString((Integer)entry.getKey(), entry.getValue().toString());
					} else if (entry.getValue() instanceof Integer) {
						proc.setInt((Integer)entry.getKey(), (Integer)entry.getValue());
					} else if(entry.getValue() instanceof Double) {
						proc.setDouble((Integer)entry.getKey(), (Double)entry.getValue());
					} else if(entry.getValue() instanceof java.util.Date) {
						java.util.Date d = (java.util.Date)entry.getValue();
						java.sql.Date date = new java.sql.Date(d.getTime());
						proc.setDate((Integer)entry.getKey(), date);
					} else if(entry.getValue() instanceof java.sql.Date) {
						proc.setDate((Integer)entry.getKey(), (java.sql.Date)entry.getValue());
					}
				}
			}
			rs = DB.executeQuery(proc);
			try {
				while(rs.next()) {
					Object o = c.newInstance();
					Method method = c.getDeclaredMethod("initFromRs", ResultSet.class);
					//反射执行该对象的initFromRs方法
					method.invoke(o, rs);
					result.add(o);
				}
			} catch (Exception e) {
				logger.error("initEntityFromRs反射过程出错，Exception为：" + e.getClass().getName());
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(proc);
			DB.close(conn);
		}
		return result;
	}
	/**
	 * 通过Class<T>类的对象获取对应的数据库中的表单名称。
	 * @param c 实体类对应的Class对象
	 * @return 成功返回表单名称，失败返回null。
	 */
	public String getTableName(Class<T> c) {
		try {
			T t = c.newInstance();
			Method DBMethod = c.getDeclaredMethod("getNameInDB", null);
			String tableName = DBMethod.invoke(t, null).toString();
			return tableName;
		} catch (Exception e) {
			logger.error("getTableName反射过程出错，Exception为：" + e.getClass().getName());
			e.printStackTrace();
		}
		return null; 
	}
	/**
	 * 通过ResultSet初始化对象。
	 * @param t 要被初始化的对象。
	 * @param rs 初始化所依据的结果集ResultSet。
	 */
	public void initEntityFromRs(T t, ResultSet rs) {
		try {
			Method method = t.getClass().getDeclaredMethod("initFromRs", ResultSet.class);
			//反射执行该对象的initFromRs方法
			method.invoke(t, rs);
		} catch (Exception e) {
			logger.error("initEntityFromRs反射过程出错，Exception为：" + e.getClass().getName());
			e.printStackTrace();
		}
	}
}
