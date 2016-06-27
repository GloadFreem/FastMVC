package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.AuthenticDAO;
import com.jinzht.web.dao.CityDAO;
import com.jinzht.web.dao.CommentDAO;
import com.jinzht.web.dao.ContentpriseDAO;
import com.jinzht.web.dao.FoundationDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.InvestorcollectDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.ProjectDAO;
import com.jinzht.web.dao.ProjectcommitrecordDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.PubliccontentDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Authenticstatus;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Investorcollect;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Projectcommitrecord;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Sharetype;
import com.jinzht.web.entity.Status;
import com.jinzht.web.entity.Users;

public class InvestorManager {

	private ShareDAO shareDao;
	private UsersDAO usersDao;
	private FoundationDAO foundationDao;
	private ProjectcommitrecordDAO projectCommitRecordDao;
	private AuthenticDAO authenticDao;
	private ProjectDAO projectDao;
	private IndustoryareaDAO industoryAreaDao;
	private InvestorcollectDAO investorCollectDao;

	/***
	 * 分页查询投资人信息
	 * 
	 * @param currentPage
	 *            当前页
	 * @return
	 */
	public List findInvestorByCursor(Users user, int currentPage, short type) {
		List listResult = new ArrayList();

		// 身份类型
		Identiytype identityType = new Identiytype();
		identityType.setIdentiyTypeId(type);

		// 封装数据
		Map map = new HashMap();
		Authenticstatus status = new Authenticstatus();
		status.setStatusId(8);
		
		map.put("identiytype", identityType);
		map.put("authenticstatus", status);

		List list = getAuthenticDao().findByProperties(map, currentPage);

		// 封装返回数据
		map = new HashMap();
		if (list != null && list.size() > 0) {
			Authentic authentic = null;
			for (int i = 0; i < list.size(); i++) {
				authentic = (Authentic) list.get(i);

				Integer userId = getAuthenticDao().findUserIdByAuthId(authentic.getAuthId());
				Users u = getUsersDao().findById(userId);
//				Users u = authentic.getUsers();
				authentic.setAuthenticstatus(null);
				authentic.setIdentiytype(null);
				authentic.setIdentiyCarA(null);
				authentic.setIdentiyCarB(null);
				authentic.setIdentiyCarNo(null);
				authentic.setAuthId(null);
				authentic.setBuinessLicence(null);
				authentic.setBuinessLicenceNo(null);
				authentic.setAutrhrecords(null);
				authentic.setOptional(null);

				List l = new ArrayList();
				String industoryArea = authentic.getIndustoryArea();
				if (industoryArea != null && industoryArea != "") {
					String[] aa = industoryArea.split("，");
//					String str = "";
					for (int j = 0; j < aa.length; j++) {
//						System.out.println("--" + aa[j]);
//						Industoryarea area = getIndustoryAreaDao().findById(
//								Integer.parseInt(aa[j].toString()));
						l.add(aa[j]);
					}
					
				}

				u.setUserstatus(null);
				u.setTelephone(null);
				u.setPassword(null);
				u.setPlatform(null);
				u.setLastLoginDate(null);
				u.setHeadSculpture(u.getHeadSculpture());
				u.setName(authentic.getName());
				
				Map requestMap = new HashMap();

				Investorcollect collect = this.findInvestCollectByUser(user, u);
				if (collect != null) {
					requestMap.put("collected", true);
				} else {
					requestMap.put("collected", false);
				}

				requestMap.put("commited", false);
				
				requestMap.put("collectCount", 100);

				requestMap.put("user", u);
				requestMap.put("areas", l);

				listResult.add(requestMap);

			}

		}

		return listResult;
	}
	
