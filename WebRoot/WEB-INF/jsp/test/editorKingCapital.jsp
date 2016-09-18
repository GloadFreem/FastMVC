    <%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta charset="utf-8" />
        <meta name="viewport"
        content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <link rel="stylesheet" href="./kindeditor-master/lib/qunit/qunit.css" />
        <link rel="stylesheet"
        href="./kindeditor-master/plugins/code/prettify.css" />
        <link rel="stylesheet"
        href="./kindeditor-master/themes/default/default.css" />
        <link rel="stylesheet"
        href="./kindeditor-master/themes/default/style.css" />
        <link rel="stylesheet" type="text/css" href="css/user.css" />

        <script src="upload/js/jquery.js"></script>
        <script src="js/jquery-ui/jquery-ui.js"></script>
        <!-- <script src="./kindeditor-master/lib/firebug-lite/build/firebug-lite.js#startOpened"></script> -->
        <script src="./kindeditor-master/lib/qunit/qunit.js"></script>
        <!-- include src files -->
        <script src="./kindeditor-master/kindeditor-all.js"></script>
        <script src="./kindeditor-master/lang/zh-CN.js"></script>
        <script src="./kindeditor-master/plugins/code/prettify.js"></script>

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


        </head>

        <script language="JavaScript">
        jQuery(function($) {
        $(".upload").dropzone({
        url : "adminUploadImage.action?flag=images"
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
        });
        KindEditor.ready(function(K) {
        var editor1 = K.create('textarea[name="content"]', {
        cssPath : './kindeditor-master/plugins/code/prettify.css',
        uploadJson : './kindeditor-master/jsp/upload_json.jsp',
        fileManagerJson : './kindeditor-master/jsp/file_manager_json.jsp',
        allowFileManager : true,
        afterCreate : function() {
        var self = this;
        K.ctrl(document, 13, function() {
        self.sync();
        document.forms['example'].submit();
        });
        K.ctrl(self.edit.doc, 13, function() {
        self.sync();
        document.forms['example'].submit();
        });
        }
        });
        prettyPrint();
        });
        </script>
        <body>
        <form action="adminAddKingCapital.action" method="post">
        <input name="contentId" value="${content.recordId }"
        style="visibility:hidden" />

        <!-- 标题 -->
        <div class="name">
        <div class="name-key">文章标题</div>
        <div class="name-enter">
        <c:choose>
            <c:when test="${content!=null }">
                <input id="title" name="title" class="add-img-input-style"
                type="text" value="${content.title }"/>
            </c:when>
            <c:otherwise>
                <input id="title" name="title" class="add-img-input-style"
                type="text" value=""/>
            </c:otherwise>
        </c:choose>
        </div>
        </div> <!-- 标签 -->
        <div class="name">
        <div class="name-key">文章标签</div>
        <div class="name-enter">
        <c:choose>
            <c:when test="${content!=null }">
                <input id="tag" name="tag" class="add-img-input-style"
                type="text" value="${content.tag }"/>
            </c:when>
            <c:otherwise>
                <input id="tag" name="tag" class="add-img-input-style"
                type="text" value=""/>
            </c:otherwise>
        </c:choose>
        </div>
        </div> <!-- 来源 -->
        <div class="name">
        <div class="name-key">文章来源</div>
        <div class="name-enter">
        <c:choose>
            <c:when test="${content!=null }">
                <input id="orignal" name="orignal" class="add-img-input-style"
                type="text" value="${content.orignal }"/>
            </c:when>
            <c:otherwise>
                <input id="orignal" name="orignal" class="add-img-input-style"
                type="text" value="商业计划"/>
            </c:otherwise>
        </c:choose>
        </div>
        </div> <!-- 链接 -->
        <div class="name">
        <div class="name-key">链接</div>
        <div class="name-enter">
        <c:choose>
            <c:when test="${content!=null }">
                <input id="url" name="url" class="add-img-input-style"
                type="text" value="${content.url }"/>
            </c:when>
            <c:otherwise>
                <input id="url" name="url" class="add-img-input-style"
                type="text" value=""/>
            </c:otherwise>
        </c:choose>
        </div>
        </div>
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
                name="image" type="text" value=${content.image}>
                </div>

                <div class="add-item-img">
                <img alt="添加活动" src="images/圆角矩形-1-拷贝_50.png">
                </div>
                </div>
                <div>
                <img alt=${content.title } src=${content.image}>
                </div>
            </c:when>
            <c:otherwise>
                <div>
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
        <!-- 图片模式 -->
        <div class="name">
        <div class="name-key">图文模式</div>
        <div class="name-enter">
        <select name="flag" id="flag">
        <option value="0">小图</option>
        <option value="1">大图</option>
        </select>
        </div>
        </div>
        <!-- 开始时间 -->
        <div class="name">
        <div class="name-key">创建时间</div>
        <div class="name-value">
        <c:choose>
            <c:when test="${content!=null}">
                <input style="color:black" id="beginTime" name="beginTime"
                type="text" value="${content.createDate}">
            </c:when>
            <c:otherwise>
                <input id="beginTime" name="beginTime" type="text"
                value="请选择开始时间">
            </c:otherwise>
        </c:choose>
        </div>
        </div>
        <!--编辑区域-->
        <div class="editor">
        <c:choose>
            <c:when test="${content!=null }">
                <textarea id="editor_id" name="content"
                style="width:90%;visibility:hidden;height:1450px;">${content.content }</textarea>
            </c:when>
            <c:otherwise>
                <textarea id="editor_id" name="content"
                style="width:90%;visibility:hidden;height:1450px;"></textarea>
            </c:otherwise>
        </c:choose>

        </div>
        <!--保存网页-->
        <div class="submit-button">
        <div class="submmit-button-item">
        <input id="save-button" type="submit" value="保存">
        </div>

        </div>
        </form>

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
        </script>
        </html>
