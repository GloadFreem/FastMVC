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
<meta name="keywords" content="金指投;投融资平台,资讯,互联网,金融,创投,观点,报告,项目">
<meta name="viewport"
	content="initial-scale=1, user-scalable=no, width=device-width">
<!--<link rel="stylesheet" type="text/css" href="css/swiper-3.4.0.min.css">-->
<link rel="stylesheet" type="text/css" href="./css/base.css">
<link rel="stylesheet" type="text/css" href="./css/swiper-3.4.0.min.css">
<link rel="stylesheet" type="text/css" href="./css/main.css">
<title>金指投</title>

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
					<div class="index-bar"></div>
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


		<div class="module index-slide"
			style="border-top-color: rgb(216, 216, 216);">
			 <div class="main">

            <div class="swiper-container">
                <div class="swiper-wrapper">
					
						<c:forEach var="banner"  items="${BannerList}" >
								  			<!--item-->
								<div class="swiper-slide" >
									<a href="${banner.url }"  >
									<img  class="b-img"
										src="${banner.image}"
									></a>
									<div class="title-mask">
									<a href=""
									>${banner.desc }</a>
									<!-- <div class="des">又到了换季添置新衣的时候，我们知道你已经买腻了那些人人都在穿的品牌，又不可能天天花高价买大牌，那么在这两者之间有没有第...</div> -->
									</div>
									<div style="clear:both"></div>
								</div>
						</c:forEach>
					

					</div>
					        <div class="swiper-button-next-2"></div>
                <div class="swiper-button-prev-2"></div>
					  <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
				</div>
			</div>
		</div>



			<!--正文-->
			<div class="content ">
				<!--最新资讯-->
				<div class="content-left c-top">
					<!--title-->
					<div class="content-title">
						<div class="font-ch">最新资讯</div>
						<div class="font-g">/</div>
						<div class="font-en">Latest News</div>
					</div>
					<!--list-->
					<div class="content-list" id="new_list">
						

					</div>
				</div>

				<!--观点报告-->
				<div class="content-right c-top">
					<!--title-->
					<div class="content-title">
						<div class="font-ch">观点报告</div>
						<div class="font-g">/</div>
						<div class="font-en">Opinion Report</div>
					</div>
					<div class="opinion-list">
						<div class="content-item" style="margin-top: 0px;" id="report_list">
							<div class="opinion-text">中国银监会10月21日召开三季度经济金融形势分析会</div>
							<div class="opinion-text">购车款报销，购车款报销</div>
							<div class="opinion-text">中国银监会10月21日召开三季度经济金融形势分析会</div>
							<div class="opinion-text">中国银监会10月21日召开三季度经济金融形势分析会</div>
							<div class="opinion-text">中国银监会10月21日召开三季度经济金</div>
							<div class="opinion-text">中国银监会10月21日召开三季度经济金</div>
							<div class="content-item">
								<div class="content-opinion-more">更多观点</div>
							</div>
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
						<div class="opinion-list">
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
	<script type="text/javascript"
		src=".//js/swiper-3.4.0.jquery.min.js" charset="utf-8"></script>
			<script type="text/javascript" src=".//js/config.js" charset="utf-8"></script>
	<script type="text/javascript" src=".//js/bar.js" charset="utf-8"></script>
	<script type="text/javascript" src=".//js/main.js" charset="utf-8"></script>

</body>
</html>