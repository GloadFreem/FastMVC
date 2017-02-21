<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>课程内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="adminAddRoadShow.action?menu=4&sortmenu=2&submenu=1"
				method="post" enctype="multipart/form-data">
				<div class="">
					<ul>
						<li>
							<div class="input-group">
								<input name="name" id="name" type="text" class="form-control"
									placeholder="输入 关键字 进行搜索" value="${roadshow.project.fullName }">
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
						</li>
						<li><input name="contentId" value="${data.financingId}"
							style="display:none">
						<li class="list-group-item">
							<div class="clear">融资总额</div>
						</li>
						<li class="list-group-item"><input name="total"
							class="form-control alert-success" value="${data.financeTotal }"
							placeholder="请输入融资总额"></li>
						<li class="list-group-item">
							<div class="clear">已融金额</div>
						</li>
						<li class="list-group-item"><input name="financed"
							class="form-control alert-success" value="${data.financedMount }"
							placeholder="请输入金额"></li>

						<li class="list-group-item">
							<div class="clear">金额限制</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<textarea name="limit" class="form-control alert-success"
									placeholder="请输入内容">${data.limitAmount }</textarea>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">开始时间</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="beginTime" class="form-control alert-success"
									value="${data.beginDate }" placeholder="请输入开始时间">
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">结束时间</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="endTime" class="form-control alert-success"
									value="${data.endDate }" placeholder="请输入结束时间">
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
						url : "../admin/adminSearchProjectByName.action",
						data : {
							"name" : $("input[name='name']").val(),
						},
						success : function(data) {
							selector = $("select[name='projectId']");
							selector.empty();

							data.data.forEach(function(e) {
								select = "<option value='"+e.projectId+"'>"
										+ e.fullName + "</option>"
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