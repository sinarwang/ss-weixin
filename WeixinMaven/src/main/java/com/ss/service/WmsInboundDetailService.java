package com.ss.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ss.dao.WmsInboundDetailDAO;
import com.ss.entity.WmsInboundDetail;
import com.ss.util.LoggerUtil;

public class WmsInboundDetailService {
	private static WmsInboundDetailService wmsInboundDetailService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static WmsInboundDetailDAO wmsInboundDetailDAO = WmsInboundDetailDAO.getInstance();
	
	public static WmsInboundDetailService getInstance() {
		if(wmsInboundDetailService == null) {
			wmsInboundDetailService = new WmsInboundDetailService();
		}
		return wmsInboundDetailService;
	}
	
	/**
	 * 获取所有产品库存的List
	 * @return 装有产品库存对象的List容器
	 */
	public List<WmsInboundDetail> getWmsInboundDetails() {
		logger.info("Service -----------getWmsInboundDetails");
		List<WmsInboundDetail> wps = wmsInboundDetailDAO.getViewEntitys();
		return wps;
	}
	
	public int getEntitysCount() {
		int count = wmsInboundDetailDAO.getEntitysCount();
		return count;
	}
	/**
	 * 获取所有产品库存的总重量
	 * @return 所有产品库存的总重量
	 */
	public BigDecimal getTotalWeight() {
		BigDecimal weight = wmsInboundDetailDAO.getTotalWeight();
		return weight;
	}
	/**
	 * 查询所有产品库存的总重量和总支数。
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeiAndQua() {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal totalWeight = wmsInboundDetailDAO.getTotalWeight();
		BigDecimal totalQuantity = wmsInboundDetailDAO.getTotalQuantity();
		result.put("totalWeight", totalWeight);
		result.put("totalQuantity", totalQuantity);
		return result;
	}
}
