package com.ss.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ss.dao.ProducemWorkorderDAO;
import com.ss.entity.ProducemWorkorder;
import com.ss.util.LoggerUtil;

public class ProducemWorkorderService {
	private static ProducemWorkorderService producemWorkorderService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static ProducemWorkorderDAO producemWorkorderDAO = ProducemWorkorderDAO.getInstance();
	
	public static ProducemWorkorderService getInstance() {
		if(producemWorkorderService == null) {
			producemWorkorderService = new ProducemWorkorderService();
		}
		return producemWorkorderService;
	}
	
	/**
	 * 获取当天的生产工单
	 * @return 装有生产工单对象的List容器。
	 */
	public List<ProducemWorkorder> getProducemWorkorders() {
		logger.info("Service -----------getProducemWorkorders");
		List<ProducemWorkorder> vprs = producemWorkorderDAO.getViewEntitys();
		return vprs;
	}
	public int getEntitysCount() {
		int count = producemWorkorderDAO.getEntitysCount();
		return count;
	}
	/**
	 * 根据工序名称获取当天的生产工单
	 * @param produceM_Name 工序名称
	 * @return 装有生产工单对象的List容器。
	 */
	public List<ProducemWorkorder> getPwosByProduceM_Name(String produceM_Name) {
		logger.info("Service -----------getProducemWorkorders");
		List<ProducemWorkorder> vprs = producemWorkorderDAO.getViewEntitysByproduceM_Name(produceM_Name);
		return vprs;
	}
	/**
	 * 查询当天生产工单的总重量和总支数。
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeiAndQua() {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal totalWeight = producemWorkorderDAO.getTotalWeight();
		BigDecimal totalQuantity = producemWorkorderDAO.getTotalQuantity();
		result.put("totalWeight", totalWeight);
		result.put("totalQuantity", totalQuantity);
		return result;
	}
	/**
	 * 通过工序名称，查询当天该工序生产工单的总重量和总支数。
	 * @param produceM_Name 工序名称
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeiAndQuaByPro_N(String produceM_Name) {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal totalWeight = producemWorkorderDAO.getTotalWeightByProduceM_Name(produceM_Name);
		BigDecimal totalQuantity = producemWorkorderDAO.getTotalQuantityByProduceM_Name(produceM_Name);
		result.put("totalWeight", totalWeight);
		result.put("totalQuantity", totalQuantity);
		return result;
	}
}
