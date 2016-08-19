package com.ss.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ss.adapter.DaoAdapter;
import com.ss.entity.ViewDailyfeedingtube;
import com.ss.entity.ViewProducemException;
import com.ss.util.LoggerUtil;
import com.ss.util.TimeUtil;

public class ViewDailyfeedingtubeDAO extends DaoAdapter<ViewDailyfeedingtube> {
	private static ViewDailyfeedingtubeDAO viewDailyfeedingtubeDAO = null;
	private static Logger logger = LoggerUtil.getInstance();
	/**
	 * 获取ViewDailyfeedingtubeDAO的单例
	 * @return ViewDailyfeedingtubeDAO的单例
	 */
	public static ViewDailyfeedingtubeDAO getInstance() {
		if(viewDailyfeedingtubeDAO == null) {
			viewDailyfeedingtubeDAO = new ViewDailyfeedingtubeDAO();
		}
		return viewDailyfeedingtubeDAO;
	}
	/**
	 * 获取所有荒管投料对象
	 * @return 装有荒管投料对象的List容器。
	 */
	public List<ViewDailyfeedingtube> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select FeedingDate,ProduceM_BillNo,ContractNo,D_Spec,D_Quantity,D_Weight,D_TotalWeight,D_FurnaceNo from view_dailyfeedingtube where FeedingDate>'" + TimeUtil.beginMonth() + "'";
		List<ViewDailyfeedingtube> vdfts = super.getViewEntitys(ViewDailyfeedingtube.class, sql);
		logger.info("----------------get viewDailyfeedingtubes succeed!-----------------");
		return vdfts;
	}
	/**
	 * 查找荒管投料对象的总个数。
	 * @return 荒管投料对象的总个数。
	 */
	public int getEntitysCount() {
		String sql = "select count(*) from view_dailyfeedingtube where FeedingDate>'" + TimeUtil.beginMonth() + "'";
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	/**
	 * 查找本月荒管投料总重量
	 * @return 本月荒管投料总重量(KG)
	 */
	public BigDecimal getTotalWeight() {
		String sql = "select sum(D_TotalWeight) from view_dailyfeedingtube where FeedingDate>'" + TimeUtil.beginMonth() + "'";
		logger.info(sql);
		BigDecimal totalWeight = super.getCountFromSql(sql);
		logger.info(totalWeight);
		return totalWeight;
	}
	
	/**
	 * 查找本月荒管投料总支数
	 * @return 本月荒管投料总支数
	 */
	public BigDecimal getTotalQuantity() {
		String sql = "select sum(D_Quantity) from view_dailyfeedingtube where FeedingDate>'" + TimeUtil.beginMonth() + "'";
		logger.info(sql);
		BigDecimal totalQuantity = super.getCountFromSql(sql);
		logger.info(totalQuantity);
		return totalQuantity;
	}
}
