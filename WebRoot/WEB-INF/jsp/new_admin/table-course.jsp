<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- /.aside -->
<section id="content">
	<section class="vbox">
		<section class="scrollable padder">
			<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
				<li><a href="index.action"><i class="fa fa-home"></i> 首页</a></li>
				<li><a href="index.action"><i class="fa"></i> 广告管理</a></li>
				<li><a href="index.action"><i class="fa"></i> 资讯Banner</a></li>
			</ul>
			<section class="panel panel-default">
				<header class="panel-heading"> 聊天室列表 </header>
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
								<th width="100">图片</th>
								<th width="150">名称</th>
								<th width="10%">描述</th>
								<th>主讲人</th>
								<th>原价</th>
								<th>现价</th>
								<th>运营</th>
								<th>运营微信</th>
								<th>创建时间</th>
								<th>是否有效</th>
								<th>排序</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${result != null}">
									<c:forEach items="${result}" var="item" varStatus="status">
										<tr>
											<td><input type="checkbox" name="post[]"
												value="${item.bid}"></td>
											<td>${item.bid}</td>
											<td><a href="${item.bimage}" /><img alt="${item.bname}"
												src="${item.bimage}" class="col-xs-12"></a></td>
											<td>${item.bname}</td>
											<td><c:choose>
													<c:when test="${item.bdesc.length()>30}">
											${item.bdesc.substring(0,30)}
											</c:when>
													<c:otherwise>
											${item.bdesc}
											</c:otherwise>
												</c:choose></td>
											<td>${item.speechmarker.name}</td>
											<td>${item.bpriceBase}</td>
											<td>${item.bpriceNow}</td>
											<td>${item.businessWeichat.name}</td>
											<td>${item.businessWeichat.wcode}</td>
											<td>${item.bstarTime}</td>
											<td><c:choose>
													<c:when test="${ item.bValid}">
											有效
											</c:when>
													<c:otherwise>无效</c:otherwise>
												</c:choose></td>
											<td>${item.bposition}</td>

											<td><a
												href="courseDetail.action?contentId=${item.bid }&menu=1&sortmenu=1&submenu=1"
												class="active"><i
													class="fa fa-edit text-success text-active"></i><i
													class="fa fa-edit text-danger text"></i></a> | <a href="#modal"
												data-href="deleteCurse.action?contentId=${item.bid }&menu=1&sortmenu=1&submenu=1"
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
				var url = "CourseList.action?" + "size=" + size + "&page="
						+ page + "&menu=" + menu + "&sortmenu=" + sortmenu
						+ "&submenu=" + submenu;
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
				var url = "CourseList.action?" + "size=" + size + "&page="
						+ page + "&menu=" + menu + "&sortmenu=" + sortmenu
						+ "&submenu=" + submenu;
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
				var url = "CourseList.action?" + "size=" + size + "&page="
						+ page + "&menu=" + menu + "&sortmenu=" + sortmenu
						+ "&submenu=" + submenu;
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
