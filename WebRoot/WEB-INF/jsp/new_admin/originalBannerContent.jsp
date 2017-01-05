<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>资讯Banner内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editOriginalBanner.action?menu=2&sortmenu=0&submenu=3" method="post" enctype="multipart/form-data">
				<div class="">
				 <input name="contentId" value="${data.bannerId}" style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">内容描述</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
							<textarea name="desc" class="form-control alert-success"  placeholder="请输入内容描述">${data.desc }</textarea></div>
						</li>
						<li class="list-group-item">
							<div class="clear">图片</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<img alt="${data.desc }" src="${data.image }">
							<input name="image" class="form-control alert-success" value="${data.image }" placeholder="请输入内容链接"></div>
								
								<input name="file" id="input-1" type="file" class="file">
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">内容地址</div>
						</li>
						<li class="list-group-item">
							<input name="url" class="form-control alert-success" value="${data.url }" placeholder="请输入内容链接"></div>
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
