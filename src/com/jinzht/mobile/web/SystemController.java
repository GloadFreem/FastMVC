package com.jinzht.mobile.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzht.tools.Config;
import com.jinzht.tools.Tools;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Collection;
import com.jinzht.web.entity.Contentimages;
import com.jinzht.web.entity.Notice;
import com.jinzht.web.entity.Preloadingpage;
import com.jinzht.web.entity.Customservice;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Roadshowplan;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Systemcode;
import com.jinzht.web.entity.Systemmessage;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Versioncontroll;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.FeelingManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.manager.ProjectManager;
import com.jinzht.web.test.User;

@Controller
public class SystemController extends BaseController {

	@Autowired
	private SystemManager systemManger;
	@Autowired
	private UserManager userManager;
	@Autowired
	private ProjectManager ProjectManager;
	@Autowired
	private FeelingManager feelingManager;

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
		if (list != null && list.size() > 0) {
			this.result.put("data", list);
		} else {
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
			if (list != null && list.size() > 0) {
				this.status = 200;
				this.result.put("data", list);
			} else {
				this.status = 200;
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
		if (objs != null && objs.length > 0) {
			for (Object obj : objs) {
				// 获取站内信
				obj = obj.toString().trim();
				Integer msgId = Integer.parseInt(obj.toString());
				if (msgId != null) {
					Systemmessage message = this.systemManger
							.findMessageById(msgId);

					if (message != null) {
						// 删除
						this.systemManger.deleteSystemMessage(message);
						this.status = 200;
						this.result.put("data", "");
						this.message = Config.STRING_SYSTEM_MESSAGE_DELETE_SUCCESS;
					}
				} else {
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
		map.put("url",
				Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_ABOUNT_US));

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestGoldInviteFriends")
	@ResponseBody
	/***
	 * 邀请送金条
	 * @return Map 返回值
	 */
	public Map requestGoldInviteFriends(HttpSession session) {
		this.result = new HashMap();

		Integer userId = 0;
		if (session.getAttribute("userId") != null) {
			userId = (Integer) session.getAttribute("userId");
		}
		String invitcode = Tools.generateInviteCode(userId, false);

		// 分享内容
		int count = Config.STRING_SHARE_GOLD.size();
		int radomIndex = (int) (0 + Math.random() * (count));
		String content = Config.STRING_SHARE_GOLD.get(radomIndex).toString();

		// 图片
		count = Config.STRING_SHARE_GOLD_IMAGES.size();
		radomIndex = (int) (0 + Math.random() * (count));
		String image = Config.STRING_SHARE_GOLD_IMAGES.get(radomIndex)
				.toString();
		image = Config.STRING_SYSTEM_ADDRESS + "images/share/" + image;
		Map map = new HashMap();
		map.put("title", "邀请好友送金条--【金指投投融资】");
		map.put("content", content);
		map.put("url", Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_GOLD)
				+ "?inviteCode=" + invitcode);
		map.put("image", image);
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
		map.put("url", Tools.generateWebUrl(Config.STRING_SYSTEM_INTRODUCE));

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
		map.put("url",
				Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_USER_PROCTOL)
						+ "?contentId=3");

		this.status = 200;
		this.result.put("data", map);
		this.message = "";

		return getResult();
	}

	@RequestMapping("/requestFeedBack")
	@ResponseBody
	/***
	 * 意见反馈
	 * 
	 * @return Map 返回值
	 */
	public Map requestFeedBack(
			@RequestParam(value = "content", required = false) String content,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		if (session.getAttribute("userId") != null) {
			String userId = session.getAttribute("userId").toString();
			if (userId != null) {
				this.status = 200;
				this.systemManger
						.addFeedback(Integer.parseInt(userId), content);
				this.message = "反馈成功！";
			} else {
				this.status = 400;
				this.message = Config.STRING_LOGING_TIP;
			}
		} else {
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
		map.put("url",
				Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_USER_PROCTOL)
						+ "?contentId=4");

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
		map.put("url",
				Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_USER_PROCTOL)
						+ "?contentId=5");

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

		int count = Config.STRING_SHARE_INVITE.size();

		int radomIndex = (int) (0 + Math.random() * (count));

		String content = Config.STRING_SHARE_INVITE.get(radomIndex).toString();

		Map map = new HashMap();
		map.put("url", Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_CODE)
				+ "?inviteCode=" + invitcode);
		map.put("image", Config.STRING_SYSTEM_INTRODUCE_IMAGE);
		map.put("title", "邀请好友--" + Config.STRING_APPP_SHARE_TITLE);
		map.put("content", content);
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
		map.put("url",
				Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_USER_PROCTOL)
						+ "?contentId=7");

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

