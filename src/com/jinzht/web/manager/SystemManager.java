package com.jinzht.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.BannerDAO;
import com.jinzht.web.dao.NoticeDAO;
import com.jinzht.web.dao.PreloadingpageDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.SustomserviceDAO;
import com.jinzht.web.dao.SystemmessageDAO;
import com.jinzht.web.dao.VersioncontrollDAO;
import com.jinzht.web.entity.Notice;
import com.jinzht.web.entity.Preloadingpage;
import com.jinzht.web.entity.Customservice;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Systemmessage;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Versioncontroll;

public class SystemManager {

	private NoticeDAO noticeDao; // 系统公告
	private PreloadingpageDAO preloadingDao; // 启动页
	private BannerDAO bannerDao; //Banner 信息
	private SustomserviceDAO sustomserviceDao; //客服信息
	private VersioncontrollDAO versioncontrollDao; // 版本更新信息
	private ShareDAO shareDao;
	private SystemmessageDAO systemMessageDao;
	

	/***
	 * 根据设备类型获取系统公告信息
	 * 
	 * @param platform
	 *            设备类型 0:Android,1:iOS
	 * @return 最近系统公告
	 */
	public Notice findNoticeByPlatform(short platform) {
		List<Notice> list = getNoticeDao().findByPlatform(platform);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/***
	 * 根据设备类型获取启动页信息
	 * 
	 * @param platform
	 *            设备类型 0:Android,1:iOS
	 * @return 最新启动页面信息
	 */
	public Preloadingpage findPreloadingPageByPlatform(short platform) {
		List<Preloadingpage> list = getPreloadingDao().findByPlatform(platform);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/***
	 * 根据设备类型获取版本更新信息
	 * 
	 * @param platform
	 *            设备类型 0:Android,1:iOS
	 * @return 最新启动页面信息
	 */
	public Versioncontroll findVersionInfoByPlatform(short platform) {
		List<Versioncontroll> list = getVersioncontrollDao().findByPlatform(
				platform);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/***
	 * 获取客服信息
	 * 
	 * @param platform
	 *            设备类型 0:Android,1:iOS
	 * @return 最新启动页面信息
	 */
	public Customservice findCustomServices() {
		List<Customservice> list = getSustomserviceDao().findAll();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/***
	 * 获取客服信息
	 * 
	 * @param platform
	 *            设备类型 0:Android,1:iOS
	 * @return 最新启动页面信息
	 */
	public List<Customservice> findBannerInfoList() {
		List<Customservice> list = getBannerDao().findAll();
		return list;
	}

	public NoticeDAO getNoticeDao() {
		return noticeDao;
	}
	
	/***
	 * 保存分享记录
	 * @param share
	 */
	public void saveShareRecord(Share share)
	{
		getShareDao().save(share);
	}
	
	/***
	 * 获取用户站内信list
	 * @param user
	 * @param page
	 * @return
	 */
	public List findSystemMessageListByUser(Users user,Integer page)
	{
		List list =null;
		list = getSystemMessageDao().findByPropertyWithPage("users", user,page);
		return list;
	}
	
	/***
	 * 根据id获取站内信
	 * @param messageId
	 * @return
	 */
	public Systemmessage findMessageById(Integer messageId)
	{
		return getSystemMessageDao().findById(messageId);
	}
	
	/***
	 * 删除站内信
	 * @param message 站内信
	 */
	public void deleteSystemMessage(Systemmessage message){
		getSystemMessageDao().delete(message);
	}
	
	/***
	 * 更新信息
	 * @param message
	 */
	public void saveOrUpdate(Systemmessage message)
	{
		getSystemMessageDao().saveOrUpdate(message);
	}
	@Autowired
	public void setNoticeDao(NoticeDAO noticeDao) {
		this.noticeDao = noticeDao;
	}

	public PreloadingpageDAO getPreloadingDao() {
		return preloadingDao;
	}

	@Autowired
	public void setPreloadingDao(PreloadingpageDAO preloadingDao) {
		this.preloadingDao = preloadingDao;
	}

	public VersioncontrollDAO getVersioncontrollDao() {
		return versioncontrollDao;
	}

	@Autowired
	public void setVersioncontrollDao(VersioncontrollDAO versioncontrollDao) {
		this.versioncontrollDao = versioncontrollDao;
	}

	public SustomserviceDAO getSustomserviceDao() {
		return sustomserviceDao;
	}
	@Autowired
	public void setSustomserviceDao(SustomserviceDAO sustomserviceDao) {
		this.sustomserviceDao = sustomserviceDao;
	}

	public BannerDAO getBannerDao() {
		return bannerDao;
	}
	@Autowired
	public void setBannerDao(BannerDAO bannerDao) {
		this.bannerDao = bannerDao;
	}

	public ShareDAO getShareDao() {
		return shareDao;
	}
	@Autowired
	public void setShareDao(ShareDAO shareDao) {
		this.shareDao = shareDao;
	}

	public SystemmessageDAO getSystemMessageDao() {
		return systemMessageDao;
	}
	@Autowired
	public void setSystemMessageDao(SystemmessageDAO systemMessageDao) {
		this.systemMessageDao = systemMessageDao;
	}

}
