
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
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="initial-scale=1, user-scalable=no, width=device-width">
<link rel="stylesheet" type="text/css" href="./css/base.css"  media="all and (min-width:1200px)">
<link rel="stylesheet" type="text/css" href="./css/base_phone.css" media="all and (max-width:1199px)">
<link rel="stylesheet" type="text/css" href="./css/main.css" media="all and (min-width:1200px)">
<link rel="stylesheet" type="text/css" href="./css/main_phone.css" media="all and (max-width:1199px)">
<link rel="stylesheet" type="text/css" href="./css/project.css" media="all and (min-width:1200px)">
<link rel="stylesheet" type="text/css" href="./css/project_phone.css" media="all and (max-width:400px)">
<link rel="stylesheet" type="text/css" href="./css/project_phone_big.css" media="all and (min-width:401px) and (max-width:600px) ">
<link rel="stylesheet" type="text/css" href="./css/project_pad.css" media="all and (min-width:601px) and (max-width:1199px) ">
<title>金指投--项目展示</title>
</head>
<body>
	<div class="page">

		<!--顶部导航-->
		<div class="header">
			<div class="content">
				<div class="h-logo"></div>
				<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro#opened"  class="kehuduan">客户端</a>
				<ul class="h-index">
							  <li id="id_main" class="nomarl">创投资讯</li>
                <li id="id_report" class="nomarl">观点报告</li>
                <li id="id_project" class="check">项目展示</li>
					<div class="index-bar-2"></div>
				</ul>
				<div class="h-right">
					<div class="search">搜索</div>
					<div class="down">下载APP</div>
					<div class="text">
						<a href="../user/Login.html">登录</a> | <a href="../user/Login.html">注册</a>
					</div>
				</div>
			</div>
		</div>

		<div class="content-bg-top"></div>


		<!--内容区域;最小1200px;-->
	<div class="middle gray-bg">
			
		<div class="content  c-top  padding"   >
		
						<c:forEach var="type"  items="${TypeList}"  >
							     <c:if test="${type.cKey=='rong'}">
									<div class="c-t-list">
										<div class="t-fo">${type.cName}</div>
									
										<div class="l-t-list" >
											<a class="checked" href="javascript:void(0);"
											>不限</a> 
										</div>
									</c:if>
									<c:if test="${type.cKey=='type'}">
										<div class="c-t-list">
		
											<div class="t-fo">${type.cName}</div>
											<div class="l-t-list" id="check_money">
												<a class="checked" href="javascript:void(0);"
												onclick="checkType(0,0)">不限</a> 
												<c:forEach var="item"  items="${type.cData}"  varStatus="a">
															<a class="normal" href="javascript:void(0);"
													onclick="checkType(${a.count},${item.itemKey})">${item.value}</a> 
												</c:forEach>
											</div>
									</c:if>
									<c:if test="${type.cKey=='range'}">
											<div class="c-t-list">
												<div class="t-fo">${type.cName}</div>
												<div class="l-t-list" id="check_range">
													<a class="checked" href="javascript:void(0);"
													onclick="checkRange(0,0)">不限</a> 
													<c:forEach var="item"  items="${type.cData}"  varStatus="a">
																<a class="normal" href="javascript:void(0);"
														onclick="checkRange(${a.count},${item.itemKey})">${item.value}</a> 
													</c:forEach>
											</div>
									</c:if>
									<c:if test="${type.cKey=='address'}">
											<div class="c-t-list" style="border:none;">
												<div class="t-fo">${type.cName}</div>
												<div class="l-t-list" id="check_address">
													<a class="checked" href="javascript:void(0);"
													onclick="checkAddress(0,0)">不限</a> 
													<c:forEach var="item"  items="${type.cData}"  varStatus="a">
																<a class="normal" href="javascript:void(0);"
														onclick="checkAddress(${a.count},${item.itemKey})">${item.value}</a> 
													</c:forEach>
											</div>
									</c:if>
									
									
								
								</div>
						</c:forEach>
		
			</div>


			<!--正文-->
			<div class="content c-top-s ">
				<!-- 数据集合 start -->

				<div class="card-project" id="id_cards">
			
				</div>
				
				  <div class="more" >
                <div class="tcdPageCode">
                    <!--<div class="span">123</div>-->
                </div>
                <!--<div class="span">123</div>-->


            </div>
				<!--<div style="clear: both"></div>-->

			</div>
			<!--<div style="clear: both"></div>-->
		</div>


		<!--//底部-->
		<div class="footer">
			<div class="content">
				<div class="top">
					<a href="about.action"  class="left m-left-0">关于我们</a>
					<a href="address.action"  class="left m-left">联系我们</a>
					<a href="service.action"  class="left m-left">服务协议</a>
					<a href="policy.action"  class="left m-left">免责声明</a>
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
			<div class="bot">京ICP备15043593号 &copy;  2015-2016 金指投 版权所有</div>
		</div>
	</div>
	

	<div class="phone_btn2" style="display: none;" id="id_phone_btn">
       <div class="p-content">
		<div class="  c-top  padding"   >
						<c:forEach var="type"  items="${TypeList}"  >
							     <c:if test="${type.cKey=='rong'}">
									<div class="p-c-t-list">
										<div class="t-fo">${type.cName}</div>
									
										<div class="l-t-list" >
											<a class="checked" href="javascript:void(0);"
											>不限</a> 
										</div>
									</c:if>
									<c:if test="${type.cKey=='type'}">
										<div class="p-c-t-list">
		
											<div class="t-fo">${type.cName}</div>
											<div class="l-t-list" id="check_money2">
												<a class="checked" href="javascript:void(0);"
												onclick="checkType2(0,0)">不限</a> 
												<c:forEach var="item"  items="${type.cData}"  varStatus="a">
															<a class="normal" href="javascript:void(0);"
													onclick="checkType2(${a.count},${item.itemKey})">${item.value}</a> 
												</c:forEach>
											</div>
									</c:if>
									<c:if test="${type.cKey=='range'}">
											<div class="p-c-t-list">
												<div class="t-fo">${type.cName}</div>
												<div class="l-t-list" id="check_range2">
													<a class="checked" href="javascript:void(0);"
													onclick="checkRange2(0,0)">不限</a> 
													<c:forEach var="item"  items="${type.cData}"  varStatus="a">
																<a class="normal" href="javascript:void(0);"
														onclick="checkRange2(${a.count},${item.itemKey})">${item.value}</a> 
													</c:forEach>
											</div>
									</c:if>
									<c:if test="${type.cKey=='address'}">
											<div class="p-c-t-list" style="border:none;">
												<div class="t-fo">${type.cName}</div>
												<div class="l-t-list" id="check_address2">
													<a class="checked" href="javascript:void(0);"
													onclick="checkAddress2(0,0)">不限</a> 
													<c:forEach var="item"  items="${type.cData}"  varStatus="a">
																<a class="normal" href="javascript:void(0);"
														onclick="checkAddress2(${a.count},${item.itemKey})">${item.value}</a> 
													</c:forEach>
											</div>
									</c:if>
								</div>
						</c:forEach>
			</div>
			<div style="clear:both;"></div>
			<div  class="p-btn"  >筛选</div>
			
			</div>
	</div>
	<div  class="phone_btn2" id="phont_btn">项目<br>筛选</div>
	<script type="text/javascript" src=".//js/jquery1.8.min.js"
		charset="utf-8"></script>
					<script type="text/javascript" src=".//js/config.js" charset="utf-8"></script>
	<script type="text/javascript" src=".//js/jquery.page.js" charset="utf-8"></script>
	<script type="text/javascript" src=".//js/project.js" charset="utf-8"></script>

</body>
</html>