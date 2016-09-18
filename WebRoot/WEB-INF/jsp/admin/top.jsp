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
    <script type="text/javascript" src="js/table/table.js"></script>
    <script src="js/setup.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            setupLeftMenu();

            $('.datatable').dataTable();
			setSidebarHeight();


        });
    </script>
</head>
<body>
	<div class="grid_12 header-repeat">
		<div id="branding">
			<div class="floatleft">
				<img src="images/金指投后台管理系统 .png" alt="Logo" />
			</div>
			<div class="floatright">
				<div class="floatleft">
					<img src="img/logo-jinzt.png" alt="用户头像" />
				</div>
				<div class="floatleft marginleft10">
					<ul class="inline-ul floatleft">
						<li>管理员</li>
						<li><a href="#">设置</a></li>
						<li><a href="adminLogin.action" target="_parent">退出</a></li>
					</ul>
					<br /> <span class="small grey">最近登录: 3 小时前</span>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="grid_12">
		<ul class="nav main">
			<li class="ic-dashboard"><a href="adminBannerListAdmin.action" target="content"><span>Banner</span></a>
			</li>
			<li class="ic-form-style"><a href="adminUserAuthenticListAdmin.action" target="content"><span>认证审核</span></a>
				</li>
			<li class="ic-typography"><a href="adminRoadShowListAdmin.action" target="content"><span>项目路演</span></a></li>
			<li class="ic-charts"><a href="adminCycleListAdmin.action" target="content"><span>圈子</span></a></li>
			<li class="ic-grid-tables"><a href="adminActionListAdmin.action" target="content"><span>活动</span></a></li>
			<li class="ic-gallery dd"><a href="adminKingCapitalListAdmin.action" target="content"><span>金日投条</span></a>
				</li>
			<li class="ic-notifications"><a href="adminPushListAdmin.action" target="content"><span>消息推送</span></a></li>

		</ul>
	</div>
	<div class="clear"></div>
</body>
</html>