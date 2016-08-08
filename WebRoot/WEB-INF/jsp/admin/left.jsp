<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>金指投科技 | 后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/text.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/grid.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/layout.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/nav.css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" /><![endif]-->
    <link href="css/table/demo_page.css" rel="stylesheet" type="text/css" />
    <!-- BEGIN: load jquery -->
    <script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery-ui/jquery.ui.core.min.js"></script>
    <script src="js/jquery-ui/jquery.ui.widget.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui/jquery.ui.accordion.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui/jquery.effects.core.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui/jquery.effects.slide.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui/jquery.ui.mouse.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui/jquery.ui.sortable.min.js" type="text/javascript"></script>
    <script src="js/table/jquery.dataTables.min.js" type="text/javascript"></script>
    <!-- END: load jquery -->
    <script src="js/setup.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            setupLeftMenu();
            $('.datatable').dataTable();
        });
    </script>
</head>
<body>
<div class="grid_2">
            <div class="box sidemenu">
                <div class="block" id="section-menu">
                    <ul class="section menu">
                        <li><a class="menuitem">系统用户</a>
                            <ul class="submenu">
                                <li><a>用户管理</a> </li>
                                <li><a>权限管理</a> </li>
                                <li><a class="active">登录记录</a> </li>
                                <li><a>操作记录</a> </li>
                            </ul>
                        </li>
                        <li><a class="menuitem">用户管理</a>
                            <ul class="submenu1">
                                <li><a href="userListAdmin.action" target="content">用户列表</a> </li>
                                <li><a href="userAuthenticListAdmin.action" target="content">认证审核</a> </li>
                                <li><a>黑名单</a> </li>
                            </ul>
                        </li>
                        <li><a class="menuitem">项目管理</a>
                            <ul class="submenu1">
                                <li><a href="projectListAdmin.action" target="content">项目列表</a> </li>
                                <li><a href="roadShowListAdmin.action" target="content">路演管理</a> </li>
                                <li><a href="sceneListAdmin.action" target="content">现场管理</a> </li>
                                <li><a>分享收藏</a> </li>
                                <li><a>路演历史</a> </li>
                            </ul>
                        </li>
                        <li><a class="menuitem">交易中心</a>
                            <ul class="submenu1">
                                <li><a>交易列表</a> </li>
                                <li><a>交易历史</a> </li>
                            </ul>
                        </li>
                         <li><a class="menuitem">结算中心</a>
                            <ul class="submenu1">
                                <li><a>投资结算</a> </li>
                                <li><a>金条账户</a> </li>
                                <li><a>交易记录</a> </li>
                                <li><a>提现记录</a> </li>
                            </ul>
                        </li>
                         <li><a class="menuitem">活动管理</a>
                            <ul class="submenu1">
                                <li><a href="actionListAdmin.action" target="content">活动列表</a> </li>
                                <li><a>分享收藏</a> </li>
                                <li><a>历史活动</a> </li>
                            </ul>
                        </li>
                         <li><a class="menuitem">圈子管理</a>
                            <ul class="submenu1">
                                <li><a href="cycleListAdmin.action" target="content">圈子列表</a>  </li>
                                <li><a>分享收藏</a> </li>
                                <li><a>黑名单</a> </li>
                            </ul>
                        </li>
                         <li><a class="menuitem">数据统计</a>
                            <ul class="submenu2">
                                <li><a>投资统计</a> </li>
                                <li><a>项目统计</a> </li>
                                <li><a>圈子统计</a> </li>
                                <li><a>活动统计</a> </li>
                            </ul>
                        </li>
                         <li><a class="menuitem">系统设置</a>
                            <ul class="submenu3">
                                <li><a href="bannerListAdmin.action" target="content">Banner管理</a> </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
</body>
</html>