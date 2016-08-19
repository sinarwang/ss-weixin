package com.ss.service;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ss.dao.ViewMyWorkflowTimeoutDAO;
import com.ss.entity.ViewMyWorkflowTimeout;
import com.ss.entity.pojo.WFTOWeightAndCardNos;
import com.ss.util.LoggerUtil;

public class ViewMyWorkflowTimeoutService {
	private static ViewMyWorkflowTimeoutService viewMyWorkflowTimeoutService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static ViewMyWorkflowTimeoutDAO viewMyWorkflowTimeoutDAO = ViewMyWorkflowTimeoutDAO.getInstance();
	
	public static ViewMyWorkflowTimeoutService getInstance() {
		if(viewMyWorkflowTimeoutService == null) {
			viewMyWorkflowTimeoutService = new ViewMyWorkflowTimeoutService();
		}
		return viewMyWorkflowTimeoutService;
	}
	/**
	 * 获取超排程的List
	 * @return 装有超排程对象的List容器
	 */
	public List<ViewMyWorkflowTimeout> getWorkFlowTimeOuts() {
		logger.info("Service -----------getWorkFlowTimeOuts");
		List<ViewMyWorkflowTimeout> wftos = viewMyWorkflowTimeoutDAO.getViewEntitys();
		return wftos;
	}
	/**
	 * 获取合同号的数量和超排程对象的总个数。
	 * @return 返回装有超排程的合同号数量(cc)和超排程对象的总个数(ec)的Map容器，可以通过key值（cc和ec），得到对应的数量值。
	 */
	public Map<String, Integer> getCcAndEc() {
		Map<String, Integer> ccAndEc = viewMyWorkflowTimeoutDAO.getCcAndEc();
		logger.info("Service -----------getContractNoCount:" + ccAndEc.get("cc") + "----getentitysCount:" + ccAndEc.get("ec"));
		return ccAndEc;
	}
	/**
	 * 获取超排程框数。
	 * @return int类型的超排程框数
	 */
	public int getEntitysCount() {
		int count = viewMyWorkflowTimeoutDAO.getEntitysCount();
		logger.info("Service -----------getEntitysCount:" + count);
		return count;
	}
	/**
	 * 拿到装有WFTOWeightAndCardNos的pojo类的List容器
	 * @return 装有WFTOWeightAndCardNos的pojo类的List容器
	 */
	public List<WFTOWeightAndCardNos> getWeightAndCardNos() {
		List<WFTOWeightAndCardNos> wtAndQts = viewMyWorkflowTimeoutDAO.getWeightAndCardNos();
		logger.info("Service -----------getWeightAndCardNos");
		return wtAndQts;
	}
}
