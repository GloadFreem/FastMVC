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
			url : "adminUploadImage.action"
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
							"name" : $("input[name='name']").val(),
						},
						success : function(data) {
							selector = $("select[name='projectId']");
							selector.empty();

							data.data.forEach(function(e) {
								select = "<option value='"+e.projectId+"'>"
										+ e.name + "</option>"
								selector.append(select);
							});

						}
					});

				});

		$("#user").change(
				function() {
					$("input[name='projectId']").val(
							$(this).find("option:selected").text());
				});

	});
</script>
</head>
<body>
	<div class="content">
		<form action="adminAddproject.action" method="post">
			<!-- 序号 -->
			<c:choose>
				<c:when test="${project!=null}">
					<input style="color:black;visibility:hidden;" name="projectId"
						type="text" value=${project.projectId}>
				</c:when>
				<c:otherwise>
					<input style="visibility:hidden;" name="projectId" type="text"
						value="-1">
				</c:otherwise>
			</c:choose>
			<!-- 名称 -->
			<div class="name">
				<div class="name-key">所属项目</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${project!=null}">
							<div class="search">
								<input style="color:black;width:95%" name="name" type="text"
									value=${content.users.name}>
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
								<input style="color:black;width:95%" id="name" name="name"
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


			<div class="name">
				<div class="name-key">融资计划</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${project!=null}">
							<div class="authinfo">
								<div class="authinfo-item">
									<!--  融资计划 -->
									<div class="name">
										<div class="name-key">融资总额</div>
										<div class="name-value">
											<input style="color:black" name="realName" type="text"
												value="请输入融资总额">
										</div>
									</div>
									<!--  已融金额 -->
									<div class="name">
										<div class="name-key">已融金额</div>
										<div class="name-value">
											<input style="color:black" name="realName" type="text"
												value="请输入已融金额">
										</div>
									</div>
									<!--  最低融资金额 -->
									<div class="name">
										<div class="name-key">最低融资额度</div>
										<div class="name-value">
											<input style="color:black" name="realName" type="text"
												value="请输入最低融资额度">
										</div>
									</div>
									<!--  分成比例 -->
									<div class="name">
										<div class="name-key">分成比例</div>
										<div class="name-value">
											<input style="color:black" name="realName" type="text"
												value="请输入分成比例">
										</div>
									</div>


									<!--  开始时间 -->
									<div class="name">
										<div class="name-key">开始时间</div>
										<div class="name-value">
											<input style="color:black" name="identityCardNo" type="text"
												value="请输入开始时间">
										</div>
									</div>
									<!--  结束时间 -->
									<div class="name">
										<div class="name-key">结束时间</div>
										<div class="name-value">
											<input style="color:black" name="identityCardNo" type="text"
												value="请输入结束时间">
										</div>
									</div>
								</div>
						</c:when>
						<c:otherwise>
							<div class="authinfo">
								<div class="authinfo-item">
									<!--  融资计划 -->
									<div class="name">
										<div class="name-key">融资总额</div>
										<div class="name-value">
											<input style="color:black" name="realName" type="text"
												value="请输入融资总额">
										</div>
									</div>
									<!--  已融金额 -->
									<div class="name">
										<div class="name-key">已融金额</div>
										<div class="name-value">
											<input style="color:black" name="realName" type="text"
												value="请输入已融金额">
										</div>
									</div>
									<!--  最低融资金额 -->
									<div class="name">
										<div class="name-key">最低融资额度</div>
										<div class="name-value">
											<input style="color:black" name="realName" type="text"
												value="请输入最低融资额度">
										</div>
									</div>
									<!--  分成比例 -->
									<div class="name">
										<div class="name-key">分成比例</div>
										<div class="name-value">
											<input style="color:black" name="realName" type="text"
												value="请输入分成比例">
										</div>
									</div>


									<!--  开始时间 -->
									<div class="name">
										<div class="name-key">开始时间</div>
										<div class="name-value">
											<input style="color:black" name="identityCardNo" type="text"
												value="请输入开始时间">
										</div>
									</div>
									<!--  结束时间 -->
									<div class="name">
										<div class="name-key">结束时间</div>
										<div class="name-value">
											<input style="color:black" name="identityCardNo" type="text"
												value="请输入结束时间">
										</div>
									</div>
								</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
	</div>
	<div>
		<c:choose>
			<c:when test="${project!=null}">
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