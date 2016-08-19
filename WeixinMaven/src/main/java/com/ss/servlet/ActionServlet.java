package com.ss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import net.sf.json.JSONArray;

import com.ss.entity.ChkPackage;
import com.ss.entity.ProducemWorkorder;
import com.ss.entity.User;
import com.ss.entity.ViewDailyfeedingtube;
import com.ss.entity.ViewMyWorkflowComplete;
import com.ss.entity.ViewMyWorkflowTimeout;
import com.ss.entity.ViewOnepieceflow;
import com.ss.entity.ViewPeopleReport;
import com.ss.entity.ViewProducemException;
import com.ss.entity.ViewScrapReport;
import com.ss.entity.WmsInboundDetail;
import com.ss.entity.WmsProduct;
import com.ss.entity.pojo.DTypeWeight;
import com.ss.entity.pojo.GoodUser;
import com.ss.entity.pojo.ProNWeiAndQua;
import com.ss.entity.pojo.ProduceMWeight;
import com.ss.entity.pojo.UserPojo;
import com.ss.entity.pojo.WFTOWeightAndCardNos;
import com.ss.service.factory.ServiceFactory;
import com.ss.util.LoggerUtil;
/**
 * 数据接口，用于jsp页面请求list的json文本的Servlet
 * @author Administrator
 *
 */
