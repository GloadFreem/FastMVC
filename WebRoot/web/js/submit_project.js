var step = 0;


var areaNum = 0;

var personNum = 1;
var areaArray = [];

$(document).ready(function () {
    //test 活动数据


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
    $("#id_next").click(function () {

        //check step1
        var img = $("#file_logo").val();
        var name = $("#name").val();
        var area = "";
        for (var i = 0; i < areaArray.length; i++) {
            var id = "#area_" + (areaArray[i]);
            if (i == 0) {
                area += $(id).text();
            } else {
                area += "，" + $(id).text();
            }
        }
        var desc = $("#p_desc").val();
        var money = $("#p_money").val();
        var pdf = $("#file_file").val();
        var pimg = $("#file_photo").val();

        console.log(img);
        console.log(name);
        console.log(area);

        console.log(desc);
        console.log(money);
        console.log(pdf);
        console.log(pimg);
        if (img == "" && img != null) {
            //$('html,body').animate({scrollTop: 0}, 300);
            $("#file_logo").focus();
            swal("请上传LOGO！");

            return;
        }
        if (name == "" && name != null) {
            $("#name").focus();
            swal("请填写项目名称！");
            return;
        }
        if (area == "" && area != null) {
            $("#name").focus();
            swal("请选择行业领域！");
            return;
        }
        if (desc == "" && desc != null) {
            $("#p_desc").focus();
            swal("请填写项目描述！");
            return;
        }
        if (money == "" && money != null) {
            $("#p_money").focus();
            swal("请输入融资金额！");
            return;
        }
        if (pdf == "" && pdf != null) {
            swal("请选择商业计划书！");
            return;
        }
        if (pimg == "" && pimg != null) {
            swal("请选择项目照片！");
            return;
        }
        step++;
        changeStepStatus(step);

        $("#id_area").val(area);

    });
    $("#id_back").click(function () {
        step--;
        changeStepStatus(step);
    });

    addPersoinPhototLinener("persion_1_file","persion_1_img");


    $("#id_add_persion").click(function () {
        personNum++;

        var contentHtml = "";

        contentHtml = contentHtml + '<div class="p_top">成员' + personNum + '</div>';
        contentHtml = contentHtml + ' <div  class="persion-css">';
        contentHtml = contentHtml + '   <div class="p_img" id="persion_' + personNum + '_img" style="float: left;">';
        contentHtml = contentHtml + '   </div>';
        contentHtml = contentHtml + '   <a href="#" class="p_img_text">上传头像<input id="persion_' + personNum + '_file" name="pic" class="img_logo" type="file"/></a>';
        contentHtml = contentHtml + '  <div class="p_title">成员姓名';
        contentHtml = contentHtml + '  </div>';
        contentHtml = contentHtml + '   <div class="p_title left">担任职务</div>';
        contentHtml = contentHtml + '  <input id="persion_' + personNum + '_name" class="p_input" name="name" placeholder="请填写成员的真实姓名"/>';
        contentHtml = contentHtml + '  <input id="persion_' + personNum + '_zhiwu" class="p_input left" name="position" placeholder="请填写成员所担任的职务"/>';
        contentHtml = contentHtml + '  <div class="title" style="margin-top: 10px;padding-left: 10px;">成员简介</div>';
        contentHtml = contentHtml + '  <textarea id="persion_' + personNum + '_desc" class="textarea" rows="4" name="selfDesc" about="" placeholder="请输入该成员的个人简介"></textarea>';
        contentHtml = contentHtml + '   </div>';
        $("#id_add_persion").before(contentHtml);
        var ID = "persion_" + personNum + "_file";
        var imgId = "persion_" + personNum + "_img";
        addPersoinPhototLinener(ID,imgId);
    });

    //提交
    //$("#id_submit").click(function () {
    //
    //});

    //form 表单提交
    $("form").submit(function (e) {
        //alert("Submitted");
        //阻断
        var c_name = $("#c_name").val();
        var c_desc = $("#c_desc").val();
        var address = $("#id_produce").val() +"-"+$("#id_city").val();
        var isNullData = true;
        console.log(c_name);
        console.log(c_desc);
        console.log(address);
        if (c_name == "" && c_name != null) {
            $("#c_name").focus();
            swal("请输入公司名称");
            e.preventDefault();
            return;
        }
        if (c_desc == "" && c_desc != null) {
            $("#c_desc").focus();
            swal("请输入公司描述");
            e.preventDefault();
            return;
        }
        if (address == "" && address != null) {
            $("#id_produce").focus();
            swal("请选择所在地！");
            e.preventDefault();
            return;
        }

        $("#id_adress").val(address);
        //$("#c_desc").val(address);



        for(var i=1;i<personNum+1;i++){

            var p_img = "#persion_"+i+"_file";
            var p_name = "#persion_"+i+"_name";
            var p_zhiwu = "#persion_"+i+"_zhiwu";
            var p_desc =  "#persion_"+i+"_desc";
            console.log($(p_img).val());
            console.log($(p_name).val());
            console.log($(p_zhiwu).val());
            console.log($(p_desc).val());
            if ($(p_img).val() == "" && $(p_img).val() != null) {
                $(p_img).focus();
                swal("请选择成员"+i+"：头像");
                e.preventDefault();
                break;
            };
            if ($(p_name).val() == "" && $(p_name).val() != null) {
                $(p_name).focus();
                swal("请输入成员"+i+"：姓名");
                e.preventDefault();
                break;
            };
            if ($(p_zhiwu).val() == "" && $(p_zhiwu).val() != null) {
                $(p_zhiwu).focus();
                swal("请输入成员"+i+"：职务");
                e.preventDefault();
                break;
            };
            if ($(p_desc).val() == "" && $(p_desc).val() != null) {
                $(p_desc).focus();
                swal("请输入成员"+i+"：描述");
                e.preventDefault();
                break;
            };
        };


    });
    //
    //$("#id_produce").click(function () {
    //    $("#submit_step_1").show();
    //    $("#submit_step_2").hide();
    //});

    var $distpicker = $('#distpicker3');

    $distpicker.distpicker({

        autoSelect: false,
        province: '请选择省份',
        city: '请选择城市'
    });






    getAreaJson();
    addSelectLinener();
    addSelectFileLinener();
    addSelectPhotoinener();

});

