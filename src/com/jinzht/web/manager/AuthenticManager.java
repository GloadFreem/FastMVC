package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.AuthenticDAO;
import com.jinzht.web.dao.AuthenticstatusDAO;
import com.jinzht.web.dao.CityDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Users;

public class AuthenticManager {
	
	private IdentiytypeDAO identitytypeDao;
	private IndustoryareaDAO industoryareaDao;
	private ProvinceDAO provinceDao;
	private CityDAO cityDao;
	private AuthenticDAO authenticDao;
	private AuthenticstatusDAO authenticStatus;
	
	/***
	 * 保存认证信息
	 * @param authentic
	 */
	public void saveAuthentic(Authentic authentic){
		getAuthenticDao().save(authentic);
	}
	
	
	/***
	 * 更新认证信息
	 * @param authentic
	 */
	public void updateAuthentic(Authentic authentic){
		getAuthenticDao().saveOrUpdate(authentic);
	}
	
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
	 * 获取省份列表
	 */
	public List findAllProvinceList()
	{
		return getProvinceDao().findAll();
	}
	
	/***
	 * 获取所有城市列表
	 * @return
	 */
	public List findAllCityList()
	{
		return getCityDao().findAll();
	}
	
	/***
	 * 根据cityid查询City对象
	 * @param cityId
	 * @return
	 */
	public City findCityByCityId(Integer cityId){
		return getCityDao().findById(cityId);
	}
	/***
	 * 根据城市查询省份
	 * @param provinceId
	 * @return
	 */
	public List findCitiesByProvinceId(Integer provinceId){
		List list = this.findAllCityList();
		List result = new ArrayList();
		Map map =null;
		for(int i = 0; i<list.size();i++){
			City city = (City) list.get(i);
			if(city.getProvince().getProvinceId()==provinceId){
				map = new HashMap();
				map.put("cityId", city.getCityId());
				map.put("name", city.getName());
				result.add(map);
			}
		}
		return result;
	}
	
	/***
	 * 获取行业类类型
	 * @return
	 */
	public List findAllIndustoryList()
	{
		return getIndustoryareaDao().findAll();
	}
	
	/***
	 * 根据id获取领域
	 * @param areaId
	 * @return
	 */
	public Industoryarea findIndustoryAreaById(Integer areaId){
		return getIndustoryareaDao().findById(areaId);
	}
	
	/***
	 * 根据authId 获取认证记录
	 * @param authId
	 * @return
	 */
	public Authentic findAuthenticById(Integer authId)
	{
		List list = getAuthenticDao().findByProperty("authId", authId);
		if(list!=null && list.size()>0)
		{
			return (Authentic) list.get(0);
		}
		return null;
	}
	
	public Authentic findAuthenticByUserId(Integer userId)
	{
		List list = this.authenticDao.findAuthenticByUserId(userId);
		if(list!=null && list.size()>0)
		{
			return (Authentic)list.get(0);
		}
		return null;
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


	public CityDAO getCityDao() {
		return cityDao;
	}
	@Autowired
	public void setCityDao(CityDAO cityDao) {
		this.cityDao = cityDao;
	}

	public ProvinceDAO getProvinceDao() {
		return provinceDao;
	}
	@Autowired
	public void setProvinceDao(ProvinceDAO provinceDao) {
		this.provinceDao = provinceDao;
	}

	public AuthenticDAO getAuthenticDao() {
		return authenticDao;
	}
	@Autowired
	public void setAuthenticDao(AuthenticDAO authenticDao) {
		this.authenticDao = authenticDao;
	}

	public AuthenticstatusDAO getAuthenticStatus() {
		return authenticStatus;
	}
	@Autowired
	public void setAuthenticStatus(AuthenticstatusDAO authenticStatus) {
		this.authenticStatus = authenticStatus;
	}
}
