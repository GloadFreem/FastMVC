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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzht.tools.Config;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Notice;
import com.jinzht.web.entity.Preloadingpage;
import com.jinzht.web.entity.Customservice;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Versioncontroll;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.test.User;

@Controller
public class SystemController extends BaseController{

	@Autowired
	private SystemManager systemManger;
	
	
	@RequestMapping("/announcementSystem")
	@ResponseBody
	/***
	 * 获取系统公告
	 * @param platform 客户端设备类型 0:Android,1:iOS
	 * @param mm
	 * @return Map 返回值
	 */
	public Map announcementSystem(@RequestParam(value="platform",required = false) short platform,ModelMap mm)
	{
		this.result = new HashMap();
		Notice notice = this.systemManger.findNoticeByPlatform(platform);
		this.status = 200;
		this.result.put("data", notice);
		this.message = "";
		return getResult();
	}
	
	
	@RequestMapping("/startPageSystem")
	@ResponseBody
	/***
	 * 启动页信息
	 * @param platform 客户端设备类型 0:Android,1:iOS
	 * @param mm
	 * @return Map 返回值
	 */
	public Map startPageSystem(@RequestParam(value="platform",required = false) short platform,ModelMap mm)
	{
		this.result = new HashMap();
		Preloadingpage pageItem = this.systemManger.findPreloadingPageByPlatform(platform);
		this.status = 200;
		this.result.put("data", pageItem);
		this.message = "";
		return getResult();
	}
	
	@RequestMapping("/versionInfoSystem")
	@ResponseBody
	/***
	 * 版本信息
	 * @param platform 客户端设备类型 0:Android,1:iOS
	 * @param mm
	 * @return Map 返回值
	 */
	public Map versionInfoSystem(@RequestParam(value="platform",required = false) short platform,ModelMap mm)
	{
		this.result = new HashMap();
		Versioncontroll versionInfo = this.systemManger.findVersionInfoByPlatform(platform);
		this.status = 200;
		this.result.put("data", versionInfo);
		this.message = "";
		return getResult();
	}
	
	@RequestMapping("/customServiceSystem")
	@ResponseBody
	/***
	 * 客服信息
	 * @param mm
	 * @return Map 返回值
	 */
	public Map customServiceSystem(ModelMap mm)
	{
		this.result = new HashMap();
		Customservice customservice = this.systemManger.findCustomServices();
		this.status = 200;
		this.result.put("data", customservice);
		this.message = "";
		return getResult();
	}
	
	@RequestMapping("/bannerSystem")
	@ResponseBody
	/***
	 * 获取Banner信息
	 * @param mm
	 * @return Map 返回值
	 */
	public Map bannerSystem(ModelMap mm)
	{
		this.result = new HashMap();
		List list = this.systemManger.findBannerInfoList();
		this.status = 200;
		this.result.put("data", list);
		this.message = "";
		return getResult();
	}
	@RequestMapping("/shareSystem")
	@ResponseBody
	/***
	 * 信息分享系统
	 * @param mm
	 * @return Map 返回值
	 */
	public Map shareSystem(ModelMap mm)
	{
		this.result = new HashMap();
		List list = this.systemManger.findBannerInfoList();
		this.status = 200;
		this.result.put("data", list);
		this.message = "";
		return getResult();
	}
}
