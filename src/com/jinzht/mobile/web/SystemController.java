package com.jinzht.mobile.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import com.jinzht.tools.Tools;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Notice;
import com.jinzht.web.entity.Preloadingpage;
import com.jinzht.web.entity.Customservice;
import com.jinzht.web.entity.Systemmessage;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Versioncontroll;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.test.User;

@Controller
public class SystemController extends BaseController {

	@Autowired
	private SystemManager systemManger;
	@Autowired
	private UserManager userManager;

	@RequestMapping("/announcementSystem")
	@ResponseBody
	/***
	 * 获取系统公告
	 * @param platform 客户端设备类型 0:Android,1:iOS
	 * @param mm
	 * @return Map 返回值
	 */
	public Map announcementSystem(
			@RequestParam(value = "platform", required = false) short platform,
			ModelMap mm) {
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
	public Map startPageSystem(
			@RequestParam(value = "platform", required = false) short platform,
			ModelMap mm) {
		this.result = new HashMap();
		Preloadingpage pageItem = this.systemManger
				.findPreloadingPageByPlatform(platform);
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
	public Map versionInfoSystem(
			@RequestParam(value = "platform", required = false) short platform,
			ModelMap mm) {
		this.result = new HashMap();
		Versioncontroll versionInfo = this.systemManger
				.findVersionInfoByPlatform(platform);
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
	public Map customServiceSystem(ModelMap mm) {
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
	public Map bannerSystem(ModelMap mm) {
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
	public Map shareSystem(ModelMap mm) {
		this.result = new HashMap();
		List list = this.systemManger.findBannerInfoList();
		this.status = 200;
		this.result.put("data", list);
		this.message = "";
		return getResult();
	}

	@RequestMapping("/requestInnerMessageList")
	@ResponseBody
	/***
	 * 站内信列表
	 * @param mm
	 * @return Map 返回值
	 */
	public Map requestInnerMessageList(
			@RequestParam(value = "page", required = false) Integer page,
			HttpSession session) {
		this.result = new HashMap();
		// 获取用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {
			List list = this.systemManger.findSystemMessageListByUser(user,
					page);
			this.status = 200;
			this.result.put("data", list);
			this.message = "";
		}

		return getResult();
	}

	@RequestMapping("/requestInnermessageDetail")
	@ResponseBody
	/***
	 * 站内信详情
	 * @param messageId 站内信id
	 * @return Map 返回值
	 */
	public Map requestInnermessageDetail(
			@RequestParam(value = "messageId", required = false) Integer messageId,
			HttpSession session) {
		this.result = new HashMap();

		// 获取站内信
		Systemmessage message = this.systemManger.findMessageById(messageId);
		this.status = 200;
		this.result.put("data", message);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestDeleteInnerMessage")
	@ResponseBody
	/***
	 * 删除站内信
	 * @param messageId
	 * @return Map 返回值
	 */
	public Map requestDeleteInnerMessage(
			@RequestParam(value = "messageId", required = false) Integer messageId,
			HttpSession session) {
		this.result = new HashMap();

		// 获取站内信
		Systemmessage message = this.systemManger.findMessageById(messageId);
		if (message != null) {
			// 删除
			this.systemManger.deleteSystemMessage(message);
			this.status = 200;
			this.result.put("data", "");
			this.message = Config.STRING_SYSTEM_MESSAGE_DELETE_SUCCESS;
		} else {
			// 删除
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_SYSTEM_MESSAGE_DELETE_FAIL;
		}

		return getResult();
	}

	@RequestMapping("/requestHasReadMessage")
	@ResponseBody
	/***
	 * 将信息标为已读状态
	 * @param messageId
	 * @return Map 返回值
	 */
	public Map requestHasReadMessage(
			@RequestParam(value = "messageId", required = false) Integer messageId,
			HttpSession session) {
		this.result = new HashMap();

		// 获取站内信
		Systemmessage message = this.systemManger.findMessageById(messageId);
		if (message != null) {
			// 更改状态
			short flag = 1;
			message.setRead(flag);

			this.systemManger.saveOrUpdate(message);
			this.status = 200;
			this.result.put("data", "");
			this.message = Config.STRING_SYSTEM_MESSAGE_READ_SUCCESS;
		} else {
			// 删除
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_SYSTEM_MESSAGE_READ_FAIL;
		}

		return getResult();
	}

	@RequestMapping("/requestPlatformIntroduce")
	@ResponseBody
	/***
	 * 平台介绍
	 * @return Map 返回值
	 */
	public Map requestPlatformIntroduce(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url", Config.STRING_SYSTEM_INTRODUCE);

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestNewUseIntroduce")
	@ResponseBody
	/***
	 * 新手指南
	 * @return Map 返回值
	 */
	public Map requestNewUseIntroduce(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url", Config.STRING_SYSTEM_INTRODUCE);

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestUserProctol")
	@ResponseBody
	/***
	 * 用户协议
	 * @return Map 返回值
	 */
	public Map requestUserProctol(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url", Config.STRING_SYSTEM_INTRODUCE);

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestLawerIntroduce")
	@ResponseBody
	/***
	 * 免责声明
	 * @return Map 返回值
	 */
	public Map requestLawerIntroduce(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url", Config.STRING_SYSTEM_INTRODUCE);

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestInviteCode")
	@ResponseBody
	/***
	 * 邀请码获取
	 * @return Map 返回值
	 */
	public Map requestInviteCode(HttpSession session) {
		this.result = new HashMap();
		// 获取用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {
			Map map = new HashMap();
			map.put("inviteCode", user.getSystemcodes());

			this.status = 200;
			this.result.put("data", map);
			this.message = "";
		}

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
