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
    initData();


});




/**
 * 测试card 数据
 */
function initData() {

    pageId++;

    $.ajax({
        url: BasePath+"requestWebViewPointList.action",
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
            //console.log(data);
            if(data.status=="200"){
                setJsonData(JSON.stringify(data.data));
            }else  if(data.status=="201"){

                showNoMore();
            }


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
        contentHtml = contentHtml + '  <div class="content-item">';
        var imgs = dataJsonList[i].imgurl;
        //console.log(imgs);
        //console.log(imgs.length);
//        if(imgs.length==0){
//            contentHtml = contentHtml + '   <img src="" class="item-img">';
//        }else {
            contentHtml = contentHtml + '  <div class="d-img"> <img src="' + imgs+ '" class="item-img"></div>';
//        }
        //contentHtml = contentHtml + '   <img src="' + imgs[0].url + '" class="item-img">';
        contentHtml = contentHtml + '  <div class="item-r">';
        contentHtml = contentHtml + '   <a href="'+BasePath+'web/reportDetail.action?id=' + dataJsonList[i].infoId + '" class="item-title">' + dataJsonList[i].title + '</a>';
        if (dataJsonList[i].oringl.lastIndexOf("观点") > 0) {
            contentHtml = contentHtml + '  <div class="item-type-1">' + dataJsonList[i].oringl + '</div>';
        } else if (dataJsonList[i].oringl.lastIndexOf("分析") > 0) {
            contentHtml = contentHtml + '  <div class="item-type-2">' + dataJsonList[i].oringl + '</div>';
        } else {
            contentHtml = contentHtml + '  <div class="item-type-0">' + dataJsonList[i].oringl + '</div>';
        }

        contentHtml = contentHtml + ' <div class="item-time">' + dataJsonList[i].publicDate + '</div>';
        contentHtml = contentHtml + '   <div class="item-desc">' + dataJsonList[i].desc + '</div>';
        contentHtml = contentHtml + '   </div>';
        contentHtml = contentHtml + '   </div>';
    }

    contentHtml = contentHtml + '<div class="content-item" id="new_list_more"> <div class="content-more" onclick="initData()">查看更多</div> </div>';
    $("#new_report_list").append(contentHtml);

}


function  showNoMore(){
    $("#new_list_more").remove();
   var contentHtml = '<div class="content-item" id="new_list_more"> <div class="content-more" >没有更多了</div> </div>';
    $("#new_report_list").append(contentHtml);
}
