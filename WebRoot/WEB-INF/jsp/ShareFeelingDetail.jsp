<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>【金指投投融资】--圈子分享</title>
<link rel="stylesheet" type="text/css"
	href="./images/share/css/share.css">
<script type="text/javascript" src="./images/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="./images/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
	jQuery(function($) { //或者$(function(){...});
		var str = "http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro";
		$(".footer-tip").click(function() {
			location.href = str;
		});
	});
</script>
<style>
</style>
</head>
<body class="d-body" style="overflow-x: hidden;">
	<!-- 如何上传项目 -->
	<div class="project">
		<img class="project-image" src="./images/share/JinCaptial.png">
	</div>
	<div class="jinzht-dec">&nbsp;&nbsp;金指投专注于成长型企业股权投融资，现已完成数个千万级项目股权融资，杰邦科技(836842),国联质检(837554),神木药业(832040),已成功关牌新三板并实现收益</div>
	<div class="line-separte"></div>
	<div class="content">
		<div>
			<div class="feeling-header">
				<div class="feeling-header-img">
					<img class="header-img" alt="" src=${user.headSculpture} }>
				</div>
			</div>
			<div class="feeling-header-content">
				<div class="name">${authentic.getName()}
					${authentic.companyAddress}</div>
				<div class="box">
					<div class="box-info">${authentic.companyName}|
						${authentic.position}</div>
					<div class="box-time">38分钟前</div>
				</div>
			</div>
		</div>
		<!-- 文字内容 -->
		<div>
			<div class="feeling-content">${data.content}</div>
		</div>
		<!-- 图片内容 -->
		<div class="content-images">

			<div class="content-image">
				<c:forEach items="${images}" var="img" varStatus="vs">
					<img class="content-image-item" src=${img.getUrl()}>
				</c:forEach>
			</div>
		</div>


		<div class="line-separte" style="margin-top: 5%"></div>

		<!-- 评论 -->
		<c:forEach items="${comments}" var="item" varStatus="vs">
			<div>
				<div class="feeling-header">
					<div class="feeling-header-img">
						<img class="header-img" alt=""
							src=${item.getUsersByUserId().getHeadSculpture()}>
					</div>
				</div>
				<div class="feeling-header-content">
					<div class="name">${item.getUsersByUserId().getName()}</div>
					<div class="name">${item.publicDate }</div>
					<div class="box">${item.content} }</div>
				</div>

			</div>
			<!-- 分割线 -->
			<div class="line"></div>
		</c:forEach>

	</div>

	<div class="foot-box">
		<p>Copyright &#169;金指投 版权所有 All Rights Reserved.</p>
	</div>

	<div class="footer-tip">
		<img class="footer-tip-image" src="./images/share/logo.png">
		<div class="tip-content">立即开始项目投融资</div>
	</div>

	</div>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</body>
</html>