function getAreaJson() {
    $.ajax({
        url: "http://www.jinzht.com:8080/jinzht/getIndustoryAreaListAuthentic.action",
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
            if (data.status == 200) {
                setHtmlNumJson(JSON.stringify(data.data));
            } else {
            }
        },
        error: function (xhr, textStatus, errMsg) {
            console.log("error:");
        }
    });

}


function setHtmlNumJson(dataStr) {
    var dataJson = JSON.parse(dataStr);
    //报名人数
    var numtHtml = '';
    areaNum = dataJson.length;
    for (var i = 0; i < dataJson.length; i++) {
        if (i == 0) {
            numtHtml = numtHtml + '  <div class="type"> ';
            numtHtml = numtHtml + '    <a href="javascript:void(0);" class="type-4" id=area_' + dataJson[i].areaId + ' onclick="selectArea(' + dataJson[i].areaId + ')">' + dataJson[i].name + '</a>';
        } else if (i != 0 && i % 5 == 0) {
            numtHtml = numtHtml + '  </div> ';
            numtHtml = numtHtml + '  <div class="type"> ';
            numtHtml = numtHtml + '    <a href="javascript:void(0);" class="type-4" id=area_' + dataJson[i].areaId + ' onclick="selectArea(' + dataJson[i].areaId + ')">' + dataJson[i].name + '</a>';
        } else {
            numtHtml = numtHtml + '    <a href="javascript:void(0);" class="type-4" id=area_' + dataJson[i].areaId + ' onclick="selectArea(' + dataJson[i].areaId + ')">' + dataJson[i].name + '</a>';
        }
    }
    numtHtml = numtHtml + '  </div> ';

    $("#div_area").after(numtHtml);

}

