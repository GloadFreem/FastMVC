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
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.hibernate.type.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.jinzht.stock.manager.SanManager;
import com.jinzht.tools.Config;
import com.jinzht.tools.DateUtils;
import com.jinzht.tools.FileUtil;
import com.jinzht.tools.MD5;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.tools.PushUtil;
import com.jinzht.tools.Tools;
import com.jinzht.web.dao.ContenttypeDAO;
import com.jinzht.web.dao.FinancestatusDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Actionimages;
import com.jinzht.web.entity.Actionintroduce;
import com.jinzht.web.entity.Audiorecord;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Authenticstatus;
import com.jinzht.web.entity.Banner;
import com.jinzht.web.entity.BusinessContentType;
import com.jinzht.web.entity.BusinessContentTypeDAO;
import com.jinzht.web.entity.BusinessInvitationCode;
import com.jinzht.web.entity.BusinessJionRecord;
import com.jinzht.web.entity.BusinessSchool;
import com.jinzht.web.entity.BusinessVideo;
import com.jinzht.web.entity.BusinessVideoDAO;
import com.jinzht.web.entity.BusinessWeichat;
import com.jinzht.web.entity.Businessplan;
import com.jinzht.web.entity.BusniessJoin;
import com.jinzht.web.entity.BusniessTag;
import com.jinzht.web.entity.BusniessTagDAO;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Contentimages;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Financestatus;
import com.jinzht.web.entity.Financialstanding;
import com.jinzht.web.entity.Financingcase;
import com.jinzht.web.entity.Financingexit;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Member;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Messagetype;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Roadshowplan;
import com.jinzht.web.entity.Scene;
import com.jinzht.web.entity.Speechmarker;
import com.jinzht.web.entity.SpeechmarkerDAO;
import com.jinzht.web.entity.Systemmessage;
import com.jinzht.web.entity.Systemuser;
import com.jinzht.web.entity.Team;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Webcontenttype;
import com.jinzht.web.entity.Weburlrecord;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.ActionManager;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.CourseManager;
import com.jinzht.web.manager.FeelingManager;
import com.jinzht.web.manager.ImManager;
import com.jinzht.web.manager.InvestorManager;
import com.jinzht.web.manager.ProjectManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.manager.WebManager;
import com.jinzht.web.test.User;
import com.message.Enity.Chatroom;
import com.message.Enity.Msg;
import com.message.Enity.MsgDetail;
import com.message.Enity.MsgImages;
import com.message.Enity.Newsbanner;
import com.message.Enity.Original;
import com.message.Enity.OriginalDetail;
import com.message.Enity.OriginalImg;
import com.message.Enity.Originalbanner;
import com.message.manager.MainManager;
import com.message.manager.MessageMananger;

@Controller
public class UserWebAdminController extends BaseController {
	@Autowired
	private WebManager webManager;
	@Autowired
	private SystemManager systemManger;
	@Autowired
	private UserManager userManager;
	@Autowired
	private AuthenticManager authenticManager;
	@Autowired
	private CourseManager courseManager;
	@Autowired
	private ImManager imManager;
	@Autowired
	private ProjectManager projectManager;
	@Autowired
	private ActionManager actionManager;
	@Autowired
	private FeelingManager feelingManager;
	@Autowired
	private MainManager mainManager;
	@Autowired
	private MessageMananger messageManager;
	@Autowired
	private SystemManager systemManager;
	@Autowired
	private SanManager sanManager;

	/***
	 * ---------------------------------------------商学后端管理系统升级------------------
	 * -------------------------
	 ***/

