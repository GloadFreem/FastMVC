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

		List list = this.userManager.getUserDao().findByPage(
				page,size);
		Integer count = this.userManager.getUserDao().countOfAllUsers();
		Tools.setValueOfWebPage(map, page, count, size, sortmenu, menu, submenu);

		map.put("result", list);

		map.put("content", "table-user");
		return Config.NEW_SERVER_CONTROL;
	}
	
	/***
	 * ---------------------------------------------后端管理系统升级--------------------
	 * -----------------------
	 ***/

}
