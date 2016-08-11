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
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="js/dropzone.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(function($) {
		$(".upload").dropzone({
			url : "adminUploadImage.action?type=project"
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
	<div class="content" style="margin-bottom:200px;">
		<form action="adminAddProject.action" method="post">
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
				<div class="name-key">公司简称</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${project!=null}">
							<input style="color:black" name="abbrevName" type="text"
								value=${project.abbrevName}>
						</c:when>
						<c:otherwise>
							<input name="abbrevName" type="text" value="请输入公司简称">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 名称 -->
			<div class="name">
				<div class="name-key">公司全称</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${project!=null}">
							<input style="color:black" name="fullName" type="text"
								value=${project.fullName}>
						</c:when>
						<c:otherwise>
							<input name="fullName" type="text" value="请输入公司全称">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 描述 -->
			<div class="name">
				<div class="name-key">地址</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${project!=null}">
							<input style="color:black" name="address" type="text"
								value=${project.address}>
						</c:when>
						<c:otherwise>
							<input name="address" type="text" value="请输入公司地址">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 密码 -->
			<div class="name">
				<div class="name-key">行业类型</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${project!=null}">
							<input style="color:black" name="industory" type="text"
								value=${project.industoryType}>
						</c:when>
						<c:otherwise>
							<input name="industory" type="text" value="请输入行业类型">
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<!--  介绍-->
			<div class="name">
				<div class="name-key">项目介绍</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${project!=null}">
							<textarea class="textarea" name="introduce">
									${project.description}
								</textarea>
						</c:when>
						<c:otherwise>
							<textarea class="textarea" name="introduce"></textarea>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<!-- 上传图片 -->
			<div class="name">
				<div class="name-key">
					上传项目封面图片(<font style="color:red">*图片地址可选填</font>)
				</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${project!=null}">
							<div>
								<input style="color:black" name="image" type="text"
									value=${project.startPageImage}>
							</div>
							<div>
								<img alt=${project.fullName } src=${project.startPageImage}>
							</div>

						</c:when>
						<c:otherwise>
							<input name="image" type="text" value="请输入 头像图片地址(选填)">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="upload dropzone needsclick"></div>
			</div>

			<!-- 设备类型 -->
			<div class="name">
				<div class="name-key">借款人</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${project!=null}">
							<c:choose>
								<c:when test="${project!=null  }">
									<select name="user" id="user">
										<option value="0">请选择借款人</option>
									</select>
								</c:when>
								<c:otherwise>
									<select name="user" id="user">
										<option value="0">请选择借款人</option>
									</select>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<select name="platform" id="platform">
								<option value="0" selected="selected">请选择借款人</option>
							</select>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<!-- 融资状态 -->
			<div class="name">
				<div class="name-key">融资状态</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${project!=null}">
							<select name="financestatus" id="financestatus">
								<c:forEach items="${status}" var="s" varStatus="v">
									<option value=${s.statusId }
										<c:choose>
											<c:when test="${s.statusId==project.financestatus.statusId}">
												selected='selected'
											</c:when>
										</c:choose>>${s.name}</option>
								</c:forEach>
							</select>
						</c:when>
						<c:otherwise>
							<select name="financestatus" id="financestatus">
								<c:forEach items="${status}" var="s" varStatus="v">
									<option value=${s.statusId }>${s.name}</option>
								</c:forEach>
							</select>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<!-- 融资计划 -->
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
											<c:choose>
												<c:when test="${show!=null}">
													<input style="color:black" name="total" type="text"
														value="${show.roadshowplan.financeTotal}万">
												</c:when>
												<c:otherwise>
													<input style="color:black" name="total" type="text"
														value="请输入融资总额">
												</c:otherwise>
											</c:choose>

										</div>
									</div>
									<!--  已融金额 -->
									<div class="name">
										<div class="name-key">已融金额</div>
										<div class="name-value">
											<c:choose>
												<c:when test="${show!=null}">
													<input style="color:black" name="financed" type="text"
														value="${show.roadshowplan.financedMount}万">
												</c:when>
												<c:otherwise>
													<input style="color:black" name="financed" type="text"
														value="请输入已融金额">
												</c:otherwise>
											</c:choose>

										</div>
									</div>
									<!--  最低融资金额 -->
									<div class="name">
										<div class="name-key">最低融资额度</div>
										<div class="name-value">
											<c:choose>
												<c:when test="${show!=null}">
													<input style="color:black" name="limit" type="text"
														value="${show.roadshowplan.limitAmount}万">
												</c:when>
												<c:otherwise>
													<input style="color:black" name="limit" type="text"
														value="请输入最低融资额度">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<!--  分成比例 -->
									<div class="name">
										<div class="name-key">分成比例</div>
										<div class="name-value">
											<c:choose>
												<c:when test="${show!=null}">
													<input style="color:black" name="profit" type="text"
														value="${show.roadshowplan.profit}">
												</c:when>
												<c:otherwise>
													<input style="color:black" name="profit" type="text"
														value="请输入分成比例">
												</c:otherwise>
											</c:choose>
										</div>
									</div>


									<!--  开始时间 -->
									<div class="name">
										<div class="name-key">开始时间</div>
										<div class="name-value">

											<c:choose>
												<c:when test="${show!=null}">
													<input style="color:black" id="beginTime" name="beginTime"
														type="text" value="${show.roadshowplan.beginDate}">
												</c:when>
												<c:otherwise>
													<input style="color:black" id="beginTime" name="beginTime"
														type="text" value="请输入开始时间">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<!--  结束时间 -->
									<div class="name">
										<div class="name-key">结束时间</div>
										<div class="name-value">
											<c:choose>
												<c:when test="${show!=null}">
													<input style="color:black" id="endTime" name="endTime"
														type="text" value="${show.roadshowplan.endDate}">
												</c:when>
												<c:otherwise>
													<input style="color:black" id="endTime" name="endTime"
														type="text" value="请输入结束时间">
												</c:otherwise>
											</c:choose>
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
											<input style="color:black" id="beginTime" name="beginTime"
												type="text" value="请输入开始时间">
										</div>
									</div>
									<!--  结束时间 -->
									<div class="name">
										<div class="name-key">结束时间</div>
										<div class="name-value">
											<input style="color:black" id="endTime" name="endTime"
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