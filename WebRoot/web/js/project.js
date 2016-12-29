
var typelist = [0];
var addresslist = [0];
var rangelist = [0];

var typeStr = "";
var addressStr = "";
var rangeStr = "";

var pageindex = 0;

Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};

Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

$(document).ready(function() {

	$("#id_main").click(function() {
		window.location.href = "./index.action";
	});
	$("#id_report").click(function() {
		window.location.href = "./report.action";
	});
	$("#id_project").click(function() {
		window.location.href = "./project.action";
	});
	
	$(".p-btn").click(function() {
//		init(0);
		i++;
//		if(i%2==0){
//			$("#id_phone_btn").removeClass();
//			$("#id_phone_btn").show();
	
//			$("#id_phone_btn").addClass("phone_btn");
//		}else{
			$("#id_phone_btn").removeClass();
			$("#id_phone_btn").show();
			$("#id_phone_btn").addClass("phone_btn");
			$("#phont_btn").show();
//			setTimeout(function(){
			
//			},120);
			setTimeout(function(){
				$(".p-content").hide();
				$("#id_phone_btn").hide();
			},180);
			
			init(0);
			
//		}
	
	});
	
	var  i = 0;
	$("#phont_btn").click(function() {
		
//		alert(123);
		$("#phont_btn").hide();
		i++;
//		if(i%2==0){
		
	
			$("#id_phone_btn").removeClass();
			$("#id_phone_btn").show();
			$("#id_phone_btn").addClass("phone_btn3");
			setTimeout(function(){
				$(".p-content").show();
			},50);
	
	});
	
	
	   var screenWidth = window.outerWidth;
	   if(screenWidth<1200){
			$('.p-content').bind("touchmove",function(e){
				e.preventDefault();
				});
	   }

	// //test 活动数据
	init(0);
	// //初始化页码
	initPageB();
	//

});

function getRequestStr(arrayStr){
	var str = arrayStr;
	var result = "";
	if(str!=null&&str!="[]"){
		str = str;
	}else{
		str = [0];
	}
	for(var i=0;i<str.length;i++){
		if(i!=str.length-1){
			result += str[i]+",";
		}else{
			result += str[i];
		}
	}
	console.log("result:"+result);
	
	return  result;
}



function checkType(num, key) {
	var size = $("#check_money").children().length;
	if (num == 0) {
		for (var i = 0; i < size; i++) {
			$("#check_money").children().eq(i).removeClass();
		}
		for (var j = 0; j < size; j++) {
			if (j == 0) {
				$("#check_money").children().eq(0).addClass("checked");
			} else {
				$("#check_money").children().eq(j).addClass("normal");
			}
		}
		typelist = [0];
	} else {
		$("#check_money").children().eq(num).removeClass();
		$("#check_money").children().eq(0).removeClass();	
		// }
		if (typelist.indexOf(key) == -1) {
			typelist[typelist.length] = key;
			$("#check_money").children().eq(num).addClass("checked");
		} else {
			typelist.remove(key);
			$("#check_money").children().eq(num).addClass("normal");
		}
		$("#check_money").children().eq(0).addClass("normal");
		typelist.remove(0);
		
		if(typelist.length==0){
			$("#check_money").children().eq(0).removeClass();
			$("#check_money").children().eq(0).addClass("checked");
		}
	}
	console.log(typelist);
	typeStr = getRequestStr(typelist);
	console.log(typeStr);
	init(0);
}



function checkRange(num, key) {
	var size = $("#check_range").children().length;
	if (num == 0) {
		for (var i = 0; i < size; i++) {
			$("#check_range").children().eq(i).removeClass();
		}
		for (var j = 0; j < size; j++) {
			if (j == 0) {
				$("#check_range").children().eq(0).addClass("checked");
			} else {
				$("#check_range").children().eq(j).addClass("normal");
			}
		}
		rangelist = [0];
	} else {
		$("#check_range").children().eq(num).removeClass();
		$("#check_range").children().eq(0).removeClass();	
		// }
		if (rangelist.indexOf(key) == -1) {
			rangelist[rangelist.length] = key;
			$("#check_range").children().eq(num).addClass("checked");
		} else {
			rangelist.remove(key);
			$("#check_range").children().eq(num).addClass("normal");
		}
		$("#check_range").children().eq(0).addClass("normal");
		rangelist.remove(0);
		
		if(rangelist.length==0){
			$("#check_range").children().eq(0).removeClass();
			$("#check_range").children().eq(0).addClass("checked");
		}
	}
	console.log(rangelist);
	rangeStr = getRequestStr(rangelist);
	console.log(rangeStr);
	init(0);
}



