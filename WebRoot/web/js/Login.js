/**
 * Created by LiLe on 16/10/29.
 */

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
});

function register() {
    window.location.href = "./regist.action";
}