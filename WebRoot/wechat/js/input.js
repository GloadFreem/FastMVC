/**
 * Created by Administrator on 2016/12/30.
 */
var isCheckNum = true;
var waitTime = 60;
var uid;
var price;
var contentid;
var code;

$(document)
		.ready(
				function() {

					$("#checkNum").click(function() {
						getCheckNum();
					});
					$("#check_input").click(function() {
						checkInput();
					});

					//微信判断
					if (is_weixn()) {
						uid = GetQueryString("id");
						price = GetQueryString("price");
						contentid = GetQueryString("contentid");
						var openId = GetQueryString("openId");
						localStorage.setItem("openId", openId);
						if (uid != null && code != "" && uid.length > 0) {
							localStorage.setItem("jinzhtId", uid);
							//            alert(localStorage.getItem("jinzhtId"));
						}
						if (price != null && price != "" && price.length > 0) {
							localStorage.setItem("jinzhtPrice", price);
							//            alert(localStorage.getItem("jinzhtId"));
						}
					} else {
						window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.tencent.mm#opened";
						window.close();
					}

				});

function getCheckNum() {

	var checkDiv = $("#telNum");
	if($("#telNum").val()!=""){
		if (isCheckNum) {
			waitTime = 60;
			isCheckNum = false;
			var tel = $("#telNum").val();
			httpCheckNum(tel);
			var inter = setInterval(function() {
				if (waitTime > 0) {
					waitTime--;
					console.log(waitTime);
					checkDiv.css('color', '#cccccc');
					checkDiv.text(waitTime + "秒");
				} else {
					clearInterval(inter);
					isCheckNum = true;
					console.log("close");
					checkDiv.css('color', '#ff7600');
					checkDiv.text("获取验证码");
					$("#loadingToast").hide();
				}

			}, 1000);
		}
	}else{
		$("#msg_code2").text("请输入电话");
		$("#toast2").show();
		setTimeout(function() {
			$("#toast2").hide();
		}, 800);
	}
	
}

function httpCheckNum(tel) {
	$("#loadingToast").show();

	var url = baseUrl + "verifyCode.action?type=4&telephone=" + tel;
	$.ajax({
		url : url,
		contentType : "application/json; charset=utf-8",
		type : "get",
		dataType : 'json',
		success : function(data) {
			isCheckNum = true;
			$("#loadingToast").hide();
			var msg = data.message;
			console.log(msg);
			$("#msg_code").text(msg);
			$("#toast").show();
			setTimeout(function() {
				$("#toast").hide();
			}, 1200);

			console.log(data);
			localStorage.setItem("Cookie", data.data);
		},
		error : function(xhr, textStatus, errMsg) {
			//console.log("error:");
			isCheckNum = true;
		}
	});

	setTimeout(function() {

	}, 2000);

}

function checkInput() {
	var tel = $("#telNum").val();
	var name = $("#nameNum").val();
	var code = $("#codeNum").val();
	;
	if (tel == "") {
		$("#msg_code2").text("请输入您的姓名");
		$("#toast2").show();
		setTimeout(function() {
			$("#toast2").hide();
		}, 800);
		return;
	}
	if (name == "") {
		$("#msg_code2").text("请输入电话号码");
		$("#toast2").show();
		setTimeout(function() {
			$("#toast2").hide();
		}, 800);
		return;
	}
	if (code == "") {
		$("#msg_code2").text("请输入验证码");
		$("#toast2").show();
		setTimeout(function() {
			$("#toast2").hide();
		}, 800);
		return;
	}

	var payBean = {};
	payBean.contentId = contentid;
	payBean.type = 3;
	payBean.unionid = uid;
	payBean.telephone = tel;
	
	payBean.name = name;
	payBean.code = code;
	localStorage.setItem("JinzhtBean", JSON.stringify(payBean));
	console.log(JSON.stringify(payBean));
	window.location.href = "pay.action";
}
