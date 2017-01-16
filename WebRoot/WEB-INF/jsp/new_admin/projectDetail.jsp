<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>课程内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editSourceDetail.action?menu=1&sortmenu=1&submenu=3"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.projectId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<div class="input-group">
							<input name="name" id="name" type="text" class="form-control"
								placeholder="输入 关键字 进行搜索" value="${data.fullName }"> <span
								class="input-group-btn">
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
					<div class="clear">公司</div>
				</li>
				<li class="list-group-item"><input name="vname"
					class="form-control alert-success" value="${data.abbrevName }"
					placeholder="请输入名称">
					</div></li>
				<li class="list-group-item">
					<div class="clear">地址</div>
				</li>
				<li class="list-group-item"><input name="vdesc"
					class="form-control alert-success" value="${data.address }"
					placeholder="请输入地址">
					</div></li>

				<li class="list-group-item">
					<div class="clear">行业类型</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<textarea name="vposition" class="form-control alert-success"
							placeholder="请输入内容描述">${data.industoryType }</textarea>
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">项目介绍</div>
				</li>
				<li class="list-group-item"><textarea name="vtimelong"
						class="form-control alert-success" style="height:300px">${data.description }</textarea>
					</div></li>

				<li class="list-group-item">
					<div class="clear">封面图片</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<a href="${data.startPageImage }" target="blank"><img
							class="col-sm-2" alt="${data.fullName }"
							src="${data.startPageImage }"></a> <input name="image"
							class="form-control alert-success "
							value="${data.startPageImage }" placeholder="请输入内容链接">
					</div> <input name="file" id="input-1" type="file" class="file">
					</div>
				</li>
				<li class="list-group-item">
					<div class="clear">商业计划</div>
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
									<th width="30%">项目名称</th>
									<th width="150">融资总额</th>
									<th width="10%">已融金额</th>
									<th>最低限额</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${data.roadshows != null}">
										<c:forEach items="${data.roadshows}" var="item"
											varStatus="status">
											<tr>
												<td><input type="checkbox" name="post[]"
													value="${item.roadShowId}"></td>
												<td>${item.roadShowId}</td>
												<td>${item.project.fullName}</td>
												<td>${item.roadshowplan.limitAmount}</td>
												<td>${item.roadshowplan.financeTotal}</td>
												<td>${item.roadshowplan.financedMount}</td>
												<td>${item.roadshowplan.beginDate}</td>
												<td>${item.roadshowplan.endDate}</td>
												<td><a
													href="roadShowDetail.action?contentId=${item.roadShowId }&menu=4&sortmenu=2&submenu=2"
													class="active"><i
														class="fa fa-edit text-success text-active"></i><i
														class="fa fa-edit text-danger text"></i></a> | <a
													href="#modal"
													data-href="deleteRoadShowPlan.action?contentId=${item.roadShowId }&menu=4&sortmenu=2&submenu=1"
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