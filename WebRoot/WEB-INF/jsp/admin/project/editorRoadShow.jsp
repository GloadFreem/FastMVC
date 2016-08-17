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
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<script type="text/javascript">
	jQuery(function($) {
		$(".upload").dropzone({
			url : "adminUploadImage.action?flag=images"
		});
		$("input:eq(2)").blur(function() {
			$("input:eq(2)").css("background-color", "#D6D6FF");
			if ($("input:eq(2)").val() == "") {
				$("input:eq(2)").val("请输入融资总额");
			}
		});
		$("input:eq(2)").focus(function() {
			$("input:eq(2)").css("background-color", "#FFFFCC");
			if ($("input:eq(2)").val() == "请输入融资总额") {
				$("input:eq(2)").val("");
			}
		});
		$("input:eq(3)").blur(function() {
			$("input:eq(3)").css("background-color", "#D6D6FF");
			if ($("input:eq(3)").val() == "") {
				$("input:eq(3)").val("请输入已融金额");
			}
		});
		$("input:eq(3)").focus(function() {
			$("input:eq(3)").css("background-color", "#FFFFCC");
			if ($("input:eq(3)").val() == "请输入已融金额") {
				$("input:eq(3)").val("");
			}
		});
		$("input:eq(4)").blur(function() {
			$("input:eq(4)").css("background-color", "#D6D6FF");
			if ($("input:eq(4)").val() == "") {
				$("input:eq(4)").val("请输入最低融资额度");
			}
		});
		$("input:eq(4)").focus(function() {
			$("input:eq(4)").css("background-color", "#FFFFCC");
			if ($("input:eq(4)").val() == "请输入最低融资额度") {
				$("input:eq(4)").val("");
			}
		});
		$("input:eq(5)").blur(function() {
			$("input:eq(5)").css("background-color", "#D6D6FF");
			if ($("input:eq(5)").val() == "") {
				$("input:eq(5)").val("请输入分成比例");
			}
		});
		$("input:eq(5)").focus(function() {
			$("input:eq(5)").css("background-color", "#FFFFCC");
			if ($("input:eq(5)").val() == "请输入分成比例") {
				$("input:eq(5)").val("");
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
										+ e.fullName + "</option>"
								selector.append(select);
							});

						}
					});

				});

		$("#projectId").change(
				function() {
					$("input[name='name']").val(
							$(this).find("option:selected").text());
				});

	});
</script>
</head>
<body>
	<div class="content">
		<form action="adminAddRoadShow.action" method="post" style="margin-bottom: 150px;">
			<!-- 序号 -->
			<c:choose>
				<c:when test="${roadshow!=null}">
					<input style="color:black;visibility:hidden;" name="roadShowId"
						type="text" value=${roadshow.roadShowId}>
				</c:when>
				<c:otherwise>
					<input style="visibility:hidden;" name="roadShowId" type="text"
						value="-1">
				</c:otherwise>
			</c:choose>
			<!-- 名称 -->
			<div class="name">
				<div class="name-key">所属项目</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${roadshow!=null}">
							<div class="search">
								<input style="color:black;width:95%" name="name" type="text"
									value=${roadshow.project.fullName}>
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
						<c:when test="${roadshow!=null}">
							<div class="authinfo">
								<div class="authinfo-item">
									<!--  融资计划 -->
									<div class="name">
										<div class="name-key">融资总额</div>
										<div class="name-value">
											<input style="color:black" name="total" type="text"
												value="${roadshow.roadshowplan.financeTotal }万">
										</div>
									</div>
									<!--  已融金额 -->
									<div class="name">
										<div class="name-key">已融金额</div>
										<div class="name-value">
											<input style="color:black" name="financed" type="text"
												value="${roadshow.roadshowplan.financedMount }万">
										</div>
									</div>
									<!--  最低融资金额 -->
									<div class="name">
										<div class="name-key">最低融资额度</div>
										<div class="name-value">
											<input style="color:black" name="limit" type="text"
												value="${roadshow.roadshowplan.limitAmount }万">
										</div>
									</div>
									<!--  分成比例 -->
									<div class="name">
										<div class="name-key">分成比例</div>
										<div class="name-value">
											<input style="color:black" name="profit" type="text"
												value="${roadshow.roadshowplan.profit}">
										</div>
									</div>


									<!--  开始时间 -->
									<div class="name">
										<div class="name-key">开始时间</div>
										<div class="name-value">
											<input style="color:black" name="beginTime" id="beginTime" type="text"
												value="${roadshow.roadshowplan.beginDate }">
										</div>
									</div>
									<!--  结束时间 -->
									<div class="name">
										<div class="name-key">结束时间</div>
										<div class="name-value">
											<input style="color:black" name="endTime" id="endTime" type="text"
												value="${roadshow.roadshowplan.endDate }">
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
											<input style="color:black" name="total" type="text"
												value="请输入融资总额">
										</div>
									</div>
									<!--  已融金额 -->
									<div class="name">
										<div class="name-key">已融金额</div>
										<div class="name-value">
											<input style="color:black" name="financed" type="text"
												value="请输入已融金额">
										</div>
									</div>
									<!--  最低融资金额 -->
									<div class="name">
										<div class="name-key">最低融资额度</div>
										<div class="name-value">
											<input style="color:black" name="limit" type="text"
												value="请输入最低融资额度">
										</div>
									</div>
									<!--  分成比例 -->
									<div class="name">
										<div class="name-key">分成比例</div>
										<div class="name-value">
											<input style="color:black" name="profit" type="text"
												value="请输入分成比例">
										</div>
									</div>


									<!--  开始时间 -->
									<div class="name">
										<div class="name-key">开始时间</div>
										<div class="name-value">
											<input style="color:black" name="beginTime" id="beginTime"
												type="text" value="请输入开始时间">
										</div>
									</div>
									<!--  结束时间 -->
									<div class="name">
										<div class="name-key">结束时间</div>
										<div class="name-value">
											<input style="color:black" name="endTime" id="endTime"
												type="text" value="请输入结束时间">
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
<script src="js/jquery.js"></script>
<script src="js/jquery.datetimepicker.full.js"></script>
<script>
	$.datetimepicker.setLocale('ch');

	$('#beginTime').datetimepicker({
		lang : 'ch',
		format : 'Y-m-d h:m:s',
		formatDate : 'Y-m-d h:m:s',
		todayButton : true
	});
	$('#endTime').datetimepicker({
		lang : 'ch',
		format : 'Y-m-d h:m:s',
		formatDate : 'Y-m-d h:m:s',
		todayButton : true
	});
</script>
</html>