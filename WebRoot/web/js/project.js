$(document).ready(function () {
    ////test 活动数据
    //init(0);
    ////点击监听
    //initClick();
    ////初始化页码
    //initPageB();
    //
    //

    $("#id_main").click(function () {
        window.location.href = "./index.action";
    });
    $("#id_report").click(function () {
        window.location.href = "./report.action";
    });
    $("#id_project").click(function () {
        window.location.href = "./project.action";
    });

});

function  checkboxType(num){
    $("#check_money").children().eq(0).removeClass()
    $("#check_money").children().eq(1).removeClass()
    $("#check_money").children().eq(2).removeClass()
    $("#check_money").children().eq(3).removeClass()
    $("#check_money").children().eq(4).removeClass()
    if(num==0){
        $("#check_money").children().eq(0).addClass("checked");
        $("#check_money").children().eq(1).addClass("normal");
        $("#check_money").children().eq(2).addClass("normal");
        $("#check_money").children().eq(3).addClass("normal");
        $("#check_money").children().eq(4).addClass("normal");
    }else if(num==1){
        $("#check_money").children().eq(0).addClass("normal");
        $("#check_money").children().eq(1).addClass("checked");
        $("#check_money").children().eq(2).addClass("normal");
        $("#check_money").children().eq(3).addClass("normal");
        $("#check_money").children().eq(4).addClass("normal");
    }else if(num==2){
        $("#check_money").children().eq(0).addClass("normal");
        $("#check_money").children().eq(1).addClass("normal");
        $("#check_money").children().eq(2).addClass("checked");
        $("#check_money").children().eq(3).addClass("normal");
        $("#check_money").children().eq(4).addClass("normal");
    }else if(num==3){
        $("#check_money").children().eq(0).addClass("normal");
        $("#check_money").children().eq(1).addClass("normal");
        $("#check_money").children().eq(2).addClass("normal");
        $("#check_money").children().eq(3).addClass("checked");
        $("#check_money").children().eq(4).addClass("normal");
    }else {
        $("#check_money").children().eq(0).addClass("normal");
        $("#check_money").children().eq(1).addClass("normal");
        $("#check_money").children().eq(2).addClass("normal");
        $("#check_money").children().eq(3).addClass("normal");
        $("#check_money").children().eq(4).addClass("checked");
    }
}
function  checkboxTypeThree(num){
    $("#check_three").children().eq(0).removeClass()
    $("#check_three").children().eq(1).removeClass()
    $("#check_three").children().eq(2).removeClass()
    if(num==0){
        $("#check_three").children().eq(0).addClass("checked");
        $("#check_three").children().eq(1).addClass("normal");
        $("#check_three").children().eq(2).addClass("normal");
    }else if(num==1){
        $("#check_three").children().eq(0).addClass("normal");
        $("#check_three").children().eq(1).addClass("checked");
        $("#check_three").children().eq(2).addClass("normal");
    }else {
        $("#check_three").children().eq(0).addClass("normal");
        $("#check_three").children().eq(1).addClass("normal");
        $("#check_three").children().eq(2).addClass("checked");
    }
}


function  checkboxTypeTwo(num){
    $("#check_type").children().eq(0).removeClass();
    $("#check_type").children().eq(1).removeClass();
    $("#check_type").children().eq(2).removeClass();
    $("#check_type").children().eq(3).removeClass();
    $("#check_type").children().eq(4).removeClass();
    $("#check_type").children().eq(5).removeClass();
    $("#check_type").children().eq(6).removeClass();
    $("#check_type").children().eq(7).removeClass();
    $("#check_type").children().eq(8).removeClass();
    $("#check_type").children().eq(9).removeClass();
    $("#check_type").children().eq(10).removeClass();
    $("#check_type").children().eq(0).addClass("normal");
    $("#check_type").children().eq(1).addClass("normal");
    $("#check_type").children().eq(2).addClass("normal");
    $("#check_type").children().eq(3).addClass("normal");
    $("#check_type").children().eq(4).addClass("normal");
    $("#check_type").children().eq(5).addClass("normal");
    $("#check_type").children().eq(6).addClass("normal");
    $("#check_type").children().eq(7).addClass("normal");
    $("#check_type").children().eq(8).addClass("normal");
    $("#check_type").children().eq(9).addClass("normal");
    $("#check_type").children().eq(10).addClass("normal");
   switch (num) {
       case 0:
           $("#check_type").children().eq(0).addClass("checked");
           break;
       case 1:
           $("#check_type").children().eq(1).addClass("checked");
           break;
       case 2:
           $("#check_type").children().eq(2).addClass("checked");
           break;
       case 3:
           $("#check_type").children().eq(3).addClass("checked");
           break;
       case 4:
           $("#check_type").children().eq(4).addClass("checked");
           break;
       case 5:
           $("#check_type").children().eq(5).addClass("checked");
           break;
       case 6:
           $("#check_type").children().eq(6).addClass("checked");
           break;
       case 7:
           $("#check_type").children().eq(7).addClass("checked");
           break;
       case 8:
           $("#check_type").children().eq(8).addClass("checked");
           break;
       case 9:
           $("#check_type").children().eq(9).addClass("checked");
           break;
       case 10:
           $("#check_type").children().eq(10).addClass("checked");
           break;
   }
}

