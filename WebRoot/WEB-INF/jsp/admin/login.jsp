<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>【金指投投融资】--邀请好友</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<script type="text/javascript" src="../images/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="../images/jquery.SuperSlide.2.1.1.js"></script>
<style>
</style>
</head>
<body class="d-body" style="overflow-x: hidden;background-color:white">
	<!-- header -->
	<div class="header">
		<img class="header-img" alt="金指投" src="../admin/images/king.png">
	</div>
	<!-- login -->
	<div class="content">
		<div class="login">
			<form action="/jinzht/admin/loginAction.action">
				<!-- login-area -->
				<div class="login-text" style="margin-top:-10%">
					<div class="icon">
						<img class="icon-img" alt="金指投" src="../admin/images/person.png">
					</div>
					<div class="textfield">
						<input type="text" value="请输入登录账号 " />
					</div>
				</div>

				<!-- password-area -->
				<div class="login-text">
					<div class="icon">
						<img class="icon-img" alt="金指投" src="../admin/images/password.png">
					</div>
					<div class="textfield">
						<input type="text" value="请输入登录密码" />
					</div>
				</div>

				<!-- login-button -->
				<div class="button">
					<input type="submit" value="登录">
				</div>
			</form>
		</div>
	</div>
</body>
</html>