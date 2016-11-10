
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="initial-scale=1, user-scalable=no, width=device-width">
<link rel="stylesheet" type="text/css" href=".//css/base.css">
<link rel="stylesheet" type="text/css" href=".//css/main.css">
<link rel="stylesheet" type="text/css" href=".//css/project.css">
<title>金指投--项目展示</title>
</head>
<body>
	<div class="page">

		<!--顶部导航-->
		<div class="header">
			<div class="content">
				<div class="h-logo"></div>
				<ul class="h-index">
					<li id="id_main">创投资讯</li>
					<li id="id_report">观点报告</li>
					<li id="id_project">项目展示</li>
					<div class="index-bar-2"></div>
				</ul>
				<div class="h-right">
					<div class="search">搜索</div>
					<div class="down">下载APP</div>
					<div class="text">
						<a href="../user/Login.html">登录</a> | <a href="../user/Login.html">注册</a>
					</div>

				</div>
			</div>
		</div>

		<div class="content-bg-top"></div>


		<!--内容区域;最小1200px;-->
		<div class="middle gray-bg">
			<!--//-->
			<div class="content  c-top" style="padding: 20px;">
				<div class="c-t-list">
					<div class="t-fo">项目状态</div>
					<div class="l-t-list" id="check_money">
						<a class="checked" href="javascript:void(0);"
							onclick="checkboxType(0)">全部</a> <a class="normal"
							href="javascript:void(0);" onclick="checkboxType(1)">融资中</a> <a
							class="normal" href="javascript:void(0);"
							onclick="checkboxType(2)">待路演</a> <a class="normal"
							href="javascript:void(0);" onclick="checkboxType(3)">融资成功</a> <a
							class="normal" href="javascript:void(0);"
							onclick="checkboxType(4)">预选库</a>
					</div>
				</div>
				<div class="c-t-list" style="border:none;">
					<div class="t-fo">投资领域</div>
					<div class="l-t-list" id="check_type">
						<a class="checked" href="javascript:void(0);"
							onclick="checkboxTypeTwo(0)">全部</a> <a class="normal"
							href="javascript:void(0);" onclick="checkboxTypeTwo(1)">互联网/电子</a>
						<a class="normal" href="javascript:void(0);"
							onclick="checkboxTypeTwo(2)">医疗/卫生</a> <a class="normal"
							href="javascript:void(0);" onclick="checkboxTypeTwo(3)">文体/艺术</a>
						<a class="normal" href="javascript:void(0);"
							onclick="checkboxTypeTwo(4)">运输/仓储</a> <a class="normal"
							href="javascript:void(0);" onclick="checkboxTypeTwo(5)">能源/环保</a>
						<a class="normal" href="javascript:void(0);"
							onclick="checkboxTypeTwo(6)">农业/牧业</a> <a class="normal"
							href="javascript:void(0);" onclick="checkboxTypeTwo(7)">商贸/仓储</a>
						<a class="normal" href="javascript:void(0);"
							onclick="checkboxTypeTwo(8)">制造业</a> <a class="normal"
							href="javascript:void(0);" onclick="checkboxTypeTwo(9)">服务业</a> <a
							class="normal" href="javascript:void(0);"
							onclick="checkboxTypeTwo(10)">其它</a>
					</div>
				</div>
			</div>


			<div class="content c-top-s">
				<div class="c-t-list" style="border:none;">
					<div class="t-fo">排序</div>
					<div class="l-t-list">
						<div class="normal">融资额度</div>
						<div class="normal">已融比例</div>
						<div class="normal">剩余时间</div>
						<div class="normal">人气指数</div>
					</div>
				</div>
			</div>



			<!--正文-->
			<div class="content c-top-s ">
				<!-- 数据集合 start -->

				<div class="card-project" id="id_cards">
					<!--demo card 1-->
					<a class="card" href="projectDetail.action"> <img
						src=".//img/test1.png" class="card_img">
						<div class="c-item" style="margin-top: 15px">
							<div class="t_title">浪游网</div>
							<div class="t_c">融资中</div>
						</div>
						<div class="c-item" style="margin-top: 16px">
							<div class="t_text">根据您的心情推荐你的行程，专注于周边目的地户外休闲游市场</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="icon"></div>
							<div class="icon_text">租赁</div>
							<div class="icon_text">运输</div>
							<div class="icon_text">能源</div>
							<div class="icon_text">服务业</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="process_title">75%</div>
							<div class="process_bar">
								<div class="process_bar_ing" id="bar_process"></div>
							</div>
						</div>
						<div class="c-item" style="margin-top: 15px">
							<div class="item_three">
								<div class="item_three_t t_left">2016</div>
								<div class="item_three_b t_left">人气指数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_center">15</div>
								<div class="item_three_b t_center">剩余天数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_right">1200万</div>
								<div class="item_three_b t_right">融资额度</div>
							</div>
						</div>
					</a> <a class="card" href=""> <img src=".//img/test2.png"
						class="card_img">
						<div class="c-item" style="margin-top: 15px">
							<div class="t_title">浪游网</div>
							<div class="t_c">融资中</div>
						</div>
						<div class="c-item" style="margin-top: 16px">
							<div class="t_text">根据您的心情推荐你的行程，专注于周边目的地户外休闲游市场</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="icon"></div>
							<div class="icon_text">租赁</div>
							<div class="icon_text">运输</div>
							<div class="icon_text">能源</div>
							<div class="icon_text">服务业</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="process_title">75%</div>
							<div class="process_bar">
								<div class="process_bar_ing" id="bar_process"></div>
							</div>
						</div>
						<div class="c-item" style="margin-top: 15px">
							<div class="item_three">
								<div class="item_three_t t_left">2016</div>
								<div class="item_three_b t_left">人气指数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_center">15</div>
								<div class="item_three_b t_center">剩余天数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_right">1200万</div>
								<div class="item_three_b t_right">融资额度</div>
							</div>
						</div>
					</a> <a class="card" href=""> <img src=".//img/test5.png"
						class="card_img">
						<div class="c-item" style="margin-top: 15px">
							<div class="t_title">浪游网</div>
							<div class="t_c">融资中</div>
						</div>
						<div class="c-item" style="margin-top: 16px">
							<div class="t_text">根据您的心情推荐你的行程，专注于周边目的地户外休闲游市场</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="icon"></div>
							<div class="icon_text">租赁</div>
							<div class="icon_text">运输</div>
							<div class="icon_text">能源</div>
							<div class="icon_text">服务业</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="process_title">75%</div>
							<div class="process_bar">
								<div class="process_bar_ing" id="bar_process"></div>
							</div>
						</div>
						<div class="c-item" style="margin-top: 15px">
							<div class="item_three">
								<div class="item_three_t t_left">2016</div>
								<div class="item_three_b t_left">人气指数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_center">15</div>
								<div class="item_three_b t_center">剩余天数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_right">1200万</div>
								<div class="item_three_b t_right">融资额度</div>
							</div>
						</div>
					</a> <a class="card" href=""> <img src=".//img/test4.png"
						class="card_img">
						<div class="c-item" style="margin-top: 15px">
							<div class="t_title">浪游网</div>
							<div class="t_c">融资中</div>
						</div>
						<div class="c-item" style="margin-top: 16px">
							<div class="t_text">根据您的心情推荐你的行程，专注于周边目的地户外休闲游市场</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="icon"></div>
							<div class="icon_text">租赁</div>
							<div class="icon_text">运输</div>
							<div class="icon_text">能源</div>
							<div class="icon_text">服务业</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="process_title">75%</div>
							<div class="process_bar">
								<div class="process_bar_ing" id="bar_process"></div>
							</div>
						</div>
						<div class="c-item" style="margin-top: 15px">
							<div class="item_three">
								<div class="item_three_t t_left">2016</div>
								<div class="item_three_b t_left">人气指数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_center">15</div>
								<div class="item_three_b t_center">剩余天数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_right">1200万</div>
								<div class="item_three_b t_right">融资额度</div>
							</div>
						</div>
					</a> <a class="card" href=""> <img src=".//img/test2.png"
						class="card_img">
						<div class="c-item" style="margin-top: 15px">
							<div class="t_title">浪游网</div>
							<div class="t_c">融资中</div>
						</div>
						<div class="c-item" style="margin-top: 16px">
							<div class="t_text">根据您的心情推荐你的行程，专注于周边目的地户外休闲游市场</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="icon"></div>
							<div class="icon_text">租赁</div>
							<div class="icon_text">运输</div>
							<div class="icon_text">能源</div>
							<div class="icon_text">服务业</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="process_title">75%</div>
							<div class="process_bar">
								<div class="process_bar_ing" id="bar_process"></div>
							</div>
						</div>
						<div class="c-item" style="margin-top: 15px">
							<div class="item_three">
								<div class="item_three_t t_left">2016</div>
								<div class="item_three_b t_left">人气指数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_center">15</div>
								<div class="item_three_b t_center">剩余天数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_right">1200万</div>
								<div class="item_three_b t_right">融资额度</div>
							</div>
						</div>
					</a> <a class="card" href=""> <img src=".//img/test3.png"
						class="card_img">
						<div class="c-item" style="margin-top: 15px">
							<div class="t_title">浪游网</div>
							<div class="t_c">融资中</div>
						</div>
						<div class="c-item" style="margin-top: 16px">
							<div class="t_text">根据您的心情推荐你的行程，专注于周边目的地户外休闲游市场</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="icon"></div>
							<div class="icon_text">租赁</div>
							<div class="icon_text">运输</div>
							<div class="icon_text">能源</div>
							<div class="icon_text">服务业</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="process_title">75%</div>
							<div class="process_bar">
								<div class="process_bar_ing" id="bar_process"></div>
							</div>
						</div>
						<div class="c-item" style="margin-top: 15px">
							<div class="item_three">
								<div class="item_three_t t_left">2016</div>
								<div class="item_three_b t_left">人气指数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_center">15</div>
								<div class="item_three_b t_center">剩余天数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_right">1200万</div>
								<div class="item_three_b t_right">融资额度</div>
							</div>
						</div>
					</a> <a class="card" href=""> <img src=".//img/test1.png"
						class="card_img">
						<div class="c-item" style="margin-top: 15px">
							<div class="t_title">浪游网</div>
							<div class="t_c">融资中</div>
						</div>
						<div class="c-item" style="margin-top: 16px">
							<div class="t_text">根据您的心情推荐你的行程，专注于周边目的地户外休闲游市场</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="icon"></div>
							<div class="icon_text">租赁</div>
							<div class="icon_text">运输</div>
							<div class="icon_text">能源</div>
							<div class="icon_text">服务业</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="process_title">75%</div>
							<div class="process_bar">
								<div class="process_bar_ing" id="bar_process"></div>
							</div>
						</div>
						<div class="c-item" style="margin-top: 15px">
							<div class="item_three">
								<div class="item_three_t t_left">2016</div>
								<div class="item_three_b t_left">人气指数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_center">15</div>
								<div class="item_three_b t_center">剩余天数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_right">1200万</div>
								<div class="item_three_b t_right">融资额度</div>
							</div>
						</div>
					</a> <a class="card" href=""> <img src=".//img/test1.png"
						class="card_img">
						<div class="c-item" style="margin-top: 15px">
							<div class="t_title">浪游网</div>
							<div class="t_c">融资中</div>
						</div>
						<div class="c-item" style="margin-top: 16px">
							<div class="t_text">根据您的心情推荐你的行程，专注于周边目的地户外休闲游市场</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="icon"></div>
							<div class="icon_text">租赁</div>
							<div class="icon_text">运输</div>
							<div class="icon_text">能源</div>
							<div class="icon_text">服务业</div>
						</div>
						<div class="c-item" style="margin-top: 10px">
							<div class="process_title">75%</div>
							<div class="process_bar">
								<div class="process_bar_ing" id="bar_process"></div>
							</div>
						</div>
						<div class="c-item" style="margin-top: 15px">
							<div class="item_three">
								<div class="item_three_t t_left">2016</div>
								<div class="item_three_b t_left">人气指数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_center">15</div>
								<div class="item_three_b t_center">剩余天数</div>
							</div>
							<div class="item_three">
								<div class="item_three_t t_right">1200万</div>
								<div class="item_three_b t_right">融资额度</div>
							</div>
						</div>
					</a>


					<!--<div style="clear: both"></div>-->
				</div>
				<!--<div style="clear: both"></div>-->

			</div>
			<!--<div style="clear: both"></div>-->
		</div>


		<!--//底部-->
		<div class="footer">
			<div class="content">
				<div class="top">
					<div class="left m-left-0">关于我们</div>
					<div class="left m-left">视频介绍</div>
					<div class="left m-left">联系我们</div>
					<div class="left m-left">意见反馈</div>
					<div class="left m-left">服务协议</div>
					<div class="left m-left">隐私政策</div>
					<div class="scan-footer m-left-0">
						<div class="img"></div>
						<div class="text">APP下载</div>
					</div>
					<div class="scan-footer m-left">
						<div class="img"></div>
						<div class="text">微信公众号</div>
					</div>
				</div>
			</div>
			<div class="bot">京ICP备15062798号 © 2015-2016 金指投 版权所有</div>
		</div>


	</div>

	<script type="text/javascript" src=".//js/jquery1.8.min.js"
		charset="utf-8"></script>
	<script type="text/javascript" src=".//js/bar.js" charset="utf-8"></script>
	<script type="text/javascript" src=".//js/project.js" charset="utf-8"></script>

</body>
</html>