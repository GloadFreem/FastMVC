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
import com.jinzht.web.entity.Systemcode;
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
		if(list!=null && list.size()>0)
		{
			this.result.put("data", list);
		}else{
			this.result.put("data", new ArrayList());
		}
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
			if(list!=null && list.size()>0)
			{
				this.status = 200;
				this.result.put("data", list);
			}else{
				this.status = 201;
				this.result.put("data", new ArrayList());
			}
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
			@RequestParam(value = "messageId", required = false) String messageId,
			HttpSession session) {
		this.result = new HashMap();

		Object[] objs = messageId.split(",");
		if(objs!=null && objs.length>0)
		{
			for(Object obj :objs)
			{
				// 获取站内信
				obj = obj.toString().trim();
				Integer msgId = Integer.parseInt(obj.toString());
				if(msgId!=null)
				{
					Systemmessage message = this.systemManger.findMessageById(msgId);
					
					if (message != null) {
						// 删除
						this.systemManger.deleteSystemMessage(message);
						this.status = 200;
						this.result.put("data", "");
						this.message = Config.STRING_SYSTEM_MESSAGE_DELETE_SUCCESS;
					} 
				}else{
					this.status = 400;
					this.result.put("data", "");
					this.message = Config.STRING_SYSTEM_MESSAGE_DELETE_FAIL;
				}
				
				
			}
			
			
			this.status = 200;
			this.result.put("data", "");
			this.message = Config.STRING_SYSTEM_MESSAGE_DELETE_SUCCESS;
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
			message.setIsRead(true);

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

	@RequestMapping("/requestUserProtocol")
	@ResponseBody
	/***
	 * 用户协议
	 * @return Map 返回值
	 */
	public Map requestUserProtocol(HttpSession session) {
		this.result = new HashMap();

		Map map = new HashMap();
		map.put("url", Config.STRING_SYSTEM_INTRODUCE);

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}
	@RequestMapping("/requestFeedBack")
	@ResponseBody
	/***
	 * 意见反馈
	 * @return Map 返回值
	 */
	public Map requestFeedBack(
			@RequestParam(value="content",required=false) String content,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		
		if(session.getAttribute("userId")!=null)
		{
			String userId = session.getAttribute("userId").toString();
			if(userId!=null)
			{
				this.status = 200;
				this.systemManger.addFeedback(Integer.parseInt(userId),content);
				this.message = "反馈成功！";
			}else{
				this.status = 400;
				this.message = Config.STRING_LOGING_TIP;
			}
		}else{
			this.status = 400;
			this.message = Config.STRING_LOGING_TIP;
		}
			
		
		
		
		
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
	@RequestMapping("/requestGoldGetRule")
	@ResponseBody
	/***
	 * 金条积累规则
	 * @return Map 返回值
	 */
	public Map requestGoldGetRule(HttpSession session) {
		this.result = new HashMap();
		
		Map map = new HashMap();
		map.put("url", Config.STRING_SYSTEM_INTRODUCE);
		
		this.status = 200;
		this.result.put("data", map);
		this.message = "";
		
		return getResult();
	}
	@RequestMapping("/requestInviteFriends")
	@ResponseBody
	/***
	 * 邀请好友
	 * @return Map 返回值
	 */
	public Map requestInviteFriends(HttpSession session) {
		this.result = new HashMap();
		
		Integer userId = 0;
		if (session.getAttribute("userId") != null) {
			userId = (Integer) session.getAttribute("userId");
		}
		String invitcode = Tools.generateInviteCode(userId, false);
		
		Map map = new HashMap();
		map.put("url", Config.STRING_SYSTEM_INTRODUCE+invitcode);
		map.put("image", Config.STRING_SYSTEM_INTRODUCE_IMAGE);
		map.put("title", Config.STRING_APPP_SHARE_TITLE);
		map.put("content", Config.STRING_APPP_SHARE_CONTENT);
		this.status = 200;
		this.result.put("data", map);
		this.message = "";
		
		return getResult();
	}
	@RequestMapping("/requestGoldUseRule")
	@ResponseBody
	/***
	 * 金条使用规则
	 * @return Map 返回值
	 */
	public Map requestGoldUseRule(HttpSession session) {
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
			
			//Object[] objs = user.getSystemcodes().toArray();
			String code = Tools.generateInviteCode(user.getUserId(), false);
			
			this.status = 200;
			this.result.put("data", code);
			this.message = "";
		}

		return getResult();
	}
	@RequestMapping("/requestuploadProjectInfo")
	@ResponseBody
	/***
	 * 上传项目信息获取
	 * @return Map 返回值
	 */
	public Map requestuploadProjectInfo(HttpSession session) {
		this.result = new HashMap();
		
		Map map = new HashMap();
		map.put("email", Config.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_EMAIL);
		map.put("tel", Config.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_TEL);
		
		this.status = 200;
		this.result.put("data", map);
		this.message = "";
		
		return getResult();
	}
	@RequestMapping("/requestHasMessageInfo")
	@ResponseBody
	/***
	 * 站内信提醒数据获取
	 * @return Map 返回值
	 */
	public Map requestHasMessageInfo(HttpSession session) {
		this.result = new HashMap();
		
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {

			//获取未读
			boolean flag = this.systemManger.findUserNotReadMessageFlag(user);
			
			Map map = new HashMap();
			map.put("flag", flag);
			
			this.status = 200;
			this.result.put("data", map);
			this.message = "";
		}
		return getResult();
	}
	@RequestMapping("/androidTest")
	public String androidTest(HttpSession session) {
		return "download";
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
