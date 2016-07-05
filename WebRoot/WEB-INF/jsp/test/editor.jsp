<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<!-- <script src="./kindeditor-master/lib/firebug-lite/build/firebug-lite.js#startOpened"></script> -->
<script src="./kindeditor-master/lib/qunit/qunit.js"></script>
<!-- include src files -->
<script type="text/javascript" src="./images/jquery-1.8.3.min.js"></script>
<script src="./kindeditor-master/kindeditor-all.js"></script>
<script src="./kindeditor-master/lang/zh-CN.js"></script>
<script src="./kindeditor-master/plugins/code/prettify.js"></script>
</head>

<script language="JavaScript">
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
	<form action="generateWebPage.action" method="post">
		<!-- 标题 -->
		<div class="title">
			<div class="title-tip">请输入文章标题</div>
			<div class="title-enter">
				<input id="title" name="title" class="title-enter-input" type="text" />
			</div>
		</div>
		<!--  名称 -->
		<div class="save">
			<div class="save-tip">请输入保存文件名称</div>
			<div class="save-enter">
				<input id="name" name="name" class="save-enter-input" type="text" />
			</div>
		</div>
		<!--  保存路径 -->
		<div class="path">
			<div class="path-tip">请输入保存文件路径</div>
			<div class="path-enter">
				<input id="path" name="path" class="path-enter-input" type="text" />
			</div>
		</div>
		<!--编辑区域-->
		<div class="editor">
			<textarea id="editor_id" name="content"
				style="width:100%;visibility:hidden;"></textarea>
		</div>
		<!--保存网页-->
		<div class="submit-button">
			<div class="submmit-button-item">
				<input id="save-button" type="submit" value="保存网页">
			</div>

		</div>
	</form>

</body>
</html>
