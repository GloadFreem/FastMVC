<%@ page language="java" import="java.util.*,com.jinzht.tools.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	Date currentDate = new Date();
	String date = DateUtils.dateToString(currentDate,
			"YYYY-MM-dd HH:mm:ss");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>金指投科技 | Banner管理</title>
<link rel="stylesheet" type="text/css" href="css/cycle.css" />
<link rel="stylesheet" type="text/css" href="css/dropzone.css" />
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<script src="js/dropzone.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(function($) {
		$(".upload").dropzone({
			url : "adminUploadFeelingImage.action?type=feeling"
		});
		$("input:eq(1)").blur(function() {
			$("input:eq(1)").css("background-color", "#D6D6FF");
		});
		$("input:eq(1)").focus(function() {
			$("input:eq(1)").css("background-color", "#FFFFCC");
		});
		$("input:eq(2)").blur(function() {
			$("input:eq(2)").css("background-color", "#D6D6FF");
			if ($("input:eq(2)").val() == "") {
			}
		});
		$("input:eq(2)").focus(function() {
			$("input:eq(2)").css("background-color", "#FFFFCC");
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
						url : "adminSearchUserByName.action",
						data : {
							"name" : $("input[name='name']").val(),
						},
						success : function(data) {
							selector = $("select[name='authId']");
							selector.empty();
							selector.append("<option value='-1'>全部</option>");
							data.data.forEach(function(e) {
								select = "<option value='"+e.authId+"'>"
										+ e.name + "</option>"
								selector.append(select);
							});

						}
					});

				});

		$("#user").change(
				function() {
					$("input[name='authId']").val(
							$(this).find("option:selected").text());
				});
	});
</script>
</head>
<body>
	<div class="content">
		<form action="adminAddPush.action" method="post">
			<!-- 序号 -->
			<c:choose>
				<c:when test="${message!=null}">
					<input style="color:black;visibility:hidden;" name="messageId"
						type="text" value=${message.messageId}>
				</c:when>
				<c:otherwise>
					<input style="visibility:hidden;" name="messageId" type="text"
						value="-1">
				</c:otherwise>
			</c:choose>
			<!-- 名称 -->
			<div class="name">
				<div class="name-key">用户姓名</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${message!=null}">
							<div class="search">
								<input style="color:black" name="name" type="text"
									value=${message.users.name}>
							</div>
							<div>
								<img name="search-img" class="search-img" alt=""
									src="../images/feeling/椭圆-2.png">
							</div>
							<div>
								<select class='user-select' name='authId' id='authId'>
									<option value="-1">全部</option>
								</select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="search">
								<input style="color:black" id="name" name="name" type="text"
									placeholder="请选择用户">
							</div>
							<div>
								<img name="search-img" class="search-img" alt=""
									src="../images/feeling/椭圆-2.png">
							</div>
							<div>
								<select class='user-select' name='authId' id='authId'>
									<option value="-1">全部</option>
								</select>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 标题 -->
			<div class="name">
				<div class="name-key">推送标题</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${message!=null}">
							<input style="color:black" id="title" name="title"
								value="" type="text">
						</c:when>
						<c:otherwise>
							<input style="color:black" id="title" name="title"
								placeholder="请输入推送标题" type="text">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 描述 -->
			<div class="name">
				<div class="name-key">推送链接</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${message!=null}">
							<textarea class="textarea" style="color:black;height:70px;"
								name="content">${message.content}</textarea>
						</c:when>
						<c:otherwise>
							<textarea class="textarea" style="color:black;height:70px;"
								name="content" placeholder="请输入推送链接"></textarea>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- WebView标题 -->
			<div class="name">
				<div class="name-key">短标题</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${message!=null}">
							<input style="color:black" id="shareTitle" name="shareTitle"
								value="" type="text">
						</c:when>
						<c:otherwise>
							<input style="color:black" id="shareTitle" name="shareTitle"
								placeholder="请输入标题" type="text">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 分享图片 -->
			<div class="name">
				<div class="name-key">图片</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${message!=null}">
							<input style="color:black" id="shareImage" name="shareImage"
								value="" type="text">
						</c:when>
						<c:otherwise>
							<input style="color:black" id="shareImage" name="shareImage"
								placeholder="请输入分享图片地址" type="text">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 链接 -->
			<div class="name">
				<div class="name-key">分享链接</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${message!=null}">
							<input style="color:black" id="shareUrl" name="shareUrl"
								value="" type="text">
						</c:when>
						<c:otherwise>
							<input style="color:black" id="shareUrl" name="shareUrl"
								placeholder="请输入分享链接" type="text">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 分享 -->
			<div class="name">
				<div class="name-key">分享简介</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${message!=null}">
							<textarea class="textarea" style="color:black;" name="shareIntroduce"></textarea>
						</c:when>
						<c:otherwise>
							<textarea class="textarea" style="color:black;" name="shareIntroduce"
								placeholder="请输入内容简介"></textarea>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 时间 -->
			<div class="name">
				<div class="name-key">推送时间</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${content!=null}">
							<input style="color:black" id="publicDate" name="publicDate"
								value="${content.publicDate }" type="text">
						</c:when>
						<c:otherwise>
							<input style="color:black" id="publicDate" name="publicDate"
								value="<%=date%>" type="text">
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div>
				<input class="banner-add-submit" type="submit" value="立即推送">
			</div>
		</form>
	</div>
</body>

<script src="js/jquery.js"></script>
<script src="js/jquery.datetimepicker.full.js"></script>
<script>
	$.datetimepicker.setLocale('ch');

	$('#publicDate').datetimepicker({
		lang : 'ch',
		format : 'Y-m-d h:m:s',
		formatDate : 'Y-m-d h:m:s',
		todayButton : false
	});
</script>
</html>