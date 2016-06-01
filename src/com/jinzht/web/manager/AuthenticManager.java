package com.jinzht.web.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.CityDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.IndustorytypeDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Users;

public class AuthenticManager {
	
	private IdentiytypeDAO identitytypeDao;
	private IndustorytypeDAO industorytypeDao;
	private IndustoryareaDAO industoryareaDao;
	private CityDAO cityDao;
	
	/***
	 * 获取身份类型
	 * @param typeId 身份id
	 * @return
	 */
	public Identiytype findIdentityTypeById(short typeId)
	{
		return getIdentitytypeDao().findById(typeId);
	}
	
	/***
	 * 获取所有的行业类型
	 * @return
	 */
	public List findAllIndustoryType()
	{
		return getIndustorytypeDao().findAll();
	}
	
	/***
	 * 获取省份列表
	 */
	public List findAllProvinceList()
	{
		return getIndustoryareaDao().findAll();
	}
	
	/***
	 * 获取所有城市列表
	 * @return
	 */
	public List findAllCityList()
	{
		return getCityDao().findAll();
	}
	

	public IdentiytypeDAO getIdentitytypeDao() {
		return identitytypeDao;
	}
	@Autowired
	public void setIdentitytypeDao(IdentiytypeDAO identitytypeDao) {
		this.identitytypeDao = identitytypeDao;
	}


	public IndustoryareaDAO getIndustoryareaDao() {
		return industoryareaDao;
	}

	@Autowired
	public void setIndustoryareaDao(IndustoryareaDAO industoryareaDao) {
		this.industoryareaDao = industoryareaDao;
	}


	public IndustorytypeDAO getIndustorytypeDao() {
		return industorytypeDao;
	}

	@Autowired
	public void setIndustorytypeDao(IndustorytypeDAO industorytypeDao) {
		this.industorytypeDao = industorytypeDao;
	}

	public CityDAO getCityDao() {
		return cityDao;
	}
	@Autowired
	public void setCityDao(CityDAO cityDao) {
		this.cityDao = cityDao;
	}
}
