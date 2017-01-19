<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside -->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>课程内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editSourceDetail.action?menu=1&sortmenu=1&submenu=3"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.memberId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<div class="input-group">
							<input name="name" id="name" type="text" class="form-control"
								placeholder="输入 关键字 进行搜索" value="${data.project.fullName }">
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
				<!-- 姓名 -->
				<li class="list-group-item">
					<div class="clear">姓名</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.name }"
					placeholder="请输入价格">
					</div></li>
				<!-- 公司 -->
				<li class="list-group-item">
					<div class="clear">公司</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.company }"
					placeholder="请输入价格">
					</div></li>
				<!-- 职位 -->
				<li class="list-group-item">
					<div class="clear">职位</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.position }"
					placeholder="请输入价格">
					</div></li>
				<!-- 地址 -->
				<li class="list-group-item">
					<div class="clear">地址</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.address }"
					placeholder="请输入价格">
					</div></li>
				<!-- 行业 -->
				<li class="list-group-item">
					<div class="clear">行业</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.industory }"
					placeholder="请输入价格">
					</div></li>



				<li class="list-group-item">
					<div class="clear">头像</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<a href="${data.icon }" target="blank"><img
							alt="${data.name }" src="${data.icon }" class="col-xs-1"></a>
						<input name="image" class="form-control alert-success"
							value="${data.icon }" placeholder="请输入图片链接"> <input
							name="file" id="input-1" type="file" class="file">
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