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
					<input name="contentId" value="${data.financingId}"
						style="display:none">

					<li class="list-group-item">
						<div class="clear">融资总额</div>
					</li>
					<li class="list-group-item"><input name="vname"
						class="form-control alert-success" value="${data.financeTotal }"
						placeholder="请输入融资总额"></li>
					<li class="list-group-item">
						<div class="clear">已融金额</div>
					</li>
					<li class="list-group-item"><input name="vdesc"
						class="form-control alert-success" value="${data.financedMount }"
						placeholder="请输入金额"></li>

					<li class="list-group-item">
						<div class="clear">金额限制</div>
					</li>
					<li class="list-group-item">
						<div class="clear">
							<textarea name="vposition" class="form-control alert-success"
								placeholder="请输入内容">${data.limitAmount }</textarea>
						</div>
					</li>
					<li class="list-group-item">
						<div class="clear">开始时间</div>
					</li>
					<li class="list-group-item">
						<div class="clear">
							<textarea name="vposition" class="form-control alert-success"
								placeholder="请输入内容">${data.beginDate }</textarea>
						</div>
					</li>
					<li class="list-group-item">
						<div class="clear">结束时间</div>
					</li>
					<li class="list-group-item">
						<div class="clear">
							<textarea name="vposition" class="form-control alert-success"
								placeholder="请输入内容">${data.endDate }</textarea>
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