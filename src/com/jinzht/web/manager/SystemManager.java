package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.tools.BannerType;
import com.jinzht.web.dao.BannerDAO;
import com.jinzht.web.dao.NoticeDAO;
import com.jinzht.web.dao.PreloadingpageDAO;
import com.jinzht.web.dao.ProjectDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.SustomserviceDAO;
import com.jinzht.web.dao.SystemmessageDAO;
import com.jinzht.web.dao.VersioncontrollDAO;
import com.jinzht.web.entity.Banner;
import com.jinzht.web.entity.Notice;
import com.jinzht.web.entity.Preloadingpage;
import com.jinzht.web.entity.Customservice;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Roadshow;
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
	private ProjectDAO projectDao;
	

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
	public List<Banner> findBannerInfoList() {
		//返回Banner结果
		List result = new ArrayList();
		Map map = null;
		
		List<Banner> list = getBannerDao().findAll();
		
		
		if(list!=null && list.size()>0)
		{
			Banner banner = null;
			for(int i = 0;i<list.size();i++)
			{
				banner = list.get(i);
				banner.setBannerType(null);
				
				
				map = new HashMap();
				//添加到返回数据中
				map.put("body", banner);
				map.put("type", BannerType.Web);
				
				//判断是否为项目
				if(banner.getProject()!=null && !banner.getProject().equals(""))
				{
					//获取项目
					Project project = getProjectDao().findById(Integer.parseInt(banner.getProject()));
					if(project!=null)
					{
						map.put("type", BannerType.Project);
						//设置项目属性
						project.setUserId(null);
						project.setFinancestatus(null);
						project.setProjectcomments(null);
						project.setProjectType(null);
						project.setAbbrevName(null);
						project.setCollections(null);
						project.setStartPageImage(null);
						project.setControlreport(null);
						project.setFinancialstanding(null);
						project.setFinancingexit(null);
						project.setFinancingcase(null);
						project.setBusinessplan(null);
						project.setAddress(null);
						project.setTimeLeft(null);
						project.setCollectionCount(null);
						project.setFullName(null);
						project.setDescription(null);
						
						Object[] obj = project.getRoadshows().toArray();
						if(obj!=null && obj.length>0)
						{
							for(int j = 0;j<obj.length;j++)
							{
								Roadshow roadShow = (Roadshow) obj[j];
								roadShow.setRoadShowId(null);
								roadShow.setProject(null);
								roadShow.getRoadshowplan().setBeginDate(null);
								roadShow.getRoadshowplan().setEndDate(null);
								roadShow.getRoadshowplan().setFinancingId(null);
							}
							
						}
						//添加到返回数据
						map.put("extr", project);
					}
					
					banner.setProject(null);
				}
				
				result.add(map);
				
			}
		}
		return result;
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

	public ProjectDAO getProjectDao() {
		return projectDao;
	}
	@Autowired
	public void setProjectDao(ProjectDAO projectDao) {
		this.projectDao = projectDao;
	}

}
