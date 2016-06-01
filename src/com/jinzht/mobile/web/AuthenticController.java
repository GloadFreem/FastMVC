package com.jinzht.mobile.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.type.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzht.tools.Config;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.test.User;

@Controller
public class AuthenticController extends BaseController {

	@Autowired
	private AuthenticManager authenticManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping("/updateIdentiyTypeUser")
	@ResponseBody
	/***
	 * 忘记密码
	 * @param userInstance
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map updateIdentiyTypeUser(
			@RequestParam(value = "ideniyType") short ideniyType,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		if (ideniyType == 0) {
			this.status = 400;
			this.message = Config.STRING_AUTH_IDENTIY_TYPE_NOT_NULL;

			return getResult();
		}

		// 获取用户
		Users user = this.findUserInSession(session);

		if (user != null) {
			// 获取身份类型
			Identiytype identityType = this.authenticManager
					.findIdentityTypeById(ideniyType);
			// 生成认证记录
			Authentic authentic = new Authentic();
			authentic.setUsers(user);
			authentic.setIdentiytype(identityType);

			// 更新用户登录信息
			Set authenticSet = new HashSet();
			authenticSet.add(authentic);

			user.setAuthentics(authenticSet);

			// 更新用户登录信息
			this.userManager.saveOrUpdateUser(user);

			// 封装返回数据
			this.status = 200;
			this.message = Config.STRING_AUTH_IDENTIY_SUCCESS;
		} else {
			this.status = 400;
			this.message = Config.STRING_AUTH_IDENTIY_FAIL;
		}

		return getResult();
	}

	@RequestMapping("/getProtocolAuthentic")
	@ResponseBody
	/***
	 * 忘记密码
	 * @param userInstance
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map getProtocolAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.result.put("data",  Config.STRING_AUTH_QUALIFICATION);

		return getResult();
	}
	@RequestMapping("/getIndustoryTypeAuthentic")
	@ResponseBody
	/***
	 * 获取行业列表
	 * @param session
	 * @return
	 */
	public Map getIndustoryTypeAuthentic(HttpSession session) {
		this.result = new HashMap();
		
		List list = this.authenticManager.findAllIndustoryType();
		this.result.put("data",  list);
		
		return getResult();
	}
	@RequestMapping("/getProvinceListAuthentic")
	@ResponseBody
	/***
	 * 获取省份列表
	 * @param session
	 * @return
	 */
	public Map getProvinceListAuthentic(HttpSession session) {
		this.result = new HashMap();
		
		List list = this.authenticManager.findAllProvinceList();
		this.result.put("data",  list);
		
		return getResult();
	}
	@RequestMapping("/getCityListAuthentic")
	@ResponseBody
	/***
	 * 获取省份列表
	 * @param session
	 * @return
	 */
	public Map getCityListAuthentic(HttpSession session) {
		this.result = new HashMap();
		
		List list = this.authenticManager.findAllCityList();
		this.result.put("data",  list);
		
		return getResult();
	}


	/***
	 * 从当前session获取用户对象
	 * 
	 * @param session
	 * @return
	 */
	private Users findUserInSession(HttpSession session) {
		Users user = null;
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			user = this.userManager.findUserById(userId);
		}
		return user;
	}

}
