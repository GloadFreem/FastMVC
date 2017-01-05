package com.jinzht.mobile.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微信支付
 * @author EasonYang
 *
 */
@Controller
public class WebchatPayController extends BaseController {
		

	/***
	 * 课程详情
	 * @return
	 */
	@RequestMapping(value = "/wechat/detail.action")
	public String addressUs() {
		return "/wechat/detail";
	}
	
	/***
	 * 课程详情
	 * @return
	 */
	@RequestMapping(value = "/web/detail2.action")
	public String addressUs2() {
		return "/web/html/content/main";
	}
	
	/***
	 * 用户验证
	 * @return
	 */
	@RequestMapping(value = "/wechat/input.action")
	public String service() {
		return "/wechat/input";
	}
	
	/***
	 * 提交支付
	 * @return
	 */
	@RequestMapping(value = "/wechat/pay.action")
	public String policy() {
		return "/wechat/zhifu";
	}
	
	
	/***
	 * 支付完成
	 * @return
	 */
	@RequestMapping(value = "/wechat/result.action")
	public String pay() {
		return "/wechat/result";
	}

}
