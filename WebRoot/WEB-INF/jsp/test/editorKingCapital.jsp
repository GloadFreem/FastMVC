
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
<link rel="stylesheet" type="text/css" href="upload/css/webuploader.css">
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
			url : "adminUploadImage.action?flag=images"
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
	<form action="adminAddKingCapital.action" method="post">
		<input name="contentId" value="${content.recordId }"
			style="visibility:hidden" />

		<!-- 标题 -->
		<div class="name">
			<div class="name-key">文章标题</div>
			<div class="name-enter">
				<c:choose>
					<c:when test="${content!=null }">
						<input id="title" name="title" class="add-img-input-style"
							type="text" value="${content.title }" />
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
			<div class="name-key">文章标签</div>
			<div class="name-enter">
				<c:choose>
					<c:when test="${content!=null }">
						<input id="tag" name="tag" class="add-img-input-style" type="text"
							value="${content.tag }" />
					</c:when>
					<c:otherwise>
						<input id="tag" name="tag" class="add-img-input-style" type="text"
							value="" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- 来源 -->
		<div class="name">
			<div class="name-key">文章来源</div>
			<div class="name-enter">
				<c:choose>
					<c:when test="${content!=null }">
						<input id="orignal" name="orignal" class="add-img-input-style"
							type="text" value="${content.orignal }" />
					</c:when>
					<c:otherwise>
						<input id="orignal" name="orignal" class="add-img-input-style"
							type="text" value="商业计划" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- 链接 -->
		<div class="name">
			<div class="name-key">链接</div>
			<div class="name-enter">
				<c:choose>
					<c:when test="${content!=null }">
						<input id="url" name="url" class="add-img-input-style" type="text"
							value="${content.url }" />
					</c:when>
					<c:otherwise>
						<input id="url" name="url" class="add-img-input-style" type="text"
							value="" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- 上传图片 -->
		<div class="name">
			<div class="name-key">
				添加图片(<font style="color:red">*图片地址可选填</font>)
			</div>
			<div class="action-value">
				<c:choose>
					<c:when test="${content!=null}">
						<div>
							<div class="add-img-input">
								<input class="add-img-input-style" style="color:black"
									name="image" type="text" value=${content.image}>
							</div>

							<div class="add-item-img">
								<img alt="添加活动" src="images/圆角矩形-1-拷贝_50.png">
							</div>
						</div>
						<div>
							<img alt=${content.title } src=${content.image}>
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
			</div>
			<div class="upload dropzone needsclick"></div>
		</div>
		<!-- 图片模式 -->
		<div class="name">
			<div class="name-key">图文模式</div>
			<div class="name-enter">
				<select name="flag" id="flag">
					<option value="0">小图</option>
					<option value="1">大图</option>
				</select>
			</div>
		</div>
		<!-- 开始时间 -->
		<div class="name">
			<div class="name-key">创建时间</div>
			<div class="name-value">
				<c:choose>
					<c:when test="${content!=null}">
						<input style="color:black" id="beginTime" name="beginTime"
							type="text" value="${content.createDate}">
					</c:when>
					<c:otherwise>
						<input id="beginTime" name="beginTime" type="text" value="请选择开始时间">
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!--编辑区域-->
		<div class="editor">
			<c:choose>
				<c:when test="${content!=null }">
					<script id="editor" type="text/plain"
						style="width:1024px;height:500px;">${content.content}</script>
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

	$('#beginTime').datetimepicker({
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
