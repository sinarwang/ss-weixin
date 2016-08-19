package com.ss.process;

import java.util.List;

import org.apache.log4j.Logger;

import com.ss.entity.ArticleItem;
import com.ss.entity.SendXmlEntity;
import com.ss.util.LoggerUtil;

public class ArticlesProcess {
	private static ArticlesProcess articlesProcess;
	private Logger logger = LoggerUtil.getInstance();
	
	public static ArticlesProcess getInstance() {
		if(articlesProcess == null) {
			articlesProcess = new ArticlesProcess();
		}
		return articlesProcess;
	}
	
	
	/**
	 * 根据发送Xml实体对象构建发送xml文本。
	 * @param xmlEntity 发送Xml实体对象（SendXmlEntity类型）
	 * @return 要发送的xml文本(String类型)
	 */
	public String getXmlResult(SendXmlEntity sendXmlEntity) {
		StringBuffer result = new StringBuffer();
		result.append("<xml><ToUserName><![CDATA[");
		result.append(sendXmlEntity.getToUserName());
		result.append("]]></ToUserName><FromUserName><![CDATA[");
		result.append(sendXmlEntity.getFromUserName());
		result.append("]]></FromUserName><CreateTime>");
		result.append(sendXmlEntity.getCreateTime());
		result.append("</CreateTime><MsgType><![CDATA[");
		result.append(sendXmlEntity.getMsgType());
		result.append("]]></MsgType><ArticleCount>");
		result.append(sendXmlEntity.getArticleCount());
		result.append("</ArticleCount><Articles>");
		
		List<ArticleItem> articleItems = sendXmlEntity.getArticles();
		for(ArticleItem ai : articleItems) {
			result.append("<item><Title><![CDATA[");
			result.append(ai.getTitle());
			result.append("]]></Title><Description><![CDATA[");
			result.append(ai.getDescription());
			result.append("]]></Description><PicUrl><![CDATA[");
			result.append(ai.getPicUrl());
			result.append("]]></PicUrl><Url><![CDATA[");
			result.append(ai.getUrl());
			result.append("]]></Url></item>");
		}
		result.append("</Articles></xml>");
		return result.toString();
	}
	
}
