<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>课程内容</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editWorker.action?menu=1&sortmenu=5&submenu=1" method="post"
				enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.wid}" style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">姓名</div>
						</li>
						<li class="list-group-item"><input name="name"
							class="form-control alert-success" value="${data.name }"
							placeholder="请输入姓名"></li>
						<li class="list-group-item">
							<div class="clear">手机号码</div>
						</li>
						<li class="list-group-item"><input name="telephone"
							class="form-control alert-success" value="${data.telephone }"
							placeholder="请输入手机号码"></li>
						<li class="list-group-item">
							<div class="clear">微信</div>
						</li>
						<li class="list-group-item"><input name="wcode"
							class="form-control alert-success" value="${data.wcode }"
							placeholder="请输入微信"></li>
					</ul>
				</div>
				<div>
					<button type="submit"
						class="btn btn-default btn-info pull-right m-t m-b m-r">完成</button>
				</div>
			</form>
		</section>
	</section>