<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String code = request.getAttribute("inviteCode").toString();

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
<script type="text/javascript">
jQuery(function($) { //或者$(function(){...});
	var str ="http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro";
	$(".download").click(function(){
		location.href=str;
	});
});
</script>
<style>
</style>
</head>
<body class="d-body" style="overflow-x: hidden;">
	<div class="aboutus">
		<div>
			<img src="./images/share/关于我们.png" style="width:30%"></img>
		</div>
		<div>
			<p>金指投（KingCapital）专注于成长型企业股权投融资，
				主要通过领投+跟投模式帮助成长型企业进行股权投融资服务；让企业价值最大化，让价值企业融资便捷化。</p>
		</div>
		<div>
			<img src="./images/share/组-6.png"></img>
		</div>
		<div>
			<p>金指投（KingCapital）线上发布金日投条、投资建议、投资学院，为投资人把好投资第一关；
				线下尽职调查、投资模拟、投资实战、投资俱乐部、投融资对接会等一系列活动，带领投资人完成实操实战。</p>
		</div>
		<div>
			<img src="./images/share/投融资-对接会-_看图王.png"></img>
		</div>
		<div>
			<p>目前，金指投KingCapital已经完成近十个千万级项目股权融资，
				其中杰邦科技（836842）、国联质检（837554）、神木药业（832040）已经成功挂牌新三板并实现投资收益，多数项目已进入新三板的各阶段</p>
		</div>
	</div>
	<div class="develophis">
		<div class="develophis-img">
			<img src="./images/share/发展历程.png" style="width:30%"></img>
		</div>
		<div class="develophis-tree">
			<img src="./images/share/组-8.png"></img>
			<div class="text1">
				<p>2016年5月金指投服务企业，杰邦科技国联质检迎来新三板挂牌敲钟</p>
			</div>
			<div class="text2">
				<p>2015年12月金指投平台成功获得，鼎鑫资本众合创投，天使轮投资，投资额500万元</p>
			</div>
			<div class="text3">
				<p>2015年12月金指投平台7家项目完成千万级融资</p>
			</div>
			<div class="text4">
				<p>2015年10月 金指投APP2.0版成功上线</p>
			</div>
			<div class="text5">
				<p>2015年8月 金指投平台第一个项目，国联质检完成股权融资，融资金额为419万元</p>
			</div>
			<div class="text6">
				<p>
					2015年4月<br>金指投团队建立，研发启动
				</p>
			</div>
			<div class="foot">
				<img src="./images/share/组-3.png" style="width:100%"></img>
			</div>
		</div>
		<div class="download">
			<div>
				<img src="./images/share/金指投logo111_99.png">
			</div>
			</img>
		</div>
	</div>
	<div class="invite">
		<div>
			<img class="invite-img" alt="bg" src="./images/share/矩形-2-拷贝-2.png">
		</div>
		<div class="invite-header">
			<img class="invite-header-img" alt="bg"
				src="./images/share/Avatar-sample-311.png">
		</div>
		<div class="invite-code"><%=code %></div>
		<div class="invite-qr-code">
			<img class="invite-qr-code-img" alt="bg"
				src="./images/share/金指投二维码.png">
		</div>
	</div>

</body>
</html>