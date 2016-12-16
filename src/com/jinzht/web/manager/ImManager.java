package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinzht.tools.Config;
import com.jinzht.tools.DateUtils;
import com.jinzht.tools.HttpUtil;
import com.jinzht.web.dao.ActionDAO;
import com.jinzht.web.dao.AttentionDAO;
import com.jinzht.web.dao.CollectionDAO;
import com.jinzht.web.dao.ContenttypeDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.RewardsystemDAO;
import com.jinzht.web.dao.RewardtradeDAO;
import com.jinzht.web.dao.SystemcodeDAO;
import com.jinzht.web.dao.UsercollectDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.dao.WeburlrecordDAO;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Attention;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Collection;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Systemcode;
import com.jinzht.web.entity.Usercollect;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Weburlrecord;
import com.message.Enity.ChatroomDAO;
import com.message.Enity.Imsetting;
import com.message.Enity.ImsettingDAO;

public class ImManager {
	private ImsettingDAO imSettingDao;
	private ChatroomDAO chatRoomDao;

	/***
	 * 获取授权信息
	 * 
	 * @return
	 */
	public boolean requestToken() {

		JSONObject obj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		obj.put("grant_type", "client_credentials");
		obj.put("client_id", Config.IM_CLIENT_ID);
		obj.put("client_secret", Config.IM_CLIENT_SECRET);

		try {
			String json = mapper.writeValueAsString(obj);
			// 开始请求
			JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.POST,
					json, "token", null);
			System.out.print(result);
			// 解析保存
			if (result != null) {
				Imsetting setting = new Imsetting();
				setting.setAccessTokean(result.getString("access_token"));
				setting.setExpires(result.getInt("expires_in"));
				setting.setRecordDate(new Date());

				getImSettingDao().save(setting);
			}

			return true;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean isNeedRequestToken() {
		// 获取数据库中记录
		List list = this.getImSettingDao().findAll();
		if (list == null || list.size() == 0) {
			return false;
		}

		Imsetting record = (Imsetting) list.get(0);
		// 判断是否已经过期
		Date recordDate = record.getRecordDate();
		long dinstance = DateUtils.compare_date(new Date(), recordDate);

		if (dinstance < 10000) {
			return true;
		}

		return false;
	}

	public List getUsers(Integer page, Integer size) {
		List list = new ArrayList();
		List records = this.getImSettingDao().findAll();
		if (records == null || records.size() == 0) {
			return new ArrayList();
		}

		Imsetting record = (Imsetting) records.get(0);

		// 开始请求
		JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.GET, null,
				"users?limit=" + size, record.getAccessTokean());
		list = (List) result.get("entities");
		// 解析保存
		if (list != null) {
			return list;
		}

		return new ArrayList();
	}

