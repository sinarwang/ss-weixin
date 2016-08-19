package com.ss.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ss.dao.WmsProductDAO;
import com.ss.entity.WmsProduct;
import com.ss.util.LoggerUtil;

public class WmsProductService {
	private static WmsProductService wmsProductService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static WmsProductDAO wmsProductDAO = WmsProductDAO.getInstance();
	
	public static WmsProductService getInstance() {
		if(wmsProductService == null) {
			wmsProductService = new WmsProductService();
		}
		return wmsProductService;
	}
	
	/**
	 * 获取所有产品库存的List
	 * @return 装有产品库存对象的List容器
	 */
	public List<WmsProduct> getWmsProducts() {
		logger.info("Service -----------getWmsProducts");
		List<WmsProduct> wps = wmsProductDAO.getViewEntitys();
		return wps;
	}
	
	public int getEntitysCount() {
		int count = wmsProductDAO.getEntitysCount();
		return count;
	}
	/**
	 * 获取所有产品库存的总重量
	 * @return 所有产品库存的总重量
	 */
	public BigDecimal getTotalWeight() {
		BigDecimal weight = wmsProductDAO.getTotalWeight();
		return weight;
	}
	/**
	 * 查询所有产品库存的总重量和总支数。
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeiAndQua() {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal totalWeight = wmsProductDAO.getTotalWeight();
		BigDecimal totalQuantity = wmsProductDAO.getTotalQuantity();
		result.put("totalWeight", totalWeight);
		result.put("totalQuantity", totalQuantity);
		return result;
	}
}
