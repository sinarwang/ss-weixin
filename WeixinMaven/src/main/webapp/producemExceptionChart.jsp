<%@ page language="java" import="java.util.*, org.apache.log4j.Logger, com.ss.servlet.*, com.ss.service.*, com.ss.util.*, com.ss.util.accessToken.*" pageEncoding="UTF-8"%>
<%@ include file="CheckOpenIdAndWeiXinJS.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>图表</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="css/animate.min.css">
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/highchart-new.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
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
	
	//设置总重量
	var totalWeight;
	//设置异常发生工序类别的数组和异常类别的数组
	var p_categories = new Array();
	var t_categories = new Array();
	//设置数据的Json数组(两个分别用于两张表的显示)
	var dataJson_p = new Array();
	var dataJson_t = new Array();
//第一个
$(function () { 
	//alert("function");
	if(<%=isExist%>) {
		//alert(1);
		$.ajax({
		      url: "actionServlet?action=producemExceptionChart",
		      datatype: "json",
		      type: "post",
		      data: {},
		      success: function (data, status, xhr) {
		    	  //alert(data);
		    	  totalWeight = eval("(" + data + ")").totalWeight;
		    	  //alert("totalWeight=" + totalWeight);
		    	   var colors = Highcharts.getOptions().colors;
		    	  $.each(eval("(" + data + ")").list_p, function(index, item) {
		    		  p_categories.push(item.produceM_Name);
		    		  var JSONObject = {
		    			        "y" : item.weight,
		    			        "color" : colors[index]
		    			    };
		    		  dataJson_p.push(JSONObject);
		    	  });
		    	  //alert("p_categories:" + p_categories);
		    	  //alert("dataJson:" + dataJson);
		    	  
		    	 
		          categories = p_categories,
		          name = '工序名称',
		          data = dataJson_p;
			      function setChart(name, categories, data, color) {
				      chart.xAxis[0].setCategories(categories, false);
				      chart.series[0].remove(false);
				      chart.addSeries({
				          name: name,
				          data: data,
				          color: color || 'white'
				      }, false);
				      chart.redraw();
			      }
			      var chart = $('#container').highcharts({
			          chart: {
			              type: 'column'
			          },
			          title: {
			              text: '异常工序详情'
			          },
			          subtitle: {
			              text: '来源:上上RFID生产管理系统'
			          },
			          xAxis: {
			              categories: categories
			          },
			          yAxis: {
			              title: {
			                  text: '重量(kg)'
			              }
			          },
			          plotOptions: {
			              column: {
			                  cursor: 'pointer',
			                  point: {
			                      events: {
			                    	  <%-- click: function() {
			                              var drilldown = this.drilldown;
			                              if (drilldown) { // drill down
			                                  setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
			                              } else { // restore
			                                  setChart(name, categories, data);
			                              }
			                          } --%>
			                      }
			                  },
			                  dataLabels: {
			                      enabled: true,
			                      color: colors[0],
			                      style: {
			                          fontWeight: 'bold'
			                      },
			                      formatter: function() {
			                          return this.y +'kg';
			                      }
			                  }
			              }
			          },
			          tooltip: {
			              formatter: function() {
			                  var point = this.point,
			                  	  rate = (this.y*100/totalWeight).toFixed(2),
			                      s = this.x +':<b>'+ this.y +'kg （' + rate + '%）查看详情</b><br/>';
			                  return s;
			              }
			          },
			          series: [{
			              name: name,
			              data: data,
			              color: 'white'
			          }],
			          exporting: {
			              enabled: false
			          }
			      })
			      .highcharts();
		      } 
		});
	}
});  

// 第二个
$(function () { 
	if(<%=isExist%>) {
		//alert(1);
		$.ajax({
		      url: "actionServlet?action=producemExceptionChart",
		      datatype: "json",
		      type: "post",
		      data: {},
		      success: function (data, status, xhr) {
		    	  //alert(data);
		    	  totalWeight = eval("(" + data + ")").totalWeight;
		    	  //alert("totalWeight=" + totalWeight);
		    	  var colors = Highcharts.getOptions().colors;
		    	  $.each(eval("(" + data + ")").list_t, function(index, item) {
		    		  t_categories.push(item.d_Type);
		    		  var JSONObject = {
		    			        "y" : item.weight,
		    			        "color" : colors[index]
		    			    };
		    		  dataJson_t.push(JSONObject);
		    	  });
		    	  //alert("p_categories:" + p_categories);
		    	  //alert("dataJson:" + dataJson);
		    	  
			      categories = t_categories,
			      name = '异常类型',
			      data = dataJson_t;
			    function setChart(name, categories, data, color) {
				    chart.xAxis[0].setCategories(categories, false);
				    chart.series[0].remove(false);
				    chart.addSeries({
				        name: name,
				        data: data,
				        color: color || 'white'
				    }, false);
				    chart.redraw();
			    }
			    var chart = $('#container-two').highcharts({
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: '异常类型详情'
			        },
			        subtitle: {
			            text: '来源:上上RFID生产管理系统'
			        },
			        xAxis: {
			            categories: categories
			        },
			        yAxis: {
			            title: {
			                text: '重量(kg)'
			            }
			        },
			        plotOptions: {
			            column: {
			                cursor: 'pointer',
			                point: {
			                    events: {
			                        <%-- click: function() {
			                            var drilldown = this.drilldown;
			                            if (drilldown) { // drill down
			                                setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
			                            } else { // restore
			                                setChart(name, categories, data);
			                            }
			                        } --%>
			                    }
			                },
			                dataLabels: {
			                    enabled: true,
			                    color: colors[0],
			                    style: {
			                        fontWeight: 'bold'
			                    },
			                    formatter: function() {
			                        return this.y +'kg';
			                    }
			                }
			            }
			        },
			        tooltip: {
			            formatter: function() {
			                var point = this.point,
			                	rate = (this.y*100/totalWeight).toFixed(2),
		                        s = this.x +':<b>'+ this.y +'kg （' + rate + '%）查看详情</b><br/>';
			                return s;
			            }
			        },
			        series: [{
			            name: name,
			            data: data,
			            color: 'white'
			        }],
			        exporting: {
			            enabled: false
			        }
			    })
			    .highcharts();
		      } 
		});
	}
});    
</script>
<div class="panel panel-success">
    <div class="panel-heading" style="background-color:#034690;">
        <h3 class="panel-title" style="color:#fff;">按工序统计</h3>
    </div>
    <div class="panel-body" style="padding:0px;">
        <div>
           <div id="container" style="min-width:100%;height:400px"></div>
        </div> 
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-heading" style="background-color:#034690;">
        <h3 class="panel-title" style="color:#fff;">按异常类型统计</h3>
    </div>
    <div class="panel-body" style="padding:0px;">
        <div>
           <div id="container-two" style="min-width:100%;height:400px"></div>
        </div> 
    </div>
</div>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>