<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>课程内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editSpeecher.action?menu=1&sortmenu=4&submenu=1" method="post"
				enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.markerId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">姓名</div>
						</li>
						<li class="list-group-item"><input name="name"
							class="form-control alert-success" value="${data.name }"
							placeholder="请输入名称">
				</div>
				</li>

				<li class="list-group-item">
					<div class="clear">简介</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<textarea name="desc" class="form-control alert-success"
							placeholder="请输入内容描述">${data.desc }</textarea>
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">封面图片</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<img alt="${data.desc }" src="${data.image }"> <input
							name="image" class="form-control alert-success"
							value="${data.image }" placeholder="请输入内容链接">
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