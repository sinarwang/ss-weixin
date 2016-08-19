<%@ page language="java" import="java.util.*, org.apache.log4j.Logger, com.ss.servlet.*, com.ss.service.*, com.ss.util.*, com.ss.util.accessToken.*" pageEncoding="UTF-8"%>
<%@ include file="CheckOpenIdAndWeiXinJS.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<title>生产报废统计报表</title>
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
			//工序数组
			var categories = new Array();
			//总重量数据数组
			var weightArray = new Array();
			//总支数数据数组
			var quantityArray = new Array();
			
			if(<%=isExist%>) {
				//alert(1);
				$.ajax({
				      url: "actionServlet?action=scrapReportChart",
				      datatype: "json",
				      type: "post",
				      data: {},
				      success: function (data, status, xhr) {
				    	  var colors = Highcharts.getOptions().colors;
				    	  $.each(eval("(" + data + ")").list, function(index, item) {
				    		  categories.push("<span style=\"fontsize:\"30px\"\">" + item.produceM_Name);
				    		  weightArray.push(item.totalWeight);
				    		  var jsonObject = {
				    				  y:item.totalQuantity,
				    				  color:'#33FF66'
				    		  };
				    		  quantityArray.push(jsonObject);
				          });
				    	  
				    	  $('#container').highcharts({                                           
						        chart: {                                                           
						            type: 'bar'                                                    
						        },                                                                 
						        title: {                                                           
						            text: ''                    
						        },                                                                 
						        subtitle: {                                                        
						            text: ' '                                  
						        },                                                                 
						        xAxis: {                                                           
						            categories: categories,
						            title: {                                                       
						                text: null                                                 
						            }, 
						        	labels:{
						                style:{ // 此处可设置样式
						                    fontSize:"30px",
						                    fontFamily: '微软雅黑'
					                	}
						        	}
					            },
						        yAxis: [{ //设置第一个y轴 
						        	//tickInterval: 20,	
						        	tickPixelInterval:100,
						        	maxPadding: 0,
						            min: 0,
						            //max:Math.max.apply(null,weightArray),
						            title: {                                                       
						                text: '重量(kg)',                             
						                align: 'high',
						                style:{ // 此处可设置样式
						                    fontSize:"30px",
						                    fontFamily: '微软雅黑'
					                	}
						            },                                                             
						            labels: {  
						            	step:4,
						                overflow: 'justify',
						                style:{ // 此处可设置样式
						                    fontSize:"30px",
						                    fontFamily: '微软雅黑'
					                	}
						            }                                                              
						        },{ //设置第二个y轴 
						        	//tickInterval: 20,
						        	tickPixelInterval:100,
						        	maxPadding: 0,
						            min: 0, 
						            //max:Math.max.apply(null,cardNosArray),
						            title: {                                                       
						                text: '支数(支)',                             
						                align: 'high',
						                style:{ // 此处可设置样式
						                    fontSize:"30px",
						                    fontFamily: '微软雅黑'
					                	}
						            },                                                             
						            labels: {      
						            	step:4,
						                overflow: 'justify',
						                style:{ // 此处可设置样式
						                    fontSize:"30px",
						                    fontFamily: '微软雅黑'
					                	}
						            },
						            opposite: true //表示是否跟第一个在反方向位置
						        }],                                                                 
						        tooltip: {                                                         
						        	shared:true,
						            style:{ // 此处可设置样式
						            	lineHeight:'40em',
						            	height:'80px',
						            	padding:'20px',
					                    fontSize:"30px",
					                    fontFamily: '微软雅黑'
				                	}
						        },                                                                 
						        plotOptions: {    
						            bar: {                                                         
						                dataLabels: {                                              
						                    enabled: true,
						                }                                                          
						            }                                                              
						        },                                                                 
						        legend: {                                                          
						            layout: 'vertical',                                            
						            align: 'right',                                                
						            verticalAlign: 'top',                                          
						            x: 140,                                                        
						            y: 200,                                                        
						            floating: true,                                                
						            borderWidth: 0,                                        
						            backgroundColor: 'blue',
						            shadow: false                                                   
						        },                                                                 
						        credits: {                                                         
						            enabled: false                                                 
						        },                                                                 
						        series: [{                                                         
						            name: '重量(kg)',                                             
						            data: weightArray,
						            yAxis:0,
						            dataLabels: {
						                enabled: false,
						                formatter: function() {
											return this.y + " kg";
										},
						                style: {
						                    fontSize: "25px",
						                    fontFamily: '微软雅黑'
						                }
						            },
						            tooltip: { valueSuffix: ' kg' }//鼠标放到曲线上时的单位
						        },{                                                         
						            name: '支数(支)',                                             
						            data: quantityArray, 
						            yAxis:1,
						            dataLabels: {
						                enabled: false,
						                formatter: function() {
											return this.y + " 支";
										},
						                style: {
						                    fontSize: "25px",
						                    fontFamily: '微软雅黑'
						                }
						            },
						            tooltip: { valueSuffix: ' 支' }//鼠标放到曲线上时的单位
						        }]
						  });
				      } 
				});
			}
		});
		</script>
		<div style="width:100%;">
		    <div style="width:380px;height:114px;margin:20px auto;line-height:15px;"><img src="images/shengchanbaofei.png"><br>
		        <div style="width:120px;height:25px;float:left;margin-left:90px;margin-top:10px;font-size:30px">
		            <div style="width:20px;height:15px;background:#95ceff;float:left;margin-right:5px;"></div>重量
		        </div>
		        <div style="width:120px;height:25px;float:left;margin-top:10px;font-size:30px">
		            <div style="width:20px;height:15px;background:#a9ff96;float:left;margin-right:5px;"></div>支数
		        </div>
		    </div>
		</div>

		<div id="container" style="min-width:500px;height:800px;text-align:center;"></div>
		<div style="width:100%;margin-top:10px;float:left">
		</div>
	</body>
</html>