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
    <meta name="viewport" content="initial-scale=1, user-scalable=no, width=device-width">
    <link rel="stylesheet" type="text/css" href=".//css/base.css">
    <!--<link rel="stylesheet" type="text/css" href="css/main.css">-->
    <!--<link rel="stylesheet" type="text/css" href="css/project.css">-->
    <link rel="stylesheet" type="text/css" href=".//css/project-detail.css">
    <title>金指投--项目详情</title>
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

    <div class="middle c-top-s">
        <div class="content ">
            <div id="top_left">
                <img id="mainImg"
                     src=".//img/test1.png">
            </div>
            <div id="top_right">
                <div id="title">华新新能源</div>
                <div id="div_img"><img src=".//img/images/rongzizhong.png" width="110px" height="36px" align="right"></div>
                <p id="fullName">西安华新新能源股份有限公司</p>
                <ul id="service">
                    <li><img src=".//img/images/service_type.png"></li>
                    <li id="industoryType">能源，销售，矿业</li>
                </ul>
                <br>
                <div class="pop_oc">
                    <div class="pop_oc_por" style="background:#5776c9; width:82%;"><span class="pop_por">82%</span>
                    </div>
                </div>
                <ul id="info">
                    <li class="info">人气指数&nbsp;<span id="collectionCount"><strong>8</strong></span></li>
                    <li class="info">剩余天数&nbsp;<span id="timeLeft"><strong>28</strong></span></li>
                    <li>融资额度&nbsp;<span id="financeTotal"><strong>8000</strong></span>万</li>
                </ul>
                <div style="clear: both"></div>
                <div style="width: 100%">
                    <button id="confirm">认投</button>
                    <!--<button id="guanzhu" class="guanzhu">关注(2万)</button>-->
                    <input type="button" id="guanzhu" class="guanzhu" value="关注">
                </div>
                <div style="clear: both"></div>
                <ul id="share" style="margin-top: 30px;">
                    <li class="text">分享到</li>
                    <li class="img"><a href="#"><img src=".//img/images/weixin.png"></a></li>
                    <li class="img"><a href="#"><img src=".//img/images/QQ.png"></a></li>
                    <li class="img"><a href="#"><img src=".//img/images/dongtai.png"></a></li>
                    <li class="img"><a href="#"><img src=".//img/images/email.png"></a></li>
                </ul>
                <div class="scan_div">
                    <div class="scan_img"></div>
                </div>
            </div>
            <div style="clear: both"></div>
        </div>
    </div>


    <!--内容区域;最小1200px;-->
    <div class="middle gray-bg">
        <div class="content c-top-s">
            <!-- 左边 -->
            <div id="middle_left">
                <div id="middle_left_top">
                    <p id="subtitle">项目介绍</p>
                    <hr>
                    <div id="middle_left_bg_img">
                        <ul>
                            <img src=".//img/images/arrow_left.png" width="50px" height="100px" id="arrow_left"
                                 onclick="pre()">
                            <img src=".//img/images/arrow_right.png" width="50px" height="100px" id="arrow_right"
                                 onclick="next()">
                        </ul>
                    </div>
                    <div class="small_images">
                        <ul>
                            <img src=".//img/images/img2.png" class="smallImg">
                            <img src=".//img/images/img2.png" class="smallImg">
                            <img src=".//img/images/img3.png" class="smallImg">
                            <img src=".//img/images/img4.png" class="smallImg">
                            <img src=".//img/images/img5.png">
                        </ul>
                    </div>
                </div>

                <p class="txt" id="txt"> ★西安华新新能源股份有限公司（证券代码 834368）， 2015 年 11
                    月公司在全国中小企业股份转让系有限责任公司挂牌。公司是专业从事工业余热余压发电的工程设计、技术服务、设备成套、工程总承包、合同能源管理以及生物质发电工程项目总承包的国家级高新技术企业，是国内余热余压发电领域领先的综合节能服务提供商。<br><br>
                    <br><br>◆公司概况<br><br>西安华新新能源股份有限公司（证券代码 834368）， 2015 年 11
                    月公司在全国中小企业股份转让系有限责任公司挂牌。公司是专业从事工业余热余压发电的工程设计、技术服务、设备成套、工程总承包、合同能源管理以及生物质发电工程项目总承包的国家级高新技术企业，是国内余热余压发电领域领先的综合节能服务提供商。<br><br><br><br>◆董事长<br><br>许建民先生持有昊祥投资
                    75%股权，并通过昊祥投资间接控制公司 73.03%的股份，系公司实际控制人。<br><br>许建民先生， 1942 年生，中国国籍。 1960 年至 1973 年期间，担任解放军 87925
                    部队航空油实验员；1973 年至 2003 年期间，先后担任西安光学仪器厂实验员、主任； 2003 年至 2007年期间，担任深圳华美奥实业发展有限公司副总经理； 2007 年至 2011 年
                    5 月期间，担任公司执行董事； 2011 年 5 月至今，担任公司董事。<br><br>◆财务状况<br><br>▶2013资产总值47445.54万元 营业收入32,635.16万元
                    毛利率31.21% 净利润6,846.54万元 <br><br>▶2014年资产总值120,782.02 营业收入79,686.23 毛利率28.77% 净利润13,041.55
                    <br><br>▶2015年资产总值197,319.34 营业收入113,451.44 毛利率30.00% 净利润20,670.17 <br><br><br><br>◆项目内容<br><br>▶募集资金规模
                    不超过4.005亿元<br><br>▶每股定价 15元<br><br>▶发行股数 不超过2670万股<br><br>▶发行市盈率
                    投前14.70倍PE(按2016年全年净利润3.59亿元计算，投后市盈率为15.80倍)<br><br><br><br>◆发行方式<br><br>（视具体销售情况调整）
                    按股数发行，设立最低认购股数：75万股，即1125万元；投资者增加股数需为5万股及其整数倍。<br><br>发行对象
                    向不超过35个符合条件的投资者发行；<br><br><br><br>◆优先条件：<br><br>▶上市公司；<br><br>▶上市公司控股公司或上市公司子公司；<br><br>▶优质企业法人；<br><br>▶高净值的个人客户。<br><br><br><br>◆规避条件：<br><br>契约型基金、资产管理计划、信托计划等有可能影响未来IPO申报的机构投资者；<br><br><br><br>◆募集资金用途
                    <br><br>补充企业经营所需流动资金。<br><br><br><br>◆认购原则
                    <br><br>1.战略投资者优先<br><br>2.金额优先<br><br>3.缴纳保证金优先<br><br><br><br>◆对赌条款<br><br>发行人大股东承诺：<br><br>以2018年6月30日为基准日申报IPO材料，若无法实现，投资人可要求企业实际控制人按年化10%利率回购其所持股份。<br><br><br><br>●注：此方案为我们可销售的意向金额，最终发行方案以发行人经股东大会审议通过的披露信息及最终认购协议为准。
                    <br><br> <br><br> <br><br> <br><br> <br><br> <br><br></p>
            </div>


        </div>

        <!-- 右边 -->
        <div id="middle_right">
            <div id="middle_right_top">
                <p class="right_title">项目信息</p>
                <hr>
                <div class="project_info">
                    <ul>
                        <li id="pro_info_img"><img src=".//img/images/rong.png"></li>
                        <li id="txt_li">融资总额</li>
                        <li id="num_li"><span id="num_li0">8000</span>万</li>
                    </ul>

                    <ul>
                        <li id="pro_info_img"><img src=".//img/images/iconfont-jine.png"></li>
                        <li id="txt_li">已融金额</li>
                        <li id="num_li"><span id="num_li1">6565</span>万</li>
                    </ul>

                    <ul>
                        <li id="pro_info_img"><img src=".//img/images/shengyushijian.png"></li>
                        <li id="txt_li">起止时间</li>
                        <li id="num_li"><span id="num_li2">2016.09.09-2016.12.31</span></li>
                    </ul>

                    <ul>
                        <li id="pro_info_img"><img src=".//img/images/address.png"></li>
                        <li id="txt_li">所在地区</li>
                        <li id="num_li"><span id="address">陕西</span></li>
                    </ul>
                </div>
            </div>

            <div id="middle_right_middle">
                <p class="right_title">项目发起人</p>
                <hr>
                <div align="center">
                    <img id="project-icon" src="">
                    <p id="project_person">许建民</p>
                    <p class="project_other" id="project-position">总经理</p>
                    <p class="project_other" id="project-address">西安</p>
                </div>
            </div>

            <div id="middle_right_bottom">
                <p class="right_title">团队成员</p>
                <hr>
                <div class="tuandui" id="tuandui">
                    <div class="person" align="center" id="person1"><img
                            src="http://www.jinzht.com:8080/jinzht/images/icon.jpg">
                        <ul>
                            <li id="tuandui_name">马能财</li>
                            <li id="tuandui_zhiwei">副总经理</li>
                        </ul>
                    </div>
                    <div class="person" align="center" id="person1"><img
                            src="http://www.jinzht.com:8080/jinzht/images/icon.jpg">
                        <ul>
                            <li id="tuandui_name">李铁军</li>
                            <li id="tuandui_zhiwei">总经理</li>
                        </ul>
                    </div>
                    <div class="person" align="center" id="person1"><img
                            src="http://www.jinzht.com:8080/jinzht/images/icon.jpg">
                        <ul>
                            <li id="tuandui_name">侯小平</li>
                            <li id="tuandui_zhiwei">总监</li>
                        </ul>
                    </div>
                    <div class="person" align="center" id="person1"><img
                            src="http://www.jinzht.com:8080/jinzht/images/icon.jpg">
                        <ul>
                            <li id="tuandui_name">张红卫</li>
                            <li id="tuandui_zhiwei">副总经理</li>
                        </ul>
                    </div>
                    <div class="person" align="center" id="person1"><img
                            src="http://www.jinzht.com:8080/jinzht/images/icon.jpg">
                        <ul>
                            <li id="tuandui_name">许建民</li>
                            <li id="tuandui_zhiwei">执行董事</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
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

<script type="text/javascript" src=".//js/jquery1.8.min.js" charset="utf-8"></script>
<script type="text/javascript" src=".//js/bar.js" charset="utf-8"></script>
<script type="text/javascript" src=".//js/project-detail.js" charset="utf-8"></script>

</body>
</html>