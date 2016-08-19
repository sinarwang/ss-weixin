package com.ss.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.ss.dao.ChkPackageDAO;
import com.ss.entity.ChkPackage;
import com.ss.util.LoggerUtil;

public class ChkPackageService {
	private static ChkPackageService chkPackageService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static ChkPackageDAO chkPackageDAO = ChkPackageDAO.getInstance();
	
	public static ChkPackageService getInstance() {
		if(chkPackageService == null) {
			chkPackageService = new ChkPackageService();
		}
		return chkPackageService;
	}
	
	/**
	 * 获取本月开始的所有打包对象
	 * @return 装有本月开始的打包对象的List容器。
	 */
	public List<ChkPackage> getChkPackages() {
		logger.info("Service -----------getChkPackages");
		List<ChkPackage> chkps = chkPackageDAO.getViewEntitys();
		return chkps;
	}
	/**
	 * 获取本月开始的所有打包对象的总个数
	 * @return 本月开始的所有打包对象的总个数
	 */
	public int getEntitysCount() {
		int count = chkPackageDAO.getEntitysCount();
		return count;
	}
	
	/**
	 * 查询本月打包对象的总重量和总支数。
	 * @return Map<String, BigDecimal>类型，key值分别是totalWeight和totalQuantity，value是对应的值。若没有数据，则返回null。
	 */
	public Map<String, BigDecimal> getTotalWeightAndQuantity() {
		Map<String, BigDecimal> result = chkPackageDAO.getTotalWeightAndQuantity();
		return result;
	}
}
