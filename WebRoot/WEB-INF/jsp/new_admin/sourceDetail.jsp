<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>课程内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editSourceDetail.action?menu=1&sortmenu=1&submenu=3" method="post"
				enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.vid}" style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<div class="input-group">
							<input name="name" id="name" type="text" class="form-control"
								placeholder="输入 关键字 进行搜索" value="${data.businessSchool.bname }">
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
					<div class="clear">标题</div>
				</li>
				<li class="list-group-item"><input name="vname"
					class="form-control alert-success" value="${data.vname }"
					placeholder="请输入名称">
				</div>
				</li>
				<li class="list-group-item">
					<div class="clear">简介</div>
				</li>
				<li class="list-group-item"><input name="vdesc"
					class="form-control alert-success" value="${data.vdesc }"
					placeholder="请输入标签">
				</div>
				</li>

				<li class="list-group-item">
					<div class="clear">位置</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<textarea name="vposition" class="form-control alert-success"
							placeholder="请输入内容描述">${data.vposition }</textarea>
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">时长</div>
				</li>
				<li class="list-group-item"><input name="vtimelong"
					class="form-control alert-success" value="${data.vtimelong }"
					placeholder="请输入价格">
					</div></li>

				<li class="list-group-item">
					<div class="clear">地址</div>
				</li>
				<li class="list-group-item"><input name="vurl"
					class="form-control alert-success" value="${data.vurl }"
					placeholder="请输入现价"> <input name="video" id="input-1"
					type="file" class="file">
					</div></li>
				<li class="list-group-item">
					<div class="clear">封面图片</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<a href="${data.vimage }" target="blank" ><img class="col-sm-4" alt="${data.vdesc }" src="${data.vimage }"></a> <input
							name="image" class="form-control alert-success "
							value="${data.vimage }" placeholder="请输入内容链接">
					</div> <input name="file" id="input-1" type="file" class="file">
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