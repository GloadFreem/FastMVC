<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>金指投科技 | Banner管理</title>
<link rel="stylesheet" type="text/css" href="css/banner.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />
<link rel="stylesheet" type="text/css" href="css/dropzone.css" />
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="js/dropzone.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(function($) {
		$(".upload").dropzone({
			url : "adminUploadImage.action?type=header"
		});
		$(".ida").dropzone({
			url : "adminUploadImage.action?type=idA"
		});
		$(".idb").dropzone({
			url : "adminUploadImage.action?type=idB"
		});
		
		
		$("input:eq(1)").blur(function() {
			$("input:eq(1)").css("background-color", "#D6D6FF");
			if ($("input:eq(1)").val() == "") {
				$("input:eq(1)").val("请输入名称");
			}
		});
		$("input:eq(1)").focus(function() {
			$("input:eq(1)").css("background-color", "#FFFFCC");
			if ($("input:eq(1)").val() == "请输入名称") {
				$("input:eq(1)").val("");
			}
		});
		$("input:eq(2)").blur(function() {
			$("input:eq(2)").css("background-color", "#D6D6FF");
			if ($("input:eq(2)").val() == "") {
				$("input:eq(2)").val("请输入描述");
			}
		});
		$("input:eq(2)").focus(function() {
			$("input:eq(2)").css("background-color", "#FFFFCC");
			if ($("input:eq(2)").val() == "请输入描述") {
				$("input:eq(2)").val("");
			}
		});
		$("input:eq(3)").blur(function() {
			$("input:eq(3)").css("background-color", "#D6D6FF");
			if ($("input:eq(3)").val() == "") {
				$("input:eq(3)").val("请输入链接地址");
			}
		});
		$("input:eq(3)").focus(function() {
			$("input:eq(3)").css("background-color", "#FFFFCC");
			if ($("input:eq(3)").val() == "请输入链接地址") {
				$("input:eq(3)").val("");
			}
		});
		$("input:eq(4)").blur(function() {
			$("input:eq(4)").css("background-color", "#D6D6FF");
			if ($("input:eq(4)").val() == "") {
				$("input:eq(4)").val("请输入 图片地址(选填)");
			}
		});
		$("input:eq(4)").focus(function() {
			$("input:eq(4)").css("background-color", "#FFFFCC");
			if ($("input:eq(4)").val() == "请输入 图片地址(选填)") {
				$("input:eq(4)").val("");
			}
		});
	});
