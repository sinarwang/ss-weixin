package com.ss.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ss.adapter.DaoAdapter;
import com.ss.entity.ChkPackage;
import com.ss.entity.ViewDailyfeedingtube;
import com.ss.entity.ViewMyWorkflowTimeout;
import com.ss.util.LoggerUtil;
import com.ss.util.TimeUtil;

public class ChkPackageDAO extends DaoAdapter<ChkPackage> {
	private static ChkPackageDAO chkPackageDAO = null;
	private static Logger logger = LoggerUtil.getInstance();
	/**
	 * 获取ChkPackageDAO的单例
	 * @return ChkPackageDAO的单例
	 */
	public static ChkPackageDAO getInstance() {
		if(chkPackageDAO == null) {
			chkPackageDAO = new ChkPackageDAO();
		}
		return chkPackageDAO;
	}
	/**
	 * 获取本月开始的所有打包对象
	 * @return 装有本月开始的打包对象的List容器。
	 */
	public List<ChkPackage> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select D_BarCode,ContractNo,D_BoxNo,D_Weight,D_Quantity,D_Numbers,D_StartTime,D_EndTime from chk_package where D_StartTime>'" + TimeUtil.beginMonth() + "'";
		List<ChkPackage> chkps = super.getViewEntitys(ChkPackage.class, sql);
		logger.info("----------------get chkPackages succeed!-----------------");
		return chkps;
	}
	/**
	 * 查找本月开始的所有打包对象的总个数。
	 * @return 本月开始的所有打包对象的总个数。
	 */
	public int getEntitysCount() {
		String sql = "select count(*) from chk_package where D_StartTime>'" + TimeUtil.beginMonth() + "'";
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	
	/**
	 * 查询本月打包对象的总重量和总支数。
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeightAndQuantity() {
		String sql_weight = "select sum(D_Weight) from chk_package where D_StartTime>'" + TimeUtil.beginMonth() + "'";
		String sql_number = "select sum(D_Quantity) from chk_package where D_StartTime>'" + TimeUtil.beginMonth() + "'";
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal result_weight = super.getCountFromSql(sql_weight);
		BigDecimal result_number = super.getCountFromSql(sql_number);
		result.put("totalWeight", result_weight);
		result.put("totalQuantity", result_number);
		if(result_weight == null || result_number == null) {
			return null;
		} else {
			return result;
		}
	}
}
