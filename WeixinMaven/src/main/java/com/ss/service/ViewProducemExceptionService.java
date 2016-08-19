package com.ss.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ss.dao.ViewProducemExceptionDAO;
import com.ss.entity.ViewProducemException;
import com.ss.util.LoggerUtil;

public class ViewProducemExceptionService {
	private static ViewProducemExceptionService viewProducemExceptionService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static ViewProducemExceptionDAO viewProducemExceptionDAO = ViewProducemExceptionDAO.getInstance();
	
	public static ViewProducemExceptionService getInstance() {
		if(viewProducemExceptionService == null) {
			viewProducemExceptionService = new ViewProducemExceptionService();
		}
		return viewProducemExceptionService;
	}
	
	/**
	 * 获取生产异常的List
	 * @return 装有生产异常对象的List容器
	 */
	public List<ViewProducemException> getViewProducemExceptions() {
		logger.info("Service -----------getViewProducemExceptions");
		List<ViewProducemException> vpes = viewProducemExceptionDAO.getViewEntitys();
		return vpes;
	}
	/**
	 * 查询各个工序的生产异常数量。
	 * @return Map<String, BigDecimal>类型，key值是工序名称，value是该工序的生产异常数量。
	 */
	public Map<String, BigDecimal> getCountsFromProduceM_Name() {
		Map<String, BigDecimal> result = viewProducemExceptionDAO.getCountsFromProduceM_Name();
		return result;
	}
	
	/**
	 * 查询本月各个工序的生产异常重量。
	 * @return Map<String, BigDecimal>类型，key值是工序名称，value是该工序的生产异常重量。
	 */
	public Map<String, BigDecimal> getWeightsFromProduceM_Name() {
		Map<String, BigDecimal> result = viewProducemExceptionDAO.getWeightsFromProduceM_Name();
		return result;
	}
	/**
	 * 查询本月各类生产异常的重量。
	 * @return Map<String, BigDecimal>类型，key值是工序名称，value是该类别的生产异常重量。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getWeightsFromD_Type() {
		Map<String, BigDecimal> result = viewProducemExceptionDAO.getWeightsFromD_Type();
		return result;
	}
	
	public int getEntitysCount() {
		int count = viewProducemExceptionDAO.getEntitysCount();
		return count;
	}
	/**
	 * 获取本月生产异常重量总和。
	 * @return BigDecimal类型，本月生产异常重量总和。
	 */
	public BigDecimal getTotalWeight() {
		BigDecimal weight = viewProducemExceptionDAO.getTotalWeight();
		return weight;
	}
}
