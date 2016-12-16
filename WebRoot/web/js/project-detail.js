

var imageUrls = new Array();
var index = -1;
var projectId;

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
    projectId = window.location.href.substr(window.location.href.indexOf("=")+1,window.location.href.length-1);
    console.log(projectId);
    requestScene();
});



function requestScene() {
    $.ajax({
        type: "POST",
        url: "http://www.jinzht.com:8080/jinzht/requestScene.action",
        data: {
            "key": "jinzht_server_security",
            "partner": "804BC337815F918AA296883FE0DFC36F",
            "projectId": projectId,
            "requestType": "webRequest",
        },
        async: true,
        dataType: "json",
        success: function (returnedData) {
            console.log(returnedData);
            if(returnedData.data[0]!=null&&returnedData.data[0]!="[]"){
            
            	var sceneId = returnedData.data[0].sceneId;
            	  requestRecorData(sceneId);
            }
 
        },
        error: function (e) {
   
            //请求失败时调用此函数
        }
    });

}

function initSmallImg() {
    var content = "";
    for(var i=0;i<imageUrls.length;i++){
        content +=' <img src="'+imageUrls[i]+'" class="smallImg"  onclick="getpage('+i+')">';
    }
    $("#small_img").html(content);
}
function requestRecorData(sceneId){
    $.ajax({
        type: "POST",
        url: "http://www.jinzht.com:8080/jinzht/requestRecorData.action",
        data: {
            "key": "jinzht_server_security",
            "partner": "804BC337815F918AA296883FE0DFC36F",
            "sceneId": sceneId,
            "requestType": "webRequest",
            "page": 1
        },
        async: true,
        dataType: "json",
        success: function (returnedData) {
            console.log(returnedData);
            var datas = returnedData.data;
            for(var i = 0; i < datas.length; i++){
                imageUrls[i] = datas[i].imageUrl;
            }
           
            initSmallImg();
            next();
        },
        error: function (e) {
        
            //请求失败时调用此函数
        }
    });
}

/**
 *上一张图片
 */
function pre() {
    index--;
    if(index == -1){
        index = imageUrls.length - 1;
    }
    var imgPath = imageUrls[Math.abs(index % imageUrls.length)];
    $("#middle_left_bg_img").css("background-image", "url("+imgPath+")");
    
    changeClass(index);
}


/**
 *
 */
function getpage(id) {
    var imgPath = imageUrls[id];
    $("#middle_left_bg_img").css("background-image", "url("+imgPath+")");
    
    changeClass(id);
    index = id;
}

function  changeClass(id){
	for(var i=0;i<imageUrls.length;i++){
    	$("#small_img").children().eq(i).removeClass();
    	if(id!=i){
    		   $("#small_img").children().eq(i).addClass("smallImg");
    	}else{
    		$("#small_img").children().eq(i).addClass("smallImg-b");
    	}
    }
}

/**
 *下一张图片
 */
function next() {
    index++;
    if(index == imageUrls.length){
        index = 0;
    }
    var imgPath = imageUrls[Math.abs(index % imageUrls.length)];
    $("#middle_left_bg_img").css("background-image", "url("+imgPath+")");
    changeClass(index);
}



