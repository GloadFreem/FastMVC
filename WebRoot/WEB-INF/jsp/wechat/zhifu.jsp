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
    <title>支付课程</title>
</head>
<body style="background: #f5f5f5">
<div class="head">
     <div class="icon2" onclick="back()">&nbsp;</div>
    <div class="title">订购课程</div>
</div>

<div class="content">

    <div class="weui-article" style="background: #ffffff;height: 5rem;border-bottom: 1px solid #e9e9e9">
        <div class="c-title2">创业公司如何做创业准备</div>
        <div class="c-price-normal-r" id="price_dan">¥98.8</div>
        <div class="c-price-desc">总计
            <div class="c-price" style="float: right;margin-top: -3px;margin-right: 10px;" id="price_all">98.8</div>
        </div>
    </div>
     <div id="loadingToast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content" id="get_msg"></p>
        </div>
    </div>
    <div id="toast" style=" display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-info-circle weui-icon_toast"></i>
            <p class="weui-toast__content"  style="margin-top: 1rem;;" id="msg_code"></p>
        </div>
    </div>

    <div class="weui-cells weui-cells_checkbox">
        <label class="weui-cell weui-check__label" for="s11">
            <div class="weui-cell__hd" style="position: relative;margin-right: 10px;padding: 10px;">
                <img src="img/weixin.png" style="width: 40px;display: block">
            </div>
            <div class="weui-cell__bd">
                <p>微信支付</p>
            </div>
            <div class="weui-cell__hd">
                <input type="checkbox" class="weui-check" name="checkbox1" id="s11" checked="checked" disabled>
                <i class="weui-icon-checked"></i>
            </div>
        </label>
    </div>
</div>
<div class="weui-footer_fixed-bottom">
    <a class="weui-btn weui-btn_jinzt"  id="price_pay">¥98.8 确认支付</a>
</div>

</body>

<script type="text/javascript" src="js/jquery2.14.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/init.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/zhifu.js" charset="utf-8"></script>
</html>