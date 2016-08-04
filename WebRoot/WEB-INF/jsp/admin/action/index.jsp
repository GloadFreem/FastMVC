<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
</head>
<body>
	
	<h3>DateTimePicker</h3>
	<input type="text" value="" id="datetimepicker"/><br><br>
</body>
<script src="js/jquery.js"></script>
<script src="js/jquery.datetimepicker.full.js"></script>
<script>

$.datetimepicker.setLocale('ch');

$('#datetimepicker').datetimepicker({
lang:'ch',
format:'Y-m-d h:m',
formatDate:'Y-m-d h:m',
 todayButton:true
});
</script>
</html>
