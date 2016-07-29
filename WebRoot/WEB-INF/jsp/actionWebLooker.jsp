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
<title>活动分享</title>
<link rel="stylesheet" type="text/css" href="./images/action/action.css">
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
<body class="d-body" style="overflow-x: hidden;background-color:white">
	<div class="aboutus">
		<img src="./images/share/矩形-4-拷贝.png"></img>
	</div>
	<div class="brief">
		<p>金指投专注于成长型企业股权投融资，现已完成数个千万级项目股权融资，杰邦科技（836842）、国联质检
			（837554）、神木药业（832040）已成功挂牌新三板并实现投资收益。</p>
	</div>
	<div class="title">
		<p>${action.name}</p>
	</div>
	<div class="content">
		<div class="detail">
			<p>
				<br>${action.description }</p>
		</div>
		<div class="tableimg">

			<c:forEach items="${action.actionimages}" var="image" varStatus="vs">

				<c:choose>
					<c:when test="${vs.index!=2}">
						<img class="item-img"
							src=${image.url}></img>
					</c:when>

					<c:otherwise>
						<img class="item-img1"
							src=${image.url}></img>
					</c:otherwise>
				</c:choose>

			</c:forEach>
		</div>
		<div class="more">
			<img src="./images/share/更多@2x.png"/>
		</div>
	</div>
	<div class="line"></div>
	<div class="tableinfo">
		<table>
			<tr>
				<td><img src="./images/share/矩形-11@3x.png"></img></td>
				<td><p>活动信息</p></td>
			</tr>
		</table>
	</div>
	<div class="act-det">
		<table>
			<tr>
				<td><img src="./images/share/剩余时间@3x.png"./images/share/矩形-11@3x.png"></img></td>
				<td><p>${action.startTime }</p></td>
			</tr>
			<tr>
				<td><img src="./images/share/iconfont-jiangshijianjie01@3x.png"></img></td>
				<td><p>${action.memberLimit }人</p></td>
				<td><p>免费</p></td>
			</tr>
		</table>
	</div>
	</div>
	<div class="footer-tip">
		<img class="footer-tip-image" src="./images/share/logo.png">
		<div class="tip-content">立即开始项目投融资</div>
	</div>
</body>
</html>