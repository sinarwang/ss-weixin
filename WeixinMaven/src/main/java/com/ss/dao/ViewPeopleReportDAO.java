package com.ss.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.ss.adapter.DaoAdapter;
import com.ss.entity.ViewPeopleReport;
import com.ss.entity.ViewProducemException;
import com.ss.entity.pojo.GoodUser;
import com.ss.util.LoggerUtil;
import com.ss.util.TimeUtil;

public class ViewPeopleReportDAO extends DaoAdapter<ViewPeopleReport> {
	private static ViewPeopleReportDAO viewPeopleReportDAO = null;
	private static Logger logger = LoggerUtil.getInstance();
	/**
	 * 获取ViewProducemExceptionDAO的单例
	 * @return ViewProducemExceptionDAO的单例
	 */
	public static ViewPeopleReportDAO getInstance() {
		if(viewPeopleReportDAO == null) {
			viewPeopleReportDAO = new ViewPeopleReportDAO();
		}
		return viewPeopleReportDAO;
	}
	/**
	 * 获取当天的生产台账
	 * @return 装有生产台账对象的List容器。
	 */
	public List<ViewPeopleReport> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select D_UserName,ProduceM_BillNo,ProduceM_Name,avgnumber,avgweight from view_people_report where D_END>='" + TimeUtil.beginDay() + "'";
		List<ViewPeopleReport> vprs = super.getViewEntitys(ViewPeopleReport.class, sql);
		logger.info("----------------get viewPeopleReports succeed!-----------------");
		return vprs;
	}
	/**
	 * 获取当天的生产台账的总个数。
	 * @return 当天的生产台账的总个数。
	 */
	public int getEntitysCount() {
		String sql = "select count(*) from view_people_report where D_End>'" + TimeUtil.beginDay() + "'";
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	/**
	 * 根据用户姓名获取当天的生产台账
	 * @param userName 用户姓名
	 * @return 装有生产台账对象的List容器。
	 */
	public List<ViewPeopleReport> getViewEntitysByUserName(String userName) {
		logger.info("DAO----getViewEntitys");
		String sql = "select D_UserName,ProduceM_BillNo,ProduceM_Name,avgnumber,avgweight from view_people_report where D_UserName='" + userName + "' and D_END>='" + TimeUtil.beginDay() + "'";
		List<ViewPeopleReport> vprs = super.getViewEntitys(ViewPeopleReport.class, sql);
		logger.info("----------------get viewPeopleReports succeed!-----------------");
		return vprs;
	}
	/**
	 * 根据工序名称获取当天的生产台账
	 * @param produceM_Name 工序名称
	 * @return 装有生产台账对象的List容器。
	 */
	public List<ViewPeopleReport> getViewEntitysByproduceM_Name(String produceM_Name) {
		logger.info("DAO----getViewEntitys");
		String sql = "select D_UserName,ProduceM_BillNo,ProduceM_Name,avgnumber,avgweight from view_people_report where produceM_Name='" + produceM_Name + "' and D_END>='" + TimeUtil.beginDay() + "'";
		List<ViewPeopleReport> vprs = super.getViewEntitys(ViewPeopleReport.class, sql);
		logger.info("----------------get viewPeopleReports succeed!-----------------");
		return vprs;
	}
	/**
	 * 获取当月最优秀员工。
	 * @return List<GoodUser>类，装有当月最优秀员工的类的对象。
	 */
	/*public List<GoodUser> getGoodUsers() {
		logger.info("DAO----getViewEntitys");
		String sql = "CALL proc_CountExcEmp(?,?)";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		String beginMonth = TimeUtil.beginMonth();
		String nextMonth = TimeUtil.nextMonth();
		parameters.put(1, beginMonth);
		parameters.put(2, nextMonth);
		
		List<Object> objects = super.executeProc(sql, parameters, GoodUser.class);
		List<GoodUser> goodUsers = new ArrayList<GoodUser>();
		for(Object o : objects) {
			goodUsers.add((GoodUser)o);
		}
		logger.info("----------------get goodUsers succeed!-----------------" + goodUsers.size());
		return goodUsers;
	}*/
	public List<GoodUser> getGoodUsers() {
		logger.info("DAO----getViewEntitys");
		String sql = "select * from exc_emp_count";
		List<Object> objects = super.getEntitysFromSql(sql, GoodUser.class);
		List<GoodUser> goodUsers = new ArrayList<GoodUser>();
		for(Object o : objects) {
			goodUsers.add((GoodUser)o);
		}
		logger.info("----------------get goodUsers succeed!-----------------" + goodUsers.size());
		return goodUsers;
	}
	
	/**
	 * 查找当天生产台账的总重量
	 * @return 当天生产台账的总重量(KG)
	 */
	public BigDecimal getTotalWeight() {
		String sql_weight = "select sum(avgweight) from view_people_report where D_END>='" + TimeUtil.beginDay() + "'";
		
		BigDecimal totalWeight = super.getCountFromSql(sql_weight);
		return totalWeight;
	}
	/**
	 * 查找当天生产台账的总支数
	 * @return 当天生产台账的总支数
	 */
	public BigDecimal getTotalQuantity() {
		String sql_quantity = "select sum(avgnumber) from view_people_report where D_END>='" + TimeUtil.beginDay() + "'";
		
		BigDecimal totalQuantity = super.getCountFromSql(sql_quantity);
		return totalQuantity;
	}
	/**
	 * 根据员工姓名，查找当天该员工生产台账的总重量
	 * @param userName 员工姓名
	 * @return 当天该员工生产台账的总重量(KG)
	 */
	public BigDecimal getTotalWeightByUserName(String userName) {
		String sql_weight = "select sum(avgweight) from view_people_report where D_UserName='" + userName + "' and D_END>='" + TimeUtil.beginDay() + "'";
		BigDecimal totalWeight = super.getCountFromSql(sql_weight);
		return totalWeight;
	}
	/**
	 * 根据员工姓名，查找当天该员工生产台账的总支数
	 * @param userName 员工姓名
	 * @return 当天该员工生产台账的总支数
	 */
	public BigDecimal getTotalQuantityByUserName(String userName) {
		String sql_quantity = "select sum(avgnumber) from view_people_report where D_UserName='" + userName + "' and D_END>='" + TimeUtil.beginDay() + "'";
		
		BigDecimal totalQuantity = super.getCountFromSql(sql_quantity);
		return totalQuantity;
	}
	/**
	 * 根据工序名称，查找当天该工序生产台账的总重量
	 * @param produceM_Name 工序名称
	 * @return 当天该工序生产台账的总重量(KG)
	 */
	public BigDecimal getTotalWeightByProduceM_Name(String produceM_Name) {
		String sql_weight = "select sum(avgweight) from view_people_report where produceM_Name='" + produceM_Name + "' and D_END>='" + TimeUtil.beginDay() + "'";
		
		BigDecimal totalWeight = super.getCountFromSql(sql_weight);
		return totalWeight;
	}
	/**
	 * 根据工序名称，查找当天该工序生产台账的总支数
	 * @param produceM_Name 工序名称
	 * @return 当天该工序生产台账的总支数
	 */
	public BigDecimal getTotalQuantityByProduceM_Name(String produceM_Name) {
		String sql_quantity = "select sum(avgnumber) from view_people_report where produceM_Name='" + produceM_Name + "' and D_END>='" + TimeUtil.beginDay() + "'";
		
		BigDecimal totalQuantity = super.getCountFromSql(sql_quantity);
		return totalQuantity;
	}
}