public class ActionServlet extends HttpServlet {
	private static Logger logger = LoggerUtil.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		String result = null;
		if(action != null && action.equals("superSchedule")) {
			result = getSuperSchedule();
		} else if(action != null && action.equals("producemException")) {
			result = getProducemException();
		} else if(action != null && action.equals("dailyfeedingtube")) {
			result = getDailyfeedingtube();
		} else if(action != null && action.equals("scrapReport")) {
			result = getScrapReport();
		} else if(action != null && action.equals("producemExceptionChart")) {
			result = getProducemExceptionChart();
		} else if(action != null && action.equals("onepieceflow")) {
			result = getOnepieceflow();
		} else if(action != null && action.equals("onepieceflow_detail")) {
			String produceM_BillNo = req.getParameter("produceM_BillNo");
			result = getOnepieceflow_detail(produceM_BillNo);
		} else if(action != null && action.equals("userList")) {
			HttpSession session = req.getSession();
			User u = (User)session.getAttribute("user");
			if(u != null && u.getUserId().equals("1001") && u.getPassword().equals("admin")) {
				logger.error("取数据getUserList");
				result = getUserList();
			}
		} else if(action != null && action.equals("viewPeopleReport")) {
			result = getViewPeopleReport();
		} else if(action != null && action.equals("vpr_user_detail")) {
			String d_UserName = req.getParameter("d_UserName");
			result = getVprsByUserName(d_UserName);
		} else if(action != null && action.equals("vpr_produceM_detail")) {
			String produceM_Name = req.getParameter("produceM_Name");
			result = getVprsByProduceM_Name(produceM_Name);
		} else if(action != null && action.equals("goodUserChart")) {
			result = getGoodUsers();
		} else if(action != null && action.equals("workflowTimeoutChart")) {
			result = getWeightAndQuantity();
		} else if(action != null && action.equals("workflowComplete")) {
			result = getWorkflowComplete();
		} else if(action != null && action.equals("chkPackage")) {
			result = getChkPackage();
		} else if(action != null && action.equals("workflowCompleteChart")) {
			result = getVwfcTotalWeiAndQua();
		} else if(action != null && action.equals("chkPackageChart")) {
			result = getChkpTotalWeiAndQua();
		} else if(action != null && action.equals("scrapReportChart")) {
			result = getProNWeiAndQuas();
		} else if(action != null && action.equals("producemWorkorder")) {
			result = getProducemWorkorder();
		} else if(action != null && action.equals("pwo_produceM_detail")) {
			String produceM_Name = req.getParameter("produceM_Name");
			result = getPwosByProduceM_Name(produceM_Name);
		} else if(action != null && action.equals("wmsInboundDetail")) {
			result = getWmsInboundDetail();
		}
		logger.info("dopost");
		PrintWriter writer = resp.getWriter();
		writer.write(result);
		writer.flush();
		writer.close();
	}
	
	public static String getSuperSchedule() {
		List<ViewMyWorkflowTimeout> mftos = ServiceFactory.getMftoService().getWorkFlowTimeOuts();
		JSONArray jsonArray = JSONArray.fromObject(mftos); 
		//转成Json格式
		String strResult = "{\"list\":" + jsonArray.toString() + "}";
		return strResult;
    }
	
	public static String getProducemException() {
		List<ViewProducemException> vpes = ServiceFactory.getVpeService().getViewProducemExceptions();
		JSONArray jsonArray = JSONArray.fromObject(vpes); 
		//转成Json格式
		String strResult = "{\"list\":" + jsonArray.toString() + "}";
		return strResult;
    }
	
	public static String getDailyfeedingtube() {
		List<ViewDailyfeedingtube> vdfts = ServiceFactory.getVdftService().getViewDailyfeedingtubes();
		JSONArray jsonArray = JSONArray.fromObject(vdfts); 
		Map<String, BigDecimal> totalWeiAndQua = ServiceFactory.getVdftService().getTotalWeiAndQua();
		BigDecimal totalWeight = totalWeiAndQua.get("totalWeight");
		BigDecimal totalQuantity = totalWeiAndQua.get("totalQuantity");
		//转成Json格式
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + ",\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getScrapReport() {
		List<ViewScrapReport> vsrs = ServiceFactory.getVsrService().getViewScrapReports();
		JSONArray jsonArray = JSONArray.fromObject(vsrs); 
		Map<String, BigDecimal> totalWeiAndQua = ServiceFactory.getVsrService().getTotalWeiAndQua();
		BigDecimal totalWeight = totalWeiAndQua.get("totalWeight");
		BigDecimal totalQuantity = totalWeiAndQua.get("totalQuantity");
		//转成Json格式
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + ",\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getProducemExceptionChart() {
		//获取当月生产异常总重量
		BigDecimal totalWeight = ServiceFactory.getVpeService().getTotalWeight();
		//获取当月各个工序生产异常重量。
		Map<String, BigDecimal> ProduceM_Weight = ServiceFactory.getVpeService().getWeightsFromProduceM_Name();
		//获取当月各类生产异常的重量。
		Map<String, BigDecimal> D_Type_Weight = ServiceFactory.getVpeService().getWeightsFromD_Type();
		//定义存储工序生产异常重量pojo类和存储类别生产异常重量pojo类的List容器
		List<ProduceMWeight> pws = new ArrayList<ProduceMWeight>();
		List<DTypeWeight> tws = new ArrayList<DTypeWeight>();
		Set<Entry<String, BigDecimal>> entrySet_p = ProduceM_Weight.entrySet();
		Set<Entry<String, BigDecimal>> entrySet_t = D_Type_Weight.entrySet();
		for(Entry e : entrySet_p) {
			ProduceMWeight pw = new ProduceMWeight();
			pw.setProduceM_Name(e.getKey().toString());
			pw.setWeight((BigDecimal)e.getValue());
			pws.add(pw);
		}
		for(Entry e : entrySet_t) {
			DTypeWeight tw = new DTypeWeight();
			tw.setD_Type(e.getKey().toString());
			tw.setWeight((BigDecimal)e.getValue());
			tws.add(tw);
		}
		//将List转为JSON对象
		JSONArray jsonArray_p = JSONArray.fromObject(pws); 
		JSONArray jsonArray_t = JSONArray.fromObject(tws); 
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"list_p\":" + jsonArray_p.toString() + ",\"list_t\":" + jsonArray_t.toString() + "}";
		return strResult;
	}
	
	public static String getOnepieceflow() {
		List<ViewOnepieceflow> vofs = ServiceFactory.getVofService().getViewOnepieceflows();
		JSONArray jsonArray = JSONArray.fromObject(vofs); 
		//转成Json格式
		String strResult = "{\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getOnepieceflow_detail(String ProduceM_BillNo) {
		List<ViewOnepieceflow> vofs = ServiceFactory.getVofService().getDetailFromProduceM_BillNo(ProduceM_BillNo);
		JSONArray jsonArray = JSONArray.fromObject(vofs); 
		//转成Json格式
		String strResult = "{\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getUserList() {
		List<User> users = ServiceFactory.getUserService().getUsers();
		List<UserPojo> userPojos = new ArrayList<UserPojo>();
		for(User u : users) {
			UserPojo userPojo = new UserPojo();
			userPojo.initFromUser(u);
			userPojos.add(userPojo);
		}
		JSONArray jsonArray = JSONArray.fromObject(userPojos); 
		//转成Json格式
		String strResult = "{\"list\":" + jsonArray.toString() + "}";
		
		return strResult;
	}
	
	public static String getGoodUsers() {
		List<GoodUser> goodUsers = ServiceFactory.getVprService().getGoodUsers();
		JSONArray jsonArray = JSONArray.fromObject(goodUsers); 
		//转成Json格式
		String strResult = "{\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getViewPeopleReport() {
		List<ViewPeopleReport> vprs = ServiceFactory.getVprService().getViewPeopleReports();
		JSONArray jsonArray = JSONArray.fromObject(vprs); 
		//转成Json格式
		Map<String, BigDecimal> totalWeiAndQua = ServiceFactory.getVprService().getTotalWeiAndQua();
		BigDecimal totalWeight = totalWeiAndQua.get("totalWeight");
		BigDecimal totalQuantity = totalWeiAndQua.get("totalQuantity");
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + ",\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getVprsByProduceM_Name(String produceM_Name) {
		List<ViewPeopleReport> vprs = ServiceFactory.getVprService().getVprsByProduceM_Name(produceM_Name);
		JSONArray jsonArray = JSONArray.fromObject(vprs); 
		//转成Json格式
		Map<String, BigDecimal> totalWeiAndQua = ServiceFactory.getVprService().getTotalWeiAndQuaByPro_N(produceM_Name);
		BigDecimal totalWeight = totalWeiAndQua.get("totalWeight");
		BigDecimal totalQuantity = totalWeiAndQua.get("totalQuantity");
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + ",\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getVprsByUserName(String userName) {
		List<ViewPeopleReport> vprs = ServiceFactory.getVprService().getVprsByUserName(userName);
		JSONArray jsonArray = JSONArray.fromObject(vprs); 
		//转成Json格式
		Map<String, BigDecimal> totalWeiAndQua = ServiceFactory.getVprService().getTotalWeiAndQuaByUserName(userName);
		BigDecimal totalWeight = totalWeiAndQua.get("totalWeight");
		BigDecimal totalQuantity = totalWeiAndQua.get("totalQuantity");
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + ",\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getWeightAndQuantity() {
		List<WFTOWeightAndCardNos> wtAndQts = ServiceFactory.getMftoService().getWeightAndCardNos();
		JSONArray jsonArray = JSONArray.fromObject(wtAndQts); 
		//转成Json格式
		String strResult = "{\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getWorkflowComplete() {
		List<ViewMyWorkflowComplete> vwfcs = ServiceFactory.getVwfcService().getWorkFlowCompletes();
		JSONArray jsonArray = JSONArray.fromObject(vwfcs); 
		//转成Json格式
		String strResult = "{\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getChkPackage() {
		List<ChkPackage> chkps = ServiceFactory.getChkpService().getChkPackages();
		JSONArray jsonArray = JSONArray.fromObject(chkps); 
		//转成Json格式
		String strResult = "{\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getVwfcTotalWeiAndQua() {
		Map<String, BigDecimal> result = ServiceFactory.getVwfcService().getTotalWeiAndQua();
		BigDecimal totalWeight = result.get("totalWeight");
		BigDecimal totalQuantity = result.get("totalQuantity");
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + "}";
		return strResult;
	}
	
	public static String getChkpTotalWeiAndQua() {
		Map<String, BigDecimal> result = ServiceFactory.getChkpService().getTotalWeightAndQuantity();
		BigDecimal totalWeight = result.get("totalWeight");
		BigDecimal totalQuantity = result.get("totalQuantity");
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + "}";
		return strResult;
	}
	
	public static String getProNWeiAndQuas() {
		List<ProNWeiAndQua> pnaqs = ServiceFactory.getVsrService().getProNWeiAndQuas();
		JSONArray jsonArray = JSONArray.fromObject(pnaqs); 
		//转成Json格式
		String strResult = "{\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getProducemWorkorder() {
		List<ProducemWorkorder> pwos = ServiceFactory.getPwoService().getProducemWorkorders();
		JSONArray jsonArray = JSONArray.fromObject(pwos); 
		//转成Json格式
		Map<String, BigDecimal> totalWeiAndQua = ServiceFactory.getPwoService().getTotalWeiAndQua();
		BigDecimal totalWeight = totalWeiAndQua.get("totalWeight");
		BigDecimal totalQuantity = totalWeiAndQua.get("totalQuantity");
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + ",\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getPwosByProduceM_Name(String produceM_Name) {
		List<ProducemWorkorder> pwos = ServiceFactory.getPwoService().getPwosByProduceM_Name(produceM_Name);
		JSONArray jsonArray = JSONArray.fromObject(pwos); 
		//转成Json格式
		Map<String, BigDecimal> totalWeiAndQua = ServiceFactory.getPwoService().getTotalWeiAndQuaByPro_N(produceM_Name);
		BigDecimal totalWeight = totalWeiAndQua.get("totalWeight");
		BigDecimal totalQuantity = totalWeiAndQua.get("totalQuantity");
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + ",\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static String getWmsProduct() {
		List<WmsProduct> wps = ServiceFactory.getWpService().getWmsProducts();
		JSONArray jsonArray = JSONArray.fromObject(wps); 
		//转成Json格式
		Map<String, BigDecimal> totalWeiAndQua = ServiceFactory.getWpService().getTotalWeiAndQua();
		BigDecimal totalWeight = totalWeiAndQua.get("totalWeight");
		BigDecimal totalQuantity = totalWeiAndQua.get("totalQuantity");
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + ",\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	public static String getWmsInboundDetail() {
		List<WmsInboundDetail> wps = ServiceFactory.getWidService().getWmsInboundDetails();
		JSONArray jsonArray = JSONArray.fromObject(wps); 
		//转成Json格式
		Map<String, BigDecimal> totalWeiAndQua = ServiceFactory.getWidService().getTotalWeiAndQua();
		BigDecimal totalWeight = totalWeiAndQua.get("totalWeight");
		BigDecimal totalQuantity = totalWeiAndQua.get("totalQuantity");
		String strResult = "{\"totalWeight\":" + totalWeight + ",\"totalQuantity\":" + totalQuantity + ",\"list\":" + jsonArray.toString() + "}";
		return strResult;
	}
	
	public static void main(String[] args) {
		System.out.println(getWmsProduct());
	}
}
