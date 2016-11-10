
var urlStr = ["url(img/bg_header_top.png)","url(img/bg_message.png)","url(img/bg_sport.png)"];
var index = 0;

/**
 * 加载
 */
$(document).ready(function () {

    $(window).scroll(function(){
        //console.log($(document).height());
        if ($(window).scrollTop()>200){
            $("#id_to_top").show();
        } else {
            $("#id_to_top").hide();
        }
    });

    $("#marker").addClass("check_abount");
    $("#id_sport").click(function() {
        window.location.href ="http://www.jinzht.com/app/action";
    });
    $("#id_investor").click(function() {
        window.location.href ="http://www.jinzht.com/app/investor";
    });
    $("#id_abount").click(function() {
        window.location.href ="http://www.jinzht.com/app/abount";
    });
    $("#id_msg").click(function() {
        window.location.href ="http://www.jinzht.com/app/info";
    });
    $("#id_project").click(function() {
        window.location.href ="http://www.jinzht.com/app/projectForWeb";
    });
    $("#id_main").click(function() {
        window.location.href ="http://www.jinzht.com/app/software";
    });

});


/**
 * 活动数据初始化
 * @param pageIndex  页码
 */
function  init(pageIndex){
    var contentHtml="";
    for (var i = 0; i <15; i++) {
        contentHtml = contentHtml + '';
        contentHtml = contentHtml + '   <a class="sport_card" href="#">';
        contentHtml = contentHtml + '     <img src="img/test'+(pageIndex%5+1)+'.png" class="card_img">';
        contentHtml = contentHtml + '      <div class="sport_b">';
        contentHtml = contentHtml + '      <div class="s_title">2016全球技术看领导峰会</div>';
        contentHtml = contentHtml + '   <div class="s_content">500+CTO聚集，重新定义技术领导力，GTLC 2016全球技术领导峰会</div>';
        contentHtml = contentHtml + '   <div class="line_dian_bottom"></div>';
        contentHtml = contentHtml + '       <div class="s_bottom">';
        contentHtml = contentHtml + '      <div class="left">';
        contentHtml = contentHtml + '     <div class="text_t">上海</div>';
        contentHtml = contentHtml + '      <div class="text_b">8月23日</div>';
        contentHtml = contentHtml + '    </div>';
        contentHtml = contentHtml + '   <div class="right">';
        contentHtml = contentHtml + '        <div>互联网</div>';
        contentHtml = contentHtml + '      <div>服务业</div>';
        contentHtml = contentHtml + '       <div>技术</div>';
        contentHtml = contentHtml + '       <div>制造业</div>';
        contentHtml = contentHtml + '     <div>金融</div>';
        contentHtml = contentHtml + '      </div>';
        contentHtml = contentHtml + '       </div>';
        contentHtml = contentHtml + '     </div>';
        contentHtml = contentHtml + '     </a>';
    }
    $("#id_sport_cards").html(contentHtml);
}

/**
 *上一张背景
 */
function m_back(){
    $("#id_m_bg_top").css("background-image",urlStr[index%3]);
    index--;
}
/**
 * 下一张背景
 */
function m_next(){
    $("#id_m_bg_top").css("background-image",urlStr[index%3]);
    index++;
}

