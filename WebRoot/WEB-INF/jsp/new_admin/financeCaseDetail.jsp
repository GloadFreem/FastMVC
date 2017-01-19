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
				<ul>
					<li class="list-group-item">
						<div class="">
							<input name="contentId" value="${data.financingCaseId}"
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

					<li class="list-group-item">
						<div class="clear">标签</div>
					</li>
					<li class="list-group-item"><input name="vtimelong"
						class="form-control alert-success" value="${data.content }"
						placeholder="请输入标签">
						</div></li>

					<li class="list-group-item">
						<div class="clear">图标</div>
					</li>
					<li class="list-group-item">
						<div class="clear">
							<a href="${data.icon }" target="blank"><img class="col-sm-1"
								alt="${data.content }" src="${data.icon }"></a> <input
								name="image" class="form-control alert-success "
								value="${data.icon }" placeholder="请输入内容链接">
						</div> <input name="file" id="input-1" type="file" class="file">
						</div>

					</li>
					<li class="list-group-item">
						<div class="clear">内容</div>
					</li>
					<li class="list-group-item">
						<!--编辑区域-->
						<div class="editor">
							<c:choose>
								<c:when test="${record!=null }">
									<script id="editor" type="text/plain"
										style="width:1024px;height:500px;">${record.content}</script>
								</c:when>
								<c:otherwise>
									<script id="editor" type="text/plain"
										style="width:1024px;height:500px;"></script>
								</c:otherwise>
							</c:choose>

						</div>

					</li>
				</ul>
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

	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor', {
			imagePathFormat : "/upload/uploadImages/{filename}"
		});
		ue.setContent("heelooeorfwej");

		function getContent() {
			var arr = [];
			arr.push("使用editor.getContent()方法可以获得编辑器的内容");
			arr.push("内容为：");
			arr.push(UE.getEditor('editor').getContent());
			alert(arr.join("\n"));
		}
	</script>