/**
 * 自定义多选按钮
 * @param areaId
 */
function selectArea(areaId) {

    if (isHasCheck(areaId)) {
        deleteAreaArray(areaId);
        areaNum--;
        changeSelectClass();
    } else {
        if (areaArray.length < 3) {
            areaArray[areaArray.length] = areaId;
            changeSelectClass();
            var id = "#area_" + areaId;
            //console.log(areaArray);
        } else {
            alert("您最多可以选择三个领域！");
        }
    }
    function changeSelectClass() {
        for (var i = 1; i < areaNum + 1; i++) {
            var divId = "#area_" + i;
            $(divId).removeClass();
            var isCheck = false;
            for (var j = 0; j < areaArray.length; j++) {
                if (i == areaArray[j]) {
                    isCheck = true;
                    break;
                }
            }
            if (isCheck) {
                $(divId).addClass('type-4-select')
            } else {
                $(divId).addClass('type-4');
            }
        }
    }

    function deleteAreaArray(id) {
        var newArray = [];
        for (var i = 0; i < areaArray.length; i++) {
            if (id != areaArray[i]) {
                newArray[newArray.length] = areaArray[i];
            }
        }
        areaArray = newArray;
        //console.log(areaArray)
    }

    function isHasCheck(id) {
        var isHasCheck = false;
        for (var k = 0; k < areaArray.length; k++) {
            if (id == areaArray[k]) {
                isHasCheck = true;
                break;
            }
        }
        //console.log("isCheck:"+isHasCheck)
        return isHasCheck;
    }
}

function changeStepStatus(id) {
    console.log(id);

    $("#id_step_1").removeClass();
    $("#id_step_2").removeClass();
    $("#id_step_3").removeClass();
    if (id == 0) {
        $("#submit_step_1").show();
        $("#submit_step_2").hide();
        $("#id_step_1").addClass('circle-2');
        $("#id_step_2").addClass('circle-1');
        $("#id_step_3").addClass('circle-1');

    } else if (id == 1) {
        $("#submit_step_1").hide();
        $("#submit_step_2").show();
        $("#id_step_1").addClass('circle-3');
        $("#id_step_2").addClass('circle-2');
        $("#id_step_3").addClass('circle-1');
    } else {

    }
}


function addSelectLinener() {
    var fileInput = document.getElementById("file_logo");//隐藏的file文本ID
    var preview = document.getElementById("logo_img");
    //var imgTest = document.getElementsByClassName("img_text");
    //imgTest.addEventListener('click',function () {
    //    console.log("click");
    //    fileInput.click();//加一个触发事件
    //});
    fileInput.addEventListener('change', function () {
        // 清除背景图片:
        console.log("file change :");
        //preview.style.backgroundImage = '';
        // 检查文件是否选择:
        if (!fileInput.value) {
            //info.innerHTML = '没有选择文件';
            return;
        }
        // 获取File引用:
        var file = fileInput.files[0];
        console.log(file);
        // 获取File信息:
        //info.innerHTML = '文件: ' + file.name + '<br>' +
        //    '大小: ' + file.size + '<br>' +
        //    '修改: ' + file.lastModifiedDate;
        if (file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
            alert('不是有效的图片文件!');
            return;
        }
        // 读取文件:
        var reader = new FileReader();
        reader.onload = function (e) {
            var
                data = e.target.result; // 'data:image/jpeg;base64,/9j/4AAQSk...(base64编码)...'
            preview.style.backgroundImage = 'url(' + data + ')';

        };
        // 以DataURL的形式读取文件:
        reader.readAsDataURL(file);
    });
};


