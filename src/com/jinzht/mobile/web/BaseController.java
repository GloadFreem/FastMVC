package com.jinzht.mobile.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzht.tools.Config;
import com.jinzht.web.entity.Users;

public class BaseController {
	protected Map result ;
	protected String message = "";
	protected int status = 401; // 状态码
	
	@RequestMapping("/noLogoInfo")
	@ResponseBody
	public Map noLogoInfo(ModelMap mm)
	{
		this.result = new HashMap();
		this.status = 401;
		this.message = Config.STRING_LOGING_TIP;
		this.result.put("data", "");
		return getResult();
	}
	
	public Map getResult() {
//		this.result = new HashMap();
		this.result.put("status", this.status);
		this.result.put("message", this.message);
		return result;
	}
	
	public void setResult(Map result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
