/**
 * Created by Administrator on 2016/12/30.
 */
var fisrtScroll = 0;
var isHas = true;

$(document).ready(function () {

    $("#bar_one").click(function () {
        changeBar(0);
    });
    $("#bar_two").click(function () {
        changeBar(1);
    });
    //$(window).scroll(function () {
    //    var scrolTop = $(window).scrollTop();
    //    fisrtScroll = $(window).scrollTop();
    //    console.log(scrolTop);
    //    if(scrolTop>100&&fisrtScroll==scrolTop&&isHas){
    //        //isHas = false;
    //        //
    //        //$(window).scrollTop(224);
    //        //setTimeout(function(){
    //        //    isHas = true;
    //        //},1000);
    //    }
    //});
    //微信判断
    if (is_weixn()) {

    } else {
        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.tencent.mm#opened";
        window.close();
    }

});




function changeBar(id) {
    console.log(id);
    var ob1 = $("#bar_one");
    var ob2 = $("#bar_two");
    ob1.removeClass();
    ob2.removeClass();
    if (id == 0) {
        ob1.addClass("bar actived");
        ob2.addClass("bar normal");
        $("#page_one").hide();
        $("#page_two").hide();
        $("#loading").show();

        setTimeout(function () {
            $("#loading").hide();
            $("#page_one").show();
            $("#page_two").hide();
        }, 1000);
    } else {
        ob1.addClass("bar normal");
        ob2.addClass("bar actived");
        $("#page_one").hide();
        $("#page_two").hide();
        $("#loading").show();
        setTimeout(function () {
            $("#loading").hide();
            $("#page_one").hide();
            $("#page_two").show();
        }, 1000);
    }
}






