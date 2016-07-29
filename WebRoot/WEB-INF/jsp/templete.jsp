<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	String title = request.getAttribute("title").toString();
	String content = request.getAttribute("content").toString();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="金指投投融资">
<meta http-equiv="description" content="金指投投融资">
<title>【金指投投融资】--<%=title %></title>
<script type="text/javascript" src="./images/jquery-1.8.3.min.js"></script>
<style type="text/css">
img
{
	width:90%;
	margin-left:5%;
}
</style>
</head>

<body>
	<div class="content">
		<%=content %>
	</div>
</body>
</html>
