
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
<link rel="stylesheet" type="text/css" href="css/action.css" />
<link rel="stylesheet" type="text/css" href="css/dropzone.css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="upload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="upload/css/diyUpload.css">


<script src="upload/js/jquery.js"></script>
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="js/dropzone.js" type="text/javascript"></script>

<script type="text/javascript"
	src="upload/js/webuploader.html5only.min.js"></script>
<script type="text/javascript" src="upload/js/diyUpload.js"></script>

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
				$("input:eq(3)").val("请选择时间");
			}
		});
		$("input:eq(3)").focus(function() {
			$("input:eq(3)").css("background-color", "#FFFFCC");
			if ($("input:eq(3)").val() == "请选择时间") {
				$("input:eq(3)").val("");
			}
		});
		$("input:eq(4)").blur(function() {
			$("input:eq(4)").css("background-color", "#D6D6FF");
			if ($("input:eq(4)").val() == "") {
				$("input:eq(4)").val("请选择时间");
			}
		});
		$("input:eq(4)").focus(function() {
			$("input:eq(4)").css("background-color", "#FFFFCC");
			if ($("input:eq(4)").val() == "请选择时间") {
				$("input:eq(4)").val("");
			}
		});

		$(".add-img-input-style").blur(function() {
			$(".add-img-input-style").css("background-color", "#D6D6FF");
			if ($(".add-img-input-style").val() == "") {
				$(".add-img-input-style").val("请输入 图片地址(选填)");
				$(".add-item-img img").css("visibility", "hidden");
			} else if ($(".add-img-input-style").val() != "请输入 图片地址(选填)") {
				$(".add-item-img img").css("visibility", "visible");
			}
		});
		$(".add-img-input-style").focus(function() {
			$(".add-img-input-style").css("background-color", "#FFFFCC");
			if ($(".add-img-input-style").val() == "请输入 图片地址(选填)") {
				$(".add-img-input-style").val("");
			} else {
				$(".add-item-img img").css("visibility", "visible");
			}
		});

		$(".add-item-img img")
				.click(
						function() {
							$
									.ajax({
										url : "adminAddActionImage.action",
										data : {
											"actionId" : $(
													"input[name='actionId']")
													.val(),
											"name" : $(".add-img-input-style")
													.val(),
										},
										success : function(data) {
											item = "<img alt='' src=";
        item+=data.image;
        item+=" />";
											$("div.images").append(item);
										}
									});

						});

		$(".diyCancel").click(function() {
			el = $(this);
			//删除
			$.ajax({
				url : "adminDeleteActionImage.action",
				data : {
					"recordId" : $(this).parent("li").children("input").val(),
				},
				success : function(data) {
					//alert(data.message);
					obj = el.parent("li").parent("ul");
					obj.remove();
				}
			});
		});

		$(".viewThumb").click(function() {
			src = $(this).children("img").attr("src");
			a = $("#model");
			a.attr("href", src);
			$("a")[0].click();
		});
		
		$(".search-img").click(
			function() {
				$.ajax({
					url : "adminSearchActionByName.action",
					data : {
						"name" : $("input[name='name']").val(),
					},
					success : function(data) {
						selector = $("select[name='actionId']");
						selector.empty();

						data.data.forEach(function(e) {
							select = "<option value='"+e.actionId+"'>" + e.name
									+ "</option>"
							selector.append(select);
						});

					}
				});

			});

	$("#actionId").change(function() {
		$("input[name='name']").val($(this).find("option:selected").text());
	});

    $("#type").change(function() {
        alert($(this).val());
    });
	
	});

	
