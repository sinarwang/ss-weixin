package com.ss.dao;

import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;
import com.ss.adapter.DaoAdapter;
import com.ss.entity.ProducemWorkorder;
import com.ss.util.LoggerUtil;
import com.ss.util.TimeUtil;

public class ProducemWorkorderDAO extends DaoAdapter<ProducemWorkorder> {
	private static ProducemWorkorderDAO producemWorkorderDAO = null;
	private static Logger logger = LoggerUtil.getInstance();
	/**
	 * 获取ViewProducemExceptionDAO的单例
	 * @return ViewProducemExceptionDAO的单例
	 */
	public static ProducemWorkorderDAO getInstance() {
		if(producemWorkorderDAO == null) {
			producemWorkorderDAO = new ProducemWorkorderDAO();
		}
		return producemWorkorderDAO;
	}
	/**
	 * 获取当天的生产工单
	 * @return 装有生产工单对象的List容器。
	 */
	public List<ProducemWorkorder> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select CardNo,ContractNo,ProduceM_Name,ProduceM_Number,ProduceM_Weight,D_CreateDate,t1.state " +
				"from ProduceM_WorkOrder t left join view_workorder_state t1 on t.D_ID=t1.D_ID " +
				"where t.D_CreateDate>='" + TimeUtil.beginDay() + "'";
		List<ProducemWorkorder> pwos = super.getViewEntitys(ProducemWorkorder.class, sql);
		logger.info("----------------get producemWorkorders succeed!-----------------");
		return pwos;
	}
	/**
	 * 获取当天的生产工单的总个数。
	 * @return 当天的生产工单的总个数。
	 */
	public int getEntitysCount() {
		String sql = "select count(*) from ProduceM_WorkOrder where D_CreateDate>'" + TimeUtil.beginDay() + "'";
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	/**
	 * 根据工序名称获取当天的生产工单
	 * @param produceM_Name 工序名称
	 * @return 装有生产工单对象的List容器。
	 */
	public List<ProducemWorkorder> getViewEntitysByproduceM_Name(String produceM_Name) {
		logger.info("DAO----getViewEntitys");
		String sql = "select CardNo,ContractNo,ProduceM_Name,ProduceM_Number,ProduceM_Weight,D_CreateDate,t1.state " +
				"from ProduceM_WorkOrder t left join view_workorder_state t1 on t.D_ID=t1.D_ID " +
				"where ProduceM_Name='" + produceM_Name + "' and D_CreateDate>='" + TimeUtil.beginDay() + "'";
		List<ProducemWorkorder> pwos = super.getViewEntitys(ProducemWorkorder.class, sql);
		logger.info("----------------get producemWorkorders succeed!-----------------");
		return pwos;
	}
	/**
	 * 查找当天生产工单的总重量
	 * @return 当天生产工单的总重量(KG)
	 */
	public BigDecimal getTotalWeight() {
		String sql_weight = "select sum(ProduceM_Weight) from ProduceM_WorkOrder where D_CreateDate>='" + TimeUtil.beginDay() + "'";
		
		BigDecimal totalWeight = super.getCountFromSql(sql_weight);
		return totalWeight;
	}
	/**
	 * 查找当天生产工单的总支数
	 * @return 当天生产工单的总支数
	 */
	public BigDecimal getTotalQuantity() {
		String sql_quantity = "select sum(ProduceM_Number) from ProduceM_WorkOrder where D_CreateDate>='" + TimeUtil.beginDay() + "'";
		
		BigDecimal totalQuantity = super.getCountFromSql(sql_quantity);
		return totalQuantity;
	}
	/**
	 * 根据工序名称，查找当天该工序生产工单的总重量
	 * @param produceM_Name 工序名称
	 * @return 当天该工序生产工单的总重量(KG)
	 */
	public BigDecimal getTotalWeightByProduceM_Name(String produceM_Name) {
		String sql_weight = "select sum(ProduceM_Weight) from ProduceM_WorkOrder where ProduceM_Name='" + produceM_Name + "' and D_CreateDate>='" + TimeUtil.beginDay() + "'";
		
		BigDecimal totalWeight = super.getCountFromSql(sql_weight);
		return totalWeight;
	}
	/**
	 * 根据工序名称，查找当天该工序生产工单的总支数
	 * @param produceM_Name 工序名称
	 * @return 当天该工序生产工单的总支数
	 */
	public BigDecimal getTotalQuantityByProduceM_Name(String produceM_Name) {
		String sql_quantity = "select sum(ProduceM_Number) from ProduceM_WorkOrder where ProduceM_Name='" + produceM_Name + "' and D_CreateDate>='" + TimeUtil.beginDay() + "'";
		
		BigDecimal totalQuantity = super.getCountFromSql(sql_quantity);
		return totalQuantity;
	}
}
