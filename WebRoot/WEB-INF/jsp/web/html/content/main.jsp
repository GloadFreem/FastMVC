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
<!--<link rel="stylesheet" type="text/css" href="css/swiper-3.4.0.min.css">-->
<link rel="stylesheet" type="text/css" href="./css/base.css">
<link rel="stylesheet" type="text/css" href="./css/main.css">
<title>金指投--创投咨询</title>
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
					<div class="index-bar"></div>
				</ul>
				<div class="h-right">
					<div class="search">搜索</div>
					<div class="down">下载APP</div>
					<div class="text">
						<a href="./login.action">登录</a> | <a href="./login.action">注册</a>
					</div>

				</div>
			</div>
		</div>


		<div class="module index-slide"
			style="border-top-color: rgb(216, 216, 216);">
			<div class="main">
				<div class="swiper-container">
					<div class="swiper-wrapper clearfix title-bar-0" id="tttt">
						<div class="swiper-slide" style="width: 1000px;">
							<a href="#" target="_blank" title="不知道换季买什么？先看看这几个时髦且不贵的牌子"><img
								src="http://img2.selfimg.com.cn/Lvogue807/2016/09/05/1473066792_PkxhBC.jpg"
								width="1000" height="400" alt="不知道换季买什么？先看看这几个时髦且不贵的牌子"
								title="不知道换季买什么？先看看这几个时髦且不贵的牌子"></a>
							<!--<div class="title-mask">-->
							<!--<a href="http://www.vogue.com.cn/invogue/dress-q/news_1643ca6430bb5268.html" target="_blank"-->
							<!--title="不知道换季买什么？先看看这几个时髦且不贵的牌子">不知道换季买什么？先看看这几个时髦且不贵的牌子</a>-->
							<!--<div class="des">又到了换季添置新衣的时候，我们知道你已经买腻了那些人人都在穿的品牌，又不可能天天花高价买大牌，那么在这两者之间有没有第...</div>-->
							<!--</div>-->
						</div>
						<div class="swiper-slide" style="width: 1000px;">
							<a href="#" target="_blank" title="她们皮肤那么好 其实是因为睡前做对了这件事"><img
								src="http://img3.selfimg.com.cn/Lvogue807/2016/10/09/1475997721_LtI2tY.jpg"
								width="1000" height="400" alt="她们皮肤那么好 其实是因为睡前做对了这件事"
								title="她们皮肤那么好 其实是因为睡前做对了这件事"></a>
							<!--<div class="title-mask">-->
							<!--<a href="http://www.vogue.com.cn/beauty/skincare/news_1925fcf5ac7af881.html" target="_blank"-->
							<!--title="她们皮肤那么好 其实是因为睡前做对了这件事">她们皮肤那么好 其实是因为睡前做对了这件事</a>-->
							<!--<div class="des">别以为明星们的好皮肤都只是粉底借助光学原理打造出的视觉效果，事实上用心保养更重要。就算再懒再累，每天晚上睡觉前她们也会...</div>-->
							<!--</div>-->
						</div>

						<div class="swiper-slide" style="width: 1000px;">
							<a href="#" target="_blank"
								title="维秘为什么会选她？Bella Hadid用一张“臭脸”踏平时尚圈"><img
								src="http://img3.selfimg.com.cn/Lvogue807/2016/10/28/1477635841_cAqOKd.jpg"
								width="1000" height="400" alt="维秘为什么会选她？Bella Hadid用一张“臭脸”踏平时尚圈"
								title="维秘为什么会选她？Bella Hadid用一张“臭脸”踏平时尚圈"></a>
							<!--<div class="title-mask">-->
							<!--<a href="http://www.vogue.com.cn/invogue/vogue-style/news_115g6f0655ce2027.html"-->
							<!--target="_blank" title="维秘为什么会选她？Bella Hadid用一张“臭脸”踏平时尚圈">维秘为什么会选她？Bella-->
							<!--Hadid用一张“臭脸”踏平时尚圈</a>-->
							<!--<div class="des">如果说2015年是Kendall Jenner和Gigi Hadid年，那么今年必然是属于Bella-->
							<!--Hadid的。昨天刚宣布的维多利亚的秘密2016巴黎大秀超...-->
							<!--</div>-->
							<!--</div>-->
						</div>


						<div class="swiper-slide" style="width: 1000px;">
							<a href="#" target="_blank" title="不知道换季买什么？先看看这几个时髦且不贵的牌子"><img
								src="http://img2.selfimg.com.cn/Lvogue807/2016/09/05/1473066792_PkxhBC.jpg"
								width="1000" height="400" alt="不知道换季买什么？先看看这几个时髦且不贵的牌子"
								title="不知道换季买什么？先看看这几个时髦且不贵的牌子"></a>
							<!--<div class="title-mask">-->
							<!--<a href="http://www.vogue.com.cn/invogue/dress-q/news_1643ca6430bb5268.html" target="_blank"-->
							<!--title="不知道换季买什么？先看看这几个时髦且不贵的牌子">不知道换季买什么？先看看这几个时髦且不贵的牌子</a>-->
							<!--<div class="des">又到了换季添置新衣的时候，我们知道你已经买腻了那些人人都在穿的品牌，又不可能天天花高价买大牌，那么在这两者之间有没有第...</div>-->
							<!--</div>-->
						</div>

					</div>
					<div class="index-slide-line"></div>

					<div class="slide-helper-l"></div>
					<div class="slide-helper-r"></div>
				</div>
				<div class="arrow pre-arrow" id="btn_next">
					<!--<div class="arrow-bg"></div>-->
					<i class="left"></i>
				</div>
				<div class="arrow next-arrow" id="btn_back">
					<!--<div class="arrow-bg"></div>-->
					<i class="right"></i>
				</div>
			</div>
		</div>


		<!--内容区域;最小1200px;-->
		<div class="middle" style="min-height: 800px">
			<!--//-->
			<div class="content c-top ">
				<div class="c-scroll-bar">
					<div class="btn-content">
						<div class="item item-color-1">所有行业</div>
						<div class="item item-color-0">医疗/卫生</div>
						<div class="item item-color-0">财经/证券</div>
						<div class="item item-color-0">新闻/实时</div>
						<div class="item item-color-0">政治/动态</div>
						<div class="item item-color-0">政治/动态</div>
						<div class="item item-color-0">医疗/卫生</div>

						<div class="item item-color-0">财经/证券</div>
						<div class="item item-color-0">新闻/实时</div>
						<div class="item item-color-0">政治/动态</div>
						<div class="item item-color-0">政治/动态</div>
						<div class="item item-color-0">新闻/实时</div>
					</div>
					<div class="btn-bar">
						<div class="btn-left icon-bar-left" onclick="changeBar2()"></div>
						<div class="btn-left icon-bar-right" onclick="changeBar()"></div>
					</div>
				</div>
			</div>

			<!--正文-->
			<div class="content ">
				<!--最新资讯-->
				<div class="content-left c-top">
					<!--title-->
					<div class="content-title">
						<div class="font-ch">最新资讯</div>
						<div class="font-g">/</div>
						<div class="font-en">Latest News</div>
					</div>
					<!--list-->
					<div class="content-list">
						<!--item-->
						<div class="content-item">
							<img src="./img/main/bg_test.png" class="item-img">
							<div class="item-r">
								<a href="MainDetail.action" class="item-title">重庆一镇领导班子以某职工名义购越野车作公务车，购车款报销</a>
								<div class="item-type-1">网易新闻</div>
								<div class="item-time">2016-10-22 09:40:00</div>
								<div class="item-desc">
									中国银监会10月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.中国银监会10月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.中国银监会10月21日召开三季度经济金融形势分析会，会议明确指出要加强理财资金投资管理.
								</div>
							</div>
						</div>

						<!--item-->
						<div class="content-item">
							<img src=".//img/main/bg_test.png" class="item-img">
							<div class="item-r">
								<a href="MainDetail.action" class="item-title">购越野车作公务车</a>
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
						<div class="font-ch">观点报告</div>
						<div class="font-g">/</div>
						<div class="font-en">Opinion Report</div>
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
	<script type="text/javascript"
		src=".//js/swiper-3.4.0.jquery.min.js" charset="utf-8"></script>
	<script type="text/javascript" src=".//js/bar.js" charset="utf-8"></script>
	<script type="text/javascript" src=".//js/main.js" charset="utf-8"></script>

</body>
</html>