function checkAddress(num, key) {
	var size = $("#check_address").children().length;
	if (num == 0) {
		for (var i = 0; i < size; i++) {
			$("#check_address").children().eq(i).removeClass();
		}
		for (var j = 0; j < size; j++) {
			if (j == 0) {
				$("#check_address").children().eq(0).addClass("checked");
			} else {
				$("#check_address").children().eq(j).addClass("normal");
			}
		}
		addresslist = [0];
	} else {
		$("#check_address").children().eq(num).removeClass();
		$("#check_address").children().eq(0).removeClass();	
		// }
		if (addresslist.indexOf(key) == -1) {
			addresslist[addresslist.length] = key;
			$("#check_address").children().eq(num).addClass("checked");
		} else {
			addresslist.remove(key);
			$("#check_address").children().eq(num).addClass("normal");
		}
		$("#check_address").children().eq(0).addClass("normal");
		addresslist.remove(0);
		
		if(addresslist.length==0){
			$("#check_address").children().eq(0).removeClass();
			$("#check_address").children().eq(0).addClass("checked");
		}
	}
	console.log(addresslist);
	addressStr = getRequestStr(addresslist);
	console.log(addressStr);
	init(0);
}





function checkType2(num, key) {
	var size = $("#check_money2").children().length;
	if (num == 0) {
		for (var i = 0; i < size; i++) {
			$("#check_money2").children().eq(i).removeClass();
		}
		for (var j = 0; j < size; j++) {
			if (j == 0) {
				$("#check_money2").children().eq(0).addClass("checked");
			} else {
				$("#check_money2").children().eq(j).addClass("normal");
			}
		}
		typelist = [0];
	} else {
		$("#check_money2").children().eq(num).removeClass();
		$("#check_money2").children().eq(0).removeClass();	
		// }
		if (typelist.indexOf(key) == -1) {
			typelist[typelist.length] = key;
			$("#check_money2").children().eq(num).addClass("checked");
		} else {
			typelist.remove(key);
			$("#check_money2").children().eq(num).addClass("normal");
		}
		$("#check_money2").children().eq(0).addClass("normal");
		typelist.remove(0);
		
		if(typelist.length==0){
			$("#check_money2").children().eq(0).removeClass();
			$("#check_money2").children().eq(0).addClass("checked");
		}
	}
	console.log(typelist);
	typeStr = getRequestStr(typelist);
	console.log(typeStr);
//	init(0);
}



function checkRange2(num, key) {
	var size = $("#check_range2").children().length;
	if (num == 0) {
		for (var i = 0; i < size; i++) {
			$("#check_range2").children().eq(i).removeClass();
		}
		for (var j = 0; j < size; j++) {
			if (j == 0) {
				$("#check_range2").children().eq(0).addClass("checked");
			} else {
				$("#check_range2").children().eq(j).addClass("normal");
			}
		}
		rangelist = [0];
	} else {
		$("#check_range2").children().eq(num).removeClass();
		$("#check_range2").children().eq(0).removeClass();	
		// }
		if (rangelist.indexOf(key) == -1) {
			rangelist[rangelist.length] = key;
			$("#check_range2").children().eq(num).addClass("checked");
		} else {
			rangelist.remove(key);
			$("#check_range2").children().eq(num).addClass("normal");
		}
		$("#check_range2").children().eq(0).addClass("normal");
		rangelist.remove(0);
		
		if(rangelist.length==0){
			$("#check_range2").children().eq(0).removeClass();
			$("#check_range2").children().eq(0).addClass("checked");
		}
	}
	console.log(rangelist);
	rangeStr = getRequestStr(rangelist);
	console.log(rangeStr);
//	init(0);
}



function checkAddress2(num, key) {
	var size = $("#check_address2").children().length;
	if (num == 0) {
		for (var i = 0; i < size; i++) {
			$("#check_address2").children().eq(i).removeClass();
		}
		for (var j = 0; j < size; j++) {
			if (j == 0) {
				$("#check_address2").children().eq(0).addClass("checked");
			} else {
				$("#check_address2").children().eq(j).addClass("normal");
			}
		}
		addresslist = [0];
	} else {
		$("#check_address2").children().eq(num).removeClass();
		$("#check_address2").children().eq(0).removeClass();	
		// }
		if (addresslist.indexOf(key) == -1) {
			addresslist[addresslist.length] = key;
			$("#check_address2").children().eq(num).addClass("checked");
		} else {
			addresslist.remove(key);
			$("#check_address2").children().eq(num).addClass("normal");
		}
		$("#check_address2").children().eq(0).addClass("normal");
		addresslist.remove(0);
		
		if(addresslist.length==0){
			$("#check_address2").children().eq(0).removeClass();
			$("#check_address2").children().eq(0).addClass("checked");
		}
	}
	console.log(addresslist);
	addressStr = getRequestStr(addresslist);
	console.log(addressStr);
//	init(0);
}



