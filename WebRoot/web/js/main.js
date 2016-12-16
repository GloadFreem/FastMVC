var pageId = -1;

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
    console.log(screenWidth);

    var swiper;
    if (screenWidth >= 1200) {
        swiper = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            slidesPerView: 3,
            paginationClickable: true,
            nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
            loop: true,
            loopAdditionalSlides: 100
        });
    } else {
        swiper = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            slidesPerView: 1,
            height:300,
            paginationClickable: true,
            nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
            loop: true,
            loopAdditionalSlides: 100
        });
    }
    $("#btn_next").click(function () {
    	 back(); 
    });
    $("#btn_back").click(function () {
       next();
    });


    initData();
    initReport();
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
function initData() {

    pageId++;

    $.ajax({
        url: BasePath+"requestWebThinkTankList.action",
        data: {
            key: "jinzht_server_security",
            partner: "f784463924c4b750acdb7873747fc745",
            page: pageId
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
            if(data.status=="200"){
                setJsonData(JSON.stringify(data.data));
            }else  if(data.status=="201"){
                if(pageId==0){
                    showSorry();
                }else {
                    showNoMore();
                }

            }

        },
        error: function (xhr, textStatus, errMsg) {
            //console.log("error:");
        }
    });

}




/**
 * 相关报告
 */
function initReport() {
    //'+BasePath+'requestViewPointList.action?key=jinzht_server_security&partner=sdfwefwf&page=0
    $.ajax({
        url: BasePath+"requestViewPointList.action",
        data: {
            key: "jinzht_server_security",
            partner: "f784463924c4b750acdb7873747fc745",
            page: 0
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
            setJsonReportData(JSON.stringify(data.data));

        },
        error: function (xhr, textStatus, errMsg) {
            //console.log("error:");
        }
    });
}

function setJsonData(dataStr) {

    var contentHtml = "";
    $("#new_list_more").remove();
    //var dataJsonList = [
    var dataJsonList = JSON.parse(dataStr);
    //console.log(dataJsonList.length);
    for (var i = 0; i < dataJsonList.length; i++) {
        contentHtml = contentHtml + '';
        contentHtml = contentHtml + ' <a href="'+BasePath+'web/MainDetail.action?id=' + dataJsonList[i].id + '" > <div class="content-item">';
        contentHtml = contentHtml + '  <div class="d-img"> <img src="' + dataJsonList[i].images[0] + '" class="item-img"></div>';
        contentHtml = contentHtml + '  <div class="item-r">';
        contentHtml = contentHtml + '   <div class="item-title">' + dataJsonList[i].title + '</div>';
        if (dataJsonList[i].oringl.length < 4) {
            contentHtml = contentHtml + '  <div class="item-type-0">' + dataJsonList[i].oringl + '</div>';
        } else if (dataJsonList[i].oringl.length ==4) {
            contentHtml = contentHtml + '  <div class="item-type-1">' + dataJsonList[i].oringl + '</div>';
        } else  if (dataJsonList[i].oringl.length ==5) {
            contentHtml = contentHtml + '  <div class="item-type-2">' + dataJsonList[i].oringl + '</div>';
        }else  if (dataJsonList[i].oringl.length >5) {
            contentHtml = contentHtml + '  <div class="item-type-3">' + dataJsonList[i].oringl + '</div>';
        }

        contentHtml = contentHtml + ' <div class="item-time">' + dataJsonList[i].publicDate + '</div>';
        contentHtml = contentHtml + '   <div class="item-desc">' + dataJsonList[i].desc + '</div>';
        contentHtml = contentHtml + '   </div>';
        contentHtml = contentHtml + '   </div></a>';
    }

    contentHtml = contentHtml + '<div class="content-item" id="new_list_more"> <div class="content-more" onclick="initData()">查看更多</div> </div>';
    $("#new_list").append(contentHtml);

}

function  showNoMore(){
    $("#new_list_more").remove();
    var contentHtml = '<div class="content-item" id="new_list_more"> <div class="content-more" >没有更多了</div> </div>';
    $("#new_list").append(contentHtml);
}

function  showSorry(){
    $("#new_list_more").remove();
    var contentHtml = '<div  style="height: 800px;color: #cccccc;"> <div >暂无数据</div> </div>';
    $("#new_list").append(contentHtml);
}


function setJsonReportData(dataStr) {

    var contentHtml = "";
    var dataJsonList = JSON.parse(dataStr);
    //console.log(dataJsonList.length);
    for (var i = 0; i < 8; i++) {
        contentHtml = contentHtml + '';
        contentHtml = contentHtml + '<a href="./reportDetail.action?id=' + dataJsonList[i].infoId + '"> <div class="opinion-text">' + dataJsonList[i].title + '</div></a>';
    }

    contentHtml = contentHtml + '<a href="./report.action"><div class="content-item"><div class="content-opinion-more" >更多资讯</div> </div></a>';
    $("#report_list").html(contentHtml);

}

function goReport() {
    window.location.href = "./report.action";
}


function goDetail(id) {

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