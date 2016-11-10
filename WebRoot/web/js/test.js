/**
 * Created by LiLe on 16/9/18.
 */
/****************************加载网络数据*********************************/

$(document).ready(function () {

    $.ajax({
        type: "POST",
        url: "http://www.jinzht.com:8080/jinzht/requestProjectDetail.action",
        data: {
            "key": "jinzht_server_security",
            "partner": "804BC337815F918AA296883FE0DFC36F",
            "projectId": "94",
            "requestType":"webRequest"
        },
        async: true,
        dataType:"json",
        success: function(returnedData) {
            alert(returnedData);
            var project = returnedData.data.project;
            alert(project);

        },
        error: function(e) {
            alert(e);
            //请求失败时调用此函数
        }
    });
});