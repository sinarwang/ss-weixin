package com.ss.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ss.dao.ViewMyWorkflowCompleteDAO;
import com.ss.dao.ViewMyWorkflowTimeoutDAO;
import com.ss.entity.ViewMyWorkflowComplete;
import com.ss.entity.ViewMyWorkflowTimeout;
import com.ss.util.LoggerUtil;

public class ViewMyWorkflowCompleteService {
	private static ViewMyWorkflowCompleteService viewMyWorkflowCompleteService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static ViewMyWorkflowCompleteDAO viewMyWorkflowCompleteDAO = ViewMyWorkflowCompleteDAO.getInstance();
	
	public static ViewMyWorkflowCompleteService getInstance() {
		if(viewMyWorkflowCompleteService == null) {
			viewMyWorkflowCompleteService = new ViewMyWorkflowCompleteService();
		}
		return viewMyWorkflowCompleteService;
	}
	/**
	 * 获取成品的List
	 * @return 装有成品对象的List容器
	 */
	public List<ViewMyWorkflowComplete> getWorkFlowCompletes() {
		logger.info("Service -----------getWorkFlowCompletes");
		List<ViewMyWorkflowComplete> vwfcs = viewMyWorkflowCompleteDAO.getViewEntitys();
		return vwfcs;
	}
	public int getEntitysCount() {
		int count = viewMyWorkflowCompleteDAO.getEntitysCount();
		logger.info("Service -----------getEntitysCount:" + count);
		return count;
	}
	
	/**
	 * 查询本月成品对象的总重量和总支数。
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeiAndQua() {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal totalWeight = viewMyWorkflowCompleteDAO.getTotalWeight();
		BigDecimal totalQuantity = viewMyWorkflowCompleteDAO.getTotalQuantity();
		result.put("totalWeight", totalWeight);
		result.put("totalQuantity", totalQuantity);
		return result;
	}
}
