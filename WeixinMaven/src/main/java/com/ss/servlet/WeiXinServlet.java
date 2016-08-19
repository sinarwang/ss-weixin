package com.ss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ss.process.ProcessRequest;
import com.ss.util.LoggerUtil;
import com.ss.util.SignUtil;
import com.ss.util.accessToken.AccessTokenThread;

/**
 * 核心请求处理类 
 * 
 */
public class WeiXinServlet extends HttpServlet {
	private Logger logger = LoggerUtil.getInstance();
	
	/**
	 * 通过get请求进行服务器的对接。
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
       // 微信加密签名  
       String signature = request.getParameter("signature");  
       // 时间戳  
       String timestamp = request.getParameter("timestamp");  
       // 随机数  
       String nonce = request.getParameter("nonce");  
       // 随机字符串  
       String echostr = request.getParameter("echostr");  
       PrintWriter out = response.getWriter();  
       // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
       if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
    	   logger.info(echostr);
           out.print(echostr);  
       }  
       out.close();
       out = null;
    }
    /**
     * 通过post请求,处理微信服务器准发过来的用户信息。
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// 调用核心业务类接收消息、处理消息
		String respMessage = null;
		try {
			respMessage = ProcessRequest.process(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
    	
       
		//try {
		//	InputStream is = request.getInputStream();
		    // 读取输入流
		    /*SAXReader reader = new SAXReader();
			Document document = null;
			try {
				document = reader.read(is);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		

			String toUserName = document.selectSingleNode("//ToUserName").getText();
	        String fromUserName = document.selectSingleNode("//FromUserName").getText();
	        String createTime = document.selectSingleNode("//CreateTime").getText();
	        String msgType = document.selectSingleNode("//MsgType").getText();
	        String content = document.selectSingleNode("//Content").getText();
	        String msgId = document.selectSingleNode("//MsgId").getText();
	        
	        String result = "<xml>" + 
	        "<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>" + 
	        "<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>" + 
	        "<CreateTime>" + createTime + "</CreateTime>" + 
	        "<MsgType><![CDATA[" + msgType + "]]></MsgType>" + 
	        "<Content><![CDATA[" + content + "]]></Content>" + 
	        "</xml>";

			
			PrintWriter out = response.getWriter();
			out.print(result);*/
    }  
}
