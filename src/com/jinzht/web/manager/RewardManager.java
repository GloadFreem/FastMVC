package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.ActionDAO;
import com.jinzht.web.dao.ActioncommentDAO;
import com.jinzht.web.dao.ActionimagesDAO;
import com.jinzht.web.dao.ActionpriseDAO;
import com.jinzht.web.dao.AttentionDAO;
import com.jinzht.web.dao.CityDAO;
import com.jinzht.web.dao.ContentpriseDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.PubliccontentDAO;
import com.jinzht.web.dao.RewardsystemDAO;
import com.jinzht.web.dao.RewardtradeDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Actionprise;
import com.jinzht.web.entity.Attention;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Rewardtrade;
import com.jinzht.web.entity.Rewardtradetype;
import com.jinzht.web.entity.Users;

public class RewardManager {
	private RewardsystemDAO rewardSystemDao;
	private RewardtradeDAO rewardTradeDao;
	
	/***
	 * 获取用户金条总数
	 * @param user
	 * @return
	 */
	public Integer findUserRewardCount(Users user)
	{
		//获取用户金条系统
		List list = getRewardSystemDao().findByProperty("users", user);
		if(list!= null && list.size()>0)
		{
			Rewardsystem system = (Rewardsystem) list.get(0);
			//返回金条总数
			return system.getCount();
		}else{
			//生成金条账户
			Rewardsystem system = new Rewardsystem();
			system.setCount(18);
			system.setUsers(user);
			
			Rewardtrade trade = new Rewardtrade();
			Set set = new HashSet();
			
			Rewardtradetype  type = new Rewardtradetype();
			type.setRewardTypeId(2);
			
			trade.setTradeDate(new Date());
			trade.setReaded(false);
			trade.setDesc("注册奖励");
			trade.setCount(18);
			trade.setRewardsystem(system);
			trade.setRewardtradetype(type);
			
			set.add(trade);
			system.setRewardtrades(set);
			
			getRewardSystemDao().save(system);
			
			return system.getCount();
		}
		
	}
	/***
	 * 获取用户金条账户
	 * @param user
	 * @return
	 */
	public Rewardsystem findRewardByUser(Users user)
	{
		//获取用户金条系统
		List list = getRewardSystemDao().findByProperty("users", user);
		if(list!= null && list.size()>0)
		{
			Rewardsystem system = (Rewardsystem) list.get(0);
			return system;
		}
		
		return null;
	}
	
	/***
	 * 获取用户应该显示奖励信息
	 * @param user
	 * @return
	 */
	public Map findRewardRecordNoRead(Users user)
	{
		Map map = new HashMap();
		Integer count = 0;
		
		//获取用户金条系统
		List list = getRewardSystemDao().findByProperty("users", user);
		
		List result = null;
		if(list!= null && list.size()>0)
		{
			Rewardsystem system = (Rewardsystem) list.get(0);
			
			Map requestMap = new HashMap();
			requestMap.put("rewardsystem", system);
			
			result  = getRewardTradeDao().findByProperties(requestMap);
			
			for(Object t : result)
			{
				Rewardtrade trade = (Rewardtrade)t;
				count += trade.getCount();
			}
		}
		
		
		
		
		count = 5;
		//请求参数封装
		map.put("count", count);
		map.put("countTomorrow", ++count);
		return map;
	}
	public List findRewardRecordList(Users user,Integer page)
	{
		//获取用户金条系统
		List list = getRewardSystemDao().findByProperty("users", user);
		
		List result = null;
		if(list!= null && list.size()>0)
		{
			Rewardsystem system = (Rewardsystem) list.get(0);
			
			Map requestMap = new HashMap();
			requestMap.put("rewardsystem", system);
			
			result  = getRewardTradeDao().findByPropertiesWithPage(requestMap, page);
		}
		return result;
	}
	
	
	public RewardsystemDAO getRewardSystemDao() {
		return rewardSystemDao;
	}

	@Autowired
	public void setRewardSystemDao(RewardsystemDAO rewardSystemDao) {
		this.rewardSystemDao = rewardSystemDao;
	}

	public RewardtradeDAO getRewardTradeDao() {
		return rewardTradeDao;
	}
	@Autowired
	public void setRewardTradeDao(RewardtradeDAO rewardTradeDao) {
		this.rewardTradeDao = rewardTradeDao;
	}

}
