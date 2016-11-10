/**
 * Created by LiLe on 16/10/27.
 */

// $('button').click(function (){
//     $(this).css("background","#5b7396");
//     $(this).css("color","#ffffff");
// });

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


function mineInfo(){
    $("#mine-info").css('color','#5b7396');
    $("#mine-info").css('background','url(./img/mine-info-highlight.png) no-repeat left');
    $("#project-center").css('color','#aaaaaa');
    $("#project-center").css('background','url(./img/project-center.png) no-repeat left');
    $("#account-setting").css('color','#aaaaaa');
    $("#account-setting").css('background','url(./img/account-setting.png) no-repeat left');
    $('#mine-info-content').css('display','block');
    $('#project-center-content').css('display','none');
    $('#account-setting-content').css('display','none');
}

function projectCenter(){
    $("#mine-info").css('color','#aaaaaa');
    $("#mine-info").css('background','url(./img/mine-info.png) no-repeat left');
    $("#project-center").css('color','#5b7396');
    $("#project-center").css('background','url(./img/project-center-highlight.png) no-repeat left');
    $("#account-setting").css('color','#aaaaaa');
    $("#account-setting").css('background','url(./img/account-setting.png) no-repeat left');
    $('#mine-info-content').css('display','none');
    $('#project-center-content').css('display','block');
    $('#account-setting-content').css('display','none');
}

function accountSetting(){
    $("#mine-info").css('color','#aaaaaa');
    $("#mine-info").css('background','url(./img/mine-info.png) no-repeat left');
    $("#project-center").css('color','#aaaaaa');
    $("#project-center").css('background','url(./img/project-center.png) no-repeat left');
    $("#account-setting").css('color','#5b7396');
    $("#account-setting").css('background','url(./img/account-setting-highlight.png) no-repeat left');
    $('#mine-info-content').css('display','none');
    $('#project-center-content').css('display','none');
    $('#account-setting-content').css('display','block');
}

// 切换到基本资料
function basicInformation(){
    $("#basic-information").css('display','block');
    $("#authentication-information").css('display','none');
    // $("#basic-information-tab").css('border-bottom','4px solid #355078','color','#355078');
    // $("#authentication-information-tab").css('border-bottom','none','color','#aaaaaa');
    $("#basic-information-tab").css('border-bottom','4px solid #355078');
    $("#authentication-information-tab").css('border-bottom','none');
    $("#basic-information-tab").css('color','#355078');
    $("#authentication-information-tab").css('color','#aaaaaa');
}

// 切换到认证资料
function authenticationInformation(){
    $("#basic-information").css('display','none');
    $("#authentication-information").css('display','block');
    // $("#basic-information-tab").css('border-bottom','none', 'color','#aaaaaa');
    // $("#authentication-information-tab").css('border-bottom','4px solid #355078','color','#355078');
    $("#basic-information-tab").css('border-bottom','none');
    $("#authentication-information-tab").css('border-bottom','4px solid #355078');
    $("#basic-information-tab").css('color','#aaaaaa');
    $("#authentication-information-tab").css('color','#355078');
}

// 切换到基本资料
function changePwd(){
    $("#change-pwd-content").css('display','block');
    $("#change-phone-content").css('display','none');
    $("#change-pwd").css('border-bottom','4px solid #355078');
    $("#change-phone").css('border-bottom','none');
    $("#change-pwd").css('color','#355078');
    $("#change-phone").css('color','#aaaaaa');
}

// 切换到认证资料
function changePhone(){
    $("#change-pwd-content").css('display','none');
    $("#change-phone-content").css('display','block');
    $("#change-pwd").css('border-bottom','none');
    $("#change-phone").css('border-bottom','4px solid #355078');
    $("#change-pwd").css('color','#aaaaaa');
    $("#change-phone").css('color','#355078');
}
