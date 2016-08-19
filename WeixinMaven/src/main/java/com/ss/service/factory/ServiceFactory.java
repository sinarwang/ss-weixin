package com.ss.service.factory;

import org.apache.log4j.Logger;

import com.ss.service.ChkPackageService;
import com.ss.service.ProducemWorkorderService;
import com.ss.service.UserService;
import com.ss.service.ViewDailyfeedingtubeService;
import com.ss.service.ViewMyWorkflowCompleteService;
import com.ss.service.ViewMyWorkflowTimeoutService;
import com.ss.service.ViewOnepieceflowService;
import com.ss.service.ViewPeopleReportService;
import com.ss.service.ViewProducemExceptionService;
import com.ss.service.ViewScrapReportService;
import com.ss.service.WmsInboundDetailService;
import com.ss.service.WmsProductService;
import com.ss.util.LoggerUtil;

public class ServiceFactory {
	private static ServiceFactory serviceFactory = null;
	private static ViewMyWorkflowTimeoutService mftoService = ViewMyWorkflowTimeoutService.getInstance();
	private static ViewProducemExceptionService vpeService = ViewProducemExceptionService.getInstance();
	private static ViewDailyfeedingtubeService vdftService = ViewDailyfeedingtubeService.getInstance();
	private static ViewScrapReportService vsrService = ViewScrapReportService.getInstance();
	private static ViewOnepieceflowService vofService = ViewOnepieceflowService.getInstance();
	private static ViewPeopleReportService vprService = ViewPeopleReportService.getInstance();
	private static UserService userService = UserService.getInstance();
	private static ViewMyWorkflowCompleteService vwfcService = ViewMyWorkflowCompleteService.getInstance();
	private static ChkPackageService chkpService = ChkPackageService.getInstance();
	private static ProducemWorkorderService pwoService = ProducemWorkorderService.getInstance();
	private static WmsProductService wpService = WmsProductService.getInstance();
	private static WmsInboundDetailService widService = WmsInboundDetailService.getInstance();
	private Logger logger = LoggerUtil.getInstance();
	
	public static ServiceFactory getInstance() {
		if(serviceFactory == null) {
			serviceFactory = new ServiceFactory();
		}
		return serviceFactory;
	}
	/**
	 * 取得超排程服务类ViewMyWorkflowTimeoutService的对象
	 * @return 超排程服务类ViewMyWorkflowTimeoutService的对象
	 */
	public static ViewMyWorkflowTimeoutService getMftoService() {
		return mftoService;
	}
	/**
	 * 取得生产异常服务类ViewProducemExceptionService的对象
	 * @return 生产异常服务类ViewProducemExceptionService的对象
	 */
	public static ViewProducemExceptionService getVpeService() {
		return vpeService;
	}
	/**
	 * 取得荒管投料服务类ViewDailyfeedingtubeService的对象
	 * @return 荒管投料服务类ViewDailyfeedingtubeService的对象
	 */
	public static ViewDailyfeedingtubeService getVdftService() {
		return vdftService;
	}
	/**
	 * 取得生产报废服务类ViewScrapReportService的对象
	 * @return 生产报废服务类ViewScrapReportService的对象
	 */
	public static ViewScrapReportService getVsrService() {
		return vsrService;
	}
	/**
	 * 取得单件流服务类ViewOnepieceflowService的对象
	 * @return 单件流服务类ViewOnepieceflowService的对象
	 */
	public static ViewOnepieceflowService getVofService() {
		return vofService;
	}
	/**
	 * 取得生产台账服务类ViewPeopleReportService的对象
	 * @return 生产台账服务类ViewPeopleReportService的对象
	 */
	public static ViewPeopleReportService getVprService() {
		return vprService;
	}
	/**
	 * 取得用户服务类UserService的对象
	 * @return 用户服务类UserService的对象
	 */
	public static UserService getUserService() {
		return userService;
	}
	/**
	 * 取得成品查询服务类ViewMyWorkflowCompleteService的对象
	 * @return 成品查询服务类ViewMyWorkflowCompleteService的对象
	 */
	public static ViewMyWorkflowCompleteService getVwfcService() {
		return vwfcService;
	}
	/**
	 * 取得打包查询服务类ChkPackageService的对象
	 * @return 打包查询服务类ChkPackageService的对象
	 */
	public static ChkPackageService getChkpService() {
		return chkpService;
	}
	/**
	 * 取得生产工单查询服务类ProducemWorkorderService的对象
	 * @return 生产工单查询服务类ProducemWorkorderService的对象
	 */
	public static ProducemWorkorderService getPwoService() {
		return pwoService;
	}
	/**
	 * 取得产品库存查询服务类WmsProductService的对象
	 * @return 产品库存查询服务类WmsProductService的对象
	 */
	public static WmsProductService getWpService() {
		return wpService;
	}
	/**
	 * 取得产品库存查询服务类WmsInboundDetailService的对象
	 * @return 产品库存查询服务类WmsInboundDetailService的对象
	 */
	public static WmsInboundDetailService getWidService() {
		return widService;
	}
}
