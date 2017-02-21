<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>课程内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="adminAddScene.action?menu=4&sortmenu=3&submenu=1"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.sceneId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
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
						</li>


						<li class="list-group-item">
							<div class="clear">上传视频</div>
						</li>
						<li class="list-group-item"><input name="vurl"
							class="form-control alert-success" value="${data.audioPath }"
							placeholder="请输入现价"> <input name="file" id="input-1"
							type="file" class="file">
				</div>
				</li>

				<li class="list-group-item">
					<div class="clear">时长</div>
				</li>
				<li class="list-group-item"><input name="time"
					class="form-control alert-success" value="${data.totlalTime }"
					placeholder="请输入价格">
					</div></li>

				<li class="list-group-item">
					<div class="clear">PPT</div>
				</li>
				<li class="list-group-item">
					<div style="width:100%;overflow:auto">
						<c:forEach items="${data.audiorecords }" var="item"
							varStatus="index">
							<c:choose>
								<c:when test="${index.index%6!=0 || index.index==0}">
							<div style="width:15%;float: left;white-space:nowrap;margin:10px;border-style:dotted;border-color:green;">
							</c:when>
								<c:otherwise>
									<div
										style="width:15%;clear: both;float: left;hite-space:nowrap;margin:10px;border-style:dotted;border-color:green;">
								</c:otherwise>
							</c:choose>
							<div>
								<a href="${item.imageUrl }" target="blank"> <img
									style="width:100%" alt="${data.project.fullName}"
									src="${item.imageUrl}"></a>
							</div>
							<div style="text-align:center;margin-top:5px;">第${index.index+1}张</div>
							<div></div>
					</div> </c:forEach>
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