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
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>【金指投投融资】--邀请好友</title>
<link rel="stylesheet" type="text/css"
	href="./images/share/css/style.css">
<script type="text/javascript" src="./images/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="./images/jquery.SuperSlide.2.1.1.js"></script>
<style>
</style>
</head>
<body class="d-body" style="overflow-x: hidden;background-color:white">
	<div class="line-separte"></div>
	<div class="foundation-content">
		<div class="foundation-content-name">基金名称:</div>
		<div class="foundation-content-value">华合鼎诚投资基金</div>
	</div>
	<div class="line"></div>
	<div class="foundation-content">
		<div class="foundation-content-name">基金规模:</div>
		<div class="foundation-content-value">3000万元人民币</div>
	</div>
	<div class="line-separte"></div>
	<div class="foundation-body">
		<div class="foundation-header">投资方向</div>
		<div class="line"></div>
		<div>
			<div class="foundation-direct">
				1、新三板拟挂牌企业的股权投融资和已挂牌企业的定向增发；<br />
				2、通过认购非公开发行股票，参与新股发行或者受让股权等方式持有上市公司股份；<br />
				3、收购有增长潜力或有管理改善空间的成熟企业；<br /> 4、即将在主板，中小板及创业板IPO股权项目投资；<br />
				5、与上述投资项目相关的咨询服务；
			</div>
		</div>
		<div class="line"></div>
	</div>
	<div class="foundation-body">
		<div class="foundation-header">投资阶段</div>
		<div class="line"></div>
		<div class="foundation-header-content"></div>
		<div class="line-separte"></div>
	</div>

	<div class="foundation-bottom">
		<div class="bottom-item" style="float:left">
			<div class="bottom-item-wrappeer">
				<div class="bottom-item-wrappeer-bg">
					<img class="bottom-item-wrappeer-bg-img" alt=""
						src="./images/share/圆角矩形-3.png">
				</div>
				<div class="wrapper-left">
					<img class="bottom-item-wrappeer-bg-img" alt=""
						src="./images/share/icon-1460354657038.png">
				</div>
				<div class="wrapper-right">提交项目</div>
			</div>
		</div>
		<div class="bottom-item" style="margin-left: 50%;">
			<div class="bottom-item-wrappeer">
				<div class="bottom-item-wrappeer-bg">
					<img class="bottom-item-wrappeer-bg-img" alt=""
						src="./images/share/圆角矩形-3.png">
				</div>
				<div class="wrapper-left">
					<img class="bottom-item-wrappeer-bg-img" alt=""
						src="./images/share/icon-1460354238081.png">
				</div>
				<div class="wrapper-right">关注</div>
			</div>
		</div>
	</div>
</body>
</html>