var isCanClick=false;var payBean;$(document).ready(function(){if(is_weixn()){}else{window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.tencent.mm#opened";window.close()}$("#price_pay").click(function(){btn_pay()});var price=localStorage.getItem("jinzhtPrice");var uid=localStorage.getItem("jinzhtId");var contentId="";var telephone="";var name="";var code="";$("#price_dan").text("¥"+price);$("#price_all").text("¥"+price);$("#price_pay").text("¥"+price+" 确认支付");getPayInfo()});function getPayInfo(){$("#get_msg").text("获取订单中");$("#loadingToast").show();alert(localStorage.getItem("JinzhtBean"));payBean=JSON.parse(localStorage.getItem("JinzhtBean"));var url=baseUrl+"requestConfirmBusinessOrder.action";var cookie=localStorage.getItem("Cookie");payBean.sessionId=cookie.replace("JSESSIONID=","");console.log("11"+cookie);$.ajax({xhrFields:{withCredentials:true},url:url,data:payBean,success:function(data){$("#loadingToast").hide();if(data.status==200){isCanClick=true;orderId=data.data.orderId;alert(orderId)}else{$("#get_msg").text(data.message);$("#loadingToast").show();setTimeout(function(){$("#loadingToast").hide();back()},1500)}console.log(data)},error:function(xhr,textStatus,errMsg){$("#get_msg").text("订单获取失败！");$("#loadingToast").show();setTimeout(function(){$("#loadingToast").hide();back()},1500)}})}function onBridgeReady(singJson){WeixinJSBridge.invoke('getBrandWCPayRequest',{"appId":singJson.appId,"timeStamp":singJson.timeStamp,"nonceStr":singJson.nonceStr,"package":singJson.package,"signType":"MD5","paySign":singJson.sign},function(res){if(res.err_msg=="get_brand_wcpay_request:ok"){$("#get_msg").text("支付成功！");$("#loadingToast").show();setTimeout(function(){$("#loadingToast").hide();$("#get_msg").text("验证订单,请稍候...");$("#loadingToast").show();setTimeout(function(){httpCheckResult(orderId)},500)},1200)}else{$("#msg_code").text("支付取消");$("#toast").show();setTimeout(function(){$("#toast").hide()},1000)}})}function btn_pay(){console.log("123");if(isCanClick){getPaySign(orderId)}}function getPaySign(orderId){var uid=localStorage.getItem("jinzhtId");$.ajax({url:baseUrl+"requestOrderSign.action",data:{orderId:orderId,type:3,unionid:uid,openId:localStorage.getItem("openId")},contentType:"application/json; charset=utf-8",type:"get",headers:{'Cookie':localStorage.getItem("Cookie")},dataType:'json',success:function(data){$("#loadingToast").hide();if(data.status==200){onBridgeReady(data.data)}else{}console.log(data)},error:function(xhr,textStatus,errMsg){$("#get_msg").text("请检查网络");$("#loadingToast").show();setTimeout(function(){$("#loadingToast").hide()},1000)}})}function httpCheckResult(orderId){$.ajax({url:baseUrl+"requestCompletePayBusinessOrder.action",data:{orderId:orderId,sessionId:payBean.sessionId},contentType:"application/json; charset=utf-8",type:"get",dataType:'json',success:function(data){$("#loadingToast").hide();if(data.status==200){window.location.href="result.action"}else{$("#get_msg").text(data.message);$("#loadingToast").show();setTimeout(function(){$("#loadingToast").hide()},800)}console.log(data)},error:function(xhr,textStatus,errMsg){$("#loadingToast").hide();$("#get_msg").text("请检查网络");$("#loadingToast").show();setTimeout(function(){$("#loadingToast").hide()},1000)}})}