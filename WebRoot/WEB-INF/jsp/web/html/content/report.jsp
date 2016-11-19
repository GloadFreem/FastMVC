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
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="initial-scale=1, user-scalable=no, width=device-width">
<link rel="stylesheet" type="text/css" href=".//css/base.css">
<link rel="stylesheet" type="text/css" href=".//css/main.css">
<link rel="stylesheet" type="text/css" href=".//css/report.css">
<title>金指投--观点报告</title>
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
					<div class="index-bar-1"></div>
				</ul>
				<div class="h-right">
					<div class="search">搜索</div>
					<div class="down">下载APP</div>
					<div class="text">
							<a href="./login.action">登录</a> | <a href="./login.action">注册</a>
					</div>

				</div>
			</div>
		</div>

		<!--banner图片展示-->
		<!--<div class="banner">-->
		<!--<div class="poster-main B_Demo" >-->
		<!--首推资讯-->
		<div class="middle">
			<div class="content c-top-s" style="height:410px;position:relative;">
			
						<c:forEach var="banner"  items="${BannerList}" >
						<c:choose>
								<c:when test="${banner.bannerId==1 }">
									<div class="t-left">
										<a id="msg_img_0" class="msg-big  "  href="${banner.url}">
										
											<img src="${banner.image}" style="width: 100%;height: 100%">
											<div id="msg_title_0" class="shadow">${banner.desc}</div>
										</a>
									</div>
								</c:when>
								
								<c:when test="${banner.bannerId==2 }">
										<div class="t-right">
											  <a id="msg_img_1" class="msg-small"   href="${banner.url}">
					                <img src="${banner.image}" style="width: 100%;height: 100%">
					                    <div id="msg_title_1" class="shadow">${banner.desc}</div>
					                </a>
								</c:when>
								<c:when test="${banner.bannerId==5 }">
											  <a id="msg_img_1" class="msg-small"  href=" ${banner.url}">
					             <img src="${banner.image}" style="width: 100%;height: 100%">
					                    <div id="msg_title_1" class="shadow">${banner.desc}</div>
					                </a>
						 				</div>
								</c:when>
								<c:otherwise >
								  <a id="msg_img_1" class="msg-small  "  href="${banner.url}">
					               <img src="${banner.image}" style="width: 100%;height: 100%">
					                    <div id="msg_title_1" class="shadow">${banner.desc}</div>
					                </a>
								</c:otherwise>
								</c:choose>
						</c:forEach>
				
				

		</div>


		<!--内容区域;最小1200px;-->
		<div class="middle" style="min-height: 800px">
			<!--正文-->
			<div class="content  ">
				<!--最新资讯-->
				<div class="content-left c-top">
					<!--title-->
					<div class="content-title">
						<div class="font-ch">最新公布</div>
						<div class="font-g">/</div>
						<div class="font-en">Latest Release</div>
					</div>
					<!--list-->
					<div class="content-list" id="new_report_list">
						

					</div>
				</div>

				<!--观点报告-->
				<div class="content-right c-top">
					<!--title-->
					<div class="content-title">
						<div class="font-ch">热门观点</div>
						<div class="font-g">/</div>
						<div class="font-en">Popular View</div>
					</div>
					<div class="opinion-list">
						<div class="content-item" style="margin-top: 0px;">
							<c:forEach var="report"  items="${HotReportList}" >
								  		<!--item-->
										<a href="<%=basePath%>web/reportDetail.action?id=${report.infoId}" ><div class="opinion-text">${report.title}</div></a>
							</c:forEach>
						</div>
					</div>
				</div>

				<!--观点报告-->
				<div class="content-right c-top-s">
					<!--title-->
					<div class="content-title">
						<div class="font-ch">热门资讯</div>
						<div class="font-g">/</div>
						<div class="font-en">Hot News</div>
					</div>
						<c:forEach var="msg"  items="${HotList}" >
							  			<!--item-->
							<div class="content-item-hot">
								<img src="${msg.images[0]}" class="item-img-hot">
								<div class="item-r-hot">
									<a href="<%=basePath%>web/MainDetail.action?id=${msg.id}" class="item-title">${msg.title}</a>
									<div class="item-time">${msg.publicDate}</div>
								</div>
							</div>
						</c:forEach>

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
						<div class="img2"></div>
						<div class="text">微信公众号</div>
					</div>
				</div>
			</div>
			<div class="bot">京ICP备15043593号 © 2015-2016 金指投 版权所有</div>
		</div>


	</div>

	<script type="text/javascript" src=".//js/jquery1.8.min.js"
		charset="utf-8"></script>
					<script type="text/javascript" src=".//js/config.js" charset="utf-8"></script>
	<script type="text/javascript" src=".//js/report.js" charset="utf-8"></script>

</body>
</html>