
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
<meta charset="UTF-8">
<title>金指投-登录界面</title>
<link href=".//css/Login.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href=".//css/base.css">
</head>
<body>
	<div class="page">

		<!--顶部导航-->
		<div class="header">
			<div class="content">
				<div class="h-logo"></div>
				<ul class="h-index">
					<li id="id_main">创投资讯</li>
					<li id="id_report">观点报告</li>
					<li id="id_project">项目展示</li>
					<div class=""></div>
				</ul>
				<div class="h-right">
					<div class="search">搜索</div>
					<div class="down">下载APP</div>
					<div class="text">
						<a href="#">登录</a> | <a href="#">注册</a>
					</div>

				</div>
			</div>
		</div>
		<div id="middle" style="background:url(.//img/login_bg.png)">
			<div class="panel">
				<img src=".//img/logo.png">
				<!--面板的中间内容-->
				<div class="panel-content">
					<!--账号输入框-->
					<div class="user-pwd">
						<img src=".//img/user.png"> <input placeholder="手机号"
							name="user">
					</div>
					<!--密码输入框-->
					<div class="user-pwd">
						<img src=".//img/pwd.png"> <input placeholder="请输入密码"
							type="password" name="pwd">
					</div>

					<!--默认的设置-->
					<div class="setting">
						<a href="#" class="pull-right">忘记密码?</a>
					</div>

					<!--登录按钮-->
					<button class="login-btn">立即进入</button>

					<!--免费注册,微信登录-->
					<div class="reg">
						<!--还没账号?&nbsp;&nbsp;<a href="#">立即注册</a>-->
						<button class="free-reg" onclick="register()">免费注册</button>
						<button class="weixin-login">微信登录</button>
					</div>
				</div>
			</div>
		</div>

		<!--//底部-->
		<div class="footer">
			<div class="content">
				<div class="top">
					<div class="left m-left-0">关于我们</div>
					<div class="left m-left">视频介绍</div>
					<div class="left m-left">联系我们</div>
					<div class="left m-left">意见反馈</div>
					<div class="left m-left">服务协议</div>
					<div class="left m-left">隐私政策</div>
					<div class="scan-footer m-left-0">
						<div class="img"></div>
						<div class="text">APP下载</div>
					</div>
					<div class="scan-footer m-left">
						<div class="img"></div>
						<div class="text">微信公众号</div>
					</div>
				</div>
			</div>
			<div class="bot">京ICP备15062798号 © 2015-2016 金指投 版权所有</div>
		</div>
	</div>
	<script type="text/javascript" src=".//js/jquery1.8.min.js"
		charset="utf-8"></script>
	<script type="text/javascript" src=".//js/Login.js" charset="utf-8"></script>
</body>
</html>