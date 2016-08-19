package com.ss.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.ss.dao.ViewDailyfeedingtubeDAO;
import com.ss.dao.ViewProducemExceptionDAO;
import com.ss.entity.ViewDailyfeedingtube;
import com.ss.entity.ViewProducemException;
import com.ss.util.LoggerUtil;

public class ViewDailyfeedingtubeService {
	private static ViewDailyfeedingtubeService viewDailyfeedingtubeService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static ViewDailyfeedingtubeDAO viewDailyfeedingtubeDAO = ViewDailyfeedingtubeDAO.getInstance();
	
	public static ViewDailyfeedingtubeService getInstance() {
		if(viewDailyfeedingtubeService == null) {
			viewDailyfeedingtubeService = new ViewDailyfeedingtubeService();
		}
		return viewDailyfeedingtubeService;
	}
	
	/**
	 * 获取生产异常的List
	 * @return 装有生产异常对象的List容器
	 */
	public List<ViewDailyfeedingtube> getViewDailyfeedingtubes() {
		logger.info("Service -----------getViewDailyfeedingtubes");
		List<ViewDailyfeedingtube> vdfts = viewDailyfeedingtubeDAO.getViewEntitys();
		return vdfts;
	}
	
	public int getEntitysCount() {
		int count = viewDailyfeedingtubeDAO.getEntitysCount();
		return count;
	}
	/**
	 * 获取本月荒管投料总重量
	 * @return 本月荒管投料总重量
	 */
	public BigDecimal getTotalWeight() {
		BigDecimal totalWeight = viewDailyfeedingtubeDAO.getTotalWeight();
		return totalWeight;
	}
	/**
	 * 获取本月荒管投料总重量和总支数
	 * @return  Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeiAndQua() {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal totalWeight = viewDailyfeedingtubeDAO.getTotalWeight();
		BigDecimal totalQuantity = viewDailyfeedingtubeDAO.getTotalQuantity();
		result.put("totalWeight", totalWeight);
		result.put("totalQuantity", totalQuantity);
		return result;
	}
}
