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
import com.message.Enity.Imsetting;
import com.message.Enity.ImsettingDAO;

public class ImManager {
	private ImsettingDAO imSettingDao;

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
		ObjectMapper mapper = new ObjectMapper();

		List records = this.getImSettingDao().findAll();
		if (records == null || records.size() == 0) {
			return new ArrayList();
		}

		Imsetting record = (Imsetting) records.get(0);

		// 开始请求
		JSONObject result = HttpUtil.sendImRequestHtt(HttpMethod.POST,
				null, "users?limit=" + size, record.getAccessTokean());
		System.out.print(result);
		// 解析保存
		if (result != null) {

		}

		return new ArrayList();
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

}
