<%@ page language="java" import="java.text.*, javax.servlet.*, javax.servlet.http.*, java.util.*, org.apache.log4j.Logger, com.ss.servlet.*, com.ss.entity.*, com.ss.service.*, com.ss.util.*, com.ss.util.accessToken.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Logger logger = LoggerUtil.getInstance();
	User u = (User)session.getAttribute("user");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String createDate = "";
	boolean isExist = false;
	if(u != null && u.getUserId().equals("1001") && u.getPassword().equals("admin")) {
		isExist = true;
		createDate = sdf.format(u.getCreateDate());
	} else {
		response.sendRedirect("login.html");
	}
%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>H+ 后台主题UI框架 - 基础表格</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css" rel="stylesheet"><base target="_blank">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>基本</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="#">选项1</a>
                                </li>
                                <li><a href="#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                         <div class="table-responsive">
                            <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>微信号</th>
                                    <th>工号</th>
                                    <th>用户名</th>
                                    <th>创建日期</th>
                                    <th>审核</th>
                                    <th>禁用</th>
                                </tr>
                            </thead>
                            <tbody id="userList">
                            </tbody>
                        </table>
                    </div>
                    </div>
                </div>
            </div>
            <!-- <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>斑马纹效果</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_basic.html#">选项1</a>
                                </li>
                                <li><a href="table_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>数据</th>
                                    <th>用户</th>
                                    <th>值</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td><span class="line">5,3,2,-1,-3,-2,2,3,5,2</span>
                                    </td>
                                    <td>张三</td>
                                    <td class="text-navy"> <i class="fa fa-level-up"></i> 40%</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td><span class="line">5,3,9,6,5,9,7,3,5,2</span>
                                    </td>
                                    <td>李四</td>
                                    <td class="text-warning"> <i class="fa fa-level-down"></i> -20%</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td><span class="line">1,6,3,9,5,9,5,3,9,6,4</span>
                                    </td>
                                    <td>王麻子</td>
                                    <td class="text-navy"> <i class="fa fa-level-up"></i> 26%</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>边框</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_basic.html#">选项1</a>
                                </li>
                                <li><a href="table_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th>年龄</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>张三</td>
                                    <td>男</td>
                                    <td>23</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>李四</td>
                                    <td>男</td>
                                    <td>27</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>王麻子</td>
                                    <td>男</td>
                                    <td>65</td>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>鼠标经过</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_basic.html#">选项1</a>
                                </li>
                                <li><a href="table_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>数据</th>
                                    <th>用户</th>
                                    <th>值</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td><span class="line">5,3,2,-1,-3,-2,2,3,5,2</span>
                                    </td>
                                    <td>张三</td>
                                    <td class="text-navy"> <i class="fa fa-level-up"></i> 40%</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td><span class="line">5,3,9,6,5,9,7,3,5,2</span>
                                    </td>
                                    <td>李四</td>
                                    <td class="text-warning"> <i class="fa fa-level-down"></i> -20%</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td><span class="line">1,6,3,9,5,9,5,3,9,6,4</span>
                                    </td>
                                    <td>王麻子</td>
                                    <td class="text-navy"> <i class="fa fa-level-up"></i> 26%</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>自定义响应式表格</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_basic.html#">选项1</a>
                                </li>
                                <li><a href="table_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-5 m-b-xs">
                                <select class="input-sm form-control input-s-sm inline">
                                    <option value="0">请选择</option>
                                    <option value="1">选项1</option>
                                    <option value="2">选项2</option>
                                    <option value="3">选项3</option>
                                </select>
                            </div>
                            <div class="col-sm-4 m-b-xs">
                                <div data-toggle="buttons" class="btn-group">
                                    <label class="btn btn-sm btn-white">
                                        <input type="radio" id="option1" name="options">天</label>
                                    <label class="btn btn-sm btn-white active">
                                        <input type="radio" id="option2" name="options">周</label>
                                    <label class="btn btn-sm btn-white">
                                        <input type="radio" id="option3" name="options">月</label>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text" placeholder="请输入关键词" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>

                                        <th></th>
                                        <th>项目</th>
                                        <th>进度</th>
                                        <th>任务</th>
                                        <th>日期</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <input type="checkbox" checked class="i-checks" name="input[]">
                                        </td>
                                        <td>米莫说｜MiMO Show</td>
                                        <td><span class="pie">0.52/1.561</span>
                                        </td>
                                        <td>20%</td>
                                        <td>2014.11.11</td>
                                        <td><a href="table_basic.html#"><i class="fa fa-check text-navy"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" class="i-checks" name="input[]">
                                        </td>
                                        <td>商家与购物用户的交互试衣应用</td>
                                        <td><span class="pie">6,9</span>
                                        </td>
                                        <td>40%</td>
                                        <td>2014.11.11</td>
                                        <td><a href="table_basic.html#"><i class="fa fa-check text-navy"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" class="i-checks" name="input[]">
                                        </td>
                                        <td>天狼---智能硬件项目</td>
                                        <td><span class="pie">3,1</span>
                                        </td>
                                        <td>75%</td>
                                        <td>2014.11.11</td>
                                        <td><a href="table_basic.html#"><i class="fa fa-check text-navy"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" class="i-checks" name="input[]">
                                        </td>
                                        <td>线下超市+线上商城+物流配送互联系统</td>
                                        <td><span class="pie">4,9</span>
                                        </td>
                                        <td>18%</td>
                                        <td>2014.11.11</td>
                                        <td><a href="table_basic.html#"><i class="fa fa-check text-navy"></i></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>-->

        </div>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/peity/jquery.peity.min.js"></script>
    <script src="js/content.min.js"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script src="js/demo/peity-demo.min.js"></script>
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
		
		function changeAudit(object) {
			var id = object.name.substring(5);
			
			$.ajax({
			      url: "userServlet?action=audit&id=" + id + "&audit=" + object.value,
			      datatype: "json",
			      type: "post",
			      data: {},
			      success: function (data, status, xhr) {
			      } 
			});
		}
		function changeDisable(object) {
			var id = object.name.substring(7);
			
			$.ajax({
			      url: "userServlet?action=disable&id=" + id + "&disable=" + object.value,
			      datatype: "json",
			      type: "post",
			      data: {},
			      success: function (data, status, xhr) {
			      } 
			});
		}
		
	    $(document).ready(function(){ 
	    	if(<%=isExist%>) {
		    	$.ajax({
				      url: "actionServlet?action=userList",
				      datatype: "json",
				      type: "post",
				      data: {},
				      success: function (data, status, xhr) {
				    	  $.each(eval("(" + data + ")").list, function(index, item) {
				      		  var str = "<tr>" + 
				  			            "<td>" + item.id +"</td>" + 
				  			            "<td>" + item.openId + "</td>" + 
				  			            "<td>" + item.userId + "</td>" + 
				  			            "<td>" + item.userName + "</td>" + 
				  			            "<td>" + item.createDate + "</td>";
				  			  if(item.audit == 1) {
				  			  	  str = str + "<td><input type=\"radio\" checked=\"checked\" name=\"audit" + item.id + "\" value=\"audit\" onclick=\"changeAudit(this)\"/>是" + 
				  			  	  			"<input type=\"radio\"  name=\"audit" + item.id + "\" value=\"unaudit\" onclick=\"changeAudit(this)\"/>否</td>";
				  			  } else if(item.audit == 0) {
				  			  	  str = str + "<td><input type=\"radio\" name=\"audit" + item.id + "\" value=\"audit\" onclick=\"changeAudit(this)\"/>是" + 
				  			  	  			"<input type=\"radio\"  checked=\"checked\" name=\"audit" + item.id + "\" value=\"unaudit\" onclick=\"changeAudit(this)\"/>否</td>";
				  			  }
				  			  if(item.disable == 1) {
				  			  	  str = str + "<td><input type=\"radio\" checked=\"checked\" name=\"disable" + item.id + "\" value=\"disable\" onclick=\"changeDisable(this)\"/>是" +
									"<input type=\"radio\" name=\"disable" + item.id + "\" value=\"able\" onclick=\"changeDisable(this)\"/>否</td>";
				  			  } else if(item.disable == 0) {
				  			  	   str = str + "<td><input type=\"radio\" name=\"disable" + item.id + "\" value=\"disable\" onclick=\"changeDisable(this)\"/>是" +
									"<input type=\"radio\" checked=\"checked\" name=\"disable" + item.id + "\" value=\"able\" onclick=\"changeDisable(this)\"/>否</td>";
				  			  }
                              str = str + "</tr>";   
				      		  $("#userList").append(str);
				      	  });
				      } 
				});
	    	}
		});
	</script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>