	/***
	 * 获取用户关注投资人列表
	 * @param user
	 * @param page
	 * @return
	 */
	public List findUserCollectInvestor(Users user,Integer page)
	{
		List list  = null;
		Map map = new HashMap();
		map.put("usersByUserId", user);
		
		list  = getInvestorCollectDao().findByPropertiesWithPage(map, page);
		for(int i =0;i<list.size();i++)
		{
			Investorcollect collect = (Investorcollect) list.get(i);
			Users u = collect.getUsersByUserCollectedId();
			
			if(u!=null)
			{
				u.setPassword(null);
				u.setTelephone(null);
				u.setRegId(null);
				u.setWechatId(null);
				u.setPlatform(null);
				u.setExtUserId(null);
				
				if(u.getAuthentics()!=null)
				{
					Object[] objs = u.getAuthentics().toArray();
					for(int j = 0;j<objs.length;j++)
					{
						Authentic authentic = (Authentic) objs[j];
						authentic.setIdentiyCarA(null);
						authentic.setIdentiyCarB(null);
						authentic.setAuthenticstatus(null);
						authentic.setIdentiyCarNo(null);
						authentic.setOptional(null);
					}
				}
			}
		}
		return list;
	}

	/***
	 * 提交项目
	 * 
	 * @param userId
	 *            对方id
	 * @param content
	 *            内容
	 * @param projectId
	 *            项目id
	 */
	public void projectCommitToInvestor(Integer userId, String content,
			Integer projectId) {
		// 获取用户
		Users user = getUsersDao().findById(userId);
		// 获取项目
		Project project = getProjectDao().findById(projectId);

		// 添加提交项目对象属性
		Projectcommitrecord record = new Projectcommitrecord();
		Status status = new Status();
		status.setRecordId(1);

		// 设置属性
		record.setUsers(user);
		record.setProject(project);
		record.setStatus(status);

		// 保存
		getProjectCommitRecordDao().save(record);

	}

	/***
	 * 获取投资人关注
	 * 
	 * @param user
	 * @return
	 */
	public Investorcollect findInvestCollectByUser(Users user, Users collectUser) {
		Map map = new HashMap();
		map.put("usersByUserId", user);
		map.put("usersByUserCollectedId", collectUser);

		List list = getInvestorCollectDao().findByPropertiesWithPage(map, 0);
		if (list != null && list.size() > 0) {
			return (Investorcollect) list.get(0);
		}
		return null;
	}

	public void addInvestCollectByUser(Users user, Users collectUser) {
		Investorcollect collect = new Investorcollect();
		collect.setUsersByUserCollectedId(collectUser);
		collect.setUsersByUserId(user);
		collect.setCollectedDate(new Date());

		getInvestorCollectDao().save(collect);

	}
	
	/***
	 * 保存分享记录
	 * @param share
	 */
	public void saveShareRecord(Share share)
	{
		getShareDao().save(share);
	}
	
	public List findDefaultFoundations()
	{
		return getFoundationDao().findDefault();
	}
	

	public UsersDAO getUsersDao() {
		return usersDao;
	}

	@Autowired
	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}

	public AuthenticDAO getAuthenticDao() {
		return authenticDao;
	}

	@Autowired
	public void setAuthenticDao(AuthenticDAO authenticDao) {
		this.authenticDao = authenticDao;
	}

	public ProjectcommitrecordDAO getProjectCommitRecordDao() {
		return projectCommitRecordDao;
	}

	@Autowired
	public void setProjectCommitRecordDao(
			ProjectcommitrecordDAO projectCommitRecordDao) {
		this.projectCommitRecordDao = projectCommitRecordDao;
	}

	public ProjectDAO getProjectDao() {
		return projectDao;
	}

	@Autowired
	public void setProjectDao(ProjectDAO projectDao) {
		this.projectDao = projectDao;
	}

	public IndustoryareaDAO getIndustoryAreaDao() {
		return industoryAreaDao;
	}

	@Autowired
	public void setIndustoryAreaDao(IndustoryareaDAO industoryAreaDao) {
		this.industoryAreaDao = industoryAreaDao;
	}

	public InvestorcollectDAO getInvestorCollectDao() {
		return investorCollectDao;
	}

	@Autowired
	public void setInvestorCollectDao(InvestorcollectDAO investorCollectDao) {
		this.investorCollectDao = investorCollectDao;
	}

	public ShareDAO getShareDao() {
		return shareDao;
	}
	@Autowired
	public void setShareDao(ShareDAO shareDao) {
		this.shareDao = shareDao;
	}

	public FoundationDAO getFoundationDao() {
		return foundationDao;
	}
	@Autowired
	public void setFoundationDao(FoundationDAO foundationDao) {
		this.foundationDao = foundationDao;
	}

}
