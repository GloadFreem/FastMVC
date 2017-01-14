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
					<c:choose>
						<c:when test="${result != null}">
							<section class="comment-list block">
								<c:forEach items="${result}" var="item" varStatus="status">
									<div class="line"></div>
									<article id="comment-id-1" class="comment-item m-l-lg">
										<a class="pull-left thumb-sm m-l-xs"> <img
											src="${item.users.headSculpture }" class="img-circle">
										</a>
										<section class="comment-body m-b-lg m-l-lg">
											<header>
												<a href="#"><strong>${item.users.name }</strong></a>
												<c:choose>
													<c:when test="${item.getFeeingtype().feelingTypeId==1}">发表</c:when>
													<c:otherwise>分享</c:otherwise>
												</c:choose>
												<a href="#" class="text-info">${item.getFeeingtype().name}</a><span
													class="text-muted text-xs">${item.publicDate} </span>
											</header>
											<div>${item.content}</div>
										</section>
									</article>

									<c:choose>
										<c:when test="${item.contentimageses!=null }">
											<article id="comment-id-2" class="comment-reply m-l-lg">
												<!-- .comment-reply -->
												<article class="comment-item m-l-lg">
													<c:forEach items="${item.contentimageses}" var="image"
														varStatus="index">
														<c:choose>
															<c:when
																test="${index.index== item.contentimageses.size()-1 || index.index== 3}">
																<div>
															</c:when>
															<c:otherwise>
																<div style="float:left">
															</c:otherwise>
														</c:choose>
														<a href="${image.url }" target="blank"> <img
															src="${image.url }" style="width:100px;margin:5px;">
														</a>
				</div>
				</c:forEach>
				</article>
				</article>
				</c:when>
				</c:choose>
				<div class="line"></div>
				<div style="margin-top:10px">
					<c:choose>
						<c:when test="${item.comments!=null }">
							<c:forEach items="${item.comments}" var="comment">
								<section>
									<article id="comment-id-2" class="comment-reply m-l-lg">
										<!-- .comment-reply -->
										<article class="comment-item m-l-lg">
											<a class="pull-left thumb-sm"> <img
												src="${comment.usersByUserId.headSculpture }"
												class="img-circle">
											</a>
											<section class="comment-body m-b-lg">
												<header>
													<a href="#"><strong>${comment.usersByUserId.name }</strong></a>
													<span class="text-muted text-xs">${comment.publicDate }
													</span>
												</header>
												<div class="col-xs-8"><a href="#" class="text-info">评论:</a>${comment.content }</div>
											</section>
										</article>
									</article>
								</section>
							</c:forEach>

						</c:when>
					</c:choose>
				</div>
				</c:forEach>
			</section>
			</c:when>
			</c:choose>
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
				var url = "feelingList.action?" + "size=" + size + "&page="
						+ page + "&menu=" + menu + "&sortmenu=" + sortmenu
						+ "&submenu=" + submenu + "&requestType=webRequest";
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
				var url = "feelingList.action?" + "size=" + size + "&page="
						+ page + "&menu=" + menu + "&sortmenu=" + sortmenu
						+ "&submenu=" + submenu + "&requestType=webRequest";
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
				var url = "feelingList.action?" + "size=" + size + "&page="
						+ page + "&menu=" + menu + "&sortmenu=" + sortmenu
						+ "&submenu=" + submenu + "&requestType=webRequest";
				//alert(url);

				window.location.href = url;

			});

	$("img")
			.bind(
					"error",
					function() {

						this.src = "http://www.jinzht.com:8080/jinzht/upload/image/20160727/20160727160846_436.png";
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
