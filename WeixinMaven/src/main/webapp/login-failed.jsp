<%@ page language="java" import="java.util.*, org.apache.log4j.Logger, com.ss.servlet.*, com.ss.util.*, com.ss.util.accessToken.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<%
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
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>登录失败</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="css/animate.min.css">
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script>
	wx.config({
		 debug:false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		 appId:'<%=appId%>', // 必填，公众号的唯一标识
		 timestamp:'<%=ret.get("timestamp")%>', // 必填，生成签名的时间戳
		 nonceStr:'<%=ret.get("nonceStr")%>', // 必填，生成签名的随机串
		 signature:'<%=ret.get("signature")%>',// 必填，签名，见附录1
		 jsApiList:['checkJsApi','closeWindow','hideOptionMenu'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
	wx.ready(function(){
		//alert("ready");
		wx.hideOptionMenu();
		$('#closeWindow').onclick = function () {
		    wx.closeWindow();
		  };
		  
		  $('#checkJsApi').onclick = function () {
			    wx.checkJsApi({
			      jsApiList: [
			        'closeWindow'
			      ],
			      success: function (res) {
			        //alert(JSON.stringify(res));
			      }
			    });
		  }
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	});
	wx.error(function (res) {
		  alert(res.errMsg);
	});
	
	function returnWeiXin() {
    	wx.closeWindow();
    }
    
	</script>
</head>
<body>
<div class="middle-box text-center animated fadeInDown" style="margin-top:80px;">
    <div>
        <div>
            <div class="logo-name"><img src="images/success.png" width="200" height="216"></div>
        </div>
        <h4 style="margin-top:25px;font-weight:bold;font-family:"微软雅黑";">登录失败！</h4>
        <h4 style="margin-top:10px;font-weight:bold;font-family:"微软雅黑";">请点击关闭继续查看！</h4>
        <form class="m-t" role="form" action="index.html" style="margin-top:50px;">
            <button id="closeWindow" onclick="returnWeiXin()" type="button" class="btn btn-primary block full-width m-b">关 闭</button>
        </form>
    </div>
</div>
<script src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>