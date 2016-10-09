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

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css"
	href="table/css/jquery.dataTables.css">
<!-- BEGIN: load jquery -->
<!-- <script src="js/jquery-1.6.4.min.js" type="text/javascript"></script> -->
<!-- jQuery -->
<script type="text/javascript" charset="utf8"
	src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
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
<script src="table/js/jquery.dataTables.js" type="text/javascript"></script>
<!-- END: load jquery -->
<!-- <script type="text/javascript" src="js/table/table.js"></script> -->
<script src="js/setup.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("div.footer-page-item").click(function() {
			page = $(this).text();
			if (page != "...") {
				size = $("select[name='example_length']").val();
				url = "adminCycleListAdmin.action?";
				url += "page=" + page;
				url += "&size=" + size;
				window.location = url;
			}
		});
		$("div.footer-page-last").click(function() {
			size = $("select[name='example_length']").val();
			page = $("div.footer-page-item-selected").text();
			page++;
			url = "adminCycleListAdmin.action?";
			url += "page=" + page;
			url += "&size=" + size;
			window.location = url;
		});
		$("div.footer-page-pre").click(function() {
			size = $("select[name='example_length']").val();
			page = $("div.footer-page-item-selected").text();
			page--;
			url = "adminCycleListAdmin.action?";
			url += "page=" + page;
			url += "&size=" + size;
			window.location = url;
		});
		$('.datatable').dataTable({
			scrollY : 500,
			deferRender : true,
			processing : true,
			language : {
				"sProcessing" : "处理中...",
				"sLengthMenu" : "显示 _MENU_ 项结果",
				"sZeroRecords" : "没有匹配结果",
				"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
				"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix" : "",
				"sSearch" : "搜索:",
				"sUrl" : "",
				"sEmptyTable" : "表中数据为空",
				"sLoadingRecords" : "载入中...",
				"sInfoThousands" : ",",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上页",
					"sNext" : "下页",
					"sLast" : "末页"
				},
				"oAria" : {
					"sSortAscending" : ": 以升序排列此列",
					"sSortDescending" : ": 以降序排列此列"
				}
			}
		});
	});
</script>
</head>
<body>
	<div class="grid_10">
		<div class="box round first grid">
			<a href="adminEditCycle.action?cycleId=" target="content"><h2>
					<div>
						<img alt="添加圈子" src="images/圆角矩形-3-拷贝-5.png">
					</div>
					<div>添加圈子</div>
				</h2></a>

			<div class="block">
				<table class="data display datatable" id="example">
					<thead>
						<tr>
							<th class="center">序号</th>
							<th class="center">内容</th>
							<th class="center">发布人</th>
							<th class="center">发布时间</th>
							<th class="center">点赞人数</th>
							<th class="center">评论人数</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${items}" var="item" varStatus="vs">
							<tr class="odd gradeX">
								<td class="center"><a
									href="adminEditCycle.action?cycleId=${item.publicContentId}"
									target="content">${item.publicContentId}</a></td>
								<td class="center">${item.content}</td>
								<td class="center">${item.users.name}</td>
								<td class="center">${item.publicDate}</td>
								<td class="center">${item.contentprises.size()}</td>
								<td class="center">${item.comments.size()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="footer-page">
			<c:choose>
				<c:when test="${page>0}">
					<div class="footer-page-pre">上一页</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<c:forEach items="${pageItem}" var="item" varStatus="vs">
				<div
					<c:choose>
			<c:when test="${item!=-1 && item==page}">
				class="footer-page-item-selected"
			</c:when>
			<c:otherwise>
				class="footer-page-item"
			</c:otherwise>
		</c:choose>>
					<c:choose>
						<c:when test="${item==-1}">
				...
			</c:when>
						<c:otherwise>
				${item}
			</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
			<c:choose>
				<c:when test="${page!=size}">
					<div class="footer-page-last">下一页</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>