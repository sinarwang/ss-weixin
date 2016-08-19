package com.ss.process;

import java.util.List;

import com.ss.entity.ArticleItem;
import com.ss.entity.ReceiveXmlEntity;
import com.ss.entity.SendXmlEntity;

public class OutXmlProcess {
	private static OutXmlProcess outXmlProcess; 
	private static TextProcess textProcess = TextProcess.getInstance();
	private static ArticlesProcess articlesProcess = ArticlesProcess.getInstance();
	private static LinkProcess linkProcess = LinkProcess.getInstance();
	
	public static OutXmlProcess getInstance() {
		if(outXmlProcess == null) {
			outXmlProcess = new OutXmlProcess();
		}
		return outXmlProcess;
	}
	/**
     * 通过接收XML实体类对象和设定好的发送文本，确定发送Text类型的XML文本。
     * @param receiveXmlEntity 接受XML实体对象。
     * @param respContent 设定好的发送文本。
     * @return 要发送的Xml文本
     */
    public static String getTextResult(ReceiveXmlEntity receiveXmlEntity, String respContent) {
    	//对发送Xml实体对象进行确定
    	SendXmlEntity sendXmlEntity = new SendXmlEntity();
    	sendXmlEntity.setFromUserName(receiveXmlEntity.getToUserName());
    	sendXmlEntity.setToUserName(receiveXmlEntity.getFromUserName());
    	sendXmlEntity.setCreateTime(receiveXmlEntity.getCreateTime());
    	sendXmlEntity.setMsgType("text");
    	sendXmlEntity.setContent(respContent);
    	
    	return textProcess.getXmlResult(sendXmlEntity);
    }
    /**
     * 通过接收XML实体类对象和设定好的发送文本，确定发送图文类型的XML文本。
     * @param receiveXmlEntity 接受XML实体对象。
     * @param articleItems 图文消息信息（ArticleItem）的List。
     * @return 要发送的Xml文本
     */
    public static String getArticlesResult(ReceiveXmlEntity receiveXmlEntity, List<ArticleItem> articleItems) {
    	//对发送Xml实体对象进行确定
    	SendXmlEntity sendXmlEntity = new SendXmlEntity();
    	sendXmlEntity.setFromUserName(receiveXmlEntity.getToUserName());
    	sendXmlEntity.setToUserName(receiveXmlEntity.getFromUserName());
    	sendXmlEntity.setCreateTime(receiveXmlEntity.getCreateTime());
    	sendXmlEntity.setMsgType("news");
    	sendXmlEntity.setArticleCount(Integer.toString(articleItems.size()));
    	sendXmlEntity.setArticles(articleItems);
    	
    	return articlesProcess.getXmlResult(sendXmlEntity);
    }
    /**
     * 通过接收XML实体类对象和设定好的发送文本，确定发送链接类型的XML文本。
     * @param receiveXmlEntity 接受XML实体对象。
     * @param title 图文消息标题
     * @param description 图文消息描述
     * @param url 点击图文消息跳转链接
     * @return 要发送的Xml文本
     */
    public static String getLinkResult(ReceiveXmlEntity receiveXmlEntity, String title, String description, String url) {
    	//对发送Xml实体对象进行确定   
    	SendXmlEntity sendXmlEntity = new SendXmlEntity();
    	sendXmlEntity.setFromUserName(receiveXmlEntity.getToUserName());
    	sendXmlEntity.setToUserName(receiveXmlEntity.getFromUserName());
    	sendXmlEntity.setCreateTime(receiveXmlEntity.getCreateTime());
    	sendXmlEntity.setMsgType("link");
    	sendXmlEntity.setTitle(title);
    	sendXmlEntity.setDescription(description);
    	sendXmlEntity.setUrl(url);
    	sendXmlEntity.setMsgId(receiveXmlEntity.getMsgId());
    	
    	return linkProcess.getXmlResult(sendXmlEntity);
    }
}
