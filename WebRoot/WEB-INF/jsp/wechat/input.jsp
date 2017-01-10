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
    <title>订购课程</title>
</head>
<body style="background: #f5f5f5">
<div class="head">
	 <div class="icon2" onclick="back()">&nbsp;</div>
    <div class="title">订购课程</div>
</div>

<div class="content" style="margin-top: 5rem;">
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">姓&nbsp;名</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" placeholder="请输入您的姓名" id="nameNum">
            </div>
        </div>
        <div class="weui-cell weui-cell_vcode">
            <div class="weui-cell__hd">
                <label class="weui-label">手机号</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" placeholder="请输入手机号" id="telNum">
            </div>
            <div class="weui-cell__ft">
                <button class="weui-vcode-btn" id="checkNum" style="text-align: center;min-width:100px;">获取验证码</button>
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入短信验证码"  id="codeNum">
            </div>
        </div>
    </div>

    <div class="desc"><img src="img/i.png" >此视频仅限课程会员播放，请填写您的真实信息成为会员</div>


    <div id="loadingToast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content">发送短信中</p>
        </div>
    </div>

    <div id="toast" style=" display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
            <p class="weui-toast__content" id="msg_code">验证码已发送</p>
        </div>
    </div>
    
     <div id="toast2" style=" display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-info-circle weui-icon_toast"></i>
            <p class="weui-toast__content"  style="margin-top: 1rem;;" id="msg_code2"></p>
        </div>
    </div>

</div>


<div class="weui-footer_fixed-bottom">
    <a class="weui-btn weui-btn_jinzt" id="check_input">下一步</a>
</div>


</body>

<script type="text/javascript" src="js/jquery2.14.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/init.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/input.min.js" charset="utf-8"></script>
</html>