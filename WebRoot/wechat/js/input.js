/**
 * Created by Administrator on 2016/12/30.
 */
var isCheckNum = true;
var waitTime = 60;

$(document).ready(function () {

    $("#checkNum").click(function () {
        getCheckNum();
    });
    $("#check_input").click(function () {
        checkInput();
    });

});

function getCheckNum() {

    var checkDiv = $("#checkNum");
    if (isCheckNum) {
        waitTime = 5;
        isCheckNum = false;
        httpCheckNum();
        var inter = setInterval(function () {
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
}

function httpCheckNum() {
    $("#loadingToast").show();

    setTimeout(function () {
        $("#loadingToast").hide();
        $("#toast").show();
    }, 2000);

    setTimeout(function () {
        $("#toast").hide();
    }, 3000);
}


function  checkInput(){

    window.location.href = "pay.action";

}








