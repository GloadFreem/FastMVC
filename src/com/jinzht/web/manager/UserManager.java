package com.jinzht.web.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.ActionDAO;
import com.jinzht.web.dao.CollectionDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.RewardsystemDAO;
import com.jinzht.web.dao.RewardtradeDAO;
import com.jinzht.web.dao.SystemcodeDAO;
import com.jinzht.web.dao.UsercollectDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Collection;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Systemcode;
import com.jinzht.web.entity.Usercollect;
import com.jinzht.web.entity.Users;

public class UserManager {
	
//	private static UsersDAO userDao = (UsersDAO)context.getBean("UsersDAO");
	private UsersDAO userDao;
	private LoginfailrecordDAO loginfailrecordDao;
	private IdentiytypeDAO identitytypeDao;
	private SystemcodeDAO systemCodeDao;
	private CollectionDAO collectionDao;
	private UsercollectDAO userCollectDao;
	private ActionDAO actionDao;
	private RewardtradeDAO rewardTradeDao;
	
	
	/***
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
			Users user = getUserDao().findByTelephone(telephone).get(0);
			user.setAuthentics(null);
			user.setActionshares(null);
			user.setCollections(null);
			user.setUserstatus(null);
			user.setWechatId(null);
			user.setRegId(null);
			user.setPlatform(null);
			user.setLastLoginDate(null);
			user.setInvestmentrecords(null);
			user.setInvestorcollectsForUserCollectedId(null);
			user.setInvestorcollectsForUserId(null);
			user.setInviterecords(null);
			return user;
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
	
	/***
	 * 生成邀请码
	 * @param code
	 */
	public void saveSystemCode(Systemcode code){
		getSystemCodeDao().save(code);
	}
	
	/***
	 * 根据用户获取用户邀请码
	 * @param user
	 * @return
	 */
	public Systemcode findSystemCodeByUser(Users user){
		List list =  getSystemCodeDao().findByProperty("users", user);
		
		if(list!=null && list.size()>0)
		{
			return (Systemcode) list.get(0);
		}
		
		return null;
	}
	
	public List findUserCollectionProjects(Users user,Integer page)
	{
		List list = null;
		Map map = new HashMap();
		map.put("users", user);
		
		list = getCollectionDao().findByPropertiesWithPage(map, page);
		if(list!=null && list.size()>0)
		{
			Collection collection = null;
			for(int i = 0;i<list.size();i++){
				collection = (Collection) list.get(i);
				collection.setUsers(null);
			}
		}
		
		return list;
	}
	
	/***
	 * 获取用户活动
	 * @param user
	 * @param page
	 * @return
	 */
	public List findUserAttendActions(Users user,Integer page)
	{
		List list = null;
		Map map = new HashMap();
		map.put("users", user);
		list = getActionDao().findByPropertiesWithPage(map, page);
		if(list!=null && list.size()>0)
		{
			Collection collection = null;
			for(int i = 0;i<list.size();i++){
				collection = (Collection) list.get(i);
				Project project = collection.getProject();
				
				project.setCommunions(null);
				project.setMembers(null);
				project.setTeams(null);
				project.setFinancingexit(null);
				project.setFinancialstanding(null);
				project.setControlreport(null);
				project.setFinancingcase(null);
				project.setBusinessplan(null);
				project.setProjectcomments(null);
				project.setProjectcommitrecords(null);
				project.setProjectimageses(null);
			}
		}
		
		return list;
	}
	public List findUserCollectionUsers(Users user,Integer page)
	{
		List list = null;
		Map map = new HashMap();
		map.put("usersByUserId", user);
		list = getUserCollectDao().findByPropertiesWithPage(map, page);
		if(list!=null && list.size()>0)
		{
			Usercollect collect = null;
			for(int i = 0;i<list.size();i++){
				collect = (Usercollect) list.get(i);
				Users u = collect.getUsersByUserId();
				u.setUserstatus(null);
				u.setTelephone(null);
				u.setPassword(null);
				u.setPlatform(null);
//				u.setAuthentics(null);
				u.setLastLoginDate(null);
				u.setHeadSculpture(user.getHeadSculpture());
			}
		}
		
		return list;
	}
	
	public List findGoldTradList(Rewardsystem rewardSystem,Integer page)
	{
		List list =null;
		Map map = new HashMap();
		map.put("rewardsystem", rewardSystem);
		
		list  = getRewardTradeDao().findByPropertiesWithPage(map, page);
		return list;
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

	public SystemcodeDAO getSystemCodeDao() {
		return systemCodeDao;
	}

	@Autowired
	public void setSystemCodeDao(SystemcodeDAO systemCodeDao) {
		this.systemCodeDao = systemCodeDao;
	}

	public CollectionDAO getCollectionDao() {
		return collectionDao;
	}
	@Autowired
	public void setCollectionDao(CollectionDAO collectionDao) {
		this.collectionDao = collectionDao;
	}

	public UsercollectDAO getUserCollectDao() {
		return userCollectDao;
	}
	@Autowired
	public void setUserCollectDao(UsercollectDAO userCollectDao) {
		this.userCollectDao = userCollectDao;
	}

	public ActionDAO getActionDao() {
		return actionDao;
	}
	@Autowired
	public void setActionDao(ActionDAO actionDao) {
		this.actionDao = actionDao;
	}

//	public RewardtradeDAO getRewardSystemDao() {
//		return rewardSystemDao;
//	}
//	@Autowired
//	public void setRewardSystemDao(RewardtradeDAO rewardSystemDao) {
//		this.rewardSystemDao = rewardSystemDao;
//	}

	public RewardtradeDAO getRewardTradeDao() {
		return rewardTradeDao;
	}
	@Autowired
	public void setRewardTradeDao(RewardtradeDAO rewardTradeDao) {
		this.rewardTradeDao = rewardTradeDao;
	}



}
