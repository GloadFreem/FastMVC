
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String count = request.getParameter("size");
	Integer size = 10;
	if (count != null) {
		size = Integer.parseInt(request.getParameter("size"));
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>金指投科技 | Banner管理</title>
<link rel="stylesheet" type="text/css" href="css/banner.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />
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
	$(document).ready(
			function() {
				$("div.footer-page-item").click(function() {
					page = $(this).text();
					if (page != "...") {
						size = $("select[name='example_length']").val();
						url = "adminUserAuthenticListAdmin.action?";
						url += "page=" + page;
						url += "&size=" + size;
						window.location = url;
					}
				});
				$("div.footer-page-last").click(function() {
					size = $("select[name='example_length']").val();
					page = $("div.footer-page-item-selected").text();
					page++;
					url = "adminUserAuthenticListAdmin.action?";
					url += "page=" + page;
					url += "&size=" + size;
					window.location = url;
				});
				$("div.footer-page-pre").click(function() {
					size = $("select[name='example_length']").val();
					page = $("div.footer-page-item-selected").text();
					page--;
					url = "adminUserAuthenticListAdmin.action?";
					url += "page=" + page;
					url += "&size=" + size;
					window.location = url;
				});

				//排序
				$("img[name='up']").click(function() {
					sortIndex($(this), 1, 0);
				});
				$("img[name='down']").click(function() {
					sortIndex($(this), 0, 0);
				});
				$("input[name='sortIndex']").change(function() {
					sortIndex($(this), 0, $(this).val());
				});

				function sortIndex(el, type, data) {
					authId = el.parent().parent().prev("input").val();
					$.ajax({
						url : "adminSortAuthentic.action",
						data : {
							"authId" : authId,
							"type" : type,
							"data" : data
						},
						success : function(data) {
							el.parent().parent()
									.find("input[name='sortIndex']").val(
											data.data);
						}
					});
				}

				$(".search-img").click(
						function() {
							name = $("input[name='name']").val();
							url = "adminSearchAuthenticListByName.action?name="
									+ name + "&page=0&size=10000";
							window.location.href = url;
						});

				size = '<%=size%>';
				$('.datatable').dataTable({
					scrollY : 500,
					deferRender : true,
					processing : true,
					searching : false,
					iDisplayLength : size,
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
			<div>

				<a href="adminEditorUserAuthentic.action?authId=" target="content"><h2>
						<div>
							<img alt="添加认证" src="images/圆角矩形-3-拷贝-5.png">
						</div>
						<div>添加认证</div>
					</h2></a>
			</div>
			<div>
				<div>
					<!-- 名称 -->
					<div class="name">
						<div class="name-value">
							<c:choose>
								<c:when test="${key!=null}">
									<div class="search">
										<input style="color:black;width:95%" name="name" type="text"
											value=${key}>
									</div>
									<div>
										<img name="search-img" class="search-img" alt=""
											src="../images/feeling/椭圆-2.png">
									</div>
								</c:when>
								<c:otherwise>
									<div class="search">
										<input style="color:black;width:95%" id="name" name="name"
											type="text" placeholder="请输入用户姓名">
									</div>
									<div>
										<img name="search-img" class="search-img" alt=""
											src="../images/feeling/椭圆-2.png">
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
			<div class="block">
				<table class="data display datatable" id="example">
					<thead>
						<tr>
							<th class="center">序号</th>
							<th class="center">姓名</th>
							<th class="center">身份类型</th>
							<th class="center">身份证A面</th>
							<th class="center">身份证号</th>
							<th class="center">审核状态</th>
							<th class="center">排序</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${items}" var="item" varStatus="vs">
							<tr class="odd gradeX">
								<td class="center"><a
									href="adminEditorUserAuthentic.action?authId=${item.authId}"
									target="content">${item.authId}</a></td>
								<td class="center">${item.name}</td>
								<td class="center">${item.identiytype.name}</td>
								<td class="center"><a
									href=${item.identiyCarA
                } target="blank">${item.identiyCarA}</a></td>
								<td class="center">${item.identiyCarNo}</td>
								<td class="center">
									<!-- 认证状态 -->
									<div class="name">
										<div class="name-key">认证状态</div>
										<div class="name-value">
											<select name="city" id="city">
												<c:forEach items="${authenticStatus}" var="c" varStatus="v">
													<option value=${c.statusId
                    }
														<c:choose>
                    <c:when test="${c.name == item.authenticstatus.name }">
                        selected="selected"
                    </c:when>
                </c:choose>>${c.name}</option>
												</c:forEach>

											</select>
										</div>
									</div>
								</td>
								<td class="center"><input id="authId"
									value="${item.authId}" style="display:none">
									<div>
										<div>
											<img name="up" alt="" src="images/up.png"
												style="width: 10%;margin-bottom:5px;">
										</div>
										<div>
											<input name="sortIndex" value="${item.sortIndex}"
												style="text-align: center; width: 20%;">
										</div>
										<div>
											<img name="down" alt="" src="images/down.png"
												style="width: 10%;margin-top:5px;">
										</div>
									</div></td>
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