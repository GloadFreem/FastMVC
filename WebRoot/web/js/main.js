$(document).ready(function () {


    $("#id_main").click(function () {
        window.location.href = "./index.action";
    });
    $("#id_report").click(function () {
        window.location.href = "./report.action";
    });
    $("#id_project").click(function () {
        window.location.href = "./project.action";
    });

    var screenWidth = window.outerWidth;
    console.log(screenWidth)

    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev'
    });
    $("#btn_next").click(function () {
        next();
    });
    $("#btn_back").click(function () {
        back();
    });
});


function changeBar() {
    for (var i = 0; i < 7; i++) {
        $(".btn-content").children().eq(i).animate({
            marginLeft: -150 * (7 - i) + "px"
        });
    }
}
function changeBar2() {
    for (var i = 0; i < 7; i++) {
        $(".btn-content").children().eq(i).animate({
            marginLeft: "0px"
        });
    }
}

/**
 *上一张背景
 */
function back() {
    $("#tttt").removeClass("title-bar-1");
    $("#tttt").addClass("title-bar-0");
}

/**
 *下一张背景
 */
function next() {

    $("#tttt").removeClass("title-bar-0");
    $("#tttt").addClass("title-bar-1");

}


/**
 *下一张背景
 */
function changePage(id) {
    $("#id_bg_top").css("background-image", urlStr[id]);
    $("#id_bg_top").css("background-position", "center");

}


function getHttpMessage() {
    $.ajax({
        url: "http://www.jinzht.com:8080/jinzht/requestConsultList.action",
        data: {
            key: "jinzht_server_security",
            partner: "f784463924c4b750acdb7873747fc745",
            page: "0",
            type: "0",
            platform: "0",
            requestType: "webRequest"
        },
        contentType: "application/json; charset=utf-8",
        type: "get",
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            //console.log(data);
            setMsgJsonData(JSON.stringify(data.data));

        },
        error: function (xhr, textStatus, errMsg) {
            console.log("error:");
        }
    });
}

/**
 * 测试card 数据
 */
function initTestData() {

    $.ajax({
        url: "http://www.jinzht.com:8080/jinzht/requestProjectList.action",
        data: {
            key: "jinzht_server_security",
            partner: "f784463924c4b750acdb7873747fc745",
            page: "0",
            type: "0",
            platform: "0",
            requestType: "webRequest"
        },
        contentType: "application/json; charset=utf-8",
        type: "get",
        crossDomain: true,
        //async: false,
        //dataType: "jsonp",
        //jsonp: "callback",
        //jsonpCallback: "showLocation",
        dataType: 'json',
        success: function (data) {
            //console.log(data);
            setJsonData(JSON.stringify(data.data));

        },
        error: function (xhr, textStatus, errMsg) {
            //console.log("error:");
        }
    });

}

