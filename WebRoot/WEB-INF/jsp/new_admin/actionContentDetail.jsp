<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside -->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>团队内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="adminAddActionContent.action?menu=5&sortmenu=2&submenu=2"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.introduceId}"
						style="display:none">
				</div>

				<li class="list-group-item">
					<div class="input-group">
						<input name="name" id="name" type="text" class="form-control"
							placeholder="输入 关键字 进行搜索" value="${data.action.name }"> <span
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
				</li>

				<!-- 内容类型 -->
				<li class="list-group-item">
					<div class="clear">内容类型</div>
				</li>
				<li class="list-group-item"><input name="type"
					class="form-control alert-success" value="${data.type }"
					placeholder="请输入类型">
					</div></li>
				<!-- 内容 -->

				<li class="list-group-item">
					<div class="clear">内容</div>
				</li>
				<li class="list-group-item"><c:choose>
						<c:when test="${data.type==0 }">
							<textarea name="content" class="form-control alert-success"
								placeholder="请输入描述" style="height:300px;">${data.content }</textarea>
							</div>
						</c:when>
						<c:otherwise>
							<div class="clear">
								<a href="${data.content }" target="blank"><img
									alt="${data.action.name }" src="${data.content }" class="col-xs-4"></a>
								<input name="image" class="form-control alert-success"
									value="${data.content }" placeholder="请输入图片链接"> <input
									name="file" id="input-1" type="file" class="file">
							</div>
						</c:otherwise>
					</c:choose></li>

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