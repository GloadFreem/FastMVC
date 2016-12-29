package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

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

public class BusinessesWebManager {
	private ContenttypeDAO contentTypeDao;
	private WeburlrecordDAO webUrlRecordDao;
	
	/***
	 * 添加网页记录
	 * @param record
	 */
	public void addWebUrlRecord(Weburlrecord record)
	{
		getWebUrlRecordDao().save(record);
	}
	
	public Weburlrecord findRecordById(Integer contentId)
	{
		return getWebUrlRecordDao().findById(contentId);
	}
	
	public ContenttypeDAO getContentTypeDao() {
		return contentTypeDao;
	}
	@Autowired
	public void setContentTypeDao(ContenttypeDAO contentTypeDao) {
		this.contentTypeDao = contentTypeDao;
	}
	public WeburlrecordDAO getWebUrlRecordDao() {
		return webUrlRecordDao;
	}
	@Autowired
	public void setWebUrlRecordDao(WeburlrecordDAO webUrlRecordDao) {
		this.webUrlRecordDao = webUrlRecordDao;
	}
	
}
