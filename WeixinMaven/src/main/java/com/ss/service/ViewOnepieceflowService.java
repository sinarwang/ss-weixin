package com.ss.service;

import java.util.List;
import org.apache.log4j.Logger;
import com.ss.dao.ViewOnepieceflowDAO;
import com.ss.entity.ViewOnepieceflow;
import com.ss.util.LoggerUtil;

public class ViewOnepieceflowService {
	private static ViewOnepieceflowService viewOnepieceflowService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static ViewOnepieceflowDAO viewOnepieceflowDAO = ViewOnepieceflowDAO.getInstance();
	
	public static ViewOnepieceflowService getInstance() {
		if(viewOnepieceflowService == null) {
			viewOnepieceflowService = new ViewOnepieceflowService();
		}
		return viewOnepieceflowService;
	}
	
	/**
	 * 获取所有单件流对象（根据生产框号分组）
	 * @return 装有单件流对象的List容器。
	 */
	public List<ViewOnepieceflow> getViewOnepieceflows() {
		logger.info("Service -----------getViewOnepieceflows");
		List<ViewOnepieceflow> vofs = viewOnepieceflowDAO.getViewEntitys();
		return vofs;
	}
	/**
	 * 根据生产框号获取所有单件流对象（详细信息）
	 * @return 装有单件流对象的List容器。
	 */
	public List<ViewOnepieceflow> getDetailFromProduceM_BillNo(String ProduceM_BillNo) {
		logger.info("Service -----------getDetailFromProduceM_BillNo");
		List<ViewOnepieceflow> vofs = viewOnepieceflowDAO.getDetailFromProduceM_BillNo(ProduceM_BillNo);
		return vofs;
	}
	/**
	 * 查找根据生产框号分组后的单件流的总个数。
	 * @return 生产框号分组后的单件流的总个数。
	 */
	public int getEntitysCount() {
		int count = viewOnepieceflowDAO.getEntitysCount();
		return count;
	}
}
