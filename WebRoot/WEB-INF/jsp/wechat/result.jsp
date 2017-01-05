<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <meta name="viewport" content="initial-scale=1, user-scalable=no, width=device-width">
    <link rel="stylesheet" type="text/css" href="./css/base.css">
    <link rel="stylesheet" type="text/css" href="./css/weui.min.css">
    <title>支付成功</title>
</head>
<body style="background: #f5f5f5">
<div class="head">
    <div class="icon" onclick="back()"><</div>
    <div class="title">支付成功</div>
</div>

<div class="content">


    <div class="weui-msg">

        <img src="img/pay_bg.png" class="pay-bg"/>
        <!--<div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>-->

        <div class="weui-msg__text-area zero-top" >
            <h2 class="weui-msg__title">您已成功订购改课程</h2>
            <h2 class="weui-msg__title">登录金指投APP开始学习吧</h2>
            <p class="weui-msg__desc zero-top2" style="color:#fff;">
                <span style="color:#44916d;">账号：</span>
               17791823421
            </p>
            <p class="weui-msg__desc zero-top3" style="color:#fff;">
                <span style="color:#44916d;">密码：</span>
                已通过短信发送
            </p>
            <!--<p class="weui-msg__desc">内容详情，可根据实际需要安排，如果换行则不超过规定长度，居中展现<a href="javascript:void(0);">文字链接</a></p>-->
        </div>
        <div class="weui-msg__opr-area " style="width: 86%;margin-left:7%;margin-top: 3rem;">
            <p class="weui-btn-area">
                <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro#opened"
                   class="weui-btn weui-btn_jinzt">打开APP</a>
            </p>
        </div>
        <div class="weui-msg__extra-area" style="margin-bottom: 4rem;">
            <div class="weui-footer">
                <p class="weui-footer__links">
                    <a href="javascript:void(0);" class="weui-footer__link" style="color: #ff6700;font-size: 15px;">—
                        温馨提示 —</a>
                </p>
                <p class="weui-footer__text booter-text" style="font-size: 14px">使用该账号密码登录金指投APP后即可</p>
                <p class="weui-footer__text booter-text" style="font-size: 14px">在[我的课程]中播放已购买的课程</p>
            </div>
        </div>
    </div>

</div>

</body>

<script type="text/javascript" src="js/jquery2.14.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/init.js" charset="utf-8"></script>
<script type="text/javascript" src="js/result.js" charset="utf-8"></script>
</html>