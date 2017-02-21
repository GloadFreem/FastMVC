<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>身份认证详情</p>
		</header>
		<section class="scrollable wrapper">
			<form action="adminAddAuthentic.action?menu=3&sortmenu=2&submenu=1"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${data.authId}" style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<!-- 用户姓名 -->
						<li class="list-group-item">
							<div class="clear">身份类型</div>
						</li>
						<li class="list-group-item"><select id="identityTypeId"
							name="identityTypeId"
							class="selectpicker show-menu-arrow form-control"
							data-max-options="2">
								<c:forEach items="${identities}" var="item">
									<option value="${item.identiyTypeId}"
										<c:choose>
							<c:when test="${data.identiytype.identiyTypeId==item.identiyTypeId}">
								  selected=selected
							</c:when>
						</c:choose>>${item.name }</option>
								</c:forEach>
						</select></li>

						<!--真实姓名 -->
						<li class="list-group-item">
							<div class="clear">真实姓名</div>
						</li>
						<li class="list-group-item"><input name="realName"
							class="form-control alert-success" value="${data.name }"
							placeholder="请输入真实姓名"></li>

						<!-- 身份证号码 -->
						<li class="list-group-item">
							<div class="clear">身份证号码</div>
						</li>
						<li class="list-group-item"><input name="identityCardNo"
							class="form-control alert-success" value="${data.identiyCarNo }"
							placeholder="请输入身份证号码"></li>

						<!-- 身份证正面 -->
						<li class="list-group-item">
							<div class="clear">身份证正面</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<a href="${data.identiyCarA }" target="blank"><img
									alt="${data.name }" src="${data.identiyCarA }" class="col-xs-1"></a>
								<input name="image" class="form-control alert-success"
									value="${data.identiyCarA }" placeholder="请输入图片链接"> <input
									name="identityCardA" id="input-1" type="file" class="file">
							</div>
						</li>
						<!-- 身份证反面 -->
						<li class="list-group-item">
							<div class="clear">身份证反面</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<a href="${data.identiyCarB }" target="blank"><img
									alt="${data.name }" src="${data.identiyCarB }" class="col-xs-1"></a>
								<input name="image" class="form-control alert-success"
									value="${data.identiyCarB }" placeholder="请输入图片链接"> <input
									name="identityCardB" id="input-1" type="file" class="file">
							</div>
						</li>

						<!-- 公司名称 -->
						<li class="list-group-item">
							<div class="clear">公司名称</div>
						</li>
						<li class="list-group-item"><input name="companyName"
							class="form-control alert-success" value="${data.companyName }"
							placeholder="请输入公司名称"></li>
						<!-- 公司地址 -->
						<li class="list-group-item">
							<div class="clear">公司地址</div>
						</li>
						<li class="list-group-item"><input name="companyAddress"
							class="form-control alert-success"
							value="${data.companyAddress }" placeholder="请输入公司地址"></li>
						<!-- 职位 -->
						<li class="list-group-item">
							<div class="clear">职位</div>
						</li>
						<li class="list-group-item"><input name="position"
							class="form-control alert-success" value="${data.position }"
							placeholder="请输入职位"></li>
						<!-- 营业执照 -->
						<li class="list-group-item">
							<div class="clear">营业执照</div>
						</li>
						<li class="list-group-item"><input name="bussinessNo"
							class="form-control alert-success"
							value="${data.buinessLicenceNo }" placeholder="请输入营业执照"></li>
						<!-- 个人介绍 -->
						<li class="list-group-item">
							<div class="clear">个人介绍</div>
						</li>
						<li class="list-group-item"><textarea name="introduce"
								class="form-control alert-success" 
								placeholder="请输入职位">${data.introduce }</textarea> <!-- 公司介绍 -->
						<li class="list-group-item">
							<div class="clear">公司介绍</div>
						</li>
						<li class="list-group-item"><textarea name="companyIntroduce"
								class="form-control alert-success"
								 placeholder="请输入公司简介">${data.companyIntroduce }</textarea></li>

						<!-- 所属行业 -->
						<li class="list-group-item">
							<div class="clear">所属行业</div>
						</li>
						<li class="list-group-item"><select id="areas" name="areas"
							class="selectpicker show-menu-arrow form-control"
							data-max-options="2">
								<c:forEach items="${areas}" var="o" varStatus="v">
									<option value=${v.index }
										<c:forEach items="${ext.areas}" var="e" varStatus="i">
															<c:choose>
																<c:when test="${v.index == e}">
																	 selected="selected"
																</c:when>
															</c:choose>
														</c:forEach>>${o.name}</option>
								</c:forEach>
						</select></li>
						<!-- 所属城市 -->
						<li class="list-group-item">
							<div class="clear">所属城市</div>
						</li>
						<li class="list-group-item"><select id="city" name="city"
							class="selectpicker show-menu-arrow form-control"
							data-max-options="2">
								<c:forEach items="${cities}" var="item">
									<option value="${item.cityId}"
										<c:choose>
							<c:when test="${data.city.cityId ==item.cityId}">
								  selected=selected
							</c:when>
						</c:choose>>${item.name }</option>
								</c:forEach>
						</select></li>
						<!-- 符合投资人协议标准 -->
						<li class="list-group-item">
							<div class="clear">符合投资人协议标准</div>
						</li>
						<li class="list-group-item"><select id="optional"
							name="optional" class="selectpicker show-menu-arrow form-control"
							data-max-options="2">
								<c:forEach items="${optional}" var="o" varStatus="v">
									<option value=${v.index }
										<c:forEach items="${ext.option}" var="e" varStatus="i">
															<c:choose>
																<c:when test="${v.index == e}">
																	 selected="selected"
																</c:when>
															</c:choose>
														</c:forEach>>${o}</option>
								</c:forEach>
						</select></li>
						<!-- 认证状态 -->
						<li class="list-group-item">
							<div class="clear">认证状态</div>
						</li>
						<li class="list-group-item"><select id="status" name="status"
							class="selectpicker show-menu-arrow form-control"
							data-max-options="2">
								<c:forEach items="${status}" var="s" varStatus="v">
									<option value=${s.statusId }
										<c:choose>
															<c:when test="${s.statusId==data.authenticstatus.statusId}">
																selected='selected'
															</c:when>
														</c:choose>>${s.name}</option>
								</c:forEach>
						</select></li>
					</ul>
				</div>
				<div>
					<button type="submit"
						class="btn btn-default btn-info pull-right m-t m-b m-r">完成</button>
				</div>
			</form>
		</section>
	</section>
