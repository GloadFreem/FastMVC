<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- /.aside -->
<section id="content">
	<section class="vbox">
		<section class="scrollable padder">
			<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
				<li><a href="index.action"><i class="fa fa-home"></i> 首页</a></li>
				<li><a href="index.action"><i class="fa"></i> 用户管理</a></li>
				<li><a href="index.action"><i class="fa"></i> 用户列表</a></li>
			</ul>
			<section class="panel panel-default">
				<header class="panel-heading"> 用户列表 </header>
				<div class="row text-sm wrapper">
					<div class="col-sm-4 hidden-xs">
						<select name="size"
							class="input-sm form-control input-s-sm inline">
							<c:forEach items="${sizes}" var="item">
								<option value=${item
									}
									<c:choose>
									<c:when test="${size==item}">
										 selected=selected
									</c:when>
								</c:choose>>${item }</option>
							</c:forEach>
						</select>
					</div>
					<!-- 				<div class="col-sm-4 m-b-xs">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-sm btn-default active"> <input
								type="radio" name="options" id="option1"> 今日
							</label> <label class="btn btn-sm btn-default"> <input
								type="radio" name="options" id="option2"> 本周
							</label> <label class="btn btn-sm btn-default"> <input
								type="radio" name="options" id="option2"> 本月
							</label>
						</div>
					</div> -->
					<div class="col-sm-8">
						<div class="input-group">
							<input type="text" class="input-sm form-control"
								placeholder="请输入搜索内容"> <span class="input-group-btn">
								<button class="btn btn-sm btn-default" type="button">搜索</button>
							</span>
						</div>
					</div>
				</div>
				<div class="table-responsive">
					<table
						class="table table-striped b-t b-light text-sm text-center-xs">
						<thead>
							<tr>
								<th width="20"><input type="checkbox"></th>
								<th width="80" class="th-sortable" data-toggle="class">序号 <span
									class="th-sort"> <i class="fa fa-sort-down text"></i> <i
										class="fa fa-sort-up text-active"></i> <i class="fa fa-sort"></i>
								</span>
								</th>
								<th width="150">名称</th>
								<th width="30%">类型</th>
								<th>内容</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${result != null}">
									<c:forEach items="${result}" var="item" varStatus="status">
										<tr>
											<td><input type="checkbox" name="post[]"
												value="${item.introduceId}"></td>
											<td>${item.introduceId}</td>
											<td>${item.action.name}</td>
											<td><c:choose>
													<c:when test="${item.type!=0}">
														图片
													</c:when>
													<c:otherwise>
													文字
												</c:otherwise>
												</c:choose></td>
											<td><c:choose>
													<c:when test="${item.type!=0}">
														<a href="${item.content}" target="blank" />
														<img alt="${item.action.name}" src="${item.content}"
															class="col-xs-4">
														</a>
													</c:when>
													<c:otherwise>
														<textarea class="col-xs-12" style="height:100px">${item.content}</textarea>
													</c:otherwise>
												</c:choose></td>
											<td><a
												href="userDetail.action?contentId=${item.introduceId }&menu=1&sortmenu=1&submenu=1"
												class="active"><i
													class="fa fa-edit text-success text-active"></i><i
													class="fa fa-edit text-danger text"></i></a> | <a href="#modal"
												data-href="deleteUser.action?contentId=${item.introduceId }&menu=1&sortmenu=1&submenu=1"
												data-toggle="modal" class="active"><i
													class="fa fa-trash-o text-success text-active"></i><i
													class="fa fa-trash-o text-danger text"></i></a>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>

						</tbody>
					</table>
				</div>
				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-4 hidden-xs">
							<select class="input-sm form-control input-s-sm inline">
								<option value="1">删除</option>
								<option value="2">导出</option>
							</select>
							<button class="btn btn-sm btn-default">应用</button>
						</div>
						<div class="col-sm-4 text-right text-center-xs">
							<ul class="pagination">

								<c:choose>
									<c:when test="${page>0}">
										<li><a name="pre" href="#"><i
												class="fa fa-chevron-left"></i></a></li>
									</c:when>
								</c:choose>


								<c:forEach items="${pages}" var="item">
									<li
										<c:choose>
										<c:when test="${page==item}">
											class="active"
										</c:when>
									</c:choose>><a
										name="page" href="#">${item+1}</a></li>
								</c:forEach>

								<c:choose>
									<c:when test="${page<count}">
										<li><a name="next" href="#"><i
												class="fa fa-chevron-right"></i></a></li>
									</c:when>
								</c:choose>


							</ul>
						</div>
					</div>
				</footer>
			</section>
		</section>
	</section>
</section>
<script type="text/javascript">
	$("a[name='page']").click(
			function() {
				var size = $("select[name='size']").val();
				var page = $(this).text() - 1;
				var menu = ${menu};
				var sortmenu = ${sortmenu};
				var submenu = ${submenu};
				var url = "actionContentList.action?" + "size=" + size
						+ "&page=" + page + "&menu=" + menu + "&sortmenu="
						+ sortmenu + "&submenu=" + submenu
						+ "&requestType=webRequest";
				//alert(url);

				window.location.href = url;

			});
	$("a[name='pre']").click(
			function() {
				var size = $("select[name='size']").val();
				var page = ${page};
				if (page > 0) {
					page--;
				}
				var menu = ${menu};
				var sortmenu = ${sortmenu};
				var submenu = ${submenu};
				var url = "actionContentList.action?" + "size=" + size
						+ "&page=" + page + "&menu=" + menu + "&sortmenu="
						+ sortmenu + "&submenu=" + submenu
						+ "&requestType=webRequest";
				//alert(url);

				window.location.href = url;

			});
	$("a[name='next']").click(
			function() {
				var size = $("select[name='size']").val();
				var page = ${page};
				if (page < ${count}) {
					page++;
				}
				var menu = ${menu};
				var sortmenu = ${sortmenu};
				var submenu = ${submenu};
				var url = "actionContentList.action?" + "size=" + size
						+ "&page=" + page + "&menu=" + menu + "&sortmenu="
						+ sortmenu + "&submenu=" + submenu
						+ "&requestType=webRequest";
				//alert(url);

				window.location.href = url;

			});
</script>
<div id="modal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">
					<i class="icon-pencil"></i> <span id="lblAddTitle"
						style="font-weight:bold">是否确认删除内容？</span>
				</h4>
			</div>
			<form class="form-horizontal form-bordered form-row-strippe"
				id="ffAdd" action="" data-toggle="validator"
				enctype="multipart/form-data">
				<div class="modal-body">删除后数据将无法恢复，请确认！</div>
				<div class="modal-footer">
					<input type="hidden" id="ID" name="ID" />
					<button type="submit" class="btn btn-default" data-dismiss="modal">取消</button>
					<a id="confirm" class="btn btn-info">确认</a>
				</div>
			</form>
		</div>
	</div>
</div>
