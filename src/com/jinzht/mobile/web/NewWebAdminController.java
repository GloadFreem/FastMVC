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
import com.jinzht.tools.MailUtil;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.tools.PushContentType;
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
import com.jinzht.web.entity.Businessplan;
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
import com.jinzht.web.entity.Systemmessage;
import com.jinzht.web.entity.Systemuser;
import com.jinzht.web.entity.Team;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Webcontenttype;
import com.jinzht.web.entity.Weburlrecord;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.ActionManager;
import com.jinzht.web.manager.AuthenticManager;
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
public class NewWebAdminController extends BaseController {
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
	@Autowired
	private MessageMananger messageManager;
	@Autowired
	private MainManager mainManager;
	@Autowired
	private ImManager imManager;

	/*
	 * @RequestMapping(value = "/newSystem/adminLogin") public String
	 * webEditor() { return "/newSystem/login"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminLoginAction") public String
	 * loginAction(
	 * 
	 * @RequestParam(value = "name", required = false) String name,
	 * 
	 * @RequestParam(value = "password", required = false) String password,
	 * HttpSession session, ModelMap map) { if (name == null || password ==
	 * null) { map.put("tip", "账号或密码不能为空!"); return "/newSystem/login"; } Object
	 * userId = session.getAttribute("userId"); password =
	 * MD5.GetMD5Code(password);
	 * 
	 * List list = this.userManager.getSystemUserDao().findByName(name); if
	 * (list != null && list.size() > 0) { Systemuser user = (Systemuser)
	 * list.get(0); if (user.getAccount().equals(name) &&
	 * user.getPassword().equals(password)) { session.setAttribute("userId",
	 * user.getUserId()); } else { map.put("tip", "账号或密码错误，请检查后重试！"); return
	 * "/newSystem/login"; } } else { map.put("tip", "该账号不存在，请联系管理人员!"); return
	 * "/newSystem/login"; }
	 * 
	 * map.put("tip", ""); // session.setAttribute("userId", null); return
	 * "/newSystem/adminIndex"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminDashboard") public String
	 * dashboard(ModelMap map) { List<Banner> list =
	 * this.systemManger.findBannerInfoList(); map.put("items",
	 * list.iterator()); return "/newSystem/dashboard"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminJsp") public String adminJsp() {
	 * return "/newSystem/index"; }
	 *//***
	 * top
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminTop") public String topJSP() {
	 * return "/newSystem/top"; }
	 *//***
	 * content
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminContent") public String
	 * contentJSP() { return "/newSystem/content"; }
	 *//***
	 * left
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminLeft") public String leftJSP() {
	 * return "/newSystem/left"; }
	 *//***
	 * bottom
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminBottom") public String
	 * bottomJSP() { return "/newSystem/bottom"; }
	 *//***
	 * banner列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminBannerListAdmin") public String
	 * bannerListAdmin(
	 * 
	 * @RequestParam(value = "start", required = false) Integer start,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (start == null) { start = 0; }
	 * 
	 * if (size == null) { size = 10; }
	 * 
	 * List<Banner> list = this.systemManger.findBannerInfoByPageList(start,
	 * size); map.put("items", list.iterator()); map.put("count", 2000);
	 * map.put("page", 1); return "/newSystem/banner/bannerList"; }
	 *//***
	 * 消息列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminPushListAdmin") public String
	 * adminPushListAdmin(ModelMap map) { List<Systemmessage> list =
	 * this.systemManger.getSystemMessageDao() .findAll(); map.put("items",
	 * list.iterator()); return "/newSystem/push/pushList"; }
	 *//***
	 * banner列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditBanner") public String
	 * editBanner(
	 * 
	 * @RequestParam(value = "bannerId", required = false) Integer bannerId,
	 * ModelMap map) { if (bannerId != null) { Banner banner =
	 * this.systemManger.getBannerDao().findById(bannerId); map.put("banner",
	 * banner); }
	 * 
	 * return "/newSystem/banner/editorBanner"; }
	 *//***
	 * 核心成员
	 * 
	 * @param map
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminMembersListAdmin") public String
	 * adminMembersListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; }
	 * 
	 * Integer count = this.projectManager.getMemberDao().countOfAllUsers();
	 * List<Member> list = this.projectManager.getMemberDao().findByPage(page,
	 * size); size = count / size; List pageSize = new ArrayList(); int index =
	 * 1; if (size > 10 && page > 10) { index = page - 5; int d = 1; for (int i
	 * = index; i <= page + 4; i++) { if (i > size) { break; } pageSize.add(i);
	 * } } else { if (size > 10 && page <= 10) { int d = 0; for (int i = index;
	 * i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); return "/newSystem/project/membersList"; }
	 *//***
	 * 团队成员
	 * 
	 * @param map
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminTeamsListAdmin") public String
	 * adminTeamsListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; }
	 * 
	 * Integer count = this.projectManager.getTeamDao().countOfAllUsers();
	 * List<Team> list = this.projectManager.getTeamDao().findByPage(page,
	 * size); size = count / size; List pageSize = new ArrayList(); int index =
	 * 1; if (size > 10 && page > 10) { index = page - 5; int d = 1; for (int i
	 * = index; i <= page + 4; i++) { if (i > size) { break; } pageSize.add(i);
	 * } } else { if (size > 10 && page <= 10) { int d = 0; for (int i = index;
	 * i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page); map.put("size",
	 * size); map.put("count", count); map.put("pageItem", pageSize); return
	 * "/newSystem/project/teamsList"; }
	 *//***
	 * 编辑成员
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditMember") public String
	 * adminEditMember(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * ModelMap map) { if (contentId != null) { Member member =
	 * this.projectManager.getMemberDao().findById( contentId);
	 * map.put("member", member); }
	 * 
	 * return "/newSystem/project/editorMember"; }
	 *//***
	 * 编辑团队
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditTeam") public String
	 * adminEditTeam(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * ModelMap map) { if (contentId != null) { Team member =
	 * this.projectManager.getTeamDao().findById(contentId); map.put("member",
	 * member); }
	 * 
	 * return "/newSystem/project/editorTeam"; }
	 *//***
	 * 编辑消息
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditPush") public String
	 * adminEditPush(
	 * 
	 * @RequestParam(value = "pushId", required = false) Integer pushId,
	 * ModelMap map) { if (pushId != null) { Systemmessage message =
	 * this.systemManger.getSystemMessageDao() .findById(pushId);
	 * map.put("message", message); }
	 * 
	 * return "/newSystem/push/editorPush"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminUploadImage")
	 * 
	 * @ResponseBody
	 *//***
	 * 上传图片
	 * 
	 * @param page
	 *            当前页
	 * @param session
	 * @return
	 */
	/*
	 * public Map uploadImage(
	 * 
	 * @RequestParam(value = "flag", required = false) String type,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile[] images,
	 * HttpSession session) {
	 * 
	 * List list; list = new ArrayList(); if (session.getAttribute(type) !=
	 * null) { session.setAttribute(type, null); // list = (ArrayList)
	 * session.getAttribute(type); }
	 * 
	 * // session.setAttribute(type, null);
	 * 
	 * this.result = new HashMap(); this.result.put("data", ""); // 保存图片 String
	 * fileName = ""; String result = ""; if (images != null && images.length >
	 * 0) {
	 * 
	 * MultipartFile file = null; Set items = new HashSet(); for (int i = 0; i <
	 * images.length; i++) { if (images[i] != null) { file = images[i]; fileName
	 * = String.format( Config.STRING_USER_FEELING_PICTUREA_FORMAT, new
	 * Date().getTime(), i); result = FileUtil.savePicture(file, fileName,
	 * "upload/uploadImages/"); if (!result.equals("")) { fileName =
	 * Config.STRING_SYSTEM_ADDRESS + "upload/uploadImages/" + result;
	 * list.add(fileName); } else { fileName = ""; }
	 * 
	 * } } this.result.put("data", fileName); session.setAttribute(type, list);
	 * } return getResult(); }
	 * 
	 * @RequestMapping(value = "/newSystem/adminUploadAudio")
	 * 
	 * @ResponseBody
	 *//***
	 * 上传音频
	 * 
	 * @param session
	 * @return
	 */
	/*
	 * public Map adminUploadAudio(
	 * 
	 * @RequestParam(value = "type", required = false) String type,
	 * 
	 * @RequestParam(value = "audio", required = false) MultipartFile[] audios,
	 * HttpSession session) {
	 * 
	 * session.setAttribute(type, null);
	 * 
	 * this.result = new HashMap(); this.result.put("data", ""); // 保存图片 String
	 * fileName = ""; String result = ""; if (audios != null && audios.length >
	 * 0) {
	 * 
	 * MultipartFile file = null; Set items = new HashSet(); List list = new
	 * ArrayList(); for (int i = 0; i < audios.length; i++) { if (audios[i] !=
	 * null) { file = audios[i]; fileName =
	 * String.format(Config.STRING_USER_AUDIO_FORMAT, new Date().getTime(), i);
	 * result = FileUtil.savePicture(file, fileName, "upload/audios/"); if
	 * (!result.equals("")) { fileName = Config.STRING_SYSTEM_ADDRESS +
	 * "upload/audios/" + result; list.add(fileName); } else { fileName = ""; }
	 * } } this.result.put("data", fileName); session.setAttribute(type, list);
	 * } return getResult(); }
	 * 
	 * @RequestMapping(value = "/newSystem/adminUploadFeelingImage")
	 * 
	 * @ResponseBody
	 *//***
	 * 上传图片
	 * 
	 * @param page
	 *            当前页
	 * @param session
	 * @return
	 */
	/*
	 * public Map uploadFeelingImage(
	 * 
	 * @RequestParam(value = "type", required = false) String type,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile[] images,
	 * HttpSession session) {
	 * 
	 * session.setAttribute(type, null);
	 * 
	 * this.result = new HashMap(); this.result.put("data", ""); // 保存图片 String
	 * fileName = ""; String result = ""; if (images != null && images.length >
	 * 0) {
	 * 
	 * MultipartFile file = null; Set items = new HashSet(); List list = new
	 * ArrayList(); for (int i = 0; i < images.length; i++) { if (images[i] !=
	 * null) { file = images[i]; fileName = String.format(
	 * Config.STRING_USER_FEELING_PICTUREA_FORMAT, new Date().getTime(), i);
	 * result = FileUtil.savePicture(file, fileName, "upload/feelingImages/");
	 * if (!result.equals("")) { fileName = Config.STRING_SYSTEM_ADDRESS +
	 * "upload/feelingImages/" + result; list.add(fileName); } else { fileName =
	 * ""; }
	 * 
	 * } }
	 * 
	 * session.setAttribute(type, list); } return getResult(); }
	 * 
	 * @RequestMapping(value = "/newSystem/adminAddBanner")
	 *//***
	 * 添加Banner
	 * 
	 * @return
	 */
	/*
	 * public String addBanner(
	 * 
	 * @RequestParam(value = "bannerId", required = false) Integer bannerId,
	 * 
	 * @RequestParam(value = "url", required = false) String url,
	 * 
	 * @RequestParam(value = "name", required = false) String name,
	 * 
	 * @RequestParam(value = "image", required = false) String image,
	 * 
	 * @RequestParam(value = "description", required = false) String
	 * description, ModelMap map, HttpSession session) { this.result = new
	 * HashMap(); this.result.put("data", "");
	 * 
	 * List l = (List) session.getAttribute("images"); Banner banner;
	 * 
	 * if (bannerId != -1) { banner =
	 * this.systemManger.getBannerDao().findById(bannerId); } else { banner =
	 * new Banner(); }
	 * 
	 * banner.setName(name); banner.setUrl(url);
	 * banner.setDescription(description);
	 * 
	 * if (image != null && !image.equals("") && l == null || l.size() == 0) {
	 * banner.setImage(image); } else { banner.setImage(l.get(0).toString()); }
	 * 
	 * if (bannerId != -1) {
	 * this.systemManger.getBannerDao().saveOrUpdate(banner); } else {
	 * this.systemManger.getBannerDao().save(banner); }
	 * 
	 * List<Banner> list = this.systemManger.findBannerInfoList();
	 * map.put("items", list.iterator()); return "/newSystem/banner/bannerList";
	 * }
	 *//***
	 * 用户列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminUserListAdmin") public String
	 * userListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } Integer count =
	 * this.userManager.getUserDao().countOfAllUsers(); List<Users> list =
	 * this.userManager.getUserDao().findByPage(page, size); size = count /
	 * size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); return "/newSystem/Users/userList"; }
	 *//***
	 * 资讯列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminContentsListAdmin") public
	 * String adminContentsListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; }
	 * 
	 * Integer count = this.systemManger.getWebUrlRecordDao()
	 * .countOfAllUsers(); List list =
	 * this.systemManger.getWebUrlRecordDao().findByPage(page, size); size =
	 * count / size; List pageSize = new ArrayList(); int index = 1; if (size >
	 * 10 && page > 10) { index = page - 5; int d = 1; for (int i = index; i <=
	 * page + 4; i++) { if (i > size) { break; } pageSize.add(i); } } else { if
	 * (size > 10 && page <= 10) { int d = 0; for (int i = index; i <= size;
	 * i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); return "/newSystem/banner/webContentList"; }
	 *//***
	 * 项目列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminProjectListAdmin") public String
	 * projectListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } Integer count =
	 * this.projectManager.getProjectDao().countOfAllUsers(); List<Users> list =
	 * this.projectManager.getProjectDao().findByPage(page, size); size = count
	 * / size; List pageSize = new ArrayList(); int index = 1; if (size > 10 &&
	 * page > 10) { index = page - 5; int d = 1; for (int i = index; i <= page +
	 * 4; i++) { if (i > size) { break; } pageSize.add(i); } } else { if (size >
	 * 10 && page <= 10) { int d = 0; for (int i = index; i <= size; i++) { if
	 * (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page); map.put("size",
	 * size); map.put("count", count); map.put("pageItem", pageSize); return
	 * "/newSystem/project/projectList"; }
	 *//***
	 * 财务状况列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminFinancestandingListAdmin")
	 * public String adminFinancestandingListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; }
	 * 
	 * Integer count = this.projectManager.getFinanceStandingDao()
	 * .countOfAllUsers(); List<Financialstanding> list = this.projectManager
	 * .getFinanceStandingDao().findByPage(page, size); size = count / size;
	 * List pageSize = new ArrayList(); int index = 1; if (size > 10 && page >
	 * 10) { index = page - 5; int d = 1; for (int i = index; i <= page + 4;
	 * i++) { if (i > size) { break; } pageSize.add(i); } } else { if (size > 10
	 * && page <= 10) { int d = 0; for (int i = index; i <= size; i++) { if (d
	 * == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); return "/newSystem/project/financeStandingList"; }
	 *//***
	 * 商业计划列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminBusinessPlanListAdmin") public
	 * String adminBusinessPlanListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; }
	 * 
	 * Integer count = this.projectManager.getBusinessPlanDao()
	 * .countOfAllUsers(); List<Businessplan> list =
	 * this.projectManager.getBusinessPlanDao() .findByPage(page, size); size =
	 * count / size; List pageSize = new ArrayList(); int index = 1; if (size >
	 * 10 && page > 10) { index = page - 5; int d = 1; for (int i = index; i <=
	 * page + 4; i++) { if (i > size) { break; } pageSize.add(i); } } else { if
	 * (size > 10 && page <= 10) { int d = 0; for (int i = index; i <= size;
	 * i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page); map.put("size",
	 * size); map.put("count", count); map.put("pageItem", pageSize); return
	 * "/newSystem/project/bussinessPlanList"; }
	 *//***
	 * 融资方案列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminFinanceCaseListAdmin") public
	 * String adminFinanceCaseListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; }
	 * 
	 * Integer count = this.projectManager.getFinancingCaseDao()
	 * .countOfAllUsers(); List<Financingcase> list =
	 * this.projectManager.getFinancingCaseDao() .findByPage(page, size); size =
	 * count / size; List pageSize = new ArrayList(); int index = 1; if (size >
	 * 10 && page > 10) { index = page - 5; int d = 1; for (int i = index; i <=
	 * page + 4; i++) { if (i > size) { break; } pageSize.add(i); } } else { if
	 * (size > 10 && page <= 10) { int d = 0; for (int i = index; i <= size;
	 * i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page); map.put("size",
	 * size); map.put("count", count); map.put("pageItem", pageSize); return
	 * "/newSystem/project/financeCaseList"; }
	 *//***
	 * 退出渠道列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminExitCaseListAdmin") public
	 * String adminExitCaseListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; }
	 * 
	 * Integer count = this.projectManager.getFinancingexitDao()
	 * .countOfAllUsers(); List<Financingexit> list =
	 * this.projectManager.getFinancingexitDao() .findByPage(page, size); size =
	 * count / size; List pageSize = new ArrayList(); int index = 1; if (size >
	 * 10 && page > 10) { index = page - 5; int d = 1; for (int i = index; i <=
	 * page + 4; i++) { if (i > size) { break; } pageSize.add(i); } } else { if
	 * (size > 10 && page <= 10) { int d = 0; for (int i = index; i <= size;
	 * i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page); map.put("size",
	 * size); map.put("count", count); map.put("pageItem", pageSize); return
	 * "/newSystem/project/financeExitList"; }
	 *//***
	 * 路演列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminRoadShowListAdmin") public
	 * String roadShowListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } Integer count =
	 * this.projectManager.getRoadShowDao().countOfAllUsers(); List<Roadshow>
	 * list = this.projectManager.getRoadShowDao().findByPage( page, size); size
	 * = count / size; List pageSize = new ArrayList(); int index = 1; if (size
	 * > 10 && page > 10) { index = page - 5; int d = 1; for (int i = index; i
	 * <= page + 4; i++) { if (i > size) { break; } pageSize.add(i); } } else {
	 * if (size > 10 && page <= 10) { int d = 0; for (int i = index; i <= size;
	 * i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page); map.put("size",
	 * size); map.put("count", count); map.put("pageItem", pageSize); return
	 * "/newSystem/project/roadShowList"; }
	 *//***
	 * 现场列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminSceneListAdmin") public String
	 * sceneListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } Integer count =
	 * this.projectManager.getSceneDao().countOfAllUsers(); List<Scene> list =
	 * this.projectManager.getSceneDao().findByPage(page, size); size = count /
	 * size; List pageSize = new ArrayList(); int index = 1; if (size > 10 &&
	 * page > 10) { index = page - 5; int d = 1; for (int i = index; i <= page +
	 * 4; i++) { if (i > size) { break; } pageSize.add(i); } } else { if (size >
	 * 10 && page <= 10) { int d = 0; for (int i = index; i <= size; i++) { if
	 * (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page); map.put("size",
	 * size); map.put("count", count); map.put("pageItem", pageSize); return
	 * "/newSystem/project/sceneList"; }
	 *//***
	 * 活动列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminActionListAdmin") public String
	 * actionListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } Integer count =
	 * this.actionManaer.getActionDao().countOfAllUsers(); List<Action> list =
	 * this.actionManaer.getActionDao().findByPage(page, size); size = count /
	 * size; List pageSize = new ArrayList(); int index = 1; if (size > 10 &&
	 * page > 10) { index = page - 5; int d = 1; for (int i = index; i <= page +
	 * 4; i++) { if (i > size) { break; } pageSize.add(i); } } else { if (size >
	 * 10 && page <= 10) { int d = 0; for (int i = index; i <= size; i++) { if
	 * (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } } map.put("items", list.iterator()); map.put("page",
	 * page); map.put("size", size); map.put("count", count);
	 * map.put("pageItem", pageSize); return "/newSystem/action/actionList"; }
	 *//***
	 * 圈子列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminCycleListAdmin") public String
	 * cycleListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } Integer count =
	 * this.feelingManager.getPublicContentDao() .countOfAllUsers();
	 * List<Publiccontent> list = this.feelingManager.getPublicContentDao()
	 * .findByPage(page, size); size = count / size; List pageSize = new
	 * ArrayList(); int index = 1; if (size > 10 && page > 10) { index = page -
	 * 5; int d = 1; for (int i = index; i <= page + 4; i++) { if (i > size) {
	 * break; } pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d
	 * = 0; for (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("page", page); map.put("size", size); map.put("count", count);
	 * map.put("pageItem", pageSize);
	 * 
	 * map.put("items", list.iterator()); return "/newSystem/cycle/cycleList"; }
	 *//***
	 * 认证审核
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminUserAuthenticListAdmin") public
	 * String userAuthenticListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } Integer count =
	 * this.userManager.getAuthenticDao().countOfAllUsers();
	 * 
	 * List citys = this.authenticManager.getCityDao().findAll(); List areas =
	 * this.authenticManager.getIndustoryareaDao().findAll(); List<Authentic>
	 * list = this.userManager.getAuthenticDao().findByPage( page, size); List
	 * authenticStatus = this.authenticManager.getAuthenticStatus() .findAll();
	 * 
	 * // int left = count % size; size = count / size; // if(left>0) // { //
	 * size++; // } List pageSize = new ArrayList(); int index = 1; if (size >
	 * 10 && page > 10) { index = page - 5; int d = 1; for (int i = index; i <=
	 * page + 4; i++) { if (i > size) { break; } pageSize.add(i); } } else { if
	 * (size > 10 && page <= 10) { int d = 0; for (int i = index; i <= size;
	 * i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("page", page); map.put("size", size); map.put("count", count);
	 * map.put("pageItem", pageSize);
	 * 
	 * map.put("authenticStatus", authenticStatus); map.put("areas", areas);
	 * map.put("cities", citys); map.put("items", list.iterator()); return
	 * "/newSystem/Users/userAuthenticList"; }
	 *//***
	 * 编辑用户
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditUser") public String
	 * editUser(
	 * 
	 * @RequestParam(value = "userId", required = false) Integer userId,
	 * ModelMap map) { List areas =
	 * this.authenticManager.getIndustoryareaDao().findAll(); List citys =
	 * this.authenticManager.getCityDao().findAll(); if (userId != null) { Users
	 * user = this.userManager.findUserById(userId);
	 * 
	 * Set set = user.getAuthentics(); Object[] objs = set.toArray();
	 * 
	 * List l = new ArrayList(); for (int i = 0; i < objs.length; i++) {
	 * Authentic authentic = (Authentic) objs[i]; String optional =
	 * authentic.getOptional(); String area = authentic.getIndustoryArea();
	 * 
	 * Map m = new HashMap();
	 * 
	 * List list = new ArrayList(); if (optional != null) { if
	 * (optional.equals("")) { list.add(0); } else { String[] tempList =
	 * optional.split(",");
	 * 
	 * for (String s : tempList) { s = s.replace(" ", "");
	 * list.add(Integer.parseInt(s.trim())); } } } else { list.add(0); }
	 * 
	 * m.put("option", list);
	 * 
	 * list = new ArrayList(); if (area != null) { if (area.equals("")) {
	 * list.add(0); } else { String[] tempList = area.split(",");
	 * 
	 * for (String s : tempList) { s = s.replace(" ", ""); list.add(s.trim()); }
	 * } } else { list.add(0); }
	 * 
	 * m.put("areas", list);
	 * 
	 * l.add(m); }
	 * 
	 * map.put("ext", l); map.put("user", user); map.put("authentics", set);
	 * 
	 * }
	 * 
	 * map.put("areas", areas); map.put("cities", citys); map.put("optional",
	 * Config.STRING_AUTH_QUALIFICATION);
	 * 
	 * return "/newSystem/Users/editorUser"; }
	 *//***
	 * 编辑项目
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditProject") public String
	 * editProject(
	 * 
	 * @RequestParam(value = "projectId", required = false) Integer projectId,
	 * ModelMap map) { if (projectId != null) { Project project =
	 * this.projectManager.findProjectById(projectId); Object[] objs =
	 * project.getRoadshows().toArray(); if (objs != null && objs.length > 0) {
	 * Roadshow show = (Roadshow) objs[0]; map.put("show", show); }
	 * map.put("project", project); }
	 * 
	 * List list = this.projectManager.getFinancestatusDao().findAll();
	 * map.put("status", list); return "/newSystem/project/editorProject"; }
	 *//***
	 * 编辑财务状况
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditFinancestanding") public
	 * String adminEditFinancestanding(
	 * 
	 * @RequestParam(value = "financeId", required = false) Integer financeId,
	 * ModelMap map) { if (financeId != null) { Financialstanding standing =
	 * this.projectManager .getFinanceStandingDao().findById(financeId); String
	 * url = standing.getUrl(); Integer index = url.indexOf("="); if (index > 3)
	 * { String contentIdStr = url.substring(index + 1); Integer contentId =
	 * Integer.parseInt(contentIdStr); Weburlrecord record =
	 * this.webManager.getWebUrlRecordDao() .findById(contentId);
	 * map.put("record", record); }
	 * 
	 * map.put("standing", standing); }
	 * 
	 * return "/test/editorProjectFinanceStanding"; }
	 *//***
	 * 编辑融资方案
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditFinanceCase") public String
	 * adminEditFinanceCase(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * ModelMap map) { if (contentId != null) { Financingcase standing =
	 * this.projectManager.getFinancingCaseDao() .findById(contentId); String
	 * url = standing.getUrl(); Integer index = url.indexOf("="); if (index > 3)
	 * { String contentIdStr = url.substring(index + 1); Integer itemId =
	 * Integer.parseInt(contentIdStr); Weburlrecord record =
	 * this.webManager.getWebUrlRecordDao() .findById(itemId); map.put("record",
	 * record); }
	 * 
	 * map.put("standing", standing); }
	 * 
	 * return "/test/editorProjectFinanceCase"; }
	 *//***
	 * 编辑财务状况
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditFinanceExit") public String
	 * adminEditFinanceExit(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer financeId,
	 * ModelMap map) { if (financeId != null) { Financingexit standing =
	 * this.projectManager.getFinancingexitDao() .findById(financeId); String
	 * url = standing.getUrl(); Integer index = url.indexOf("="); if (index > 3)
	 * { String contentIdStr = url.substring(index + 1); Integer contentId =
	 * Integer.parseInt(contentIdStr); Weburlrecord record =
	 * this.webManager.getWebUrlRecordDao() .findById(contentId);
	 * map.put("record", record); }
	 * 
	 * map.put("standing", standing); }
	 * 
	 * return "/test/editorProjectFinanceExit"; }
	 *//***
	 * 编辑财务状况
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditBussinessPlan") public
	 * String adminEditBussinessPlan(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer financeId,
	 * ModelMap map) { if (financeId != null) { Businessplan standing =
	 * this.projectManager.getBusinessPlanDao() .findById(financeId); String url
	 * = standing.getUrl(); Integer index = url.indexOf("="); if (index > 3) {
	 * String contentIdStr = url.substring(index + 1); Integer contentId =
	 * Integer.parseInt(contentIdStr); Weburlrecord record =
	 * this.webManager.getWebUrlRecordDao() .findById(contentId);
	 * map.put("record", record); }
	 * 
	 * map.put("standing", standing); }
	 * 
	 * return "/test/editorProjectBussinessPlan"; }
	 *//***
	 * 编辑现场
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditScene") public String
	 * editScene(
	 * 
	 * @RequestParam(value = "sceneId", required = false) Integer sceneId,
	 * ModelMap map) { if (sceneId != null) { Scene scene =
	 * this.projectManager.getSceneDao().findById(sceneId);
	 * 
	 * map.put("scene", scene); }
	 * 
	 * return "/newSystem/project/editorScene"; }
	 *//***
	 * 编辑活动
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditAction") public String
	 * editAction(
	 * 
	 * @RequestParam(value = "actionId", required = false) Integer actionId,
	 * ModelMap map) { if (actionId != null) { Action action =
	 * this.actionManaer.findActionById(actionId);
	 * 
	 * map.put("action", action); }
	 * 
	 * return "/newSystem/action/editorAction"; }
	 *//***
	 * 编辑资讯
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditWebContent") public String
	 * adminEditWebContent(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * ModelMap map) { if (contentId != null) { Weburlrecord record =
	 * this.systemManger.getWebUrlRecordDao() .findById(contentId);
	 * map.put("record", record); }
	 * 
	 * List<Contenttype> l = this.systemManger.getContentTypeDao().findAll();
	 * map.put("types", l);
	 * 
	 * return "/test/editor"; }
	 *//***
	 * 编辑活动
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminAddActionImage")
	 * 
	 * @ResponseBody public Map addActionImage(
	 * 
	 * @RequestParam(value = "actionId", required = false) Integer actionId,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) {
	 * 
	 * Map m = new HashMap(); if (name != null) { Action action =
	 * this.actionManaer.findActionById(actionId);
	 * 
	 * Actionimages image = new Actionimages(); image.setUrl(name);
	 * 
	 * image.setAction(action);
	 * 
	 * this.actionManaer.getActionImageDao().save(image);
	 * 
	 * m.put("action", action); m.put("image", image.getUrl()); }
	 * 
	 * return m; }
	 */

