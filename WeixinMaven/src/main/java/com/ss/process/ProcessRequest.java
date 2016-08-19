package com.ss.process;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ss.entity.ReceiveXmlEntity;
import com.ss.entity.User;
import com.ss.service.UserService;
import com.ss.service.factory.ServiceFactory;
import com.ss.util.LoggerUtil;
import com.ss.util.Map2EntityProcess;
import com.ss.util.RequestXML2Map;
/**
 * 对各类请求进行处理的类。
 * @author Administrator
 *
 */
public class ProcessRequest {
	public static final String appUrl = "http://1.shangshangweixin.applinzi.com/";
	private static OutXmlProcess outXmlProcess = OutXmlProcess.getInstance();
	private static Logger logger = LoggerUtil.getInstance();
	private static TextProcess textProcess = TextProcess.getInstance();
	/**
	 * 根据request请求进行具体的处理
	 * @param request Http请求
	 * @param response Http回应
	 * @return
	 * @throws Exception
	 */
    public static String process(HttpServletRequest request,HttpServletResponse response) throws Exception{
        @SuppressWarnings("unchecked")
        //解析请求Xml文档为map类型容器
        Map<String, String> map = RequestXML2Map.parseXml(request);
        //将map转化为接收Xml实体对象
        ReceiveXmlEntity receiveXmlEntity = new Map2EntityProcess().getMsgEntity(map);  
        //最终返回的xml文本
        String result = "";
        //回复的文本内容
        String respContent = "";
        
        String msgType = receiveXmlEntity.getMsgType();
        //logger.error("MsgType:" + receiveXmlEntity.getMsgType() + "Event:" + receiveXmlEntity.getEvent());
        //文本消息
        if (msgType.equals("text")) {
        	//若接受到的文本消息是超排程，则进行相关链接消息，或图文回复处理
			/*String title = "超排程相关信息";
			String description = "超排程相关详细信息";
			String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/superSchedule.jsp";
			//先随意设一个msgId。
			String msgId = "1213344325";
			logger.info("------begin-----getLinkResult");
			getLinkResult(receiveXmlEntity, title, description, url, msgId);*/
        	
        	 
        	//通过接受XML实体类对象receiveXmlEntity和设定好的发送文本respContent，确定发送XML文本。
        	result = textProcess .EntityProcessForContent(receiveXmlEntity);
        	
        }
        //图片消息
        else if (msgType.equals("image")) {
        	respContent = "";
        	return null;
        }
        //声音消息
        else if (msgType.equals("voice")) {
        	respContent = "";
        	return null;
        }
        //视频消息
        else if (msgType.equals("video")) {
        	respContent = "";
        	return null;
        }
        //地理位置
        else if (msgType.equals("location")) {
        	respContent = "";
        	return null;
        }
        //事件类型
        else if (msgType.equals("event")) {
        	String event = receiveXmlEntity.getEvent();
        	//订阅
        	if (event.equals("subscribe")) {
        		logger.error("----------------关注事件开始-----------------");
        		respContent = "Hi，感谢关注！为保证您使用微信公众号查询RFID系统相关信息，请先进行【" +
        				"<a href=\"" + appUrl + "registerServlet?openId=" + receiveXmlEntity.getFromUserName() + "&action=unCheck\">身份验证</a>" + "】\n\n" +
        						"小智将竭诚为您提供安全快捷的在线订单查询服务。\n\n";
        		//通过接受XML实体类对象receiveXmlEntity和设定好的发送文本respContent，确定发送XML文本。
            	result = outXmlProcess.getTextResult(receiveXmlEntity, respContent);
        	}
        	//取消订阅
        	else if (event.equals("unsubscribe")) {
        		String openId = receiveXmlEntity.getFromUserName();
        		if(ServiceFactory.getUserService().deleteUser(openId)) {
        			logger.info("delete User which openId=" + openId);
        		} else {
        			logger.info("delete User which openId=" + openId + " error!");
        		}
        	}
        	//点击菜单
        	else if (event.equals("CLICK")) {
        		String eventKey = receiveXmlEntity.getEventKey();
        		result = textProcess.getContentFromEventKey(eventKey, receiveXmlEntity);
        	}
        }
        return result;
    }
    
    
}