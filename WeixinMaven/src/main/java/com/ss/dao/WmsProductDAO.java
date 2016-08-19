package com.ss.dao;

import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;
import com.ss.adapter.DaoAdapter;
import com.ss.entity.WmsProduct;
import com.ss.util.LoggerUtil;

public class WmsProductDAO extends DaoAdapter<WmsProduct> {
	private static WmsProductDAO wmsProductDAO = null;
	private static Logger logger = LoggerUtil.getInstance();
	/**
	 * 获取WmsProductDAO的单例
	 * @return WmsProductDAO的单例
	 */
	public static WmsProductDAO getInstance() {
		if(wmsProductDAO == null) {
			wmsProductDAO = new WmsProductDAO();
		}
		return wmsProductDAO;
	}
	
	/**
	 * 获取所有产品库存对象
	 * @return 装有产品库存对象的List容器。
	 */
	public List<WmsProduct> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select Pro_Name,D_Steel,D_Spec,D_Quantity,D_Weight,Bin from wms_product";
		List<WmsProduct> wps = super.getViewEntitys(WmsProduct.class, sql);
		logger.info("----------------get wmsProducts succeed!-----------------");
		return wps;
	}
	
	/**
	 * 查找产品库存对象的总个数。
	 * @return 产品库存对象的总个数。
	 */
	public int getEntitysCount() {
		String sql = "select count(*) from wms_product";
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	
	/**
	 * 查找所有产品库存的总重量
	 * @return 产品库存总重量（KG）
	 */
	public BigDecimal getTotalWeight() {
		String sql = "select sum(D_Weight) from wms_product";
		logger.info(sql);
		BigDecimal weight = super.getCountFromSql(sql);
		logger.info(weight);
		return weight;
	}
	/**
	 * 查找所有产品库存的总支数
	 * @return 产品库存总支数
	 */
	public BigDecimal getTotalQuantity() {
		String sql = "select sum(D_Quantity) from wms_product";
		logger.info(sql);
		BigDecimal quantity = super.getCountFromSql(sql);
		logger.info(quantity);
		return quantity;
	}
}