function setJsonData(dataStr) {

    var contentHtml = "";
    //var dataJsonList = [
    var dataJsonList = JSON.parse(dataStr);
    //console.log(dataJsonList.length);
    for (var i = 0; i < 8; i++) {
        contentHtml = contentHtml + '';
        contentHtml = contentHtml + ' <a class="card" href="http://www.jinzht.com/app/projectDetail/' + dataJsonList[i].projectId + '">';
        contentHtml = contentHtml + '     <img src="' + dataJsonList[i].startPageImage + '" class="card_img" id=project_' + dataJsonList[i].projectId + '>';
        contentHtml = contentHtml + '     <div class="item" style="margin-top: 15px">';
        contentHtml = contentHtml + '     <div class="t_title" style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box; -webkit-box-orient: vertical;-webkit-line-clamp: 1; max-width: 70%;overflow: hidden;font-size: ">' + dataJsonList[i].abbrevName + '</div>';
        contentHtml = contentHtml + '    <div class="t_c">' + dataJsonList[i].financestatus.name + '</div>';
        contentHtml = contentHtml + '    </div>';
        contentHtml = contentHtml + '   <div class="item" style="margin-top: 16px">';
        contentHtml = contentHtml + '   <div class="t_text" style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;overflow: hidden;">' + dataJsonList[i].description.trim().substring(0, 40) + '...</div>';
        contentHtml = contentHtml + '   </div>';
        contentHtml = contentHtml + '   <div class="item" style="margin-top: 10px">';
        contentHtml = contentHtml + '     <div class="icon"></div>';
        var type = dataJsonList[i].industoryType;
        var strarray = type.split("，");
        //console.log(strarray);
        for (var t = 0; t < strarray.length; t++) {
            contentHtml = contentHtml + '    <div class="icon_text">' + (strarray[t]) + '</div>';
        }
        contentHtml = contentHtml + '    </div>';
        contentHtml = contentHtml + '   <div class="item" style="margin-top: 10px">';
        var num = parseInt(Math.floor(dataJsonList[i].roadshows[0].roadshowplan.financedMount) * 100 / parseInt(dataJsonList[i].roadshows[0].roadshowplan.financeTotal));
        var left;
        var t_left;
        left = num * 2.6;
        if (num < 100) {
            t_left = left - 30;
        } else {
            t_left = left - 45;
        }
        contentHtml = contentHtml + '   <div class="process_title" style="margin-left: ' + t_left + 'px;">' + num + '%</div>';
        contentHtml = contentHtml + '    <div class="process_bar">';
        //contentHtml = contentHtml + '    <div class="process_bar_ing" style="width: '+left+'px;"></div>';
        if (num == 100) {
            contentHtml = contentHtml + '    <div class="process_bar_ing" style="width: ' + left + 'px;   background: -webkit-linear-gradient(left, #ffa153, #c25616);"></div>';
        } else {
            contentHtml = contentHtml + '    <div class="process_bar_ing" style="width: ' + left + 'px;"></div>';
        }
        contentHtml = contentHtml + '    </div>';
        contentHtml = contentHtml + '    </div>';
        contentHtml = contentHtml + '   <div class="item" style="margin-top: 15px">';
        contentHtml = contentHtml + '    <div class="item_three">';
        contentHtml = contentHtml + '    <div class="item_three_t t_left" style="margin-left: 10px">' + dataJsonList[i].collectionCount + '</div>';
        contentHtml = contentHtml + '    <div class="item_three_b t_left">人气指数</div>';
        contentHtml = contentHtml + '    </div>';
        contentHtml = contentHtml + '  <div class="item_three">';
        var endDate = dataJsonList[i].roadshows[0].roadshowplan.endDate;
        contentHtml = contentHtml + '   <div class="item_three_t t_center">' + getEndTime(endDate) + '</div>';
        contentHtml = contentHtml + '   <div class="item_three_b t_center">剩余天数</div>';
        contentHtml = contentHtml + '   </div>';
        contentHtml = contentHtml + '   <div class="item_three">';
        contentHtml = contentHtml + '  <div class="item_three_t t_right">' + dataJsonList[i].roadshows[0].roadshowplan.financeTotal + '万</div>';
        contentHtml = contentHtml + '   <div class="item_three_b t_right">融资额度</div>';
        contentHtml = contentHtml + '     </div>';
        contentHtml = contentHtml + '    </div>';
        contentHtml = contentHtml + '    </a>';
    }
    $("#id_cards").html(contentHtml);


    //图片请求
    for (var k = 0; k < dataJsonList.length; k++) {
        var projectid = dataJsonList[k].projectId;
        //console.log("id:" + projectid)
        getMP3data(projectid);
    }
}


function getMP3data(projectid) {
    $.ajax({
        url: "http://www.jinzht.com:8080/jinzht/requestScene.action",
        data: {
            key: "jinzht_server_security",
            partner: "f784463924c4b750acdb7873747fc745",
            platform: "0",
            projectId: projectid,
            requestType: "webRequest"
        },
        contentType: "application/json; charset=utf-8",
        type: "get",
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            //console.log(data);
            if (data.status == 200) {
                getPPTdata(data.data[0].sceneId, projectid);
            }
        },
        error: function (xhr, textStatus, errMsg) {
            console.log("error:");
        }
    });
}


