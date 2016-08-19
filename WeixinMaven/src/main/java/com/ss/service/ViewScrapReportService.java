package com.ss.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.ss.dao.ViewProducemExceptionDAO;
import com.ss.dao.ViewScrapReportDAO;
import com.ss.entity.ViewProducemException;
import com.ss.entity.ViewScrapReport;
import com.ss.entity.pojo.ProNWeiAndQua;
import com.ss.util.LoggerUtil;

public class ViewScrapReportService {
	private static ViewScrapReportService viewScrapReportService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static ViewScrapReportDAO viewScrapReportDAO = ViewScrapReportDAO.getInstance();
	
	public static ViewScrapReportService getInstance() {
		if(viewScrapReportService == null) {
			viewScrapReportService = new ViewScrapReportService();
		}
		return viewScrapReportService;
	}
	
	/**
	 * 获取生产报废的List
	 * @return 装有生产报废对象的List容器
	 */
	public List<ViewScrapReport> getViewScrapReports() {
		logger.info("Service -----------getViewScrapReports");
		List<ViewScrapReport> vsrs = viewScrapReportDAO.getViewEntitys();
		return vsrs;
	}
	
	public int getEntitysCount() {
		int count = viewScrapReportDAO.getEntitysCount();
		return count;
	}

	/**
	 * 获取从上月1日至今，生产报废总重量
	 * @return 从上月1日至今，生产报废总重量
	 */
	public BigDecimal getTotalWeight() {
		BigDecimal weight = viewScrapReportDAO.getTotalWeight();
		return weight;
	}
	
	/**
	 * 查询从上月1日至今，生产报废总重量和总支数。
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeiAndQua() {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal totalWeight = viewScrapReportDAO.getTotalWeight();
		BigDecimal totalQuantity = viewScrapReportDAO.getTotalQuantity();
		result.put("totalWeight", totalWeight);
		result.put("totalQuantity", totalQuantity);
		return result;
	}
	
	/**
	 * 获取从上月1日至今，各个工序的报废总重量和总支数。
	 * @return List<ProNWeiAndQua>类，ProNWeiAndQua的pojo类中包含有相关信息。
	 */
	public List<ProNWeiAndQua> getProNWeiAndQuas() {
		List<ProNWeiAndQua> proNWeiAndQuas = viewScrapReportDAO.getProNWeiAndQuas();
		return proNWeiAndQuas;
	}
}
