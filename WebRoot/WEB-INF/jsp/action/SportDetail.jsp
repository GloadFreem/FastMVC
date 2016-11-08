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
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
<title>金指投活动详情</title>
<style type="text/css">
html, body {
	margin: 0;
	border: 0;
	vertical-align: baseline;
	font: inherit;
	font-size: 100%;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	font-family: sans-serif;
	/* 1 */
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
	/* 2 */
	-webkit-text-size-adjust: 100%;
}

body {
	-webkit-touch-callout: none;
	-webkit-font-smoothing: antialiased;
	font-smoothing: antialiased;
	-webkit-text-size-adjust: none;
	-moz-text-size-adjust: none;
	text-size-adjust: none;
	-webkit-tap-highlight-color: transparent;
	-webkit-tap-highlight-color: transparent;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	margin: 0;
	padding: 0;
	color: #747474;
	word-wrap: break-word;
	font-size: 14px;
	font-family: -apple-system;
	font-family: "-apple-system", "Helvetica Neue", "Roboto", "Segoe UI",
		sans-serif;
	line-height: 20px;
	text-rendering: optimizeLegibility;
	-webkit-backface-visibility: hidden;
	-webkit-user-drag: none;
	-ms-content-zooming: none;
}

.content {
	/*margin-top: 1rem;;*/
	width: 100%;
	float: left;
	font-size: 1rem;
	/*line-height: 1.8rem;*/
	margin-bottom: 2rem;;
	color: #474747;
	background: #efefef;
}

.content a {
	text-decoration: none;
}

.title-img {
	width: 100%;
	height: 62.5%;
}

.card {
	width: 100%;
	background: #ffffff;
	margin-top: 0.5rem;
	float: left;
}

.card .c-top {
	width: 98%;
	padding: 1%;
	padding-top: 0.5rem;;
	height: 1.8rem;
	float: left;
}

.card .c-top .icon {
	width: 5px;
	margin-top: 0.1rem;;
	margin-left: 0.5rem;;
	height: 1.4rem;;
	float: left;
}

.card .c-top .text {
	width: 60%;
	margin-left: 0.5rem;;
	font-size: 1rem;
	line-height: 1.6rem;;
	height: 1.8rem;;
	float: left;
}

.card .c-content {
	width: 98%;
	padding: 1%;
	padding-top: .5rem;;
	border-top: 1px solid #dcdcdc;
	/*border-bottom: 1px solid #dcdcdc;*/
	float: left;
}

.c-content .icon {
	width: 1rem;;
	height: 1rem;
	margin-left: 1rem;
	margin-top: 0.3rem;;
	float: left;
}

.c-content .item {
	width: 100%;
	/*height: 1.8rem;;*/
	float: left;
}

.n-left {
	width: 3rem;
	height: 3rem;
	margin-left: 1rem;;
	border-radius: 1.5rem;
	margin-top: 0.5rem;;
	margin-bottom: 0.5rem;;
	float: left;
}

.n-center {
	width: 60%;
	height: 4rem;
	margin-left: .5rem;;
	float: left;
}

.n-center .name {
	width: 100%;
	margin-top: .7rem;;
	height: 1.5rem;
	color: #474747;
	font-size: 1rem;
	line-height: 1.5rem;
	float: left;
}

.n-center .desc {
	width: 100%;
	height: 1.2rem;
	font-size: 0.8rem;
	color: #747474;
	line-height: 1.2rem;;
	float: left;
}

.n-right {
	width: 15%;
	height: 3rem;
	color: #9b9b9b;
	font-size: 0.65rem;
	line-height: 2.5rem;;
	text-align: center;
	/*margin-right: .5rem;;*/
	float: right;
}

.c-content .t-text {
	width: 80%;
	margin-left: 12px;;
	height: 1.8rem;;
	font-size: 0.85rem;
	line-height: 1.6rem;;
	color: #747474;
	float: left;
}

.c-content .c-text {
	width: 96%;
	margin-top: 1rem;;
	font-size: 0.9rem;;
	line-height: 1.5rem;;
	margin-left: 2%;
	float: left;
}

.c-content .c-img {
	margin-top: .5rem;;
	width: 96%;
	margin-left: 2%;
	float: left;
}

/*.circle {*/
/*font-size: 0.6rem;*/
/*width: 1.2rem;*/
/*height: 1rem;*/
/*line-height: 1rem;*/
/*text-align: center;*/
/*color: #ffffff;*/
/*border-radius: 4px;*/
/*background: #ffffff;*/
/*}*/
.line {
	border-bottom: 1px solid #ececec;
}

.zan-icon {
	margin-left: 5%;
	margin-top: .2rem;;
	width: 1rem;;
	height: 1rem;;
	float: left;
}

.zan-name {
	margin-left: 1rem;
	width: 84%;
	color: #ff6700;
	font-size: .75rem;
	line-height: 1.5rem;;
	/*height: 1.2rem;;*/
	float: left;
}

.ping-item {
	width: 90%;
	font-size: .8rem;
	text-align: left;
	line-height: 1.4rem;;
	float: left;
}

.ping-item span {
	margin-left: 5px;
	margin-right: 5px;;
	color: #747474;
	font-size: .8rem;
	line-height: 1.4rem;;
}

.ping-icon {
	margin-left: 5%;
	margin-top: .2rem;;
	width: 1rem;;
	height: 1rem;;
	float: left;
}
</style>
</head>
<body>
	<div class="content">
		<img class="title-img" src="${action.startPageImage }">

		<div class="card">
			<div class="c-top">
				<img class="icon" src="images/action/icon_blue_ling.png">
				<div class="text">
					<strong>活动介绍</strong>
				</div>
			</div>
			<div class="c-content">
				<div class="item ">
					<img class="icon" src="images/action/icon_time.png">
					<div class="t-text">${action.startTime }</div>
				</div>
				<div class="item">
					<img class="icon"
						src="images/action/iconfont-jiangshijianjie01.png">
					<div class="t-text">${action.memberLimit }人</div>
				</div>
				<div class="item">
					<img class="icon" src="images/action/iconfont-dingwei.png">
					<div class="t-text">${action.address }</div>
				</div>
			</div>
		</div>

		<div class="card">
			<div class="c-top">
				<img class="icon" src="images/action/icon_blue_ling.png">
				<div class="text">
					<strong>活动详情</strong>
				</div>
			</div>
			<div class="c-content">
				<c:choose>
					<c:when test="${action.actionintroduces != null}">
						<c:forEach items="${action.actionintroduces}" var="item">
							<c:choose>
								<c:when test="${item.type==0 }">
									<div class="c-text">
										<!--str =   str.replace(/[<br>]/g, "<br>")//去掉回车换行-->
										${item.content}
									</div>
								</c:when>
								<c:otherwise>
									<img class="c-img" src="${item.content }" />
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
				</c:choose>

			</div>
		</div>
		<div style="width:100%;height:50px;">&nbsp</div>
</body>
</html>