function getPPTdata(sceneId, id) {

    $.ajax({
        url: "http://www.jinzht.com:8080/jinzht/requestRecorData.action",
        data: {
            key: "jinzht_server_security",
            partner: "f784463924c4b750acdb7873747fc745",
            platform: "0",
            sceneId: sceneId,
            page: "0",
            requestType: "webRequest"
        },
        contentType: "application/json; charset=utf-8",
        type: "get",
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            //console.log(data);
            if (data.status == 200) {
                if (data.data[0].imageUrl != "") {
                    var imgid = "#project_" + id;
                    //console.log(data.data[0].imageUrl);
                    //console.log(imgid);
                    $(imgid).attr("src", data.data[0].imageUrl);
                }

            }
            //var id = "#project"+dataJsonList[k].projectId;

        },
        error: function (xhr, textStatus, errMsg) {
            console.log("error:");
        }
    });
}


function setMsgJsonData(dataStr) {
    var contentHtml = "";
    //var dataJsonList = [
    var dataMsgJsonList = JSON.parse(dataStr);
    //console.log(dataMsgJsonList);
    //if(dataMsgJsonList.length>8){
    //1
    for (var i = 0; i < 9; i++) {
        var id_img = "#msg_img_" + i;
        var id_title = "#msg_title_" + i;
        //console.log(dataMsgJsonList[i].image);
        $(id_img).css("background", "#EBEBEF");
        $(id_img).css("background-image", "url(" + dataMsgJsonList[i].image + ")");
        $(id_img).css("backgroundRepeat", "no-repeat");
        $(id_img).css("background-size", "100%");
        $(id_title).text(dataMsgJsonList[i].title);
        if (i > 4) {
            var id_content = "#msg_content_" + i;
            var id_frome = "#msg_from_" + i;
            $(id_content).text(dataMsgJsonList[i].title);
            $(id_frome).text(dataMsgJsonList[i].createDate + "    " + dataMsgJsonList[i].contenttype.name);

        } else {
            $(id_img).attr('href', dataMsgJsonList[i].url);
        }
    }
    $("#msg_div_5").attr('href', dataMsgJsonList[5].url);
    $("#msg_div_6").attr('href', dataMsgJsonList[6].url);
    $("#msg_div_7").attr('href', dataMsgJsonList[7].url);
    $("#msg_div_8").attr('href', dataMsgJsonList[8].url);
}

function getBannerdata() {
    $.ajax({
        url: "http://www.jinzht.com:8080/jinzht/bannerSystem.action",
        data: {
            key: "jinzht_server_security",
            partner: "f784463924c4b750acdb7873747fc745",
            requestType: "webRequest"
        },
        contentType: "application/json; charset=utf-8",
        type: "get",
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            if (data.status == 200) {
                bannerData = data.data;
                $("#id_bg_top").click(function () {
                    var id = Math.abs(index % 3);
                    console.log(id);
                    if (bannerData != "") {
                        window.location.href = bannerData[3 - id].body.url;
                    }
                });
                //getPPTdata(data.data[0].sceneId,projectid);
            }
        },
        error: function (xhr, textStatus, errMsg) {
            console.log("error:");
        }
    });
}


function getEndTime(str) {
    var day = "15";
    Date.prototype.diff = function (date) {
        return (date.getTime() - this.getTime()) / (24 * 60 * 60 * 1000);
    };
// 构造两个日期，分别是系统时间和2013/04/08 12:43:45
    var now = new Date();
    var date = new Date(str);
// 调用日期差方法，求得参数日期与系统时间相差的天数
    day = Math.floor(now.diff(date));
// 打印日期差
    return day;

}