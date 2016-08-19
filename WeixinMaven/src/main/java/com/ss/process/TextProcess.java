package com.ss.process;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ss.entity.ArticleItem;
import com.ss.entity.Product;
import com.ss.entity.ReceiveXmlEntity;
import com.ss.entity.SendXmlEntity;
import com.ss.service.ProductService;
import com.ss.service.UserService;
import com.ss.service.ViewDailyfeedingtubeService;
import com.ss.service.ViewMyWorkflowCompleteService;
import com.ss.service.ViewMyWorkflowTimeoutService;
import com.ss.service.ViewOnepieceflowService;
import com.ss.service.ViewPeopleReportService;
import com.ss.service.ViewProducemExceptionService;
import com.ss.service.ViewScrapReportService;
import com.ss.service.factory.ServiceFactory;
import com.ss.util.LoggerUtil;
/**
 * 封装有根据接收到的实体对象ReceiveXmlEntity，负责对Text类型请求进行关键字处理，并返回对应的回复XML文本内容的一系列操作。
 * @author Administrator
 *
 */
public class TextProcess {
	private static TextProcess textProcess;
	private Logger logger = LoggerUtil.getInstance();
	private static String register = "Hi，您尚未注册！为保证您使用微信公众号查询RFID系统相关信息，请先进行【" +
			"<a href=\"" + ProcessRequest.appUrl + "registerServlet?openId=OPEN_ID&action=unCheck\">身份验证</a>" + "】！\n\n" +
			"小智将竭诚为您提供安全快捷的在线订单查询服务。\n\n";
	private static String help = "若您已经进行身份验证，请回复以下序号进行相关数据查询：\n" +
						"【1】生产异常查询\n" +
						"【2】单件流查询\n" +
						"【3】荒管投料量查询\n" +
						"【4】超排程订单查询\n" +
						"【5】员工日薪查询\n" +
						"【6】生产报废查询\n" +
						"【7】生产改制查询\n" +
						"【8】生产台账查询\n" +
						"【9】成品查询\n" +
						"【10】打包查询\n" +
						"【11】生产工单查询\n" +
						"【12】产品库存查询";
	