/**
 * 初始化页码
 */
function initPageB() {
	$(".tcdPageCode").createPage({
		pageCount : 11,
		current : 1,
		backFn : function(p) {
			console.log("123:" + p);
			pageindex = parseInt(p - 1);
			init(parseInt(p - 1));
		}
	});
}

/**
 * 点击监听
 */
function initClick() {
}
/**
 * 测试 活动 数据
 * 
 * @param pageIndex
 *            页码
 */
function init(pageIndex) {
	$.ajax({
		url : BasePath + "requestSearchProjectList.action",
		data : {

			page : pageIndex + "",
			type : typeStr,
			address : addressStr,
			range : rangeStr
		},
		contentType : "application/json; charset=utf-8",
		type : "get",
		crossDomain : true,
		dataType : 'json',
		success : function(data) {
			if (data.status == 200) {
				
				setJsonData(JSON.stringify(data.data));
			} else {
			}
		},
		error : function(xhr, textStatus, errMsg) {
			console.log("error:");
		}
	});
}

function setJsonData(dataStr) {

	var contentHtml = "";
	// var dataJsonList = [
	var dataJsonList = JSON.parse(dataStr);
   if(dataJsonList.length>0){
	   
   
	for (var i = 0; i < dataJsonList.length; i++) {
		contentHtml = contentHtml + '';
		contentHtml = contentHtml + ' <a class="card" href="' + BasePath
				+ 'web/projectDetail.action?id=' + dataJsonList[i].projectId
				+ '">';
		contentHtml = contentHtml + '     <img src="'
				+ dataJsonList[i].startPageImage
				+ '" class="card_img" id=project_' + dataJsonList[i].projectId
				+ '>';
		contentHtml = contentHtml
				+ '     <div class="c-item" >';
		contentHtml = contentHtml
				+ '     <div class="t_title" style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box; -webkit-box-orient: vertical;-webkit-line-clamp: 1; max-width: 60%;overflow: hidden; ">'
				+ dataJsonList[i].abbrevName + '</div>';
		contentHtml = contentHtml + '    <div class="t_c">'
				+ dataJsonList[i].financestatus.name + '</div>';
		contentHtml = contentHtml + '    </div>';
		contentHtml = contentHtml
				+ '   <div class="c-item" >';
		contentHtml = contentHtml
				+ '   <div class="t_text" style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;overflow: hidden;">'
				+ dataJsonList[i].description.trim().substring(0, 40)
				+ '...</div>';
		contentHtml = contentHtml + '   </div>';
		contentHtml = contentHtml
				+ '   <div class="c-item" >';
		contentHtml = contentHtml + '     <div class="icon"></div>';
		var type = dataJsonList[i].industoryType;
		var strarray = type.split("，");
		// console.log(strarray);
		for (var t = 0; t < strarray.length; t++) {
			contentHtml = contentHtml + '    <div class="icon_text">'
					+ (strarray[t]) + '</div>';
		}
		contentHtml = contentHtml + '    </div>';
		contentHtml = contentHtml
				+ '   <div class="c-item" >';
		var num = parseInt(Math
				.floor(dataJsonList[i].roadshows[0].roadshowplan.financedMount)
				* 100
				/ parseInt(dataJsonList[i].roadshows[0].roadshowplan.financeTotal));
		var left;
		var t_left;
		if (num > 100) {
			num = 99;
		}
		left = num * 2.6;

		if (num > 100) {
			t_left = 230;
		} else if (num < 100 && num > 10) {
			t_left = left - 30;
		} else if (num < 11) {
			t_left = 0;
		} else {
			t_left = left - 45;
		}
		contentHtml = contentHtml
				+ '   <div class="process_title" style="margin-left: ' + t_left
				+ 'px;">' + num + '%</div>';
		contentHtml = contentHtml + '    <div class="process_bar">';
		// contentHtml = contentHtml + ' <div class="process_bar_ing"
		// style="width: '+left+'px;"></div>';
		if (num == 100) {
			contentHtml = contentHtml
					+ '    <div class="process_bar_ing" style="width: '
					+ left
					+ 'px;   background: -webkit-linear-gradient(left, #ffa153, #c25616);"></div>';
		} else {
			contentHtml = contentHtml
					+ '    <div class="process_bar_ing" style="width: ' + left
					+ 'px;"></div>';
		}
		contentHtml = contentHtml + '    </div>';
		contentHtml = contentHtml + '    </div>';
		contentHtml = contentHtml
				+ '  <div class="c-item2" > ';
		contentHtml = contentHtml + '    <div class="item_three">';
		contentHtml = contentHtml
				+ '    <div class="item_three_t t_left">'
				+ dataJsonList[i].collectionCount + '</div>';
		contentHtml = contentHtml
				+ '    <div class="item_three_b t_left">人气指数</div>';
		contentHtml = contentHtml + '    </div>';
		contentHtml = contentHtml + '  <div class="item_three">';
		var endDate = dataJsonList[i].roadshows[0].roadshowplan.endDate;
		contentHtml = contentHtml + '   <div class="item_three_t t_center">'
				+ getEndTime(endDate) + '</div>';
		contentHtml = contentHtml
				+ '   <div class="item_three_b t_center">剩余天数</div>';
		contentHtml = contentHtml + '   </div>';
		contentHtml = contentHtml + '   <div class="item_three">';
		contentHtml = contentHtml + '  <div class="item_three_t t_right">'
				+ dataJsonList[i].roadshows[0].roadshowplan.financeTotal
				+ '万</div>';
		contentHtml = contentHtml
				+ '   <div class="item_three_b t_right">融资额度</div>';
		contentHtml = contentHtml + '     </div>';
		contentHtml = contentHtml + '  </div> ';
		contentHtml = contentHtml + '    </a>';
			
	}
   }else{
	   contentHtml = "<div  style='color:#323232;font-size:18px;margin-top:40px;'>暂无项目</div>"
   }
   
   var screenWidth = window.outerWidth;
   if(screenWidth>1199){
	   $("#id_cards").html(contentHtml);
	 }else{
		 if(pageindex==0){
			 $("#new_list_more").remove();
			   contentHtml = contentHtml + '</div><div class="content-item" id="new_list_more"> <div class="content-more" onclick="getmore();">查看更多</div> </div>';
			  $("#id_cards").html(contentHtml);
		 }else{
			 $("#new_list_more").remove();
			   contentHtml = contentHtml + '</div><div class="content-item" id="new_list_more"> <div class="content-more" onclick="getmore();">查看更多</div> </div>';
		
			  $("#id_cards").append(contentHtml);
		 }
	
	 }
   
}
   
   	
	
 


