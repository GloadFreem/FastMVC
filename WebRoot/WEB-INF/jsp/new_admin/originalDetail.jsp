<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>课程内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="adminAddOriginal.action?menu=7&sortmenu=2&submenu=2"
				method="post" enctype="multipart/form-data">
				<ul>
					<li class="list-group-item">
						<div class="">
							<input name="contentId" value="${data.infoId}"
								style="display:none">
							<ul class="list-group gutter list-group-lg list-group-sp">
								<div class="input-group">
									<input name="name" id="name" type="text" class="form-control"
										placeholder="输入 关键字 进行搜索" value="${data.title }"> <span
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
						<div class="clear">标题</div>
					</li>
					<li class="list-group-item"><input name="title"
						class="form-control alert-success" value="${data.title }"
						placeholder="请输入标题">
						</div></li>
					<li class="list-group-item">
						<div class="clear">来源</div>
					</li>
					<li class="list-group-item"><input name="original"
						class="form-control alert-success" value="${data.oringl }"
						placeholder="请输入来源">
						</div></li>
					<li class="list-group-item">
						<div class="clear">发布时间</div>
					</li>
					<li class="list-group-item"><input name="publicDate"
						class="form-control alert-success" value="${data.publicDate }"
						placeholder="请输入来源">
						</li>
					<li class="list-group-item">
						<div class="clear">类型</div>
					</li>
					<li class="list-group-item"><input name="type"
						class="form-control alert-success" value="${data.webcontenttype.typeId }"
						placeholder="请输入类型">
						</li>

					<li class="list-group-item">
						<div class="clear">图标</div>
					</li>
					<li class="list-group-item"><c:forEach
							items="${data.originalImgs }" var="item">
							<div class="clear">
								<a href="${item.url }" target="blank"><img class="col-sm-1"
									alt="${data.title }" src="${item.url }"></a> <input
									name="image" class="form-control alert-success "
									value="${item.url }" placeholder="请输入内容链接">
							</div>
							<input name="file" id="input-1" type="file" class="file">

						</c:forEach></li>
					<li class="list-group-item">
						<div class="clear">内容</div>
					</li>
					<li class="list-group-item">
						<!--编辑区域-->
						<div class="editor">
							<c:forEach items="${data.originalDetails}" var="item">
									<script id="editor" type="text/plain"
										style="width:1024px;height:500px;">${item.content}</script>

							</c:forEach>
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