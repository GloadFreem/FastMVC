package com.jinzht.mobile.web;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinzht.tools.Config;
import com.jinzht.tools.DateUtils;
import com.jinzht.tools.FileUtil;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Member;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Roadshowplan;
import com.jinzht.web.entity.Scene;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Weburlrecord;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.ImManager;
import com.jinzht.web.manager.InvestorManager;
import com.jinzht.web.manager.ProjectManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.manager.WebManager;
import com.message.Enity.Msg;
import com.message.Enity.MsgBean;
import com.message.Enity.MsgDetail;
import com.message.Enity.Newsbanner;
import com.message.Enity.Original;
import com.message.Enity.OriginalDetail;
import com.message.Enity.Originalbanner;
import com.message.manager.MainManager;
import com.message.manager.MessageMananger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Controller
public class ImController extends BaseController {
	@Autowired
	private WebManager webManager;
	@Autowired
	private MessageMananger messageManager;
	@Autowired
	private MainManager mainManager;
	@Autowired
	private ProjectManager projectManager;
	@Autowired
	private ImManager imManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/im/requestIMTokean")
	@ResponseBody
	public Map requestIMTokean(ModelMap map) {
		this.result = new HashMap();
		boolean result = this.imManager.requestToken();

		this.result.put("result", result);
		return getResult();
	}

	@RequestMapping(value = "/im/requestIsNeedRequestIMTokean")
	@ResponseBody
	public Map requestIsNeedRequestIMTokean(ModelMap map) {
		this.result = new HashMap();
		boolean result = this.imManager.isNeedRequestToken();

		this.result.put("result", result);
		return getResult();
	}

	@RequestMapping(value = "/im/requestImUsers")
	@ResponseBody
	public Map requestImUsers(ModelMap map) {
		this.result = new HashMap();
		List result = this.imManager.getUsers(0, 100);

		this.result.put("result", result);
		return getResult();
	}

