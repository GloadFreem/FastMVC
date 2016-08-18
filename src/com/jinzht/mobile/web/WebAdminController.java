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
import com.jinzht.tools.Tools;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Actionimages;
import com.jinzht.web.entity.Audiorecord;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Authenticstatus;
import com.jinzht.web.entity.Banner;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Contentimages;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Financestatus;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Roadshowplan;
import com.jinzht.web.entity.Scene;
import com.jinzht.web.entity.Systemuser;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Weburlrecord;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.ActionManager;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.FeelingManager;
import com.jinzht.web.manager.InvestorManager;
import com.jinzht.web.manager.ProjectManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.manager.WebManager;
import com.jinzht.web.test.User;

@Controller
public class WebAdminController extends BaseController {
	@Autowired
	private WebManager webManager;
	@Autowired
	private SystemManager systemManger;
	@Autowired
	private UserManager userManager;
	@Autowired
	private AuthenticManager authenticManager;
	@Autowired
	private ProjectManager projectManager;
	@Autowired
	private ActionManager actionManaer;
	@Autowired
	private FeelingManager feelingManager;

	@RequestMapping(value = "/admin/adminLogin")
	public String webEditor() {
		return "/admin/login";
	}

	@RequestMapping(value = "/admin/adminLoginAction")
	public String loginAction(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password,
			HttpSession session, ModelMap map) {
		if (name == null || password == null) {
			map.put("tip", "账号或密码不能为空!");
			return "/admin/login";
		}
		Object userId = session.getAttribute("userId");
		password = MD5.GetMD5Code(password);

		List list = this.userManager.getSystemUserDao().findByName(name);
		if (list != null && list.size() > 0) {
			Systemuser user = (Systemuser) list.get(0);
			if (user.getAccount().equals(name)
					&& user.getPassword().equals(password)) {
				session.setAttribute("userId", user.getUserId());
			} else {
				map.put("tip", "账号或密码错误，请检查后重试！");
				return "/admin/login";
			}
		} else {
			map.put("tip", "该账号不存在，请联系管理人员!");
			return "/admin/login";
		}

		map.put("tip", "");
		// session.setAttribute("userId", null);
		return "/admin/adminIndex";
	}

	@RequestMapping(value = "/admin/adminDashboard")
	public String dashboard(ModelMap map) {
		List<Banner> list = this.systemManger.findBannerInfoList();
		map.put("items", list.iterator());
		return "/admin/dashboard";
	}

	@RequestMapping(value = "/admin/adminJsp")
	public String adminJsp() {
		return "/admin/index";
	}

