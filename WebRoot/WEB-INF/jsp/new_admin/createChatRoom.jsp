<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>创建/编辑聊天室</p>
		</header>
		<section class="scrollable wrapper">
			<form action="createChatRoom.action" method="post">
				<div class="">
					<input name="contentId" value="${data.chatroomId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">名称</div>
							<div class="m-t">
								<input name="name" class="form-control alert-success"
									value="${data.name }" placeholder="请输入聊天室名称">
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">项目现场</div>
							<div class="m-t">
								<!-- comment form -->
								<article class="comment-item media" id="comment-form">
									<section class="media-body">
										<div class="input-group">
											<input type="text" class="form-control" placeholder="请输入项目名称">
											<span class="input-group-btn">
												<button class="btn btn-primary" type="button">查询</button>
											</span>
										</div>
									</section>
								</article>
								<select name="projectId" class="form-control m-t">
									<option value="5330">官方</option>
									<option value="5330">项目</option>
									<option value="5330">项目</option>
									<option value="5330">项目三</option>
								</select>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">群主</div>
							<div class="m-t">
								<!-- comment form -->
								<article class="comment-item media" id="comment-form">
									<a class="pull-left thumb-sm avatar"><img
										src="${img}" class="img-circle"></a>
									<section class="media-body">
										<div class="input-group">
											<input type="text" class="form-control" placeholder="请输入姓名"
												value="${data.ownerName}"> <span
												class="input-group-btn">
												<button class="btn btn-primary" type="button">查询</button>
											</span>
										</div>
									</section>
								</article>
								<select name="owner" class="form-control m-t">
									<option value="5333">官方</option>
									<option value="5333">章</option>
									<option value="5333">张</option>
									<option value="5333">笑笑</option>
								</select>
							</div>

						</li>
						<li class="list-group-item">
							<div class="clear">人数限制</div>
							<div class="m-t">
								<input name="maxusers" class="form-control alert-success"
									value="${data.maxusers }" placeholder="请输入最大人数限制">
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">聊天室简介</div>
							<div class="clear m-t">
								<textarea name="desc" class="form-control alert-success"
									placeholder="请输入简介内容">${data.description }</textarea>
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
</section>