	public static TextProcess getInstance() {
		if(textProcess == null) {
			textProcess = new TextProcess();
		}
		return textProcess;
	}
	/**
	 * 根据接收到的实体对象，确定回复Xml文本消息内容。
	 * @param xmlEntity 接收到的实体对象（ReceiveXmlEntity类型）
	 * @return 回复Xml消息内容（String类型）
	 */
	public String EntityProcessForContent(ReceiveXmlEntity receiveXmlEntity) {
		String receiveContent = receiveXmlEntity.getContent();
		StringBuffer content = new StringBuffer();
		/////////////////////////////////////////////////
		if(receiveContent != null) {
			boolean isExist = ServiceFactory.getUserService().isExist(receiveXmlEntity.getFromUserName());
			if(!isExist) {
				content.append(register.replace("OPEN_ID", receiveXmlEntity.getFromUserName()));
				return OutXmlProcess.getTextResult(receiveXmlEntity, content.toString());
			}
			//
			//若为1，则进行生产异常查询
			//
			if(receiveContent.trim().equals("1")) {
				if(isExist) {
					return getProExceptionXml(receiveXmlEntity);
				}
				//
				//若为2，则进行单件流查询
				//
			} else if(receiveContent.trim().equals("2")) {
				if(isExist) {
					return getOnepieceflowXml(receiveXmlEntity);
				}
				//
				//若为3，则进行荒管投料量查询
				//
			} else if(receiveContent.trim().equals("3")) {
				if(isExist) {
					return getVDFTXml(receiveXmlEntity);
				}
				//
				//若为4，则进行超排程查询
				//
			} else if(receiveContent.trim().equals("4") || receiveContent.trim().equals("超排程")) {
				if(isExist) {
					return getWFTOXml(receiveXmlEntity);
					
					//链接格式的回复
					
					/*String url = ProcessRequest.appUrl +"superSchedule.jsp";
					String title = "超排程相关信息";
					String description = "超排程详细信息：\n" +
							"超排程信息总个数：8";
					logger.info("------begin-----getLinkResult");
					logger.info(OutXmlProcess.getLinkResult(receiveXmlEntity, title, description, url));
					return OutXmlProcess.getLinkResult(receiveXmlEntity, title, description, url);*/
				}
				//
				//若为5，则进行员工日薪查询
				//
			} else if(receiveContent.trim().equals("5")) {
				if(isExist) {
					content.append("员工日薪查询尚未开发完成，敬请期待！");
					return OutXmlProcess.getTextResult(receiveXmlEntity, content.toString());
				}
				//
				//若为6，则进行生产报废查询
				//
			} else if(receiveContent.trim().equals("6")) {
				if(isExist) {
					return getScrapReportXml(receiveXmlEntity);
				}
				//
				//若为7，则进行生产改制查询
				//
			} else if(receiveContent.trim().equals("7")) {
				if(isExist) {
					content.append("生产改制查询尚未开发完成，敬请期待！");
					return OutXmlProcess.getTextResult(receiveXmlEntity, content.toString());
				}
				//
				//若为8，则进行生产台账查询
				//
			} else if(receiveContent.trim().equals("8")) {
				if(isExist) {
					return getPeopleReportXml(receiveXmlEntity);
				}
				//
				//若为9，则进行成品查询
				//
			} else if(receiveContent.trim().equals("9")) {
				if(isExist) {
					return getWorkflowCompleteXml(receiveXmlEntity);
				}
				//
				//若为10，则进行打包查询
				//
			} else if(receiveContent.trim().equals("10")) {
				if(isExist) {
					return getChkPackageXml(receiveXmlEntity);
				}
				//
				//若为11，则进行生产工单查询
				//
			} else if(receiveContent.trim().equals("11")) {
				if(isExist) {
					return getProducemWorkorderXml(receiveXmlEntity);
				}
				//
				//若为12，则进行产品库存查询
				//
			} else if(receiveContent.trim().equals("12")) {
				if(isExist) {
					return getWmsProductXml(receiveXmlEntity);
				}
			} 
		}
		
		/////////////////////////////////////////////////
		//判断含不含@，根据结果进行不同处理。
		if(receiveContent.contains("@")) {
			String[] strs = receiveContent.split("@");
			
			if(strs.length == 2) {
				if(!strs[0].equals("")) {
					//接收到的文本形如x@xxx 或 x@xxx@
					//选项序号
					int num = 0;
					try {
						num = Integer.parseInt(strs[0].trim());
					} catch (NumberFormatException e) {
						content.append(help);
						return OutXmlProcess.getTextResult(receiveXmlEntity, content.toString());
					}
					//选项名称
					String optionName = strs[1];
					if(optionName != null && optionName.trim().equals("企业文化")) {
						//若@字符后面的字符串为“企业文化”，进行如下操作。
						String cultureStr = getCultureStrFromNum(num);
						content.append(cultureStr);
					} else if(optionName != null && optionName.trim().equals("群发")) {
						
					} else {
						content.append(help);
					}
				} else if(strs[1].equals("admin")) {
					//接受到的文本形如@xxx 或 @xxx@
					content.append("管理员后台登录点这里：" +
							"<a href=\"" + ProcessRequest.appUrl + "login.html\">管理员登录</a>");
					//此处返回text文本回应的xml文本
					return OutXmlProcess.getTextResult(receiveXmlEntity, content.toString());
				} else {
					content.append(help);
				}
			} else{
				content.append(help);
				return OutXmlProcess.getTextResult(receiveXmlEntity, content.toString());
			}
			
			
			//此处返回text文本回应的xml文本
			return OutXmlProcess.getTextResult(receiveXmlEntity, content.toString());
		}
		
		content.append(help);
		return OutXmlProcess.getTextResult(receiveXmlEntity, content.toString());
	}
	/**
	 * 根据序号查询企业文化相关返回文本。
	 * @param num 序号
	 * @return 返回的文本
	 */
	public String getCultureStrFromNum(int num) {
		String str = "";
		/*"1、企业标志。\n" 
		"2、理念系统。\n" 
		"3、员工手册。\n" 
		"4、公益慈善。\n" 
		"5、行为规范。\n" 
		"6、活动掠影。\n" 
		"7、廉政文化。\n\n" */
		switch(num) {
		case 1:
			str = "Logo图形是由三个不同颜色的字母“S” 元素形状组合而成，三个“S”层层递进，传递出进步、勇往无前的企业精神，具有强烈的时代感和视觉冲击力。";
			break;
		case 2:
			str = "上上核心理念：为全球工业装备提供安全可靠的不锈钢管\n" +
					"企业精神：艰苦创业、以质取胜、以信为本\n" +
					"经营理念：以成熟的技术和可靠的产品质量服务用户，贡献社会，发展自己\n";
			break;
		case 3:
			str = "为了使每位员工尽快熟悉和了解本公司，同时帮助您通过努力成为一名优秀的员工，" +
					"我们撰写了这本《员工手册》。本手册提供了您所需要的信息和公司管理员工的条款和条件";
			break;
		case 4:
			//本项不回复文本消息
			str = "公益慈善查看这里：<a href=\"http://www.shangshang.com.cn/culture/&i=13&comContentId=13.html\">公益慈善</a>";
			break;
		case 5:
			str = "上上员工的行为和言谈举止直接关系到集团形象和员工在顾客心中的位置。" +
					"为展现上上集团员工良好的素养与优雅的风度，也使每一位员工能够不断提高修养并把良好的素养表现得当，" +
					"要求全体员工务必在态度与行为上严格按本规范执行。";
			break;
		case 6:
			//本项不回复文本消息
			str = "活动掠影查看这里：<a href=\"http://www.shangshang.com.cn/events.html\">活动掠影</a>";
			break;
		case 7:
			str = "为促进企业健康和谐发展，上上集团及其下属公司不断加强廉政文化建设，" +
					"开展形式多样的廉政文化活动，将廉政文化理念渗透到安全生产、经营管理的各个环节当中，为企业发展添动力。\n" +
					"具体可以查看这里：<a href=\"http://www.shangshang.com.cn/lzwh.html\">廉政文化</a>";
			break;
		default:
			str = help;
			break;
		}
		return str;
	}
	/**
	 * 根据接收的事件key值确定回复消息内容。
	 * @param eventKey
	 * @return 返回回复的Xml文本
	 */
	public String getContentFromEventKey(String eventKey, ReceiveXmlEntity receiveXmlEntity) {
		StringBuffer content = new StringBuffer();
		boolean isExist = ServiceFactory.getUserService().isExist(receiveXmlEntity.getFromUserName());
		if(!isExist) {
			content.append(register.replace("OPEN_ID", receiveXmlEntity.getFromUserName()));
			return OutXmlProcess.getTextResult(receiveXmlEntity, content.toString());
		} else {
			int keyValue = Integer.parseInt(eventKey);
			switch(keyValue) {
			//单件流
			case 11:
				content.append(getOnepieceflowXml(receiveXmlEntity));
				break;
			//生产台账
			case 12:
				content.append(getPeopleReportXml(receiveXmlEntity));
				break;
			//生产工单
			case 13:
				content.append(getProducemWorkorderXml(receiveXmlEntity));
				break;
			//超排程
			case 21:
				content.append(getWFTOXml(receiveXmlEntity));
				break;
			//生产报废
			case 22:
				content.append(getScrapReportXml(receiveXmlEntity));
				break;
			//生产异常
			case 23:
				//本项不回复文本消息
				content.append(getProExceptionXml(receiveXmlEntity));
				break;
			//荒管投料
			case 24:
				content.append(getVDFTXml(receiveXmlEntity));
				break;
			//成品查询
			case 25:
				content.append(getWorkflowCompleteXml(receiveXmlEntity));
				break;
			//打包查询
			case 31:
				content.append(getChkPackageXml(receiveXmlEntity));
				//content.append(OutXmlProcess.getTextResult(receiveXmlEntity, "了解更多服务暂未添加，敬请期待~~~~~"));
				break;
			//产品库存查询
			case 32:
				content.append(getWmsProductXml(receiveXmlEntity));
				//content.append(OutXmlProcess.getTextResult(receiveXmlEntity, "了解更多服务暂未添加，敬请期待~~~~~"));
				break;
			default:
				logger.error("-----------eventKey:" + eventKey + "--------------");
				break;
			}
			return content.toString();
		}
	}
	/**
	 * 获得查询生产异常返回的Xml文本
	 * @return 查询生产异常返回的Xml文本
	 */
	public String getProExceptionXml(ReceiveXmlEntity receiveXmlEntity) {
		logger.info("--------开始生产异常查询");
		//图文格式的回复
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = new ArticleItem();
		item.setTitle("生产异常相关信息");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取未处理生产异常总个数
		int count = ServiceFactory.getVpeService().getEntitysCount();
		//获取各个工序的生产异常数量值，并拼成显示用的字符串。
		Map<String, BigDecimal> CountsFromProduceM_Name = ServiceFactory.getVpeService().getCountsFromProduceM_Name();
		Set<Entry<String, BigDecimal>> entrySet = CountsFromProduceM_Name.entrySet();
		StringBuffer str = new StringBuffer();
		int i = 1;
		for(Entry e : entrySet) {
			if(i%2 == 0) {
				str.append(e.getKey() + "：" + e.getValue() + "起\n");
			} else {
				str.append(e.getKey() + "：" + e.getValue() + "起\n");
			}
			i++;
		}
		item.setDescription("生产异常相关查询，截止到" + sdf.format(d) + "\n本月生产异常起数为：" + count + "起\n" + str.toString() + "\n" +
				"点击下方\"查看全文\"可查询生产异常相关详细信息。");
		String url = ProcessRequest.appUrl +"producemException.jsp?openId=" + receiveXmlEntity.getFromUserName();
		//item.setPicUrl(ProcessRequest.appUrl + "images/fca78b08-03d4-49e8-a583-ab1f985dbb75.jpg");
		item.setPicUrl("");
		item.setUrl(url);
		items.add(item);
		//此处返回图文回应的xml文本
		return OutXmlProcess.getArticlesResult(receiveXmlEntity, items);
	}
	/**
	 * 获得查询超排程返回的Xml文本
	 * @return 查询超排程返回的Xml文本
	 */
	public String getWFTOXml(ReceiveXmlEntity receiveXmlEntity) {
		logger.info("--------开始超排程");
		//图文格式的回复
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = new ArticleItem();
		item.setTitle("超排程相关信息");
		Map<String, Integer> ccAndEc = ServiceFactory.getMftoService().getCcAndEc();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		item.setDescription("超排程订单查询，截止到" + sdf.format(d) + "\n超排程框数为：" + ccAndEc.get("ec") + "框\n超排程合同份数为：" + ccAndEc.get("cc") + "份\n\n" +
				"点击下方\"查看全文\"可查询超排程详细信息。");
		String url = ProcessRequest.appUrl +"superSchedule.jsp?openId=" + receiveXmlEntity.getFromUserName();
		//item.setPicUrl(ProcessRequest.appUrl + "images/fca78b08-03d4-49e8-a583-ab1f985dbb75.jpg");
		item.setPicUrl("");
		item.setUrl(url);
		items.add(item);
		//此处返回图文回应的xml文本
		return OutXmlProcess.getArticlesResult(receiveXmlEntity, items);
	}
	/**
	 * 获得查询单件流返回的Xml文本
	 * @return 查询单件流返回的Xml文本
	 */
	public String getOnepieceflowXml(ReceiveXmlEntity receiveXmlEntity) {
		logger.info("--------开始单件流查询");
		//图文格式的回复
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = new ArticleItem();
		item.setTitle("单件流相关信息");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取本月单件流总条数
		int count = ServiceFactory.getVofService().getEntitysCount();
		item.setDescription("单件流相关查询，截止到" + sdf.format(d) + "\n从上月1日至当前时间总计生产框数为：" + count + "框\n" +
				"点击下方\"查看全文\"可查询各框物料流转相关详细信息。");
		String url = ProcessRequest.appUrl +"onepieceflow.jsp?openId=" + receiveXmlEntity.getFromUserName();
		//item.setPicUrl(ProcessRequest.appUrl + "images/fca78b08-03d4-49e8-a583-ab1f985dbb75.jpg");
		item.setPicUrl("");
		item.setUrl(url);
		items.add(item);
		//此处返回图文回应的xml文本
		return OutXmlProcess.getArticlesResult(receiveXmlEntity, items);
	}
	/**
	 * 获得查询荒管投料返回的Xml文本
	 * @return 查询荒管投料返回的Xml文本
	 */
	public String getVDFTXml(ReceiveXmlEntity receiveXmlEntity) {
		logger.info("--------开始荒管投料量查询");
		//图文格式的回复
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = new ArticleItem();
		item.setTitle("荒管投料量相关信息");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取本月荒管投料量总条数
		int count = ServiceFactory.getVdftService().getEntitysCount();
		//获取本月荒管投料总重量
		BigDecimal weight = ServiceFactory.getVdftService().getTotalWeight();
		DecimalFormat df = new java.text.DecimalFormat("#.00");  
		item.setDescription("荒管投料量相关查询，截止到" + sdf.format(d) + "\n本月荒管投料总框数为：" + count + "框\n本月荒管投料总重量为：" + df.format(weight.doubleValue()/1000) + " t\n\n" +
				"点击下方\"查看全文\"可查询荒管投料量相关详细信息。");
		String url = ProcessRequest.appUrl +"dailyfeedingtube.jsp?openId=" + receiveXmlEntity.getFromUserName();
		//item.setPicUrl(ProcessRequest.appUrl + "images/fca78b08-03d4-49e8-a583-ab1f985dbb75.jpg");
		item.setPicUrl("");
		item.setUrl(url);
		items.add(item);
		//此处返回图文回应的xml文本
		return OutXmlProcess.getArticlesResult(receiveXmlEntity, items);
	}
	/**
	 * 获得查询生产报废返回的Xml文本
	 * @return 查询生产报废返回的Xml文本
	 */
	public String getScrapReportXml(ReceiveXmlEntity receiveXmlEntity) {
		logger.info("--------开始生产报废查询");
		//图文格式的回复
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = new ArticleItem();
		item.setTitle("生产报废相关信息");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取本月上产报废总框数
		int count = ServiceFactory.getVsrService().getEntitysCount();
		//获取本月上产报废总重量
		BigDecimal weight = ServiceFactory.getVsrService().getTotalWeight();
		DecimalFormat df = new java.text.DecimalFormat("#.00");  
		item.setDescription("生产报废查询，截止到" + sdf.format(d) + "\n从上月1日至当前时间生产报废总起数为：" + count + "起\n生产报废总重量为：" + df.format(weight) + "KG\n\n" +
				"点击下方\"查看全文\"可查询生产报废详细信息。");
		String url = ProcessRequest.appUrl +"scrapReport.jsp?openId=" + receiveXmlEntity.getFromUserName();
		//item.setPicUrl(ProcessRequest.appUrl + "images/fca78b08-03d4-49e8-a583-ab1f985dbb75.jpg");
		item.setPicUrl("");
		item.setUrl(url);
		items.add(item);
		//此处返回图文回应的xml文本
		return OutXmlProcess.getArticlesResult(receiveXmlEntity, items);
	}
	/**
	 * 获得查询生产台账返回的Xml文本
	 * @return 查询生产台账返回的Xml文本
	 */
	public String getPeopleReportXml(ReceiveXmlEntity receiveXmlEntity) {
		logger.info("--------开始生产台账查询");
		//图文格式的回复
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = new ArticleItem();
		item.setTitle("生产台账相关信息");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取当天上产台账总数
		int count = ServiceFactory.getVprService().getEntitysCount();
		item.setDescription("生产台账查询，截止到" + sdf.format(d) + "\n当天生产台账总数为：" + count + "\n" +
				"点击下方\"查看全文\"可查询生产台账详细信息。");
		String url = ProcessRequest.appUrl +"viewPeopleReport.jsp?openId=" + receiveXmlEntity.getFromUserName();
		//item.setPicUrl(ProcessRequest.appUrl + "images/fca78b08-03d4-49e8-a583-ab1f985dbb75.jpg");
		item.setPicUrl("");
		item.setUrl(url);
		items.add(item);
		//此处返回图文回应的xml文本
		return OutXmlProcess.getArticlesResult(receiveXmlEntity, items);
	}
	/**
	 * 获得查询成品返回的Xml文本
	 * @return 查询成品返回的Xml文本
	 */
	public String getWorkflowCompleteXml(ReceiveXmlEntity receiveXmlEntity) {
		logger.info("--------开始成品查询");
		//图文格式的回复
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = new ArticleItem();
		item.setTitle("成品相关信息");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取当月成品总数
		int count = ServiceFactory.getVwfcService().getEntitysCount();
		item.setDescription("成品查询，截止到" + sdf.format(d) + "\n当月成品总数为：" + count + "\n" +
				"点击下方\"查看全文\"可查询成品详细信息。");
		String url = ProcessRequest.appUrl +"myWorkflowComplete.jsp?openId=" + receiveXmlEntity.getFromUserName();
		//item.setPicUrl(ProcessRequest.appUrl + "images/fca78b08-03d4-49e8-a583-ab1f985dbb75.jpg");
		item.setPicUrl("");
		item.setUrl(url);
		items.add(item);
		//此处返回图文回应的xml文本
		return OutXmlProcess.getArticlesResult(receiveXmlEntity, items);
	}
	/**
	 * 获得查询打包返回的Xml文本
	 * @return 查询打包返回的Xml文本
	 */
	public String getChkPackageXml(ReceiveXmlEntity receiveXmlEntity) {
		logger.info("--------开始打包查询");
		//图文格式的回复
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = new ArticleItem();
		item.setTitle("打包相关信息");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取当天打包总数
		int count = ServiceFactory.getChkpService().getEntitysCount();
		item.setDescription("打包查询，截止到" + sdf.format(d) + "\n当月打包总数为：" + count + "\n" +
				"点击下方\"查看全文\"可查询打包详细信息。");
		String url = ProcessRequest.appUrl +"chkPackage.jsp?openId=" + receiveXmlEntity.getFromUserName();
		//item.setPicUrl(ProcessRequest.appUrl + "images/fca78b08-03d4-49e8-a583-ab1f985dbb75.jpg");
		item.setPicUrl("");
		item.setUrl(url);
		items.add(item);
		//此处返回图文回应的xml文本
		return OutXmlProcess.getArticlesResult(receiveXmlEntity, items);
	}
	/**
	 * 获得查询生产工单返回的Xml文本
	 * @return 查询生产工单返回的Xml文本
	 */
	public String getProducemWorkorderXml(ReceiveXmlEntity receiveXmlEntity) {
		logger.info("--------开始生产工单查询");
		//图文格式的回复
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = new ArticleItem();
		item.setTitle("生产工单相关信息");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取当天生产工单总数
		int count = ServiceFactory.getPwoService().getEntitysCount();
		item.setDescription("生产工单查询，截止到" + sdf.format(d) + "\n当天生产工单总数为：" + count + "\n" +
				"点击下方\"查看全文\"可查询生产工单详细信息。");
		String url = ProcessRequest.appUrl +"producemWorkorder.jsp?openId=" + receiveXmlEntity.getFromUserName();
		//item.setPicUrl(ProcessRequest.appUrl + "images/fca78b08-03d4-49e8-a583-ab1f985dbb75.jpg");
		item.setPicUrl("");
		item.setUrl(url);
		items.add(item);
		//此处返回图文回应的xml文本
		return OutXmlProcess.getArticlesResult(receiveXmlEntity, items);
	}
	/**
	 * 获得查询产品库存返回的Xml文本
	 * @return 查询产品库存返回的Xml文本
	 */
	public String getWmsProductXml(ReceiveXmlEntity receiveXmlEntity) {
		logger.info("--------开始产品库存查询");
		//图文格式的回复
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = new ArticleItem();
		item.setTitle("产品库存相关信息");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取产品库存总重量
		BigDecimal weight = ServiceFactory.getWidService().getTotalWeight();
		DecimalFormat df = new java.text.DecimalFormat("#.00");  
		item.setDescription("产品库存查询，截止到" + sdf.format(d) + "\n产品库存总重量为：" + df.format(weight.doubleValue()/1000) + " t\n" +
				"点击下方\"查看全文\"可查询产品库存详细信息。");
		String url = ProcessRequest.appUrl +"wmsInboundDetail.jsp?openId=" + receiveXmlEntity.getFromUserName();
		//item.setPicUrl(ProcessRequest.appUrl + "images/fca78b08-03d4-49e8-a583-ab1f985dbb75.jpg");
		item.setPicUrl("");
		item.setUrl(url);
		items.add(item);
		//此处返回图文回应的xml文本
		return OutXmlProcess.getArticlesResult(receiveXmlEntity, items);
	}
	
	/**
	 * 根据发送Xml实体对象构建发送xml文本。
	 * @param xmlEntity 发送Xml实体对象（SendXmlEntity类型）
	 * @return 要发送的xml文本(String类型)
	 */
	public String getXmlResult (SendXmlEntity sendXmlEntity) {
		StringBuffer result = new StringBuffer();
		result.append("<xml><ToUserName><![CDATA[");
		result.append(sendXmlEntity.getToUserName());
		result.append("]]></ToUserName><FromUserName><![CDATA[");
		result.append(sendXmlEntity.getFromUserName());
		result.append("]]></FromUserName><CreateTime>");
		result.append(sendXmlEntity.getCreateTime());
		result.append("</CreateTime><MsgType><![CDATA[");
		result.append(sendXmlEntity.getMsgType());
		result.append("]]></MsgType><Content><![CDATA[");
		result.append(sendXmlEntity.getContent());
		result.append("]]></Content></xml>");
		return result.toString();
	}
	
}
