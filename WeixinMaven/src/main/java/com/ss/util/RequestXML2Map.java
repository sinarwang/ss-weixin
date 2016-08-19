package com.ss.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 
 * @author Administrator
 *
 */
public class RequestXML2Map {
	/**
	 * 将request中的xml文本解析为Map容器返回。
	 * @param request http请求
	 * @return 储存有request请求信息的Map类型容器
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");

        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(isr);
        //遍历将根节点下的子节点加入map当中
        Element Node = document.getRootElement();
        List<Element> elements = Node.elements();
        for(Element e: elements) {
        	map.put(e.getName(), e.getText());
        }
        
        isr.close();
        is.close();
        /*String userName = document.selectSingleNode("//ToUserName").getText();
        map.put("ToUserName", userName);
        String fromUserName = document.selectSingleNode("//FromUserName").getText();
        map.put("FromUserName", fromUserName);
        String createTime = document.selectSingleNode("//CreateTime").getText();
        map.put("CreateTime", createTime);
        String msgType = document.selectSingleNode("//MsgType").getText();
        map.put("MsgType", msgType);
        String content = document.selectSingleNode("//Content").getText();
        map.put("Content", content);
        String msgId = document.selectSingleNode("//MsgId").getText();
        map.put("MsgId", msgId);*/
        return map;
    }
}
