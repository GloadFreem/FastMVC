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
<link rel="stylesheet"
	href="./kindeditor-master/themes/default/style.css" />
<!-- include src files -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.all.min.js">
		
	</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="ueditor/lang/zh-cn/zh-cn.js"></script>
<link rel="stylesheet"
	href="./kindeditor-master/themes/default/style.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />

<script src="upload/js/jquery.js"></script>
<script src="js/jquery-ui/jquery-ui.js"></script>

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
</script>
<body>
	<form action="adminAddProjectFinanceStanding.action" method="post">
		<input name="financeId" value="${standing.financeId }"
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
							type="text" value="财务状况"/>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!--编辑区域-->
		<div class="editor">
			<c:choose>
				<c:when test="${record!=null }">
					<script id="editor" type="text/plain"
						style="width:1024px;height:500px;">${record.content}</script>
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
<script type="text/javascript">
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor', {
		imagePathFormat : "/upload/uploadImages/{filename}"
	});
	ue.setContent("heelooeorfwej");

	function getContent() {
		var arr = [];
		arr.push("使用editor.getContent()方法可以获得编辑器的内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getContent());
		alert(arr.join("\n"));
	}
</script>
</html>