	/************************************** 聊天室 ****************************************************/
	/***
	 * 创建聊天室
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "im/createChatRoom")
	@ResponseBody
	public Map createChatRoom(@RequestParam(value = "name") String name,
			@RequestParam(value = "desc") String desc,
			@RequestParam(value = "maxusers") Integer maxusers,
			@RequestParam(value = "owner") Integer userId, ModelMap map,
			HttpSession session) {
		this.result = new HashMap();

		Users u = this.userManager.findUserById(userId);
		Map result = this.imManager.createChatRoom(name, desc, maxusers, u);

		this.result.put("result", result);
		return getResult();
	}

	/***
	 * 创建项目聊天室
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "im/createProjectChatRoom")
	@ResponseBody
	public Map createProjectChatRoom(
			@RequestParam(value = "maxusers") Integer maxusers,
			@RequestParam(value = "owner") Integer userId, ModelMap map,
			HttpSession session) {
		this.result = new HashMap();

		Users u = this.userManager.findUserById(userId);

		List<Project> projects = this.projectManager.findProjectsByCursor(0, 0,
				100000);
		for (Project project : projects) {
			Map result = this.imManager.createChatRoom(project.getAbbrevName(),
					project.getFullName(), maxusers, u);

			List<Scene> scenes = this.projectManager.getSceneDao()
					.findByProperty("project", project);

			for (Scene scene : scenes) {
				String id = ((Map) result.get("data")).get("id").toString();
				scene.setChatRoomId(id);

				this.projectManager.getSceneDao().saveOrUpdate(scene);
			}
		}

		this.result.put("result", result);
		return getResult();
	}

	@RequestMapping(value = "im/chatRoomList")
	@ResponseBody
	public Map chatRoomList(@RequestParam(value = "page") Integer page,
			@RequestParam(value = "size") Integer size, ModelMap map,
			HttpSession session) {
		this.result = new HashMap();

		Map result = this.imManager.chatRoomList(page, size);

		this.result.put("result", result.get("data"));
		return getResult();
	}

	@RequestMapping(value = "im/deleteChatRoom")
	@ResponseBody
	/***
	 * 删除聊天室
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map deleteChatRoom(
			@RequestParam(value = "chatRoomId") String chatRoomId,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();

		Map result = this.imManager.deleteChatRoom(chatRoomId);

		this.result.put("result", result.get("data"));
		return getResult();
	}

	@RequestMapping(value = "im/chatRoomDetail")
	@ResponseBody
	/***
	 * 获取聊天室详情
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map chatRoomDetail(
			@RequestParam(value = "chatRoomId") String chatRoomId,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();

		Map result = this.imManager.ChatRoomDetail(chatRoomId);

		this.result.put("result", result.get("data"));
		return getResult();
	}

	@RequestMapping(value = "im/userJoinedchatRoomList")
	@ResponseBody
	/***
	 * 获取用户加入的聊天室列表
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map userJoinedchatRoomList(
			@RequestParam(value = "name") String name, ModelMap map,
			HttpSession session) {
		this.result = new HashMap();

		Map result = this.imManager.userJoinedChatRoomList(name);

		this.result.put("result", result.get("data"));
		return getResult();
	}

	@RequestMapping(value = "im/addUserToChatRoom")
	@ResponseBody
	/***
	 * 添加用户到聊天室
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map addUserToChatRoom(
			@RequestParam(value = "chatRoomId") String chatRoomId,
			@RequestParam(value = "userId") Integer userId, ModelMap map,
			HttpSession session) {
		this.result = new HashMap();
		Users user = this.userManager.findUserById(userId);
		if (user != null) {
			Map result = this.imManager.addUserToChatRoom(chatRoomId,
					Config.IM_USER_NAME + user.getUserId());

			this.result.put("result", result.get("data"));
		}

		return getResult();
	}

	@RequestMapping(value = "im/addUsersToChatRoom")
	@ResponseBody
	/***
	 * 批量添加用户到聊天室
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map addUsersToChatRoom(
			@RequestParam(value = "chatRoomId") String chatRoomId,
			@RequestParam(value = "userId") String usersId, ModelMap map,
			HttpSession session) {
		this.result = new HashMap();
		// 批量
		String[] ids = usersId.split(",");
		List list = new ArrayList();
		for (String str : ids) {
			Integer userId = Integer.parseInt(str);
			Users user = this.userManager.findUserById(userId);
			list.add(Config.IM_USER_NAME + user.getUserId());
		}
		if (list != null) {
			Map result = this.imManager.addUsersToChatRoom(chatRoomId, list);

			this.result.put("result", result.get("data"));
		}

		return getResult();
	}

	@RequestMapping(value = "im/removeUserFromChatRoom")
	@ResponseBody
	/***
	 * 移除用户
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map removeUserFromChatRoom(
			@RequestParam(value = "chatRoomId") String chatRoomId,
			@RequestParam(value = "userId") Integer userId, ModelMap map,
			HttpSession session) {
		this.result = new HashMap();
		Users user = this.userManager.findUserById(userId);
		if (user != null) {
			Map result = this.imManager.removeUserToChatRoom(chatRoomId,
					Config.IM_USER_NAME + user.getUserId());

			this.result.put("result", result.get("data"));
		}

		return getResult();
	}

	@RequestMapping(value = "im/removeUsersFromChatRoom")
	@ResponseBody
	/***
	 * 批量移除用户
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map removeUsersFromChatRoom(
			@RequestParam(value = "chatRoomId") String chatRoomId,
			@RequestParam(value = "userId") String usersId, ModelMap map,
			HttpSession session) {
		this.result = new HashMap();
		// 批量
		String[] ids = usersId.split(",");
		List list = new ArrayList();
		for (String str : ids) {
			Integer userId = Integer.parseInt(str);
			Users user = this.userManager.findUserById(userId);
			list.add(Config.IM_USER_NAME + user.getUserId());
		}
		if (list != null) {
			Map result = this.imManager.removeUsersToChatRoom(chatRoomId, list);

			this.result.put("result", result.get("data"));
		}

		return getResult();
	}

	@RequestMapping(value = "im/addAllUserToIM")
	@ResponseBody
	/***
	 * 获取用户加入的聊天室列表
	 * @param chatRoomId
	 * @param map
	 * @param sessionF
	 * @return
	 */
	public Map addAllUserToIM(@RequestParam(value = "userId") Integer userId,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		List<Users> users = this.userManager.getUserDao().findByUserIdDesc(0, 300);
		for (Users user : users) {
			if (user != null) {
				Map result;
				if (user.getPassword() != null) {
					result = this.imManager.addUserToIM(Config.IM_USER_NAME
							+ user.getUserId(), user.getPassword(),
							user.getName());
				} else {
					result = this.imManager.addUserToIM(Config.IM_USER_NAME
							+ user.getUserId(), user.getWechatId(),
							user.getName());
				}
			}

		}

		// this.result.put("result", result.get("entities"));
		return getResult();
	}

