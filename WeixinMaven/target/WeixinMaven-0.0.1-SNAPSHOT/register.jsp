<%@ page language="java" import="java.util.*, org.apache.log4j.Logger, com.ss.servlet.*, com.ss.service.*, com.ss.util.*, com.ss.util.accessToken.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<%
String openId = request.getParameter("openId");
Logger logger = LoggerUtil.getInstance();
logger.error("-----------------register.jsp中获得openId=" + openId + "---------------");
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" >
	<title>用户身份验证</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="css/animate.min.css">
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript">
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
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	});
	wx.error(function (res) {
		  //alert(res.errMsg);
	});
    
	function ajax() {
	    //先声明一个异步请求对象
	    var xmlHttpReg = null;
	    
	    if (window.ActiveXObject) {//如果是IE
	        xmlHttpReg = new ActiveXObject("Microsoft.XMLHTTP");
	    } else if (window.XMLHttpRequest) {
	        xmlHttpReg = new XMLHttpRequest(); //实例化一个xmlHttpReg
	    }

	    //如果实例化成功,就调用open()方法,就开始准备向服务器发送请求
	    if (xmlHttpReg != null) {
	    	var url = "registerServlet?action=check&openId=" + registerForm.openId.value + "&userId=" + registerForm.userId.value + "&userName=" + registerForm.userName.value + "&password=" + registerForm.password.value + "&confirm_password=" + registerForm.confirm_password.value;
	    	xmlHttpReg.open("get", url, true);
	        xmlHttpReg.onreadystatechange = doResult; //设置回调函数
	        xmlHttpReg.send(null);
	    }

	    //回调函数
	    //一旦readyState的值改变,将会调用这个函数,readyState=4表示完成相应

	    //设定函数doResult()
	    function doResult() {
	        if (xmlHttpReg.readyState == 4) {//4代表执行完成
	            if (xmlHttpReg.status == 200) {//200代表执行成功
		        	var result = xmlHttpReg.responseText.trim();
		        	//alert(result);
	            	if(result == "succeed") {
	            		//alert("恭喜，用户注册成功！");
	            		location.href = "register-success.jsp";
	            	} else if(result == "failed1") {
	            		alert("抱歉，用户注册失败！可能原因：" +
								"1、用户已经存在。    " +
								"2、内部程序发生错误。");
	            	} else if(result == "failed2") {
	            		alert("抱歉，用户注册失败！可能原因：密码和确认密码填写不相同。");
	            	} else if(result == "failed3") {
	            		alert("抱歉，用户注册失败！可能原因：表单填写不完全。");
	            	} else if(result == "failed4") {
	            		alert("抱歉，用户注册失败！可能原因：用户没有通过微信注册。");
	            	} 
	            }
	        }
	    }
	}
	</script>
</head>
<body>
<div class="login">
    <div class="full">
        <img src="images/b1.png">
    </div>
    <div class="middle-box text-center animated fadeInDown join">
        <div>
            <div style="margin-bottom:30px;">
                <div class="logo-name">
                    <img src="images/logo-login.png" width="200" height="200">
                </div>
            </div>
            <form name="registerForm" class="m-t" role="form">
            	<input type="hidden" name="openId" value="<%=openId%>"/>
                <div class="form-group">
                    <input type="text" name="userId" class="form-control" placeholder="工号" required="">
                    <div class="index-tu"><div><img src="images/gonghao.png" width="19" height="22"></div></div>
                </div>
                <div class="form-group">
                    <input type="text" name="userName" class="form-control" placeholder="姓名" required="">
                    <div class="index-tu"><div><img src="images/xingming.png" width="19" height="22"></div></div>
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="密码" required="">
                    <div class="index-tu"><div><img src="images/mima.png" width="19" height="22"></div></div>
                </div>
                <div class="form-group">
                    <input type="password" name="confirm_password" class="form-control" placeholder="确认密码" required="">
                    <div class="index-tu"><div><img src="images/queding.png" width="19" height="22"></div></div>
                </div>
                <button type="button" onclick="ajax()" class="btn btn-primary block full-width m-b">注 册</button>
                <p class="text-muted text-center"> <br/><a href="#"><small>忘记密码了？</small></a> | <a href="register.html"><small>注册一个新账号</small></a>
                </p>
            </form>
        </div>
    </div>
</div>
<script src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>