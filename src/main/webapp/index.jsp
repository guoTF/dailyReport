<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
  <%@include file="./commons/global.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@include file="./commons/basejs.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
	 <link rel="stylesheet" href="${staticPath }/static/daily/css/login.css">
</head>
<body>
	<div class="container loginbg">
	        <div class="row">
	            <div class="col-md-5 col-md-offset-4">
	                <div class="login-panel panel panel-default">
	                    <div class="panel-heading">
	                        <h3 class="panel-title login-title">日志管理系统</h3>
	                    </div>
	                    <div class="panel-body">
	                        <form role="form" id="serializeForm">
	                            <fieldset>
	                                <div class="form-group">
	                                    <input class="form-control" placeholder="用户名" name="userName" type="text" autofocus autocomplete="off" id="userName" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5a-zA-Z0-9\w]/g,'');">
	                                </div>
	                                <div class="form-group">
	                                    <input class="form-control" placeholder="登录密码" name="password" type="password" value="" autocomplete="off" id="password">
	                                </div>
	                                <div class="form-group login-validate">
	                                    <input class="form-control" placeholder="验证码" 
	                                    name="identifyingCode" type="number" value="" id="identifyingCode">
	                                    <img src="${path}/common/getImgCode" alt="" id="validateImg">
	                                </div>
	                                <div class="checkbox">
	                                    <label>
	                                        <input name="remember" type="checkbox" value="Remember Me">记住密码
	                                    </label>
	                                </div>
	                                <a href="javascript:void(0);" id="submitForm" class="btn btn-lg btn-success btn-block">Login</a>
	                                <div class="register">
				                    	还没有账户？<a href="${path}/login/toRegister">立即注册</a>
				                    </div>
	                            </fieldset>
	                        </form>
	                    </div>
	                    
	                </div>
	            </div>
	        </div>
	    </div>
	
	    
	 <script type="text/javascript" src="${staticPath }/static/daily/login.js" charset="utf-8"></script>    
</body>
</html>
