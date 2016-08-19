<%@ page language="java" import="java.util.*, org.apache.log4j.Logger, com.ss.servlet.*, com.ss.service.*, com.ss.util.*, com.ss.util.accessToken.*" pageEncoding="UTF-8"%>
<%@ include file="CheckOpenIdAndWeiXinJS.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<title>优秀员工</title>
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
			//定义三个数组，前两个用于生成柱状图，最后一个用于员工照片展示。
			var categories = new Array();
			var weightJson = new Array();
			var userArray = new Array();
			var userPhotoArray = new Array();
			
			if(<%=isExist%>) {
				//alert(1);
				$.ajax({
				      url: "actionServlet?action=goodUserChart",
				      datatype: "json",
				      type: "post",
				      data: {},
				      success: function (data, status, xhr) {
						  var colors = Highcharts.getOptions().colors;
						  colors.splice(1,1);
						  colors.push('#052095');
						  colors.push('#0f9505');
						  colors.push('#ec4bc0');
						  colors.push('#2a5fd8');
						  
				    	  $.each(eval("(" + data + ")").list, function(index, item) {
				    		  categories.push("<span style=\"fontsize:26\">" + item.produceM_Name + "</span><span style=\"color:#ef9920\">★</span>" + item.userName);
				    		  var weight = parseFloat((item.totalWeight/1000).toFixed(2));
				    		  var jsonObject = {
				    				  y:weight,
				    				  color:colors[index]
				    		  };
				    		  weightJson.push(jsonObject);
				    		  userArray.push(item.userName);
				    		  userPhotoArray.push(item.uid);
				          });
				    	  
				    	  
				    	  $('#container').highcharts({                                           
						        chart: {                                                           
						            type: 'bar'                                                    
						        },                                                                 
						        title: {                                                           
						            text: ' '                    
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
						                    fontSize:26,
						                    fontFamily: '微软雅黑'
					                	}
						        	}
					            },
						        yAxis: {  
						        	tickInterval: 50,	
						            min: 0,                                                        
						            title: {                                                       
						                text: '重量(t)',                             
						                align: 'high',
						                style:{ // 此处可设置样式
						                    fontSize:26,
						                    fontFamily: '微软雅黑'
					                	}
						            },                                                             
						            labels: {                                                      
						                overflow: 'justify',
						                style:{ // 此处可设置样式
						                    fontSize:26,
						                    fontFamily: '微软雅黑'
					                	}
						            }                                                              
						        },                                                                 
						        tooltip: {                                                         
						            valueSuffix: ' t',
						            style:{ // 此处可设置样式
						            	lineHeight:'40em',
						            	height:'80px',
						            	padding:'20px',
					                    fontSize:26,
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
						            name: '重量(t)',                                             
						            data: weightJson,  
						            dataLabels: {
						                enabled: true,
						                formatter: function() {
						                	return this.y + " t";
						                },
						                style: {
						                    fontSize: 26,
						                    fontFamily: '微软雅黑'
						                }
						            }   
						        }]
						  });
				    	  
				    	  var userPhotoStr ="";
				    	  for(var i=0;i<userArray.length;i++) {
				    		  //userPhotoStr = userPhotoStr + "<li style=\"width:33%;list-style:none;float:left;margin-top:20px;\"><img src=\"images/" + userPhotoArray[i] + ".jpg\"></span><img src=\"images/qi.jpg\"><br /><span style=\"padding-left:30px;line-height:55px;font-size:20px;\">" + userArray[i] + "</li>";
				    		  userPhotoStr = userPhotoStr + "<li style=\"width:33%;list-style:none;float:left;margin-top:20px;\"><img src=\"images/user.jpg\"></span><img src=\"images/qi.jpg\"><br /><span style=\"padding-left:30px;line-height:55px;font-size:20px;\">" + userArray[i] + "</li>";
				    	  }
				    	  $('#userPhoto').append(userPhotoStr);
				      } 
				});
			}
		});
		</script>
		<div style="width:100%;">
			<div style="width:605px;height:194px;margin:0 auto;"><img src="images/yiuxiu.jpg"></div>
		</div>
		<div id="container" style="min-width:500px;height:1700px;text-align:center;"></div>
		<div style="width:100%;margin-top:10px;float:left">
			<div style="width:250px;height:30px;margin-left:70px;font-size:30px;">最优秀员工照片</div>
			<div style="width:100%;height:auto;margin-left:70px;">
				<ul style="width:100%;" id="userPhoto">
				</ul>
			</div>
		</div>
	</body>
</html>