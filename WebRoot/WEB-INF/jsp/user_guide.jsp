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
<title>【金指投投融资】--新手指南</title>
<link rel="stylesheet" type="text/css"
	href="./images/share/css/share.css">
<script type="text/javascript" src="./images/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="./images/jquery.SuperSlide.2.1.1.js"></script>
<style>
</style>
</head>
<body class="d-body" style="overflow-x: hidden;">
	<!-- 如何上传项目 -->
	<div class="upload">
		<img class="upload-img" src="./images/share/upload_project.png">
	</div>
	<div class="upload-desc">
		<div class="upload-desc-line">
			<p class="upload-desc-line1-p">
				No.<font size="30px">1</font>
			</p>
		</div>
		<div class="upload-desc-line">
			<p class="upload-desc-line2-p">进入金指投APP</p>
		</div>
		<div class="upload-desc-line">
			<p class="upload-desc-line3-p">
				点击<font color="#fb6807">底部Logo</font>图标
			</p>
		</div>
		<div class="upload-desc-line">
			<p class="upload-desc-line4-p">进入Logo菜单中心</p>
		</div>
	</div>
	<!-- 底部菜单 -->
	<div class="menu">
		<img class="menu-img" src="./images/share/menu.png">
	</div>
	<div class="menu-desc">
		<div class="menu-desc-line">
			<p class="menu-desc-line1-p">
				No.<font size="30px">2</font>
			</p>
		</div>
		<div class="menu-desc-line">
			<p class="menu-desc-line2-p">在Logo菜单中</p>
		</div>
		<div class="menu-desc-line">
			<p class="menu-desc-line3-p">
				点击<font color="#fb6807">上传项目</font>按钮
			</p>
		</div>
		<div class="menu-desc-line">
			<p class="menu-desc-line4-p">进行上传</p>
		</div>
	</div>

	<!-- 上传项目 -->
	<div class="upload-project">
		<img class="upload-project-img"
			src="./images/share/upload_project_menu.png">
	</div>
	<div class="upload-project-email">
		<img class="upload-project-email-img"
			src="./images/share/upload_info.png">
	</div>

	<div class="commit-desc">
		<div class="commit-desc-line">
			<p class="commit-desc-line1-p">
				No.<font size="30px">3</font>
			</p>
		</div>
		<div class="commit-desc-line">
			<p class="commit-desc-line2-p">通过邮件将项目资料</p>
		</div>
		<div class="commit-desc-line">
			<p class="commit-desc-line3-p">发送给我们</p>
		</div>
		<div class="commit-desc-line">
			<p class="commit-desc-line4-p">审核完成后我们会</p>
		</div>
		<div class="commit-desc-line">
			<p class="commit-desc-line4-p">帮您上传</p>
		</div>
	</div>


	<div class="invest">
		<img class="invest-img" src="./images/share/how_invest.png">
	</div>
	<div class="upload-desc">
		<div class="upload-desc-line">
			<p class="upload-desc-line1-p">
				No.<font size="30px">1</font>
			</p>
		</div>
		<div class="upload-desc-line">
			<p class="upload-desc-line2-p">选择您看好的</p>
		</div>
		<div class="upload-desc-line">
			<p class="upload-desc-line3-p">
				状态为<font color="#fb6807">融资中</font>的项目
			</p>
		</div>
	</div>
	<div class="financing">
		<img class="financing-img" src="./images/share/financing.png">
	</div>
	<div class="financing-desc">
		<div class="upload-desc-line">
			<p class="upload-desc-line1-p">
				No.<font size="30px">2</font>
			</p>
		</div>
		<div class="upload-desc-line">
			<p class="upload-desc-line3-p">
				点击底部的<font color="#fb6807">认投</font>按钮
			</p>
		</div>
		<div class="upload-desc-line">
			<p class="upload-desc-line4-p">进入项目认投页面</p>
		</div>
	</div>

	<div class="confirm-desc">
		<div class="confirm-desc-line">
			<p class="confirm-desc-line1-p">
				No.<font size="30px">3</font>
			</p>
		</div>
		<div class="confirm-desc-line">
			<p class="confirm-desc-line2-p">在认投页面输入金额</p>
		</div>
		<div class="confirm-desc-line">
			<p class="confirm-desc-line3-p">并选择投资意向</p>
		</div>
		<div class="confirm-desc-line">
			<p class="confirm-desc-line3-p">
				<font color="#fb6807">领投</font>或<font color="#fb6807">跟投</font>
			</p>
		</div>
	</div>
	<div class="submmit">
		<img class="submmit-img" src="./images/share/finace_pay.png">
	</div>
	<div class="submmit-desc">
		<div class="submmit-desc-line">
			<p class="submmit-desc-line1-p">
				No.<font size="30px">4</font>
			</p>
		</div>
		<div class="submmit-desc-line">
			<p class="submmit-desc-line3-p">
				点击底部<font color="#fb6807">立即支付</font>按钮
			</p>
		</div>
		<div class="submmit-desc-line">
			<p class="submmit-desc-line3-p">进行支付</p>
		</div>
		<div class="submmit-desc-line">
			<p class="submmit-desc-line4-p">完成项目认投</p>
		</div>
	</div>
	<div class="foot-box">
		<p>Copyright &#169;金指投 版权所有 All Rights Reserved.</p>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</body>
</html>