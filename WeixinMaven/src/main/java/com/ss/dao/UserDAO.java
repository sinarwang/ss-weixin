package com.ss.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ss.adapter.DaoAdapter;
import com.ss.entity.User;
import com.ss.util.DB;

public class UserDAO extends DaoAdapter<User> {
	private static UserDAO userDAO = null;
	/**
	 * 获取UserDAO的单例
	 * @return UserDAO的单例
	 */
	public static UserDAO getInstance() {
		if(userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}
	
	/**
	 * 获取所有用户对象
	 * @return 装有用户对象的List容器。
	 */
	public List<User> getEntitys() {
		List<User> users = super.getEntitys(User.class);
		logger.error("----------------get Users succeed!-----------------");
		return users;
	}
	@Override
	public boolean add(User t) {
		String sql = "insert into weixin_user1(openId,userId,userName,password,createDate) values (?,?,?,?,?)";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(1, t.getOpenId());
		parameters.put(2, t.getUserId());
		parameters.put(3, t.getUserName());
		parameters.put(4, t.getPassword());
		parameters.put(5, t.getCreateDate());
		
		boolean result = executeUpdate(sql, parameters);
		return result;
		//logger.error("----------------User saved!-----------------");
	}
	@Override
	public boolean update(User t) {
		String sql = "update weixin_user1 set userName=?, password=?, audit=?, disable=? where id=" + t.getId();
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(1, t.getUserName());
		parameters.put(2, t.getPassword());
		parameters.put(3, t.getAudit());
		parameters.put(4, t.getDisable());
		
		boolean isSuccess = executeUpdate(sql, parameters);
		if(isSuccess) {
			logger.error("----------------User modified succeed!-----------------");
		} else {
			logger.error("----------------User modified failed!-----------------");
		}
		return isSuccess;
	}
	@Override
	public User getEntityByPK(Class<User> c, Object pk) {
		User user = super.getEntityByPK(c, pk);
		if(user != null && user.getOpenId() != null && !user.getOpenId().equals("")) {
			logger.error("------------get User openId=" + user.getOpenId() + "User Name:" + user.getUserName());
		} else {
			logger.error("----------------don't get User!-----------------");
			return null;
		}
		return user;
	}
	/**
	 * 根据微信openId查询用户类。
	 * @param openId 用户的微信openId
	 * @return 用户类的对象
	 */
	public User getEntityByOpenId(String openId) {
		String sql = "select * from weixin_user1 where openId='" + openId + "'";
		User user = super.getEntityFromSql(sql, User.class);
		if(user != null && user.getOpenId() != null && !user.getOpenId().equals("")) {
			logger.error("------------get User openId=" + user.getOpenId() + "User Name:" + user.getUserName());
		} else {
			logger.error("----------------don't get User!-----------------");
			return null;
		}
		return user;
	}
	/**
	 * 根据工号查询用户类。
	 * @param userId 工号
	 * @return 用户类的对象，若没有查找到用户，返回null。
	 */
	public User getEntityByUserId(String userId) {
		String sql = "select * from weixin_user1 where userId='" + userId + "'";
		User user = super.getEntityFromSql(sql, User.class);
		if(user != null && user.getOpenId() != null && !user.getOpenId().equals("")) {
			logger.error("------------get User openId=" + user.getOpenId() + "User Name:" + user.getUserName());
		} else {
			logger.error("----------------don't get User!-----------------");
			return null;
		}
		return user;
	}
	/**
	 * 根据工号和密码取得用户类。
	 * @param userId 工号
	 * @param password 密码
	 * @return 用户类的对象，若没有查找到用户，返回null。
	 */
	public User getEntityByUiAndPw(String userId, String password) {
		String sql = "select * from weixin_user1 where userId='" + userId + "' and password='" + password + "'";
		User user = super.getEntityFromSql(sql, User.class);
		if(user != null && user.getOpenId() != null && !user.getOpenId().equals("")) {
			logger.error("------------get User userId=" + user.getUserId() + " id:" + user.getId() + " openId=" + user.getOpenId());
		} else {
			logger.error("----------------don't get User!-----------------");
			return null;
		}
		return user;
	}
	@Override
	public boolean delete(User t) {
		String sql = "delete from weixin_user1 where openId='" + t.getOpenId() + "'";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(1, t.getOpenId());
		logger.error(sql);
		boolean isSuccess = super.executeUpdate(sql, null);
		if(isSuccess) {
			logger.error("----------------user delete succeed!-----------------");
		} else {
			logger.error("----------------user delete failed!-----------------");
		}
		return isSuccess;
	}
	@Override
	public void deleteEntitys(Object[] pks) {
		super.deleteEntitys(pks);
		logger.error("----------------users delete succeed!-----------------");
	}
	/**
	 * 根据id查询用户类。
	 * @param id 用户id
	 * @return 用户类的对象，若没有查找到用户，返回null。
	 */
	public User getEntityById(int id) {
		String sql = "select * from weixin_user1 where id=" + id + "";
		User user = super.getEntityFromSql(sql, User.class);
		if(user != null && user.getOpenId() != null && !user.getOpenId().equals("")) {
			logger.error("------------get User openId=" + user.getOpenId() + "User Name:" + user.getUserName());
		} else {
			logger.error("----------------don't get User!-----------------");
			return null;
		}
		return user;
	}
	
	//测试删除用户
	/*public void run() {
		Connection conn = null;
		Statement stmt = null;
		conn = DB.getConn();
		stmt = DB.createStmt(conn);
		String sql = "delete from weixin_user where openId='ouUHiw63dV3ST1xfDtrJR_tS0ED8'";
			
		DB.executeUpdate(stmt, sql);
		DB.close(stmt);
		DB.close(conn);
		System.out.print("delete success");
	}
	public static void main(String[] args) {
		UserDAO.getInstance().run();
	}*/
}
