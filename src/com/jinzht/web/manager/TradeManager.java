package com.jinzht.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.BannerDAO;
import com.jinzht.web.dao.NoticeDAO;
import com.jinzht.web.dao.PreloadingpageDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.SustomserviceDAO;
import com.jinzht.web.dao.SystemmessageDAO;
import com.jinzht.web.dao.TraderecordDAO;
import com.jinzht.web.dao.VersioncontrollDAO;
import com.jinzht.web.entity.Notice;
import com.jinzht.web.entity.Preloadingpage;
import com.jinzht.web.entity.Customservice;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Systemmessage;
import com.jinzht.web.entity.Traderecord;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Versioncontroll;

public class TradeManager {
	private TraderecordDAO tradeRecordDao;
	
	/***
	 * 获取用户交易流水
	 * @param user 用户
	 * @param page 当前页码
	 * @return
	 */
	public List findTradeRecordByUser(Users user,Integer page)
	{
		List list = null;
		
		list = getTradeRecordDao().findByPropertyWithPage("users", user, page);
		
		return list;
	}
	/***
	 * 添加交易记录
	 * @param user
	 * @param record
	 */
	public void addTradeRecord(Users user,Traderecord record)
	{
		getTradeRecordDao().save(record);
	}

	public TraderecordDAO getTradeRecordDao() {
		return tradeRecordDao;
	}
	@Autowired
	public void setTradeRecordDao(TraderecordDAO tradeRecordDao) {
		this.tradeRecordDao = tradeRecordDao;
	}

}
