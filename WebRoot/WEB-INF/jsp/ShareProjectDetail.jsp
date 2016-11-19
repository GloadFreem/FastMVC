<%@ page language="java"
	import="java.util.*,com.jinzht.web.entity.Project,com.jinzht.web.entity.Roadshow,com.jinzht.web.entity.Roadshowplan"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	Map map = (Map) request.getAttribute("data");
	Project project = (Project) map.get("project");
	Set set = project.getRoadshows();

	Roadshow roadShow = (Roadshow) request.getAttribute("roadshow");

	Roadshowplan plan = (Roadshowplan) request.getAttribute("plan");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<title><%=project.getAbbrevName()%>--【金指投投融资】</title>
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
	<div class="status">
		<img class="status-image" src="./images/share/status.png">
		<div class="status-name"><%=project.getFinancestatus().getName()%></div>
	</div>
		<div class="header" style="margin-top: -30%;">
			<img class="header-image" src="./images/share/header-icon.png">
			<p class="project-name"><%=project.getFullName()%></p>
		</div>
		<div class="line"></div>
		<div class="project-detail">
			<!-- 融资总额-->
			<div class="total-finacne">
				<div class="total-finacne-img">
					<img class="finacne-image" src="./images/share/iconfont-rong.png">
				</div>
				<div class="item-name">融资总额</div>
				<div class="item-value"><%=plan.getFinanceTotal()%>万
				</div>
			</div>
			<!-- 已融总额-->
			<div class="total-finacne">
				<div class="total-finacne-img">
					<img class="finacne-image" src="./images/share/iconfont-jine.png">
				</div>
				<div class="item-name">已融金额</div>
				<div class="item-value"><%=plan.getFinancedMount()%>万
				</div>
			</div>
			<!-- 起止时间-->
			<div class="total-finacne">
				<div class="total-finacne-img">
					<img class="finacne-image"
						src="./images/share/iconfont-shengyushijian.png">
				</div>
				<div class="item-name">起止时间</div>
				<div class="item-value"><%=plan.getBeginDate()%><br /><%=plan.getEndDate()%></div>
			</div>
			<!-- 所在地-->
			<div class="total-finacne">
				<div class="total-finacne-img">
					<img class="finacne-image" src="./images/share/location.png">
				</div>
				<div class="item-name">所在地</div>
				<div class="item-value"><%=project.getAddress()%></div>
			</div>
		</div>

		<!-- 项目描述 -->
		<div class="project-desc">
			<div>
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<%=project.getDescription()%></div>
			</div>

			<div>
				<div class="project-images">
					<c:forEach items="${images}" var="projectImages" varStatus="vs">
						<img class="project-desc-image" src=${projectImages.imageUrl}>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="line-separte"></div>
		<!-- 团队 -->
		<div class="content">
			<div class="header">
				<img class="header-image" src="./images/share/team.png">
				<p class="project-name">团队</p>
			</div>
			<div class="line"></div>
			<!-- 项目描述 -->
			<div class="project-desc">
				<div>

					<c:forEach items="${members}" var="member" varStatus="vs">

						<c:choose>
							<c:when test="${vs.index==0}">
								<div class="team-item-none">
									<img class="team-desc-image" src=${member.icon}>
									<div class="team-desc-name">${member.name}</div>
									<div class="team-desc-value">${member.position}</div>
								</div>
							</c:when>

							<c:otherwise>
								<div class="team-item">
									<img class="team-desc-image" src=${member.icon}>
									<div class="team-desc-name">${member.name}</div>
									<div class="team-desc-value">${member.position}</div>
								</div>
							</c:otherwise>
						</c:choose>

					</c:forEach>

				</div>
			</div>
			<div class="line"></div>
			<!-- 项目描述 -->
			<div class="finance-desc">
				<div style=" display: -webkit-flex;display: flex;justify-content: center;">
					<c:if test="${not empty extr}">
						<c:forEach items="${extr}" var="member" varStatus="vs">

							<c:choose>
								<c:when test="${vs.index==0}">
									<div class="team-item">
										<img class="team-desc-image" src=${member.icon}>
										<div class="team-desc-name">${member.content}</div>
									</div>
								</c:when>

								<c:otherwise>
									<div class="team-item">
										<img class="team-desc-image" src=${member.icon}>
										<div class="team-desc-name">${member.content}</div>
									</div>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</c:if>

					<%--<div class="team-item">--%>
						<%--<img class="team-desc-image" src="./images/share/Bar-chart.png" />--%>
						<%--<div class="team-desc-name">财务</div>--%>
						<%--<div class="team-desc-value">状况</div>--%>
					<%--</div>--%>
					<%--<div class="team-item">--%>
						<%--<img class="team-desc-image" src="./images/share/Pie-chart.png" />--%>
						<%--<div class="team-desc-name">融资</div>--%>
						<%--<div class="team-desc-value">方案</div>--%>
					<%--</div>--%>
					<%--<div class="">--%>
						<%--<img class="team-desc-image" src="./images/share/Loop.png" />--%>
						<%--<div class="team-desc-name">退出</div>--%>
						<%--<div class="team-desc-value">渠道</div>--%>
					<%--</div>--%>
				</div>
			</div>
			<div class="line-separte" style="margin-top: 0%;"></div>
		</div>
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