</script>
</head>
<body>
	<div class="content">
		<a href="" id="model" target="blank" style="display:none"></a>
		<form action="adminAddActionContent.action" method="post">
			<!-- 序号 -->
			<c:choose>
				<c:when test="${content!=null}">
					<input style="color:black;visibility:hidden;" name="contentId"
						type="text" value=${content.introduceId}>
				</c:when>
				<c:otherwise>
					<input style="visibility:hidden;" name="contentId" type="text"
						value="-1">
				</c:otherwise>
			</c:choose>
			<!-- 名称 -->
			<div class="name">
				<div class="name-key">所属活动</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${content!=null}">
							<div class="search">
								<input style="color:black;width:95%" name="name" type="text"
									value=${content.action.name}>
							</div>
							<div>
								<img name="search-img" class="search-img" alt=""
									src="../images/feeling/椭圆-2.png">
							</div>
							<div>
								<select class='user-select' name='actionId' id='actionId'></select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="search">
								<input style="color:black;width:95%" id="name" name="name"
									type="text" value="请选择路演">
							</div>
							<div>
								<img name="search-img" class="search-img" alt=""
									src="../images/feeling/椭圆-2.png">
							</div>
							<div>
								<select class='user-select' name='actionId' id='actionId'></select>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- 类型 -->
			<div class="name">
				<div class="name-key">类型</div>
				<div class="name-value">
					<select name="type" id="type">
						<c:forEach items="${types}" var="c" varStatus="v">
							<option value=${v.index
                }
								<c:choose>
                <c:when test="${v.index == content.type }">
                    selected="selected"
                </c:when>
            </c:choose>>${c}</option>
						</c:forEach>

					</select>
				</div>
			</div>

			<c:choose>
				<c:when test="${content!=null}">
					<c:choose>
						<c:when test="${content.type==0}">
							<!-- 描述 -->
							<div class="name">
								<div class="name-key">内容</div>
								<div class="name-value">
									<textarea class="textarea" style="color:black"
										name="content">${content.content}</textarea>
						</c:when>
					</c:choose>

				</c:when>
				<c:otherwise>
					<!-- 描述 -->
					<div class="name">
						<div class="name-key">内容</div>
						<div class="name-value">
							<textarea class="textarea" name="content">请输入描述</textarea>
				</c:otherwise>
			</c:choose>
	</div>
	</div>

	<c:choose>
		<c:when test="${content.type!=0}">
			<!-- 上传图片 -->
			<div class="name">
				<div class="name-key">
					添加图片(<font style="color:red">*图片地址可选填</font>)
				</div>
				<div class="action-value">
					<c:choose>
						<c:when test="${content!=null}">
							<div>
								<div class="add-img-input">
									<input class="add-img-input-style" style="color:black"
										name="content" type="text" value=${content.content}>
								</div>

								<div class="add-item-img">
									<img alt="添加活动" src="images/圆角矩形-1-拷贝_50.png">
								</div>
							</div>
							<div>
								<img alt=${content.action.name }
									src=${content.content
									} style="width:30%">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<div class="images"></div>
								<div class="add-img-input">
									<input class="add-img-input-style" name="image" type="text"
										value="请输入 图片地址(选填)">
								</div>
								<div class="add-item-img">
									<img alt="添加活动" src="images/圆角矩形-1-拷贝_50.png">
								</div>
							</div>

						</c:otherwise>
					</c:choose>
				</div>
				<div class="upload dropzone needsclick"></div>
			</div>
		</c:when>
	</c:choose>
	<div>
		<input class="banner-add-submit" type="submit" value="确认添加">
	</div>
	</form>
	</div>
</body>

<script src="js/jquery.js"></script>
<script src="js/jquery.datetimepicker.full.js"></script>
<script>
	$.datetimepicker.setLocale('ch');

	$('#beginTime').datetimepicker({
		lang : 'ch',
		format : 'Y-m-d H:m:s',
		formatDate : 'Y-m-d H:m:s',
		todayButton : true
	});
	$('#endTime').datetimepicker({
		lang : 'ch',
		format : 'Y-m-d H:m:s',
		formatDate : 'Y-m-d H:m:s',
		todayButton : true
	});
</script>

</html>