    <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
    <link rel="stylesheet" type="text/css" href="./css/base.css"  media="all and (min-width:1200px)">
<link rel="stylesheet" type="text/css" href="./css/base_phone.css" media="all and (max-width:1199px)">
    <link rel="stylesheet" type="text/css" href="./css/project-detail.css"  media="all and (min-width:1200px)">
<link rel="stylesheet" type="text/css" href="./css/project-detail-phone.css" media="all and (max-width:1199px)">
    <title>金指投--项目详情</title>
</head>
<body>
<div class="page">

    <!--顶部导航-->
    <div class="header">
        <div class="content">
            <div class="h-logo"></div>
            		<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro#opened"  class="kehuduan">客户端</a>
			
            <ul class="h-index">
         		  <li id="id_main" class="nomarl">创投资讯</li>
                <li id="id_report" class="nomarl">观点报告</li>
                <li id="id_project" class="check">项目展示</li>
                <div class="index-bar-2"></div>
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

    <div class="middle c-top-s" >
        <div class="content  " >
            <div id="top_left">
                <img id="mainImg"
                     src="${Project.startPageImage }">
            </div>
            <div id="top_right">
                <div id="title">${Project.abbrevName }</div>
                <div id="div_img">
                   <c:if test="${Status==6 }">
                	<img src=".//img/images/rongzizhong.png" width="110px" height="36px" align="right">
                	</c:if>
                	  <c:if test="${Status!=6 }">
                	<img src=".//img/images/yuxuan.png" width="110px" height="36px" align="right">
                	</c:if>
                </div>
                <p id="fullName">${Project.fullName }</p>
                <ul id="service">
                    <li><img src=".//img/images/service_type.png"></li>
                    <li id="industoryType">${Project.industoryType }</li>
                </ul>
                <br>
                <div class="pop_oc">
                    <div class="pop_oc_por" style="width:${NumPeset}%;"><span class="pop_por">${NumPeset}%</span>
                    </div>
                </div>
                <ul id="info">
                    <li class="info">人气指数&nbsp;<span id="collectionCount"><strong>${Project.collectionCount }</strong></span></li>
                    <li class="info">剩余天数&nbsp;<span id="timeLeft"><strong>${OverTime}</strong></span></li>
                    <li>融资额度&nbsp;<span id="financeTotal"><strong>${Roadshowplan.financeTotal }</strong></span>万</li>
                </ul>
                <div style="clear: both"></div>
                <div style="width: 100%">
              		   <c:if test="${Status==6 }">
	                    <button  class="rentou">认投
	                        <div class="scanner">
	                            <div class="img"></div>
	                            <div class="text">请下载APP完成操作</div>
	                              <button  class="guanzhu">关注<div class="scanner">
	     		      </c:if>
	     		       <c:if test="${Status!=6 }">
	                   
	                              <button  class="guanzhu" style="margin-left:0;">关注<div class="scanner">
	     		      </c:if>
	     		      
                    <!--<button id="guanzhu" class="guanzhu">关注(2万)</button>-->
                  
                        <div class="img"></div>
                        <div class="text">请下载APP完成操作</div>
                    </div></button>
                </div>
                <div style="clear: both"></div>
                <ul id="share" style="margin-top: 30px;display:none;">
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
                    <div class="line"></div>
                      <c:if test="${Status==6 }">
                    <div id="middle_left_bg_img">
                        <ul>
                            <img src=".//img/images/arrow_left.png" width="50px" height="100px" id="arrow_left"
                                 onclick="pre()">
                            <img src=".//img/images/arrow_right.png" width="50px" height="100px" id="arrow_right"
                                 onclick="next()">
                        </ul>
                    </div>
                    <div class="small_images">
                    
                        <ul id="small_img">
                     
                        </ul>
                    </div>
                    <div style="clear:both;"></div>
                           
                </div>
                <p class="txt" id="txt"> ${Project.description}</p>
                
			  </c:if>      
			    <c:if test="${Status!=6 }">
			    			<p class="txt" id="txt"> ${Project.description}</p>
			    </c:if> 
            </div>
        </div>

        <!-- 右边 -->
        <div id="middle_right" >
            <div id="middle_right_top">
                <p class="right_title">项目信息</p>
                  <c:if test="${Status==6 }">
	                   <div class="status"></div>
	     		   </c:if>
	     		   <c:if test="${Status!=6 }">
	                   <div class="status2"></div>
	     		  </c:if>
                <div class="line"></div>
                <div class="project_info">
                    <ul>
                        <li id="pro_info_img"><img src=".//img/images/rong.png"></li>
                        <li id="txt_li">融资总额</li>
                        <li id="num_li"><span id="num_li0">${Roadshowplan.financeTotal}</span>万</li>
                    </ul>

                    <ul>
                        <li id="pro_info_img"><img src=".//img/images/iconfont-jine.png"></li>
                        <li id="txt_li">已融金额</li>
                        <li id="num_li"><span id="num_li1">${Roadshowplan.financedMount }</span>万</li>
                    </ul>

                    <ul>
                        <li id="pro_info_img"><img src=".//img/images/shengyushijian.png"></li>
                        <li id="txt_li">起止时间</li>
                        <li id="num_li"><span id="num_li2">${fn:substring(Roadshowplan.beginDate, 0, 10)}~${fn:substring(Roadshowplan.endDate, 0, 10)}</span></li>
                    </ul>

                    <ul>
                        <li id="pro_info_img"><img src=".//img/images/address.png"></li>
                        <li id="txt_li">所在地区</li>
                        <li id="num_li"><span id="address">${Project.address }</span></li>
                    </ul>
                 
                </div>
            </div>
            
              <div id="middle_right_bottom2">
                <p class="right_title">项目详情</p>
                   <div class="phone-txt" > ${Project.description}</div>
         	   <div style="clear: both"></div>
         	   </div>

            <div id="middle_right_middle">
                <p class="right_title">项目发起人</p>
                <div class="line"></div>
                <div align="center">
                    <img id="project-icon" src="${Menber.icon}">
                    <p id="project_person">${Menber.name}</p>
                    <p class="project_other" id="project-position">${Menber.position}</p>
                    <p class="project_other" id="project-address">${Menber.address}</p>
                </div>
                <div style="clear:both;"></div>
            </div>

            <div id="middle_right_bottom">
                <p class="right_title">团队成员</p>
                <div class="line"></div>
                <div class="tuandui" id="tuandui">
                
               			 <c:forEach var="person"  items="${Project.teams}" >
							     <div class="person" align="center" id="person1">
							     <img src="${person.icon}">
			                        <ul>
			                            <li id="tuandui_name">${person.name}</li>
			                            <li id="tuandui_zhiwei">${person.position}</li>
			                        </ul>
			                    </div>
						</c:forEach>
						
						<div style="clear: both"></div>
                    
                </div>
            </div>
        </div>
    </div>
  
	<div class="ren-phone">
				  <c:if test="${Status==6 }">
	                  <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro#opened" class="col-r">关注</a>
				<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro#opened"  class="col-l">认投</a>
	     		   </c:if>
	     		   <c:if test="${Status!=6 }">
	                <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro#opened" class="col-r2">关注</a>
	
	     		  </c:if>
		
	</div>
			
		<div class="footer">
			<div class="content">
				<div class="top">
					<a href="about.action"  class="left m-left-0">关于我们</a>
					<a href="address.action"  class="left m-left">联系我们</a>
					<a href="service.action"  class="left m-left">服务协议</a>
					<a href="policy.action"  class="left m-left">免责声明</a>
					<div class="scan-footer m-left-0">
						<div class="img"></div>
						<div class="text">APP下载</div>
					</div>
					<div class="scan-footer m-left">
						<div class="img2"></div>
						<div class="text">微信公众号</div>
					</div>
				</div>
			</div>
			<div class="bot">京ICP备15043593号 &copy;  2015-2016 金指投 版权所有</div>
		</div>


	</div>

<script type="text/javascript" src=".//js/jquery1.8.min.js" charset="utf-8"></script>
			<script type="text/javascript" src=".//js/config.js" charset="utf-8"></script>
<script type="text/javascript" src=".//js/bar.js" charset="utf-8"></script>
<script type="text/javascript" src=".//js/project-detail.js" charset="utf-8"></script>

</body>
</html>