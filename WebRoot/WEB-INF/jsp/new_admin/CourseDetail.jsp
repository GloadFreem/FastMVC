<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>课程内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editCurseDetail.action?menu=1&sortmenu=1&submenu=1"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.bid}" style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">课程名称</div>
						</li>
						<li class="list-group-item"><input name="name"
							class="form-control alert-success" value="${data.bname }"
							placeholder="请输入课程名称">
				</div>
				</li>

				<li class="list-group-item">
					<div class="clear">内容类型</div>
				</li>
				<li class="list-group-item"><select id="type" name="type"
					class="selectpicker show-menu-arrow form-control"
					data-max-options="2">
						<c:forEach items="${types}" var="item">
							<option value="${item.cid}"
								<c:choose>
							<c:when test="${result.businessContentType.cid==item.cid}">
								  selected=selected
							</c:when>
						</c:choose>>${item.cname }</option>
						</c:forEach>
				</select></li>

				<li class="list-group-item">
					<div class="clear">聊天室</div>
				</li>
				<li class="list-group-item">
					<div class="input-group">
						<input name="key" id="key" type="text" class="form-control"
							placeholder="输入 关键字 进行搜索" value="${data.chatroom.name }">
						<span class="input-group-btn">
							<button id="searchbtn" type="button"
								class="btn btn-info btn-icon">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
					<div>
						<select class="selectpicker show-menu-arrow form-control"
							data-max-options="2" name='projectId' id='projectId'></select>
					</div>
					</div>

				</li>

				<li class="list-group-item">
					<div class="clear">运营人员</div>
				</li>
				<li class="list-group-item">姓名： <select id="wcode" name="wcode"
					class="selectpicker show-menu-arrow form-control"
					data-max-options="2">
						<c:forEach items="${users}" var="item">
							<option value="${item.wid}"
								<c:choose>
							<c:when test="${result.businessWeichat.wid==item.wid}">
								  selected=selected
							</c:when>
						</c:choose>>${item.name }--${item.wcode }</option>
						</c:forEach>
				</select>
				</li>

				<li class="list-group-item">
					<div class="clear">标签</div>
				</li>
				<li class="list-group-item"><select id="tag" name="tag"
					class="selectpicker show-menu-arrow form-control"
					data-max-options="2">
						<c:forEach items="${tages}" var="item">
							<option value="${item.tid}"
								<c:choose>
							<c:when test="${data.busniessTag.tid==item.tid}">
								selected=selected
							</c:when>
						</c:choose>>${item.tname }</option>
						</c:forEach>
				</select></li>
				<li class="list-group-item">
					<div class="clear">内容描述</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<textarea name="content" class="form-control alert-success"
							placeholder="请输入内容描述">${data.bdesc }</textarea>
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">原价</div>
				</li>
				<li class="list-group-item"><input name="bpriceBase"
					class="form-control alert-success" value="${data.bpriceBase }"
					placeholder="请输入价格">
					</div></li>

				<li class="list-group-item">
					<div class="clear">现价</div>
				</li>
				<li class="list-group-item"><input name="bpriceNow"
					class="form-control alert-success" value="${data.bpriceNow }"
					placeholder="请输入现价">
					</div></li>

				<li class="list-group-item">
					<div class="clear">开始时间</div>
				</li>
				<li class="list-group-item"><input name="bstarTime"
					class="form-control alert-success" value="${data.bstarTime }"
					placeholder="请输入开始时间">
					</div></li>
				<li class="list-group-item">
					<div class="clear">封面图片</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<img alt="${data.bdesc }" src="${data.bimage }"> <input
							name="image" class="form-control alert-success"
							value="${data.bimage }" placeholder="请输入内容链接">
					</div> <input name="file" id="input-1" type="file" class="file">
					</div>
				</li>
				<li class="list-group-item">
					<div class="clear">主讲人</div>
				</li>
				<li class="list-group-item"><select id="speechmarker"
					name="speechmarker"
					class="selectpicker show-menu-arrow form-control"
					data-max-options="2">
						<c:forEach items="${speeches}" var="item">
							<option value="${item.markerId}"
								<c:choose>
							<c:when test="${data.speechmarker.markerId==item.markerId}">
								selected=selected
							</c:when>
						</c:choose>>${item.name }</option>
						</c:forEach>
				</select></li>

				<li class="list-group-item">
					<div class="clear">主讲人介绍</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<textarea name="speechDesc" class="form-control alert-success"
							placeholder="请输入内容描述">${data.speechmarker.desc }</textarea>
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">人数限制</div>
				</li>
				<li class="list-group-item"><input name="limit"
					class="form-control alert-success" value="${data.blimit }"
					placeholder="请输入人数限制">
					</div></li>
				<li class="list-group-item">
					<div class="clear">是否有效</div>
				</li>
				<li class="list-group-item"><select
					class="selectpicker show-menu-arrow form-control"
					data-max-options="2" name='valid' id='valid'>
						<c:choose>
							<c:when test="${data.bValid}">
								<option value=1 selected="selected">有效</option>
								<option value=0>无效</option>
							</c:when>
							<c:otherwise>
								<option value=1>有效</option>
								<option value=0 selected="selected">无效</option>
							</c:otherwise>
						</c:choose>

				</select></li>


				<li class="list-group-item">
					<div class="clear">课程目录</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<div class="table-responsive">
							<table
								class="table table-striped b-t b-light text-sm text-center-xs">
								<thead>
									<tr>
										<th width="20"><input type="checkbox"></th>
										<th width="80" class="th-sortable" data-toggle="class">序号
											<span class="th-sort"> <i class="fa fa-sort-down text"></i>
												<i class="fa fa-sort-up text-active"></i> <i
												class="fa fa-sort"></i>
										</span>
										</th>
										<th width="100">图片</th>
										<th width="150">名称</th>
										<th width="25%">描述</th>
										<th>目录</th>
										<th>链接</th>
										<th>时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${data.businessVideos != null}">
											<c:forEach items="${data.businessVideos}" var="item"
												varStatus="status">
												<tr>
													<td><input type="checkbox" name="post[]"
														value="${item.vid}"></td>
													<td>${item.vid}</td>
													<td><a href="${item.vimage}" target="blank" /><img
														alt="${item.vname}" src="${item.vimage}" class="col-xs-12"></a></td>
													<td>${item.vname}</td>
													<td>${item.vdesc}</td>
													<td>${item.vposition}</td>
													<td>${item.vurl}</td>
													<td>${item.vtimelong}</td>
													<td><a
														href="sourceDetail.action?contentId=${item.vid }&menu=1&sortmenu=1&submenu=4"
														class="active"><i
															class="fa fa-edit text-success text-active"></i><i
															class="fa fa-edit text-danger text"></i></a> | <a
														href="#modal"
														data-href="deleteSource.action?contentId=${item.vid }&menu=1&sortmenu=1&submenu=4"
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
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">邀请码</div>
				</li>
				<li class="list-group-item">
					<div class="table-responsive">
						<table class="table table-striped b-t b-light text-sm">
							<thead>
								<tr>
									<th width="20"><input type="checkbox"></th>
									<th width="120" class="th-sortable" data-toggle="class">序号
										<span class="th-sort"> <i class="fa fa-sort-down text"></i>
											<i class="fa fa-sort-up text-active"></i> <i
											class="fa fa-sort"></i>
									</span>
									</th>
									<th width="110">所属课程</th>
									<th width="60%">邀请码</th>
									<th>是否已过期</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${codes != null}">
										<c:forEach items="${codes}" var="item" varStatus="status">
											<tr>
												<td><input type="checkbox" name="post[]"
													value="${item.cid}"></td>
												<td>${item.cid}</td>
												<td>${item.businessSchool.bname}</td>
												<td>${item.ccode}</td>
												<td><c:choose>
														<c:when test="${item.cvalid==1}">有效</c:when>
														<c:otherwise>
													已失效
												</c:otherwise>
													</c:choose></td>
												<td><a
													href="codeDetail.action?contentId=${item.cid }&menu=1&sortmenu=2&submenu=1"
													class="active"><i
														class="fa fa-edit text-success text-active"></i><i
														class="fa fa-edit text-danger text"></i></a> | <a
													href="#modal"
													data-href="deleteCode.action?contentId=${item.cid }&menu=1&sortmenu=2&submenu=1"
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
				</li>

				</ul>
				</div>
				<div>
					<button type="submit"
						class="btn btn-default btn-info pull-right m-t m-b m-r">完成</button>
				</div>
			</form>
		</section>
	</section>

	<script type="text/javascript">
		$("#searchbtn").click(
				function() {
					$.ajax({
						url : "SearchChatRoomByName.action",
						data : {
							"name" : $("input[name='key']").val(),
						},
						success : function(data) {
							selector = $("select[name='projectId']");
							selector.empty();

							data.data.forEach(function(e) {
								select = "<option value='"+e.chatroomId+"'>"
										+ e.name + "</option>"
								selector.append(select);
							});

						}
					});

				});

		$("#projectId").change(function() {
			$("input[name='key']").val($(this).find("option:selected").text());
		});
	</script>