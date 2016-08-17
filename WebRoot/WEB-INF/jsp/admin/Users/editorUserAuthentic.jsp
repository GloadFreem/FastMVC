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
<link rel="stylesheet" type="text/css" href="css/authentic.css" />
<link rel="stylesheet" type="text/css" href="css/dropzone.css" />
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="js/dropzone.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(function($) {
		$(".upload").dropzone({
			url : "adminUploadImage.action?flag=images"
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
		<form action="adminAddAuthentic.action"  method="post">
			<!-- 序号 -->
			<c:choose>
				<c:when test="${authentic!=null}">
					<input style="color:black;visibility:hidden;" name="authId"
						type="text" value=${authentic.authId}>
				</c:when>
				<c:otherwise>
					<input style="visibility:hidden;" name="authId" type="text"
						value="-1">
				</c:otherwise>
			</c:choose>

			<!-- 认证身份 -->
			<div class="name">
				<div class="name-key">身份认证信息</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${authentic!=null}">
							<div class="authinfo">
									<div class="authinfo-item">
										<!--  身份认证信息 -->
										<div class="name">
											<div class="name-key">身份类型</div>
											<div class="name-value">
												<c:choose>
													<c:when test="${authentic.identiytype.identiyTypeId==-1}">
														<select name="identityTypeId" id="identityTypeId">
															<option value="1">项目方</option>
															<option value="2">投资人</option>
															<option value="3">投资机构</option>
															<option value="4">智囊团</option>
														</select>
													</c:when>
													<c:when test="${authentic.identiytype.identiyTypeId==1}">
														<select name="identityTypeId" id="identityTypeId">
															<option value="1" selected="selected">项目方</option>
															<option value="2">投资人</option>
															<option value="3">投资机构</option>
															<option value="4">智囊团</option>
														</select>
													</c:when>
													<c:when test="${authentic.identiytype.identiyTypeId==2}">
														<select name="identityTypeId" id="identityTypeId">
															<option value="1">项目方</option>
															<option value="2" selected="selected">投资人</option>
															<option value="3">投资机构</option>
															<option value="4">智囊团</option>
														</select>
													</c:when>
													<c:when test="${authentic.identiytype.identiyTypeId==3}">
														<select name="identityTypeId" id="identityTypeId">
															<option value="1">项目方</option>
															<option value="2">投资人</option>
															<option value="3" selected="selected">投资机构</option>
															<option value="4">智囊团</option>
														</select>
													</c:when>
													<c:otherwise>
														<select name="identityTypeId" id="identityTypeId">
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
													value=${authentic.name}>
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
														value=${authentic.identiyCarA}>
												</div>
												<div>
													<img alt=${authentic.name } src=${authentic.identiyCarA}>
												</div>
											</div>
											<div class="upload dropzone needsclick"></div>
										</div>
										<!-- 身份证B面 -->
										<div class="name">
											<div class="name-key">
												上传身份证B面图片(<font style="color:red">*图片地址可选填</font>)
											</div>
											<div class="name-value">
												<div>
													<input style="color:black" name="identityCardB" type="text"
														value=${authentic.identiyCarB}>
												</div>
												<div>
													<img alt=${authentic.name } src=${authentic.identiyCarB}>
												</div>
											</div>
											<div class="upload dropzone needsclick"></div>
										</div>

										<!--  身份证号码 -->
										<div class="name">
											<div class="name-key">身份证号码</div>
											<div class="name-value">
												<input style="color:black" name="identityCardNo" type="text"
													value=${authentic.identiyCarNo}>
											</div>
										</div>

										<!--  公司名称-->
										<div class="name">
											<div class="name-key">公司名称</div>
											<div class="name-value">
												<input style="color:black" name="companyName" type="text"
													value=${authentic.companyName}>
											</div>
										</div>
										<!--  公司地址-->
										<div class="name">
											<div class="name-key">公司地址</div>
											<div class="name-value">
												<input style="color:black" name="companyAddress" type="text"
													value=${authentic.companyAddress}>
											</div>
										</div>
										<!--  职位-->
										<div class="name">
											<div class="name-key">职位</div>
											<div class="name-value">
												<input style="color:black" name="position" type="text"
													value=${authentic.position}>
											</div>
										</div>
										<!--  营业执照-->
										<div class="name">
											<div class="name-key">营业执照</div>
											<div class="name-value">
												<input style="color:black" name="bussinessNo" type="text"
													value=${authentic.buinessLicenceNo}>
											</div>
										</div>
										<!--  个人介绍-->
										<div class="name">
											<div class="name-key">个人介绍</div>
											<div class="name-value">
												<textarea class="textarea" name="introduce">${authentic.introduce}</textarea>
											</div>
										</div>
										<!--  公司介绍-->
										<div class="name">
											<div class="name-key">公司介绍</div>
											<div class="name-value">
												<textarea class="textarea" name="companyIntroduce">${authentic.companyIntroduce}</textarea>
											</div>
										</div>
										<!--  符合投资人协议表标准 -->
										<div class="name">
											<div class="name-key">符合投资人协议表标准</div>
											<div class="name-value">
												<select name="optional" id="optional" multiple="multiple">
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
												</select>
											</div>
										</div>
										<!--  所属行业 -->
										<div class="name">
											<div class="name-key">所属行业</div>
											<div class="name-value">
												<select name="areas" id="areas" multiple="multiple">
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
														<c:choose>
															<c:when test="${c.cityId==authentic.city.cityId}">
																selected='selected'
															</c:when>
														</c:choose>
													>${c.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!--  认证状态 -->
										<div class="name">
											<div class="name-key">认证状态</div>
											<div class="name-value">
												<select name="status" id="status">
													<c:forEach items="${status}" var="s" varStatus="v">
														<option value=${s.statusId }
														<c:choose>
															<c:when test="${s.statusId==authentic.authenticstatus.statusId}">
																selected='selected'
															</c:when>
														</c:choose>
														>${s.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										
										
									</div>
								</div>
						</c:when>
						<c:otherwise>
							<div class="authinfo">
								<div class="authinfo-item">
									<!--  身份认证信息 -->
									<div class="name">
										<div class="name-key">身份类型</div>
										<div class="name-value">
											<select name="identityTypeId" id="identityTypeId">
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
										<div class="upload dropzone needsclick"></div>
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
										<div class="upload dropzone needsclick"></div>
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
									
										<!--  认证状态 -->
										<div class="name">
											<div class="name-key">认证状态</div>
											<div class="name-value">
												<select name="city" id="city">
													<c:forEach items="${identities}" var="c" varStatus="v">
														<option value=${c.identiyTypeId }>${c.name}</option>
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
			<c:when test="${authentic!=null}">
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