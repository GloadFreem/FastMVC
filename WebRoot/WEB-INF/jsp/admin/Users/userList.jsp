
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
			
		$("div.footer-page-item").click(function(){
			page = $(this).text();
			if(page!="..."){
				size = $("select[name='example_length']").val();
				url = "adminUserListAdmin.action?";
				url += "page="+page;
				url += "&size="+size;
				window.location=url;
			}
		});
		$("div.footer-page-last").click(function(){
			size = $("select[name='example_length']").val();
			page = $(this).text();
			url = "adminUserListAdmin.action?";
			url += "page="+page;
			url += "&size="+size;
			window.location=url;
		});
		$("div.footer-page-pre").click(function(){
			size = $("select[name='example_length']").val();
			page = 0;
			url = "adminUserListAdmin.action?";
			url += "page="+page;
			url += "&size="+size;
			window.location=url;
		});

	$("select[name='example_length']").val(100);

		$('.datatable').dataTable({
		
			scrollY : 500,
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
			<a href="adminEditUser.action?userId=" target="content"><h2>
					<div>
						<img alt="添加用户" src="images/圆角矩形-3-拷贝-5.png">
					</div>
					<div>添加用户</div>
				</h2></a>

			<div class="block">
				<table class="data display datatable" id="example">
					<thead>
						<tr>
							<th class="center">序号</th>
							<th class="center">姓名</th>
							<th class="center">手机号码</th>
							<th class="center">头像</th>
							<th class="center">设备类型</th>
							<th class="center">最近登录时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${items}" var="item" varStatus="vs">
							<tr class="odd gradeX">
								<td class="center"><a
									href="adminEditUser.action?userId=${item.userId}"
									target="content">${item.userId}</a></td>
								<td class="center">${item.name}</td>
								<td class="center">${item.telephone}</td>
								<td class="center"><a
									href=${item.headSculpture
                } target="blank">${item.headSculpture}</a></td>
								<td class="center"><c:choose>
										<c:when test="${item.platform==1}">
											<select name="platform" id="platform">
												<option value="0">请选择设备类型</option>
												<option value="1" selected="selected">iPhone设备</option>
												<option value="2">Android设备</option>
											</select>
										</c:when>
										<c:otherwise>
											<select name="platform" id="platform">
												<option value="0">请选择设备类型</option>
												<option value="1">iPhone设备</option>
												<option value="2" selected="selected">Android设备</option>
											</select>
										</c:otherwise>
									</c:choose></td>
								<td class="center">${item.lastLoginDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="footer-page">
			<div class="footer-page-pre">首页</div>
			<c:choose>
				<c:when test="${pageItem.size()>4}">
					<c:forEach items="${pageItem}" var="item" varStatus="vs">
						<c:choose>
							<c:when test="${vs.index<3  || vs.index>pageItem.size()-4}">
								<div class="footer-page-item">${item}</div>
							</c:when>
							<c:when test="${vs.index==3  || vs.index==pageItem.size()-4}">
								<div class="footer-page-item">...</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach items="${pageItem}" var="item" varStatus="vs">
						<div class="footer-page-item">${item}</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>

			<div class="footer-page-last">尾页</div>
		</div>
</body>
</html>