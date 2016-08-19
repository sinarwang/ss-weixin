package com.ss.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.ss.adapter.DaoAdapter;
import com.ss.entity.WmsInboundDetail;
import com.ss.util.LoggerUtil;

public class WmsInboundDetailDAO extends DaoAdapter<WmsInboundDetail> {
	private static WmsInboundDetailDAO wmsInboundDetailDAO = null;
	private static Logger logger = LoggerUtil.getInstance();
	/**
	 * 获取WmsProductDAO的单例
	 * @return WmsProductDAO的单例
	 */
	public static WmsInboundDetailDAO getInstance() {
		if(wmsInboundDetailDAO == null) {
			wmsInboundDetailDAO = new WmsInboundDetailDAO();
		}
		return wmsInboundDetailDAO;
	}
	
	/**
	 * 获取所有产品库存对象
	 * @return 装有产品库存对象的List容器。
	 */
	public List<WmsInboundDetail> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select Pro_Name,D_Steel,D_Spec,D_Quantity,D_Weight,BinCode from wms_inbound_detail";
		List<WmsInboundDetail> wids = super.getViewEntitys(WmsInboundDetail.class, sql);
		logger.info("----------------get wmsInboundDetails succeed!-----------------");
		return wids;
	}
	
	/**
	 * 查找产品库存对象的总个数。
	 * @return 产品库存对象的总个数。
	 */
	public int getEntitysCount() {
		String sql = "select count(*) from wms_inbound_detail";
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
		String sql = "select sum(D_Weight) from wms_inbound_detail";
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
		String sql = "select sum(D_Quantity) from wms_inbound_detail";
		logger.info(sql);
		BigDecimal quantity = super.getCountFromSql(sql);
		logger.info(quantity);
		return quantity;
	}
}
