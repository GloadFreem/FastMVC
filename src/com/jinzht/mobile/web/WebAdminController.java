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
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.tools.Tools;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Actionimages;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Authenticstatus;
import com.jinzht.web.entity.Banner;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Contentimages;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Scene;
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
	
	
	@RequestMapping(value = "/admin/login")
	public String webEditor() {
		return "/admin/login";
	}
	
	
	@RequestMapping(value = "/admin/loginAction")
	public String loginAction() {
		return "/admin/index";
	}
	
	@RequestMapping(value = "/admin/dashboard")
	public String dashboard() {
		return "/admin/dashboard";
	}
	
	
	@RequestMapping(value = "/admin/adminJsp")
	public String adminJsp() {
		return "/admin/adminIndex";
	}
	
	/***
	 * top
	 * @return
	 */
	@RequestMapping(value = "/admin/top")
	public String topJSP() {
		return "/admin/top";
	}
	
	/***
	 * content
	 * @return
	 */
	@RequestMapping(value = "/admin/content")
	public String contentJSP() {
		return "/admin/content";
	}
	
	/***
	 * left
	 * @return
	 */
	@RequestMapping(value = "/admin/left")
	public String leftJSP() {
		return "/admin/left";
	}
	/***
	 * bottom
	 * @return
	 */
	@RequestMapping(value = "/admin/bottom")
	public String bottomJSP() {
		return "/admin/bottom";
	}
	
	
	
	/***
	 * banner列表
	 * @return
	 */
	@RequestMapping(value = "/admin/bannerListAdmin")
	public String bannerListAdmin(ModelMap map) {
		List<Banner> list = this.systemManger.findBannerInfoList();
		map.put("items", list.iterator());
		return "/admin/banner/bannerList";
	}
	
	
	/***
	 * banner列表
	 * @return
	 */
	@RequestMapping(value = "/admin/editBanner")
	public String editBanner(
			@RequestParam(value="bannerId",required=false)Integer bannerId,
			ModelMap map) {
		if(bannerId!=null)
		{
			Banner banner = this.systemManger.getBannerDao().findById(bannerId);
			map.put("banner", banner);
		}
		
		return "/admin/banner/editorBanner";
	}
	
	@RequestMapping(value = "/admin/uploadImage")
	@ResponseBody
	/***
	 * 上传图片
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map uploadImage(
			@RequestParam(value = "type",required = false) String type,
			@RequestParam(value = "file",required = false) MultipartFile[] images,
			HttpSession session) {
		
		session.setAttribute(type, null);
		
		
		this.result = new HashMap();
		this.result.put("data", "");
		// 保存图片
		String fileName ="";
		String result="";
		if(images!=null && images.length>0){
			
			MultipartFile file = null;
			Set items = new HashSet();
			List list = new ArrayList();
			for(int i =0;i<images.length;i++){
				if (images[i]!= null) {
					file = images[i];
					fileName = String.format(
							Config.STRING_USER_FEELING_PICTUREA_FORMAT,
							new Date().getTime(),i);
					result = FileUtil.savePicture(
							file, fileName,
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
			
			session.setAttribute(type, list);
		}
			return getResult();
	}
	
	
	@RequestMapping(value = "/admin/addBanner")
	/***
	 * 添加Banner
	 * @return
	 */
	public String addBanner(
			@RequestParam(value = "bannerId",required = false) Integer bannerId,
			@RequestParam(value = "url",required = false) String url,
			@RequestParam(value = "name",required = false)String name,
			@RequestParam(value = "image",required = false) String image,
			@RequestParam(value = "description",required = false) String description,
			ModelMap map,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		
		List l = (List) session.getAttribute("images");
		Banner banner;
		
		if(bannerId!=-1)
		{
			banner = this.systemManger.getBannerDao().findById(bannerId);
		}else{
			banner = new Banner();
		}
		
		banner.setName(name);
		banner.setUrl(url);
		banner.setDescription(description);
		
		if(image!=null && !image.equals("") && l==null || l.size()==0)
		{
			banner.setImage(image);
		}else{
			banner.setImage(l.get(0).toString());
		}
		
		if(bannerId!=-1)
		{
			this.systemManger.getBannerDao().saveOrUpdate(banner);
		}else{
			this.systemManger.getBannerDao().save(banner);
		}
		
		List<Banner> list = this.systemManger.findBannerInfoList();
		map.put("items", list.iterator());
		return "/admin/banner/bannerList";
	}
	
	/***
	 * 用户列表
	 * @return
	 */
	@RequestMapping(value = "/admin/userListAdmin")
	public String userListAdmin(ModelMap map) {
		List<Users> list = this.userManager.getUserDao().findAll();
		map.put("items", list.iterator());
		return "/admin/Users/userList";
	}
	
	/***
	 * 项目列表
	 * @return
	 */
	@RequestMapping(value = "/admin/projectListAdmin")
	public String projectListAdmin(ModelMap map) {
		List<Users> list = this.projectManager.getProjectDao().findAll();
		map.put("items", list.iterator());
		return "/admin/project/projectList";
	}
	
	/***
	 * 路演列表
	 * @return
	 */
	@RequestMapping(value = "/admin/roadShowListAdmin")
	public String roadShowListAdmin(ModelMap map) {
		List<Roadshow> list = this.projectManager.getRoadShowDao().findAll();
		map.put("items", list.iterator());
		return "/admin/project/roadShowList";
	}
	/***
	 * 现场列表
	 * @return
	 */
	@RequestMapping(value = "/admin/sceneListAdmin")
	public String sceneListAdmin(ModelMap map) {
		List<Scene> list = this.projectManager.getSceneDao().findAll();
		map.put("items", list.iterator());
		return "/admin/project/sceneList";
	}
	/***
	 * 活动列表
	 * @return
	 */
	@RequestMapping(value = "/admin/actionListAdmin")
	public String actionListAdmin(ModelMap map) {
		List<Action> list = this.actionManaer.getActionDao().findAll();
		map.put("items", list.iterator());
		return "/admin/action/actionList";
	}
	/***
	 * 圈子列表
	 * @return
	 */
	@RequestMapping(value = "/admin/cycleListAdmin")
	public String cycleListAdmin(ModelMap map) {
		List<Publiccontent> list = this.feelingManager.getPublicContentDao().findAll();
		map.put("items", list.iterator());
		return "/admin/cycle/cycleList";
	}
	
	
	/***
	 * 认证审核
	 * @return
	 */
	@RequestMapping(value = "/admin/userAuthenticListAdmin")
	public String userAuthenticListAdmin(ModelMap map) {
		List citys =this.authenticManager.getCityDao().findAll();
		List areas = this.authenticManager.getIndustoryareaDao().findAll();
		List<Authentic> list = this.userManager.getAuthenticDao().findAll();
		List authenticStatus = this.authenticManager.getAuthenticStatus().findAll();
		
		map.put("authenticStatus", authenticStatus);
		map.put("areas", areas);
		map.put("cities", citys);
		map.put("items", list.iterator());
		return "/admin/Users/userAuthenticList";
	}
	
	
	/***
	 * 编辑用户
	 * @return
	 */
	@RequestMapping(value = "/admin/editUser")
	public String editUser(
			@RequestParam(value="userId",required=false)Integer userId,
			ModelMap map) {
		List areas = this.authenticManager.getIndustoryareaDao().findAll();
		List citys =this.authenticManager.getCityDao().findAll();
		if(userId!=null)
		{
			Users user = this.userManager.findUserById(userId);
			
			Set set = user.getAuthentics();
			Object[] objs = set.toArray();
			
			List l = new ArrayList(); 
			for(int i =0;i<objs.length;i++)
			{
				Authentic authentic = (Authentic) objs[i];
				String optional = authentic.getOptional();
				String area = authentic.getIndustoryArea();
				
				Map m = new HashMap();
				
				List list =new ArrayList();
				if(optional!=null)
				{
					if(optional.equals(""))
					{
						list.add(0);
					}else{
						String[] tempList = optional.split(",");
						
						for(String s : tempList)
						{
							list.add(Integer.parseInt(s.trim()));
						}
					}
				}else{
					list.add(0);
				}
				
				m.put("option", list);
				
				list =new ArrayList();
				if(area!=null)
				{
					if(area.equals(""))
					{
						list.add(0);
					}else{
						String[] tempList = area.split(",");
						
						for(String s : tempList)
						{
							list.add(s.trim());
						}
					}
				}else{
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
	 * @return
	 */
	@RequestMapping(value = "/admin/editProject")
	public String editProject(
			@RequestParam(value="projectId",required=false)Integer projectId,
			ModelMap map) {
		if(projectId!=null)
		{
			Project project = this.projectManager.findProjectById(projectId);
			
			map.put("project", project);
		}
		
		
		return "/admin/project/editorProject";
	}
	/***
	 * 编辑现场
	 * @return
	 */
	@RequestMapping(value = "/admin/editScene")
	public String editScene(
			@RequestParam(value="sceneId",required=false)Integer sceneId,
			ModelMap map) {
		if(sceneId!=null)
		{
			Scene scene = this.projectManager.getSceneDao().findById(sceneId);
			
			map.put("scene", scene);
		}
		
		return "/admin/project/editorScene";
	}
	/***
	 * 编辑活动
	 * @return
	 */
	@RequestMapping(value = "/admin/editAction")
	public String editAction(
			@RequestParam(value="actionId",required=false)Integer actionId,
			ModelMap map) {
		if(actionId!=null)
		{
			Action action = this.actionManaer.findActionById(actionId);
			
			map.put("action", action);
		}
		
		return "/admin/action/editorAction";
	}
	/***
	 * 编辑活动
	 * @return
	 */
	@RequestMapping(value = "/admin/addActionImage")
	@ResponseBody
	public Map addActionImage(
			@RequestParam(value="actionId",required=false)Integer actionId,
			@RequestParam(value="name",required=false)String name,
			ModelMap map) {
		
		Map m = new HashMap();
		if(name!=null)
		{
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
	 * 编辑现场
	 * @return
	 */
	@RequestMapping(value = "/admin/editCycle")
	public String editCycle(
			@RequestParam(value="cycleId",required=false)Integer cycleId,
			ModelMap map) {
		if(cycleId!=null)
		{
			Publiccontent content = this.feelingManager.getPublicContentDao().findById(cycleId);
			
			map.put("content", content);
		}
		
		return "/admin/cycle/editorCycle";
	}
	/***
	 * 测试 
	 * @return
	 */
	@RequestMapping(value = "/admin/index")
	public String index(
			ModelMap map) {

		return "/admin/action/index";
	}
	/***
	 * 编辑路演
	 * @return
	 */
	@RequestMapping(value = "/admin/editRoadShow")
	public String editRoadShow(
			@RequestParam(value="roadShowId",required=false)Integer roadShowId,
			ModelMap map) {
		List areas = this.authenticManager.getIndustoryareaDao().findAll();
		List citys =this.authenticManager.getCityDao().findAll();
		if(roadShowId!=null)
		{
			Roadshow roadshow = this.projectManager.getRoadShowDao().findById(roadShowId);
			
			map.put("roadshow", roadshow);
		}
		
		map.put("areas", areas);
		map.put("cities", citys);
		
		return "/admin/project/editorRoadShow";
	}
	/***
	 * 编辑认证信息
	 * @return
	 */
	@RequestMapping(value = "/admin/editorUserAuthentic")
	public String editorUserAuthentic(
			@RequestParam(value="authId",required=false)Integer authId,
			ModelMap map) {
		List areas = this.authenticManager.getIndustoryareaDao().findAll();
		List citys =this.authenticManager.getCityDao().findAll();
		List identities = this.authenticManager.getIdentitytypeDao().findAll();
		List authenticStatus = this.authenticManager.getAuthenticStatus().findAll();
		if(authId!=null)
		{
			Authentic authentic = this.authenticManager.findAuthenticById(authId);
			
			
			String optional = authentic.getOptional();
			String area = authentic.getIndustoryArea();
			
			Map m = new HashMap();
			
			List list =new ArrayList();
			if(!optional.equals(null))
			{
				if(optional.equals(""))
				{
					list.add(0);
				}else{
					String[] tempList = optional.split(",");
					
					for(String s : tempList)
					{
						list.add(s);
					}
				}
			}else{
				list.add(0);
			}
			
			m.put("option", list);
			
			list =new ArrayList();
			if(!area.equals(null))
			{
				if(area.equals(""))
				{
					list.add(0);
				}else{
					String[] tempList = area.split(",");
					
					for(String s : tempList)
					{
						list.add(s);
					}
				}
			}else{
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
	
	@RequestMapping(value = "/admin/addUser")
	/***
	 * 添加用户
	 * @return
	 */
	public String addUser(
			@RequestParam(value = "userId",required = false) Integer userId,
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "telephone",required = false)String telephone,
			@RequestParam(value = "password",required = false) String password,
			@RequestParam(value = "image",required = false) String image,
			@RequestParam(value = "platform",required = false) String platform,
			@RequestParam(value = "Str",required = false) String Str,
			@RequestParam(value = "identityTypeId",required = false) String identityTypeId,
			@RequestParam(value = "realName",required = false) String realName,
			@RequestParam(value = "identityCardA",required = false) String identityCardA,
			@RequestParam(value = "identityCardB",required = false) String identityCardB,
			@RequestParam(value = "identityCardNo",required = false) String identityCardNo,
			@RequestParam(value = "companyName",required = false) String companyName,
			@RequestParam(value = "companyAddress",required = false) String companyAddress,
			@RequestParam(value = "position",required = false) String position,
			@RequestParam(value = "bussinessNo",required = false) String bussinessNo,
			@RequestParam(value = "introduce",required = false) String introduce,
			@RequestParam(value = "companyIntroduce",required = false) String companyIntroduce,
			@RequestParam(value = "optional",required = false) String optional,
			@RequestParam(value = "areas",required = false) String areas,
			@RequestParam(value = "city",required = false) String city,
			ModelMap map,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		
		List l = (List) session.getAttribute("images");
		Users user;
		
		if(userId!=-1)
		{
			user = this.userManager.findUserById(userId);
		}else{
			user = new Users();
		}
		
		String encryptPassword = Tools.generatePassword(password, telephone);
		user.setName(name);
		user.setHeadSculpture(image);
		user.setTelephone(telephone);
		user.setPassword(encryptPassword);
		user.setPlatform((short) Integer.parseInt(platform));
		user.setWechatId(Str);
		
		//更新头像
		if(session.getAttribute("header")!=null)
		{
			List  headers = (List) session.getAttribute("header");
			user.setHeadSculpture(headers.get(0).toString());
			
			session.setAttribute("header", null);
			
		}else{
			user.setHeadSculpture(image);
		}
		
		
		if(userId!=-1)
		{
			//更新
			//生成认证信息
			Object[] objs = user.getAuthentics().toArray();
			Authentic authentic;
			if(objs!=null && objs.length>0)
			{
				authentic = (Authentic)objs[0];
			}else{
				authentic = new Authentic();
			}
			
			
			Identiytype  type = new Identiytype();
			type.setIdentiyTypeId((short)Integer.parseInt(identityTypeId));
			
//			Authenticstatus status = new Authenticstatus();
//			status.setStatusId(7);
//			
//			authentic.setAuthenticstatus(status);
			authentic.setIdentiytype(type);
			authentic.setName(realName);
			authentic.setIdentiyCarNo(identityCardNo);
			authentic.setCompanyAddress(companyAddress);
			authentic.setCompanyIntroduce(companyIntroduce);
			authentic.setIntroduce(introduce);
			authentic.setIndustoryArea(areas);
			authentic.setOptional(optional);
			
			//城市
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);
			
			//更新身份证A面
			if(session.getAttribute("idA")!=null)
			{
				List  images = (List) session.getAttribute("idA");
				authentic.setIdentiyCarA(images.get(0).toString());
				
				session.setAttribute("idA", null);
				
			}else{
				if(!identityCardA.equals(""))
				{
					authentic.setIdentiyCarA(identityCardA);
				}
			}
			//更新身份证B面
			if(session.getAttribute("idB")!=null)
			{
				List  images = (List) session.getAttribute("idB");
				authentic.setIdentiyCarB(images.get(0).toString());
				
				session.setAttribute("idB", null);
				
			}else{
				if(!identityCardB.equals(""))
				{
					authentic.setIdentiyCarB(identityCardB);
				}
			}
			
			//保存
			this.userManager.saveOrUpdateUser(user);
		}else{
			//生成认证信息
			Authentic authentic = new Authentic();
			
			Identiytype  type = new Identiytype();
			type.setIdentiyTypeId((short)Integer.parseInt(identityTypeId));
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
			authentic.setUsers(user);
			
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);
			
			
			//更新身份证A面
			if(session.getAttribute("idA")!=null)
			{
				List  images = (List) session.getAttribute("idA");
				authentic.setIdentiyCarA(images.get(0).toString());
				
				session.setAttribute("idA", null);
				
			}else{
				authentic.setIdentiyCarA(identityCardA);
			}
			//更新身份证B面
			if(session.getAttribute("idB")!=null)
			{
				List  images = (List) session.getAttribute("idB");
				authentic.setIdentiyCarA(images.get(0).toString());
				
				session.setAttribute("idB", null);
				
			}else{
				authentic.setIdentiyCarA(identityCardB);
			}
			
			Set set = new HashSet();
			set.add(authentic);
			
			user.setAuthentics(set);
			
			//保存
			this.userManager.addUser(user);
		}
		
		List<Users> list = this.userManager.getUserDao().findAll();
		map.put("items", list.iterator());
		return "/admin/Users/userList";
	}
	@RequestMapping(value = "/admin/addAuthentic")
	/***
	 * 添加用户
	 * @return
	 */
	public String addAuthentic(
			@RequestParam(value = "authId",required = false) Integer authId,
			@RequestParam(value = "identityTypeId",required = false) String identityTypeId,
			@RequestParam(value = "realName",required = false) String realName,
			@RequestParam(value = "identityCardA",required = false) String identityCardA,
			@RequestParam(value = "identityCardB",required = false) String identityCardB,
			@RequestParam(value = "identityCardNo",required = false) String identityCardNo,
			@RequestParam(value = "companyName",required = false) String companyName,
			@RequestParam(value = "companyAddress",required = false) String companyAddress,
			@RequestParam(value = "position",required = false) String position,
			@RequestParam(value = "bussinessNo",required = false) String bussinessNo,
			@RequestParam(value = "introduce",required = false) String introduce,
			@RequestParam(value = "companyIntroduce",required = false) String companyIntroduce,
			@RequestParam(value = "optional",required = false) String optional,
			@RequestParam(value = "areas",required = false) String areas,
			@RequestParam(value = "city",required = false) String city,
			@RequestParam(value = "status",required = false) String status,
			ModelMap map,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		
		List l = (List) session.getAttribute("images");
		Authentic authentic;
		
		if(authId!=-1)
		{
			authentic = this.authenticManager.findAuthenticById(authId);
			
			//更新
			Identiytype  type = new Identiytype();
			type.setIdentiyTypeId((short)Integer.parseInt(identityTypeId));
			
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
			
			//城市
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);
			
			//更新身份证A面
			if(session.getAttribute("idA")!=null)
			{
				List  images = (List) session.getAttribute("idA");
				authentic.setIdentiyCarA(images.get(0).toString());
				
				session.setAttribute("idA", null);
				
			}else{
				if(!identityCardA.equals(""))
				{
					authentic.setIdentiyCarA(identityCardA);
				}
			}
			//更新身份证B面
			if(session.getAttribute("idB")!=null)
			{
				List  images = (List) session.getAttribute("idB");
				authentic.setIdentiyCarB(images.get(0).toString());
				
				session.setAttribute("idB", null);
				
			}else{
				if(!identityCardB.equals(""))
				{
					authentic.setIdentiyCarB(identityCardB);
				}
			}
			
			//保存
			this.authenticManager.updateAuthentic(authentic);
		}else{
			authentic = new Authentic();
			
			//更新
			Identiytype  type = new Identiytype();
			type.setIdentiyTypeId((short)Integer.parseInt(identityTypeId));
			
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
			
			//城市
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);
			
			//更新身份证A面
			if(session.getAttribute("idA")!=null)
			{
				List  images = (List) session.getAttribute("idA");
				authentic.setIdentiyCarA(images.get(0).toString());
				
				session.setAttribute("idA", null);
				
			}else{
				if(!identityCardA.equals(""))
				{
					authentic.setIdentiyCarA(identityCardA);
				}
			}
			//更新身份证B面
			if(session.getAttribute("idB")!=null)
			{
				List  images = (List) session.getAttribute("idB");
				authentic.setIdentiyCarB(images.get(0).toString());
				
				session.setAttribute("idB", null);
				
			}else{
				if(!identityCardB.equals(""))
				{
					authentic.setIdentiyCarB(identityCardB);
				}
			}
			
			//保存
			this.authenticManager.saveAuthentic(authentic);
			
		}
		
		
		List list = this.authenticManager.getAuthenticDao().findAll();
		map.put("items", list.iterator());
		return "/admin/Users/userAuthenticList";
	}
	@RequestMapping(value = "/admin/addProject")
	/***
	 * 添加项目
	 * @return
	 */
	public String addProject(
			@RequestParam(value = "userId",required = false) Integer userId,
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "telephone",required = false)String telephone,
			@RequestParam(value = "password",required = false) String password,
			@RequestParam(value = "image",required = false) String image,
			@RequestParam(value = "platform",required = false) String platform,
			@RequestParam(value = "Str",required = false) String Str,
			@RequestParam(value = "identityTypeId",required = false) String identityTypeId,
			@RequestParam(value = "realName",required = false) String realName,
			@RequestParam(value = "identityCardA",required = false) String identityCardA,
			@RequestParam(value = "identityCardB",required = false) String identityCardB,
			@RequestParam(value = "identityCardNo",required = false) String identityCardNo,
			@RequestParam(value = "companyName",required = false) String companyName,
			@RequestParam(value = "companyAddress",required = false) String companyAddress,
			@RequestParam(value = "position",required = false) String position,
			@RequestParam(value = "bussinessNo",required = false) String bussinessNo,
			@RequestParam(value = "introduce",required = false) String introduce,
			@RequestParam(value = "companyIntroduce",required = false) String companyIntroduce,
			@RequestParam(value = "optional",required = false) String optional,
			@RequestParam(value = "areas",required = false) String areas,
			@RequestParam(value = "city",required = false) String city,
			ModelMap map,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		
		List l = (List) session.getAttribute("images");
		Users user;
		
		if(userId!=-1)
		{
			user = this.userManager.findUserById(userId);
		}else{
			user = new Users();
		}
		
		String encryptPassword = Tools.generatePassword(password, telephone);
		user.setName(name);
		user.setHeadSculpture(image);
		user.setTelephone(telephone);
		user.setPassword(encryptPassword);
		user.setPlatform((short) Integer.parseInt(platform));
		user.setWechatId(Str);
		
		//更新头像
		if(session.getAttribute("header")!=null)
		{
			List  headers = (List) session.getAttribute("header");
			user.setHeadSculpture(headers.get(0).toString());
			
			session.setAttribute("header", null);
			
		}else{
			user.setHeadSculpture(image);
		}
		
		
		if(userId!=-1)
		{
			//更新
			//生成认证信息
			Object[] objs = user.getAuthentics().toArray();
			Authentic authentic;
			if(objs!=null && objs.length>0)
			{
				authentic = (Authentic)objs[0];
			}else{
				authentic = new Authentic();
			}
			
			
			Identiytype  type = new Identiytype();
			type.setIdentiyTypeId((short)Integer.parseInt(identityTypeId));
			
			Authenticstatus status = new Authenticstatus();
			status.setStatusId(7);
			
			authentic.setAuthenticstatus(status);
			authentic.setIdentiytype(type);
			authentic.setName(realName);
			authentic.setIdentiyCarNo(identityCardNo);
			authentic.setCompanyAddress(companyAddress);
			authentic.setCompanyIntroduce(companyIntroduce);
			authentic.setIntroduce(introduce);
			authentic.setIndustoryArea(areas);
			authentic.setOptional(optional);
			
			//城市
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);
			
			//更新身份证A面
			if(session.getAttribute("idA")!=null)
			{
				List  images = (List) session.getAttribute("idA");
				authentic.setIdentiyCarA(images.get(0).toString());
				
				session.setAttribute("idA", null);
				
			}else{
				if(!identityCardA.equals(""))
				{
					authentic.setIdentiyCarA(identityCardA);
				}
			}
			//更新身份证B面
			if(session.getAttribute("idB")!=null)
			{
				List  images = (List) session.getAttribute("idB");
				authentic.setIdentiyCarB(images.get(0).toString());
				
				session.setAttribute("idB", null);
				
			}else{
				if(!identityCardB.equals(""))
				{
					authentic.setIdentiyCarB(identityCardB);
				}
			}
			
			//保存
			this.userManager.saveOrUpdateUser(user);
		}else{
			//生成认证信息
			Authentic authentic = new Authentic();
			
			Identiytype  type = new Identiytype();
			type.setIdentiyTypeId((short)Integer.parseInt(identityTypeId));
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
			authentic.setUsers(user);
			
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);
			
			
			//更新身份证A面
			if(session.getAttribute("idA")!=null)
			{
				List  images = (List) session.getAttribute("idA");
				authentic.setIdentiyCarA(images.get(0).toString());
				
				session.setAttribute("idA", null);
				
			}else{
				authentic.setIdentiyCarA(identityCardA);
			}
			//更新身份证B面
			if(session.getAttribute("idB")!=null)
			{
				List  images = (List) session.getAttribute("idB");
				authentic.setIdentiyCarA(images.get(0).toString());
				
				session.setAttribute("idB", null);
				
			}else{
				authentic.setIdentiyCarA(identityCardB);
			}
			
			Set set = new HashSet();
			set.add(authentic);
			
			user.setAuthentics(set);
			
			//保存
			this.userManager.addUser(user);
		}
		
		List<Users> list = this.userManager.getUserDao().findAll();
		map.put("items", list.iterator());
		return "/admin/Users/userList";
	}
	@RequestMapping(value = "/admin/addAction")
	/***
	 * 添加项目
	 * @return
	 */
	public String addAction(
			@RequestParam(value = "actionId",required = false) Integer actionId,
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "description",required = false)String description,
			@RequestParam(value = "address",required = false) String address,
			@RequestParam(value = "type",required = false) String type,
			@RequestParam(value = "beginTime",required = false) String beginTime,
			@RequestParam(value = "endTime",required = false) String endTime,
			ModelMap map,
			HttpSession session) throws  Exception {
		this.result = new HashMap();
		this.result.put("data", "");
		
		List l = (List) session.getAttribute("images");
		Action action;
		
		if(actionId!=-1)
		{
			action = this.actionManaer.findActionById(actionId);
		}else{
			action = new Action();
		}
    
		
		action.setAddress(address);
		action.setName(name);
		action.setDescription(description);
		action.setType((short)Integer.parseInt(type));
		action.setStartTime(DateUtils.stringToDate(beginTime, "yyyy-MM-dd"));
		action.setEndTime(DateUtils.stringToDate(endTime, "yyyy-MM-dd"));
		
		//更新头像
		if(session.getAttribute("images")!=null)
		{
			List  images = (List) session.getAttribute("images");
			
			Set set = new HashSet();
			if(action.getActionimages()!=null)
			{
				set = action.getActionimages();
			}
			
			for(int i =0;i<images.size();i++)
			{
				Actionimages items = new Actionimages();
				items.setUrl(images.get(i).toString());
				items.setAction(action);
				set.add(items);
			}
			
			action.setActionimages(set);
			session.setAttribute("images", null);
		}
		
		if(actionId!=-1)
		{
			this.actionManaer.saveOrUpdate(action);
		}else{
			this.actionManaer.getActionDao().save(action);
		}
		
		
		List<Action> list = this.actionManaer.getActionDao().findAll();
		map.put("items", list.iterator());
		return "/admin/action/actionList";
	}
}
