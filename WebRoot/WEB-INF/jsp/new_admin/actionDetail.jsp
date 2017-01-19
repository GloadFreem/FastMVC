<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside -->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>团队内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editSourceDetail.action?menu=1&sortmenu=1&submenu=3"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.actionId}"
						style="display:none">
				</div>
				</li>
				<!--名称 -->
				<li class="list-group-item">
					<div class="clear">活动名称</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.name }"
					placeholder="请输入活动名称">
				</div>
				</li>
				<!-- 公司地址 -->
				<li class="list-group-item">
					<div class="clear">地址</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.address }"
					placeholder="请输入公司">
					</div></li>
				<!-- 限制人数 -->
				<li class="list-group-item">
					<div class="clear">人数限制</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.memberLimit }"
					placeholder="请输入职位">
					</div></li>
				<!-- 是否免费 -->
				<li class="list-group-item">
					<div class="clear">是否免费</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.type }"
					placeholder="请输入是否免费">
					</div></li>
				<!-- 开始时间 -->
				<li class="list-group-item">
					<div class="clear">开始时间</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.startTime }"
					placeholder="请输入开始时间">
					</div></li>
				<!-- 结束时间 -->
				<li class="list-group-item">
					<div class="clear">结束时间</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.endTime }"
					placeholder="请输入开始时间">
					</div></li>
				<!-- 介绍 -->
				<li class="list-group-item">
					<div class="clear">介绍</div>
				</li>
				<li class="list-group-item"><textarea name="vtimelong"
						class="form-control alert-success" placeholder="请输入描述"
						style="height:300px;">${data.description }</textarea>
					</div></li>

				<li class="list-group-item">
					<div class="clear">封面</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<a href="${data.startPageImage }" target="blank"><img
							alt="${data.name }" src="${data.startPageImage }"
							class="col-xs-4"></a> <input name="image"
							class="form-control alert-success"
							value="${data.startPageImage }" placeholder="请输入图片链接"> <input
							name="file" id="input-1" type="file" class="file">
					</div>
				</li>


				<li class="list-group-item">
					<div class="clear">内容</div>
				</li>
				<li class="list-group-item">
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
									<th width="150">名称</th>
									<th width="30%">类型</th>
									<th>内容</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${data.actionintroduces != null}">
										<c:forEach items="${data.actionintroduces}" var="item" varStatus="status">
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
														class="fa fa-edit text-danger text"></i></a> | <a
													href="#modal"
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
						url : "SearchCurseByName.action",
						data : {
							"name" : $("input[name='name']").val(),
						},
						success : function(data) {
							selector = $("select[name='projectId']");
							selector.empty();

							data.data.forEach(function(e) {
								select = "<option value='"+e.bid+"'>" + e.bname
										+ "</option>"
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
	</script>