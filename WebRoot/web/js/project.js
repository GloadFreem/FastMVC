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
    ////test 活动数据
    init(0);
    ////点击监听
    //initClick();
    ////初始化页码
    initPageB();
    //
    //

  

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
        pageCount: 2,
        current: 1,
        backFn: function (p) {
            console.log("123:" + p);
            init(parseInt(p-1));
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
        url: "http://www.jinzht.com:8080/jinzht/requestProjectList.action",
        data: {
            key: "jinzht_server_security",
            partner: "f784463924c4b750acdb7873747fc745",
            page: pageIndex+"",
            type: "0",
            size:12,
            platform: "0",
            requestType: "webRequest"
        },
        contentType: "application/json; charset=utf-8",
        type: "get",
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            if(data.status==200 ){
                setJsonData(JSON.stringify(data.data));
            }else {
            }
        },
        error: function (xhr, textStatus, errMsg) {
            console.log("error:");
        }
    });
}


function setJsonData(dataStr) {

    var contentHtml = "";
    //var dataJsonList = [
    var dataJsonList = JSON.parse(dataStr);

    for (var i = 0; i < dataJsonList.length; i++) {
        contentHtml = contentHtml + '';
        contentHtml = contentHtml + ' <a class="card" href="'+BasePath+'web/projectDetail.action?id=' + dataJsonList[i].projectId + '">';
        contentHtml = contentHtml + '     <img src="' + dataJsonList[i].startPageImage + '" class="card_img" id=project_' + dataJsonList[i].projectId + '>';
        contentHtml = contentHtml + '     <div class="c-item" style="margin-top: 15px">';
        contentHtml = contentHtml + '     <div class="t_title" style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box; -webkit-box-orient: vertical;-webkit-line-clamp: 1; max-width: 70%;overflow: hidden;font-size: ">' + dataJsonList[i].abbrevName + '</div>';
        contentHtml = contentHtml + '    <div class="t_c">' + dataJsonList[i].financestatus.name + '</div>';
        contentHtml = contentHtml + '    </div>';
        contentHtml = contentHtml + '   <div class="c-item" style="margin-top: 16px">';
        contentHtml = contentHtml + '   <div class="t_text" style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;overflow: hidden;">' + dataJsonList[i].description.trim().substring(0, 40) + '...</div>';
        contentHtml = contentHtml + '   </div>';
        contentHtml = contentHtml + '   <div class="c-item" style="margin-top: 10px">';
        contentHtml = contentHtml + '     <div class="icon"></div>';
        var type = dataJsonList[i].industoryType;
        var strarray = type.split("，");
        //console.log(strarray);
        for (var t = 0; t < strarray.length; t++) {
            contentHtml = contentHtml + '    <div class="icon_text">' + (strarray[t]) + '</div>';
        }
        contentHtml = contentHtml + '    </div>';
        contentHtml = contentHtml + '   <div class="c-item" style="margin-top: 10px">';
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
        contentHtml = contentHtml + '   <div class="c-item" style="margin-top: 15px">';
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
        console.log("id:" + projectid)
        getMP3data(projectid);
    }
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
