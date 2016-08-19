<%@ page language="java" import="java.util.*, org.apache.log4j.Logger, com.ss.servlet.*, com.ss.service.*, com.ss.util.*, com.ss.util.accessToken.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String openId = request.getParameter("openId");
	boolean isExist = false;
	if(openId == null || openId.equals("")) {
		isExist = false;
	} else {
		isExist = UserService.getInstance().isExist(openId);
	}
	Logger logger = LoggerUtil.getInstance();
	StringBuffer requestUrl = request.getRequestURL();
	String queryString = request.getQueryString();
	String url = requestUrl.toString();
	if(queryString != null) {
		url = requestUrl +"?"+queryString;
	}
	String appId = AccessTokenThread.getAppId();
	Map<String, String> ret = Sign.getAuthorityCheck(url);
	for (Map.Entry entry : ret.entrySet()) {
        logger.info(entry.getKey() + ", " + entry.getValue());
    }
%>