</script>
</head>
<body>
	<div class="content">
		<form action="adminAddUser.action"  method="post">
			<!-- 序号 -->
			<c:choose>
				<c:when test="${user!=null}">
					<input style="color:black;visibility:hidden;" name="userId"
						type="text" value=${user.userId}>
				</c:when>
				<c:otherwise>
					<input style="visibility:hidden;" name="userId" type="text"
						value="-1">
				</c:otherwise>
			</c:choose>
			<!-- 名称 -->
			<div class="name">
				<div class="name-key">姓名</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${user!=null}">
							<input style="color:black" name="name" type="text"
								value=${user.name}>
						</c:when>
						<c:otherwise>
							<input name="name" type="text" value="请输入姓名">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 描述 -->
			<div class="name">
				<div class="name-key">手机号码</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${user!=null}">
							<input style="color:black" name="telephone" type="text"
								value=${user.telephone}>
						</c:when>
						<c:otherwise>
							<input name="telephone" type="text" value="请输入手机号码">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 密码 -->
			<div class="name">
				<div class="name-key">密码</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${user!=null}">
							<input style="color:black" name="password" type="text"
								value=${user.password}>
						</c:when>
						<c:otherwise>
							<input name="password" type="text" value="请输入密码">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 上传图片 -->
			<div class="name">
				<div class="name-key">
					上传头像图片(<font style="color:red">*图片地址可选填</font>)
				</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${user!=null}">
							<div>
								<input style="color:black" name="image" type="text"
									value=${user.headSculpture}>
							</div>
							<div>
								<img alt=${user.name } src=${user.headSculpture}>
							</div>

						</c:when>
						<c:otherwise>
							<input name="image" type="text" value="请输入 头像图片地址(选填)">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="upload dropzone needsclick"></div>
			</div>

			<!-- 设备类型 -->
			<div class="name">
				<div class="name-key">设备类型</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${user!=null}">
							<c:choose>
								<c:when test="${user.platform==1}">
									<select name="platform" id="platform">
										<option value="0">请选择设备类型</option>
										<option value="1" selected="selected">iPhone设备</option>
										<option value="2">Android设备</option>
									</select>
								</c:when>
								<c:otherwise>
									<select name="platform" id="platform">
										<option value="0">请选择设备类型</option>
										<option value="1">iPhone设备</option>
										<option value="2" selected="selected">Android设备</option>
									</select>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<select name="platform" id="platform">
								<option value="0" selected="selected">请选择设备类型</option>
								<option value="1">iPhone设备</option>
								<option value="2">Android设备</option>
							</select>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<!-- 微信id -->
			<div class="name">
				<div class="name-key">微信id</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${user!=null}">
							<input style="color:black" name="Str" id="Str" type="text"
								value=${user.wechatId}>
						</c:when>
						<c:otherwise>
							<input style="color:black" name="Str" id="Str" type="text"
								value="请输入微信识别码">
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<!-- 认证身份 -->
			<div class="name">
				<div class="name-key">身份认证信息</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${authentics!=null}">
							<c:forEach items="${user.authentics}" var="item" varStatus="vs">
								<div class="authinfo">
									<div class="authinfo-item">
										<!--  身份认证信息 -->
										<div class="name">
											<div class="name-key">身份类型</div>
											<div class="name-value">
												<c:choose>
													<c:when test="${item.identiytype.identiyTypeId==-1}">
														<select name="identityTypeId" id="identityTypeId">
															<option value="0" selected="selected">请选择身份类型</option>
															<option value="1">项目方</option>
															<option value="2">投资人</option>
															<option value="3">投资机构</option>
															<option value="4">智囊团</option>
														</select>
													</c:when>
													<c:when test="${item.identiytype.identiyTypeId==1}">
														<select name="identityTypeId" id="identityTypeId">
															<option value="0">请选择身份类型</option>
															<option value="1" selected="selected">项目方</option>
															<option value="2">投资人</option>
															<option value="3">投资机构</option>
															<option value="4">智囊团</option>
														</select>
													</c:when>
													<c:when test="${item.identiytype.identiyTypeId==2}">
														<select name="identityTypeId" id="identityTypeId">
															<option value="0">请选择身份类型</option>
															<option value="1">项目方</option>
															<option value="2" selected="selected">投资人</option>
															<option value="3">投资机构</option>
															<option value="4">智囊团</option>
														</select>
													</c:when>
													<c:when test="${item.identiytype.identiyTypeId==3}">
														<select name="identityTypeId" id="identityTypeId">
															<option value="0">请选择身份类型</option>
															<option value="1">项目方</option>
															<option value="2">投资人</option>
															<option value="3" selected="selected">投资机构</option>
															<option value="4">智囊团</option>
														</select>
													</c:when>
													<c:otherwise>
														<select name="identityTypeId" id="identityTypeId">
															<option value="0">请选择身份类型</option>
															<option value="1">项目方</option>
															<option value="2">投资人</option>
															<option value="3">投资机构</option>
															<option value="4" selected="selected">智囊团</option>
														</select>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<!--  真实姓名 -->
										<div class="name">
											<div class="name-key">真实姓名</div>
											<div class="name-value">
												<input style="color:black" name="realName" type="text"
													value=${item.name}>
											</div>
										</div>

										<!-- 身份证A面 -->
										<div class="name">
											<div class="name-key">
												上传身份证A面图片(<font style="color:red">*图片地址可选填</font>)
											</div>
											<div class="name-value">
												<div>
													<input style="color:black" name="identityCardA" type="text"
														value=${item.identiyCarA}>
												</div>
												<div>
													<img alt="" src=${item.identiyCarA}>
												</div>
											</div>
											<div class="ida dropzone needsclick"></div>
										</div>
										<!-- 身份证B面 -->
										<div class="name">
											<div class="name-key">
												上传身份证B面图片(<font style="color:red">*图片地址可选填</font>)
											</div>
											<div class="name-value">
												<div>
													<input style="color:black" name="identityCardB" type="text"
														value=${item.identiyCarB}>
												</div>
												<div>
													<img alt="" src=${item.identiyCarB}>
												</div>
											</div>
											<div class="idb dropzone needsclick"></div>
										</div>

										<!--  身份证号码 -->
										<div class="name">
											<div class="name-key">身份证号码</div>
											<div class="name-value">
												<input style="color:black" name="identityCardNo" type="text"
													value=${item.identiyCarNo}>
											</div>
										</div>

										<!--  公司名称-->
										<div class="name">
											<div class="name-key">公司名称</div>
											<div class="name-value">
												<input style="color:black" name="companyName" type="text"
													value=${item.companyName}>
											</div>
										</div>
										<!--  公司地址-->
										<div class="name">
											<div class="name-key">公司地址</div>
											<div class="name-value">
												<input style="color:black" name="companyAddress" type="text"
													value=${item.companyAddress}>
											</div>
										</div>
										<!--  职位-->
										<div class="name">
											<div class="name-key">职位</div>
											<div class="name-value">
												<input style="color:black" name="position" type="text"
													value=${item.position}>
											</div>
										</div>
										<!--  营业执照-->
										<div class="name">
											<div class="name-key">营业执照</div>
											<div class="name-value">
												<input style="color:black" name="bussinessNo" type="text"
													value=${item.buinessLicenceNo}>
											</div>
										</div>
										<!--  个人介绍-->
										<div class="name">
											<div class="name-key">个人介绍</div>
											<div class="name-value">
												<textarea class="textarea" name="introduce">${item.introduce}</textarea>
											</div>
										</div>
										<!--  公司介绍-->
										<div class="name">
											<div class="name-key">公司介绍</div>
											<div class="name-value">
												<textarea class="textarea" name="companyIntroduce">${item.companyIntroduce}</textarea>
											</div>
										</div>
										<!--  符合投资人协议表标准 -->
										<div class="name">
											<div class="name-key">符合投资人协议表标准</div>
											<div class="name-value">
												<select name="optional" id="optional" multiple="multiple">
													<option value="0">请选择符合投资人标准协议</option>
													<c:forEach items="${optional}" var="o" varStatus="v">
														<option value=${v.index }
															<c:forEach items="${ext[vs.index].option}" var="e" varStatus="i">
															<c:choose>
																<c:when test="${v.index == e}">
																	 selected="selected"
																</c:when>
															</c:choose>
														</c:forEach>>${o}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!--  所属行业 -->
										<div class="name">
											<div class="name-key">所属行业</div>
											<div class="name-value">
												<select name="areas" id="areas" multiple="multiple">
													<option value="0">请选择行业类型</option>
													<c:forEach items="${areas}" var="o" varStatus="v">
														<option value=${o.areaId }
															<c:forEach items="${ext[vs.index].areas}" var="e" varStatus="i">
															<c:choose>
																<c:when test="${o.areaId == e}">
																	 selected="selected"
																</c:when>
															</c:choose>
														</c:forEach>>${o.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!--  城市 -->
										<div class="name">
											<div class="name-key">城市</div>
											<div class="name-value">
												<select name="city" id="city">
													<option value="0">城市</option>

													<c:forEach items="${cities}" var="c" varStatus="v">
														<option value=${c.cityId }
															<c:if test="${c.cityId==item.city.cityId }">
															selected="selected"
														</c:if>>${c.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="authinfo">
								<div class="authinfo-item">
									<!--  身份认证信息 -->
									<div class="name">
										<div class="name-key">身份类型</div>
										<div class="name-value">
											<select name="identityTypeId" id="identityTypeId">
												<option value="0" selected="selected">请选择身份类型</option>
												<option value="1">项目方</option>
												<option value="2">投资人</option>
												<option value="3">投资机构</option>
												<option value="4">智囊团</option>
											</select>
										</div>
									</div>
									<!--  真实姓名 -->
									<div class="name">
										<div class="name-key">真实姓名</div>
										<div class="name-value">
											<input style="color:black" name="realName" type="text"
												value="请输入真实姓名">
										</div>
									</div>

									<!-- 身份证A面 -->
									<div class="name">
										<div class="name-key">
											上传身份证A面图片(<font style="color:red">*图片地址可选填</font>)
										</div>
										<div class="name-value">
											<div>
												<input style="color:black" name="identityCardA" type="text"
													value="请输入身份证A面地址">
											</div>
										</div>
										<div class="ida dropzone needsclick"></div>
									</div>
									<!-- 身份证B面 -->
									<div class="name">
										<div class="name-key">
											上传身份证B面图片(<font style="color:red">*图片地址可选填</font>)
										</div>
										<div class="name-value">
											<div>
												<input style="color:black" name="identityCardB" type="text"
													value="请输入身份证B面地址">
											</div>
										</div>
										<div class=" dropzone needsclick"></div>
									</div>

									<!--  身份证号码 -->
									<div class="name">
										<div class="name-key">身份证号码</div>
										<div class="name-value">
											<input style="color:black" name="identityCardNo" type="text"
												value="请输入身份证号码">
										</div>
									</div>

									<!--  公司名称-->
									<div class="name">
										<div class="name-key">公司名称</div>
										<div class="name-value">
											<input style="color:black" name="companyName" type="text"
												value="请输入公司名称">
										</div>
									</div>
									<!--  公司地址-->
									<div class="name">
										<div class="name-key">公司地址</div>
										<div class="name-value">
											<input style="color:black" name="companyAddress" type="text"
												value="请输入公司地址">
										</div>
									</div>
									<!--  职位-->
									<div class="name">
										<div class="name-key">职位</div>
										<div class="name-value">
											<input style="color:black" name="position" type="text"
												value="请输入职位">
										</div>
									</div>
									<!--  营业执照-->
									<div class="name">
										<div class="name-key">营业执照</div>
										<div class="name-value">
											<input style="color:black" name="bussinessNo" type="text"
												value="请输入营业执照号">
										</div>
									</div>
									<!--  个人介绍-->
									<div class="name">
										<div class="name-key">个人介绍</div>
										<div class="name-value">
											<textarea class="textarea" name="introduce"></textarea>
										</div>
									</div>
									<!--  公司介绍-->
									<div class="name">
										<div class="name-key">公司介绍</div>
										<div class="name-value">
											<textarea class="textarea" name="companyIntroduce"></textarea>
										</div>
									</div>
									<!--  符合投资人协议表标准 -->
									<div class="name">
										<div class="name-key">符合投资人协议表标准</div>
										<div class="name-value">
											<select name="optional" id="optional" multiple="multiple">
												<option value="0">请选择符合投资人标准协议</option>
												<c:forEach items="${optional}" var="o" varStatus="v">
													<option value=${v.index }>${o}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!--  所属行业 -->
									<div class="name">
										<div class="name-key">所属行业</div>
										<div class="name-value">
											<select name="areas" id="areas" multiple="multiple">
												<option value="0">请选择行业类型</option>
												<c:forEach items="${areas}" var="o" varStatus="v">
													<option value=${v.index }>${o.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<!--  城市 -->
									<div class="name">
										<div class="name-key">城市</div>
										<div class="name-value">
											<select name="city" id="city">
												<option value="0">城市</option>
												<c:forEach items="${cities}" var="c" varStatus="v">
													<option value=${v.index }>${c.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
	</div>
	<div>
		<c:choose>
			<c:when test="${user!=null}">
				<input class="banner-add-submit" type="submit" value="确认更新">

			</c:when>
			<c:otherwise>
				<input class="banner-add-submit" type="submit" value="确认添加">
			</c:otherwise>
		</c:choose>
	</div>
	</form>
	</div>
</body>
</html>