	/***
	 * top
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminTop")
	public String topJSP() {
		return "/admin/top";
	}

	/***
	 * content
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminContent")
	public String contentJSP() {
		return "/admin/content";
	}

	/***
	 * left
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminLeft")
	public String leftJSP() {
		return "/admin/left";
	}

	/***
	 * bottom
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminBottom")
	public String bottomJSP() {
		return "/admin/bottom";
	}

	/***
	 * banner列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminBannerListAdmin")
	public String bannerListAdmin(ModelMap map) {
		List<Banner> list = this.systemManger.findBannerInfoList();
		map.put("items", list.iterator());
		return "/admin/banner/bannerList";
	}

	/***
	 * banner列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminEditBanner")
	public String editBanner(
			@RequestParam(value = "bannerId", required = false) Integer bannerId,
			ModelMap map) {
		if (bannerId != null) {
			Banner banner = this.systemManger.getBannerDao().findById(bannerId);
			map.put("banner", banner);
		}

		return "/admin/banner/editorBanner";
	}

	@RequestMapping(value = "/admin/adminUploadImage")
	@ResponseBody
	/***
	 * 上传图片
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map uploadImage(
			@RequestParam(value = "flag", required = false) String type,
			@RequestParam(value = "file", required = false) MultipartFile[] images,
			HttpSession session) {

		List list ;
		if(session.getAttribute(type)!=null)
		{
			list = (ArrayList)session.getAttribute(type);
			
		}else{
			list = new ArrayList();
		}
//		session.setAttribute(type, null);

		this.result = new HashMap();
		this.result.put("data", "");
		// 保存图片
		String fileName = "";
		String result = "";
		if (images != null && images.length > 0) {

			MultipartFile file = null;
			Set items = new HashSet();
			for (int i = 0; i < images.length; i++) {
				if (images[i] != null) {
					file = images[i];
					fileName = String.format(
							Config.STRING_USER_FEELING_PICTUREA_FORMAT,
							new Date().getTime(), i);
					result = FileUtil.savePicture(file, fileName,
							"upload/uploadImages/");
					if (!result.equals("")) {
						fileName = Config.STRING_SYSTEM_ADDRESS
								+ "upload/uploadImages/" + result;
						list.add(fileName);
					} else {
						fileName = "";
					}

				}
			}
			this.result.put("data", fileName);
			session.setAttribute(type, list);
		}
		return getResult();
	}

	@RequestMapping(value = "/admin/adminUploadAudio")
	@ResponseBody
	/***
	 * 上传音频
	 * @param session
	 * @return
	 */
	public Map adminUploadAudio(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "audio", required = false) MultipartFile[] audios,
			HttpSession session) {

		session.setAttribute(type, null);

		this.result = new HashMap();
		this.result.put("data", "");
		// 保存图片
		String fileName = "";
		String result = "";
		if (audios != null && audios.length > 0) {

			MultipartFile file = null;
			Set items = new HashSet();
			List list = new ArrayList();
			for (int i = 0; i < audios.length; i++) {
				if (audios[i] != null) {
					file = audios[i];
					fileName = String.format(Config.STRING_USER_AUDIO_FORMAT,
							new Date().getTime(), i);
					result = FileUtil.savePicture(file, fileName,
							"upload/audios/");
					if (!result.equals("")) {
						fileName = Config.STRING_SYSTEM_ADDRESS
								+ "upload/audios/" + result;
						list.add(fileName);
					} else {
						fileName = "";
					}
				}
			}
			this.result.put("data", fileName);
			session.setAttribute(type, list);
		}
		return getResult();
	}

	@RequestMapping(value = "/admin/adminUploadFeelingImage")
	@ResponseBody
	/***
	 * 上传图片
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map uploadFeelingImage(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "file", required = false) MultipartFile[] images,
			HttpSession session) {

		session.setAttribute(type, null);

		this.result = new HashMap();
		this.result.put("data", "");
		// 保存图片
		String fileName = "";
		String result = "";
		if (images != null && images.length > 0) {

			MultipartFile file = null;
			Set items = new HashSet();
			List list = new ArrayList();
			for (int i = 0; i < images.length; i++) {
				if (images[i] != null) {
					file = images[i];
					fileName = String.format(
							Config.STRING_USER_FEELING_PICTUREA_FORMAT,
							new Date().getTime(), i);
					result = FileUtil.savePicture(file, fileName,
							"upload/feelingImages/");
					if (!result.equals("")) {
						fileName = Config.STRING_SYSTEM_ADDRESS
								+ "upload/feelingImages/" + result;
						list.add(fileName);
					} else {
						fileName = "";
					}

				}
			}

			session.setAttribute(type, list);
		}
		return getResult();
	}

	@RequestMapping(value = "/admin/adminAddBanner")
	/***
	 * 添加Banner
	 * @return
	 */
	public String addBanner(
			@RequestParam(value = "bannerId", required = false) Integer bannerId,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "description", required = false) String description,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		List l = (List) session.getAttribute("images");
		Banner banner;

		if (bannerId != -1) {
			banner = this.systemManger.getBannerDao().findById(bannerId);
		} else {
			banner = new Banner();
		}

		banner.setName(name);
		banner.setUrl(url);
		banner.setDescription(description);

		if (image != null && !image.equals("") && l == null || l.size() == 0) {
			banner.setImage(image);
		} else {
			banner.setImage(l.get(0).toString());
		}

		if (bannerId != -1) {
			this.systemManger.getBannerDao().saveOrUpdate(banner);
		} else {
			this.systemManger.getBannerDao().save(banner);
		}

		List<Banner> list = this.systemManger.findBannerInfoList();
		map.put("items", list.iterator());
		return "/admin/banner/bannerList";
	}

	/***
	 * 用户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminUserListAdmin")
	public String userListAdmin(ModelMap map) {
		List<Users> list = this.userManager.getUserDao().findAll();
		map.put("items", list.iterator());
		return "/admin/Users/userList";
	}

	/***
	 * 项目列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminProjectListAdmin")
	public String projectListAdmin(ModelMap map) {
		List<Users> list = this.projectManager.getProjectDao().findAll();
		map.put("items", list.iterator());
		return "/admin/project/projectList";
	}

	/***
	 * 路演列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminRoadShowListAdmin")
	public String roadShowListAdmin(ModelMap map) {
		List<Roadshow> list = this.projectManager.getRoadShowDao().findAll();
		map.put("items", list.iterator());
		return "/admin/project/roadShowList";
	}

	/***
	 * 现场列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminSceneListAdmin")
	public String sceneListAdmin(ModelMap map) {
		List<Scene> list = this.projectManager.getSceneDao().findAll();
		map.put("items", list.iterator());
		return "/admin/project/sceneList";
	}

	/***
	 * 活动列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminActionListAdmin")
	public String actionListAdmin(ModelMap map) {
		List<Action> list = this.actionManaer.getActionDao().findAll();
		map.put("items", list.iterator());
		return "/admin/action/actionList";
	}

	/***
	 * 圈子列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminCycleListAdmin")
	public String cycleListAdmin(ModelMap map) {
		List<Publiccontent> list = this.feelingManager.getPublicContentDao()
				.findAll();
		map.put("items", list.iterator());
		return "/admin/cycle/cycleList";
	}

	/***
	 * 认证审核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminUserAuthenticListAdmin")
	public String userAuthenticListAdmin(ModelMap map) {
		List citys = this.authenticManager.getCityDao().findAll();
		List areas = this.authenticManager.getIndustoryareaDao().findAll();
		List<Authentic> list = this.userManager.getAuthenticDao().findAll();
		List authenticStatus = this.authenticManager.getAuthenticStatus()
				.findAll();

		map.put("authenticStatus", authenticStatus);
		map.put("areas", areas);
		map.put("cities", citys);
		map.put("items", list.iterator());
		return "/admin/Users/userAuthenticList";
	}

	/***
	 * 编辑用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminEditUser")
	public String editUser(
			@RequestParam(value = "userId", required = false) Integer userId,
			ModelMap map) {
		List areas = this.authenticManager.getIndustoryareaDao().findAll();
		List citys = this.authenticManager.getCityDao().findAll();
		if (userId != null) {
			Users user = this.userManager.findUserById(userId);

			Set set = user.getAuthentics();
			Object[] objs = set.toArray();

			List l = new ArrayList();
			for (int i = 0; i < objs.length; i++) {
				Authentic authentic = (Authentic) objs[i];
				String optional = authentic.getOptional();
				String area = authentic.getIndustoryArea();

				Map m = new HashMap();

				List list = new ArrayList();
				if (optional != null) {
					if (optional.equals("")) {
						list.add(0);
					} else {
						String[] tempList = optional.split(",");

						for (String s : tempList) {
							s = s.replace(" ", "");
							list.add(Integer.parseInt(s.trim()));
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
							list.add(s.trim());
						}
					}
				} else {
					list.add(0);
				}

				m.put("areas", list);

				l.add(m);
			}

			map.put("ext", l);
			map.put("user", user);
			map.put("authentics", set);

		}

		map.put("areas", areas);
		map.put("cities", citys);
		map.put("optional", Config.STRING_AUTH_QUALIFICATION);

		return "/admin/Users/editorUser";
	}

	/***
	 * 编辑项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminEditProject")
	public String editProject(
			@RequestParam(value = "projectId", required = false) Integer projectId,
			ModelMap map) {
		if (projectId != null) {
			Project project = this.projectManager.findProjectById(projectId);
			Object[] objs = project.getRoadshows().toArray();
			if (objs != null && objs.length > 0) {
				Roadshow show = (Roadshow) objs[0];
				map.put("show", show);
			}
			map.put("project", project);
		}

		List list = this.projectManager.getFinancestatusDao().findAll();
		map.put("status", list);
		return "/admin/project/editorProject";
	}

	/***
	 * 编辑现场
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminEditScene")
	public String editScene(
			@RequestParam(value = "sceneId", required = false) Integer sceneId,
			ModelMap map) {
		if (sceneId != null) {
			Scene scene = this.projectManager.getSceneDao().findById(sceneId);

			map.put("scene", scene);
		}

		return "/admin/project/editorScene";
	}

	/***
	 * 编辑活动
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminEditAction")
	public String editAction(
			@RequestParam(value = "actionId", required = false) Integer actionId,
			ModelMap map) {
		if (actionId != null) {
			Action action = this.actionManaer.findActionById(actionId);

			map.put("action", action);
		}

		return "/admin/action/editorAction";
	}

	/***
	 * 编辑活动
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminAddActionImage")
	@ResponseBody
	public Map addActionImage(
			@RequestParam(value = "actionId", required = false) Integer actionId,
			@RequestParam(value = "name", required = false) String name,
			ModelMap map) {

		Map m = new HashMap();
		if (name != null) {
			Action action = this.actionManaer.findActionById(actionId);

			Actionimages image = new Actionimages();
			image.setUrl(name);

			image.setAction(action);

			this.actionManaer.getActionImageDao().save(image);

			m.put("action", action);
			m.put("image", image.getUrl());
		}

		return m;
	}

	/***
	 * 添加现场
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminAddScene")
	public String adminAddScene(
			@RequestParam(value = "sceneId", required = false) Integer sceneId,
			@RequestParam(value = "projectId", required = false) Integer projectId,
			@RequestParam(value = "audio", required = false) MultipartFile[] audios,
			HttpSession session, ModelMap map) {

		Map m = new HashMap();
		Scene scene;
		if (sceneId != -1) {
			scene = this.projectManager.getSceneDao().findById(sceneId);
			if (projectId != null && projectId != -1) {
				Project project = this.projectManager
						.findProjectById(projectId);
				scene.setProject(project);
			}

			// 保存图片
			String fileName = "";
			String result = "";
			if (audios != null && audios.length > 0) {
				MultipartFile file = null;
				Set items = new HashSet();
				List list = new ArrayList();
				for (int i = 0; i < audios.length; i++) {
					if (audios[i] != null) {
						file = audios[i];
						fileName = String.format(
								Config.STRING_USER_AUDIO_FORMAT,
								new Date().getTime(), i);
						result = FileUtil.savePicture(file, fileName,
								"upload/audio/");
						if (!result.equals("")) {
							fileName = Config.STRING_SYSTEM_ADDRESS
									+ "upload/audio/" + result;
							list.add(fileName);
						} else {
							fileName = "";
						}

					}
				}
				if(!fileName.equals(""))
				{
					scene.setAudioPath(fileName);
				}
			}
			this.projectManager.getSceneDao().saveOrUpdate(scene);
		} else {
			scene = new Scene();
			if (projectId != null && projectId != -1) {
				Project project = this.projectManager
						.findProjectById(projectId);
				scene.setProject(project);
			}

			// 保存图片
			String fileName = "";
			String result = "";
			if (audios != null && audios.length > 0) {
				MultipartFile file = null;
				Set items = new HashSet();
				List list = new ArrayList();
				for (int i = 0; i < audios.length; i++) {
					if (audios[i] != null) {
						file = audios[i];
						fileName = String.format(
								Config.STRING_USER_AUDIO_FORMAT,
								new Date().getTime(), i);
						result = FileUtil.savePicture(file, fileName,
								"upload/audio/");
						if (!result.equals("")) {
							fileName = Config.STRING_SYSTEM_ADDRESS
									+ "upload/audio/" + result;
							list.add(fileName);
						} else {
							fileName = "";
						}

					}
				}
				scene.setAudioPath(fileName);
			}
			this.projectManager.getSceneDao().save(scene);
		}

		if (session.getAttribute("scene") != null) {
			List images = (ArrayList) session.getAttribute("scene");
			// 保存图片
			String fileName = "";
			String result = "";
			if (images != null && images.size() > 0) {

				Set items = new HashSet();
				List list = new ArrayList();
				String file;
				Audiorecord record;
				for (int i = 0; i < images.size(); i++) {
					if (images.get(i) != null) {
						file = images.get(i).toString();
						record = new Audiorecord();
						record.setImageUrl(file);
						record.setStartTime(0);
						record.setEndTime(10);
						record.setSortIndex(i);
						record.setScene(scene);

						this.projectManager.getAudioRecordDao().save(record);
					}
				}
				session.setAttribute("scene", null);
			}
		}

		map.put("scene", scene);
		return "/admin/project/editorScene";
	}
	@RequestMapping(value="admin/adminDeleteSceneAudioRecord")
	@ResponseBody
	public Map adminDeleteSceneAudioRecord(
			@RequestParam(value="recordId",required=false)Integer recordId
			)
	{
		this.result = new HashMap();
		if(recordId!=null && recordId!=-1 )
		{
			Audiorecord record = this.projectManager.getAudioRecordDao().findById(recordId);
			if(record!=null)
			{
				this.projectManager.getAudioRecordDao().delete(record);
				this.status=200;
				this.message="删除成功!";
			}
		}
		return getResult();
	}

	/***
	 * 编辑现场
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminEditCycle")
	public String editCycle(
			@RequestParam(value = "cycleId", required = false) Integer cycleId,
			HttpSession session, ModelMap map) {
		if (cycleId != null) {
			Publiccontent content = this.feelingManager.getPublicContentDao()
					.findById(cycleId);

			map.put("content", content);
		}

		List l = new ArrayList();

		for (int i = 0; i < 9; i++) {
			l.add(i);
		}

		map.put("imgCount", l);

		session.setAttribute("feeling", null);
		return "/admin/cycle/editorCycle";
	}

	/***
	 * 测试
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminIndex")
	public String index(ModelMap map) {

		return "/admin/action/index";
	}

	/***
	 * 编辑路演
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminEditRoadShow")
	public String editRoadShow(
			@RequestParam(value = "roadShowId", required = false) Integer roadShowId,
			ModelMap map) {
		if (roadShowId != null) {
			Roadshow roadshow = this.projectManager.getRoadShowDao().findById(
					roadShowId);

			map.put("roadshow", roadshow);
		}

		return "/admin/project/editorRoadShow";
	}

	/***
	 * 编辑认证信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/adminEditorUserAuthentic")
	public String editorUserAuthentic(
			@RequestParam(value = "authId", required = false) Integer authId,
			ModelMap map) {
		List areas = this.authenticManager.getIndustoryareaDao().findAll();
		List citys = this.authenticManager.getCityDao().findAll();
		List identities = this.authenticManager.getIdentitytypeDao().findAll();
		List authenticStatus = this.authenticManager.getAuthenticStatus()
				.findAll();
		if (authId != null) {
			Authentic authentic = this.authenticManager
					.findAuthenticById(authId);

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
			map.put("authentic", authentic);

		}

		map.put("identities", identities);
		map.put("areas", areas);
		map.put("cities", citys);
		map.put("status", authenticStatus);
		map.put("optional", Config.STRING_AUTH_QUALIFICATION);

		return "/admin/Users/editorUserAuthentic";
	}

	@RequestMapping(value = "/admin/adminAddUser")
	/***
	 * 添加用户
	 * @return
	 */
	public String addUser(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "platform", required = false) String platform,
			@RequestParam(value = "Str", required = false) String Str,
			@RequestParam(value = "identityTypeId", required = false) String identityTypeId,
			@RequestParam(value = "realName", required = false) String realName,
			@RequestParam(value = "identityCardA", required = false) String identityCardA,
			@RequestParam(value = "identityCardB", required = false) String identityCardB,
			@RequestParam(value = "identityCardNo", required = false) String identityCardNo,
			@RequestParam(value = "companyName", required = false) String companyName,
			@RequestParam(value = "companyAddress", required = false) String companyAddress,
			@RequestParam(value = "position", required = false) String position,
			@RequestParam(value = "bussinessNo", required = false) String bussinessNo,
			@RequestParam(value = "introduce", required = false) String introduce,
			@RequestParam(value = "companyIntroduce", required = false) String companyIntroduce,
			@RequestParam(value = "optional", required = false) String optional,
			@RequestParam(value = "areas", required = false) String areas,
			@RequestParam(value = "city", required = false) String city,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		List l = (List) session.getAttribute("images");
		Users user;

		if (userId != -1) {
			user = this.userManager.findUserById(userId);
		} else {
			user = new Users();
		}

		String encryptPassword = Tools.generatePassword(password, telephone);
		user.setName(name);
		user.setHeadSculpture(image);
		user.setTelephone(telephone);
		user.setPassword(encryptPassword);
		user.setPlatform((short) Integer.parseInt(platform));
		user.setWechatId(Str);

		// 更新头像
		if (session.getAttribute("header") != null) {
			List headers = (List) session.getAttribute("header");
			user.setHeadSculpture(headers.get(0).toString());

			session.setAttribute("header", null);

		} else {
			user.setHeadSculpture(image);
		}

		if (userId != -1) {
			// 更新
			// 生成认证信息
			Object[] objs = user.getAuthentics().toArray();
			Authentic authentic;
			if (objs != null && objs.length > 0) {
				authentic = (Authentic) objs[0];
			} else {
				authentic = new Authentic();
			}

			Identiytype type = new Identiytype();
			type.setIdentiyTypeId((short) Integer.parseInt(identityTypeId));

			// Authenticstatus status = new Authenticstatus();
			// status.setStatusId(7);
			//
			// authentic.setAuthenticstatus(status);
			authentic.setIdentiytype(type);
			authentic.setName(realName);
			authentic.setIdentiyCarNo(identityCardNo);
			authentic.setCompanyAddress(companyAddress);
			authentic.setCompanyIntroduce(companyIntroduce);
			authentic.setIntroduce(introduce);
			authentic.setIndustoryArea(areas);
			authentic.setOptional(optional);

			// 城市
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);

			// 更新身份证A面
			if (session.getAttribute("idA") != null) {
				List images = (List) session.getAttribute("idA");
				authentic.setIdentiyCarA(images.get(0).toString());

				session.setAttribute("idA", null);

			} else {
				if (!identityCardA.equals("")) {
					authentic.setIdentiyCarA(identityCardA);
				}
			}
			// 更新身份证B面
			if (session.getAttribute("idB") != null) {
				List images = (List) session.getAttribute("idB");
				authentic.setIdentiyCarB(images.get(0).toString());

				session.setAttribute("idB", null);

			} else {
				if (!identityCardB.equals("")) {
					authentic.setIdentiyCarB(identityCardB);
				}
			}

			// 保存
			this.userManager.saveOrUpdateUser(user);
		} else {
			// 生成认证信息
			Authentic authentic = new Authentic();

			Identiytype type = new Identiytype();
			type.setIdentiyTypeId((short) Integer.parseInt(identityTypeId));
			Authenticstatus status = new Authenticstatus();
			status.setStatusId(7);

			authentic.setAuthenticstatus(status);
			authentic.setIdentiytype(type);
			authentic.setName(realName);
			authentic.setIdentiyCarA(identityCardA);
			authentic.setIdentiyCarB(identityCardB);
			authentic.setIdentiyCarNo(identityCardNo);
			authentic.setCompanyName(companyName);
			authentic.setCompanyAddress(companyAddress);
			authentic.setCompanyIntroduce(companyIntroduce);
			authentic.setIntroduce(introduce);
			authentic.setIndustoryArea(areas);
			authentic.setOptional(optional);
			authentic.setPosition(position);

			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);

			// 更新身份证A面
			if (session.getAttribute("idA") != null) {
				List images = (List) session.getAttribute("idA");
				authentic.setIdentiyCarA(images.get(0).toString());

				session.setAttribute("idA", null);

			} else {
				authentic.setIdentiyCarA(identityCardA);
			}
			// 更新身份证B面
			if (session.getAttribute("idB") != null) {
				List images = (List) session.getAttribute("idB");
				authentic.setIdentiyCarA(images.get(0).toString());

				session.setAttribute("idB", null);

			} else {
				authentic.setIdentiyCarA(identityCardB);
			}

			// 保存
			this.userManager.addUser(user);
			authentic.setUsers(user);
			this.authenticManager.saveAuthentic(authentic);

			Set set = new HashSet();
			set.add(authentic);
			user.setAuthentics(set);

			// 保存
			this.userManager.saveOrUpdateUser(user);
		}
		
		List citys = this.authenticManager.getCityDao().findAll();
		List areaList = this.authenticManager.getIndustoryareaDao().findAll();
		if (userId != null) {

			Set set = user.getAuthentics();
			Object[] objs = set.toArray();

			List ll = new ArrayList();
			for (int i = 0; i < objs.length; i++) {
				Authentic authentic = (Authentic) objs[i];
				optional = authentic.getOptional();
				String area = authentic.getIndustoryArea();

				Map m = new HashMap();

				List list = new ArrayList();
				if (optional != null) {
					if (optional.equals("")) {
						list.add(0);
					} else {
						String[] tempList = optional.split(",");

						for (String s : tempList) {
							s = s.replace(" ", "");
							list.add(Integer.parseInt(s.trim()));
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
							list.add(s.trim());
						}
					}
				} else {
					list.add(0);
				}

				m.put("areas", list);

				ll.add(m);
			}

			map.put("ext", ll);
			map.put("user", user);
			map.put("authentics", set);

		}

		map.put("areas", areaList);
		map.put("cities", citys);
		map.put("optional", Config.STRING_AUTH_QUALIFICATION);
		
		return "admin/Users/editorUser";
	}

	@RequestMapping(value = "/admin/adminAddAuthentic")
	/***
	 * 添加用户
	 * @return
	 */
	public String addAuthentic(
			@RequestParam(value = "authId", required = false) Integer authId,
			@RequestParam(value = "identityTypeId", required = false) String identityTypeId,
			@RequestParam(value = "realName", required = false) String realName,
			@RequestParam(value = "identityCardA", required = false) String identityCardA,
			@RequestParam(value = "identityCardB", required = false) String identityCardB,
			@RequestParam(value = "identityCardNo", required = false) String identityCardNo,
			@RequestParam(value = "companyName", required = false) String companyName,
			@RequestParam(value = "companyAddress", required = false) String companyAddress,
			@RequestParam(value = "position", required = false) String position,
			@RequestParam(value = "bussinessNo", required = false) String bussinessNo,
			@RequestParam(value = "introduce", required = false) String introduce,
			@RequestParam(value = "companyIntroduce", required = false) String companyIntroduce,
			@RequestParam(value = "optional", required = false) String optional,
			@RequestParam(value = "areas", required = false) String areas,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "status", required = false) String status,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		List l = (List) session.getAttribute("images");
		Authentic authentic;

		if (authId != -1) {
			authentic = this.authenticManager.findAuthenticById(authId);

			// 更新
			Identiytype type = new Identiytype();
			type.setIdentiyTypeId((short) Integer.parseInt(identityTypeId));

			Authenticstatus authenticStatus = new Authenticstatus();
			authenticStatus.setStatusId(Integer.parseInt(status));

			authentic.setAuthenticstatus(authenticStatus);
			authentic.setIdentiytype(type);
			authentic.setName(realName);
			authentic.setIdentiyCarNo(identityCardNo);
			authentic.setCompanyAddress(companyAddress);
			authentic.setCompanyIntroduce(companyIntroduce);
			authentic.setIntroduce(introduce);
			authentic.setIndustoryArea(areas);
			authentic.setOptional(optional);

			// 城市
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);

			// 更新身份证A面
			if (session.getAttribute("idA") != null) {
				List images = (List) session.getAttribute("idA");
				authentic.setIdentiyCarA(images.get(0).toString());

				session.setAttribute("idA", null);

			} else {
				if (!identityCardA.equals("")) {
					authentic.setIdentiyCarA(identityCardA);
				}
			}
			// 更新身份证B面
			if (session.getAttribute("idB") != null) {
				List images = (List) session.getAttribute("idB");
				authentic.setIdentiyCarB(images.get(0).toString());

				session.setAttribute("idB", null);

			} else {
				if (!identityCardB.equals("")) {
					authentic.setIdentiyCarB(identityCardB);
				}
			}

			// 保存
			this.authenticManager.updateAuthentic(authentic);
		} else {
			authentic = new Authentic();

			// 更新
			Identiytype type = new Identiytype();
			type.setIdentiyTypeId((short) Integer.parseInt(identityTypeId));

			Authenticstatus authenticStatus = new Authenticstatus();
			authenticStatus.setStatusId(7);

			authentic.setAuthenticstatus(authenticStatus);
			authentic.setIdentiytype(type);
			authentic.setName(realName);
			authentic.setIdentiyCarNo(identityCardNo);
			authentic.setCompanyAddress(companyAddress);
			authentic.setCompanyIntroduce(companyIntroduce);
			authentic.setIntroduce(introduce);
			authentic.setIndustoryArea(areas);
			authentic.setOptional(optional);

			// 城市
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);

			// 更新身份证A面
			if (session.getAttribute("idA") != null) {
				List images = (List) session.getAttribute("idA");
				authentic.setIdentiyCarA(images.get(0).toString());

				session.setAttribute("idA", null);

			} else {
				if (!identityCardA.equals("")) {
					authentic.setIdentiyCarA(identityCardA);
				}
			}
			// 更新身份证B面
			if (session.getAttribute("idB") != null) {
				List images = (List) session.getAttribute("idB");
				authentic.setIdentiyCarB(images.get(0).toString());

				session.setAttribute("idB", null);

			} else {
				if (!identityCardB.equals("")) {
					authentic.setIdentiyCarB(identityCardB);
				}
			}

			// 保存
			this.authenticManager.saveAuthentic(authentic);

		}

		List areasList = this.authenticManager.getIndustoryareaDao().findAll();
		List citys = this.authenticManager.getCityDao().findAll();
		List identities = this.authenticManager.getIdentitytypeDao().findAll();
		List authenticStatusList = this.authenticManager.getAuthenticStatus()
				.findAll();

		optional = authentic.getOptional();
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
		map.put("authentic", authentic);

		map.put("identities", identities);
		map.put("areas", areasList);
		map.put("cities", citys);
		map.put("status", authenticStatusList);
		map.put("optional", Config.STRING_AUTH_QUALIFICATION);

		return "/admin/Users/editorUserAuthentic";
		
	}

	@RequestMapping(value = "/admin/adminAddProject")
	/***
	 * 添加项目
	 * @return
	 */
	public String addProject(
			@RequestParam(value = "projectId", required = false) Integer projectId,
			@RequestParam(value = "abbrevName", required = false) String abbrevName,
			@RequestParam(value = "fullName", required = false) String fullName,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "industory", required = false) String industory,
			@RequestParam(value = "introduce", required = false) String introduce,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "user", required = false) Integer user,
			@RequestParam(value = "financestatus", required = false) Integer financestatus,
			@RequestParam(value = "total", required = false) String total,
			@RequestParam(value = "financed", required = false) String financed,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "profit", required = false) String profit,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			ModelMap map, HttpSession session) throws Exception {
		this.result = new HashMap();
		this.result.put("data", "");

		List l = (List) session.getAttribute("images");
		Project project;

		if (projectId != -1) {
			project = this.projectManager.findProjectById(projectId);
		} else {
			project = new Project();
		}

		project.setAbbrevName(abbrevName);
		project.setFullName(fullName);
		project.setAddress(address);
		project.setIndustoryType(industory);
		project.setDescription(introduce);
		project.setStartPageImage(image);

		Financestatus status = new Financestatus();
		status.setStatusId(financestatus);

		project.setFinancestatus(status);

		Roadshow show;
		if (projectId != -1) {
			Object[] objs = project.getRoadshows().toArray();
			if (objs != null && objs.length > 0) {
				show = (Roadshow) objs[0];
			} else {
				show = new Roadshow();
			}
		} else {
			show = new Roadshow();
			// 保存项目
			this.projectManager.getProjectDao().save(project);
		}

		show.setProject(project);

		String totalStr = total.replace("万", "");
		String financedStr = financed.replace("万", "");
		String limitStr = limit.replace("万", "");

		beginTime = beginTime.replace(".0", "");
		endTime = endTime.replace(".0", "");

		Roadshowplan plan;
		if (show.getRoadshowplan() != null) {
			plan = show.getRoadshowplan();
		} else {
			plan = new Roadshowplan();
		}

		plan.setBeginDate(DateUtils.stringToDate(beginTime,
				"yyyy-MM-dd HH:mm:ss"));
		plan.setEndDate(DateUtils.stringToDate(endTime, "yyyy-MM-dd HH:mm:ss"));
		plan.setFinanceTotal(Integer.parseInt(totalStr));
		plan.setFinancedMount(Integer.parseInt(financedStr));
		plan.setLimitAmount(Double.parseDouble(limitStr));
		plan.setProfit(profit);

		Set s = new HashSet();
		s.add(show);
		plan.setRoadshows(s);

		if (show.getRoadshowplan() == null) {
			this.projectManager.getRoadShowPlanDao().save(plan);
		} else {
			this.projectManager.getRoadShowPlanDao().saveOrUpdate(plan);
		}

		show.setRoadshowplan(plan);

		if (project.getRoadshows() == null) {
			this.projectManager.getRoadShowDao().save(show);
		} else {
			this.projectManager.getRoadShowDao().saveOrUpdate(show);
		}

		Set set = new HashSet();
		set.add(show);

		project.setRoadshows(set);

		// 更新头像
		if (session.getAttribute("project") != null) {
			List headers = (List) session.getAttribute("project");
			project.setStartPageImage(headers.get(0).toString());

			session.setAttribute("project", null);

		} else {
			project.setStartPageImage(image);
		}

		this.projectManager.getProjectDao().saveOrUpdate(project);

		List<Project> list = this.projectManager.getProjectDao().findAll();
		map.put("items", list.iterator());
		return "/admin/project/projectList";
	}

	@RequestMapping(value = "/admin/adminAddRoadShow")
	/***
	 * 添加项目
	 * @return
	 */
	public String adminAddRoadShow(
			@RequestParam(value = "roadShowId", required = false) Integer roadShowId,
			@RequestParam(value = "projectId", required = false) Integer projectId,
			@RequestParam(value = "total", required = false) String total,
			@RequestParam(value = "financed", required = false) String financed,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "profit", required = false) String profit,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			ModelMap map, HttpSession session) throws Exception {
		this.result = new HashMap();
		this.result.put("data", "");

		Roadshow show;

		if (roadShowId != -1) {
			show = this.projectManager.getRoadShowDao().findById(roadShowId);
		} else {
			show = new Roadshow();
			Project project = this.projectManager.findProjectById(projectId);
			show.setProject(project);
		}

		String totalStr = total.replace("万", "");
		String financedStr = financed.replace("万", "");
		String limitStr = limit.replace("万", "");

		beginTime = beginTime.replace(".0", "");
		endTime = endTime.replace(".0", "");

		Roadshowplan plan;
		if (show.getRoadshowplan() != null) {
			plan = show.getRoadshowplan();
		} else {
			plan = new Roadshowplan();
		}

		plan.setBeginDate(DateUtils.stringToDate(beginTime,
				"yyyy-MM-dd HH:mm:ss"));
		plan.setEndDate(DateUtils.stringToDate(endTime, "yyyy-MM-dd HH:mm:ss"));
		plan.setFinanceTotal(Integer.parseInt(totalStr));
		plan.setFinancedMount(Integer.parseInt(financedStr));
		plan.setLimitAmount(Double.parseDouble(limitStr));
		plan.setProfit(profit);

		Set s = new HashSet();
		s.add(show);
		plan.setRoadshows(s);

		if (show.getRoadshowplan() == null) {
			this.projectManager.getRoadShowPlanDao().save(plan);
		} else {
			this.projectManager.getRoadShowPlanDao().saveOrUpdate(plan);
		}

		show.setRoadshowplan(plan);

		if (roadShowId == -1) {
			this.projectManager.getRoadShowDao().save(show);
		} else {
			this.projectManager.getRoadShowDao().saveOrUpdate(show);
		}

		List<Roadshow> list = this.projectManager.getRoadShowDao().findAll();
		map.put("items", list.iterator());
		return "/admin/project/roadShowList";
	}

	@RequestMapping(value = "/admin/adminAddAction")
	/***
	 * 添加项目
	 * @return
	 */
	public String addAction(
			@RequestParam(value = "actionId", required = false) Integer actionId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			ModelMap map, HttpSession session) throws Exception {
		this.result = new HashMap();
		this.result.put("data", "");

		List l = (List) session.getAttribute("images");
		Action action;

		if (actionId != -1) {
			action = this.actionManaer.findActionById(actionId);
		} else {
			action = new Action();
		}

		beginTime = beginTime.replace(".0", "");
		endTime = endTime.replace(".0", "");

		//限制人数
		limit = limit.replace("人", "");
		action.setAddress(address);
		action.setName(name);
		action.setMemberLimit((short)Integer.parseInt(limit));
		action.setDescription(description);
		action.setType((short) Integer.parseInt(type));
		action.setStartTime(DateUtils.stringToDate(beginTime,
				"yyyy-MM-dd hh:mm:ss"));
		action.setEndTime(DateUtils
				.stringToDate(endTime, "yyyy-MM-dd hh:mm:ss"));

		// 更新头像
		if (session.getAttribute("images") != null) {
			List images = (List) session.getAttribute("images");

			Set set = new HashSet();
			if (action.getActionimages() != null) {
				set = action.getActionimages();
			}

			for (int i = 0; i < images.size(); i++) {
				Actionimages items = new Actionimages();
				items.setUrl(images.get(i).toString());
				items.setAction(action);
				set.add(items);
			}

			action.setActionimages(set);
			session.setAttribute("images", null);
		}

		if (actionId != -1) {
			this.actionManaer.saveOrUpdate(action);
		} else {
			this.actionManaer.getActionDao().save(action);
		}

		map.put("action", action);
		return "/admin/action/editorAction";
	}

	@RequestMapping(value = "/admin/adminAddCycle")
	/***
	 * 添加项目
	 * @return
	 */
	public String addCycle(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "authId", required = false) Integer authId,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "publicDate", required = false) String publicDate,
			ModelMap map, HttpSession session) throws Exception {
		this.result = new HashMap();
		this.result.put("data", "");

		Publiccontent feeling;

		if (contentId != -1) {
			feeling = this.feelingManager.findPublicContentById(contentId);
		} else {
			feeling = new Publiccontent();
		}

		if (authId != null) {
			Integer userId = this.authenticManager.getAuthenticDao()
					.findUserIdByAuthId(authId);

			Users user = this.userManager.findUserById(userId);

			feeling.setUsers(user);
		}

		Date date = DateUtils.stringToDate(publicDate,
				DateUtils.DATETIME_FORMAT);
		if (date != null) {
			feeling.setPublicDate(date);
		}
		feeling.setContent(content);

		// 更新头像
		if (session.getAttribute("feeling") != null) {
			List images = (List) session.getAttribute("feeling");

			Set set = new HashSet();
			if (feeling.getContentimageses() != null) {
				set = feeling.getContentimageses();
			}

			for (int i = 0; i < images.size(); i++) {
				Contentimages items = new Contentimages();
				items.setUrl(images.get(i).toString());
				items.setPubliccontent(feeling);
				set.add(items);
			}

			feeling.setContentimageses(set);
			session.setAttribute("feeling", null);
		}

		if (contentId != -1) {
			this.feelingManager.saveOrUpdate(feeling);
		} else {
			this.feelingManager.getPublicContentDao().save(feeling);
		}

		List<Publiccontent> list = this.feelingManager.getPublicContentDao()
				.findAll();
		map.put("items", list.iterator());
		return "/admin/cycle/cycleList";
	}

	@RequestMapping(value = "/admin/adminSearchUserByName")
	@ResponseBody
	public Map searchUserByName(
			@RequestParam(value = "name", required = false) String name) {
		Map map = new HashMap();

		List<Authentic> users = this.userManager.findUserByName(name);

		map.put("data", users);
		return map;
	}

	@RequestMapping(value = "/admin/adminSearchProjectByName")
	@ResponseBody
	public Map searchProjectByName(
			@RequestParam(value = "name", required = false) String name) {
		Map map = new HashMap();

		List<Project> projects = this.projectManager.findProjectByName(name);

		map.put("data", projects);
		return map;
	}
}
