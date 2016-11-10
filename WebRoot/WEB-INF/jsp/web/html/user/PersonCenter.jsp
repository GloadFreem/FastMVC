
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>Title</title>
<link rel="stylesheet" type="text/css" href=".//css/base.css">
<link href=".//css/PersonCenter.css" rel="stylesheet">

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
		<!--面板-->
		<div id="panel">
			<!--左边的类型-->
			<div id="type">
				<div id="mine-info" onclick="mineInfo()">我的资料</div>
				<!--<div id="zichan-account">资产账户</div>-->
				<div id="project-center" onclick="projectCenter()">项目中心</div>
				<!--<div id="mine-guanzhu">我的关注</div>-->
				<!--<div id="mine-money">我的金条</div>-->
				<!--<div id="mine-activity">我的活动</div>-->
				<div id="account-setting" onclick="accountSetting()">账户设置</div>
			</div>

			<div id="mine-info-content" style="display:block">
				<!--右边的内容-->
				<div class="content-card">
					<button class="tab" id="basic-information-tab"
						style="margin-left:50px;border-bottom:4px solid #355078;"
						onclick="basicInformation()">基本资料</button>
					<div id="tab"></div>
					<button class="tab" id="authentication-information-tab"
						onclick="authenticationInformation()">认证资料</button>
					<hr style="margin:0 110px 44px 50px;">

					<!--基本资料内容-->
					<div id="basic-information" style="display:block;">
						<!--<img src="img/account-setting.png" alt="头像">-->
						<!--<input id="upload-touxiang" type="file">上传图像</input>-->
						<div class="img" id="touxiang"></div>
						<a href="#" id="upload-touxiang">上传头像 <input id="file_logo"
							name="image" class="img_logo" type="file"
							style="margin-left:47px;width:88px;">
						</a>
						<p>姓名</p>
						<input type="text" class="txt-input">
						<p>公司名称</p>
						<input type="text" class="txt-input">
						<p>担任职位</p>
						<input type="text" class="txt-input">
						<p>所在地</p>
						<input type="text" placehold="选择省份" style="width:200px;"
							class="txt-input">
						<!--<input type="text" placehold="选择城市" style="float:left;margin-left:20px;margin:200px;">-->
						<p>个人简介</p>
						<input type="text" style="width:800px; height:100px;"
							class="txt-input">
						<p>投资领域</p>
						<div id="domain">
							<button type="button">互联网</button>
							<button type="button">电子</button>
							<button type="button">租赁</button>
							<button type="button">服务业</button>
						</div>
						<div id="fun-btn">
							<button type="button" id="save">保存</button>
							<button type="button" id="cancel" style="margin-left:40px;">取消</button>
						</div>
					</div>

					<!--认证资料开始-->
					<div id="authentication-information" style="display:none;">
						<p>真实姓名</p>
						<input type="text" placeholder="请输入真实姓名" class="txt-input">
						<p>身份证号码</p>
						<input type="text" placeholder="请输入身份证号码" class="txt-input">

						<p style="margin-top:50px;">
							身份证照片 <span
								style="font-size:13px; color:#aaaaaa;line-height:13px;margin-top:1.5px;">(清晰的照片可以快速审核通过)</span>
						</p>
						<div id="id-photo">
							<img src=".//img/account-setting.png"> <a href="#"
								id="upload-pic">上传身份证正面照片 <input id="file_logo" name="image"
								class="img_logo" type="file" style="margin-left:0;width:153px;">
							</a>
						</div>

						<p style="margin-top:50px;">
							营业执照照片 <span
								style="font-size:13px; color:#aaaaaa;line-height:13px;margin-top:1.5px;">(清晰的照片可以快速审核通过)</span>
						</p>
						<div id="id-photo">
							<img src=".//img/account-setting.png"> <a href="#"
								id="upload-pic">上传营业执照照片 <input id="file_logo" name="image"
								class="img_logo" type="file" style="margin-left:0;width:153px;">
							</a>
						</div>


						<p style="margin-top:30px;">营业执照注册号</p>
						<input type="text" placeholder="请输入营业执照注册号" class="txt-input">

						<div id="terms">
							<div>
								<input type="checkbox"> <span>就放假啊放假啊快放假啦舒服分开始减肥了</span>
							</div>
							<div>
								<input type="checkbox"> <span>就放假啊放假啊快放假啦舒服分开始减肥了</span>
							</div>
							<div>
								<input type="checkbox"> <span>就放假啊放假啊快放假啦舒服分开始减肥了</span>
							</div>
						</div>
						<button type="submit" id="submit" onclick="submit()">提交</button>

					</div>
				</div>
			</div>

			<div id="project-center-content" style="display:none"></div>

			<div id="account-setting-content" style="display:none">
				<div class="content">
					<button class="tab" id="change-pwd"
						style="margin-left:50px;border-bottom:4px solid #355078;"
						onclick="changePwd()">修改登录密码</button>
					<div id="tab"></div>
					<button class="tab" id="change-phone" onclick="changePhone()">更改绑定手机</button>
					<hr style="margin:0 110px 44px 50px;">

					<div id="change-pwd-content" style="display:block;">
						<div class="user-phone-number" style="margin-top:80px">
							<img src=".//img/suo.png"> <input placeholder="请输入旧密码"
								name="phone-number">
						</div>

						<div class="user-phone-number" style="margin-top:30px">
							<img src=".//img/suo.png"> <input placeholder="请输入新密码"
								name="phone-number">
						</div>

						<div class="pop_oc">
							<div class="pop_oc_por"></div>
						</div>
						<div class="user-phone-number" style="margin-top:38px">
							<img src=".//img/suo.png"> <input placeholder="请确认新密码"
								name="phone-number">
						</div>

						<button class="submit" onclick="next()" type="submit">提交</button>
					</div>

					<div id="change-phone-content" style="display:none;">

						<p
							style="width:%100;height:30px;margin-top:70px;font-size:30px;color:#355078;text-align:center;">
							15288833321</p>
						<div class="captcha">
							<img src=".//img/shouji.png"> <input
								placeholder="请输入验证码" name="phone-number">
							<button class="obtain-captcha" onclick="obtainCaptcha()">获取验证码</button>
						</div>
						<button class="next-step" onclick="next()">下一步</button>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
<script type="text/javascript" src=".//js/jquery1.8.min.js"
	charset="utf-8"></script>
<script type="text/javascript" src=".//js/user/PersonCenter.js"
	charset="utf-8"></script>
</html>