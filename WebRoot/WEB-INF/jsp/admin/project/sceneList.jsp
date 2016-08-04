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
<link rel="stylesheet" type="text/css" href="css/reset.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/text.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/grid.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/layout.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/nav.css" media="screen" />
<!--[if IE 6]><link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" /><![endif]-->
<link href="css/table/demo_page.css" rel="stylesheet" type="text/css" />
<!-- BEGIN: load jquery -->
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-ui/jquery.ui.core.min.js"></script>
<script src="js/jquery-ui/jquery.ui.widget.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.ui.accordion.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.effects.core.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.effects.slide.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.ui.mouse.min.js" type="text/javascript"></script>
<script src="js/jquery-ui/jquery.ui.sortable.min.js"
	type="text/javascript"></script>
<script src="js/table/jquery.dataTables.min.js" type="text/javascript"></script>
<!-- END: load jquery -->
<script type="text/javascript" src="js/table/table.js"></script>
<script src="js/setup.js" type="text/javascript"></script>
<script type="text/javascript">

        $(document).ready(function () {
            setupLeftMenu();

            $('.datatable').dataTable();
			setSidebarHeight();


        });
    </script>
</head>
<body>
	<div class="grid_10">
		<div class="box round first grid">
		<a href="editScene.action?sceneId=" target="content"><h2>
			<div><img alt="添加现场" src="images/圆角矩形-3-拷贝-5.png"></div>
			<div>添加现场</div>
			</h2></a>
			
			<div class="block">
				<table class="data display datatable" id="example">
					<thead>
						<tr>
							<th class="center">序号</th>
							<th class="center">项目</th>
							<th class="center">音频地址</th>
							<th class="center">总时长</th>
							<th class="center">开始时间</th>
							<th class="center">结束时间</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${items}" var="item" varStatus="vs">
					<tr class="odd gradeX">
							<td class="center"><a href="editScene.action?sceneId=${item.sceneId}" target="content">${item.sceneId}</a></td>
							<td class="center"><a href="editProject.action?projectId=${item.project.projectId}" target="content">${item.project.fullName}</a></td>
							<td class="center"><a href=${item.audioPath} target="content">${item.audioPath}</a></td>
							<td class="center">${item.totlalTime}</td>
							<td class="center">${item.beginTime}</td>
							<td class="center">${item.endTime}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>