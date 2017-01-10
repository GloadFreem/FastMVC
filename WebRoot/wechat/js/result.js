/**
 * Created by Administrator on 2016/12/30.
 */

$(document).ready(
		function() {

			// 微信判断
			if (is_weixn()) {
				var bean =  payBean = JSON.parse(localStorage.getItem("JinzhtBean"));
					$("#telNum").text(""+bean.telephone);
			} else {
				window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.tencent.mm#opened";
				window.close();
			}

		}
);
