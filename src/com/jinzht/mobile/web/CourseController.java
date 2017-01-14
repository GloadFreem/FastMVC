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
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.BusinessInvitationCode;
import com.jinzht.web.entity.BusinessJionRecord;
import com.jinzht.web.entity.BusinessSchool;
import com.jinzht.web.entity.BusniessJoin;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Member;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.ProjectHotSearch;
import com.jinzht.web.entity.ProjectSearchBean;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Roadshowplan;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Weburlrecord;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.CourseManager;
import com.jinzht.web.manager.InvestorManager;
import com.jinzht.web.manager.ProjectManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.manager.WebManager;
import com.jinzht.web.test.User;
import com.message.Enity.Msg;
import com.message.Enity.MsgBean;
import com.message.Enity.MsgDetail;
import com.message.Enity.Newsbanner;
import com.message.Enity.Original;
import com.message.Enity.OriginalDetail;
import com.message.Enity.Originalbanner;
import com.message.manager.MainManager;
import com.message.manager.MessageMananger;

@Controller
public class CourseController extends BaseController {
	@Autowired
	private UserManager userManager;
	@Autowired
	private CourseManager courseManager;

	@RequestMapping(value = "/CourseList")
	@ResponseBody
	public Map generateWebPage(
			@RequestParam(value = "page", required = false) Integer page,
			HttpSession session, ModelMap map) {
		this.result = new HashMap();

		List<BusinessSchool> list = this.courseManager.getBuinessSchoolDao().findByValidPage(page);

		Users user = this.findUserInSession(session);
		// 封装
		for (BusinessSchool school : list) {
			school.setBdesc(null);
			school.setChatroom(null);
			school.setSpeechmarker(null);
			school.setBusniessJoins(null);
			school.setBusinessContentType(null);
			school.setBusinessWeichat(null);
			school.setBusinessVideos(null);
			school.setBusinessType(null);
			school.setBusniessTag(null);

			// 查找是否已参加课程
			List l = this.courseManager.getBusniessJoinDao()
					.findBySchoolAndUser(user, school, 0);
			if (l != null && l.size() > 0) {
				school.setBextr2("1");
			}
		}

		if (list == null) {
			list = new ArrayList();
		} else if (list != null && list.size() == 0 && page > 0) {
			this.status = 201;
		} else {
			this.status = 200;
		}

		this.message = "";
		this.result.put("data", list);
		return getResult();
	}

	@RequestMapping(value = "/mineCourseList")
	@ResponseBody
	public Map MineCourseList(
			@RequestParam(value = "page", required = false) Integer page,
			HttpSession session, ModelMap map) {
		this.result = new HashMap();

		Users user = this.findUserInSession(session);
		List<BusniessJoin> l = this.courseManager.getBusniessJoinDao()
				.findByUser(user, page);

		List<BusinessSchool> list = new ArrayList();
		for (BusniessJoin j : l) {
			list.add(j.getBusinessSchool());
		}

		// 封装
		for (BusinessSchool school : list) {
			school.setBdesc(null);
			school.setChatroom(null);
			school.setSpeechmarker(null);
			school.setBusniessJoins(null);
			school.setBusinessContentType(null);
			school.setBusinessWeichat(null);
			school.setBusinessVideos(null);
			school.setBusinessType(null);
			school.setBusniessTag(null);

			school.setBextr2("1");
		}

		if (list == null) {
			list = new ArrayList();
		} else if (list != null && list.size() == 0 && page > 0) {
			this.status = 201;
		} else {
			this.status = 200;
		}

		this.message = "";
		this.result.put("data", list);
		return getResult();
	}

	@RequestMapping(value = "/CourseDetail")
	@ResponseBody
	public Map CourseDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			HttpSession session,
			ModelMap map) {
		this.result = new HashMap();

		Users user = this.findUserInSession(session);
		if(user!=null){
			BusinessSchool school = this.courseManager.getBuinessSchoolDao()
					.findById(contentId);
			
			// 查找是否已参加课程
			List l = this.courseManager.getBusniessJoinDao()
					.findBySchoolAndUser(user, school, 0);
			if (l != null && l.size() > 0) {
				school.setBextr2("1");
			}
			
			if (school == null) {
				this.result.put("data", "");
			} else {
				this.result.put("data", school);
			}
			if(user.getName()==null){
				user.setName("用户"+user.getUserId());
			}
			this.result.put("extr", user.getName());
			this.status = 200;
			this.message = "";
		}else{
			this.result.put("extr", "");
			this.status = 401;
			this.message = "请先登录!";
		}
		 
		return getResult();
	}

	@RequestMapping(value = "/attendCourse")
	@ResponseBody
	public Map attendCourse(
			@RequestParam(value = "courseId", required = false) Integer courseId,
			@RequestParam(value = "inviteCode", required = false) String inviteCode,
			@RequestParam(value = "telephone", required = false) String telephone,
			HttpSession session, ModelMap map) {
		this.result = new HashMap();

		Users user = this.findUserInSession(session);

		List<BusinessInvitationCode> codes = this.courseManager
				.getBusinessInvitationCodeDao().findByCcode(inviteCode);

		if (codes != null && codes.size() > 0) {
			BusinessInvitationCode code = codes.get(0);

			// 是否已使用
			if (code.getCvalid().equalsIgnoreCase("0")) {
				this.status = 400;
				this.result.put("data", "");
				this.message = "邀请码无效";

				return getResult();
			}

			Integer courseID = code.getBusinessSchool().getBid();

			if (courseId != courseID) {
				this.status = 400;
				this.result.put("data", "");
				this.message = "该邀请码针对本课程无效";
			} else {
				BusinessSchool school = this.courseManager
						.getBuinessSchoolDao().findById(courseID);

				BusniessJoin join = new BusniessJoin();
				join.setBusinessInvitationCode(code);
				join.setBusinessSchool(school);
				join.setUsers(user);
				join.setCattendtime(new Date());
				join.setJtel(telephone);

				// 保存
				this.courseManager.getBusniessJoinDao().save(join);
				
				//设置邀请码过期
				code.setCvalid("0");
				
				this.courseManager.getBusinessInvitationCodeDao().saveOrUpdate(code);

				this.status = 200;
				this.result.put("data", "");
				this.message = "参课成功!";
			}
		} else {
			this.status = 400;
			this.result.put("data", "");
			this.message = "邀请码无效";
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
