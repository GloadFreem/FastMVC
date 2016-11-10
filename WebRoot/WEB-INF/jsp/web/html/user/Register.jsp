
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
<title>金指投-注册界面</title>
<link href=".//css/Register.css" style="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href=".//css/base.css">
</head>
<body>
	<div class="page">


		<div class="top-info">
			<img src=".//img/jinzht-logo.png">
			<div class="top-txt">
				<p>已有账号&nbsp;&nbsp;</p>
				<a href="Login.html">立即登录</a>
			</div>

		</div>

		<div class="panel">
			<p class="panel-title">欢迎注册</p>
			<hr>
			<!--注册进度-->
			<div class="rate-of-progress">
				<!--手机号注册-->
				<div class="phone-num-reg">
					<p>手机号注册</p>
					<img src=".//img/choosing.png" id="phone-num-reg">
				</div>
				<!--设置密码-->
				<div class="set-pwd">
					<p>设置密码</p>
					<img src=".//img/choose.png" id="set-pwd">
				</div>
				<!--选择身份-->
				<div class="choose-idtentity">
					<p>选择身份</p>
					<img src=".//img/choose.png" id="choose-idtentity">
				</div>
			</div>

			<div class="btn" id="step1">
				<!--手机号码输入框-->
				<div class="user-phone-number">
					<img src=".//img/shouji.png"> <input placeholder="请输入手机号码"
						name="phone-number">
				</div>

				<!--协议-->
				<div class="protocol">
					<img src=".//img/choosed.png">
					<p class="agree">已阅读并同意</p>
					<span>&lt;&lt;金指投用户协议&gt;&gt;</span>
				</div>
				<!--下一步-->
				<button class="next-step" onclick="next()">下一步</button>
			</div>

			<!--第二步-->
			<div class="btn" id="step2">
				<div class="captcha">
					<img src=".//img/shouji.png"> <input placeholder="请输入验证码"
						name="phone-number">
					<button class="obtain-captcha" onclick="obtainCaptcha()">获取验证码</button>
				</div>
				<div class="pwd">
					<img src=".//img/suo.png"> <input placeholder="请设置密码">
				</div>

				<div class="progress">
					<div class="back-progress-bar">
						<div class="progress-bar"></div>
						<!--<span class="grade">好</span>-->
					</div>
				</div>
				<div class="pwd">
					<img src=".//img/suo.png"> <input placeholder="请确认密码">
				</div>

				<button class="next" onclick="chooseId()">下一步</button>
			</div>

			<!--第三步-->
			<div class="card" id="step3">
				<div class="choose-id" id="id1">
					<img src=".//img/blue-circle.png"
						style="width: 20px; height: 20px; margin-top: 20px">
					<p style="margin-top: 9px; font-size: 26px; color: #3b5d8f">标题</p>
					<p style="margin: 19px 16px; font-size: 16px; line-height: 26px">这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,
						这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,这里是内容</p>
				</div>
				<div class="choose-id" id="id2" style="margin-left: 10px">
					<img src=".//img/blue-circle.png"
						style="width: 20px; height: 20px; margin-top: 20px">
					<p style="margin-top: 9px; font-size: 26px; color: #3b5d8f">标题</p>
					<p style="margin: 19px 16px; font-size: 16px; line-height: 26px">这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,
						这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,这里是内容</p>
				</div>
				<div class="choose-id" id="id3" style="margin-left: 10px">
					<img src=".//img/blue-circle.png"
						style="width: 20px; height: 20px; margin-top: 20px">
					<p style="margin-top: 9px; font-size: 26px; color: #3b5d8f">标题</p>
					<p style="margin: 19px 16px; font-size: 16px; line-height: 26px">这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,
						这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,这里是内容</p>
				</div>
				<div class="choose-id" id="id4" style="margin-left: 10px">
					<img src=".//img/blue-circle.png"
						style="width: 20px; height: 20px; margin-top: 20px">
					<p style="margin-top: 9px; font-size: 26px; color: #3b5d8f">标题</p>
					<p style="margin: 19px 16px; font-size: 16px; line-height: 26px">这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,
						这里是内容,这里是内容,这里是内容,这里是内容,这里是内容,这里是内容</p>
				</div>
			</div>

			<button class="submit" onclick="submit()">完成</button>

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

</body>
<script type="text/javascript" src=".//js/jquery1.8.min.js"
	charset="utf-8"></script>
<script type="text/javascript" src=".//js/user/Register.js"
	charset="utf-8"></script>
</html>