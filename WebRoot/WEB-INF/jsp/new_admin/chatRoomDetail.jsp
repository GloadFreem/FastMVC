<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>聊天室详情</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editCurseDetail.action?menu=1&sortmenu=1&submenu=1"
				method="post" enctype="multipart/form-data">
				<ul class="list-group gutter list-group-lg list-group-sp">
					<li class="list-group-item">
						<div class="">
							<input name="contentId" value="${data.chatroomId}" style="display:none">
						</div>
					</li>

					<li class="list-group-item">
						<div class="clear">IM识别号</div>
					</li>
					<li class="list-group-item"><input name="bpriceNow"
						class="form-control alert-success" value="${data.code }"
						placeholder="请输入现价"></li>

					<li class="list-group-item">
						<div class="clear">聊天室</div>
					</li>
					<li class="list-group-item">
						<div class="input-group">
							<input name="key" id="key" type="text" class="form-control"
								placeholder="输入 关键字 进行搜索" value="${data.name }"> <span
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
						<div class="clear">内容描述</div>
					</li>
					<li class="list-group-item">
						<div class="clear">
							<textarea name="content" class="form-control alert-success"
								placeholder="请输入内容描述">${data.description }</textarea>
						</div>
					</li>

					<li class="list-group-item">
						<div class="clear">群主</div>
					</li>
					<li class="list-group-item"><input name="bpriceBase"
						class="form-control alert-success" value="${data.ownerName }"
						placeholder="请输入价格">
						</div></li>


					<li class="list-group-item">
						<div class="clear">创建时间</div>
					</li>
					<li class="list-group-item"><input name="bstarTime"
						class="form-control alert-success" value="${data.createDate }"
						placeholder="请输入开始时间"></li>
				</ul>
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
									+ ${data.bid};
							window.open(url);
						});
	</script>