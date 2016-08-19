package com.ss.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ss.adapter.DaoAdapter;
import com.ss.entity.ViewMyWorkflowTimeout;
import com.ss.entity.pojo.WFTOWeightAndCardNos;

public class ViewMyWorkflowTimeoutDAO extends DaoAdapter<ViewMyWorkflowTimeout> {
	private static ViewMyWorkflowTimeoutDAO viewMyWorkflowTimeoutDAO = null;
	/**
	 * 获取ViewMyWorkflowTimeoutDAO的单例
	 * @return ViewMyWorkflowTimeoutDAO的单例
	 */
	public static ViewMyWorkflowTimeoutDAO getInstance() {
		if(viewMyWorkflowTimeoutDAO == null) {
			viewMyWorkflowTimeoutDAO = new ViewMyWorkflowTimeoutDAO();
		}
		return viewMyWorkflowTimeoutDAO;
	}
	
	/**
	 * 获取所有超排程对象
	 * @return 装有超排程对象的List容器。
	 */
	public List<ViewMyWorkflowTimeout> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select CardNo,contractNo,TimeOut,ProduceM_Name,ProduceM_Spec from view_weixin_workflow_timeout where ContractNo not like '%BH%' and D_situation is null order by TimeOut , CardId asc";
		List<ViewMyWorkflowTimeout> vwfts = super.getViewEntitys(ViewMyWorkflowTimeout.class, sql);
		logger.info("----------------get viewMyWorkflowTimeouts succeed!-----------------");
		return vwfts;
	}
	/**
	 * 查找超排程对象的总个数。
	 * @return 超排程对象的总个数。
	 */
	public int getEntitysCount() {
		String tableName = getTableName(ViewMyWorkflowTimeout.class);
		String sql = "select count(*) from (select CardId from " + tableName + " where ContractNo not like '%BH%' and D_situation is null order by TimeOut asc limit 0,13) t";
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	
	/**
	 * 查找超排程的合同号的数量，每个细分合同号也算作不同的合同号。
	 * 如S301-SH160623009-4和S301-SH160623009-7是两个不同的合同号。
	 * @return 超排程合同号的数量。
	 */
	public int getContractNoCount() {
		String tableName = getTableName(ViewMyWorkflowTimeout.class);
		String sql = "select count(*) from " +
						"(select count(*) from " +
							"(select ContractNo from " + tableName + " where ContractNo not like '%BH%' and D_situation is null order by TimeOut asc limit 0,13) t " +
						"group by ContractNo) t1" ;
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	/**
	 * 通过存储过程查找超排程的合同号数量(cc)和超排程对象的总个数(ec)，合同号只统计大的合同号的个数。
	 * 如S301-SH160623009-4和S301-SH160623009-7将算作相同的合同号。
	 * @return 返回装有超排程的合同号数量(cc)和超排程对象的总个数(ec)的Map容器，可以通过key值（cc和ec），得到对应的数量值。
	 */
	/*public Map<String, Integer> getCcAndEc() {
		String sql = "{ call Pro_ContractNoCount(?,?) }";
		Map<Integer, Object> returns = new HashMap<Integer, Object>();
		Map<Integer, Object> results = null;
		Map<String, Integer> ccAndEc = new HashMap<String, Integer>();
		returns.put(1, Integer.class);
		returns.put(2, Integer.class);
		results = super.executeProc(sql, returns, null);
		int contractNoCount = (Integer)results.get(1);
		int entitysCount = (Integer)results.get(2);
		ccAndEc.put("cc", contractNoCount);
		ccAndEc.put("ec", entitysCount);
		return ccAndEc;
	}*/
	
	/**
	 * 通过存储过程查找超排程的合同号数量(cc)和超排程对象的总个数(ec)，合同号只统计大的合同号的个数。
	 * 如S301-SH160623009-4和S301-SH160623009-7将算作相同的合同号。
	 * @return 返回装有超排程的合同号数量(cc)和超排程对象的总个数(ec)的Map容器，可以通过key值（cc和ec），得到对应的数量值。
	 */
	public Map<String, Integer> getCcAndEc() {
		String sql = "{ call Pro_ContractNoCount1(?,?,?) }";
		Map<Integer, Object> returns = new HashMap<Integer, Object>();
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		Map<Integer, Object> results = null;
		Map<String, Integer> ccAndEc = new HashMap<String, Integer>();
		returns.put(1, Integer.class);
		returns.put(2, Integer.class);
		parameters.put(3, -1);
		results = super.executeProc(sql, returns, parameters);
		int contractNoCount = (Integer)results.get(1);
		int entitysCount = (Integer)results.get(2);
		ccAndEc.put("cc", contractNoCount);
		ccAndEc.put("ec", entitysCount);
		return ccAndEc;
	}
	/**
	 * 拿到装有WFTOWeightAndCardNos的pojo类的List容器
	 * @return 装有WFTOWeightAndCardNos的pojo类的List容器
	 */
	public List<WFTOWeightAndCardNos> getWeightAndCardNos() {
		List<WFTOWeightAndCardNos> wtAndQts = new ArrayList<WFTOWeightAndCardNos>();
		String sql = "select produceM_Name,count(*) totalCardNo,sum(D_totalWeight) totalWeight from view_my_workflow_timeout GROUP BY produceM_Name";
		List<Object> objects = super.getEntitysFromSql(sql, WFTOWeightAndCardNos.class);
		for(Object o : objects) {
			wtAndQts.add((WFTOWeightAndCardNos)o);
		}
		return wtAndQts;
	}
	
	public static void main(String[] args) {
		Map<String, Integer> m = new HashMap<String, Integer>();
		m = ViewMyWorkflowTimeoutDAO.getInstance().getCcAndEc();
		System.out.println(m.get("cc") + "-------" + m.get("ec"));
	}
		
}
