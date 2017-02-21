<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>用户详情</p>
		</header>
		<section class="scrollable wrapper">
			<form action="adminAddUser.action?menu=3&sortmenu=1&submenu=1"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.userId}" style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<!-- 用户姓名 -->
						<li class="list-group-item">
							<div class="clear">姓名</div>
						</li>
						<li class="list-group-item"><input name="name"
							class="form-control alert-success" value="${data.name }"
							placeholder="请输入用户姓名"></li>

						<!--手机号码  -->
						<li class="list-group-item">
							<div class="clear">手机号码</div>
						</li>
						<li class="list-group-item"><input name="telephone"
							class="form-control alert-success" value="${data.telephone }"
							placeholder="请输入手机号码"></li>

						<!-- 密码 -->
						<li class="list-group-item">
							<div class="clear">密码</div>
						</li>
						<li class="list-group-item"><input name="password" disabled="disabled"
							class="form-control alert-success" value="${data.password }"
							placeholder="请输入密码"></li>

						<!-- 用户头像 -->
						<li class="list-group-item">
							<div class="clear">头像</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<a href="${data.headSculpture }" target="blank"><img
									alt="${data.name }" src="${data.headSculpture }"
									class="col-xs-1"></a> <input name="image"
									class="form-control alert-success"
									value="${data.headSculpture }" placeholder="请输入图片链接"> <input
									name="file" id="input-1" type="file" class="file">
							</div>
						</li>

						<!-- 设备类型 -->

						<li class="list-group-item">
							<div class="clear">设备类型</div>
						</li>
						<li class="list-group-item"><select id="platform" name="platform"
							class="selectpicker show-menu-arrow form-control"
							data-max-options="2">
								<c:choose>
									<c:when test="${data.platform==1}">
										<option value="1" selected="selected">iOS设备</option>
										<option value="2">Android设备</option>
									</c:when>
									<c:otherwise>
										<option value="1">iOS设备</option>
										<option value="2" selected="selected">Android设备</option>
									</c:otherwise>
								</c:choose>

						</select></li>

						<!-- 微信openId -->
						<li class="list-group-item">
							<div class="clear">微信openId</div>
						</li>
						<li class="list-group-item"><input name="openId"
							class="form-control alert-success" value="${data.wechatId }"
							placeholder="请输入微信openId"></li>


						<!-- 认证信息 -->
						<li class="list-group-item">
							<div class="clear">认证信息</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<div class="table-responsive">
									<table
										class="table table-striped b-t b-light text-sm text-center-xs">
										<thead>
											<tr>
												<th width="20"><input type="checkbox"></th>
												<th width="80" class="th-sortable" data-toggle="class">序号
													<span class="th-sort"> <i
														class="fa fa-sort-down text"></i> <i
														class="fa fa-sort-up text-active"></i> <i
														class="fa fa-sort"></i>
												</span>
												</th>
												<th width="150">姓名</th>
												<th width="10%">身份类型</th>
												<th width="150">身份证A面</th>
												<th>身份证号</th>
												<th>审核状态</th>
												<th>排序</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${data.authentics != null}">
													<c:forEach items="${data.authentics}" var="item"
														varStatus="status">
														<tr>
															<td><input type="checkbox" name="post[]"
																value="${item.authId}"></td>
															<td>${item.authId}</td>
															<td>${item.name}</td>
															<td>${item.identiytype.name}</td>
															<td><a href="${item.identiyCarA}" /><img
																alt="${item.name}" src="${item.identiyCarA}"
																class="col-xs-12"></a></td>
															<td>${item.identiyCarNo}</td>
															<td><c:choose>
																	<c:when test="${ item.authenticstatus.statusId==6}">
											未认证
											</c:when>
																	<c:when test="${ item.authenticstatus.statusId==7}">
											认证中
											</c:when>
																	<c:when test="${ item.authenticstatus.statusId==8}">
											已认证
											</c:when>
																	<c:otherwise>认证失败</c:otherwise>
																</c:choose></td>
															<td>${item.sortIndex}</td>
															<td><a
																href="authenticDetail.action?contentId=${item.authId }&menu=3&sortmenu=2&submenu=1"
																class="active"><i
																	class="fa fa-edit text-success text-active"></i><i
																	class="fa fa-edit text-danger text"></i></a> | <a
																href="#modal"
																data-href="deleteUser.action?contentId=${item.authId }&menu=3&sortmenu=1&submenu=1"
																data-toggle="modal" class="active"><i
																	class="fa fa-trash-o text-success text-active"></i><i
																	class="fa fa-trash-o text-danger text"></i></a>
														</tr>
													</c:forEach>
												</c:when>
											</c:choose>

										</tbody>
									</table>
								</div>
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

