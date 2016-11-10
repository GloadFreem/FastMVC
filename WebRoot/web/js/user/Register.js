/**
 * Created by LiLe on 16/10/25.
 */

function next() {
    document.getElementById("phone-num-reg").src = "./img/choosed.png";
    document.getElementById("set-pwd").src = "./img/choosing.png";
    document.getElementById("step1").style.display = "none";
    document.getElementById("step2").style.display = "inline-block";
}

function chooseId() {
    $('#set-pwd').attr('src', './img/choosed.png');
    $('#choose-idtentity').attr('src', './img/choosing.png');
    // $('#step2').attr('display','none');
    // $('#step3').attr('display', 'inline-block');
    document.getElementById("step2").style.display = "none";
    document.getElementById("step3").style.display = "inline-block";
    document.getElementsByClassName("submit")[0].style.display = "inline-block";
}

function submit() {
    $('#choose-idtentity').attr('src', './img/choosed.png');
    window.location.href = "./personalCenter.action";
}

// 切换卡片
$('#id1').hover(function () {
    var ps = $('#id1').find('p');
    for (var i = 0; i < ps.length; i++) {
        var p = ps[i];
        p.style.color = "white";
    }
    $('#id1').find('img').attr('src', './img/white-circle.png');
}, function () {
    var ps = $('#id1').find('p');
    for (var i = 0; i < ps.length; i++) {
        var p = ps[i];
        p.style.color = "#3b5d8f";
    }
    $('#id1').find('img').attr('src', './img/blue-circle.png')
});

$('#id2').hover(function () {
    var ps = $('#id2').find('p');
    for (var i = 0; i < ps.length; i++) {
        var p = ps[i];
        p.style.color = "white";
    }
    $('#id2').find('img').attr('src', './img/white-circle.png');
}, function () {
    var ps = $('#id2').find('p');
    for (var i = 0; i < ps.length; i++) {
        var p = ps[i];
        p.style.color = "#3b5d8f";
    }
    $('#id2').find('img').attr('src', './img/blue-circle.png')
});

$('#id3').hover(function () {
    var ps = $('#id3').find('p');
    for (var i = 0; i < ps.length; i++) {
        var p = ps[i];
        p.style.color = "white";
    }
    $('#id3').find('img').attr('src', './/img/white-circle.png');
}, function () {
    var ps = $('#id3').find('p');
    for (var i = 0; i < ps.length; i++) {
        var p = ps[i];
        p.style.color = "#3b5d8f";
    }
    $('#id3').find('img').attr('src', './/img/blue-circle.png')
});

$('#id4').hover(function () {
    var ps = $('#id4').find('p');
    for (var i = 0; i < ps.length; i++) {
        var p = ps[i];
        p.style.color = "white";
    }
    $('#id4').find('img').attr('src', './/img/white-circle.png');
}, function () {
    var ps = $('#id4').find('p');
    for (var i = 0; i < ps.length; i++) {
        var p = ps[i];
        p.style.color = "#3b5d8f";
    }
    $('#id4').find('img').attr('src', './/img/blue-circle.png')
});