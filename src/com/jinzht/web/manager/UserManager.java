package com.jinzht.web.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Users;

public class UserManager {
	
//	private static UsersDAO userDao = (UsersDAO)context.getBean("UsersDAO");
	private UsersDAO userDao;
	private LoginfailrecordDAO loginfailrecordDao;
	private IdentiytypeDAO identitytypeDao;
	
	
	/***
	 * 获取所有用户
	 * @return 用户列表
	 */
	public List<Users> findAllUsers()
	{ 	
		return getUserDao().findAll();
	}
	
	public Users findUserById(Integer userId)
	{
		return getUserDao().findById(userId);
	}
	
	/***
	 * 根据用户手机号码获取用户对象
	 * @param telephone 手机号码
	 * @return Users 对象
	 */
	public Users findUserByTelephone(String telephone)
	{
		List list = getUserDao().findByTelephone(telephone);
		if(list!=null && list.size()>0){
			return getUserDao().findByTelephone(telephone).get(0);
		}
		
		return null;
	}
	
	public Users findUserByWechatId(String wechatId)
	{
		List list =  getUserDao().findByWechatId(wechatId);
		
		if(list!=null && list.size()>0)
		{
			return (Users) list.get(0);
		}
		
		return null;
	}
	
	/***
	 * 新添加用户
	 * @param user 用户实例
	 * @return Users 对象
	 */
	public Users addUser(Users user)
	{
		return getUserDao().save(user);
	}
	
	/***
	 * 更新或增加用户
	 * @param user
	 * @return
	 */
	public Users saveOrUpdateUser(Users user)
	{
		return getUserDao().saveOrUpdate(user);
	}
	
	/***
	 * 获取登录失败记录
	 * @param date
	 * @return
	 */
	public Loginfailrecord findRecordByDate(Date date)
	{
		List list = getLoginfailrecordDao().findByProperty("loginFailDate", date);
		if(list != null && list.size()>0)
		{
			return (Loginfailrecord) list.get(0);
		}
		
		return null;
	}
	
	/***
	 * 添加或更新登录失败记录
	 * @param record
	 * @param isUpdate
	 * @return
	 */
	public Loginfailrecord addOrUpdateLoginFailRecord(Loginfailrecord record,boolean isUpdate)
	{
		Map map = new HashMap();
		map.put("users", record.getUsers());
		map.put("loginFailDate", new Date());
		//获取当日登录失败记录
		List list  = getLoginfailrecordDao().findByProperties(map);
		Loginfailrecord recordInstance = null;
		if(list != null && list.size()>0){
			recordInstance = (Loginfailrecord) list.get(0);
		}
		
		if(isUpdate){
			//判断是否是第一次登录失败
			short count=0;
			if(recordInstance == null){
				
				record.setCount(count);
			}else{
				recordInstance.setPlatform(record.getPlatform());
				record = recordInstance;
				count = recordInstance.getCount();
			}
			
			if(count<5){
				count++;
				record.setCount(count);				
			}else{
				return record;
			}
			
			//更新信息
			getLoginfailrecordDao().update(record);
		}else{
			record = recordInstance;
		}
		
		return record;
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
	
	public UsersDAO getUserDao() {
		return userDao;
	}
	
	@Autowired
	public void setUserDao(UsersDAO userDao) {
		this.userDao = userDao;
	}

	public LoginfailrecordDAO getLoginfailrecordDao() {
		return loginfailrecordDao;
	}
	@Autowired
	public void setLoginfailrecordDao(LoginfailrecordDAO loginfailrecordDao) {
		this.loginfailrecordDao = loginfailrecordDao;
	}

	public IdentiytypeDAO getIdentitytypeDao() {
		return identitytypeDao;
	}
	@Autowired
	public void setIdentitytypeDao(IdentiytypeDAO identitytypeDao) {
		this.identitytypeDao = identitytypeDao;
	}
}
