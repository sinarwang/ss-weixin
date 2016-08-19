package com.ss.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.ss.adapter.DaoAdapter;
import com.ss.entity.ViewScrapReport;
import com.ss.entity.pojo.GoodUser;
import com.ss.entity.pojo.ProNWeiAndQua;
import com.ss.util.LoggerUtil;
import com.ss.util.TimeUtil;

public class ViewScrapReportDAO extends DaoAdapter<ViewScrapReport> {
	private static ViewScrapReportDAO viewScrapReportDAO = null;
	private static Logger logger = LoggerUtil.getInstance();
	/**
	 * 获取ViewScrapReportDAO的单例
	 * @return ViewScrapReportDAO的单例
	 */
	public static ViewScrapReportDAO getInstance() {
		if(viewScrapReportDAO == null) {
			viewScrapReportDAO = new ViewScrapReportDAO();
		}
		return viewScrapReportDAO;
	}
	
	/**
	 * 获取所有生产报废对象
	 * @return 装有生产报废对象的List容器。
	 */
	public List<ViewScrapReport> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select CardNo,ContractNo,ProduceM_Name,D_Quantity1,D_Weight,D_Numbers,D_User,D_Date from view_scrap_report where D_Date>'" + TimeUtil.lastMonth() + "'";
		List<ViewScrapReport> vpes = super.getViewEntitys(ViewScrapReport.class, sql);
		logger.info("----------------get viewScrapReports succeed!-----------------");
		return vpes;
	}
	
	/**
	 * 查找生产报废对象的总个数。
	 * @return 生产报废对象的总个数。
	 */
	public int getEntitysCount() {
		String sql = "select count(*) from view_scrap_report where D_Date>'" + TimeUtil.lastMonth() + "'";
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	
	/**
	 * 查找从上月1日至今，生产报废的总重量
	 * @return 从上月1日至今，生产报废总重量（KG）
	 */
	public BigDecimal getTotalWeight() {
		String sql = "select sum(D_Weight) from view_scrap_report where D_Date>'" + TimeUtil.lastMonth() + "'";
		logger.info(sql);
		BigDecimal weight = super.getCountFromSql(sql);
		logger.info(weight);
		return weight;
	}
	/**
	 * 查找从上月1日至今，生产报废的总支数
	 * @return 从上月1日至今，生产报废总支数
	 */
	public BigDecimal getTotalQuantity() {
		String sql = "select sum(D_Quantity1) from view_scrap_report where D_Date>'" + TimeUtil.lastMonth() + "'";
		logger.info(sql);
		BigDecimal quantity = super.getCountFromSql(sql);
		logger.info(quantity);
		return quantity;
	}
	/**
	 * 获取从上月1日至今，各个工序的报废总重量和总支数。
	 * @return List<ProNWeiAndQua>类，ProNWeiAndQua的pojo类中包含有相关信息。
	 */
	public List<ProNWeiAndQua> getProNWeiAndQuas() {
		logger.info("DAO----getViewEntitys");
		String sql = "select ProduceM_Name,sum(D_Weight) totalWeight,sum(D_Quantity1) totalQuantity from view_scrap_report where D_Date>'" + TimeUtil.lastMonth() + "' group by ProduceM_Name";
		List<Object> objects = super.getEntitysFromSql(sql, ProNWeiAndQua.class);
		List<ProNWeiAndQua> proNWeiAndQuas = new ArrayList<ProNWeiAndQua>();
		for(Object o : objects) {
			proNWeiAndQuas.add((ProNWeiAndQua)o);
		}
		logger.info("----------------get goodUsers succeed!-----------------" + proNWeiAndQuas.size());
		return proNWeiAndQuas;
	}
}
