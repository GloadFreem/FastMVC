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
public class BusinessesWebAdminController extends BaseController {
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

	/***
	 * ---------------------------------------------商学后端管理系统升级------------------
	 * -------------------------
	 ***/

	/***
	 * 商学内容
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/CourseList")
	public String CourseList(ModelMap map, HttpSession session) {
		List list = this.courseManager.getBuinessSchoolDao().findAll();

		map.put("result", list);
		map.put("content", "table-course");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/courseDetail")
	public String courseDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			ModelMap map, HttpSession session) {
		if (contentId != null) {
			BusinessSchool school = this.courseManager.getBuinessSchoolDao()
					.findById(contentId);
			map.put("data", school);

		}

		List types = this.courseManager.getBusinessContentTypeDao().findAll();
		List tages = this.courseManager.getBusniessTagDao().findAll();
		List speeches = this.courseManager.getSpeechmarkerDAO().findAll();
		List users = this.courseManager.getBusinessWe3iChatDao().findAll();

		map.put("users", users);
		map.put("types", types);
		map.put("tages", tages);
		map.put("speeches", speeches);
		map.put("content", "CourseDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/workerDetail")
	public String workerDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			ModelMap map, HttpSession session) {
		if (contentId != null) {
			BusinessWeichat user = this.courseManager.getBusinessWe3iChatDao()
					.findById(contentId);
			map.put("data", user);

		}

		map.put("content", "workerDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/deleteWorker")
	public String deleteWorker(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			ModelMap map, HttpSession session) {
		if (contentId != null) {
			BusinessWeichat user = this.courseManager.getBusinessWe3iChatDao()
					.findById(contentId);
			this.courseManager.getBusinessWe3iChatDao().delete(user);
		}

		List list = this.courseManager.getBusinessWe3iChatDao().findAll();
		
		map.put("result", list);
		map.put("content", "table-course-worker");
		return Config.NEW_SERVER_CONTROL;
	}
	@RequestMapping(value = "newSystem/editWorker")
	public String editWorker(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "wcode", required = false) String wcode,
			ModelMap map, HttpSession session) {
		BusinessWeichat user;
		if (contentId != null) {
			user = this.courseManager.getBusinessWe3iChatDao()
					.findById(contentId);
		}else{
			user = new BusinessWeichat();
		}
		
		
		user.setName(name);
		user.setTelephone(telephone);
		user.setWcode(wcode);
		
		
		if(contentId!=null)
		{
			this.courseManager.getBusinessWe3iChatDao().saveOrUpdate(user);
		}else{
			this.courseManager.getBusinessWe3iChatDao().save(user);
		}
		
		
		map.put("data", user);
		map.put("content", "workerDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editCurseDetail")
	public String editCurseDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "speechDesc", required = false) String speechDesc,
			@RequestParam(value = "speechName", required = false) String speechName,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "weichat", required = false) String weichat,
			@RequestParam(value = "wcode", required = false) Integer wcode,
			@RequestParam(value = "tag", required = false) Integer tag,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "speechmarker", required = false) Integer speechmarker,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "bpriceBase", required = false) String bpriceBase,
			@RequestParam(value = "bpriceNow", required = false) String bpriceNow,
			@RequestParam(value = "bstarTime", required = false) String bstarTime,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "file", required = false) MultipartFile file,
			ModelMap map, HttpSession session) {
		BusinessSchool school;
		if (contentId != null) {
			school = this.courseManager.getBuinessSchoolDao().findById(
					contentId);

		} else {
			school = new BusinessSchool();
		}

		if (school != null) {
			school.setBname(name);
			school.setBimage(image);
			school.setBdesc(content);
			school.setBpriceBase(bpriceBase);
			school.setBpriceNow(bpriceNow);
			school.setBstarTime(new Date());

			BusniessTag busniessTag = this.courseManager.getBusniessTagDao()
					.findById(tag);
			school.setBusniessTag(busniessTag);

			BusinessContentType contentType = this.courseManager
					.getBusinessContentTypeDao().findById(type);
			school.setBusinessContentType(contentType);

			Speechmarker marker = this.courseManager.getSpeechmarkerDAO()
					.findById(speechmarker);

			school.setSpeechmarker(marker);
			
			BusinessWeichat wechat = this.courseManager.getBusinessWe3iChatDao().findById(wcode);
			
			school.setBusinessWeichat(wechat);

			if (file != null) {
				// 保存图片
				String fileName = String.format(
						Config.STRING_USER_HEADER_PICTURE_FORMAT,
						new Date().getTime());
				String result = FileUtil.savePicture(file, fileName,
						"upload/headerImages/");
				if (!result.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS
							+ "upload/headerImages/" + result;
					school.setBimage(fileName);
				}
			}

			if (contentId != null) {
				this.courseManager.getBuinessSchoolDao().saveOrUpdate(school);

			} else {
				this.courseManager.getBuinessSchoolDao().save(school);
			}
		}
		map.put("data", school);

		List types = this.courseManager.getBusinessContentTypeDao().findAll();
		List tages = this.courseManager.getBusniessTagDao().findAll();
		List speeches = this.courseManager.getSpeechmarkerDAO().findAll();
		List users = this.courseManager.getBusinessWe3iChatDao().findAll();

		map.put("types", types);
		map.put("tages", tages);
		map.put("speeches", speeches);
		map.put("users", users);

		map.put("content", "CourseDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editSourceDetail")
	public String editSourceDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "projectId", required = false) Integer curseId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "vdesc", required = false) String vdesc,
			@RequestParam(value = "vposition", required = false) Integer vposition,
			@RequestParam(value = "vurl", required = false) String vurl,
			@RequestParam(value = "vtimelong", required = false) Integer vtimelong,
			@RequestParam(value = "bpriceBase", required = false) String bpriceBase,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "video", required = false) MultipartFile video,
			ModelMap map, HttpSession session) {
		BusinessVideo v;
		if (contentId != null) {
			v = this.courseManager.getBusinessVedioDao().findById(contentId);

		} else {
			v = new BusinessVideo();
		}

		if (v != null) {
			v.setVname(name);
			v.setVdesc(vdesc);
			v.setVurl(vurl);
			v.setVimage(image);
			v.setVtimelong(vtimelong);
			v.setVposition(vposition);
			
			if (curseId != null) {
				BusinessSchool school = this.courseManager.getBuinessSchoolDao()
						.findById(curseId);
				if (school != null) {
					v.setBusinessSchool(school);
				}
			}

			if (file != null) {
				// 保存图片
				String fileName = String.format(
						Config.STRING_USER_HEADER_PICTURE_FORMAT,
						new Date().getTime());
				String result = FileUtil.savePicture(file, fileName,
						"upload/headerImages/");
				if (!result.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS
							+ "upload/headerImages/" + result;
					v.setVimage(fileName);
				}
			}

			if (video != null) {
				// 保存图片
				String fileName = String.format(
						Config.STRING_USER_HEADER_PICTURE_FORMAT,
						new Date().getTime());
				String result = FileUtil.savePicture(video, fileName,
						"upload/video/");
				if (!result.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS + "upload/video/"
							+ result;
					v.setVurl(fileName);
				}
			}

			if (contentId != null) {
				this.courseManager.getBusinessVedioDao().saveOrUpdate(v);
			} else {
				this.courseManager.getBusinessVedioDao().save(v);
			}

		}
		
		map.put("data", v);

		map.put("content", "sourceDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/sourceDetail")
	public String sourceDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			ModelMap map, HttpSession session) {
		if (contentId != null) {
			BusinessVideo video = this.courseManager.getBusinessVedioDao()
					.findById(contentId);
			
			
			map.put("data", video);

		}

		map.put("content", "sourceDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/codeDetail")
	public String codeDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			ModelMap map, HttpSession session) {
		if (contentId != null) {
			BusinessInvitationCode code = this.courseManager
					.getBusinessInvitationCodeDao().findById(contentId);
			
			map.put("data", code);
			map.put("school", code);

		}

		map.put("content", "CodeDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editCodeDetail")
	public String editCodeDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "ccode", required = false) String ccode,
			@RequestParam(value = "projectId", required = false) Integer curseId,
			ModelMap map, HttpSession session) {
		BusinessInvitationCode code;
		if (contentId != null) {
			code = this.courseManager.getBusinessInvitationCodeDao().findById(
					contentId);

		} else {
			code = new BusinessInvitationCode();
		}

		if (curseId != null) {
			BusinessSchool school = this.courseManager.getBuinessSchoolDao()
					.findById(curseId);
			if (school != null) {
				code.setBusinessSchool(school);
			}
		}

		code.setCcode(ccode);

		if (contentId != null) {
			this.courseManager.getBusinessInvitationCodeDao()
					.saveOrUpdate(code);

		} else {
			code.setCvalid("1");
			this.courseManager.getBusinessInvitationCodeDao().save(code);
		}

		map.put("data", code);

		map.put("content", "CodeDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 搜索
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/SearchCurseByName")
	@ResponseBody
	public Map SearchCurseByName(
			@RequestParam(value = "name", required = false) String key,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		List list = this.courseManager.getBuinessSchoolDao().findByKeyName(key);

		this.result.put("data", list);
		this.status = 200;
		this.message = "";

		return getResult();
	}

	/***
	 * 邀请码
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/InviteCodeList")
	public String InviteCodeList(ModelMap map, HttpSession session) {
		List list = this.courseManager.getBusinessInvitationCodeDao().findAll();

		map.put("result", list);
		map.put("content", "table-invite-code");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 讲师
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/speecherList")
	public String speecherList(ModelMap map, HttpSession session) {
		List list = this.courseManager.getSpeechmarkerDAO().findAll();

		map.put("result", list);
		map.put("content", "table-speecher");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 讲师详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/speecherDetail")
	public String speecherDetail(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		if (contentId != null) {
			Speechmarker marker = this.courseManager.getSpeechmarkerDAO()
					.findById(contentId);
			map.put("data", marker);
		}

		map.put("content", "speecherDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/editSpeecher")
	public String editSpeecher(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "desc", required = false) String desc,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "file", required = false) MultipartFile file,
			ModelMap map, HttpSession session) {
		Speechmarker marker;
		if (contentId != null) {
			marker = this.courseManager.getSpeechmarkerDAO()
					.findById(contentId);

		} else {
			marker = new Speechmarker();
		}

		if (marker != null) {
			marker.setName(name);
			marker.setDesc(desc);
			marker.setImage(image);

			if (file != null) {
				// 保存图片
				String fileName = String.format(
						Config.STRING_USER_HEADER_PICTURE_FORMAT,
						new Date().getTime());
				String result = FileUtil.savePicture(file, fileName,
						"upload/headerImages/");
				if (!result.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS
							+ "upload/headerImages/" + result;
					marker.setImage(fileName);
				}
			}

			if (contentId != null) {
				this.courseManager.getSpeechmarkerDAO().saveOrUpdate(marker);
			} else {
				this.courseManager.getSpeechmarkerDAO().save(marker);
			}

		}
		map.put("data", marker);

		map.put("content", "speecherDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 删除
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/deleteSpeecher")
	public String deleteSpeecher(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			ModelMap map, HttpSession session) {
		if (contentId != null) {
			Speechmarker marker = this.courseManager.getSpeechmarkerDAO()
					.findById(contentId);
			if (marker != null) {
				this.courseManager.getSpeechmarkerDAO().delete(marker);
			}
		}

		List list = this.courseManager.getSpeechmarkerDAO().findAll();

		map.put("result", list);
		map.put("content", "table-speecher");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 参课
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/AttendList")
	public String AttendList(ModelMap map, HttpSession session) {
		List list = this.courseManager.getBusniessJoinDao().findAll();

		map.put("result", list);
		map.put("content", "table-course-attend");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 删除记录
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/deleteJoinRecord")
	public String deleteJoinRecord(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			ModelMap map, HttpSession session) {

		BusniessJoin record = this.courseManager.getBusniessJoinDao().findById(
				contentId);
		if (record != null) {
			this.courseManager.getBusniessJoinDao().delete(record);
		}

		List list = this.courseManager.getBusniessJoinDao().findAll();

		map.put("result", list);
		map.put("content", "table-course-attend");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 资源列表
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/sourceList")
	public String sourceList(ModelMap map, HttpSession session) {
		List list = this.courseManager.getBusinessVedioDao().findAll();

		map.put("result", list);
		map.put("content", "table-source");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * 运营
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/WorkerList")
	public String WorkerList(ModelMap map, HttpSession session) {
		List list = this.courseManager.getBusinessWe3iChatDao().findAll();

		map.put("result", list);
		map.put("content", "table-course-worker");
		return Config.NEW_SERVER_CONTROL;
	}

	/***
	 * ---------------------------------------------后端管理系统升级--------------------
	 * -----------------------
	 ***/

}
