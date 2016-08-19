package com.ss.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.adapter.DaoAdapter;
import com.ss.entity.ViewMyWorkflowComplete;
import com.ss.entity.ViewMyWorkflowTimeout;
import com.ss.util.TimeUtil;

public class ViewMyWorkflowCompleteDAO extends DaoAdapter<ViewMyWorkflowComplete> {
	private static ViewMyWorkflowCompleteDAO viewMyWorkflowCompleteDAO = null;
	/**
	 * 获取ViewMyWorkflowCompleteDAO的单例
	 * @return ViewMyWorkflowCompleteDAO的单例
	 */
	public static ViewMyWorkflowCompleteDAO getInstance() {
		if(viewMyWorkflowCompleteDAO == null) {
			viewMyWorkflowCompleteDAO = new ViewMyWorkflowCompleteDAO();
		}
		return viewMyWorkflowCompleteDAO;
	}
	
	/**
	 * 获取当月所有成品对象
	 * @return 装有成品对象的List容器。
	 */
	public List<ViewMyWorkflowComplete> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select D_Rate,ProduceM_EndTime,ProduceM_Number,ProduceM_Weight,ProduceM_Weights,CardNo,ContractNo,FeedingDate,DeliveryDate from view_my_workflow_complete where ProduceM_EndTime>'" + TimeUtil.beginMonth() + "'";
		List<ViewMyWorkflowComplete> vwfcs = super.getViewEntitys(ViewMyWorkflowComplete.class, sql);
		logger.info("----------------get viewMyWorkflowCompletes succeed!-----------------");
		return vwfcs;
	}
	
	/**
	 * 查找当月成品对象的总个数。
	 * @return 成品对象的总个数。
	 */
	public int getEntitysCount() {
		String sql = "select count(*) from view_my_workflow_complete where ProduceM_EndTime>'" + TimeUtil.beginMonth() + "'";
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	
	/**
	 * 查找本月成品对象的总重量
	 * @return 本月成品对象的总重量(KG)
	 */
	public BigDecimal getTotalWeight() {
		String sql_weight = "select sum(ProduceM_Weight) from view_my_workflow_complete where ProduceM_EndTime>'" + TimeUtil.beginMonth() + "'";
		
		BigDecimal totalWeight = super.getCountFromSql(sql_weight);
		return totalWeight;
	}
	/**
	 * 查找本月成品对象的总支数。
	 * @return 本月成品对象的总支数。
	 */
	public BigDecimal getTotalQuantity() {
		String sql_number = "select sum(ProduceM_Number) from view_my_workflow_complete where ProduceM_EndTime>'" + TimeUtil.beginMonth() + "'";
		BigDecimal totalQuantity = super.getCountFromSql(sql_number);
		return totalQuantity;
	}
}