	/***
	 * 添加现场
	 * 
	 * @return
	 */
	@RequestMapping(value = "/newSystem/adminAddScene")
	public String adminAddScene(
			@RequestParam(value = "contentId", required = false) Integer sceneId,
			@RequestParam(value = "projectId", required = false) Integer projectId,
			@RequestParam(value = "file", required = false) MultipartFile audios,
			@RequestParam(value = "time", required = false) Integer time,
			@RequestParam(value = "vurl", required = false) String vurl,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session, ModelMap map) {

		Map m = new HashMap();
		Scene scene;
		if (sceneId != -1) {
			scene = this.projectManager.getSceneDao().findById(sceneId);
			scene.setAudioPath(vurl);
			scene.setTotlalTime(time);
			if (projectId != null && projectId != -1) {
				Project project = this.projectManager
						.findProjectById(projectId);
				scene.setProject(project);
			}

			// 保存音频
			String fileName = "";
			String res = "";
			if (audios != null) {
				fileName = String.format(Config.STRING_USER_AUDIO_FORMAT,
						new Date().getTime(), 0);
				res = FileUtil.savePicture(audios, fileName, "upload/audio/");
				if (!res.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS + "upload/audio/"
							+ res;
				} else {
					fileName = "";
				}
				if (!fileName.equals("")) {
					scene.setAudioPath(fileName);
				}
			}
			this.projectManager.getSceneDao().saveOrUpdate(scene);
		} else {
			scene = new Scene();
			scene.setAudioPath(vurl);
			scene.setTotlalTime(time);
			if (projectId != null && projectId != -1) {
				Project project = this.projectManager
						.findProjectById(projectId);
				scene.setProject(project);
			}

			// 保存音频
			String fileName = "";
			String res = "";
			if (audios != null) {
				fileName = String.format(Config.STRING_USER_AUDIO_FORMAT,
						new Date().getTime(), 0);
				res = FileUtil.savePicture(audios, fileName, "upload/audio/");
				if (!res.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS + "upload/audio/"
							+ res;
				} else {
					fileName = "";
				}
				if (!fileName.equals("")) {
					scene.setAudioPath(fileName);
				}
			}

			this.projectManager.getSceneDao().save(scene);
		}

		if (session.getAttribute("scene") != null) {
			List images = (ArrayList) session.getAttribute("scene"); // 保存图片
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

		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "sceneDetail");
		map.put("data", scene);
		return Config.NEW_SERVER_CONTROL;
	}

