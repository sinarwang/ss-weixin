<%@ page language="java" import="java.util.*, org.apache.log4j.Logger, com.ss.servlet.*, com.ss.service.*, com.ss.util.*, com.ss.util.accessToken.*" pageEncoding="UTF-8"%>
<%@ include file="CheckOpenIdAndWeiXinJS.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" >
		<title>生产工单相关查询</title>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/login.css">
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
		
			//定义工序select选择菜单的数组
			var produceMSelect = new Array();
			
			var allData = null;
			
			var wftos = new Array();
			
			var totalWeight;
			var totalQuantity;
			
			$(document).ready(function(){ 
				if(<%=isExist%>) {
					//alert(1);
					$.ajax({
					      url: "actionServlet?action=producemWorkorder",
					      datatype: "json",
					      type: "post",
					      data: {},
					      success: function (data, status, xhr) {
					    	  //alert(data);
					    	  allData = data;
					    	  totalRecords = eval("(" + data + ")").list.length;
					    	  totalWeight = eval("(" + data + ")").totalWeight;
					    	  totalWeight = parseFloat((totalWeight/1000).toFixed(2));
					    	  totalQuantity = eval("(" + data + ")").totalQuantity;
						      //通过计算，得到分页应该需要分几页，其中不满一页的数据按一页计算 
						  	  if(totalRecords % pageSize != 0) {
								totalPages = Math.floor(totalRecords / pageSize) + 1;
						  	  } else {
						  		totalPages = totalRecords / pageSize;
						  	  }
					    	  $.each(eval("(" + data + ")").list, function(index, item) {
					    		  	  //初始化select数组
					    		 	  if(contains(item.produceM_Name, produceMSelect) == 0) {
					    		 		 produceMSelect.push(item.produceM_Name);
					    		 	  }
					    		  	  
					    		 	  wftos.push(item);
					    		  
					    			  if(index < pageSize) {
					    				  addInfo(index, item);
					    			  } else if(index > pageSize) {
					    				  return;
					    			  }
					          });
				    		  $("#loading").remove();
				    		  var dataStaStr = "<tr><td colspan=\"2\" style=\"background:#64acdd;font-size:16px;color:#fff\">当天生产工单数据统计</td></tr>" +
					  		  					"<tr>" + 
										            "<td style=\"width:50%\">总重量</td>" + 
										            "<td style=\"width:50%\">" + totalWeight + " t</td>" + 
										        "</tr>" + 
										        "<tr>" + 
										            "<td style=\"width:50%\">总支数</td>";
					          if(totalQuantity == null) {
							  		dataStaStr = dataStaStr + "<td style=\"width:50%\">0 支</td>" + 
							        							"</tr>";
							  } else {
							    	dataStaStr = dataStaStr + "<td style=\"width:50%\">" + totalQuantity + " 支</td>" + 
																"</tr>";
							  }
  		  					  $("#dataSta").append(dataStaStr);
				    		  
				    		  //循环select数组，建立select选项
						      for(var i=0;i<produceMSelect.length;i++){
						    	  $("#produceM").append("<option value=\"" + produceMSelect[i] + "\">" + produceMSelect[i] + "</option>");
						      } 
					      } 
					     
					});
				}
			}); 
		
			var pageSize = 10;
			var pageNo = 1;//当前的页码
			var totalRecords = 0;
			var totalPages = 0;
			
			//首页
			function topPage() {
				 $("#info").empty();
				 pageNo = 1;
				 circleWftoS();
			}
			//上一页
			function previousPage() {
				 $("#info").empty();
				 pageNo--;
				 if(pageNo <= 0) {
					 pageNo = 1;
				 }
				 circleWftoS();
			}	
			//下一页
			function nextPage() {
				 $("#info").empty();
				 pageNo++;
				 if(pageNo >= totalPages) {
					 pageNo = totalPages;
				 }
				 circleWftoS();
			}
			//尾页
			function bottomPage() {
				$("#info").empty();
				 pageNo = totalPages;
				 circleWftoS();
			}         
			
			function circleWftoS() {
				 wx.hideOptionMenu();
				 $.each(wftos, function(index, item) {
					 if(index >= ((pageNo-1)*pageSize) && index < (pageNo*pageSize)) {
						 addInfo(index, item);
					 } else if(index > (pageNo*pageSize)) {
						 return;
					 }
		          });
			}
			
			function addInfo(index, item) {
				var str = "";
				if(item.state == 1) {
					str = str + "<tr style=\"font-size:12px;color:#006837\">";
				} else {
					str = str + "<tr style=\"font-size:12px;color:#c1272d\">";
				}
				str = str + "<td>" + (index+1) + "</td>" +
						"<td>" + item.cardNo + "</td>" + 
	                    "<td>" + item.contractNo + "</td>" + 
	                    "<td>" + item.produceM_Name + "</td>";
	                    
                if(item.produceM_Number == 0) {
   		        	str = str + "<td></td>";
   		        } else {
   		        	str = str + "<td>" + item.produceM_Number + "</td>";
   		        }
   		        if(item.produceM_Weight == 0) {
   		        	str = str + "<td></td>";
   		        } else {
   		        	str = str + "<td>" + item.produceM_Weight + "</td>";
   		        }
   		        str = str + "<td>" + item.d_CreateDate + "</td>" +
               				"</tr>";
				$("#info").append(str);
			}
			
			function contains(str, arr) {
				for(var i=0;i<arr.length;i++) {
					if(str == arr[i]) {
						return 1;
					}
				}
				return 0;
			}
			
			function produceManager() {
				wftos = [];
				totalWeight = 0;
				totalQuantity = 0;
				totalRecords = 0;
				$.each(eval("(" + allData + ")").list, function(index, item) {
					if(selectForm.produceM.value == "所有") {
						wftos.push(item);
						totalWeight = totalWeight + item.produceM_Weight;
						totalQuantity = totalQuantity + item.produceM_Number;
					} else if(selectForm.produceM.value == item.produceM_Name) {
						wftos.push(item);
						totalWeight = totalWeight + item.produceM_Weight;
						totalQuantity = totalQuantity + item.produceM_Number;
					}
		        });
				totalRecords = wftos.length;
				totalWeight = parseFloat((totalWeight/1000).toFixed(2));
				//通过计算，得到分页应该需要分几页，其中不满一页的数据按一页计算 
			  	if(totalRecords % pageSize != 0) {
					totalPages = Math.floor(totalRecords / pageSize) + 1;
			  	} else {
			  		totalPages = totalRecords / pageSize;
			  	}
			  	$("#info").empty();
			  	$("#dataSta").empty();
			  	$.each(wftos, function(index, item) {
	    			  if(index < pageSize) {
	    				  addInfo(index, item);
	    			  } else if(index > pageSize) {
	    				  return;
	    			  }
	          	}); 
			  	var dataStaStr = "<tr><td colspan=\"2\" style=\"background:#64acdd;font-size:16px;color:#fff\">当天生产台账数据统计</td></tr>" +
					    	    "<tr>" + 
						            "<td style=\"width:50%\">工序</td>" + 
						            "<td style=\"width:50%\">" + selectForm.produceM.value + "</td>" + 
				        	    "</tr>" + 					
					    	    "<tr>" + 
						            "<td style=\"width:50%\">总重量</td>" + 
						            "<td style=\"width:50%\">" + totalWeight + " t</td>" + 
						        "</tr>" + 
						        "<tr>" + 
						            "<td style=\"width:50%\">总支数</td>" + 
						            "<td style=\"width:50%\"> " + totalQuantity + " 支</td>" + 
						        "</tr>";
				$("#dataSta").append(dataStaStr);
			}
			
	</script>
	</head>
	<body>
		<div class="panel panel-success" style="border:0px;box-shadow:0px;margin-top:-2px">
			<table class="table" style="text-align:center;color:#000">
				<form action="" id="selectForm" name="selectForm">
			        <tr>
			            <td style="font-family:微软雅黑;height:40px;line-height:40px;font-size:15px">工序：
			                <select name="produceM" id="produceM" onchange="produceManager()" style="width:100px;border-radius:3px;outline:none;height:30px;border:1px solid #000;font-size:12px;padding:3px;">
			                	<option value="所有">所有</option>
			                <select>
			            </td>
			            <td style="font-family:微软雅黑;font-size:15px;text-align:left">
			                <div style="width:100%;height:25px;float:left;font-size:13px;padding-top:5px;padding-left:20px;color:#c1272d">
			                    <div style="width:20px;height:15px;background:#c1272d;float:left;margin-right:5px;"></div>未完成
			                </div>
			                <div style="width:100%;height:25px;font-size:13px;float:left;padding-top:5px;padding-left:20px;color:#006837">
			                    <div style="width:20px;height:15px;background:#006837;float:left;margin-right:5px;"></div>已完成
			                </div>
			            </td>
			        </tr>
				</form>
			</table>
		    <div class="panel-body" style="padding:0px;">
		        <div class="table-responsive" >
		            <table class="table" style="text-align:center;">
		            <thead>
		                <tr>
		                    <th style="text-align:center">序号</th>
		                    <th style="text-align:center">框号</th>
		                    <th style="text-align:center">合同号</th>
		                    <th style="text-align:center">工序名称</th>
		                    <th style="text-align:center">工序支数</th>
		                    <th style="text-align:center">工序重量</th>
		                    <th style="text-align:center">创建时间</th>
		                </tr>
		            </thead>
		            <tbody id="info">
		            	<tr id="loading">
		            		<td colspan="6">
		            			<font>数据正在加载中，请稍候</font>
			            		<img src="images/loading.gif" width="19" height="22">
		            		</td>
		            	</tr>
		            </tbody>
		            </table> 
		        </div> 
		        <div style="width:100%">
		        <div style="margin:0 auto;width:250px;"> 
		        <ul class="pagination" style="margin-top:5px;">
		                <li><a href="#" onclick="topPage()">首页</a></li>
		                <li><a href="#" onclick="previousPage()">前一页</a></li>
		                <li><a href="#" onclick="nextPage()">后一页</a></li>
		                <li><a href="#" onclick="bottomPage()">尾页</a></li>
		        </ul>
		        </div>
		        </div> 
		    </div>
		    <div class="table" style="width:95%;margin-left:2.5%">
			    <table id="dataSta" class="table table-bordered" style="text-align:center;background:#e9f8ff;color:#000;font-size:14px;">
			    </table>
			</div>
		</div>
	</body>
</html>