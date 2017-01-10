<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
                                                  prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="initial-scale=1, user-scalable=no, width=device-width">
<link rel="stylesheet" type="text/css" href="./css/base.css">
<link rel="stylesheet" type="text/css" href="./css/weui.min.css">
<title>课程详情</title>
</head>
<body>
	<div class="head">
		<div class="icon" style="color: #000">&nbsp;</div>
		<div class="title">课程详情</div>
		 <div class="pagebar" id="page_bar"  style="display: none;">
        <div class="c-listbar shadow">
            <div class="bar actived" id="bar_one2">介绍</div>
            <div class="bar nornal" id="bar_two2">目录</div>
        </div>
    </div>
	</div>

	<div class="content" id="content">

		<img class="c-img" src="${data.bimage}" />
		<div class="c-listbar"  id="listbar" >
			<div class="bar actived" id="bar_one">介绍</div>
			<div class="bar nornal" id="bar_two">目录</div>
		</div>

		<!--//loadingmore-->
		<div class="c-listview" style="display: none" id="loading">
			<div class="weui-loadmore">
				<i class="weui-loading"></i> <span class="weui-loadmore__tips">正在加载</span>
			</div>
		</div>
		<!--//loadingmore-->
		<div class="c-listview" style="display: none">
			<div class="weui-loadmore weui-loadmore_line">
				<span class="weui-loadmore__tips">暂无数据</span>
			</div>
		</div>

		<!--listview page1-->
		<div class="c-listview" id="page_one">
			<div class="c-title2">${data.bname}</div>
			<div class="price">
				<div class="c-price">¥ ${data.bpriceNow}</div>
				<div class="c-price-normal">¥ ${data.bpriceBase}</div>
				<div class="c-price-desc">已经有${data.bextr}人学习</div>
			</div>
			<div class="price">
				<div class="weui-panel weui-panel_access">
					<div class="weui-panel__hd">
						<div class="c-title">讲师简介</div>
					</div>
					<div class="weui-panel__bd">
						<a href="javascript:void(0);"
							class="weui-media-box weui-media-box_appmsg">
							<div class="weui-media-box__hd">
								<img class="weui-media-box__thumb" style="border-radius:50%;"
									src=" ${data.speechmarker.image}" alt="">
							</div>
							<div class="weui-media-box__bd">
								<h4 class="weui-media-box__title2">
									${data.speechmarker.name}</h4>
								<p class="weui-media-box__desc2">${data.speechmarker.desc}</p>
							</div>
						</a>
					</div>
				</div>
				<div class="weui-panel__hd">
					<div class="c-title">课程简介</div>
				</div>
				<article class="weui-article ">
					<section>
						<p class="padding">${data.bdesc}</p>
					</section>
				</article>
			</div>
		</div>

		<!--listview page2-->
		<div class="c-listview" style="margin-bottom: 4rem;display: none"
			id="page_two">


			<c:forEach var="item"  items="${data.businessVideos}">
				<a href="href="input.action?id=${uid}&price=${data.bpriceNow}&contentid=${contentId}&openId=${openId}"
					class="weui-media-box weui-media-box_appmsg">
					<div class="weui-media-box__hd2">
						<img class="weui-media-box__thumb" src="${item.vimage}" alt="">
						<img class="weui-media-box__thumb2" src="img/play.png" alt="">
						<div style="clear: both"></div>
					</div>
					<div class="weui-media-box__bd">
						<h4 class="weui-media-box__title3">${item.vname}</h4>
						<p class="weui-media-box__desc">${fn:substring(item.vtimelong/60, 0, 1)}分${item.vtimelong%60}秒</p>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>

	<div class="weui-footer_fixed-bottom"
		style="z-index: 99999;background: #ffffff;border-top: 1px solid #eaeaea">
		<a
			href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro#opened"
			class=" eason-btn1"><img src="img/logo.png" class="logo" />更多热门课程</a>
		<a href="input.action?id=${uid}&price=${data.bpriceNow}&contentid=${contentId}&openId=${openId}" class=" eason-btn2">开始学习</a>
	</div>


</body>

<script type="text/javascript" src="js/jquery2.14.min.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/init.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/detail.js" charset="utf-8"></script>
</html>