function  checkboxTypeFour(num){
    $("#check_four").children().eq(0).removeClass();
    $("#check_four").children().eq(1).removeClass();
    $("#check_four").children().eq(2).removeClass();
    $("#check_four").children().eq(3).removeClass();
    $("#check_four").children().eq(4).removeClass();
    $("#check_four").children().eq(5).removeClass();
    $("#check_four").children().eq(6).removeClass();
    $("#check_four").children().eq(7).removeClass();
    $("#check_four").children().eq(8).removeClass();
    $("#check_four").children().eq(0).addClass("normal");
    $("#check_four").children().eq(1).addClass("normal");
    $("#check_four").children().eq(2).addClass("normal");
    $("#check_four").children().eq(3).addClass("normal");
    $("#check_four").children().eq(4).addClass("normal");
    $("#check_four").children().eq(5).addClass("normal");
    $("#check_four").children().eq(6).addClass("normal");
    $("#check_four").children().eq(7).addClass("normal");
    $("#check_four").children().eq(8).addClass("normal");
   switch (num) {
       case 0:
           $("#check_four").children().eq(0).addClass("checked");
           break;
       case 1:
           $("#check_four").children().eq(1).addClass("checked");
           break;
       case 2:
           $("#check_four").children().eq(2).addClass("checked");
           break;
       case 3:
           $("#check_four").children().eq(3).addClass("checked");
           break;
       case 4:
           $("#check_four").children().eq(4).addClass("checked");
           break;
       case 5:
           $("#check_four").children().eq(5).addClass("checked");
           break;
       case 6:
           $("#check_four").children().eq(6).addClass("checked");
           break;
       case 7:
           $("#check_four").children().eq(7).addClass("checked");
           break;
       case 8:
           $("#check_four").children().eq(8).addClass("checked");
           break;
   }
}

/**
 * 初始化页码
 */
function initPageB() {
    $(".tcdPageCode").createPage({
        pageCount: 4,
        current: 1,
        backFn: function (p) {
            console.log("123:" + p);
            init(parseInt(p));
        }
    });
}

/**
 * 点击监听
 */
function initClick() {
}
/**
 *  测试 活动 数据
 * @param pageIndex 页码
 */
function init(pageIndex) {
    $.ajax({
        url: "http://www.jinzht.com:8080/jinzht/requestActionList.action",
        data: {
            key: "jinzht_server_security",
            partner: "f784463924c4b750acdb7873747fc745",
            page: pageIndex+"",
            type: "0",
            platform: "0",
            requestType: "webRequest"
        },
        contentType: "application/json; charset=utf-8",
        type: "get",
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            if(data.status==200 ){
                setHtmlJson(JSON.stringify(data.data));
            }else {
            }
        },
        error: function (xhr, textStatus, errMsg) {
            console.log("error:");
        }
    });
}


function  setHtmlJson(dataStr){
    var contentHtml = "";
    var dataJsonList = JSON.parse(dataStr);
    for (var i = 0; i < dataJsonList.length; i++) {
        contentHtml = contentHtml + '';
        contentHtml = contentHtml + '<a class="sport_card" href="http://www.jinzht.com/app/actionDetail/'+dataJsonList[i].actionId+'">';
        contentHtml = contentHtml + '<img src="' + dataJsonList[i].startPageImage + '" class="card_img">';
        contentHtml = contentHtml + '<div class="sport_b">';
        if(dataJsonList[i].name.length>18){
            contentHtml = contentHtml + '<div class="s_title">' + dataJsonList[i].name.substring(0,18) + '...</div>';
        }else {
            contentHtml = contentHtml + '<div class="s_title">' + dataJsonList[i].name + '</div>';
        }
        var actionintroduces = dataJsonList[i].actionintroduces;
        if(actionintroduces.length!=0){
                contentHtml = contentHtml + '<div class="s_content">' + actionintroduces[0].content.substring(0,40) + '...</div>';
        }else {
            contentHtml = contentHtml + '<div class="s_content">\n\n敬请期待\n\<n></n></div>';
        }
        contentHtml = contentHtml + '<div class="line_dian_bottom"></div>';
        contentHtml = contentHtml + '<div class="s_bottom">';
        contentHtml = contentHtml + '<div class="left" style="width: 100%;float: left">';
        contentHtml = contentHtml + '<div class="text_t" style="width: 100%;float: left">' + dataJsonList[i].address + '</div>';
        contentHtml = contentHtml + '</div>';
        contentHtml = contentHtml + '<div class="left" style="width: 100%;float: left">';
        contentHtml = contentHtml + '<div class="text_b" style="width: 100%;float: left">' + dataJsonList[i].endTime + '</div>';
        contentHtml = contentHtml + '</div>';
        contentHtml = contentHtml + '</div>';
        contentHtml = contentHtml + '</div>';
        contentHtml = contentHtml + '</a>';
    }
    $("#id_sport_cards").html(contentHtml);
}