	/*
	 * 
	 * @RequestMapping(value = "newSystem/adminDeleteSceneAudioRecord")
	 * 
	 * @ResponseBody public Map adminDeleteSceneAudioRecord(
	 * 
	 * @RequestParam(value = "recordId", required = false) Integer recordId) {
	 * this.result = new HashMap(); if (recordId != null && recordId != -1) {
	 * Audiorecord record = this.projectManager.getAudioRecordDao()
	 * .findById(recordId); if (record != null) {
	 * this.projectManager.getAudioRecordDao().delete(record); this.status =
	 * 200; this.message = "删除成功!"; } } return getResult(); }
	 * 
	 * @RequestMapping(value = "newSystem/adminDeleteActionImage")
	 * 
	 * @ResponseBody public Map adminDeleteActionImage(
	 * 
	 * @RequestParam(value = "recordId", required = false) Integer recordId) {
	 * this.result = new HashMap(); if (recordId != null && recordId != -1) {
	 * List<Actionimages> images = this.actionManaer.getActionImageDao()
	 * .findByProperty("imgId", recordId); if (images != null && images.size() >
	 * 0) { Actionimages item = images.get(0);
	 * this.actionManaer.getActionImageDao().delete(item); this.status = 200;
	 * this.message = "删除成功!"; } } return getResult(); }
	 *//***
	 * 编辑现场
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditCycle") public String
	 * editCycle(
	 * 
	 * @RequestParam(value = "cycleId", required = false) Integer cycleId,
	 * HttpSession session, ModelMap map) { if (cycleId != null) { Publiccontent
	 * content = this.feelingManager.getPublicContentDao() .findById(cycleId);
	 * 
	 * map.put("content", content); }
	 * 
	 * List l = new ArrayList();
	 * 
	 * for (int i = 0; i < 9; i++) { l.add(i); }
	 * 
	 * map.put("imgCount", l);
	 * 
	 * session.setAttribute("feeling", null); return
	 * "/newSystem/cycle/editorCycle"; }
	 *//***
	 * 测试
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminIndex") public String
	 * index(ModelMap map) {
	 * 
	 * return "/newSystem/action/index"; }
	 *//***
	 * 编辑路演
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditRoadShow") public String
	 * editRoadShow(
	 * 
	 * @RequestParam(value = "roadShowId", required = false) Integer roadShowId,
	 * ModelMap map) { if (roadShowId != null) { Roadshow roadshow =
	 * this.projectManager.getRoadShowDao().findById( roadShowId);
	 * 
	 * map.put("roadshow", roadshow); }
	 * 
	 * return "/newSystem/project/editorRoadShow"; }
	 *//***
	 * 编辑认证信息
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditorUserAuthentic") public
	 * String editorUserAuthentic(
	 * 
	 * @RequestParam(value = "authId", required = false) Integer authId,
	 * ModelMap map) { List areas =
	 * this.authenticManager.getIndustoryareaDao().findAll(); List citys =
	 * this.authenticManager.getCityDao().findAll(); List identities =
	 * this.authenticManager.getIdentitytypeDao().findAll(); List
	 * authenticStatus = this.authenticManager.getAuthenticStatus() .findAll();
	 * if (authId != null) { Authentic authentic = this.authenticManager
	 * .findAuthenticById(authId);
	 * 
	 * String optional = authentic.getOptional(); String area =
	 * authentic.getIndustoryArea();
	 * 
	 * Map m = new HashMap();
	 * 
	 * List list = new ArrayList(); if (!optional.equals(null)) { if
	 * (optional.equals("")) { list.add(0); } else { String[] tempList =
	 * optional.split(",");
	 * 
	 * for (String s : tempList) { s = s.replace(" ", ""); list.add(s.trim()); }
	 * } } else { list.add(0); }
	 * 
	 * m.put("option", list);
	 * 
	 * list = new ArrayList(); if (area != null) { if (area.equals("")) {
	 * list.add(0); } else { String[] tempList = area.split(",");
	 * 
	 * for (String s : tempList) { s = s.replace(" ", ""); list.add(s); } } }
	 * else { list.add(0); }
	 * 
	 * m.put("areas", list); map.put("ext", m); map.put("authentic", authentic);
	 * 
	 * }
	 * 
	 * map.put("identities", identities); map.put("areas", areas);
	 * map.put("cities", citys); map.put("status", authenticStatus);
	 * map.put("optional", Config.STRING_AUTH_QUALIFICATION);
	 * 
	 * return "/newSystem/Users/editorUserAuthentic"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSortAuthentic")
	 * 
	 * @ResponseBody
	 *//***
	 * 排序用户
	 * 
	 * @return
	 */
	/*
	 * public Map adminSortAuthentic(
	 * 
	 * @RequestParam(value = "authId", required = false) Integer authId,
	 * 
	 * @RequestParam(value = "type", required = false) Integer type,
	 * 
	 * @RequestParam(value = "data", required = false) Integer data) {
	 * this.result = new HashMap(); // 获取authentic Integer sortIndex = 0; if
	 * (authId != null) { Authentic authentic = this.authenticManager
	 * .findAuthenticById(authId); sortIndex = authentic.getSortIndex(); if
	 * (type == 0) { // up sortIndex--; } else if (type == 1) { sortIndex++; //
	 * down } else { sortIndex = data; }
	 * 
	 * authentic.setSortIndex(sortIndex);
	 * 
	 * // 保存 this.authenticManager.getAuthenticDao().saveOrUpdate(authentic);
	 * this.status = 200; } else { this.status = 400; }
	 * 
	 * this.result.put("data", sortIndex);
	 * 
	 * return this.getResult(); }
	 */

	@RequestMapping(value = "/newSystem/adminAddUser")
	/***
	 * 添加用户
	 * @return
	 */
	public String addUser(
			@RequestParam(value = "contentId", required = false) Integer userId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "file", required = false) MultipartFile[] images,
			@RequestParam(value = "platform", required = false) String platform,
			@RequestParam(value = "openId", required = false) String openId,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
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
		user.setWechatId(openId);

		// 更新头像
		// 保存图片
		String fileName = "";
		String result = "";
		if (images != null) {

			MultipartFile file = images[0];
			fileName = String.format(
					Config.STRING_USER_FEELING_PICTUREA_FORMAT,
					new Date().getTime(), 0);
			result = FileUtil.savePicture(file, fileName,
					"upload/uploadImages/");
			if (!result.equals("")) {
				fileName = Config.STRING_SYSTEM_ADDRESS
						+ "upload/uploadImages/" + result;
				user.setHeadSculpture(fileName);
			}
		}

