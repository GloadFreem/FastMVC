<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	Integer contentId = -1;
%>

<!DOCTYPE html>
<html lang="zh" class="app">
<head>
<meta charset="utf-8" />
<link rel="icon" href="images/icon.png" type="image/x-icon" />
<title>金指投 | 后台管理系统</title>
<meta name="description" content="金指投中国成长型企业股权投融资平台" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="css/app.v2.css" type="text/css" />
<link rel="stylesheet" href="css/fileupload/fileinput.css"
	type="text/css" />
<link rel="stylesheet" href="js\calendar/bootstrap_calendar.css"
	type="text/css" cache="false" />
<script src="js/app.v2.js"></script>

<c:forEach items="${style}" var="item">
	<link rel="stylesheet" href="${item }" type="text/css" />
</c:forEach>
<c:forEach items="${js}" var="item">
	<script src="${item}"></script>
</c:forEach>

<!--[if lt IE 9]> <script src="js/ie/html5shiv.js" cache="false"></script> <script src="js/ie/respond.min.js" cache="false"></script> <script src="js/ie/excanvas.js" cache="false"></script> <![endif]-->
</head>
<body>
	<!-- 头部-->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- 底部-->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<script>
	$(function() {
		$('#modal').on(
				'show.bs.modal',
				function(e) {
					$(this).find('#confirm').attr('href',
							$(e.relatedTarget).data('href'));
				})
	});
</script>

