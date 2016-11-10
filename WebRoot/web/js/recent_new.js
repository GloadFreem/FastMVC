var isShowDetail = false;

$(document).ready(function () {
    //test 活动数据

    $("#id_dongtai").hide();
    $("#id_xie").hide();
    var isShow = false;

    //$("#marker").addClass("check_sport");
    $("#id_sport").click(function () {
        window.location.href = "http://www.jinzht.com/app/action";
    });
    $("#id_investor").click(function () {
        window.location.href = "http://www.jinzht.com/app/investor";
    });
    $("#id_abount").click(function () {
        window.location.href = "http://www.jinzht.com/app/abount";
    });
    $("#id_msg").click(function () {
        window.location.href = "http://www.jinzht.com/app/info";
    });
    $("#id_project").click(function () {
        window.location.href = "http://www.jinzht.com/app/projectForWeb";
    });
    $("#id_main").click(function () {
        window.location.href = "http://www.jinzht.com/app/software";
    });
    $("#id_recent").click(function () {
        isShowDetail =!isShowDetail;
        $("#id_dongtai").hide();
        $("#id_xie").hide();
        $("#id_content").animate({
            //left:'250px',
            width: '1198px',
        });
        $(".item").animate({
            fontSize: '24px',
            color: '#323232',
            paddingLeft: '30px;',
            marginLeft: "100px",
            lineHeight: '38px',
            //padding-left: 30px;

        });
        $(".s_content_right").animate({
            width: '1px'
        });
        $(".s_content_right").hide();
        $("#id_dongtai").removeClass();
        $("#id_recent").removeClass();
        $("#id_recent").addClass('activied');
    });

});


function gotoDetail() {
    if(!isShowDetail){
        showDetail();
        isShowDetail =!isShowDetail;
    }
    getDetailJson();

}


function getDetailJson(){

}

function  showDetail(){
    $("#id_content").animate({
        //left:'250px',
        width: '300px',
    });
    $(".item").animate({
        fontSize: '16px',
        color: '#689ff1',
        paddingLeft: '0px;',
        marginLeft: "0px",
        lineHeight: '15px',
    });
    $(".s_content_right").show();
    $(".s_content_right").animate({
        width: '850px'
    });
    //$("#id_detail").fadeIn(1000);
    $("#id_dongtai").removeClass();
    $("#id_recent").removeClass();
    $("#id_dongtai").show();
    $("#id_xie").show();
    $("#id_dongtai").addClass('activied');
}