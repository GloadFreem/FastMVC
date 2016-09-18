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
<link rel="stylesheet" type="text/css" href="css/user.css" />
<link rel="stylesheet" type="text/css" href="css/dropzone.css" />
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="js/dropzone.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(function($) {
		$(".upload").dropzone({
			url : "adminUploadImage.action?flag=header"
		});
		$(".ida").dropzone({
			url : "adminUploadImage.action?flag=idA"
		});
		$(".idb").dropzone({
			url : "adminUploadImage.action?flag=idB"
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

		$(".search-img").click(
				function() {
					$.ajax({
						url : "adminSearchProjectByName.action",
						data : {
							"name" : $("input[name='companyName']").val(),
						},
						success : function(data) {
							selector = $("select[name='projectId']");
							selector.empty();

							data.data.forEach(function(e) {
								select = "<option value='"+e.projectId+"'>"
										+ e.fullName + "</option>"
								selector.append(select);
							});

						}
					});

				});

		$("#projectId").change(
				function() {
					$("input[name='companyName']").val(
							$(this).find("option:selected").text());
				});
	});
</script>
</head>
<body>
	<div class="content">
		<form action="adminAddTeam.action" method="post">
			<!-- 序号 -->
			<c:choose>
				<c:when test="${member!=null}">
					<input style="color:black;visibility:hidden;" name="personId"
						type="text" value=${member.personId}>
				</c:when>
				<c:otherwise>
					<input style="visibility:hidden;" name="personId" type="text"
						value="-1">
				</c:otherwise>
			</c:choose>
			<!-- 名称 -->
			<div class="name">
				<div class="name-key">所属项目</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${member!=null}">
							<div class="search">
								<input style="color:black;width:95%" name="companyName" type="text"
									value=${member.project.fullName}>
							</div>
							<div>
								<img name="search-img" class="search-img" alt=""
									src="../images/feeling/椭圆-2.png">
							</div>
							<div>
								<select class='user-select' name='projectId' id='projectId'></select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="search">
								<input style="color:black;width:95%" id="name" name="companyName"
									type="text" value="请选择项目">
							</div>
							<div>
								<img name="search-img" class="search-img" alt=""
									src="../images/feeling/椭圆-2.png">
							</div>
							<div>
								<select class='user-select' name='projectId' id='projectId'></select>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<!-- 名称 -->
			<div class="name">
				<div class="name-key">姓名</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${member!=null}">
							<input style="color:black" name="name" type="text"
								value=${member.name}>
						</c:when>
						<c:otherwise>
							<input name="name" type="text" value="请输入姓名">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 描述 -->
			<div class="name">
				<div class="name-key">公司</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${member!=null}">
							<input style="color:black" name="company" type="text"
								value=${member.company}>
						</c:when>
						<c:otherwise>
							<input name="company" type="text" value="请输入公司">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="name">
				<div class="name-key">地址</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${member!=null}">
							<input style="color:black" name="address" type="text"
								value=${member.address}>
						</c:when>
						<c:otherwise>
							<input name="address" type="text" value="请输入地址">
						</c:otherwise>
					</c:choose>
				</div>
			</div>	<div class="name">
				<div class="name-key">职位</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${member!=null}">
							<input style="color:black" name="position" type="text"
								value=${member.address}>
						</c:when>
						<c:otherwise>
							<input name="position" type="text" value="请输入地址">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 上传图片 -->
			<div class="name">
				<div class="name-key">
					上传头像图片(<font style="color:red">*图片地址可选填</font>)
				</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${member!=null}">
							<div>
								<input style="color:black" name="image" type="text"
									value=${member.icon}>
							</div>
							<div>
								<img alt=${member.icon } src=${member.icon}>
							</div>

						</c:when>
						<c:otherwise>
							<input name="image" type="text" value="请输入 头像图片地址(选填)">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="upload dropzone needsclick"></div>
			</div>

			
	</div>
	</div>
			<div>
		<c:choose>
			<c:when test="${user!=null}">
				<input class="banner-add-submit" type="submit" value="确认更新">

			</c:when>
			<c:otherwise>
				<input class="banner-add-submit" type="submit" value="确认添加">
			</c:otherwise>
		</c:choose>
	</div>
	</form>
	</div>
</body>
</html>