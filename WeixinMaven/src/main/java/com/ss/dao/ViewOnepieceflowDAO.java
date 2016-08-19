package com.ss.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import com.ss.adapter.DaoAdapter;
import com.ss.entity.ViewOnepieceflow;
import com.ss.util.LoggerUtil;
import com.ss.util.TimeUtil;

public class ViewOnepieceflowDAO extends DaoAdapter<ViewOnepieceflow> {
	private static ViewOnepieceflowDAO viewOnepieceflowDAO = null;
	private static Logger logger = LoggerUtil.getInstance();
	/**
	 * 获取ViewProducemExceptionDAO的单例
	 * @return ViewProducemExceptionDAO的单例
	 */
	public static ViewOnepieceflowDAO getInstance() {
		if(viewOnepieceflowDAO == null) {
			viewOnepieceflowDAO = new ViewOnepieceflowDAO();
		}
		return viewOnepieceflowDAO;
	}
	
	/**
	 * 获取所有单件流对象（根据生产框号分组）（简要信息）
	 * @return 装有单件流对象的List容器。
	 */
	public List<ViewOnepieceflow> getViewEntitys() {
		logger.info("DAO----getViewEntitys");
		String sql = "select ProduceM_BillNo,ContractNo from view_onepieceflow " + whereStr() + " group by ProduceM_BillNo";
		List<ViewOnepieceflow> vofs = super.getViewEntitys(ViewOnepieceflow.class, sql);
		logger.info("----------------get viewOnepieceflows succeed!-----------------");
		return vofs;
	}
	/**
	 * 根据生产框号获取所有单件流对象（详细信息）
	 * @return 装有单件流对象的List容器。
	 */
	public List<ViewOnepieceflow> getDetailFromProduceM_BillNo(String ProduceM_BillNo) {
		logger.info("DAO----getDetailFromProduceM_BillNo");
		String sql = "select ProduceM_Name,ProduceM_Weight,ProduceM_Number,ProduceM_BeginDate,ProduceM_EndDate,ProduceM_StartTime,ProduceM_EndTime,RealTime,TimeOut from view_onepieceflow where ProduceM_BillNo='" + ProduceM_BillNo + "'";
		List<ViewOnepieceflow> vofs = super.getViewEntitys(ViewOnepieceflow.class, sql);
		logger.info("----------------get viewOnepieceflowsDetail succeed!-----------------");
		return vofs;
	}
	/**
	 * 查找根据生产框号分组后的单件流的总个数。
	 * @return 生产框号分组后的单件流的总个数。
	 */
	public int getEntitysCount() {
		String sql = "select count(*) from (select ProduceM_BillNo from view_onepieceflow " + whereStr() + " group by ProduceM_BillNo) t";
		logger.info(sql);
		int count = super.getCountFromSql(sql).intValue();
		logger.info(count);
		return count;
	}
	/**
	 * 获取查询所需的生产框号(ProduceM_BillNo)筛选条件。
	 * 如获取"where ProduceM_BillNo like '1607%' OR ProduceM_BillNo LIKE '1608%'"
	 * @return 查询所需where语句
	 */
	public String whereStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar c = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		String str1 = "'" + sdf.format(calendar.getTime()).substring(2) + "%'";
		if(c.get(Calendar.MONTH) == 0) {
			calendar.set(c.get(Calendar.YEAR)-1, 11, 1, 0, 0, 0);
		} else {
			calendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH)-1, 1, 0, 0, 0);
		}
		String str2 = "'" + sdf.format(calendar.getTime()).substring(2) + "%'";
		String result = "where ProduceM_BillNo like " + str1 + " or ProduceM_BillNo like " + str2;
		return result;
	}
	
}