function addSelectFileLinener() {
    var fileInput = document.getElementById("file_file");//隐藏的file文本ID
    var preview = document.getElementById("id_file");
    fileInput.addEventListener('change', function () {
        // 清除背景图片:
        console.log("file change :");
        //preview.style.backgroundImage = '';
        // 检查文件是否选择:
        if (!fileInput.value) {
            //info.innerHTML = '没有选择文件';
            return;
        }
        // 获取File引用:
        var file = fileInput.files[0];
        console.log(file);
        preview.style.fontSize = 12;
        preview.text = (file.name) + '';
    });
};


function addSelectPhotoinener() {
    var fileInput = document.getElementById("file_photo");//隐藏的file文本ID
    var preview = document.getElementById("id_photo");
    fileInput.addEventListener('change', function () {
        // 清除背景图片:
        console.log("file change :");
        //preview.style.backgroundImage = '';
        // 检查文件是否选择:
        if (!fileInput.value) {
            //info.innerHTML = '没有选择文件';
            return;
        }
        // 获取File引用:
        var file = fileInput.files[0];
        console.log(file);
        // 获取File信息:
        //info.innerHTML = '文件: ' + file.name + '<br>' +
        //    '大小: ' + file.size + '<br>' +
        //    '修改: ' + file.lastModifiedDate;
        if (file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
            alert('不是有效的图片文件!');
            return;
        }
        if (file.size > 1024 * 1024 * 2) {
            alert('图片超过2MB! ( ' + Math.floor(file.size * 100 / (1024 * 1024)) / 100 + "MB)");
            return;
        }
        //preview.text = (file.name)+'';
        //// 读取文件:
        var reader = new FileReader();
        reader.onload = function (e) {
            var
                data = e.target.result; // 'data:image/jpeg;base64,/9j/4AAQSk...(base64编码)...'
            preview.style.backgroundImage = 'url(' + data + ')';

        };
        //// 以DataURL的形式读取文件:
        reader.readAsDataURL(file);
    });
};


/**
 *  测试 活动 数据
 */
function init(id) {
    $.ajax({
        url: "http://www.jinzht.com:8080/jinzht/requestDetailAction.action",
        data: {
            key: "jinzht_server_security",
            partner: "f784463924c4b750acdb7873747fc745",
            contentId: id + "",
            version: "1",
            platform: "0",
            requestType: "webRequest"
        },
        contentType: "application/json; charset=utf-8",
        type: "get",
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            if (data.status == 200) {
                setHtmlJson(JSON.stringify(data.data));
            } else {

            }
        },
        error: function (xhr, textStatus, errMsg) {
            console.log("error:");
        }
    });
}



function addPersoinPhototLinener(id,imgid) {
    var fileInput = document.getElementById(id);//隐藏的file文本ID
    var preview = document.getElementById(imgid);
    //var imgTest = document.getElementsByClassName("img_text");
    //imgTest.addEventListener('click',function () {
    //    console.log("click");
    //    fileInput.click();//加一个触发事件
    //});
    fileInput.addEventListener('change', function () {
        // 清除背景图片:
        console.log("file change :");
        //preview.style.backgroundImage = '';
        // 检查文件是否选择:
        if (!fileInput.value) {
            //info.innerHTML = '没有选择文件';
            return;
        }
        // 获取File引用:
        var file = fileInput.files[0];
        console.log(file);
        // 获取File信息:
        //info.innerHTML = '文件: ' + file.name + '<br>' +
        //    '大小: ' + file.size + '<br>' +
        //    '修改: ' + file.lastModifiedDate;
        if (file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
            alert('不是有效的图片文件!');
            return;
        }
        // 读取文件:
        var reader = new FileReader();
        reader.onload = function (e) {
            var
                data = e.target.result; // 'data:image/jpeg;base64,/9j/4AAQSk...(base64编码)...'
            preview.style.backgroundImage = 'url(' + data + ')';

        };
        // 以DataURL的形式读取文件:
        reader.readAsDataURL(file);
    });
};


