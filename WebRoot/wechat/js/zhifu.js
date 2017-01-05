/**
 * Created by Administrator on 2017/1/3.
 */


$(document).ready(function () {

    $("#btn_pay").click(function () {
        btn_pay();
    });

});


function pay(){
	if (typeof WeixinJSBridge == "undefined") {
	    console.log('error');
	    if (document.addEventListener) {
	        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	    } else if (document.attachEvent) {
	        document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
	        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	    }
	} else {
	    onBridgeReady();
	}
}


/**
 * 微信支付
 */
function onBridgeReady() {
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId": "wxdcd3847ae7077ffe ",     //公众号名称，由商户传入
            "timeStamp": " 1395712654",         //时间戳，自1970年以来的秒数
            "nonceStr": "e61463f8efa94090b1f366cccfbbb444", //随机串
            "package": "prepay_id=wxdcd3847ae7077ffe",
            "signType": "MD5",         //微信签名方式:
            "paySign": "70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名
        },
        function (res) {
//        	alert(JSON.stringify(res));
            if (res.err_msg == "get_brand_wcpay_request:ok") {	
            	window.location.href = "result.action";
            } else{
            	window.location.href = "result.action";
            }    // 使用以上方式判断前端返回,微信团队郑重提示:res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        }
    );
}


function  btn_pay(){
	pay();
//    
}