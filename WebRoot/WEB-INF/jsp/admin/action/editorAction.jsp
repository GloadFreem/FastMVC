    <%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        $(".page").dropzone({
        url : "adminUploadImage.action?flag=actionStartPage"
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
        });
        </script>
        </head>
        <body>
        <div class="content">
        <a href="" id="model" target="blank" style="display:none"></a>
        <form action="adminAddAction.action" method="post">
        <!-- 序号 -->
        <c:choose>
            <c:when test="${action!=null}">
                <input style="color:black;visibility:hidden;" name="actionId"
                type="text" value=${action.actionId}>
            </c:when>
            <c:otherwise>
                <input style="visibility:hidden;" name="actionId" type="text"
                value="-1">
            </c:otherwise>
        </c:choose>
        <!-- 名称 -->
        <div class="name">
        <div class="name-key">名称</div>
        <div class="name-value">
        <c:choose>
            <c:when test="${action!=null}">
                <input style="color:black" name="name" type="text"
                value=${action.name}>
            </c:when>
            <c:otherwise>
                <input name="name" type="text" value="请输入名称">
            </c:otherwise>
        </c:choose>
        </div>
        </div>
        <!-- 描述 -->
        <div class="name">
        <div class="name-key">描述</div>
        <div class="name-value">
        <c:choose>
            <c:when test="${action!=null}">
                <textarea class="textarea" style="color:black" name="description">${action.description}</textarea>
            </c:when>
            <c:otherwise>
                <textarea class="textarea" name="description">请输入描述</textarea>
            </c:otherwise>
        </c:choose>
        </div>
        </div>
        <!-- 地址 -->
        <div class="name">
        <div class="name-key">地址</div>
        <div class="name-value">
        <c:choose>
            <c:when test="${action!=null}">
                <input style="color:black" name="address" type="text"
                value=${action.address}>
            </c:when>
            <c:otherwise>
                <input name="address" type="text" value="请输入地址">
            </c:otherwise>
        </c:choose>
        </div>
        </div>
        <!-- 限制人数 -->
        <div class="name">
        <div class="name-key">限制人数</div>
        <div class="name-value">
        <c:choose>
            <c:when test="${action!=null}">
                <input style="color:black" name="limit" type="text"
                value="${action.memberLimit}人">
            </c:when>
            <c:otherwise>
                <input name="limit" type="text" value="请输入限制人数">
            </c:otherwise>
        </c:choose>
        </div>
        </div>
        <!-- 类型 -->
        <div class="name">
        <div class="name-key">是否收费</div>
        <div class="name-value">
        <c:choose>
            <c:when test="${action!=null}">
                <c:choose>
                    <c:when test="${action.type==1}">
                        <select name="type">
                        <option selected="selected" value="1">免费</option>
                        <option value="2">收费</option>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <select name="type">
                        <option value="1">免费</option>
                        <option selected="selected" value="2">收费</option>
                        </select>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <select name="type">
                <option selected="selected" value="1">免费</option>
                <option value="2">收费</option>
                </select>
            </c:otherwise>
        </c:choose>
        </div>
        </div>
        <!-- 开始时间 -->
        <div class="name">
        <div class="name-key">开始时间</div>
        <div class="name-value">
        <c:choose>
            <c:when test="${action!=null}">
                <input style="color:black" id="beginTime" name="beginTime"
                type="text" value="${action.startTime}">
            </c:when>
            <c:otherwise>
                <input id="beginTime" name="beginTime" type="text"
                value="请选择开始时间">
            </c:otherwise>
        </c:choose>
        </div>
        </div>
        <!-- 结束时间 -->
        <div class="name">
        <div class="name-key">结束时间</div>
        <div class="name-value">
        <c:choose>
            <c:when test="${action!=null}">
                <input style="color:black" id="endTime" name="endTime"
                type="text" value="${action.endTime}">
            </c:when>
            <c:otherwise>
                <input id="endTime" name="endTime" type="text" value="请选择开始时间">
            </c:otherwise>
        </c:choose>
        </div>
        </div>

        <!-- 上传图片 -->
        <div class="name">
        <div class="name-key">
        添加封面(<font style="color:red">*图片地址可选填</font>)
        </div>
        <div class="action-value">
        <c:choose>
            <c:when test="${action!=null}">
                <div>
                <div class="add-img-input">
                <input class="" style="color:black;width:90%;height:35px;"
                name="startPageImage" type="text" value=${action.startPageImage}>
                </div>

                <div class="">
                <img alt="添加活动" src="images/圆角矩形-1-拷贝_50.png">
                </div>
                </div>
                <div>
                <img alt="" src=${action.startPageImage}>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                <div class="images"></div>
                <div class="add-img-input">
                <input class="add-img-input-style" name="startPageImage" type="text"
                value="请输入 图片地址(选填)">
                </div>
                <div class="add-item-img">
                <img alt="添加活动" src="images/圆角矩形-1-拷贝_50.png">
                </div>
                </div>

            </c:otherwise>
        </c:choose>
        </div>
        <div class="page dropzone needsclick" style="height:300px;width:90%"></div>
        </div>
        <!-- 上传图片 -->
        <div class="name">
        <div class="name-key">
        添加图片(<font style="color:red">*图片地址可选填</font>)
        </div>
        <div class="action-value">
        <c:choose>
            <c:when test="${action!=null}">
                <div>
                <div class="images">
                <div class="parentFileBox">
                <c:forEach items="${action.actionimages}" var="item"
                           varStatus="vs">
                    <ul class="fileBoxUl" style="width:15%;float:left">
                    <li id="fileBox" class="" style="margin-left:-35px;"><input
                    name="recordId" value="${item.imgId }" style="display:none">
                    <div class="viewThumb">
                    <img src="${item.url }">
                    </div>
                    <div style="display: block;" class="diyCancel"></div>
                    <div class="diyFileName">位置${vs.index }</div>
                    </ul>
                </c:forEach>
                </div>
                </div>
                <div class="add-img-input">
                <input class="add-img-input-style" style="color:black"
                name="image" type="text" value=${banner.image}>
                </div>

                <div class="add-item-img">
                <img alt="添加活动" src="images/圆角矩形-1-拷贝_50.png">
                </div>
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
                <div>
                <img alt=${banner.name } src=${banner.image}>
                </div>
            </c:otherwise>
        </c:choose>
        </div>
        <div class="upload dropzone needsclick"></div>
        </div>

        <div>
        <input class="banner-add-submit" type="submit" value="确认添加">
        </div>
        </form>
        </div>

        <!-- 内容 -->
        <div class="action-content">
        <c:forEach items="${action.actionintroduces}" var="content">
            <c:choose>
                <c:when test="${content.type==0}">
                    <div >
                    ${content.content}
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                    <img alt="" src=${content.content}/>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>

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