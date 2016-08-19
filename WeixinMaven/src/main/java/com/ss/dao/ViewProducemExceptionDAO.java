package com.ss.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ss.adapter.DaoAdapter;
import com.ss.entity.ViewProducemException;
import com.ss.util.LoggerUtil;
import com.ss.util.TimeUtil;

public class ViewProducemExceptionDAO extends DaoAdapter<ViewProducemException> {
	private static ViewProducemExceptionDAO viewProducemExceptionDAO = null;
	private static Logger logger = LoggerUtil.getInstance();
	/**
	 * 获取ViewProducemExceptionDAO的单例
	 * @return ViewProducemExceptionDAO的单例
	 */
	public static ViewProducemExceptionDAO getInstance() {
		if(viewProducemExceptionDAO == null) {
			viewProducemExceptionDAO = new ViewProducemExceptionDAO();
		}
		return viewProducemExceptionDAO;
	}
	/**
	 * 获取所有生产异常对象
	 * @return 装有生产异常对象的List容器。
	 */
	public List<ViewProducemException> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select ContractNo,CardNo,ProduceM_Name,D_type,D_Date,N_Quantity,N_Numbers,N_Weight,D_State from view_producem_exception where D_Date>'" + TimeUtil.beginMonth() + "'";
		List<ViewProducemException> vpes = super.getViewEntitys(ViewProducemException.class, sql);
		logger.info("----------------get viewProducemExceptions succeed!-----------------");
		return vpes;
	}
	/**
	 * 查找生产异常对象的总个数。
	 * @return 生产异常对象的总个数。
	 */
	public int getEntitysCount() {
		String sql = "select count(*) from view_producem_exception where D_Date>'" + TimeUtil.beginMonth() + "'";
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	/**
	 * 查询各个工序的生产异常数量。
	 * @return Map<String, BigDecimal>类型，key值是工序名称，value是该工序的生产异常数量。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getCountsFromProduceM_Name() {
		String sql = "select count(*) num, ProduceM_Name produceM_Name from view_producem_exception where D_Date>'" + TimeUtil.beginMonth() + "' group by ProduceM_Name";
		logger.info(sql);
		String[] parameterNames = {"produceM_Name", "num"};
		Map<String, BigDecimal> result = super.executeQuery(sql, parameterNames);
		if(result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}
	/**
	 * 查询本月各个工序的生产异常重量。
	 * @return Map<String, BigDecimal>类型，key值是工序名称，value是该工序的生产异常重量。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getWeightsFromProduceM_Name() {
		String sql = "select sum(N_Weight) weight, ProduceM_Name produceM_Name from view_producem_exception where D_Date>'" + TimeUtil.beginMonth() + "' group by ProduceM_Name";
		logger.info(sql);
		String[] parameterNames = {"produceM_Name", "weight"};
		Map<String, BigDecimal> result = super.executeQuery(sql, parameterNames);
		if(result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}
	/**
	 * 查询本月各类生产异常的重量。
	 * @return Map<String, BigDecimal>类型，key值是生产异常类别名，value是该类别的生产异常重量。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getWeightsFromD_Type() {
		String sql = "select sum(N_Weight) weight, D_Type d_Type from view_producem_exception where D_Date>'" + TimeUtil.beginMonth() + "' group by D_Type";
		logger.info(sql);
		String[] parameterNames = {"d_Type", "weight"};
		Map<String, BigDecimal> result = super.executeQuery(sql, parameterNames);
		if(result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}
	/**
	 * 获取本月生产异常重量总和。
	 * @return BigDecimal类型，本月生产异常重量总和。
	 */
	public BigDecimal getTotalWeight() {
		String sql = "select sum(N_Weight) from view_producem_exception where D_Date>'" + TimeUtil.beginMonth() + "'";
		logger.info(sql);
		BigDecimal count = super.getCountFromSql(sql);
		logger.info(count);
		return count;
	}
	
	
	/*public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1, 0, 0, 0);
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = "select ContractNo,CardNo,ProduceM_Name,D_type,D_Date,N_Quantity,N_Numbers,N_Weight,D_State from view_producem_exception where D_State in (0,1) and D_Date>'" + sdf.format(d) + "'";
		System.out.println(str);
	}*/
}
