<%@ page language="java" import="java.util.*, org.apache.log4j.Logger, com.ss.servlet.*, com.ss.service.*, com.ss.util.*, com.ss.util.accessToken.*" pageEncoding="UTF-8"%>
<%@ include file="CheckOpenIdAndWeiXinJS.jsp" %>
<% 
String produceM_BillNo = request.getParameter("produceM_BillNo");
%>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, minimum-scale= 1.0, initial-scale= 1.0">
		<title>单件流详情</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/timeline/base.css">
		<script src="js/jquery-1.11.1.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/login.css">
		<link rel="stylesheet" type="text/css" href="css/animate.min.css">
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="js/highchart-new.js"></script>
	</head>
	<body>
		<div class="timeline-top">
		    <a href="onepieceflow.jsp?openId=<%=openId%>"><i class="glyphicon glyphicon-chevron-left"></i>返回 </a>
		    <span>工艺体验</span>
		</div>
		
		<div class="container">
			<div id="timeline">
			</div>
		</div>
		<div id="container" style="min-width:100%;height:350px"></div>
		<div class="container-two-top">
	    	<div class="container-two-top-div"><img src="images/haoshi.png"></div>
		    <table class="container-two-top-table">
		        <tr>
		            <td><span>总共耗时</span><div class="container-two-top-table-div"></div></td>
		            <td><span>生产耗时</span><div class="container-two-top-table-div-two"></div></td>
		            <td><span>转工序耗时</span><div class="container-two-top-table-div-three"></td>
		        </tr>
		    </table>
		</div>
		<div class="table-responsive" >
		    <table class="table" style="text-align:center;">
		        <thead>
		            <tr>
		                <th style="text-align:center">生产总道次数</th>
		                <th style="text-align:center">总共耗时</th>
		                <th style="text-align:center">生产耗时</th>
		                <th style="text-align:center">转工序耗时</th>
		            </tr>
		        </thead>
		        <tbody id="totalTable">
		        </tbody>
		    </table> 
		</div>
			
		<script type="text/javascript" src="js/raphael-min.js"></script>
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
				if(<%=isExist%>) {
					//alert(1);
					$.ajax({
					      url: "actionServlet?action=onepieceflow_detail&produceM_BillNo=<%=produceM_BillNo%>",
					      datatype: "json",
					      type: "post",
					      data: {},
					      success: function (data, status, xhr) {
					    	  circleJsonObject(data);
					      } 
					});
				}
			}); 
			
			//定义生产总耗时
			var totalRealTime=0;
			//定义转工序总耗时
			var totalTimeOut=0;
			//道次数
			var processCount=0;
			
			function circleJsonObject(data) {
				$.each(eval("(" + data + ")").list, function(index, item) {
					//定义生产耗时字符串
					var realTime = getTimeFromMins(item.realTime);
					//定义转工序耗时字符串
					var timeOut = getTimeFromMins(item.timeOut);
					//获取单件流状态
					var state = getState(item);
					
					totalRealTime = totalRealTime + item.realTime;
					totalTimeOut = totalTimeOut + item.timeOut;
					processCount = processCount + 1;
					
					//加载圆圈小图标
					var str = "<div class=\"timeline-item\">" + 
								"<div class=\"timeline-icon\">" +
								"<div class=\"percentBox\">" +
								"<div id=\"bg" + (index+1) + "\"></div> ";
					//根据单件流状态设定状态值
					if(state == 1) {
						str = str + "<div class=\"pertxt-finish\"><img src=\"images/jinxingzhong.png\" width=\"25\" height=\"25\"></div>" +
									"<div id=\"txt" + (index+1) + "\" class=\"pertxt\">进行中</div>" +
									"</div>" + 
									"</div>";
					} else if(state == 2) {
						str = str + "<div class=\"pertxt-finish\"><img src=\"images/jinxingzhong.png\" width=\"25\" height=\"25\"></div>" +
									"<div id=\"txt" + (index+1) + "\" class=\"pertxt\">进行中</div>" +
									"</div>" + 
									"</div>";
					} else if(state == 3) {
						str = str + "<div class=\"pertxt-finish\"><img src=\"images/dui.png\" width=\"25\" height=\"25\"></div>" +
									"<div id=\"txt" + (index+1) + "\" class=\"pertxt\">已完成</div>" +
									"</div>" + 
									"</div>";
					} else if(state == 4) {
						str = str + "<div class=\"pertxt-finish\"><img src=\"images/dui.png\" width=\"25\" height=\"25\"></div>" +
									"<div id=\"txt" + (index+1) + "\" class=\"pertxt\">已完成</div>" +
									"</div>" + 
									"</div>";
					} else {
						str = str + "<div class=\"pertxt-finish\"></div>" +
									"<div id=\"txt" + (index+1) + "\" class=\"pertxt\">未知</div>" +
									"</div>" + 
									"</div>";
					}
					
					//判断在左还是在右
					if(index%2 == 0) {
						str = str + "<div class=\"timeline-content right\">";
					} else {
						str = str + "<div class=\"timeline-content\">";
					}
					//判断用哪个工序图标
					if(item.produceM_Name == "打头") {
						str = str + "<h2 class=\"text-center\"><img src=\"images/datou.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "焊头") {
						str = str + "<h2 class=\"text-center\"><img src=\"images/hantou.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "检验"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/jianyan.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "矫直"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/jiaozhi.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "精整"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/jingzheng.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "冷拔"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/lengba.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "冷轧"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/lengzha.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "内磨"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/neimo.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "切管"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/qieguan.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "去油"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/quyou.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "润滑"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/runhua.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "酸洗"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/suanxi.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "退火"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/tuihuo.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "外抛"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/waipao.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else if(item.produceM_Name == "下料"){
						str = str + "<h2 class=\"text-center\"><img src=\"images/xialiao.png\" width=\"33\">" + item.produceM_Name + "</h2>";
					} else {
						str = str + "<h2 class=\"text-center\"><img src=\"\" width=\"33\">" + item.produceM_Name + "</h2>";
					}
					//根据状态盖上不同的章
					if(state == 1) {
						str = str + "<div class=\"pertxt-finish-bb\">" +
									"<img src=\"images/daichan.png\" width=\"50\" height=\"50\">" +
									"</div>";
					} else if(state == 2) {
						str = str + "<div class=\"pertxt-finish-bb\">" +
									"<img src=\"images/zaizhi.png\" width=\"50\" height=\"50\">" +
									"</div>";
					} else if(state == 3) {
						str = str + "<div class=\"pertxt-finish-bb\">" +
									"<img src=\"images/wancheng.png\" width=\"50\" height=\"50\">";
						//判断生产耗时item.realTime，若生产耗时小于5min，则判定为连刷，盖上章。
						if(item.realTime < 5) {
							str = str + "<img src=\"images/lianshua.png\" width=\"50\" height=\"50\" style=\"margin-left:-150px;margin-top:-5px\">" + 
										"</div>";
						} else {
							str = str + "</div>";
						}
					} else if(state == 4) {
						str = str + "<div class=\"pertxt-finish-bb\">" +
									"<img src=\"images/chaoqi.png\" width=\"50\" height=\"50\">";
						//判断生产耗时item.realTime，若生产耗时小于5min，则判定为连刷，盖上章。
						if(item.realTime < 5) {
							str = str + "<img src=\"images/lianshua.png\" width=\"50\" height=\"50\" style=\"margin-left:-150px;margin-top:-5px\">" + 
										"</div>";
						} else {
							str = str + "</div>";
						}
					} 
					
					//加载相关数据
					str = str + "<p>" +
							"计划开始时间：" + item.produceM_BeginDate + "<br />" +
							"计划结束时间：" + item.produceM_EndDate + "<br />" +
							"生产接料时间：" + item.produceM_StartTime + "<br />" +
							"生产完工时间：" + item.produceM_EndTime + "<br />" +
							"生产耗时：" + realTime + "<br />" +
							"<span style=\"font-size:13px;\">重量：" + item.produceM_Weight + "kg　支数：" + item.produceM_Number + "支　米数：*米</span>" +
							"</p>" +
							"<h3 class=\"text-center\">转工序耗时：" + timeOut + "</h3>" +
							"</div>" +
							"</div>";
					$("#timeline").append(str);
					
					if(state == 1) {
						eval("init('bg" + (index+1) + "',0,'#txt" + (index+1) + "','#51aed3')");
					} else if(state == 2) {
						eval("init('bg" + (index+1) + "',0.5,'#txt" + (index+1) + "','#51aed3')");
					} else if(state == 3) {
						eval("init('bg" + (index+1) + "',1,'#txt" + (index+1) + "','#51aed3')");
					} else if(state == 4) {
						eval("init('bg" + (index+1) + "',1,'#txt" + (index+1) + "','#51aed3')");
					} 
	        	});
				
				//分钟数转小时。
				var totalTime = parseFloat(((totalTimeOut+totalRealTime)/60).toFixed(1));
				totalRealTime=parseFloat((totalRealTime/60).toFixed(1));
				totalTimeOut=parseFloat((totalTimeOut/60).toFixed(1));
				
				<%-- 图表 --%>
				var colors = Highcharts.getOptions().colors,
		        categories = ['生产耗时:' + totalRealTime + '小时', '转工序耗时:' + totalTimeOut + '小时'],
		        name = 'Browser brands',
		        data = [{
		                y: totalRealTime,
		                color: colors[9],
		                drilldown: {
		                    name: 'MSIE versions',
		                    categories: ['总耗时'],
		                    data: [totalTime],
		                    color: colors[9]
		                }
		            },{
		                y: totalTimeOut,
		                color: colors[2],
		                drilldown: {
		                    name: 'Opera versions',
		                    categories: [],
		                    data: [],
		                    color: colors[2]
		                }
		            }];
			    var browserData = [];
			    var versionsData = [];
			    for (var i = 0; i < data.length; i++) {
			        browserData.push({
			            name: categories[i],
			            y: data[i].y,
			            color: data[i].color
			        });
			        for (var j = 0; j < data[i].drilldown.data.length; j++) {
			            var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5 ;
			            versionsData.push({
			                name: data[i].drilldown.categories[j],
			                y: data[i].drilldown.data[j],
			                color: Highcharts.Color(data[i].color).brighten(brightness).get()
			            });
			        }
			    }
			    $('#container').highcharts({
			        chart: {
			            type: 'pie'
			        },
			        title: {
			            text: '生产框号：<%=produceM_BillNo%>'
			        },
			        plotOptions: {
			            pie: {
			                shadow: false,
			                center: ['50%', '50%']
			            }
			        },
			        tooltip: {
			            valueSuffix: '小时'
			        },
			        series: [{
			            name: '时间',
			            data: browserData,
			            size: '90%',
			            dataLabels: {
			                formatter: function() {
			                    return this.y > 5 ? this.point.name : null;
			                },
			                color: 'black',
			                distance: -80
			            }
			        }, {
			            name: '时间',
			            data: versionsData,
			            size: '90%',
			            innerSize: '80%',
			            dataLabels: {
			                formatter: function() {
			                    // display only if larger than 1
			                    return this.y > 1 ? '<b>'+ this.point.name +':</b> '+ this.y +'小时'  : null;
			                }
			            }
			        }]
			    });
			    <%-- 统计表单生成 --%>
			    $("#totalTable").append("<tr class=\"success\" style=\"font-size:12px;\">" + 
						                "<td>" + processCount + "</td>" + 
						                "<td>" + totalTime + "小时</td>" + 
						                "<td>" + totalRealTime + "小时</td>" + 
						                "<td>" + totalTimeOut + "小时</td>" + 
						            	"</tr>");
			}
			//根据单个JsonObject获得流向单相应状态
			//若为1，则表示待产；若为2，则表示在制；若为3，则表示完成；若为4，则表示超期；若为5，则计划结束时间没有。
			function getState(item) {
				var date = item.produceM_EndDate + " 23:59:59";
				if(item.produceM_StartTime == null || item.produceM_StartTime == "") {
					return 1;
				} else if(item.produceM_EndTime == null || item.produceM_EndTime == "") {
					return 2;
				} else if(item.produceM_EndTime <= date) {
					return 3;
				} else if(item.produceM_EndTime > date) {
					return 4;
				} else {
					return 5;
				}
			}
			//根据分钟数获得处理得到的时间字符串（*时*分）
			function getTimeFromMins(mins) {
				var hour = Math.floor(mins/60);
				var min = mins-hour*60;
				var timeStr = hour + "时" + min + "分";
				return timeStr;
			}
			
			/*圆形百分比*/
			var  paper =  null;
			function init(b,n,t,c){
			    //初始化Raphael画布 
			    this.paper = Raphael(b, 44, 44); 
			    
			    //把底图先画上去 
			    this.paper.image("images/progressBg.png", 0, 0, 44, 44); 
			    
			    //进度比例，0到1，在本例中我们画65% 
			    //需要注意，下面的算法不支持画100%，要按99.99%来画 
			    var percent = n , 
			        drawPercent = percent >= 1 ? 0.9999 : percent; 
			    
			    //开始计算各点的位置，见后图 
			    //r1是内圆半径，r2是外圆半径 
			    var r1 = 19, r2 = 22, PI = Math.PI, 
			        p1 = { 
			            x:22,  
			            y:44 
			        }, 
			        p4 = { 
			            x:p1.x, 
			            y:p1.y - r2 + r1 
			        }, 
			        p2 = {  
			            x:p1.x + r2 * Math.sin(2 * PI * (1 - drawPercent)), 
			            y:p1.y - r2 + r2 * Math.cos(2 * PI * (1 - drawPercent)) 
			        }, 
			        p3 = { 
			            x:p4.x + r1 * Math.sin(2 * PI * (1 - drawPercent)), 
			            y:p4.y - r1 + r1 * Math.cos(2 * PI * (1 - drawPercent)) 
			        }, 
			        path = [ 
			            'M', p1.x, ' ', p1.y, 
			            'A', r2, ' ', r2, ' 0 ', percent > 0.5 ? 1 : 0, ' 1 ', p2.x, ' ', p2.y, 
			            'L', p3.x, ' ', p3.y, 
			            'A', r1, ' ', r1, ' 0 ', percent > 0.5 ? 1 : 0, ' 0 ', p4.x, ' ', p4.y, 
			            'Z' 
			        ].join(''); 
			    
			    //用path方法画图形，由两段圆弧和两条直线组成，画弧线的算法见后 
			    this.paper.path(path) 
			        //填充渐变色，从#3f0b3f到#ff66ff 
			        .attr({"stroke-width":0, "stroke":"#d2d4d8", "fill":"90-" + c}); 
			    
			    //显示进度文字 
			    $(t).text(Math.round(percent * 100) + "%"); 
			}
		</script>
	</body>
</html>