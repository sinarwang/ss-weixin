package com.ss.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ss.dao.ViewPeopleReportDAO;
import com.ss.dao.ViewProducemExceptionDAO;
import com.ss.entity.ViewPeopleReport;
import com.ss.entity.ViewProducemException;
import com.ss.entity.pojo.GoodUser;
import com.ss.util.LoggerUtil;

public class ViewPeopleReportService {
	private static ViewPeopleReportService viewPeopleReportService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static ViewPeopleReportDAO viewPeopleReportDAO = ViewPeopleReportDAO.getInstance();
	
	public static ViewPeopleReportService getInstance() {
		if(viewPeopleReportService == null) {
			viewPeopleReportService = new ViewPeopleReportService();
		}
		return viewPeopleReportService;
	}
	
	/**
	 * 获取当天的生产台账
	 * @return 装有生产台账对象的List容器。
	 */
	public List<ViewPeopleReport> getViewPeopleReports() {
		logger.info("Service -----------getViewPeopleReports");
		List<ViewPeopleReport> vprs = viewPeopleReportDAO.getViewEntitys();
		return vprs;
	}
	public int getEntitysCount() {
		int count = viewPeopleReportDAO.getEntitysCount();
		return count;
	}
	/**
	 * 根据工序名称获取当天的生产台账
	 * @param produceM_Name 工序名称
	 * @return 装有生产台账对象的List容器。
	 */
	public List<ViewPeopleReport> getVprsByProduceM_Name(String produceM_Name) {
		logger.info("Service -----------getViewPeopleReports");
		List<ViewPeopleReport> vprs = viewPeopleReportDAO.getViewEntitysByproduceM_Name(produceM_Name);
		return vprs;
	}
	/**
	 * 根据用户姓名获取当天的生产台账
	 * @param userName 用户姓名
	 * @return 装有生产台账对象的List容器。
	 */
	public List<ViewPeopleReport> getVprsByUserName(String userName) {
		logger.info("Service -----------getViewPeopleReports");
		List<ViewPeopleReport> vprs = viewPeopleReportDAO.getViewEntitysByUserName(userName);
		return vprs;
	}
	/**
	 * 获取当月优秀员工
	 * @return 装有优秀员工的List
	 */
	public List<GoodUser> getGoodUsers() {
		logger.info("Service -----------getViewPeopleReports");
		List<GoodUser> goodUsers = viewPeopleReportDAO.getGoodUsers();
		return goodUsers;
	}

	/**
	 * 查询当天生产台账的总重量和总支数。
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeiAndQua() {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal totalWeight = viewPeopleReportDAO.getTotalWeight();
		BigDecimal totalQuantity = viewPeopleReportDAO.getTotalQuantity();
		result.put("totalWeight", totalWeight);
		result.put("totalQuantity", totalQuantity);
		return result;
	}
	/**
	 * 通过工序名称，查询当天该工序生产台账的总重量和总支数。
	 * @param produceM_Name 工序名称
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeiAndQuaByPro_N(String produceM_Name) {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal totalWeight = viewPeopleReportDAO.getTotalWeightByProduceM_Name(produceM_Name);
		BigDecimal totalQuantity = viewPeopleReportDAO.getTotalQuantityByProduceM_Name(produceM_Name);
		result.put("totalWeight", totalWeight);
		result.put("totalQuantity", totalQuantity);
		return result;
	}
	/**
	 * 通过员工名称，查询当天该员工生产台账的总重量和总支数。
	 * @param userName 员工名称
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeiAndQuaByUserName(String userName) {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal totalWeight = viewPeopleReportDAO.getTotalWeightByUserName(userName);
		BigDecimal totalQuantity = viewPeopleReportDAO.getTotalQuantityByUserName(userName);
		result.put("totalWeight", totalWeight);
		result.put("totalQuantity", totalQuantity);
		return result;
	}
}