			// Object[] objs = user.getSystemcodes().toArray();
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

			// 获取未读
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

	// 新手指南
	@RequestMapping(value = "/UserGuide")
	public String UserGuide(ModelMap model) {
		return "user_guide";
	}

	// 分享项目展示
	@RequestMapping(value = "/ShareProjectDetail")
	public String ShareProjectDetail(
			@RequestParam(value = "projectId", required = false) Integer projectId,
			ModelMap model) {
		// 获取项目
		Project project = this.ProjectManager.findProjectById(projectId);

		List list = new ArrayList();

		// 商业计划书
		Object[] objs = project.getBusinessplans().toArray();
		if (objs != null && objs.length > 0) {
			list.add(objs[0]);
		}

		// 风险报告
		objs = project.getControlreports().toArray();
		if (objs != null && objs.length > 0) {
			list.add(objs[0]);
		}
		// 融资计划
		objs = project.getFinancialstandings().toArray();
		if (objs != null && objs.length > 0) {
			list.add(objs[0]);
		}
		// 融资案例
		objs = project.getFinancingcases().toArray();
		if (objs != null && objs.length > 0) {
			list.add(objs[0]);
		}
		// 退出渠道
		objs = project.getFinancingexits().toArray();
		if (objs != null && objs.length > 0) {
			list.add(objs[0]);
		}

		Set set = project.getRoadshows();

		Roadshow roadShow = new Roadshow();
		if (set != null && set.size() > 0) {
			roadShow = (Roadshow) (set.toArray()[0]);
		}

		Roadshowplan plan = roadShow.getRoadshowplan();
		if (plan == null) {
			plan = new Roadshowplan();
		}
		Object[] images = project.getProjectimageses().toArray();

		Map map = new HashMap();
		map.put("project", project);

		project.setBusinessplans(null);
		project.setControlreports(null);
		project.setFinancialstandings(null);
		project.setFinancingcases(null);
		project.setFinancingexits(null);

		model.put("data", map);

		model.put("images", images);
		model.put("roadshow", roadShow);
		model.put("members", project.getMembers().toArray());
		model.put("plan", plan);
		model.put("extr", list);
		return "ShareProjectDetail";
	}

	// 关于我们
	@RequestMapping(value = "/abountUs")
	public String abountUs(ModelMap model) {
		return "abount_me";
	}

	// 邀请码邀请好友
	@RequestMapping(value = "/shareInvite")
	public String shareInvite(
			@RequestParam(value = "inviteCode", required = false) String inviteCode,
			ModelMap model) {
		model.put("inviteCode", inviteCode);
		return "invite_card";
	}

	// 邀请码送金条
	@RequestMapping(value = "/shareInviteGold")
	public String shareInviteGold(
			@RequestParam(value = "inviteCode", required = false) String inviteCode,
			ModelMap model) {

		model.put("inviteCode", inviteCode);
		return "invite_gold";
	}

	// 分享圈子内容
	@RequestMapping(value = "/shareFeeling")
	public String shareFeeling(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			ModelMap model) {
		Publiccontent content = this.feelingManager.getPublicContentDao()
				.findById(contentId);

		Users user = content.getUsers();
		Object[] authentic = user.getAuthentics().toArray();

		model.put("data", content);
		model.put("user", user);
		model.put("authentic", authentic[0]);
		model.put("images", content.getContentimageses());
		model.put("comments", content.getComments());

		return "ShareFeelingDetail";
	}
	
	// 分享圈子内容
	@RequestMapping(value = "/requestConsultList")
	@ResponseBody
	public Map requestConsultList(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "version", required = false) Integer version,
			ModelMap model) {
		this.result = new HashMap();
		List list = this.systemManger.getWebUrlRecordDao().findAll();
		
		
		
		this.status=200;
		this.result.put("data", list);
		return getResult();
	}

	// 用户协议
	@RequestMapping(value = "/UserProctol")
	public String UserProctol(ModelMap model) {
		return "UserProctol";
	}

	// 投资人详情
	@RequestMapping(value = "/H5UserInfo")
	public String H5UserInfo(ModelMap model) {
		return "UserInfo";
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
