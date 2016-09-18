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
        <link rel="stylesheet" type="text/css" href="css/reset.css"
        media="screen" />
        <link rel="stylesheet" type="text/css" href="css/text.css"
        media="screen" />
        <link rel="stylesheet" type="text/css" href="css/grid.css"
        media="screen" />
        <link rel="stylesheet" type="text/css" href="css/layout.css"
        media="screen" />
        <link rel="stylesheet" type="text/css" href="css/nav.css" media="screen" />
        <!--[if IE 6]><link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen" /><![endif]-->
        <!--[if IE 7]><link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" /><![endif]-->
        <link href="css/table/demo_page.css" rel="stylesheet" type="text/css" />

        <!-- DataTables CSS -->
        <link rel="stylesheet" type="text/css"
        href="table/css/jquery.dataTables.css">
        <!-- BEGIN: load jquery -->
        <!-- <script src="js/jquery-1.6.4.min.js" type="text/javascript"></script> -->
        <!-- jQuery -->
        <script type="text/javascript" charset="utf8"
        src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery-ui/jquery.ui.core.min.js"></script>
        <script src="js/jquery-ui/jquery.ui.widget.min.js"
        type="text/javascript"></script>
        <script src="js/jquery-ui/jquery.ui.accordion.min.js"
        type="text/javascript"></script>
        <script src="js/jquery-ui/jquery.effects.core.min.js"
        type="text/javascript"></script>
        <script src="js/jquery-ui/jquery.effects.slide.min.js"
        type="text/javascript"></script>
        <script src="js/jquery-ui/jquery.ui.mouse.min.js" type="text/javascript"></script>
        <script src="js/jquery-ui/jquery.ui.sortable.min.js"
        type="text/javascript"></script>
        <script src="table/js/jquery.dataTables.js" type="text/javascript"></script>
        <!-- END: load jquery -->
        <!-- <script type="text/javascript" src="js/table/table.js"></script> -->
        <script src="js/setup.js" type="text/javascript"></script>
        <script type="text/javascript">
        $(document).ready(function() {

        <%--$('.datatable').dataTable({--%>
        <%--scrollY : 500,--%>
        <%--deferRender: true,--%>
        <%--processing: false,--%>
        <%--language : {--%>
        <%--"sProcessing" : "处理中...",--%>
        <%--"sLengthMenu" : "显示 _MENU_ 项结果",--%>
        <%--"sZeroRecords" : "没有匹配结果",--%>
        <%--"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",--%>
        <%--"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",--%>
        <%--"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",--%>
        <%--"sInfoPostFix" : "",--%>
        <%--"sSearch" : "搜索:",--%>
        <%--"sUrl" : "",--%>
        <%--"sEmptyTable" : "表中数据为空",--%>
        <%--"sLoadingRecords" : "载入中...",--%>
        <%--"sInfoThousands" : ",",--%>
        <%--"oPaginate" : {--%>
        <%--"sFirst" : "首页",--%>
        <%--"sPrevious" : "上页",--%>
        <%--"sNext" : "下页",--%>
        <%--"sLast" : "末页"--%>
        <%--},--%>
        <%--"oAria" : {--%>
        <%--"sSortAscending" : ": 以升序排列此列",--%>
        <%--"sSortDescending" : ": 以降序排列此列"--%>
        <%--}--%>
        <%--}--%>
        <%--});--%>
        $('.datatable').dataTable({
        language : {
        "sProcessing" : "处理中...",
        "sLengthMenu" : "显示 _MENU_ 项结果",
        "sZeroRecords" : "没有匹配结果",
        "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix" : "",
        "sSearch" : "搜索:",
        "sUrl" : "",
        "sEmptyTable" : "表中数据为空",
        "sLoadingRecords" : "载入中...",
        "sInfoThousands" : ",",
        "oPaginate" : {
        "sFirst" : "首页",
        "sPrevious" : "上页",
        "sNext" : "下页",
        "sLast" : "末页"
        },
        "oAria" : {
        "sSortAscending" : ": 以升序排列此列",
        "sSortDescending" : ": 以降序排列此列"
        }
        }
        });

        function retrieveData( sSource111,aoData111, fnCallback111) {
        $.ajax({
        url : sSource111,//这个就是请求地址对应sAjaxSource
        data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(result) {
        fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
        },
        error : function(msg) {
        }
        });
        }
        });
        </script>
        </head>
        <body>
        <div class="grid_10">
        <div class="box round first grid">
        <a href="adminEditBanner.action?bannerId=" target="content"><h2>
        <div><img alt="添加Banner" src="images/圆角矩形-3-拷贝-5.png"></div>
        <div>添加Banner</div>
        </h2></a>

        <div class="block">
        <table class="data display datatable" id="example">
        <thead>
        <tr>
        <th class="center">序号</th>
        <th class="center">名称</th>
        <th class="center">描述</th>
        <th class="center">图片地址</th>
        <th class="center">链接地址</th>
        <th class="center">关联项目</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${items}" var="item" varStatus="vs">
            <tr class="odd gradeX">
            <td class="center"><a href="adminEditBanner.action?bannerId=${item.body.bannerId}"
            target="content">${item.body.bannerId}</a></td>
            <td class="center">${item.body.name}</td>
            <td class="center">${item.body.description}</td>
            <td class="center"><a href=${item.body.image} target="blank">${item.body.image}</a></td>
            <td class="center"><a href=${item.body.url} target="blank">查看内容</a></td>
            <td class="center">${item.body.project}</td>
            </tr>
        </c:forEach>
        </tbody>
        </table>
        </div>
        </div>
        </div>
        </body>
        </html>