
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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="initial-scale=1, user-scalable=no, width=device-width">
<link rel="stylesheet" type="text/css" href=".//css/base.css">
<link rel="stylesheet" type="text/css" href=".//css/main.css">
<title>金指投--资讯详情</title>
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


		<!--内容区域;最小1200px;-->
		<div class="middle" style="min-height: 800px">

			<!--正文-->
			<div class="content  ">
				<!--最新资讯-->
				<div class="content-left c-top-s">
					<!--title-->
					<div class="content-item">
						<div class="font-detail">首页 / 创投咨询 /</div>
						<div class="font-detail-title">${Title}</div>
						<!--<div class="font-en">Latest News</div>-->
					</div>
					<!--list-->
					<div class="content-list">
					    <div class="content-detail">
										${Detail}
						</div>
					</div>
				</div>


				<!--观点报告-->
				<div class="content-right c-top">
					<!--title-->
					<div class="content-title">
						<div class="font-ch">相关资讯</div>
					</div>

					<div class="content-list" >
					     
					     <div class="content-item-hot">
							<div style="color:#cccccc">暂无</div>
						</div>
						<!--item
						<div class="content-item-hot">
							<img src=".//img/main/bg_test.png" class="item-img-hot">
							<div class="item-r-hot">
								<a href="#" class="item-title">购车款报销，购车款报销，购车款报销购车款报销，购车款报销</a>

							</div>
						</div>
					-->

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
	<script type="text/javascript" src=".//js/bar.js" charset="utf-8"></script>
	<script type="text/javascript" src=".//js/main-detail.js"
		charset="utf-8"></script>

</body>
</html>