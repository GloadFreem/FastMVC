/**
 * Created by Administrator on 2017/1/3.
 */
function is_weixn(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
        return true;
    } else {
        return false;
    }
}

function  back(){
    history.go(-1);
}