	/***
	 * 用户列表
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/userList")
	public String userList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.userManager.getUserDao().findByPage(page, size);
		Integer count = this.userManager.getUserDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-user");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 认证审核
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/authenticList")
	public String authenticList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.authenticManager.getAuthenticDao().findByPage(page,
				size);
		Integer count = this.authenticManager.getAuthenticDao()
				.countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-authentic");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 项目
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/projectList")
	public String projectList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.projectManager.getProjectDao().findByPage(page, size);
		Integer count = this.projectManager.getProjectDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-project");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 路演管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/roadshowList")
	public String roadshowList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.projectManager.getRoadShowDao().findByPage(page, size);
		Integer count = this.projectManager.getRoadShowDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-roadshow");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 现场管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/sceneList")
	public String sceneList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.projectManager.getSceneDao().findByPage(page, size);
		Integer count = this.projectManager.getSceneDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-scene");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 财务状况管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/financeStandingList")
	public String financeStandingList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.projectManager.getFinanceStandingDao().findByPage(
				page, size);
		Integer count = this.projectManager.getFinanceStandingDao()
				.countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-finance-standing");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 商业计划管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/businessPlanList")
	public String businessPlanList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.projectManager.getBusinessPlanDao().findByPage(page,
				size);
		Integer count = this.projectManager.getBusinessPlanDao()
				.countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-business-plan");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 融资方案管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/financeCaseList")
	public String financeCaseList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.projectManager.getFinancingCaseDao().findByPage(page,
				size);
		Integer count = this.projectManager.getFinancingCaseDao()
				.countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-finance-case");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 融资方案管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/financeExitList")
	public String financeExitList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.projectManager.getFinancingexitDao().findByPage(page,
				size);
		Integer count = this.projectManager.getFinancingexitDao()
				.countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-finance-exit");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 成员管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/memberList")
	public String memberList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.projectManager.getMemberDao().findByPage(page, size);
		Integer count = this.projectManager.getMemberDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-member");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 团队管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/teamList")
	public String teamList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.projectManager.getTeamDao().findByPage(page, size);
		Integer count = this.projectManager.getTeamDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-team");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 活动管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/actionList")
	public String actionList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.actionManager.getActionDao().findByPage(page, size);
		Integer count = this.actionManager.getActionDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-action");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 团队管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/actionContentList")
	public String actionContentList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.actionManager.getActionIntroduceDao().findByPage(page,
				size);
		Integer count = this.actionManager.getActionIntroduceDao()
				.countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-action-content");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 圈子管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/feelingList")
	public String feelingList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.feelingManager.getPublicContentDao().findByPage(page,
				size);
		Integer count = this.feelingManager.getPublicContentDao()
				.countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-feeling");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 资讯管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/consultList")
	public String consultList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		List list = this.messageManager.getMsgDao().findByPage(page,
				size);
		Integer count = this.messageManager.getMsgDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-consult");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 原创管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/originalList")
	public String originalList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if (size == null) {
			size = 10;
		}
		
		if (page == null) {
			page = 0;
		}
		
		List list = this.mainManager.getOrigianlDao().findByPage(page,
				size);
		Integer count = this.mainManager.getOrigianlDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);
		
		map.put("result", list);
		
		map.put("content", "table-original");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 金日投条管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/kingConsultList")
	public String kingConsultList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if (size == null) {
			size = 10;
		}
		
		if (page == null) {
			page = 0;
		}
		
		List list = this.systemManager.getWebUrlRecordDao().findByPage(page,
				size);
		Integer count = this.systemManager.getWebUrlRecordDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);
		
		map.put("result", list);
		
		map.put("content", "table-king");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 聊天室管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/chatRoomsList")
	public String chatRoomsList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if (size == null) {
			size = 10;
		}
		
		if (page == null) {
			page = 0;
		}
		
		List list = this.imManager.getChatRoomDao().findByPage(page,
				size);
		Integer count = this.imManager.getChatRoomDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);
		
		map.put("result", list);
		
		map.put("content", "table-chatroom");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 新三板管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/newOTCList")
	public String newOTCList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if (size == null) {
			size = 10;
		}
		
		if (page == null) {
			page = 0;
		}
		
		List list = this.sanManager.getCompanyDAO().findByPage(page,
				size);
		Integer count = this.sanManager.getCompanyDAO().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);
		
		map.put("result", list);
		
		map.put("content", "table-otc");
		return Config.NEW_SERVER_CONTROL;
	}
	
	
	/***
	 * 金条管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/rewardList")
	public String rewardList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if (size == null) {
			size = 10;
		}
		
		if (page == null) {
			page = 0;
		}
		
		List list = this.userManager.getRewardSystemDao().findByPage(page,
				size);
		Integer count = this.userManager.getRewardSystemDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);
		
		map.put("result", list);
		
		map.put("content", "table-reward");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 金条交易管理
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/rewardTradeList")
	public String rewardTradeList(
			ModelMap map,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if (size == null) {
			size = 10;
		}
		
		if (page == null) {
			page = 0;
		}
		
		List list = this.userManager.getRewardTradeDao().findByPage(page,
				size);
		Integer count = this.userManager.getRewardTradeDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);
		
		map.put("result", list);
		
		map.put("content", "table-reward-trade");
		return Config.NEW_SERVER_CONTROL;
	}
	
	
	
	/***
	 * 用户详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/userDetail")
	public String userDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Users user = this.userManager.getUserDao().findById(contentId);
			
			map.put("data", user);
		}

		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "userDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	
	
	/***
	 * 认证详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/authenticDetail")
	public String authenticDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if (contentId != null) {
			Authentic authentic = this.authenticManager
					.findAuthenticById(contentId);

			String optional = authentic.getOptional();
			String area = authentic.getIndustoryArea();

			Map m = new HashMap();

			List list = new ArrayList();
			if (!optional.equals(null)) {
				if (optional.equals("")) {
					list.add(0);
				} else {
					String[] tempList = optional.split(",");

					for (String s : tempList) {
						s = s.replace(" ", "");
						list.add(s.trim());
					}
				}
			} else {
				list.add(0);
			}

			m.put("option", list);

			list = new ArrayList();
			if (area != null) {
				if (area.equals("")) {
					list.add(0);
				} else {
					String[] tempList = area.split(",");

					for (String s : tempList) {
						s = s.replace(" ", "");
						list.add(s);
					}
				}
			} else {
				list.add(0);
			}

			m.put("areas", list);
			map.put("ext", m);
			map.put("data", authentic);

		}
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		
		List areas = this.authenticManager.getIndustoryareaDao().findAll();
		List citys = this.authenticManager.getCityDao().findAll();
		List identities = this.authenticManager.getIdentitytypeDao().findAll();
		List authenticStatus = this.authenticManager.getAuthenticStatus().findAll();
				
		map.put("identities", identities);
		map.put("areas", areas);
		map.put("cities", citys);
		map.put("status", authenticStatus);
		map.put("optional", Config.STRING_AUTH_QUALIFICATION);
		
		map.put("content", "authenticDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	
	

	/***
	 * 金条详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/rewardDetail")
	public String rewardDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Rewardsystem system = this.userManager.getRewardSystemDao().findById(contentId);
			
			map.put("data", system);
		}

		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "rewardDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 项目详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/projectDetail")
	public String projectDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Project content = this.projectManager.getProjectDao().findById(contentId);
			
			map.put("data", content);
		}
		
		List l = this.projectManager.getFinancestatusDao().findAll();
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "projectDetail");
		map.put("status", l);
		return Config.NEW_SERVER_CONTROL;
	}
	
	
	/***
	 * 融资计划 详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/roadShowDetail")
	public String roadShowDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Roadshowplan content = this.projectManager.getRoadShowPlanDao().findById(contentId);
			
			if(content!= null)
			{
				List<Roadshow> list  = this.projectManager.getRoadShowDao().findByProperty("roadshowplan", content);
				if(list!=null && list.size()>0)
				{
					map.put("roadshow",list.get(0));
				}
				
				
			}
			
			map.put("data", content);
		}
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "roadShowDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 现场 详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/sceneDetail")
	public String sceneDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			Scene content = this.projectManager.getSceneDao().findById(contentId);
			
			map.put("data", content);
		}
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "sceneDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	
	
	/***
	 * 成员详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/memberDetail")
	public String memberDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Member content = this.projectManager.getMemberDao().findById(contentId);			
			map.put("data", content);
		}
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "memberDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 团队详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/teamDetail")
	public String teamDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			Team content = this.projectManager.getTeamDao().findById(contentId);			
			map.put("data", content);
		}
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "teamDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	
	/***
	 * 活动详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/actionDetail")
	public String actionDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			Action content = this.actionManager.getActionDao().findById(contentId);			
			map.put("data", content);
		}
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "actionDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 活动内容详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/actionContentDetail")
	public String actionContentDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			Actionintroduce content = this.actionManager.getActionIntroduceDao().findById(contentId);			
			map.put("data", content);
		}
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "actionContentDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	
	
	/***
	 * 财务状况详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/financeStandingDetail")
	public String financeStandingDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Financialstanding content = this.projectManager.getFinanceStandingDao().findById(contentId);
			String url = content.getUrl();
			Integer index = url.indexOf("=");
			if (index > 3) {
				String contentIdStr = url.substring(index + 1);
				Integer id = Integer.parseInt(contentIdStr);
				Weburlrecord record = this.webManager.getWebUrlRecordDao()
						.findById(id);
				map.put("record", record);
				
			}
			map.put("data", content);
		}
		
		//添加样式表
		List l_style = new ArrayList();
		l_style.add("../admin/kindeditor-master/themes/default/style.css");
		
		map.put("style", l_style);
		
		l_style = new ArrayList();
		l_style.add("../admin/ueditor/ueditor.config.js");
		l_style.add("../admin/ueditor/ueditor.all.min.js");
		l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
		
		map.put("js", l_style);
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "financeStandingDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 商业计划详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/businessPlanDetail")
	public String businessPlanDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Businessplan content = this.projectManager.getBusinessPlanDao().findById(contentId);
			String url = content.getUrl();
			Integer index = url.indexOf("=");
			if (index > 3) {
				String contentIdStr = url.substring(index + 1);
				Integer id = Integer.parseInt(contentIdStr);
				Weburlrecord record = this.webManager.getWebUrlRecordDao()
						.findById(id);
				map.put("record", record);
			}
			map.put("data", content);
		}
		
		//添加样式表
		List l_style = new ArrayList();
		l_style.add("../admin/kindeditor-master/themes/default/style.css");
		
		map.put("style", l_style);
		
		l_style = new ArrayList();
		l_style.add("../admin/ueditor/ueditor.config.js");
		l_style.add("../admin/ueditor/ueditor.all.min.js");
		l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
		
		map.put("js", l_style);
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "businessPlanDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 融资案例详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/financeCaseDetail")
	public String financeCaseDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Financingcase content = this.projectManager.getFinancingCaseDao().findById(contentId);
			String url = content.getUrl();
			Integer index = url.indexOf("=");
			if (index > 3) {
				String contentIdStr = url.substring(index + 1);
				Integer id = Integer.parseInt(contentIdStr);
				Weburlrecord record = this.webManager.getWebUrlRecordDao()
						.findById(id);
				map.put("record", record);
				
			}
			map.put("data", content);
		}
		
		//添加样式表
		List l_style = new ArrayList();
		l_style.add("../admin/kindeditor-master/themes/default/style.css");
		
		map.put("style", l_style);
		
		l_style = new ArrayList();
		l_style.add("../admin/ueditor/ueditor.config.js");
		l_style.add("../admin/ueditor/ueditor.all.min.js");
		l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
		
		map.put("js", l_style);
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "financeCaseDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 退出渠道
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/financeExitDetail")
	public String financeExitDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Financingexit content = this.projectManager.getFinancingexitDao().findById(contentId);
			String url = content.getUrl();
			Integer index = url.indexOf("=");
			if (index > 3) {
				String contentIdStr = url.substring(index + 1);
				Integer id = Integer.parseInt(contentIdStr);
				Weburlrecord record = this.webManager.getWebUrlRecordDao()
						.findById(id);
				map.put("record", record);
			}
			map.put("data", content);
		}
		
		
		//添加样式表
		List l_style = new ArrayList();
		l_style.add("../admin/kindeditor-master/themes/default/style.css");
		
		map.put("style", l_style);
		
		l_style = new ArrayList();
		l_style.add("../admin/ueditor/ueditor.config.js");
		l_style.add("../admin/ueditor/ueditor.all.min.js");
		l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
		
		map.put("js", l_style);
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "financeExitDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 资讯详情 
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/consultDetail")
	public String consultDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Msg content = this.messageManager.getMsgDao().findById(contentId);
			
			
			
			map.put("data", content);
		}
		
		//添加样式表
		List l_style = new ArrayList();
		l_style.add("../admin/kindeditor-master/themes/default/style.css");
		map.put("style", l_style);
		
		l_style = new ArrayList();
		l_style.add("../admin/ueditor/ueditor.config.js");
		l_style.add("../admin/ueditor/ueditor.all.min.js");
		l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
		
		map.put("js", l_style);
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "consultDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	
	/***
	 * 原创详情 
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/originalDetail")
	public String originalDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Original content = this.mainManager.getOrigianlDao().findById(contentId);
			

			map.put("data", content);
		}
		
		//添加样式表
		List l_style = new ArrayList();
		l_style.add("../admin/kindeditor-master/themes/default/style.css");
		
		map.put("style", l_style);
		
		l_style = new ArrayList();
		l_style.add("../admin/ueditor/ueditor.config.js");
		l_style.add("../admin/ueditor/ueditor.all.min.js");
		l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
		
		map.put("js", l_style);
		
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "originalDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 金日投条详情 
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/kingConsultDetail")
	public String kingConsultDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Weburlrecord content = this.systemManager.getWebUrlRecordDao().findById(contentId);
			
			//添加样式表
			List l_style = new ArrayList();
			l_style.add("../admin/kindeditor-master/themes/default/style.css");
			
			map.put("style", l_style);
			
			l_style = new ArrayList();
			l_style.add("../admin/ueditor/ueditor.config.js");
			l_style.add("../admin/ueditor/ueditor.all.min.js");
			l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
			
			map.put("js", l_style);
			map.put("data", content);
		}
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "kingConsultDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	/***
	 * 聊天室详情 
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/chatRoomDetail")
	public String chatRoomDetail(
			ModelMap map,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session) {
		
		if(contentId!=null)
		{
			
			Chatroom content = this.imManager.getChatRoomDao().findById(contentId);
			map.put("data", content);
		}
		
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "chatRoomDetail");
		return Config.NEW_SERVER_CONTROL;
	}
	

	/***
	 * ---------------------------------------------后端管理系统升级--------------------
	 * -----------------------
	 ***/

}
