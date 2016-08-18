<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>金指投科技 | Banner管理</title>
<link rel="stylesheet" type="text/css" href="css/banner.css" />
<link rel="stylesheet" type="text/css" href="css/dropzone.css" />
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="js/dropzone.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(function($) {
		$(".upload").dropzone({
			url : "adminUploadImage.action?flag=images"
		});
		$("input:eq(1)").blur(function() {
			$("input:eq(1)").css("background-color", "#D6D6FF");
			if ($("input:eq(1)").val() == "") {
				$("input:eq(1)").val("请输入名称");
			}
		});
		$("input:eq(1)").focus(function() {
			$("input:eq(1)").css("background-color", "#FFFFCC");
			if ($("input:eq(1)").val() == "请输入名称") {
				$("input:eq(1)").val("");
			}
		});
		$("input:eq(2)").blur(function() {
			$("input:eq(2)").css("background-color", "#D6D6FF");
			if ($("input:eq(2)").val() == "") {
				$("input:eq(2)").val("请输入描述");
			}
		});
		$("input:eq(2)").focus(function() {
			$("input:eq(2)").css("background-color", "#FFFFCC");
			if ($("input:eq(2)").val() == "请输入描述") {
				$("input:eq(2)").val("");
			}
		});
		$("input:eq(3)").blur(function() {
			$("input:eq(3)").css("background-color", "#D6D6FF");
			if ($("input:eq(3)").val() == "") {
				$("input:eq(3)").val("请输入链接地址");
			}
		});
		$("input:eq(3)").focus(function() {
			$("input:eq(3)").css("background-color", "#FFFFCC");
			if ($("input:eq(3)").val() == "请输入链接地址") {
				$("input:eq(3)").val("");
			}
		});
		$("input:eq(4)").blur(function() {
			$("input:eq(4)").css("background-color", "#D6D6FF");
			if ($("input:eq(4)").val() == "") {
				$("input:eq(4)").val("请输入 图片地址(选填)");
			}
		});
		$("input:eq(4)").focus(function() {
			$("input:eq(4)").css("background-color", "#FFFFCC");
			if ($("input:eq(4)").val() == "请输入 图片地址(选填)") {
				$("input:eq(4)").val("");
			}
		});
	});
</script>
</head>
<body>
	<div class="content">
		<form action="adminAddBanner.action" method="post">
			<!-- 序号 -->
			<c:choose>
				<c:when test="${banner!=null}">
					<input style="color:black;visibility:hidden;" name="bannerId" type="text"
						value=${banner.bannerId}>
				</c:when>
				<c:otherwise>
					<input style="visibility:hidden;" name="bannerId" type="text" value="-1">
				</c:otherwise>
			</c:choose>
			<!-- 名称 -->
			<div class="name">
				<div class="name-key">名称</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${banner!=null}">
							<input style="color:black" name="name" type="text"
								value=${banner.name}>
						</c:when>
						<c:otherwise>
							<input name="name" type="text" value="请输入名称">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 描述 -->
			<div class="name">
				<div class="name-key">描述</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${banner!=null}">
							<input style="color:black" name="description" type="text"
								value=${banner.description}>
						</c:when>
						<c:otherwise>
							<input name="description" type="text" value="请输入描述">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 链接地址 -->
			<div class="name">
				<div class="name-key">内容链接</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${banner!=null}">
							<input style="color:black" name="url" type="text"
								value=${banner.url}>
						</c:when>
						<c:otherwise>
							<input name="url" type="text" value="请输入链接地址">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 上传图片 -->
			<div class="name">
				<div class="name-key">
					上传图片(<font style="color:red">*图片地址可选填</font>)
				</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${banner!=null}">
							<div>
								<input style="color:black" name="image" type="text"
									value=${banner.image}>
							</div>
							<div>
								<img alt=${banner.name } src=${banner.image}></div>

						</c:when>
						<c:otherwise>
							<input name="image" type="text" value="请输入 图片地址(选填)">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="upload dropzone needsclick"></div>
			</div>

			<div>
				<input class="banner-add-submit" type="submit" value="确认添加">
			</div>
		</form>
	</div>
</body>
</html>