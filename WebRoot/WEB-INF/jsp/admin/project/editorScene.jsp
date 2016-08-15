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
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="js/jquery-ui/jquery-ui.js"></script>
<script src="js/dropzone.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<script type="text/javascript">
	jQuery(function($) {
		var media = $('#media')[0];
		var audioTimer = null;

		$(".upload").dropzone({
			url : "adminUploadImage.action?type=scene"
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

		$(".search-img").click(
				function() {
					$.ajax({
						url : "adminSearchProjectByName.action",
						data : {
							"name" : $("input[name='name']").val(),
						},
						success : function(data) {
							selector = $("select[name='projectId']");
							selector.empty();

							data.data.forEach(function(e) {
								select = "<option value='"+e.projectId+"'>"
										+ e.fullName + "</option>"
								selector.append(select);
							});

						}
					});

				});

		$("#projectId").change(
				function() {
					$("input[name='name']").val(
							$(this).find("option:selected").text());
				});

		$("#dialog-form")
				.dialog(
						{
							autoOpen : false,
							height : 300,
							width : 350,
							modal : true,
							buttons : {
								"添加" : function() {
									var formData = new FormData($('form')[1]);
									$
											.ajax({
												url : 'adminUploadImage.action?type=scene', //server script to process data
												type : 'POST',
												xhr : function() { // custom xhr
													myXhr = $.ajaxSettings
															.xhr();
													if (myXhr.upload) { // check if upload property exists
														myXhr.upload
																.addEventListener(
																		'progress',
																		progressHandlingFunction,
																		false); // for handling the progress of the upload
													}
													return myXhr;
												},
												//Ajax事件
												beforeSend : function() {
												},
												success : function(data) {
													filename = data.data;
													str = "<div class='content-item'><div style='content-item-tip'><input name='contentId' style='display:none'></div><div style='content-tip-img'><img alt='' src='"+filename+"'></div></div>";
													$(".contents:first")
															.append(str);
												},
												complete : function() {
													$("#submit").removeAttr(
															"disabled");
												},
												error : function(
														XMLHttpRequest,
														textStatus, errorThrown) {
													alert(XMLHttpRequest.status);
													alert(XMLHttpRequest.readyState);
													alert(errorThrown);
												},
												// Form数据
												data : formData,
												cache : false,
												processData : false,
												contentType : false,
											});

									$(this).dialog("close");
								},
								"取消" : function() {
									$(this).dialog("close");
								}
							},
							close : function() {
								//allFields.val("").removeClass("ui-state-error");
							}
						});

		$("#insert").click(function() {
			$("#dialog-form").dialog("open");
		});

		$("#file").change(function() {
			var file = this.files[0];
			name = file.name;
			size = file.size;
			type = file.type;
		});

		$("#audio").change(function() {
			var file = this.files[0];
			name = file.name;
			size = file.size;
			type = file.type;
			
			$("#audio_name").val(name);
			

		});

		function progressHandlingFunction(e) {
			if (e.lengthComputable) {
				$('progress').attr({
					value : e.loaded,
					max : e.total
				});
			}
		}

		//绑定播放暂停控制
		$('#play').bind('click', function() {
			playAudio();
		});

		//播放暂停切换
		function playAudio() {
			if (media.paused) {
				play();
			} else {
				pause();
			}
		}

		//播放
		function play() {
			media.play();
			$('#play').attr("src", "images/pause.png");
		}

		//暂停
		function pause() {
			media.pause();
			$('#play').attr("src", "images/播放.png");
		}

	});
</script>
<style>
body {
	font-size: 62.5%;
}

label, input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td, div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
</head>
<body>
	<div id="dialog-form" title="选择图片">
		<p class="validateTips">请选择PPT图片。</p>

		<form id="upload" action="adminUploadImage.action?type=scene"
			enctype="multipart/form-data" method="post">
			<fieldset>
				<label for="name">选择图片</label> <input type="file" name="file"
					id="file" class="text ui-widget-content ui-corner-all"
					accept="image/*">
			</fieldset>
		</form>
	</div>
	<div class="content">
		<form action="adminAddScene.action" method="post"
			enctype="multipart/form-data">
			<!-- 序号 -->
			<c:choose>
				<c:when test="${scene!=null}">
					<input style="color:black;visibility:hidden;" name="sceneId"
						type="text" value=${scene.sceneId}>
				</c:when>
				<c:otherwise>
					<input style="visibility:hidden;" name="sceneId" type="text"
						value="-1">
				</c:otherwise>
			</c:choose>
			<!-- 名称 -->
			<div class="name">
				<div class="name-key">所属路演</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${scene!=null}">
							<div class="search">
								<input style="color:black;width:95%" name="name" type="text"
									value=${scene.project.fullName}>
							</div>
							<div>
								<img name="search-img" class="search-img" alt=""
									src="../images/feeling/椭圆-2.png">
							</div>
							<div>
								<select class='user-select' name='projectId' id='projectId'></select>
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
								<select class='user-select' name='projectId' id='projectId'></select>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>


			<div class="name">
				<div class="name-key">现场</div>
				<div class="name-value">
					<c:choose>
						<c:when test="${scene!=null}">
							<div class="authinfo">
								<div class="authinfo-item">
									<!--  融资计划 -->
									<div class="name">
										<div class="name-key">音频地址</div>
										<div class="name-value">
											<input style="color:black" name="audio" id="audio"
												type="file" value="选择音频">
											<div>${scene.audioPath }</div>
										</div>
									</div>

									<!--  已融金额 -->
									<div class="name">
										<div class="name-key">音频名称</div>
										<div class="name-value">
											<input style="color:black" name="audio_name" id="audio_name"
												type="text" value="${scene.audioPath }">
										</div>
									</div>
									<c:choose>
										<c:when test="${scene.audioPath!=null && scene.audioPath!=''}">
											<!--  最低融资金额 -->
											<div class="name">
												<div class="name-key">音频</div>
												<div class="name-value">
													<div class="player">
														<div class="play-start">
															<img id="play" alt="" src="images/播放.png">
														</div>

														<div class="play-progress">
															<div class="progress-text">00:10:34</div>
															<div class="action-bar">
																<img alt="" src="images/圆角矩形-5.png">
															</div>
															<div class="start-text">00:00:00</div>
															<div class="progress-bar">
																<img alt="" src="images/圆角矩形-6.png">
															</div>
															<div class="insert">
																<img id="insert" alt="" src="images/插入PPT.png">
															</div>
														</div>
														<div class="contents"></div>
													</div>
												</div>
												<!-- 音频播放 -->
												<div>
													<audio id="media" src="${scene.audioPath }"></audio>
												</div>
										</c:when>
									</c:choose>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="authinfo">
								<div class="authinfo-item">
									<!--  融资计划 -->
									<div class="name">
										<div class="name-key">音频地址</div>
										<div class="name-value">
											<input style="color:black" name="audio" id="audio"
												type="file" value="选择音频">
											<div>${scene.audioPath }</div>
										</div>
									</div>

									<!--  已融金额 -->
									<div class="name">
										<div class="name-key">音频名称</div>
										<div class="name-value">
											<input style="color:black" name="audio_name" id="audio_name"
												type="text" value="${scene.audioPath }">
										</div>
									</div>
									<c:choose>
										<c:when test="${scene.audioPath!=null && scene.audioPath!=''}">
											<!--  最低融资金额 -->
											<div class="name">
												<div class="name-key">音频</div>
												<div class="name-value">
													<div class="player">
														<div class="play-start">
															<img id="play" alt="" src="images/播放.png">
														</div>

														<div class="play-progress">
															<div class="progress-text">00:10:34</div>
															<div class="action-bar">
																<img alt="" src="images/圆角矩形-5.png">
															</div>
															<div class="start-text">00:00:00</div>
															<div class="progress-bar">
																<img alt="" src="images/圆角矩形-6.png">
															</div>
															<div class="insert">
																<img id="insert" alt="" src="images/插入PPT.png">
															</div>
														</div>
														<div class="contents"></div>
													</div>
												</div>
												<!-- 音频播放 -->
												<div>
													<audio id="media" src="${scene.audioPath }"></audio>
												</div>
										</c:when>
									</c:choose>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
	</div>
	<div>
		<c:choose>
			<c:when test="${scene!=null}">
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