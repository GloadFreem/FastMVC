package com.jinzht.mobile.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzht.tools.Config;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.test.User;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserManager userManger;
	@RequestMapping(value="createUser")
	public String createUser(@ModelAttribute("user") Users user){
		String path = System.getProperty("jinzht.root");
		System.out.println(path);
		List<Users> users = userManger.findAllUsers();
		for (int i = 0; i < users.size(); i++) {
			Users u = users.get(i);
			System.out.println(u.getTelephone());
		}
		return "index";
	}
	
	@RequestMapping("/valid")
	public String handleValid1(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			System.out.println("失败!"+bindingResult.getErrorCount()+bindingResult.getFieldError().getDefaultMessage());
		}else{
			System.out.println("成功!");
		}
		return "index";
	}
	
	@RequestMapping("/jsonResult")
	@ResponseBody
	public Map jsonResult(ModelMap mm)
	{
		this.result = new HashMap();
		Users userItem = new Users();
		userItem.setTelephone("198545545");
		userItem.setPassword("jhsdhfjgh");
		
		this.status = 200;
		this.result.put("data", userItem);
		this.message = Config.STRING_LOGING_SUCCESS;
		return getResult();
	}
	
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
	

}
