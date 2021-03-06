
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link rel="stylesheet"
		href="./kindeditor-master/themes/default/style.css" />

	<link rel="stylesheet" type="text/css" href="css/banner.css" />
	<link rel="stylesheet" type="text/css" href="css/user.css" />
	<link rel="stylesheet" type="text/css" href="css/action.css" />
	<link rel="stylesheet" type="text/css" href="css/dropzone.css" />
	<link rel="stylesheet" type="text/css"
		href="css/jquery.datetimepicker.css" />
	<link rel="stylesheet" type="text/css"
		href="upload/css/webuploader.css">
		<link rel="stylesheet" type="text/css" href="upload/css/diyUpload.css">


			<script src="upload/js/jquery.js"></script>
			<script src="js/dropzone.js" type="text/javascript"></script>

			<script type="text/javascript"
				src="upload/js/webuploader.html5only.min.js"></script>
			<script type="text/javascript" src="upload/js/diyUpload.js"></script>

			<!-- include src files -->
			<script type="text/javascript" charset="utf-8"
				src="ueditor/ueditor.config.js"></script>
			<script type="text/javascript" charset="utf-8"
				src="ueditor/ueditor.all.min.js">
				
			</script>
			<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
			<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
			<script type="text/javascript" charset="utf-8"
				src="ueditor/lang/zh-cn/zh-cn.js"></script>
</head>

<script language="JavaScript">
	jQuery(function($) {
		$(".upload").dropzone({
			url : "adminUploadImage.action?flag=News"
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
<body>
	<form action="adminAddNewsInfo.action" method="post">
		<input name="contentId" value="${msg.infoId }"
			style="visibility:hidden" />

		<!-- 标题 -->
		<div class="name">
			<div class="name-key">内容标题</div>
			<div class="name-enter">
				<c:choose>
					<c:when test="${msg!=null }">
						<input id="title" name="title" class="add-img-input-style"
							type="text" value="${msg.title }" />
					</c:when>
					<c:otherwise>
						<input id="title" name="title" class="add-img-input-style"
							type="text" value="" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- 标签 -->
		<div class="name">
			<div class="name-key">文章来源</div>
			<div class="name-enter">
				<c:choose>
					<c:when test="${msg!=null }">
						<input id="original" name="original" class="add-img-input-style"
							type="text" value="${msg.oringl }" />
					</c:when>
					<c:otherwise>
						<input id="original" name="original" class="add-img-input-style"
							type="text" value="" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- 来源 -->
		<div class="name">
			<div class="name-key">发布时间</div>
			<div class="name-enter">
				<c:choose>
					<c:when test="${msg!=null }">
						<input id="publicDate" name="publicDate"
							class="add-img-input-style" type="text"
							value="${msg.publicDate }" />
					</c:when>
					<c:otherwise>
						<input id="publicDate" name="publicDate"
							class="add-img-input-style" type="text" value="发布时间" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<!-- 链接 -->
		<div class="name">
			<div class="name-key">类型</div>
			<div class="name-enter">
				<select name="type" id="type">
					<c:forEach items="${options }" var="item">
						<option value="${item.key }"
							<c:choose>
					<c:when test="${item.key==msg.webcontenttype.typeId }">
						selected="selected"
					</c:when>
					
				</c:choose>>${item.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<!-- 上传图片 -->
		<div class="name">
			<div class="name-key">
				添加图片(<font style="color:red">*图片地址可选填</font>)
			</div>
			<div class="action-value">
				<c:forEach items="${msg.msgImageses}" var="image">
					<c:choose>
						<c:when test="${image!=null}">
							<div>
								<div class="add-img-input">
									<input class="add-img-input-style" style="color:black"
										name="image" type="text" value=${image.url}>
								</div>

								<div class="add-item-img">
									<img alt="添加活动" src="images/圆角矩形-1-拷贝_50.png">
								</div>
							</div>
							<div>
								<img alt=${msg.title } src=${image.url}>
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<div class="add-img-input">
									<input class="add-img-input-style" name="image" type="text"
										value="请输入 图片地址(选填)">
								</div>
								<div class="add-item-img">
									<img alt="添加活动" src="images/圆角矩形-1-拷贝_50.png">
								</div>
							</div>

						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div class="upload dropzone needsclick"></div>
		</div>

		<!--编辑区域-->
		<div class="editor">
			<c:choose>
				<c:when test="${msg!=null }">
					<c:forEach var="item" items="${msg.msgDetails }">
						<script id="editor" type="text/plain"
							style="width:1024px;height:500px;">${item.content}</script>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<script id="editor" type="text/plain"
						style="width:1024px;height:500px;"></script>
				</c:otherwise>
			</c:choose>

		</div>
		<!--保存网页-->
		<div class="submit-button">
			<div class="submmit-button-item">
				<input id="save-button" type="submit" value="保存">
			</div>

		</div>
	</form>

</body>
<script src="js/jquery.datetimepicker.full.js"></script>
<script>
	$.datetimepicker.setLocale('ch');

	$('#publicDate').datetimepicker({
		lang : 'ch',
		format : 'Y-m-d H:m:s',
		formatDate : 'Y-m-d H:m:s',
		todayButton : true
	});
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor', {
		imagePathFormat : "/upload/uploadImages/{filename}"
	});

	function getContent() {
		var arr = [];
		arr.push("使用editor.getContent()方法可以获得编辑器的内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getContent());
		alert(arr.join("\n"));
	}
</script>
</html>