//	// 图片请求
//	for (var k = 0; k < dataJsonList.length; k++) {
//		var projectid = dataJsonList[k].projectId;
//		console.log("id:" + projectid)
//		getMP3data(projectid);
//	}
function getmore(){
	pageindex ++;
	init(pageindex);
}

function getEndTime(str) {
	var day = "15";
	//ios
	var str = str.replace(/\-/g, "/");  
	Date.prototype.diff = function(date) {
		return (date.getTime() - this.getTime()) / (24 * 60 * 60 * 1000);
	};
	// 构造两个日期，分别是系统时间和2013/04/08 12:43:45
	var now = new Date();
	var date = new Date(str);
	// 调用日期差方法，求得参数日期与系统时间相差的天数
	day = Math.floor(now.diff(date))+"";
//	alert(day);
	// 打印日期差
	return day;

}

function getMP3data(projectid) {
	$.ajax({
		url : "http://www.jinzht.com:8080/jinzht/requestScene.action",
		data : {
			key : "jinzht_server_security",
			partner : "f784463924c4b750acdb7873747fc745",
			platform : "0",
			projectId : projectid,
			requestType : "webRequest"
		},
		contentType : "application/json; charset=utf-8",
		type : "get",
		crossDomain : true,
		dataType : 'json',
		success : function(data) {
			// console.log(data);
			if (data.status == 200) {
				if (data.data != null && data.data != "" && data.data != "[]") {
					getPPTdata(data.data[0].sceneId, projectid);
				}

			}
		},
		error : function(xhr, textStatus, errMsg) {
			console.log("error:");
		}
	});
}

function getPPTdata(sceneId, id) {

	$.ajax({
		url : "http://www.jinzht.com:8080/jinzht/requestRecorData.action",
		data : {
			key : "jinzht_server_security",
			partner : "f784463924c4b750acdb7873747fc745",
			platform : "0",
			sceneId : sceneId,
			page : "0",
			requestType : "webRequest"
		},
		contentType : "application/json; charset=utf-8",
		type : "get",
		crossDomain : true,
		dataType : 'json',
		success : function(data) {
			// console.log(data);
			if (data.status == 200) {
				if (data.data[0].imageUrl != "") {
					var imgid = "#project_" + id;
					// console.log(data.data[0].imageUrl);
					// console.log(imgid);
					$(imgid).attr("src", data.data[0].imageUrl);
				}

			}
			// var id = "#project"+dataJsonList[k].projectId;

		},
		error : function(xhr, textStatus, errMsg) {
			console.log("error:");
		}
	});
}
