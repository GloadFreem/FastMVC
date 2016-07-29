<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String inviteCode = request.getAttribute("inviteCode").toString();
	if(inviteCode==null)
	{
		inviteCode="";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>【金指投投融资】--邀请送金条</title>
<link rel="stylesheet" type="text/css"
	href="./images/share/css/style.css">
<script type="text/javascript" src="./images/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="./images/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="./images/clipboard.min.js"></script>
<script type="text/javascript">
jQuery(function($) { //或者$(function(){...});
	var str ="http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro";
	$(".invite-download-img").click(function(){
		location.href=str;
	});
});
</script>
<style>
</style>
</head>
<body class="d-body body-background"
	style="overflow-x: hidden; background-color:#ffb900">
	<div class="gold">
		<div>
			<img class="invite-img" alt="bg" src="./images/share/形状-13.png">
		</div>
		<div class="gold-header">
			<img class="gold-header-img1" alt="bg" src="./images/share/形状-1.png">
		</div>
		<div class="gold-code">
			<img class="gold-header-img2" alt="bg" src="./images/share/组-11.png">
		</div>
		<div class="gold-code1">
			<img class="gold-header-img3" alt="bg" src="./images/share/lihe.png">
		</div>
		<div class="gold-qr-code">
			<img class="gold-qr-code-img" alt="bg" src="./images/share/指环码.png">
		</div>
		<div class="king-code">
			<div class="king-code-text"><%=inviteCode%></div>
			<div class="king-code-img-wrapper">
				<img class="gold-qr-code-img" alt="bg" src="./images/share/copy.png">
			</div>

		</div>
		<!-- 
		<button class="king-code-img-btn" data-clipboard-action="copy"
				data-clipboard-target="div"></button> -->

		<script>
    	var clipboard = new Clipboard('.king-code-img-wrapper', {
        	text: function() {
            	return $(".king-code-text").text();
        	}
    	});

    	clipboard.on('success', function(e) {
        	console.log(e);
    	});

    	clipboard.on('error', function(e) {
        	console.log(e);
    	});
        </script>

		<div class="rule">
			<div class="rule-item">1.下载并安装金指投APP</div>
			<div class="rule-item">2.填写好友的指环码完成注册，即可获得金条，您的好友也可以获得一定的金条</div>
			<div class="rule-item">3.一根金条的价值等于一元人民币，可在商场进行消费，详见《金条使用规则》</div>
		</div>
		<div class="er-code">
			<div class="er-code-img">
				<img class="king-code-img" alt="bg"
					src="./images/share/2015-09-02-1530101789.png">
			</div>
			<div class="er-code-img-text">扫描下载并安装金指投APP</div>
		</div>
	</div>

	<div class="gold-download">
		<img class="invite-download-img" alt="bg"
			src="./images/share/立即领取.png">
	</div>
</body>
</html>