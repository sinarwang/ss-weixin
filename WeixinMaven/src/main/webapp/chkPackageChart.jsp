<%@ page language="java" import="java.util.*, org.apache.log4j.Logger, com.ss.servlet.*, com.ss.service.*, com.ss.util.*, com.ss.util.accessToken.*" pageEncoding="UTF-8"%>
<%@ include file="CheckOpenIdAndWeiXinJS.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<title>本月打包数据统计</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/animate.min.css">
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="js/highchart-new.js"></script>
	</head>
	<body>
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
	
	$(document).ready(function(){ 
		var totalWeight;
		var totalQuantity;
		
		if(<%=isExist%>) {
			//alert(1);
			$.ajax({
			      url: "actionServlet?action=chkPackageChart",
			      datatype: "json",
			      type: "post",
			      data: {},
			      success: function (data, status, xhr) {
			    	    totalWeight = eval("(" + data + ")").totalWeight;
			    	    totalWeight = parseFloat((totalWeight/1000).toFixed(2));
			    	    totalQuantity = eval("(" + data + ")").totalQuantity;
			    	  
			    	    var colors = Highcharts.getOptions().colors;
			            $('#container').highcharts({
			                chart: {
			                    
			                },
			                title: {
			                    text: ' ',
			    				style:{
			    					fontSize:40,
			    					fontFamily: '微软雅黑'
			    				}
			                },
			                subtitle: {
			                    text: ''
			                },
			                xAxis: [{
			                    categories: ['',''],

			    				labels: {
			                        format: '{value}',//格式化Y轴刻度
			                        style: {
			                            fontSize:26
			                        }
			                    } 
			                }],
			                yAxis: [{ // Primary yAxis
			                    labels: {
			                        format: '{value} t',//格式化Y轴刻度
			                        style: {
			                            color: '#89A54E',
			                            fontSize: "30px",
			                        }
			                    },
			                    title: {
			    					text: '重量(t)', 
			                        style: {
			    						fontSize: "30px",
			                            color: '#89A54E'
			                        }
			                    }
			                }, { // Secondary yAxis
			                    title: {
			    					text: '支数(支)',
			                        style: {
			    						fontSize: "30px",
			                            color: '#4572A7'
			                        }
			                    },
			                    labels: {
			                        format: '{value} 支',
			                        style: {
			                            color: '#4572A7',
			                            fontSize: "30px",
			                        }
			                    },
			                    opposite: true
			                }],
			                tooltip: {
			    				headerFormat: '',
			    				style: {                      // 文字内容相关样式
			    					color: "#0099FF",
			    					fontSize: "30px",
			    					fontWeight: "blod",
			    					fontFamily: "Courir new"
			    				}
			                },
			                //图例样式设置
			                legend: {
			                    layout: 'vertical',
			                    align: 'left',
			                    x: -120,
			                    verticalAlign: 'top',
			                    y: 100,
			                    floating: true,
			                    backgroundColor: '#FFFFFF'
			                },
			                series: [{
			                    name: '总支数',
			                    color: '#4572A7',
			                    type: 'column',
			                    yAxis: 1,
			                    data: ["", totalQuantity],
			                    tooltip: {
			                        valueSuffix: ' 支'
			                    },
			    				dataLabels: {
			    					enabled: true,
			    					formatter: function() {
			    						return this.y + " 支";
			    					},
			    					style: {
			    						fontSize: '35px',
			    					}
			    				}
			                }, {
			                    name: '总重量',
			                    color: '#89A54E',
			                    type: 'column',
			                    data: [totalWeight, ""],
			                    tooltip: {
			                        valueSuffix: ' t'
			                    },
			    				dataLabels: {
			    					enabled: true,
			    					formatter: function() {
			    						return this.y + " t";
			    					},
			    					style: {
			    						fontSize: '35px',
			    					}
			    				}
			                }]
			            });
			      } 
			});
		}
	});
	</script>
	<div style="width:100%;">
	    <div style="width:380px;height:114px;margin:20px auto;line-height:15px;"><img src="images/dabao.png"><br>
	        <div style="width:120px;height:25px;float:left;margin-left:90px;margin-top:10px;font-size:30px">
	            <div style="width:20px;height:15px;background:#89a54e;float:left;margin-right:5px;"></div>总重量
	        </div>
	        <div style="width:120px;height:25px;float:left;margin-top:10px;font-size:30px">
	            <div style="width:20px;height:15px;background:#4572a7;float:left;margin-right:5px;"></div>总支数
	        </div>
	    </div>
	</div>
	<div id="container" style="width:100%;height:800px;text-align:center;"></div>
	<div style="width:100%;position:absolute;height:100px;float:left">
	     <ul style="width:100%;">
	        <li style="width:25%;float:left;list-style:none">&nbsp;</li>
	        <li style="width:15%;float:left;list-style:none;text-align:center;padding-left:0px;font-size:30px;font-family:微软雅黑;">总重量</li>
	        <li style="width:35%;float:left;list-style:none;text-align:center;font-size:30px;font-family:微软雅黑;">总支数</li>
	        <li style="width:15%;float:left;list-style:none"></li>
	     
	     </ul>
	</div>
</body>
</html>