	@RequestMapping(value = "im/addUserToIM")
	@ResponseBody
	/***
	 * 获取用户加入的聊天室列表
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map addUserToIM(@RequestParam(value = "userId") Integer userId,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		Users user = this.userManager.findUserById(userId);
		if (user != null) {
			Map result;
			if (user.getPassword() != null) {
				result = this.imManager.addUserToIM(
						Config.IM_USER_NAME + user.getUserId(),
						user.getPassword(), user.getName());
			} else {
				result = this.imManager.addUserToIM(
						Config.IM_USER_NAME + user.getUserId(),
						user.getWechatId(), user.getName());
			}
			this.result.put("result", result.get("entities"));
		}

		return getResult();
	}

	@RequestMapping(value = "im/IMUser")
	@ResponseBody
	/***
	 * 获取用户加入的聊天室列表
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map IMUser(@RequestParam(value = "userId") Integer userId,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		Users user = this.userManager.findUserById(userId);
		if (user != null) {
			Map result;
			String name = Config.IM_USER_NAME + user.getUserId();
			result = this.imManager.userDetail(name);
			this.result.put("result", result.get("entities"));
		}

		return getResult();
	}
	
	
	@RequestMapping(value = "im/IMRestUserPassword")
	@ResponseBody
	/***
	 * 获取用户加入的聊天室列表
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map IMRestUserPassword(
			@RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "password") String password,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		Users user = this.userManager.findUserById(userId);
		if (user != null) {
			Map result;
			String name = Config.IM_USER_NAME + user.getUserId();
			result = this.imManager.resetUser(name, password);
			this.result.put("result", result.get("entities"));
		}
		
		return getResult();
	}

	@RequestMapping(value = "im/modifyChatRoom")
	@ResponseBody
	/***
	 * 更新聊天室信息
	 * @param chatRoomId
	 * @param map
	 * @param session
	 * @return
	 */
	public Map modifyChatRoom(
			@RequestParam(value = "chatRoomId") String chatRoomId,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "desc") String description,
			@RequestParam(value = "maxusers") Integer maxusers, ModelMap map,
			HttpSession session) {
		this.result = new HashMap();

		Map result = this.imManager.modifyChatRoom(chatRoomId, name,
				description, maxusers);

		this.result.put("result", result.get("data"));
		return getResult();
	}

	@RequestMapping(value = "im/sendMessage")
	@ResponseBody
	public Map sendMessage(@RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "msg") String msg,
			@RequestParam(value = "type") short type, ModelMap map,
			HttpSession session) {
		this.result = new HashMap();
		String userName = Config.IM_USER_NAME + userId;
		List list = new ArrayList();
		list.add(userName);

		Map result = this.imManager.sendMessage(list, msg, type);

		this.result.put("result", result.get("data"));
		return getResult();
	}
	
	/***
	 * 搜索
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "newSystem/SearchChatRoomByName")
	@ResponseBody
	public Map SearchChatRoomByName(
			@RequestParam(value = "name", required = false) String key,
			   @RequestParam(value="menu",required=false)Integer menu,
			   @RequestParam(value="sortmenu",required=false)Integer sortmenu,
			   @RequestParam(value="submenu",required=false)Integer submenu,
			ModelMap map, HttpSession session) {
		this.result = new HashMap();
		List list = this.imManager.getChatRoomDao().findByKeyName(key);
		
		
		this.result.put("data", list);
		this.status = 200;
		this.message = "";

		return getResult();
	}

	/************************************** 聊天室 ****************************************************/

}
