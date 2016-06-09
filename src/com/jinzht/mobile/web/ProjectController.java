package com.jinzht.mobile.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jinzht.tools.Config;
import com.jinzht.tools.FileUtil;
import com.jinzht.web.dao.FinancialstandingDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Businessplan;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Controlreport;
import com.jinzht.web.entity.Financestatus;
import com.jinzht.web.entity.Financialstanding;
import com.jinzht.web.entity.Financingcase;
import com.jinzht.web.entity.Financingexit;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Sharetype;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.ProjectManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;

@Controller
public class ProjectController extends BaseController {

	@Autowired
	private ProjectManager ProjectManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private SystemManager systemManager;

	@RequestMapping(value = "/requestProjectList")
	@ResponseBody
	/***
	 * 获取圈子列表
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestProjectList(
			@RequestParam(value = "page", required = true) Integer page,
			HttpSession session) {
		this.result = new HashMap();
		List list = this.ProjectManager.findProjectsByCursor(page);
		if (list != null && list.size() > 0) {
			this.status = 200;
			this.message = "";
			this.result.put("data", list);
		} else {
			this.status = 201;
			this.result.put("data", new ArrayList());
			this.message = Config.STRING_FEELING_NO_DATA;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectDetail")
	@ResponseBody
	public Map requestProjectDetail(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		// 获取项目
		Project project = this.ProjectManager.findProjectById(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", project);
		this.message = Config.STRING_FEELING_ADD_SUCCESS;

		return getResult();
	}

	@RequestMapping(value = "/ 	")
	@ResponseBody
	/***
	 * 财务状况
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectFinance(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Financialstanding financialStanding = this.ProjectManager
				.findProjectFinancialStanding(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", financialStanding);

		if (financialStanding != null) {
			this.message = "";
		} else {
			this.status = 400;
			this.message = Config.STRING_PROJECT_NO_DETAIL;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinanceCase")
	@ResponseBody
	/***
	 * 
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectFinanceCase(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Financingcase financingCase = this.ProjectManager
				.findProjectFinancialCase(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", financingCase);

		if (financingCase != null) {
			this.message = "";
		} else {
			this.status = 400;
			this.message = Config.STRING_PROJECT_NO_DETAIL;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinancePlan")
	@ResponseBody
	/***
	 * 获取项目商业计划书
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectFinancePlan(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Businessplan businessPlan = this.ProjectManager
				.findProjectBusinessPlan(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", businessPlan);

		if (businessPlan != null) {
			this.message = "";
		} else {
			this.status = 400;
			this.message = Config.STRING_PROJECT_NO_DETAIL;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinanceExit")
	@ResponseBody
	/***
	 * 退出渠道
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectFinanceExit(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Financingexit financingExit = this.ProjectManager
				.findProjectFinanceExit(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", financingExit);

		if (financingExit != null) {
			this.message = "";
		} else {
			this.status = 400;
			this.message = Config.STRING_PROJECT_NO_DETAIL;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinanceControl")
	@ResponseBody
	/***
	 * 风控报告
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectFinanceControl(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Controlreport controlReport = this.ProjectManager
				.findProjectControlReport(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", controlReport);

		if (controlReport != null) {
			this.message = "";
		} else {
			this.status = 400;
			this.message = Config.STRING_PROJECT_NO_DETAIL;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinanceStatus")
	@ResponseBody
	public Map requestProjectFinanceStatus(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Financestatus project = this.ProjectManager
				.findProjectFinancialStatus(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", project);

		return getResult();
	}

	@RequestMapping(value = "/requestCommentProject")
	@ResponseBody
	/***
	 * 点赞/取消点赞
	 * @param contentId
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map requestCommentProject(
			@RequestParam(value = "contentId") Integer contentId,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "atUserId") Integer userId,
			@RequestParam(value = "flag") short flag, HttpSession session) {

		this.result = new HashMap();
		this.result.put("data", "");

		// // 获取当前发布内容用户
		// Users user = this.findUserInSession(session);
		//
		// if (user == null) {
		// this.status = 400;
		// this.message = Config.STRING_LOGING_FAIL_NO_USER;
		// } else {
		// // 查看当前操作状态，1:评论,2:回复
		// Publiccontent publicContent = this.ProjectManager
		// .findPublicContentById(contentId);
		//
		// Comment comment = new Comment();
		// comment.setContent(content);
		// comment.setUsersByUserId(user);
		// if (flag != 1) {
		// Users atUser = this.userManager.findUserById(userId);
		// comment.setUsersByAtUserId(atUser);
		// }
		// // 保存回复
		// publicContent.getComments().add(comment);
		//
		// this.ProjectManager.saveOrUpdate(publicContent);
		//
		// // 处理返回值
		// Users user1 = comment.getUsersByUserId();
		// if (user1.getAuthentics() != null) {
		// Object[] l = user.getAuthentics().toArray();
		// if (l.length > 0) {
		// Authentic authentic = (Authentic) l[0];
		// user1.setName(authentic.getName());
		// } else {
		// user1.setName("");
		// }
		//
		// } else {
		// user1.setName("");
		// }
		// user1.setAuthentics(null);
		// user1.setUserstatus(null);
		// user1.setTelephone(null);
		// user1.setPassword(null);
		// user1.setPlatform(null);
		// user1.setLastLoginDate(null);
		// comment.setUsersByUserId(user1);
		//
		// user1 = comment.getUsersByAtUserId();
		// if (user1 != null) {
		// if (user.getAuthentics() != null) {
		// Object[] l = user.getAuthentics().toArray();
		// if (l.length > 0) {
		// Authentic authentic = (Authentic) l[0];
		// user1.setName(authentic.getName());
		// } else {
		// user1.setName("");
		// }
		//
		// } else {
		// user1.setName("");
		// }
		// user1.setAuthentics(null);
		// user1.setUserstatus(null);
		// user1.setTelephone(null);
		// user1.setPassword(null);
		// user1.setPlatform(null);
		// user1.setLastLoginDate(null);
		// comment.setUsersByAtUserId(user1);
		// }
		//
		// // 封装返回结果
		// this.status = 200;
		// this.result.put("data", comment);
		// if (flag != 1) {
		// this.message = Config.STRING_FEELING_REPLY_SUCCESS;
		// } else {
		// this.message = Config.STRING_FEELING_COMMENT_SUCCESS;
		// }
		// }

		return getResult();
	}

	@RequestMapping(value = "/requestShareProject")
	@ResponseBody
	/***
	 * 状态分享
	 * @param contentId
	 * @param content
	 * @param session
	 * @return
	 */
	public Map requestShareProject(@RequestParam(value = "type") int type,
			@RequestParam(value = "contentId") Integer contentId,
			@RequestParam(value = "content") String content, HttpSession session) {

		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 查看当前操作状态，1:评论,2:回复
			Publiccontent publicContent = this.ProjectManager
					.findPublicContentById(contentId);
			// 生成分享链接
			Share share = new Share();
			Sharetype shareType = new Sharetype();
			shareType.setShareTypeId(type);
			share.setSharetype(shareType);
			share.setShareDate(new Date());
			share.setContent(content);
			share.setContentId(contentId);

			String url = ""; // 生成分享链接
			switch (type) {
			case 3:
				url = Config.STRING_SHARE_APP_URL;
				break;
			default:
				url = String.format("%s%d/%d", Config.STRING_SYSTEM_ADDRESS,
						type, contentId);
				break;
			}

			share.setUrl(url);

			// 保存分享记录
			this.systemManager.saveShareRecord(share);

			share.setShareId(null);
			share.setSharetype(null);
			share.setShareDate(null);
			// 封装返回结果
			this.status = 200;
			this.result.put("data", share);
			this.message = "";
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectCollect")
	@ResponseBody
	/***
	 * 关注项目
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectCollect(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			int flag = this.ProjectManager.projectCollect(projectId, user);
			
			Map map  = new HashMap();
			map.put("flag", flag);
			
			//封装返回数据
			this.status = 200;
			this.result.put("data", map);
			if(flag==0){
				this.message = Config.STRING_PROJECT_COLLECT_CANCEL;
			}else{
				this.message = Config.STRING_PROJECT_COLLECT_ADD;
			}
		}

		return getResult();
	}
	@RequestMapping(value = "/requestProjectComment")
	@ResponseBody
	/***
	 * 评论项目
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectComment(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			@RequestParam(value = "content", required = true) String content,
			HttpSession session) {
		this.result = new HashMap();
		
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			
			//添加评论
			this.ProjectManager.projectComment(projectId, content, user);
			
			//封装返回数据
			this.status = 200;
			this.result.put("data", "");
			this.message = Config.STRING_PROJECT_COMMENT_SUCCESS;
		}
		return getResult();
	}
	
	@RequestMapping(value = "/requestScene")
	@ResponseBody
	/***
	 * 项目现场
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestScene(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			
			//添加评论
			List list = this.ProjectManager.findSceneByProjectId(projectId);
			
			//封装返回数据
			this.status = 200;
			this.result.put("data", list);
			this.message = Config.STRING_PROJECT_SCENE_SUCCESS;
		}
		return getResult();
	}
	@RequestMapping(value = "/requestRecorData")
	@ResponseBody
	/***
	 * 项目现场
	 * @param sceneId
	 * @param session
	 * @return
	 */
	public Map requestRecorData(
			@RequestParam(value = "sceneId", required = true) Integer sceneId,
			HttpSession session) {
		this.result = new HashMap();
		
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			
			//添加评论
			List list = this.ProjectManager.findAudioRecordBySceneId(sceneId);
			
			//封装返回数据
			this.status = 200;
			this.result.put("data", list);
			this.message = Config.STRING_PROJECT_SCENE_SUCCESS;
		}
		return getResult();
	}
	@RequestMapping(value = "/requestRoadShowByProjectId")
	@ResponseBody
	/***
	 * 项目现场
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestRoadShowByProjectId(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			
			//添加评论
			Roadshow roadShow = this.ProjectManager.findRoadShowByProject(projectId);
			
			//封装返回数据
			this.status = 200;
			this.result.put("data", roadShow);
			this.message = Config.STRING_PROJECT_SCENE_SUCCESS;
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