	/***
	 * 聊天室列表
	 * 
	 * @return
	 */
	public Map chatRoomList(Integer page, Integer size) {
		List list = new ArrayList();

		// 创建传参
		List records = this.getImSettingDao().findAll();
		if (records == null || records.size() == 0) {
			return null;
		}

		Imsetting record = (Imsetting) records.get(0);

		// 开始请求
		JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.GET, null,
				"chatrooms?pagenum=" + page + "&pagesize=" + size,
				record.getAccessTokean());
		// Map map = (HashMap) result.get("data");
		// 解析保存
		return result;
	}

	/***
	 * 删除聊天室
	 * 
	 * @return
	 */
	public Map deleteChatRoom(String chatRoomId) {
		List list = new ArrayList();

		// 创建传参
		List records = this.getImSettingDao().findAll();
		if (records == null || records.size() == 0) {
			return null;
		}

		Imsetting record = (Imsetting) records.get(0);

		// 开始请求
		JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.DELETE, null,
				"chatrooms/" + chatRoomId, record.getAccessTokean());
		// Map map = (HashMap) result.get("data");
		// 解析保存
		return result;
	}

	/***
	 * 删除聊天室
	 * 
	 * @return
	 */
	public Map ChatRoomDetail(String chatRoomId) {
		List list = new ArrayList();

		// 创建传参
		List records = this.getImSettingDao().findAll();
		if (records == null || records.size() == 0) {
			return null;
		}

		Imsetting record = (Imsetting) records.get(0);

		// 开始请求
		JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.GET, null,
				"chatrooms/" + chatRoomId, record.getAccessTokean());
		// Map map = (HashMap) result.get("data");
		// 解析保存
		return result;
	}

	/***
	 * 创建聊天室
	 * 
	 * @param name
	 *            聊天室名称
	 * @param description
	 *            聊天室简介
	 * @param maxusers
	 *            最大成员数
	 * @param owner
	 *            群主
	 * @return
	 */
	public Map createChatRoom(String name, String description,
			Integer maxusers, Users owner) {
		List list = new ArrayList();

		// 创建传参
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("description", description);
		jsonObject.put("maxusers", maxusers);
		jsonObject.put("owner", Config.IM_USER_NAME+owner.getUserId());

		// 转换格式
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(jsonObject);

			List records = this.getImSettingDao().findAll();
			if (records == null || records.size() == 0) {
				return null;
			}

			Imsetting record = (Imsetting) records.get(0);

			// 开始请求
			JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.POST,
					json, "chatrooms", record.getAccessTokean());
			// Map map = (HashMap) result.get("data");
			// 解析保存
			return result;

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/***
	 * 修改群聊信息
	 * 
	 * @param chatRoomId
	 *            聊天室id
	 * @param name
	 *            聊天室名称
	 * @param description
	 *            描述
	 * @param maxusers
	 *            最大人数限制
	 * @return
	 */
	public Map modifyChatRoom(String chatRoomId, String name,
			String description, Integer maxusers) {
		List list = new ArrayList();

		// 创建传参
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("description", description);
		jsonObject.put("maxusers", maxusers);
		// jsonObject.put("owner", owner.getName());
		// jsonObject.put("owner", "jinzhttest13186156568");

		// 转换格式
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(jsonObject);

			List records = this.getImSettingDao().findAll();
			if (records == null || records.size() == 0) {
				return null;
			}

			Imsetting record = (Imsetting) records.get(0);

			// 开始请求
			JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.PUT, json,
					"chatrooms/" + chatRoomId, record.getAccessTokean());
			// Map map = (HashMap) result.get("data");
			// 解析保存
			return result;

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public Map sendMessage(List userName, String msg, short type) {
		List list = new ArrayList();

		// 创建传参
		JSONObject jsonObject = new JSONObject();
		Map map = new HashMap();

		String t = "txt";
		switch (type) {
		case 1:
			t = "img";
			break;
		case 2:
			t = "audio";
			break;
		case 3:
			t = "video";
			break;
		case 4:
			t = "cmd";
			break;
		default:
			t = "txt";
			break;
		}

		map.put("type", t);
		map.put("msg", msg);

		jsonObject.put("target_type", "users");
		jsonObject.put("target", userName);
		jsonObject.put("msg", map);

		// 转换格式
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(jsonObject);

			List records = this.getImSettingDao().findAll();
			if (records == null || records.size() == 0) {
				return null;
			}

			Imsetting record = (Imsetting) records.get(0);

			// 开始请求
			JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.POST,
					json, "messages/", record.getAccessTokean());
			// Map map = (HashMap) result.get("data");
			// 解析保存
			return result;

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/***
	 * 获取用户参加的聊天室
	 * 
	 * @param name
	 *            用户名称
	 * @return
	 */
	public Map userJoinedChatRoomList(String name) {
		List list = new ArrayList();

		List records = this.getImSettingDao().findAll();
		if (records == null || records.size() == 0) {
			return null;
		}

		Imsetting record = (Imsetting) records.get(0);

		// 开始请求
		JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.GET, null,
				"users/" + name + "/joined_chatrooms/",
				record.getAccessTokean());
		// Map map = (HashMap) result.get("data");
		// 解析保存
		return result;
	}

	/***
	 * 添加用户到聊天室
	 * 
	 * @param chatRoomId
	 *            聊天室id
	 * @param name
	 *            用户名称
	 * @return
	 */
	public Map addUserToChatRoom(String chatRoomId, String name) {
		List list = new ArrayList();

		List records = this.getImSettingDao().findAll();
		if (records == null || records.size() == 0) {
			return null;
		}

		Imsetting record = (Imsetting) records.get(0);

		// 开始请求
		JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.POST, null,
				"chatrooms/" + chatRoomId + "/users/" + name,
				record.getAccessTokean());
		// Map map = (HashMap) result.get("data");
		// 解析保存
		return result;
	}

	/***
	 * 移除用户
	 * 
	 * @param chatRoomId
	 *            聊天室id
	 * @param name
	 *            用户名称
	 * @return
	 */
	public Map removeUserToChatRoom(String chatRoomId, String name) {
		List list = new ArrayList();

		List records = this.getImSettingDao().findAll();
		if (records == null || records.size() == 0) {
			return null;
		}

		Imsetting record = (Imsetting) records.get(0);

		// 开始请求
		JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.DELETE, null,
				"chatrooms/" + chatRoomId + "/users/" + name,
				record.getAccessTokean());
		// Map map = (HashMap) result.get("data");
		// 解析保存
		return result;
	}

	/***
	 * 批量添加用户到聊天室
	 * 
	 * @param chatRoomId
	 *            聊天室id
	 * @param name
	 *            用户名称
	 * @return
	 */
	public Map addUsersToChatRoom(String chatRoomId, List names) {
		List list = new ArrayList();

		// 创建传参
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("usernames", names);

		// 转换格式
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(jsonObject);

			List records = this.getImSettingDao().findAll();
			if (records == null || records.size() == 0) {
				return null;
			}

			Imsetting record = (Imsetting) records.get(0);

			// 开始请求
			JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.POST,
					json, "chatrooms/" + chatRoomId + "/users",
					record.getAccessTokean());
			// Map map = (HashMap) result.get("data");
			// 解析保存
			return result;

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/***
	 * 批量移除用户
	 * 
	 * @param chatRoomId
	 *            聊天室id
	 * @param name
	 *            用户名称
	 * @return
	 */
	public Map removeUsersToChatRoom(String chatRoomId, List names) {
		List list = new ArrayList();

		List records = this.getImSettingDao().findAll();
		if (records == null || records.size() == 0) {
			return null;
		}

		Imsetting record = (Imsetting) records.get(0);

		String url = "chatrooms/" + chatRoomId + "/users/";
		for (Object name : names) {
			String nameStr = name.toString();
			url += nameStr + ",";
		}

		// 开始请求
		JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.DELETE, null,
				url, record.getAccessTokean());
		// Map map = (HashMap) result.get("data");
		// 解析保存
		return result;
	}

	/***
	 * 注册用户
	 * 
	 * @param name
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 */
	public Map addUserToIM(String name, String password, String nickname) {
		List list = new ArrayList();

		// 创建传参
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", name);
		jsonObject.put("password", password);
		jsonObject.put("nickname", nickname);

		// 转换格式
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(jsonObject);

			List records = this.getImSettingDao().findAll();
			if (records == null || records.size() == 0) {
				return null;
			}

			Imsetting record = (Imsetting) records.get(0);

			// 开始请求
			JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.POST,
					json, "users/", record.getAccessTokean());
			// Map map = (HashMap) result.get("data");
			// 解析保存
			return result;

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	/***
	 * 重置用户
	 * 
	 * @param name
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return
	 */
	public Map resetUser(String name, String password) {
		List list = new ArrayList();
		
		// 创建传参
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("newpassword", password);
		
		// 转换格式
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(jsonObject);
			
			List records = this.getImSettingDao().findAll();
			if (records == null || records.size() == 0) {
				return null;
			}
			
			Imsetting record = (Imsetting) records.get(0);
			
			// 开始请求
			JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.PUT,
					json, "users/"+name+"/password", record.getAccessTokean());
			// Map map = (HashMap) result.get("data");
			// 解析保存
			return result;
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/***
	 * 注册用户
	 * 
	 * @param name
	 *            用户名称
	 * @return
	 */
	public Map userDetail(String name) {
		List list = new ArrayList();
		
		List records = this.getImSettingDao().findAll();
		if (records == null || records.size() == 0) {
			return null;
		}
		
		Imsetting record = (Imsetting) records.get(0);
		
		// 开始请求
		JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.POST,
				null, "users/"+name, record.getAccessTokean());
		// Map map = (HashMap) result.get("data");
		// 解析保存
		return result;
		
	}

	/**
	 * @return the imSettingDao
	 */
	public ImsettingDAO getImSettingDao() {
		return imSettingDao;
	}

	/**
	 * @param imSettingDao
	 *            the imSettingDao to set
	 */
	@Autowired
	public void setImSettingDao(ImsettingDAO imSettingDao) {
		this.imSettingDao = imSettingDao;
	}

	/**
	 * @return the chatRoomDao
	 */
	public ChatroomDAO getChatRoomDao() {
		return chatRoomDao;
	}

	/**
	 * @param chatRoomDao
	 *            the chatRoomDao to set
	 */
	@Autowired
	public void setChatRoomDao(ChatroomDAO chatRoomDao) {
		this.chatRoomDao = chatRoomDao;
	}

}
