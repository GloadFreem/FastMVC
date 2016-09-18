<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>编辑网页</title>
<link rel="stylesheet" href="./kindeditor-master/lib/qunit/qunit.css" />
<link rel="stylesheet"
	href="./kindeditor-master/plugins/code/prettify.css" />
<link rel="stylesheet"
	href="./kindeditor-master/themes/default/default.css" />
<link rel="stylesheet"
	href="./kindeditor-master/themes/default/style.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />

<script src="upload/js/jquery.js"></script>
<script src="js/jquery-ui/jquery-ui.js"></script>
<!-- <script src="./kindeditor-master/lib/firebug-lite/build/firebug-lite.js#startOpened"></script> -->
<script src="./kindeditor-master/lib/qunit/qunit.js"></script>
<!-- include src files -->
<script src="./kindeditor-master/kindeditor-all.js"></script>
<script src="./kindeditor-master/lang/zh-CN.js"></script>
<script src="./kindeditor-master/plugins/code/prettify.js"></script>
</head>

<script language="JavaScript">
	jQuery(function($) {
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
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="content"]', {
			cssPath : './kindeditor-master/plugins/code/prettify.css',
			uploadJson : './kindeditor-master/jsp/upload_json.jsp',
			fileManagerJson : './kindeditor-master/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
</script>
<body>
	<form action="adminAddProjectFinanceCase.action" method="post">
		<input name="financeId" value="${standing.financingCaseId }"
			style="visibility:hidden" />
		<!-- 名称 -->
		<div class="name">
			<div class="name-key" style="margin-left:5%">所属项目</div>
			<div class="name-value" style="margin-left:5%">
				<c:choose>
					<c:when test="${standing!=null}">
						<div class="search">
							<input style="color:black;width:95%" name="name" type="text"
								value=${standing.project.fullName}>
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

		<!-- 标题 -->
		<div class="title">
			<div class="title-tip">请输入文章标题</div>
			<div class="title-enter">
				<c:choose>
					<c:when test="${standing!=null }">
						<input id="title" name="title" class="title-enter-input"
							type="text" value="${standing.content }"/>
					</c:when>
					<c:otherwise>
						<input id="title" name="title" class="title-enter-input"
							type="text" value="融资方案"/>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!--编辑区域-->
		<div class="editor">
			<c:choose>
				<c:when test="${record!=null }">
					<textarea id="editor_id" name="content"
						style="width:90%;visibility:hidden;height:1450px;">${record.content }</textarea>
				</c:when>
				<c:otherwise>
					<textarea id="editor_id" name="content"
						style="width:90%;visibility:hidden;height:1450px;"></textarea>
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
</html>
