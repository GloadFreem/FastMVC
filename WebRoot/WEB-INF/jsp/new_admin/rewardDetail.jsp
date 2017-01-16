<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>身份认证详情</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editCurseDetail.action?menu=1&sortmenu=1&submenu=1"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.rewardId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">


						<!-
						-真实姓名 -->
						<li class="list-group-item">
							<div class="clear">真实姓名</div>
						</li>
						<li class="list-group-item"><input name="name"
							class="form-control alert-success" value="${data.users.name }"
							placeholder="请输入真实姓名" disabled="disabled"></li>

						<!-- 金条数目 -->
						<li class="list-group-item">
							<div class="clear">金条总数</div>
						</li>
						<li class="list-group-item"><input name="name"
							class="form-control alert-success" value="${data.count }"
							placeholder="请输入金条总数"></li>
						<li class="list-group-item">
							<div class="table-responsive">
								<table
									class="table table-striped b-t b-light text-sm text-center-xs">
									<thead>
										<tr>
											<th width="20"><input type="checkbox"></th>
											<th width="80" class="th-sortable" data-toggle="class">序号
												<span class="th-sort"> <i
													class="fa fa-sort-down text"></i> <i
													class="fa fa-sort-up text-active"></i> <i
													class="fa fa-sort"></i>
											</span>
											</th>
											<th width="100">头像</th>
											<th width="150">姓名</th>
											<th>交易类型</th>
											<th>金条数</th>
											<th>描述</th>
											<th>交易时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${data.rewardtrades != null}">
												<c:forEach items="${data.rewardtrades}" var="item"
													varStatus="status">

													<c:choose>
														<c:when test="${status.index<10}">
															<tr>
																<td><input type="checkbox" name="post[]"
																	value="${item.rewardTradeId}"></td>
																<td>${item.rewardTradeId}</td>
																<td><a
																	href="${item.rewardsystem.users.headSculpture}" /><img
																	alt="${item.rewardsystem.users.name}"
																	src="${item.rewardsystem.users.headSculpture}"
																	class="col-xs-12"></a></td>
																<td>${item.rewardsystem.users.name}</td>
																<td>${item.rewardtradetype.name}</td>
																<td>${item.count}</td>
																<td>${item.desc}</td>
																<td>${item.tradeDate}</td>
																<td><a
																	href="userDetail.action?contentId=${item.rewardTradeId }&menu=1&sortmenu=1&submenu=1"
																	class="active"><i
																		class="fa fa-edit text-success text-active"></i><i
																		class="fa fa-edit text-danger text"></i></a> | <a
																	href="#modal"
																	data-href="deleteUser.action?contentId=${item.rewardTradeId }&menu=1&sortmenu=1&submenu=1"
																	data-toggle="modal" class="active"><i
																		class="fa fa-trash-o text-success text-active"></i><i
																		class="fa fa-trash-o text-danger text"></i></a>
															</tr>
														</c:when>
													</c:choose>

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
					<button type="button" id="export"
						class="btn btn-default btn-success pull-left m-t m-b m-r">导出邀请码</button>
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

		$("#export")
				.click(
						function() {
							var url = "http://www.jinzht.com:8080/jinzht/newSystem/downloadInviteCode.action?contentId="
									+ ${data.rewardId};
							window.open(url);
						});
	</script>