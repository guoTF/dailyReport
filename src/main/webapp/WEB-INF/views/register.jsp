<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
  <%@include file="/commons/global.jsp" %>
 <%@ page import="java.util.Date"%>
<!DOCTYPE HTML>
<html>
<head>
	<%@include file="/commons/basejs.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
	<link rel="stylesheet" href="${staticPath }/static/daily/css/login.css">
</head>
<body>
	<div class="container">
	        <div class="row">
	            <div class="col-md-6 col-md-offset-4">
	                <div class="login-panel panel panel-default">
	                    <div class="panel-heading">
	                        <h3 class="panel-title">注册</h3>
	                    </div>
	                    <div class="panel-body">
	                        <form  action="" id="serializeResiger" onsubmit="return false;">
	                            <fieldset>
	                                <div class="form-group login-validate">
	                                	 <label for="userNameResiger" class="">用户名:</label>
	                                    <input class="form-control required"  placeholder="用户名" name="userName" type="text" autofocus autocomplete="off" id="userNameResiger" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5a-zA-Z0-9\w]/g,'');">
	                                </div>
	                                 
	                                 <div class="form-group login-validate">
	                                 <label for="passwordResiger" class="">登录密码:</label>
	                                    <input class="form-control required"  placeholder="登录密码" name="password" type="password"  autocomplete="off" id="passwordResiger">
	                                </div>
	                                 <div class="form-group login-validate">
	                                 <label for="password2" class="">确认密码:</label>
	                                    <input class="form-control required"  placeholder="确认登录密码" name="password2" type="password"  autocomplete="off" id="password2">
	                                </div>
	                               <div class="form-group login-validate">
	                                  <label for="phoneNum" class="codeValidate">手机号码:</label>
	                                    <input class="form-control required"  placeholder="手机号码"  type="text"  name="phone" autocomplete="off" id="phoneNum">
	                                    <!-- <button id="sendMobile" class="btn btn-info btn-sm sendMobile" style="margin-left:13px;">获取手机验证码</button> -->
	                                </div>
	                               <!--   <div class="form-group" style="display:flex;">
	                                 	<label for="mobileCode" class="" >手机验证码:</label>
	                                    <input class="form-control required" placeholder="手机验证码"  name="identifyingCode" type="number"   id="mobileCode">
	                                     
	                                </div> -->
	                                 <div class="form-group login-validate">
	                                  <label for="mail" class="">有效邮箱:</label>
	                                    <input class="form-control required"  placeholder="有效邮箱"  type="text"  autocomplete="off" id="mail">
	                                </div>
	                                <div class="form-group login-validate">
	                                     <label for="department" class="areaValidate" style="">所在部门:</label>
	                                    <select class="form-control" id="departmentId" name="department">
	                                    	<option>请选择</option>
	                                    </select>
	                                   
	                                    <label for="post" class="posValidate">职位:&nbsp;&nbsp;</label>
	                                   <!--  <input class="form-control required" placeholder="职位"  name="post" type="text" autofocus autocomplete="off" id="post" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5a-zA-Z0-9\w]/g,'');"> -->
	                                   <select class="form-control" id="post" name="post">
	                                    	<option>请选择</option>
	                                    </select>
	                                </div>
	                                <div class="form-group login-validate">
	                                <label for="identifyingCode"  style="margin-right:19px;">验证码:</label>
	                                    <input class="form-control required" placeholder="请输入验证码" 
	                                    name="identifyingCode" type="number"  id="identifyingCode">
	                                    <img src="${path}/common/getImgCode" alt="" id="validateImg">
	                                </div>
	                                <button  id="submitResiger" class="btn btn-lg btn-success btn-block">注册</button>
	                                <!-- <div class="register">
				                    	还没有账户？<a href="#">立即注册</a>
				                    </div> -->
	                            </fieldset>
	                        </form>
	                    </div>
	                    
	                </div>
	            </div>
	        </div>
	    </div>
	
	    
	 <script type="text/javascript" src="${staticPath }/static/daily/login.js?timestamp=<%=new Date().getTime()%>" charset="utf-8"></script>    
</body>
</html>