		if (userId != null) {
			this.userManager.getUserDao().saveOrUpdate(user);
		} else {
			this.userManager.getUserDao().save(user);
		}

		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);

		map.put("data", user);
		map.put("content", "userDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "/newSystem/adminAddAuthentic")
	/***
	 * 添加用户认证
	 * 
	 * @return
	 */
	public String addAuthentic(
			@RequestParam(value = "contentId", required = false) Integer authId,
			@RequestParam(value = "identityTypeId", required = false) String identityTypeId,
			@RequestParam(value = "realName", required = false) String realName,
			@RequestParam(value = "identityCardA", required = false) MultipartFile identityCardA,
			@RequestParam(value = "identityCardB", required = false) MultipartFile identityCardB,
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
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		List l = (List) session.getAttribute("images");
		Authentic authentic;

		// 是否需要通知
		boolean isNotify = false;

		if (authId != -1) {
			// 获取认证身份
			authentic = this.authenticManager.findAuthenticById(authId);
			// 获取用户认证状态
			Authenticstatus authenticState = authentic.getAuthenticstatus();

			if (authenticState.getStatusId() != Integer.parseInt(status)) {
				isNotify = true;
			}

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
			authentic.setBuinessLicenceNo(bussinessNo);

			// 城市
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);

			// 更新身份证A面
			String fileName = "";
			String result = "";
			if (identityCardA != null) {

				fileName = String.format(
						Config.STRING_USER_FEELING_PICTUREA_FORMAT,
						new Date().getTime(), 0);
				result = FileUtil.savePicture(identityCardA, fileName,
						"upload/uploadImages/");
				if (!result.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS
							+ "upload/uploadImages/" + result;
					authentic.setIdentiyCarA(fileName);
				}
			}
			// 更新身份证B面
			if (identityCardB != null) {

				fileName = String.format(
						Config.STRING_USER_FEELING_PICTUREA_FORMAT,
						new Date().getTime(), 0);
				result = FileUtil.savePicture(identityCardB, fileName,
						"upload/uploadImages/");
				if (!result.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS
							+ "upload/uploadImages/" + result;
					authentic.setIdentiyCarB(fileName);
				}
			}

			// 保存
			this.authenticManager.updateAuthentic(authentic);

			// 如果需要通知，短信、邮件、站内信信息建立
			if (isNotify) {
				// 信息通知
				final Users u = this.userManager.getUserDao().findById(
						authentic.getUsers().getUserId());
				final Integer s = Integer.parseInt(status);

				final NewWebAdminController self = this;
				// 审核通过
				new Thread() {
					public void run() {
						String content = "";
						if (s == 8) {
							content = Config.STRING_SMS_AUTH_TRUE;
						} else if (s == 9) {
							content = Config.STRING_SMS_AUTH_FALSE; // 审核失败
						}

						System.out.println(content);
						try {
							// 发送用户注册成功短信
							MsgUtil SMS = new MsgUtil();
							SMS.setTelePhone(u.getTelephone());
							SMS.setMsgType(MessageType.NormalMessage);
							// 短信内容：感谢你注册金指投--专注中国成长型企业股权投融资
							SMS.setContent(content); // 发送短信
							MsgUtil.send(); // 发送注册成功邮件
							MailUtil mu = new MailUtil();
							mu.sendAuthentic(mu, u.getName(), u.getTelephone(),
									s);
						} catch (Exception e) {
							e.printStackTrace();

						}

						// 审核通过系统消息
						Systemmessage message = new Systemmessage();
						message.setUsers(u);
						message.setContent(content);
						message.setIsRead(false);
						message.setTitle("用户认证审核结果:");
						message.setMessageDate(new Date());
						message.setValid(true);

						Messagetype type = new Messagetype();
						type.setMessageTypeId(2);

						message.setMessagetype(type);

						// 推送消息
						PushUtil pushUtil = new PushUtil();
						pushUtil.setTitle(content);
						pushUtil.setShareUrl("");
						pushUtil.setContent(content);
						pushUtil.setWebViewTitle("");
						pushUtil.setShareImage("");
						pushUtil.setShareIntroduce("");
						pushUtil.setContentType(PushContentType.authentic);

						if (u != null && !u.getRegId().equals(null)) {
							pushUtil.setRegId(u.getRegId());
							pushUtil.setPlatform(u.getPlatform());
							pushUtil.send();
						} else {
							pushUtil.setIsAllPush(true);
							pushUtil.send();
						}

						// 保存
						self.systemManger.getSystemMessageDao().save(message);
					}
				}.start();
			}

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
			authentic.setBuinessLicenceNo(bussinessNo);

			// 城市
			City c = new City();
			c.setCityId(Integer.parseInt(city));
			authentic.setCity(c);

			// 更新身份证A面
			String fileName = "";
			String result = "";
			if (identityCardA != null) {

				fileName = String.format(
						Config.STRING_USER_FEELING_PICTUREA_FORMAT,
						new Date().getTime(), 0);
				result = FileUtil.savePicture(identityCardA, fileName,
						"upload/uploadImages/");
				if (!result.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS
							+ "upload/uploadImages/" + result;
					authentic.setIdentiyCarA(fileName);
				}
			}
			// 更新身份证B面
			if (identityCardB != null) {

				fileName = String.format(
						Config.STRING_USER_FEELING_PICTUREA_FORMAT,
						new Date().getTime(), 0);
				result = FileUtil.savePicture(identityCardB, fileName,
						"upload/uploadImages/");
				if (!result.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS
							+ "upload/uploadImages/" + result;
					authentic.setIdentiyCarB(fileName);
				}
			}

			// 保存
			this.authenticManager.saveAuthentic(authentic);

		}

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
		map.put("data", authentic);

		List areaes = this.authenticManager.getIndustoryareaDao().findAll();
		List citys = this.authenticManager.getCityDao().findAll();
		List identities = this.authenticManager.getIdentitytypeDao().findAll();
		List authenticStatus = this.authenticManager.getAuthenticStatus()
				.findAll();

		map.put("identities", identities);
		map.put("areas", areaes);
		map.put("cities", citys);
		map.put("status", authenticStatus);
		map.put("optional", Config.STRING_AUTH_QUALIFICATION);

		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "authenticDetail");
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "/newSystem/adminAddProject")
	/*
	 * 添加项目
	 * 
	 * @return
	 */
	public String addProject(
			@RequestParam(value = "contentId", required = false) Integer projectId,
			@RequestParam(value = "abbrevName", required = false) String abbrevName,
			@RequestParam(value = "fullName", required = false) String fullName,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "industory", required = false) String industory,
			@RequestParam(value = "introduce", required = false) String introduce,
			@RequestParam(value = "financestatus", required = false) Integer financestatus,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			ModelMap map, HttpSession session) throws Exception {
		this.result = new HashMap();
		this.result.put("data", "");

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

		// 更新封面图
		if (file != null) {

			String fileName = String.format(
					Config.STRING_USER_FEELING_PICTUREA_FORMAT,
					new Date().getTime(), 0);
			String name = FileUtil.savePicture(file, fileName,
					"upload/uploadImages/");
			if (!result.equals("")) {
				fileName = Config.STRING_SYSTEM_ADDRESS
						+ "upload/uploadImages/" + name;
				project.setStartPageImage(fileName);
			}
		}

		this.projectManager.getProjectDao().saveOrUpdate(project);

		List l = this.projectManager.getFinancestatusDao().findAll();

		map.put("data", project);
		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "projectDetail");
		map.put("status", l);
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "/newSystem/adminAddRoadShow")
	/***
	 * 添加路演
	 * 
	 * @return
	 */
	public String adminAddRoadShow(
			@RequestParam(value = "contentId", required = false) Integer financingId,
			@RequestParam(value = "projectId", required = false) Integer projectId,
			@RequestParam(value = "total", required = false) String total,
			@RequestParam(value = "financed", required = false) String financed,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "profit", required = false) String profit,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			ModelMap map, HttpSession session) throws Exception {
		this.result = new HashMap();
		this.result.put("data", "");

		Roadshowplan show;

		if (financingId != -1) {
			show = this.projectManager.getRoadShowPlanDao().findById(
					financingId);
		} else {
			show = new Roadshowplan();
			// 保存融资计划
			this.projectManager.getRoadShowPlanDao().save(show);

			// 获取项目
			Project project = this.projectManager.findProjectById(projectId);
			Set<Roadshow> shows = project.getRoadshows();
			if (shows != null && shows.size() > 0) {
				show.setRoadshows(shows);
			} else {
				Roadshow roadShow = new Roadshow();
				roadShow.setProject(project);
				roadShow.setRoadshowplan(show);
				this.projectManager.getRoadShowDao().save(roadShow);
			}
		}

		String totalStr = total.replace("万", "");
		String financedStr = financed.replace("万", "");
		String limitStr = limit.replace("万", "");

		beginTime = beginTime.replace(".0", "");
		endTime = endTime.replace(".0", "");

		show.setBeginDate(DateUtils.stringToDate(beginTime,
				"yyyy-MM-dd HH:mm:ss"));
		show.setEndDate(DateUtils.stringToDate(endTime, "yyyy-MM-dd HH:mm:ss"));
		show.setFinanceTotal(Integer.parseInt(totalStr));
		show.setFinancedMount(Integer.parseInt(financedStr));
		show.setLimitAmount(Double.parseDouble(limitStr));
		show.setProfit(profit);

		// 保存信息
		this.projectManager.getRoadShowPlanDao().saveOrUpdate(show);

		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "roadShowDetail");
		map.put("data", show);
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "newSystem/adminAddTeam")
	/**
	 * 添加团队成员
	 * @param personId  成员id
	 * @param projectId 项目id
	 * @param name 姓名
	 * @param company 公司
	 * @param position 职位
	 * @param address 地址
	 * @param introduce 个人介绍
	 * @param url 内容链接
	 * @param image 头像图片
	 * @param menu 
	 * @param sortmenu
	 * @param submenu
	 * @param session
	 * @param map
	 * @return
	 */
	public String adminAddTeam(
			@RequestParam(value = "contentId", required = false) Integer personId,
			@RequestParam(value = "projectId", required = false) Integer projectId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "company", required = false) String company,
			@RequestParam(value = "position", required = false) String position,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "introduce", required = false) String introduce,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			HttpSession session, ModelMap map) {
		// 判断是否添加成员
		Team member;
		if (personId != -1) { // 获取成员
			member = this.projectManager.getTeamDao().findById(personId);
			if (projectId != null) {
				Project project = this.projectManager
						.findProjectById(projectId);
				member.setProject(project);
			}
			member.setName(name);
			member.setCompany(company);
			member.setPosition(position);
			member.setAddress(address);
			member.setUrl(url);
			member.setIntroduce(introduce);

			if (image != null && !image.equals(member.getIcon())) {
				member.setIcon(image);
			}

			// 更新图片
			if (file != null) {

				String fileName = String.format(
						Config.STRING_USER_FEELING_PICTUREA_FORMAT,
						new Date().getTime(), 0);
				String res = FileUtil.savePicture(file, fileName,
						"upload/uploadImages/");
				if (!res.equals("")) {
					fileName = Config.STRING_SYSTEM_ADDRESS
							+ "upload/uploadImages/" + res;
					member.setIcon(fileName);
				}
			}

			this.projectManager.getTeamDao().saveOrUpdate(member);
		} else {
			// 新添加成员
			if (projectId != null) {
				Project project = this.projectManager
						.findProjectById(projectId);
				member = new Team();
				member.setProject(project);
				member.setName(name);
				member.setCompany(company);
				member.setPosition(position);
				member.setAddress(address);
				member.setUrl(url);
				member.setIntroduce(introduce);
				if (image != null && !image.equals(member.getIcon())) {
					member.setIcon(image);
				}

				// 更新图片
				if (file != null) {

					String fileName = String.format(
							Config.STRING_USER_FEELING_PICTUREA_FORMAT,
							new Date().getTime(), 0);
					String res = FileUtil.savePicture(file, fileName,
							"upload/uploadImages/");
					if (!res.equals("")) {
						fileName = Config.STRING_SYSTEM_ADDRESS
								+ "upload/uploadImages/" + res;
						member.setIcon(fileName);
					}
				}
				this.projectManager.getTeamDao().save(member);
			} else {
				member = new Team();
				member.setName(name);
				member.setCompany(company);
				member.setPosition(position);
				member.setAddress(address);
				member.setUrl(url);
				member.setIntroduce(introduce);
				if (image != null && !image.equals(member.getIcon())) {
					member.setIcon(image);
				}

				// 更新图片
				if (file != null) {

					String fileName = String.format(
							Config.STRING_USER_FEELING_PICTUREA_FORMAT,
							new Date().getTime(), 0);
					String res = FileUtil.savePicture(file, fileName,
							"upload/uploadImages/");
					if (!res.equals("")) {
						fileName = Config.STRING_SYSTEM_ADDRESS
								+ "upload/uploadImages/" + res;
						member.setIcon(fileName);
					}
				}
				this.projectManager.getTeamDao().save(member);
			}

		}

		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "teamDetail");
		map.put("data", member);
		return Config.NEW_SERVER_CONTROL;
	}

	@RequestMapping(value = "/newSystem/adminAddAction")
	// 添加项目
	public String addAction(
			@RequestParam(value = "contentId", required = false) Integer actionId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "startPageImage", required = false) String startPageImage,
			@RequestParam(value = "menu", required = false) Integer menu,
			@RequestParam(value = "sortmenu", required = false) Integer sortmenu,
			@RequestParam(value = "submenu", required = false) Integer submenu,
			ModelMap map, HttpSession session) throws Exception {
		this.result = new HashMap();
		this.result.put("data", "");

		Action action;

		if (actionId != -1) {
			action = this.actionManaer.findActionById(actionId);
		} else {
			action = new Action();
		}

		beginTime = beginTime.replace(".0", "");
		endTime = endTime.replace(".0", "");

		// 限制人数
		limit = limit.replace("人", "");
		action.setAddress(address);
		action.setName(name);
		action.setMemberLimit((short) Integer.parseInt(limit));
		action.setDescription(description);
		action.setType((short) Integer.parseInt(type));
		action.setStartTime(DateUtils.stringToDate(beginTime,
				"yyyy-MM-dd HH:mm:ss"));
		action.setEndTime(DateUtils
				.stringToDate(endTime, "yyyy-MM-dd HH:mm:ss"));

		// 更新图片
		if (file != null) {

			String fileName = String.format(
					Config.STRING_USER_FEELING_PICTUREA_FORMAT,
					new Date().getTime(), 0);
			String res = FileUtil.savePicture(file, fileName,
					"upload/uploadImages/");
			if (!res.equals("")) {
				fileName = Config.STRING_SYSTEM_ADDRESS
						+ "upload/uploadImages/" + res;
				action.setStartPageImage(fileName);
			}
		}


		if (actionId != -1) {
			this.actionManaer.saveOrUpdate(action);
		} else {
			this.actionManaer.getActionDao().save(action);
		}

		if (action.getUrl() == null || action.getUrl() == "") {
			String actionUrl = String.format(Config.ACTION_URL,
					Config.STRING_SYSTEM_ADDRESS, action.getActionId());
			action.setUrl(actionUrl);

			this.actionManaer.saveOrUpdate(action);
		}

		Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
		map.put("content", "actionDetail");
		map.put("data", action);
		return Config.NEW_SERVER_CONTROL;
	}

	/*
	 * @RequestMapping(value = "/newSystem/adminAddCycle")
	 *//***
	 * 添加项目
	 * 
	 * @return
	 */
	/*
	 * public String addCycle(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "authId", required = false) Integer authId,
	 * 
	 * @RequestParam(value = "content", required = false) String content,
	 * 
	 * @RequestParam(value = "publicDate", required = false) String publicDate,
	 * ModelMap map, HttpSession session) throws Exception { this.result = new
	 * HashMap(); this.result.put("data", "");
	 * 
	 * Publiccontent feeling;
	 * 
	 * if (contentId != -1) { feeling =
	 * this.feelingManager.findPublicContentById(contentId); } else { feeling =
	 * new Publiccontent(); }
	 * 
	 * if (authId != null) { Integer userId =
	 * this.authenticManager.getAuthenticDao() .findUserIdByAuthId(authId);
	 * 
	 * Users user = this.userManager.findUserById(userId);
	 * 
	 * feeling.setUsers(user); }
	 * 
	 * Date date = DateUtils.stringToDate(publicDate,
	 * DateUtils.DATETIME_FORMAT); if (date != null) {
	 * feeling.setPublicDate(date); } feeling.setContent(content);
	 * 
	 * // 更新头像 if (session.getAttribute("feeling") != null) { List images =
	 * (List) session.getAttribute("feeling");
	 * 
	 * Set set = new HashSet(); if (feeling.getContentimageses() != null) { set
	 * = feeling.getContentimageses(); }
	 * 
	 * for (int i = 0; i < images.size(); i++) { Contentimages items = new
	 * Contentimages(); items.setUrl(images.get(i).toString());
	 * items.setPubliccontent(feeling); set.add(items); }
	 * 
	 * feeling.setContentimageses(set); session.setAttribute("feeling", null); }
	 * 
	 * if (contentId != -1) { this.feelingManager.saveOrUpdate(feeling); } else
	 * { this.feelingManager.getPublicContentDao().save(feeling); }
	 * 
	 * List<Publiccontent> list = this.feelingManager.getPublicContentDao()
	 * .findAll(); map.put("items", list.iterator()); return
	 * "/newSystem/cycle/cycleList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminAddPush")
	 *//***
	 * 添加推送
	 * 
	 * @return
	 */
	/*
	 * public String adminAddPush(
	 * 
	 * @RequestParam(value = "messageId", required = false) Integer messageId,
	 * 
	 * @RequestParam(value = "authId", required = false) final Integer authId,
	 * 
	 * @RequestParam(value = "title", required = false) final String title,
	 * 
	 * @RequestParam(value = "shareTitle", required = false) final String
	 * shareTitle,
	 * 
	 * @RequestParam(value = "shareIntroduce", required = false) final String
	 * shareIntroduce,
	 * 
	 * @RequestParam(value = "shareImage", required = false) final String
	 * shareImage,
	 * 
	 * @RequestParam(value = "shareUrl", required = false) final String
	 * shareUrl,
	 * 
	 * @RequestParam(value = "content", required = false) final String content,
	 * 
	 * @RequestParam(value = "publicDate", required = false) String publicDate,
	 * ModelMap map, HttpSession session) throws Exception { this.result = new
	 * HashMap(); this.result.put("data", "");
	 * 
	 * Systemmessage message;
	 * 
	 * if (messageId != -1) { message =
	 * this.systemManger.getSystemMessageDao().findById( messageId); } else {
	 * message = new Systemmessage(); }
	 * 
	 * final Users user;
	 * 
	 * if (authId != null) { if (authId != -1) { Integer userId =
	 * this.authenticManager.getAuthenticDao() .findUserIdByAuthId(authId);
	 * 
	 * user = this.userManager.findUserById(userId);
	 * 
	 * message.setUsers(user); } else { user = null; } } else { user = null; }
	 * 
	 * message.setValid(false); message.setTitle(title);
	 * message.setContent(content);
	 * 
	 * // 消息类型 Messagetype type = new Messagetype(); type.setMessageTypeId(4);
	 * message.setMessagetype(type);
	 * 
	 * Date date = DateUtils.stringToDate(publicDate,
	 * DateUtils.DATETIME_FORMAT); if (date != null) {
	 * message.setMessageDate(date); }
	 * 
	 * if (messageId != -1) {
	 * this.systemManger.getSystemMessageDao().saveOrUpdate(message); } else {
	 * this.systemManger.getSystemMessageDao().save(message); }
	 * 
	 * // 开始推送 new Thread() { public void run() { PushUtil pushUtil = new
	 * PushUtil(); pushUtil.setTitle(title); pushUtil.setShareUrl(shareUrl);
	 * pushUtil.setContent(content); pushUtil.setWebViewTitle(shareTitle);
	 * pushUtil.setShareImage(shareImage);
	 * pushUtil.setShareIntroduce(shareIntroduce); if (authId != -1) { if (user
	 * != null && !user.getRegId().equals(null)) {
	 * pushUtil.setRegId(user.getRegId());
	 * pushUtil.setPlatform(user.getPlatform()); pushUtil.send(); } else {
	 * pushUtil.setIsAllPush(true); pushUtil.send(); } } else {
	 * pushUtil.setIsAllPush(true); pushUtil.send(); } }; }.start();
	 * 
	 * map.put("message", message); return "/newSystem/push/editorPush"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchUserByName")
	 * 
	 * @ResponseBody public Map searchUserByName(
	 * 
	 * @RequestParam(value = "name", required = false) String name) { Map map =
	 * new HashMap();
	 * 
	 * List<Authentic> users = this.userManager.findUserByName(name);
	 * 
	 * map.put("data", users); return map; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchUserListByName") public
	 * String adminSearchUserListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<User> list =
	 * this.userManager.findUserListByName(name); Integer count = list.size();
	 * // List<Users> list = this.userManager.getUserDao().findByPage(page, //
	 * size); size = count / size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return "/newSystem/Users/userList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchAuthenticListByName")
	 * public String adminSearchAuthenticListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Authentic> list =
	 * this.userManager.getAuthenticDao().findByName( name); Integer count =
	 * list.size(); size = count / size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return
	 * "/newSystem/Users/userAuthenticList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchProjectListByName") public
	 * String adminSearchProjectListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Project> list =
	 * this.projectManager.getProjectDao().findByName( name); Integer count =
	 * list.size(); size = count / size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return "/newSystem/project/projectList";
	 * }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchActionListByName") public
	 * String adminSearchActionListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Action> list =
	 * this.actionManaer.getActionDao().findByName(name); Integer count =
	 * list.size(); size = count / size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return "/newSystem/action/actionList"; }
	 * 
	 * @RequestMapping(value =
	 * "/newSystem/adminSearchFinanceStandingListByName") public String
	 * adminSearchFinanceStandingListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Project> projects =
	 * this.projectManager.getProjectDao() .findByName(name);
	 * 
	 * List list = new ArrayList(); for (Project p : projects) { Object[] objs =
	 * p.getFinancialstandings().toArray(); if (objs != null && objs.length > 0)
	 * { list.add(objs[0]); } } Integer count = list.size(); size = count /
	 * size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return
	 * "/newSystem/project/financeStandingList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchBuinessPlanListByName")
	 * public String adminSearchBuinessPlanListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Project> projects =
	 * this.projectManager.getProjectDao() .findByName(name);
	 * 
	 * List list = new ArrayList(); for (Project p : projects) { Object[] objs =
	 * p.getBusinessplans().toArray(); if (objs != null && objs.length > 0) {
	 * list.add(objs[0]); } } Integer count = list.size(); size = count / size;
	 * List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return
	 * "/newSystem/project/bussinessPlanList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchFinanceCaseListByName")
	 * public String adminSearchFinanceCaseListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Project> projects =
	 * this.projectManager.getProjectDao() .findByName(name);
	 * 
	 * List list = new ArrayList(); for (Project p : projects) { Object[] objs =
	 * p.getFinancingcases().toArray(); if (objs != null && objs.length > 0) {
	 * list.add(objs[0]); } } Integer count = list.size(); size = count / size;
	 * List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return
	 * "/newSystem/project/financeCaseList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchFinanceExitListByName")
	 * public String adminSearchFinanceExitListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Project> projects =
	 * this.projectManager.getProjectDao() .findByName(name);
	 * 
	 * List list = new ArrayList(); for (Project p : projects) { Object[] objs =
	 * p.getFinancingexits().toArray(); if (objs != null && objs.length > 0) {
	 * list.add(objs[0]); } } Integer count = list.size(); size = count / size;
	 * List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return
	 * "/newSystem/project/financeExitList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchMemberListByName") public
	 * String adminSearchMemberListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Project> projects =
	 * this.projectManager.getProjectDao() .findByName(name);
	 * 
	 * List list = new ArrayList(); for (Project p : projects) { Object[] objs =
	 * p.getMembers().toArray(); if (objs != null && objs.length > 0) {
	 * list.add(objs[0]); } } Integer count = list.size(); size = count / size;
	 * List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return "/newSystem/project/membersList";
	 * }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchTeamListByName") public
	 * String adminSearchTeamListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Project> projects =
	 * this.projectManager.getProjectDao() .findByName(name);
	 * 
	 * List list = new ArrayList(); for (Project p : projects) { Object[] objs =
	 * p.getTeams().toArray(); if (objs != null && objs.length > 0) {
	 * list.add(objs[0]); } } Integer count = list.size(); size = count / size;
	 * List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return "/newSystem/project/teamsList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchRoadShowListByName")
	 * public String adminSearchRoadShowListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Project> projectList =
	 * this.projectManager.getProjectDao() .findByName(name); List list = new
	 * ArrayList(); for (int i = 0; i < projectList.size(); i++) { if
	 * (projectList.get(i).getRoadshows() != null) { Object[] obj =
	 * projectList.get(i).getRoadshows().toArray();
	 * 
	 * list.add(obj[0]); } } Integer count = list.size(); size = count / size;
	 * List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return
	 * "/newSystem/project/roadShowList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchSceneListByName") public
	 * String adminSearchSceneListByName(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List<Project> projectList =
	 * this.projectManager.getProjectDao() .findByName(name); List list = new
	 * ArrayList(); for (int i = 0; i < projectList.size(); i++) { List l =
	 * this.projectManager.findSceneByProjectId(projectList
	 * .get(i).getProjectId(), null); if (l != null && l.size() > 0) {
	 * list.add(l.get(0)); } } Integer count = list.size(); size = count / size;
	 * List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name); return "/newSystem/project/sceneList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchProjectByName")
	 * 
	 * @ResponseBody public Map searchProjectByName(
	 * 
	 * @RequestParam(value = "name", required = false) String name) { Map map =
	 * new HashMap();
	 * 
	 * List<Project> projects = this.projectManager.findProjectByName(name);
	 * 
	 * map.put("data", projects); return map; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchActionByName")
	 * 
	 * @ResponseBody public Map adminSearchActionByName(
	 * 
	 * @RequestParam(value = "name", required = false) String name) { Map map =
	 * new HashMap();
	 * 
	 * List<Action> actions = this.actionManaer.findProjectByName(name);
	 * 
	 * map.put("data", actions); return map; }
	 * 
	 * 
	 * 
	 * 
	 * @RequestMapping(value = "/newSystem/adminAddProjectFinanceStanding")
	 * public String adminAddProjectFinanceStanding(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer financeId,
	 * 
	 * @RequestParam(value = "projectId", required = false) Integer projectId,
	 * 
	 * @RequestParam(value = "title", required = false) String title,
	 * 
	 * @RequestParam(value = "icon", required = false) String icon,
	 * 
	 * @RequestParam(value = "editorValue", required = false) String content,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map) {
	 * 
	 * Financialstanding standing; Weburlrecord record;
	 * 
	 * if (content == null) { content = ""; } if (financeId != null) { standing
	 * = this.projectManager.getFinanceStandingDao().findById( financeId);
	 * String url = standing.getUrl(); Integer index = url.indexOf("="); if
	 * (index > 3) { String contentIdStr = url.substring(index + 1); Integer
	 * contentId = Integer.parseInt(contentIdStr); record =
	 * this.webManager.getWebUrlRecordDao().findById( contentId);
	 * record.setContent(content);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * 
	 * } else { record = new Weburlrecord(); record.setTitle(title);
	 * 
	 * // 财务状况 Contenttype type = new Contenttype(); type.setTypeId(9);
	 * 
	 * record.setContenttype(type); record.setTag(title); record.setUrl("");
	 * record.setContent(content); record.setCreateDate(new Date());
	 * 
	 * this.webManager.getWebUrlRecordDao().save(record); }
	 * 
	 * url = Tools.generateWebRecordUrl(record.getRecordId());
	 * record.setUrl(url);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * standing.setIcon(icon); standing.setUrl(url); standing.setContent(title);
	 * 
	 * this.projectManager.getFinanceStandingDao().saveOrUpdate(standing);
	 * 
	 * } else { standing = new Financialstanding(); if (projectId != null) {
	 * Project project = this.projectManager .findProjectById(projectId);
	 * standing.setProject(project); }
	 * 
	 * record = new Weburlrecord(); record.setTitle(title);
	 * 
	 * // 财务状况 Contenttype type = new Contenttype(); type.setTypeId(9);
	 * 
	 * record.setContenttype(type); record.setTag(title); record.setUrl("");
	 * record.setContent(content); record.setCreateDate(new Date());
	 * 
	 * this.webManager.getWebUrlRecordDao().save(record);
	 * 
	 * String url = Tools.generateWebRecordUrl(record.getRecordId());
	 * record.setUrl(url);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * standing.setIcon(icon); standing.setUrl(url); standing.setContent(title);
	 * 
	 * this.projectManager.getFinanceStandingDao().save(standing); }
	 * 
	 * // 添加样式表 List l_style = new ArrayList();
	 * l_style.add("../admin/kindeditor-master/themes/default/style.css");
	 * 
	 * map.put("style", l_style);
	 * 
	 * l_style = new ArrayList();
	 * l_style.add("../admin/ueditor/ueditor.config.js");
	 * l_style.add("../admin/ueditor/ueditor.all.min.js");
	 * l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
	 * 
	 * map.put("js", l_style);
	 * 
	 * Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
	 * map.put("record", record); map.put("content", "financeStandingDetail");
	 * map.put("data", standing); return Config.NEW_SERVER_CONTROL; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminAddProjectBussinessPlan") public
	 * String adminAddProjectBussinessPlan(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer financeId,
	 * 
	 * @RequestParam(value = "projectId", required = false) Integer projectId,
	 * 
	 * @RequestParam(value = "title", required = false) String title,
	 * 
	 * @RequestParam(value = "icon", required = false) String icon,
	 * 
	 * @RequestParam(value = "editorValue", required = false) String content,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map) {
	 * 
	 * Businessplan standing; Weburlrecord record; if (content == null) {
	 * content = ""; } if (financeId != null) { standing =
	 * this.projectManager.getBusinessPlanDao().findById( financeId); String url
	 * = standing.getUrl(); Integer index = url.indexOf("="); if (index > 3) {
	 * String contentIdStr = url.substring(index + 1); Integer contentId =
	 * Integer.parseInt(contentIdStr); record =
	 * this.webManager.getWebUrlRecordDao().findById( contentId);
	 * record.setContent(content);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * 
	 * } else { record = new Weburlrecord(); record.setTitle(title);
	 * 
	 * // 财务状况 Contenttype type = new Contenttype(); type.setTypeId(9);
	 * 
	 * record.setContenttype(type); record.setTag(title); record.setUrl("");
	 * record.setContent(content); record.setCreateDate(new Date());
	 * 
	 * this.webManager.getWebUrlRecordDao().save(record); }
	 * 
	 * url = Tools.generateWebRecordUrl(record.getRecordId());
	 * record.setUrl(url);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * standing.setIcon(Config.STRING_PROJECT_FINANCESTANDING);
	 * standing.setUrl(url); standing.setContent(title);
	 * 
	 * this.projectManager.getBusinessPlanDao().saveOrUpdate(standing);
	 * 
	 * } else { standing = new Businessplan(); if (projectId != null) { Project
	 * project = this.projectManager .findProjectById(projectId);
	 * standing.setProject(project); }
	 * 
	 * record = new Weburlrecord(); record.setTitle(title);
	 * 
	 * // 财务状况 Contenttype type = new Contenttype(); type.setTypeId(9);
	 * 
	 * record.setContenttype(type); record.setTag(title); record.setUrl("");
	 * record.setContent(content); record.setCreateDate(new Date());
	 * 
	 * this.webManager.getWebUrlRecordDao().save(record);
	 * 
	 * String url = Tools.generateWebRecordUrl(record.getRecordId());
	 * record.setUrl(url);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * standing.setIcon(Config.STRING_PROJECT_FINANCESTANDING);
	 * standing.setUrl(url); standing.setContent(title);
	 * 
	 * this.projectManager.getBusinessPlanDao().save(standing); }
	 * 
	 * // 添加样式表 List l_style = new ArrayList();
	 * l_style.add("../admin/kindeditor-master/themes/default/style.css");
	 * 
	 * map.put("style", l_style);
	 * 
	 * l_style = new ArrayList();
	 * l_style.add("../admin/ueditor/ueditor.config.js");
	 * l_style.add("../admin/ueditor/ueditor.all.min.js");
	 * l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
	 * 
	 * map.put("js", l_style);
	 * 
	 * Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
	 * map.put("record", record); map.put("content", "businessPlanDetail");
	 * map.put("data", standing); return Config.NEW_SERVER_CONTROL; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminAddProjectFinanceCase") public
	 * String adminAddProjectFinanceCase(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer financeId,
	 * 
	 * @RequestParam(value = "projectId", required = false) Integer projectId,
	 * 
	 * @RequestParam(value = "title", required = false) String title,
	 * 
	 * @RequestParam(value = "icon", required = false) String icon,
	 * 
	 * @RequestParam(value = "editorValue", required = false) String content,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map) {
	 * 
	 * Financingcase standing; Weburlrecord record; if (content == null) {
	 * content = ""; } if (financeId != null) { standing =
	 * this.projectManager.getFinancingCaseDao().findById( financeId); String
	 * url = standing.getUrl(); Integer index = url.indexOf("="); if (index > 3)
	 * { String contentIdStr = url.substring(index + 1); Integer contentId =
	 * Integer.parseInt(contentIdStr); record =
	 * this.webManager.getWebUrlRecordDao().findById( contentId);
	 * record.setContent(content);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * 
	 * } else { record = new Weburlrecord(); record.setTitle(title);
	 * 
	 * // 财务状况 Contenttype type = new Contenttype(); type.setTypeId(9);
	 * 
	 * record.setContenttype(type); record.setTag(title); record.setUrl("");
	 * record.setContent(content); record.setCreateDate(new Date());
	 * 
	 * this.webManager.getWebUrlRecordDao().save(record); }
	 * 
	 * url = Tools.generateWebRecordUrl(record.getRecordId());
	 * record.setUrl(url);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * standing.setIcon(Config.STRING_PROJECT_FINANCESTANDING);
	 * standing.setUrl(url); standing.setContent(title);
	 * 
	 * this.projectManager.getFinancingCaseDao().saveOrUpdate(standing);
	 * 
	 * } else { standing = new Financingcase(); if (projectId != null) { Project
	 * project = this.projectManager .findProjectById(projectId);
	 * standing.setProject(project); }
	 * 
	 * record = new Weburlrecord(); record.setTitle(title);
	 * 
	 * // 财务状况 Contenttype type = new Contenttype(); type.setTypeId(9);
	 * 
	 * record.setContenttype(type); record.setTag(title); record.setUrl("");
	 * record.setContent(content); record.setCreateDate(new Date());
	 * 
	 * this.webManager.getWebUrlRecordDao().save(record);
	 * 
	 * String url = Tools.generateWebRecordUrl(record.getRecordId());
	 * record.setUrl(url);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * standing.setIcon(Config.STRING_PROJECT_FINANCESTANDING);
	 * standing.setUrl(url); standing.setContent(title);
	 * 
	 * this.projectManager.getFinancingCaseDao().save(standing); }
	 * 
	 * // 添加样式表 List l_style = new ArrayList();
	 * l_style.add("../admin/kindeditor-master/themes/default/style.css");
	 * 
	 * map.put("style", l_style);
	 * 
	 * l_style = new ArrayList();
	 * l_style.add("../admin/ueditor/ueditor.config.js");
	 * l_style.add("../admin/ueditor/ueditor.all.min.js");
	 * l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
	 * 
	 * map.put("js", l_style);
	 * 
	 * Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
	 * map.put("record", record); map.put("content", "financeCaseDetail");
	 * map.put("data", standing); return Config.NEW_SERVER_CONTROL; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminAddProjectFinanceExit") public
	 * String adminAddProjectFinanceExit(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer financeId,
	 * 
	 * @RequestParam(value = "projectId", required = false) Integer projectId,
	 * 
	 * @RequestParam(value = "title", required = false) String title,
	 * 
	 * @RequestParam(value = "icon", required = false) String icon,
	 * 
	 * @RequestParam(value = "editorValue", required = false) String content,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map) {
	 * 
	 * Financingexit standing; Weburlrecord record; if (content == null) {
	 * content = ""; } if (financeId != null) { standing =
	 * this.projectManager.getFinancingexitDao().findById( financeId); String
	 * url = standing.getUrl(); Integer index = url.indexOf("="); if (index > 3)
	 * { String contentIdStr = url.substring(index + 1); Integer contentId =
	 * Integer.parseInt(contentIdStr); record =
	 * this.webManager.getWebUrlRecordDao().findById( contentId);
	 * record.setContent(content);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * 
	 * } else { record = new Weburlrecord(); record.setTitle(title);
	 * 
	 * // 财务状况 Contenttype type = new Contenttype(); type.setTypeId(9);
	 * 
	 * record.setContenttype(type); record.setTag(title); record.setUrl("");
	 * record.setContent(content); record.setCreateDate(new Date());
	 * 
	 * this.webManager.getWebUrlRecordDao().save(record); }
	 * 
	 * url = Tools.generateWebRecordUrl(record.getRecordId());
	 * record.setUrl(url);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * standing.setIcon(Config.STRING_PROJECT_FINANCESTANDING);
	 * standing.setUrl(url); standing.setContent(title);
	 * 
	 * this.projectManager.getFinancingexitDao().saveOrUpdate(standing);
	 * 
	 * } else { standing = new Financingexit(); if (projectId != null) { Project
	 * project = this.projectManager .findProjectById(projectId);
	 * standing.setProject(project); }
	 * 
	 * record = new Weburlrecord(); record.setTitle(title);
	 * 
	 * // 财务状况 Contenttype type = new Contenttype(); type.setTypeId(9);
	 * 
	 * record.setContenttype(type); record.setTag(title); record.setUrl("");
	 * record.setContent(content); record.setCreateDate(new Date());
	 * 
	 * this.webManager.getWebUrlRecordDao().save(record);
	 * 
	 * String url = Tools.generateWebRecordUrl(record.getRecordId());
	 * record.setUrl(url);
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
	 * standing.setIcon(Config.STRING_PROJECT_FINANCESTANDING);
	 * standing.setUrl(url); standing.setContent(title);
	 * 
	 * this.projectManager.getFinancingexitDao().save(standing); }
	 * 
	 * // 添加样式表 List l_style = new ArrayList();
	 * l_style.add("../admin/kindeditor-master/themes/default/style.css");
	 * 
	 * map.put("style", l_style);
	 * 
	 * l_style = new ArrayList();
	 * l_style.add("../admin/ueditor/ueditor.config.js");
	 * l_style.add("../admin/ueditor/ueditor.all.min.js");
	 * l_style.add("../admin/ueditor/lang/zh-cn/zh-cn.js");
	 * 
	 * map.put("js", l_style);
	 * 
	 * Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
	 * map.put("record", record); map.put("content", "financeExitDetail");
	 * map.put("data", standing); return Config.NEW_SERVER_CONTROL; }
	 * 
	 * @RequestMapping(value = "newSystem/adminAddMember") public String
	 * adminAddMember(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer memberId,
	 * 
	 * @RequestParam(value = "projectId", required = false) Integer projectId,
	 * 
	 * @RequestParam(value = "name", required = false) String name,
	 * 
	 * @RequestParam(value = "company", required = false) String company,
	 * 
	 * @RequestParam(value = "position", required = false) String position,
	 * 
	 * @RequestParam(value = "industory", required = false) String industory,
	 * 
	 * @RequestParam(value = "address", required = false) String address,
	 * 
	 * @RequestParam(value = "image", required = false) String image,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile file,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * HttpSession session, ModelMap map) { // 判断是否添加成员 Member member; if
	 * (memberId != -1) { // 获取成员 member =
	 * this.projectManager.getMemberDao().findById(memberId); if (projectId !=
	 * null && projectId != member.getProject().getProjectId()) { Project
	 * project = this.projectManager .findProjectById(projectId);
	 * member.setProject(project); } member.setName(name);
	 * member.setIndustory(industory); member.setCompany(company);
	 * member.setPosition(position); member.setAddress(address);
	 * member.setEmial(Config.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_EMAIL);
	 * member.setTelephone(Config.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_EMAIL);
	 * 
	 * if (image != null && !image.equals(member.getIcon())) {
	 * member.setIcon(image); }
	 * 
	 * // 保存音频 String fileName = ""; String res = ""; if (file != null) {
	 * fileName = String.format(Config.STRING_USER_AUDIO_FORMAT, new
	 * Date().getTime(), 0); res = FileUtil.savePicture(file, fileName,
	 * "upload/audio/"); if (!res.equals("")) { fileName =
	 * Config.STRING_SYSTEM_ADDRESS + "upload/audio/" + res; } else { fileName =
	 * ""; } if (!fileName.equals("")) { member.setIcon(fileName); } }
	 * 
	 * this.projectManager.getMemberDao().saveOrUpdate(member); } else { //
	 * 新添加成员 if (projectId != null) { Project project = this.projectManager
	 * .findProjectById(projectId); Object[] list =
	 * project.getMembers().toArray(); if (list != null && list.length > 0) {
	 * member = (Member) list[0]; member.setProject(project);
	 * member.setName(name); member.setCompany(company);
	 * member.setIndustory(industory); member.setPosition(position);
	 * member.setAddress(address);
	 * member.setEmial(Config.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_EMAIL);
	 * member.setTelephone(Config.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_EMAIL);
	 * if (image != null && !image.equals(member.getIcon())) {
	 * member.setIcon(image); }
	 * 
	 * // 保存音频 String fileName = ""; String res = ""; if (file != null) {
	 * fileName = String.format( Config.STRING_USER_AUDIO_FORMAT, new
	 * Date().getTime(), 0); res = FileUtil.savePicture(file, fileName,
	 * "upload/audio/"); if (!res.equals("")) { fileName =
	 * Config.STRING_SYSTEM_ADDRESS + "upload/audio/" + res; } else { fileName =
	 * ""; } if (!fileName.equals("")) { member.setIcon(fileName); } }
	 * this.projectManager.getMemberDao().saveOrUpdate(member); } else { member
	 * = new Member(); member.setName(name); member.setProject(project);
	 * member.setCompany(company); member.setIndustory(industory);
	 * member.setPosition(position); member.setAddress(address);
	 * member.setEmial(Config.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_EMAIL);
	 * member.setTelephone(Config.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_EMAIL);
	 * if (image != null && !image.equals(member.getIcon())) {
	 * member.setIcon(image); }
	 * 
	 * // 保存音频 String fileName = ""; String res = ""; if (file != null) {
	 * fileName = String.format( Config.STRING_USER_AUDIO_FORMAT, new
	 * Date().getTime(), 0); res = FileUtil.savePicture(file, fileName,
	 * "upload/audio/"); if (!res.equals("")) { fileName =
	 * Config.STRING_SYSTEM_ADDRESS + "upload/audio/" + res; } else { fileName =
	 * ""; } if (!fileName.equals("")) { member.setIcon(fileName); } }
	 * this.projectManager.getMemberDao().save(member); } } else { member = new
	 * Member(); member.setName(name); member.setIndustory(industory);
	 * member.setCompany(company); member.setPosition(position);
	 * member.setAddress(address);
	 * member.setEmial(Config.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_EMAIL);
	 * member.setTelephone(Config.STRING_SYSTEM_SERVICE_PROJECT_UPLOAD_EMAIL);
	 * if (image != null && !image.equals(member.getIcon())) {
	 * member.setIcon(image); }
	 * 
	 * 
	 * // 保存音频 String fileName = ""; String res = ""; if (file != null) {
	 * fileName = String.format(Config.STRING_USER_AUDIO_FORMAT, new
	 * Date().getTime(), 0); res = FileUtil.savePicture(file, fileName,
	 * "upload/audio/"); if (!res.equals("")) { fileName =
	 * Config.STRING_SYSTEM_ADDRESS + "upload/audio/" + res; } else { fileName =
	 * ""; } if (!fileName.equals("")) { member.setIcon(fileName); } }
	 * this.projectManager.getMemberDao().save(member); }
	 * 
	 * }
	 * 
	 * Tools.setValueOfWebPage(map, 0, 0, 0, sortmenu, menu, submenu);
	 * map.put("content", "memberDetail"); map.put("data", member); return
	 * Config.NEW_SERVER_CONTROL; }
	 * 
	 * /** 活动内容
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminActionContentListAdmin") public
	 * String adminActionContentListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; }
	 * 
	 * Integer count = this.actionManaer.getActionIntroduceDao()
	 * .countOfAllUsers();
	 * 
	 * List<Actionintroduce> list = this.actionManaer.getActionIntroduceDao()
	 * .findByPage(page, size); size = count / size; List pageSize = new
	 * ArrayList(); int index = 1; if (size > 10 && page > 10) { index = page -
	 * 5; int d = 1; for (int i = index; i <= page + 4; i++) { if (i > size) {
	 * break; } pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d
	 * = 0; for (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("page", page); map.put("count", count); map.put("size", size);
	 * map.put("pageItem", pageSize); String[] l = { "文字", "图片" };
	 * map.put("types", l); map.put("items", list); return
	 * "newSystem/action/actionContentList"; }
	 *//**
	 * 活动内容详情
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditActionContent") public
	 * String adminEditActionContent(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * ModelMap map) { if (contentId != null) { Actionintroduce introduce =
	 * this.actionManaer .getActionIntroduceDao().findById(contentId);
	 * 
	 * map.put("content", introduce); } String[] list = { "文字", "图片" };
	 * map.put("types", list); return "newSystem/action/editorActionContent"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminAddActionContent") public String
	 * adminAddActionContent(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "actionId", required = false) Integer actionId,
	 * 
	 * @RequestParam(value = "type", required = false) short type,
	 * 
	 * @RequestParam(value = "content", required = false) String content,
	 * HttpSession session, ModelMap map) { Actionintroduce introduce; if
	 * (contentId != -1) { introduce =
	 * this.actionManaer.getActionIntroduceDao().findById( contentId);
	 * introduce.setType(type); introduce.setContent(content);
	 * 
	 * if (type != 0) { // 更新身份证B面 if (session.getAttribute("images") != null) {
	 * List images = (List) session.getAttribute("images");
	 * introduce.setContent(images.get(0).toString());
	 * 
	 * session.setAttribute("images", null);
	 * 
	 * } }
	 * 
	 * this.actionManaer.getActionIntroduceDao().saveOrUpdate(introduce); } else
	 * { Action action = this.actionManaer.findActionById(actionId); introduce =
	 * new Actionintroduce(); introduce.setContent(content);
	 * introduce.setAction(action); introduce.setType(type); if (type != 0) { //
	 * 更新身份证B面 if (session.getAttribute("images") != null) { List images =
	 * (List) session.getAttribute("images");
	 * introduce.setContent(images.get(0).toString());
	 * 
	 * session.setAttribute("images", null);
	 * 
	 * } }
	 * 
	 * this.actionManaer.getActionIntroduceDao().save(introduce); }
	 * 
	 * String[] list = { "文字", "图片" }; map.put("types", list);
	 * map.put("content", introduce); return
	 * "newSystem/action/editorActionContent"; }
	 *//***
	 * 金日投条
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/adminKingCapitalListAdmin") public
	 * String adminKingCapitalListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; }
	 * 
	 * Integer count = this.webManager.getWebUrlRecordDao().countOfAllUsers();
	 * List<Weburlrecord> list = this.webManager.getWebUrlRecordDao()
	 * .findKingCapital(page, size); size = count / size; List pageSize = new
	 * ArrayList(); int index = 1; if (size > 10 && page > 10) { index = page -
	 * 5; int d = 1; for (int i = index; i <= page + 4; i++) { if (i > size) {
	 * break; } pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d
	 * = 0; for (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); return "newSystem/KingCapital/kingcapitalList"; }
	 *//***
	 * 金日投条详情
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/adminEditKingCapital") public String
	 * adminEditKingCapital(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * ModelMap map) { if (contentId != null) { Weburlrecord record =
	 * this.webManager.findRecordById(contentId); map.put("content", record); }
	 * return "test/editorKingCapital"; }
	 *//***
	 * 添加金日投条
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "newSystem/adminAddKingCapital") public String
	 * adminAddKingCapital(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "title", required = false) String title,
	 * 
	 * @RequestParam(value = "tag", required = false) String tag,
	 * 
	 * @RequestParam(value = "url", required = false) String url,
	 * 
	 * @RequestParam(value = "image", required = false) String image,
	 * 
	 * @RequestParam(value = "editorValue", required = false) String content,
	 * 
	 * @RequestParam(value = "orignal", required = false) String orignal,
	 * 
	 * @RequestParam(value = "flag", required = false) Boolean flag,
	 * 
	 * @RequestParam(value = "beginTime", required = false) String beginTime,
	 * HttpSession session, ModelMap map) throws Exception { Weburlrecord
	 * record; Contenttype type = new Contenttype(); type.setTypeId(1);
	 * 
	 * if (session.getAttribute("images") != null) { List images = (List)
	 * session.getAttribute("images"); image = images.get(0).toString();
	 * session.setAttribute("images", null); }
	 * 
	 * if (content == null) { content = ""; }
	 * 
	 * if (contentId != null) { record =
	 * this.webManager.findRecordById(contentId); record.setUrl(url);
	 * record.setTag(tag); record.setFlag(flag); record.setTitle(title);
	 * record.setImage(image); record.setContent(content);
	 * record.setOrignal(orignal); record.setContenttype(type); if (beginTime !=
	 * null) { record.setCreateDate(DateUtils.stringToDate(beginTime,
	 * "yyyy-MM-dd HH:mm:ss")); } else { record.setCreateDate(new Date()); }
	 * 
	 * this.webManager.getWebUrlRecordDao().saveOrUpdate(record); } else {
	 * record = new Weburlrecord(); record.setTag(tag); record.setUrl(url);
	 * record.setFlag(flag); record.setTitle(title); record.setImage(image);
	 * record.setOrignal(orignal); record.setContent(content);
	 * record.setContenttype(type); if (beginTime != null) {
	 * record.setCreateDate(DateUtils.stringToDate(beginTime,
	 * "yyyy-MM-dd HH:mm:ss")); } else { record.setCreateDate(new Date()); }
	 * 
	 * this.webManager.getWebUrlRecordDao().save(record); } map.put("content",
	 * record); return "test/editorKingCapital"; }
	 *//***
	 * 提交项目
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "submitProject") public String submitProject(
	 * 
	 * @RequestParam(value = "name", required = false) String[] name,
	 * 
	 * @RequestParam(value = "area", required = false) String area,
	 * 
	 * @RequestParam(value = "address", required = false) String address,
	 * 
	 * @RequestParam(value = "account", required = false) Integer account,
	 * 
	 * @RequestParam(value = "pic", required = false) MultipartFile[] pic,
	 * 
	 * @RequestParam(value = "image", required = false) MultipartFile image,
	 * 
	 * @RequestParam(value = "telephone", required = false) String telephone,
	 * 
	 * @RequestParam(value = "position", required = false) String[] position,
	 * 
	 * @RequestParam(value = "selfDesc", required = false) String[] selfDesc,
	 * 
	 * @RequestParam(value = "projectName", required = false) String
	 * projectName,
	 * 
	 * @RequestParam(value = "projectDesc", required = false) String
	 * projectDesc,
	 * 
	 * @RequestParam(value = "companyName", required = false) String
	 * companyName,
	 * 
	 * @RequestParam(value = "companyDesc", required = false) String
	 * companyDesc,
	 * 
	 * @RequestParam(value = "bussinessPlan", required = false) MultipartFile
	 * bussinessPlan,
	 * 
	 * @RequestParam(value = "startPageImage", required = false) MultipartFile
	 * startPageImage, HttpSession session, ModelMap map) throws Exception {
	 * 
	 * Project project = new Project(); project.setAbbrevName(telephone);
	 * project.setFullName(companyName); project.setDescription(companyDesc);
	 * project.setAddress(address); project.setIndustoryType(area); // 保存图片
	 * String fileName = ""; String result = ""; if (startPageImage != null) {
	 * 
	 * MultipartFile file = startPageImage; fileName = String.format(
	 * Config.STRING_USER_FEELING_PICTUREA_FORMAT, new Date().getTime(), 0);
	 * result = FileUtil.savePicture(file, fileName, "upload/uploadImages/"); if
	 * (!result.equals("")) { fileName = Config.STRING_SYSTEM_ADDRESS +
	 * "upload/uploadImages/" + result; project.setStartPageImage(fileName); } }
	 * 
	 * Financestatus status = new Financestatus(); status.setStatusId(7); //
	 * 待审核项目 project.setFinancestatus(status);
	 * 
	 * Roadshow roadShow = new Roadshow(); roadShow.setProject(project);
	 * 
	 * Roadshowplan plan = new Roadshowplan(); plan.setBeginDate(new Date());
	 * plan.setEndDate(new Date()); plan.setFinancedMount(account);
	 * 
	 * this.projectManager.getRoadShowPlanDao().save(plan);
	 * roadShow.setRoadshowplan(plan);
	 * 
	 * // 保存 // 项目 this.projectManager.getProjectDao().save(project); // 路演
	 * this.projectManager.getRoadShowDao().save(roadShow);
	 * 
	 * if (name != null && name.length > 0) { Member member = new Member();
	 * member.setName(name[0]); member.setPosition(position[0]);
	 * member.setProject(project); // 保存图片 fileName = ""; result = ""; if (pic
	 * != null && pic.length > 0) {
	 * 
	 * MultipartFile file = null; Set items = new HashSet(); for (int i = 0; i <
	 * pic.length; i++) { if (pic[i] != null) { file = pic[i]; fileName =
	 * String.format( Config.STRING_USER_FEELING_PICTUREA_FORMAT, new
	 * Date().getTime(), i); result = FileUtil.savePicture(file, fileName,
	 * "upload/uploadImages/"); if (!result.equals("")) { fileName =
	 * Config.STRING_SYSTEM_ADDRESS + "upload/uploadImages/" + result;
	 * member.setIcon(fileName); }
	 * 
	 * } break; } }
	 * 
	 * this.projectManager.getMemberDao().save(member); }
	 * 
	 * Team team; for (int i = 0; i < name.length; i++) { team = new Team();
	 * team.setIntroduce(selfDesc[i]); team.setName(name[i]);
	 * team.setPosition(position[i]); // 保存图片 fileName = ""; result = ""; if
	 * (pic != null && pic.length > 0) {
	 * 
	 * MultipartFile file = null; Set items = new HashSet(); for (int j = 0; j <
	 * pic.length; j++) { if (pic[i] != null) { file = pic[i]; fileName =
	 * String.format( Config.STRING_USER_FEELING_PICTUREA_FORMAT, new
	 * Date().getTime(), i); result = FileUtil.savePicture(file, fileName,
	 * "upload/uploadImages/"); if (!result.equals("")) { fileName =
	 * Config.STRING_SYSTEM_ADDRESS + "upload/uploadImages/" + result;
	 * team.setIcon(fileName); }
	 * 
	 * } } }
	 * 
	 * this.projectManager.getTeamDao().save(team); } return
	 * "redirect:http://www.jinzht.com/app/submitSuccess/"; // return
	 * "redirect:http://www.jinzht.com/app/submitFail/"; }
	 * 
	 * @RequestMapping(value = "newSystem/adminNewsContentListAdmin")
	 *//***
	 * 
	 */
	/*
	 * public String adminNewsContentListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } Integer count =
	 * this.messageManager.getMsgDao().countOfAllRecords(); List<Users> list =
	 * this.messageManager.getMsgDao().findByPage(page, size); size = count /
	 * size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize);
	 * 
	 * return "newSystem/NewsContent/contentList"; }
	 * 
	 * @RequestMapping(value = "newSystem/adminArticleListAdmin")
	 *//***
	 * 
	 */
	/*
	 * public String adminArticleListAdmin(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } Integer count =
	 * this.mainManager.getOrigianlDao().countOfAllRecords(); List<Users> list =
	 * this.mainManager.getOrigianlDao().findByPage(page, size); size = count /
	 * size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize);
	 * 
	 * return "newSystem/NewsContent/articesList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchContentsListByKey") public
	 * String adminSearchContentsListByKey(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List list =
	 * this.messageManager.getMsgDao().findByName(name); Integer count =
	 * list.size(); // List<Users> list =
	 * this.userManager.getUserDao().findByPage(page, // size); size = count /
	 * size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name);
	 * 
	 * return "newSystem/NewsContent/contentList"; }
	 * 
	 * @RequestMapping(value = "/newSystem/adminSearchArticesListByKey") public
	 * String adminSearchArticesListByKey(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "name", required = false) String name, ModelMap
	 * map) { if (page == null) { page = 0; }
	 * 
	 * if (size == null) { size = 10; } List list =
	 * this.mainManager.getOrigianlDao().findByName(name); Integer count =
	 * list.size(); // List<Users> list =
	 * this.userManager.getUserDao().findByPage(page, // size); size = count /
	 * size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize); map.put("key", name);
	 * 
	 * return "newSystem/NewsContent/articesList"; }
	 *//***
	 * 编辑资讯内容
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditNewsInfo") public String
	 * adminEditNewsContent(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * ModelMap map) { if (contentId != null) { Msg msg =
	 * this.messageManager.getMsgDao().findById(contentId); map.put("msg", msg);
	 * }
	 * 
	 * List list = new ArrayList(); Map dataMap = new HashMap();
	 * dataMap.put("key", 1); dataMap.put("value", "无图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 2); dataMap.put("value",
	 * "小图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 3); dataMap.put("value",
	 * "大图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 4); dataMap.put("value",
	 * "多图模式"); list.add(dataMap);
	 * 
	 * map.put("options", list); return "test/editNewsContent"; }
	 *//***
	 * 编辑资讯内容
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminAddNewsInfo") public String
	 * adminAddNewsInfo(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "title", required = false) String title,
	 * 
	 * @RequestParam(value = "original", required = false) String original,
	 * 
	 * @RequestParam(value = "image", required = false) String image,
	 * 
	 * @RequestParam(value = "type", required = false) Integer type,
	 * 
	 * @RequestParam(value = "editorValue", required = false) String content,
	 * 
	 * @RequestParam(value = "publicDate", required = false) String publicDate,
	 * HttpSession session, ModelMap map) { Msg msg; if (contentId != null) {
	 * msg = this.messageManager.getMsgDao().findById(contentId); } else { msg =
	 * new Msg(); this.messageManager.getMsgDao().save(msg); }
	 * 
	 * Webcontenttype contentType = new Webcontenttype();
	 * contentType.setTypeId(type); msg.setTitle(title);
	 * msg.setOringl(original); msg.setPublicDate(new Date());
	 * msg.setWebcontenttype(contentType);
	 * 
	 * MsgDetail detail; if (msg.getMsgDetails() != null &&
	 * msg.getMsgDetails().size() > 0) { Object[] obj =
	 * msg.getMsgDetails().toArray(); detail = (MsgDetail) obj[0];
	 * detail.setContent(content); } else { detail = new MsgDetail();
	 * detail.setMsg(msg); detail.setContent(content);
	 * 
	 * // 保存 this.messageManager.getMsgDetailDAO().save(detail);
	 * 
	 * // 保存关系 Set set = new HashSet(); set.add(detail);
	 * 
	 * msg.setMsgDetails(set); }
	 * 
	 * List l = (List) session.getAttribute("News");
	 * 
	 * Set set = new HashSet(); if (l != null && l.size() > 0) { if (type != 4
	 * && type != 1) { for (int i = 0; i < msg.getMsgImageses().size(); i++) {
	 * Object[] objs = msg.getMsgImageses().toArray(); MsgImages images =
	 * (MsgImages) objs[i];
	 * this.messageManager.getMsgImagesDao().delete(images); } } else { if
	 * (msg.getMsgImageses().size() == 3) { for (int i = 0; i < l.size(); i++) {
	 * Object[] objs = msg.getMsgImageses().toArray(); MsgImages images =
	 * (MsgImages) objs[i];
	 * this.messageManager.getMsgImagesDao().delete(images); } } }
	 * 
	 * int i = l.size() >= 3 ? 3 : l.size(); for (int j = 0; j < i; j++) {
	 * MsgImages images = new MsgImages(); images.setUrl(l.get(j).toString());
	 * images.setMsg(msg); this.messageManager.getMsgImagesDao().save(images);
	 * set.add(images); } } else { if (image != null && !image.equals("")) {
	 * 
	 * if (type != 4 && type != 1) { Object[] objs =
	 * msg.getMsgImageses().toArray(); MsgImages images = (MsgImages) objs[0];
	 * this.messageManager.getMsgImagesDao().delete(images); } else { if
	 * (msg.getMsgImageses().size() >= 3) { Object[] objs =
	 * msg.getMsgImageses().toArray(); MsgImages images = (MsgImages) objs[0];
	 * this.messageManager.getMsgImagesDao().delete(images); } }
	 * 
	 * MsgImages images = new MsgImages(); images.setMsg(msg);
	 * images.setUrl(image); this.messageManager.getMsgImagesDao().save(images);
	 * set.add(images); } }
	 * 
	 * msg.setMsgImageses(set);
	 * this.messageManager.getMsgDao().saveOrUpdate(msg);
	 * 
	 * session.setAttribute("News", null);
	 * 
	 * List list = new ArrayList(); Map dataMap = new HashMap();
	 * dataMap.put("key", 1); dataMap.put("value", "无图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 2); dataMap.put("value",
	 * "小图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 3); dataMap.put("value",
	 * "大图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 4); dataMap.put("value",
	 * "多图模式"); list.add(dataMap);
	 * 
	 * map.put("msg", msg); map.put("options", list); return
	 * "test/editNewsContent"; }
	 *//***
	 * 编辑资讯内容
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminAddOriginal") public String
	 * adminAddOriginal(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "title", required = false) String title,
	 * 
	 * @RequestParam(value = "original", required = false) String original,
	 * 
	 * @RequestParam(value = "image", required = false) String image,
	 * 
	 * @RequestParam(value = "type", required = false) Integer type,
	 * 
	 * @RequestParam(value = "editorValue", required = false) String content,
	 * 
	 * @RequestParam(value = "publicDate", required = false) String publicDate,
	 * HttpSession session, ModelMap map) { Original msg; if (contentId != null)
	 * { msg = this.mainManager.getOrigianlDao().findById(contentId); } else {
	 * msg = new Original(); this.mainManager.getOrigianlDao().save(msg); }
	 * 
	 * Webcontenttype contentType = new Webcontenttype();
	 * contentType.setTypeId(type); msg.setTitle(title);
	 * msg.setOringl(original); msg.setPublicDate(publicDate);
	 * msg.setWebcontenttype(contentType);
	 * 
	 * OriginalDetail detail; if (msg.getOriginalDetails() != null &&
	 * msg.getOriginalDetails().size() > 0) { Object[] obj =
	 * msg.getOriginalDetails().toArray(); detail = (OriginalDetail) obj[0];
	 * detail.setOriginal(msg); detail.setContent(content); } else { detail =
	 * new OriginalDetail(); detail.setOriginal(msg);
	 * detail.setContent(content);
	 * 
	 * // 保存 this.mainManager.getOriginalDetailDao().save(detail);
	 * 
	 * // 保存关系 Set set = new HashSet(); set.add(detail);
	 * 
	 * msg.setOriginalDetails(set); }
	 * 
	 * List l = (List) session.getAttribute("Original");
	 * 
	 * Set set = new HashSet(); if (l != null && l.size() > 0) { if (type != 4
	 * && type != 1) { for (int i = 0; i < msg.getOriginalImgs().size(); i++) {
	 * Object[] objs = msg.getOriginalImgs().toArray(); OriginalImg images =
	 * (OriginalImg) objs[i];
	 * this.mainManager.getOriginalImgDao().delete(images); } } else { if
	 * (msg.getOriginalImgs().size() == 3) { for (int i = 0; i < l.size(); i++)
	 * { Object[] objs = msg.getOriginalImgs().toArray(); OriginalImg images =
	 * (OriginalImg) objs[i];
	 * this.mainManager.getOriginalImgDao().delete(images); } } }
	 * 
	 * int i = l.size() >= 3 ? 3 : l.size(); for (int j = 0; j < i; j++) {
	 * OriginalImg images = new OriginalImg();
	 * images.setUrl(l.get(j).toString()); images.setOriginal(msg);
	 * this.mainManager.getOriginalImgDao().save(images); set.add(images); } }
	 * else { if (image != null && !image.equals("") && (l == null || (l != null
	 * && l.size() == 0))) {
	 * 
	 * if (type != 4 && type != 1) { Object[] objs =
	 * msg.getOriginalImgs().toArray(); OriginalImg images = (OriginalImg)
	 * objs[0]; this.mainManager.getOriginalImgDao().delete(images); } else { if
	 * (msg.getOriginalDetails().size() >= 3) { Object[] objs =
	 * msg.getOriginalDetails().toArray(); OriginalImg images = (OriginalImg)
	 * objs[0]; this.mainManager.getOriginalImgDao().delete(images); } }
	 * 
	 * OriginalImg images = new OriginalImg(); images.setOriginal(msg);
	 * images.setUrl(image); this.mainManager.getOriginalImgDao().save(images);
	 * set.add(images); } }
	 * 
	 * msg.setOriginalImgs(set);
	 * 
	 * this.mainManager.getOrigianlDao().saveOrUpdate(msg);
	 * 
	 * session.setAttribute("Original", null);
	 * 
	 * List list = new ArrayList(); Map dataMap = new HashMap();
	 * dataMap.put("key", 1); dataMap.put("value", "无图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 2); dataMap.put("value",
	 * "小图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 3); dataMap.put("value",
	 * "大图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 4); dataMap.put("value",
	 * "多图模式"); list.add(dataMap);
	 * 
	 * map.put("msg", msg); map.put("options", list); return "test/editArtices";
	 * }
	 *//***
	 * 编辑资讯内容
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminDeleteNewsInfo") public String
	 * adminDeleteNewsInfo(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * ModelMap map) { if (contentId != null) { Msg msg =
	 * this.messageManager.getMsgDao().findById(contentId);
	 * this.messageManager.getMsgDao().delete(msg); }
	 * 
	 * int page = 0; int size = 10; Integer count =
	 * this.messageManager.getMsgDao().countOfAllRecords(); List<Users> list =
	 * this.messageManager.getMsgDao().findByPage(page, size); size = count /
	 * size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize);
	 * 
	 * return "newSystem/NewsContent/contentList"; }
	 *//***
	 * 编辑资讯内容
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminEditArtic") public String
	 * adminEditArtic(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * ModelMap map) { if (contentId != null) { Original msg =
	 * this.mainManager.getOrigianlDao() .findById(contentId); map.put("msg",
	 * msg); }
	 * 
	 * List list = new ArrayList(); Map dataMap = new HashMap();
	 * dataMap.put("key", 1); dataMap.put("value", "无图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 2); dataMap.put("value",
	 * "小图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 3); dataMap.put("value",
	 * "大图模式"); list.add(dataMap);
	 * 
	 * dataMap = new HashMap(); dataMap.put("key", 4); dataMap.put("value",
	 * "多图模式"); list.add(dataMap);
	 * 
	 * map.put("options", list); return "test/editArtices"; }
	 *//***
	 * 删除资讯内容
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/newSystem/adminDeleteArtic") public String
	 * adminDeleteArtic(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * ModelMap map) { if (contentId != null) { Original msg =
	 * this.mainManager.getOrigianlDao() .findById(contentId);
	 * this.mainManager.getOrigianlDao().delete(msg); }
	 * 
	 * int page = 0; int size = 10; Integer count =
	 * this.mainManager.getOrigianlDao().countOfAllRecords(); List<Users> list =
	 * this.mainManager.getOrigianlDao().findByPage(page, size); size = count /
	 * size; List pageSize = new ArrayList();
	 * 
	 * int index = 1; if (size > 10 && page > 10) { index = page - 5; int d = 1;
	 * for (int i = index; i <= page + 4; i++) { if (i > size) { break; }
	 * pageSize.add(i); } } else { if (size > 10 && page <= 10) { int d = 0; for
	 * (int i = index; i <= size; i++) { if (d == 10) { break; }
	 * 
	 * d++; pageSize.add(i); } } else { for (int i = index; i <= size; i++) {
	 * pageSize.add(i); } } }
	 * 
	 * map.put("items", list.iterator()); map.put("page", page);
	 * map.put("count", count); map.put("size", size); map.put("pageItem",
	 * pageSize);
	 * 
	 * return "newSystem/NewsContent/articesList"; }
	 *//***
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * ---------------------------------------------后端管理系统升级--------------------
	 * -----------------------
	 ***/
	/*
	*//***
	 * 首页
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/index") public String
	 * requestIndexPage(ModelMap map, HttpSession session) { map.put("content",
	 * "content"); return Config.NEW_SERVER_CONTROL; }
	 *//***
	 * 资讯内容
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/news") public String
	 * requestNewsPage(ModelMap map, HttpSession session) { map.put("content",
	 * "buttons"); return Config.NEW_SERVER_CONTROL; }
	 *//***
	 * 资讯内容
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/notebook") public String
	 * requestNotebook(ModelMap map, HttpSession session) {
	 * 
	 * map.put("content", "notebook"); return Config.NEW_SERVER_CONTROL; }
	 *//***
	 * 资讯Banner
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/newsBanner") public String
	 * requestNewsBanner(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map, HttpSession session) {
	 * 
	 * List list = this.messageManager.getNewsbannerDAO().findAll();
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("content", "table-news-banner"); map.put("result",
	 * list); return Config.NEW_SERVER_CONTROL; }
	 *//***
	 * 删除资讯Banner
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/delNewsBanner") public String
	 * requestDelNewsBanner(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map, HttpSession session) {
	 * 
	 * if (contentId != null) { Newsbanner banner =
	 * this.messageManager.getNewsbannerDAO() .findById(contentId); if (banner
	 * != null) { this.messageManager.getNewsbannerDAO().delete(banner); }
	 * 
	 * }
	 * 
	 * List list = this.messageManager.getNewsbannerDAO().findAll();
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("content", "table-news-banner"); map.put("result",
	 * list); return Config.NEW_SERVER_CONTROL; }
	 *//***
	 * 资讯Banner详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/newBannerDetail") public String
	 * newBannerDetail(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map, HttpSession session) {
	 * 
	 * if (contentId != null) { Newsbanner banner =
	 * this.messageManager.getNewsbannerDAO() .findById(contentId); if (banner
	 * != null) { map.put("data", banner); } }
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("content", "newBannerContent"); return
	 * Config.NEW_SERVER_CONTROL; }
	 *//***
	 * 编辑Banner详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/editNewBanner") public String
	 * editNewBanner(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "desc", required = false) String desc,
	 * 
	 * @RequestParam(value = "image", required = false) String image,
	 * 
	 * @RequestParam(value = "url", required = false) String url,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile[] images,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map, HttpSession session) {
	 * 
	 * if (contentId != null) { Newsbanner banner =
	 * this.messageManager.getNewsbannerDAO() .findById(contentId); if (banner
	 * != null) { // 更新 if (desc != null) { banner.setDesc(desc); }
	 * 
	 * if (image != null) { banner.setImage(image); }
	 * 
	 * if (url != null) { banner.setUrl(url); }
	 * 
	 * // 保存图片 String fileName = ""; String result = ""; if (images != null &&
	 * images.length > 0) {
	 * 
	 * MultipartFile file = null; Set items = new HashSet(); for (int i = 0; i <
	 * images.length; i++) { if (images[i] != null) { file = images[i]; fileName
	 * = String.format( Config.STRING_USER_FEELING_PICTUREA_FORMAT, new
	 * Date().getTime(), i); result = FileUtil.savePicture(file, fileName,
	 * "upload/uploadImages/"); if (!result.equals("")) { fileName =
	 * Config.STRING_SYSTEM_ADDRESS + "upload/uploadImages/" + result; } else {
	 * fileName = ""; }
	 * 
	 * } }
	 * 
	 * if (fileName != null && fileName != "") { banner.setImage(fileName); }
	 * else { if (image != null && image != "") { banner.setImage(image); } } }
	 * 
	 * // 保存 this.messageManager.getNewsbannerDAO().saveOrUpdate(banner);
	 * map.put("data", banner); } }
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("content", "newBannerContent"); return
	 * Config.NEW_SERVER_CONTROL; }
	 *//***
	 * 原创Banner
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/originalBanner") public String
	 * originalNewsBanner(
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map, HttpSession session) {
	 * 
	 * List list = this.messageManager.getOriginalbannerDAO().findAll();
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("content", "table-original-banner"); map.put("result",
	 * list); return Config.NEW_SERVER_CONTROL; }
	 *//***
	 * 删除原创Banner
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/delOriginalBanner") public String
	 * requestDelOriginalBanner(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map, HttpSession session) {
	 * 
	 * if (contentId != null) { Originalbanner banner =
	 * this.messageManager.getOriginalbannerDAO() .findById(contentId); if
	 * (banner != null) {
	 * this.messageManager.getOriginalbannerDAO().delete(banner); }
	 * 
	 * }
	 * 
	 * List list = this.messageManager.getOriginalbannerDAO().findAll();
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("content", "table-original-banner"); map.put("result",
	 * list); return Config.NEW_SERVER_CONTROL; }
	 *//***
	 * 原创Banner详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/originalBannerDetail") public String
	 * originalBannerDetail(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map, HttpSession session) {
	 * 
	 * if (contentId != null) { Originalbanner banner =
	 * this.messageManager.getOriginalbannerDAO() .findById(contentId); if
	 * (banner != null) { map.put("data", banner); } }
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("content", "originalBannerContent"); return
	 * Config.NEW_SERVER_CONTROL; }
	 *//***
	 * 编辑Banner详情
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	/*
	 * @RequestMapping(value = "newSystem/editOriginalBanner") public String
	 * editOriginalBanner(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "desc", required = false) String desc,
	 * 
	 * @RequestParam(value = "image", required = false) String image,
	 * 
	 * @RequestParam(value = "url", required = false) String url,
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile[] images,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map, HttpSession session) {
	 * 
	 * if (contentId != null) { Originalbanner banner =
	 * this.messageManager.getOriginalbannerDAO() .findById(contentId); if
	 * (banner != null) { // 更新 if (desc != null) { banner.setDesc(desc); }
	 * 
	 * if (image != null) { banner.setImage(image); }
	 * 
	 * if (url != null) { banner.setUrl(url); }
	 * 
	 * // 保存图片 String fileName = ""; String result = ""; if (images != null &&
	 * images.length > 0) {
	 * 
	 * MultipartFile file = null; Set items = new HashSet(); for (int i = 0; i <
	 * images.length; i++) { if (images[i] != null) { file = images[i]; fileName
	 * = String.format( Config.STRING_USER_FEELING_PICTUREA_FORMAT, new
	 * Date().getTime(), i); result = FileUtil.savePicture(file, fileName,
	 * "upload/uploadImages/"); if (!result.equals("")) { fileName =
	 * Config.STRING_SYSTEM_ADDRESS + "upload/uploadImages/" + result; } else {
	 * fileName = ""; }
	 * 
	 * } }
	 * 
	 * if (fileName != null && fileName != "") { banner.setImage(fileName); }
	 * else { if (image != null && image != "") { banner.setImage(image); } } }
	 * 
	 * // 保存 this.messageManager.getOriginalbannerDAO().saveOrUpdate(banner);
	 * map.put("data", banner); } }
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("content", "originalBannerContent"); return
	 * Config.NEW_SERVER_CONTROL; }
	 * 
	 * // 聊天室页面
	 * 
	 * @RequestMapping(value = "newSystem/createChatRoomPage") public String
	 * createChatRoomPage(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map) { if (contentId != null) { Chatroom room =
	 * this.imManager.getChatRoomDao().findById(contentId); map.put("data",
	 * room); Integer userId = Integer.parseInt(room.getOwner()); Users u =
	 * this.userManager.findUserById(userId); map.put("img",
	 * u.getHeadSculpture()); }
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("content", "createChatRoom"); return
	 * Config.NEW_SERVER_CONTROL; }
	 * 
	 * // 聊天室添加
	 * 
	 * @RequestMapping(value = "newSystem/createChatRoom") public String
	 * createChatRoom(
	 * 
	 * @RequestParam(value = "name") String name,
	 * 
	 * @RequestParam(value = "desc") String desc,
	 * 
	 * @RequestParam(value = "maxusers") Integer maxusers,
	 * 
	 * @RequestParam(value = "owner") Integer userId,
	 * 
	 * @RequestParam(value = "projectId") Integer projectId,
	 * 
	 * @RequestParam(value = "contentId") Integer contentId,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map) { Users u = this.userManager.findUserById(userId); Chatroom
	 * room; if (contentId != null) { room =
	 * this.imManager.getChatRoomDao().findById(contentId); } else { room = new
	 * Chatroom(); }
	 * 
	 * if (contentId == null) { Map result = this.imManager.createChatRoom(name,
	 * desc, maxusers, u); if (result.get("data") != null) { Map m = (Map)
	 * result.get("data"); String id = m.get("id").toString(); room.setCode(id);
	 * 
	 * } map.put("result", result); }
	 * 
	 * room.setName(name); room.setDescription(desc);
	 * room.setMaxusers(maxusers); room.setAffiliationsCount(0);
	 * room.setCreateDate(new Date()); room.setOwnerName(u.getName());
	 * room.setOwner(String.valueOf(userId));
	 * room.setExt(String.valueOf(projectId));
	 * 
	 * if (contentId != null) {
	 * this.imManager.getChatRoomDao().saveOrUpdate(room); } else {
	 * this.imManager.getChatRoomDao().save(room); }
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("img", u.getHeadSculpture()); map.put("data", room);
	 * map.put("content", "createChatRoom"); return Config.NEW_SERVER_CONTROL; }
	 * 
	 * // 聊天室列表
	 * 
	 * @RequestMapping(value = "newSystem/chatRoomList") public String
	 * chatRoomList(
	 * 
	 * @RequestParam(value = "size", required = false) Integer size,
	 * 
	 * @RequestParam(value = "page", required = false) Integer page,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map) {
	 * 
	 * List list = this.imManager.getChatRoomDao().findAll();
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu);
	 * 
	 * map.put("result", list); map.put("content", "table-chatroom-banner");
	 * return Config.NEW_SERVER_CONTROL; }
	 * 
	 * // 删除聊天室
	 * 
	 * @RequestMapping(value = "newSystem/deleteChatRoom") public String
	 * deleteChatRoom(
	 * 
	 * @RequestParam(value = "contentId", required = false) Integer contentId,
	 * 
	 * @RequestParam(value = "menu", required = false) Integer menu,
	 * 
	 * @RequestParam(value = "sortmenu", required = false) Integer sortmenu,
	 * 
	 * @RequestParam(value = "submenu", required = false) Integer submenu,
	 * ModelMap map) {
	 * 
	 * if (contentId != null) { Chatroom room =
	 * this.imManager.getChatRoomDao().findById(contentId);
	 * 
	 * if (room != null) { this.imManager.getChatRoomDao().delete(room); } }
	 * 
	 * List list = this.imManager.getChatRoomDao().findAll();
	 * 
	 * map.put("menu", menu); map.put("sortmenu", sortmenu); map.put("submenu",
	 * submenu); map.put("result", list); map.put("content",
	 * "table-chatroom-banner"); return Config.NEW_SERVER_CONTROL; }
	 */

	/***
	 * ---------------------------------------------后端管理系统升级--------------------
	 * -----------------------
	 ***/

}
