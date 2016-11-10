<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="initial-scale=1, user-scalable=no, width=device-width">
<link rel="stylesheet" type="text/css" href=".//css/base.css">
<link rel="stylesheet" type="text/css" href=".//css/main.css">
<link rel="stylesheet" type="text/css" href=".//css/report.css">
<title>金指投--观点报告</title>
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
					<div class="index-bar-1"></div>
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

		<!--banner图片展示-->
		<!--<div class="banner">-->
		<!--<div class="poster-main B_Demo" >-->
		<!--首推资讯-->
		<div class="middle">
			<div class="content c-top-s">
				<!--demo msg 1-->
				<div class="t-left">
					<a id="msg_img_0" class="msg-big  " href="javascript:void(0);"
						style="background: url(.//img/message/20160904101118589.jpg) no-repeat center;">
						<div id="msg_title_0" class="shadow">【金日投条】据传Uber上半年至少亏了12.7亿美元</div>
					</a>
				</div>
				<div class="t-right">
					<a id="msg_img_1" class="msg-small  " href="javascript:void(0);"
						style="background: url(.//img/message/20160904100937733.jpg)
                    no-repeat center;">
						<div id="msg_title_1" class="shadow">干货丨企业分拆的正确姿势：抓业务和时机，伺候好股东、客户和员工</div>
					</a> <a id="msg_img_2" class="msg-small-2 " href="javascript:void(0);"
						style="background: url(.//img/message/20160904100815646.jpg)
                    no-repeat center;">
						<div id="msg_title_2" class="shadow">苹果面临欧盟数十亿欧元罚款；北京禁止平衡车上路，违者罚款10元</div>
					</a> <a id="msg_img_3" class="msg-small " href="javascript:void(0);"
						style="background: url(.//img/message/160904101343155.jpg) no-repeat
                    center;">
						<div id="msg_title_3" class="shadow">关于“约教练”项目投资建议</div>
					</a> <a id="msg_img_4" class="msg-small-2 " href="javascript:void(0);"
						style="background: url(.//img/message/0160904101441166.jpg) no-repeat
                    center;">
						<div id="msg_title_4" class="shadow">选择第三方财富管理机构的三大技巧</div>
					</a>

				</div>
			</div>
		</div>
		<!--</div>-->


		<!--内容区域;最小1200px;-->
		<div class="middle" style="min-height: 800px">
			<!--正文-->
			<div class="content  ">
				<!--最新资讯-->
				<div class="content-left c-top">
					<!--title-->
					<div class="content-title">
						<div class="font-ch">最新公布</div>
						<div class="font-g">/</div>
						<div class="font-en">Latest Release</div>
					</div>
					<!--list-->
					<div class="content-list">
						<!--item-->
						<div class="content-item">
							<img
								src="http://cms-bucket.nosdn.127.net/e960161df4d14790862f68bef2947a5020161022104200.png"
								class="item-img">
							<div class="item-r">
								<a href="reportDetail.action" class="item-title">史上最黄嘉宾空降网易态度星际趴</a>
								<div class="item-type-1">网易财经</div>
								<div class="item-time">2016-10-22 10:44:25</div>
								<div class="item-desc">装垫儿台，这里是装垫儿台！
									这几天的海口，刚送走一个台风，又迎来一个台风，然鹅这都不是大事！万众瞩目的海口国际广告节今天迎风开幕了！离“网易态度星际趴”大派对就只剩2天啦！！
								</div>
							</div>
						</div>

						<!--item-->
						<div class="content-item">
							<img src=".//img/main/bg_test.png" class="item-img">
							<div class="item-r">
								<a href="reportDetail.action" class="item-title">购越野车作公务车</a>
								<div class="item-type-2">第一财经日报</div>
								<div class="item-time">2016-10-22 09:40:00</div>
								<div class="item-desc">
									中国银监会10月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.中国银监会10月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.
								</div>
							</div>
						</div>

						<!--item-->
						<div class="content-item">
							<img src=".//img/main/bg_test.png" class="item-img">
							<div class="item-r">
								<a href="#" class="item-title">购车款报销，购车款报销</a>
								<div class="item-type-0">金日投条</div>
								<div class="item-time">2016-10-22 09:40:00</div>
								<div class="item-desc">
									中国0月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.中国银监会10月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.
								</div>
							</div>
						</div>
						<!--item-->
						<div class="content-item">
							<img src=".//img/main/bg_test.png" class="item-img">
							<div class="item-r">
								<a href="#" class="item-title">购车款报销，购车款报销</a>
								<div class="item-type-0">金日投条</div>
								<div class="item-time">2016-10-22 09:40:00</div>
								<div class="item-desc">
									中国0月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.中国银监会10月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.
								</div>
							</div>
						</div>
						<!--item-->
						<div class="content-item">
							<img src=".//img/main/bg_test.png" class="item-img">
							<div class="item-r">
								<a href="#" class="item-title">购车款报销，购车款报销</a>
								<div class="item-type-0">金日投条</div>
								<div class="item-time">2016-10-22 09:40:00</div>
								<div class="item-desc">
									中国0月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.中国银监会10月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.
								</div>
							</div>
						</div>

						<div class="content-item">
							<div class="content-more">查看更多</div>
						</div>

					</div>
				</div>

				<!--观点报告-->
				<div class="content-right c-top">
					<!--title-->
					<div class="content-title">
						<div class="font-ch">热门观点</div>
						<div class="font-g">/</div>
						<div class="font-en">Popular View</div>
					</div>
					<div class="opinion-list">
						<div class="content-item" style="margin-top: 0px;">
							<div class="opinion-text">中国银监会10月21日召开三季度经济金融形势分析会</div>
							<div class="opinion-text">购车款报销，购车款报销</div>
							<div class="opinion-text">中国银监会10月21日召开三季度经济金融形势分析会</div>
							<div class="opinion-text">中国银监会10月21日召开三季度经济金融形势分析会</div>
							<div class="opinion-text">中国银监会10月21日召开三季度经济金</div>
							<div class="opinion-text">中国银监会10月21日召开三季度经济金</div>
							<div class="content-item">
								<div class="content-opinion-more">更多资讯</div>
							</div>
						</div>
					</div>
				</div>

				<!--观点报告-->
				<div class="content-right c-top-s">
					<!--title-->
					<div class="content-title">
						<div class="font-ch">热门资讯</div>
						<div class="font-g">/</div>
						<div class="font-en">Hot News</div>
					</div>

					<div class="content-list">
						<!--item-->
						<div class="content-item-hot">
							<img src=".//img/main/bg_test.png" class="item-img-hot">
							<div class="item-r-hot">
								<a href="#" class="item-title">购车款报销，购车款报销，购车款报销购车款报销，购车款报销</a>
								<div class="item-time">2016-10-22 09:40:00</div>
							</div>
						</div>
						<!--item-->
						<div class="content-item-hot">
							<img src=".//img/main/bg_test.png" class="item-img-hot">
							<div class="item-r-hot">
								<a href="#" class="item-title">购车款报销，购车款报销，购车款报销购车款报销，购车款报销</a>
								<div class="item-time">2016-10-22 09:40:00</div>
							</div>
						</div>
						<!--item-->
						<div class="content-item-hot">
							<img src=".//img/main/bg_test.png" class="item-img-hot">
							<div class="item-r-hot">
								<a href="#" class="item-title">购车款报销，购车款报销，购车款报销购车款报销，购车款报销</a>
								<div class="item-time">2016-10-22 09:40:00</div>
							</div>
						</div>
						<!--item-->
						<div class="content-item-hot">
							<img src=".//img/main/bg_test.png" class="item-img-hot">
							<div class="item-r-hot">
								<a href="#" class="item-title">购车款报销，购车款报销，购车款报销购车款报销，购车款报销</a>
								<div class="item-time">2016-10-22 09:40:00</div>
							</div>
						</div>
						<!--item-->
						<div class="content-item-hot">
							<img src=".//img/main/bg_test.png" class="item-img-hot">
							<div class="item-r-hot">
								<a href="#" class="item-title">购车款报销，购车款报销，购车款报销购车款报销，购车款报销</a>
								<div class="item-time">2016-10-22 09:40:00</div>
							</div>
						</div>

					</div>
				</div>
			</div>
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
	<script type="text/javascript" src=".//js/report.js" charset="utf-8"></script>

</body>
</html>