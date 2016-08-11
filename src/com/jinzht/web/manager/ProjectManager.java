package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.AudiorecordDAO;
import com.jinzht.web.dao.CityDAO;
import com.jinzht.web.dao.CollectionDAO;
import com.jinzht.web.dao.ContentpriseDAO;
import com.jinzht.web.dao.FinancestatusDAO;
import com.jinzht.web.dao.FinancialstandingDAO;
import com.jinzht.web.dao.FinancingcaseDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.InvestmentrecordDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.ProjectDAO;
import com.jinzht.web.dao.ProjectcommentDAO;
import com.jinzht.web.dao.ProjectcommitrecordDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.PubliccontentDAO;
import com.jinzht.web.dao.RoadshowDAO;
import com.jinzht.web.dao.RoadshowplanDAO;
import com.jinzht.web.dao.SceneDAO;
import com.jinzht.web.dao.ScenecommentDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Businessplan;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Collection;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Controlreport;
import com.jinzht.web.entity.Financestatus;
import com.jinzht.web.entity.Financialstanding;
import com.jinzht.web.entity.Financingcase;
import com.jinzht.web.entity.Financingexit;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Investmentrecord;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Projectcomment;
import com.jinzht.web.entity.Projectcommitrecord;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Roadshowplan;
import com.jinzht.web.entity.Scene;
import com.jinzht.web.entity.Scenecomment;
import com.jinzht.web.entity.Users;

public class ProjectManager {

	private SceneDAO sceneDao;
	private ShareDAO shareDao;
	private ScenecommentDAO sceneCommentDao;
	private ProjectDAO projectDao;
	private CollectionDAO collectionDao;
	private AudiorecordDAO audioRecordDao;
	private RoadshowDAO roadShowDao;
	private RoadshowplanDAO roadShowPlanDao;
	private PubliccontentDAO publicContentDao;
	private ContentpriseDAO contentPriseDao;
	private FinancestatusDAO financestatusDao;
	private ProjectcommentDAO projectCommentDao;
	private InvestmentrecordDAO investmentRecordDao;
	private ProjectcommitrecordDAO projectCommitRecordDao;
	
	public Publiccontent findPublicContentById(Integer contentId) {
		return getPublicContentDao().findById(contentId);
	}

	/***
	 * 获取项目列表
	 * 
	 * @param currentPage
	 *            分页：当前页
	 * @return 项目列表
	 */
	public List findProjectsByCursor(int currentPage) {
		List list = getProjectDao().findByCursor(currentPage);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Project project = (Project) list.get(i);
				project.setCommunions(null);
				project.setMembers(null);
				project.setTeams(null);
				project.setFinancingexits(null);
				project.setFinancialstandings(null);
				project.setControlreports(null);
				project.setFinancingcases(null);
				project.setBusinessplans(null);
				project.setProjectcomments(null);
				project.setProjectcommitrecords(null);
				project.setProjectimageses(null);
			}
			return list;
		}
		return null;
	}
	
	/***
	 * 根据类型获取项目列表
	 * @param type
	 * @param currentPage
	 * @return
	 */
	public List findProjectsByCursor(int type,int currentPage) {
		Map map = new HashMap();
		if(type==0)
		{
			List l1 = getProjectDao().findProjectHomeList(currentPage,1);
			List l2 = getProjectDao().findProjectHomeList(currentPage,2);
			List l3 = getProjectDao().findProjectHomeList(currentPage,4);
			
			List list = new ArrayList();
			if (l1 != null && l1.size() > 0) {
				for (int i = 0; i < l1.size(); i++) {
					
					
					Project project = (Project) l1.get(i);
					project.setCommunions(null);
					project.setMembers(null);
					project.setTeams(null);
					project.setFinancingexits(null);
					project.setFinancialstandings(null);
					project.setControlreports(null);
					project.setFinancingcases(null);
					project.setBusinessplans(null);
					project.setProjectcomments(null);
					project.setProjectcommitrecords(null);
					project.setProjectimageses(null);
					
					// 人气指数
					Integer count = this.findCountProjectCollection(project);
					project.setCollectionCount(count);
					
					list.add(project);
				}
			}
			if (l2 != null && l2.size() > 0) {
				for (int i = 0; i < l2.size(); i++) {
					Project project = (Project) l2.get(i);
					project.setCommunions(null);
					project.setMembers(null);
					project.setTeams(null);
					project.setFinancingexits(null);
					project.setFinancialstandings(null);
					project.setControlreports(null);
					project.setFinancingcases(null);
					project.setBusinessplans(null);
					project.setProjectcomments(null);
					project.setProjectcommitrecords(null);
					project.setProjectimageses(null);
					
					// 人气指数
					Integer count = this.findCountProjectCollection(project);
					project.setCollectionCount(count);
					
					list.add(project);
				}
			}
			if (l3 != null && l3.size() > 0) {
				for (int i = 0; i < l3.size(); i++) {
					Project project = (Project) l3.get(i);
					project.setCommunions(null);
					project.setMembers(null);
					project.setTeams(null);
					project.setFinancingexits(null);
					project.setFinancialstandings(null);
					project.setControlreports(null);
					project.setFinancingcases(null);
					project.setBusinessplans(null);
					project.setProjectcomments(null);
					project.setProjectcommitrecords(null);
					project.setProjectimageses(null);
					
					// 人气指数
					Integer count = this.findCountProjectCollection(project);
					project.setCollectionCount(count);
					list.add(project);
				}
			}
			return list;
		}else{
			Financestatus status = new Financestatus();
			status.setStatusId(3);

			map.put("financestatus", status);
			
			List list = getProjectDao().findByPropertiesWithPage(map, currentPage);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Project project = (Project) list.get(i);
					project.setCommunions(null);
					project.setMembers(null);
					project.setTeams(null);
					project.setFinancingexits(null);
					project.setFinancialstandings(null);
					project.setControlreports(null);
					project.setFinancingcases(null);
					project.setBusinessplans(null);
					project.setProjectcomments(null);
					project.setProjectcommitrecords(null);
					project.setProjectimageses(null);
					
					// 人气指数
					Integer count = this.findCountProjectCollection(project);
					project.setCollectionCount(count);
				}
				return list;
			}
		}
		
		return null;
	}

	/***
	 * 根据项目id获取项目
	 * 
	 * @param projectId
	 * @return
	 */
	public Project findProjectById(Integer projectId) {
		Project project = getProjectDao().findById(projectId);
		if (project != null) {
			// 人气指数
			Integer count = this.findCountProjectCollection(project);
			project.setCollectionCount(count);
			
			//评论数量
			count  = this.findCountProjectComment(project);
			project.setCommentCount(count);
			//用户是否已关注
			

			// 剩余时间
			Object[] roadshows = project.getRoadshows().toArray();
			if (roadshows != null) {
				Roadshow roadShow = (Roadshow) roadshows[0];
				// 融资计划
				Roadshowplan plan = roadShow.getRoadshowplan();
				// 开始时间
				Date benginDate = new Date();
				// 结束时间
				Date endDate = plan.getEndDate();

				// 时间差
				long instance = endDate.getTime() - benginDate.getTime();

				// 转换为天

				int days = (int) (instance / (24 * 60 * 60 * 1000));

				// 设置剩余时间
				project.setTimeLeft(days);
			}
			return project;
		}
		return null;
	}

	/***
	 * 获取项目的收藏数量
	 * 
	 * @param project
	 * @return
	 */
	public Integer findCountProjectCollection(Project project) {
		Map map = new HashMap();
		map.put("project", project);
		return getCollectionDao().counterByProperties(map);
	}
	/***
	 * 获取项目评论数量
	 * 
	 * @param project
	 * @return
	 */
	public Integer findCountProjectComment(Project project) {
		Map map = new HashMap();
		map.put("project", project);
		return getProjectCommentDao().counterByProperties(map);
	}
	/***
	 * 获取项目评论列表
	 * 
	 * @param project
	 * @return
	 */
	public List findProjectComment(Project project,Integer page) {
		Map map = new HashMap();
		map.put("project", project);
		List list =  getProjectCommentDao().findByProperties(map, page);
		if(list!=null && list.size()>0)
		{
			Projectcomment comment =null;
			Users user = null;
			Users  commentUser = null;
			for(int i =0;i<list.size();i++)
			{
				comment =(Projectcomment)list.get(i);
				
				commentUser = comment.getUsers();
				
				user = new Users();
				user.setUserId(commentUser.getUserId());
				user.setName(commentUser.getName());
				user.setHeadSculpture(commentUser.getHeadSculpture());
				user.setAuthentics(null);
				
				
				if(user.getName()==null || user.getName().equals(""))
				{
					String telephone = commentUser.getTelephone();
					Integer length = telephone.length();
					String name = "用户"+telephone.substring(length-4, length);
					user.setName(name);
				}
				
				comment.setUsers(user);
			}
		}
		return list;
	}

	/***
	 * 获取项目融资状况
	 * 
	 * @param project
	 * @return
	 */
	public Financialstanding findProjectFinancialStanding(Integer projectId) {
		Project project = this.findProjectById(projectId);
		
		Object[] objs = project.getFinancialstandings().toArray();
		if(objs!=null && objs.length>0)
		{
			Financialstanding standing = (Financialstanding) objs[0];
			return standing;
		}
		
		return null;
		
	}

	/***
	 * 融资方案
	 * 
	 * @param projectId
	 * @return
	 */
	public Financingcase findProjectFinancialCase(Integer projectId) {
		Project project = this.findProjectById(projectId);
		
		Object[] objs = project.getFinancingcases().toArray();
		if(objs!=null && objs.length>0)
		{
			Financingcase financeCase = (Financingcase) objs[0];
			return financeCase;
		}
		
		return null;
	}

	/***
	 * 获取项目商业计划书
	 * 
	 * @param projectId
	 * @return
	 */
	public Businessplan findProjectBusinessPlan(Integer projectId) {
		Project project = this.findProjectById(projectId);
		
		Object[] objs = project.getBusinessplans().toArray();
		if(objs!=null && objs.length>0)
		{
			Businessplan businessPlan = (Businessplan) objs[0];
			return businessPlan;
		}
		
		return null;
	}

	/***
	 * 退出渠道
	 * @param projectId
	 * @return
	 */
	public Financingexit findProjectFinanceExit(Integer projectId) {
		Project project = this.findProjectById(projectId);
		
		Object[] objs = project.getFinancingexits().toArray();
		if(objs!=null && objs.length>0)
		{
			Financingexit Financingexit = (Financingexit) objs[0];
			return Financingexit;
		}
		
		return null;
	}
	/***
	 * 获取风控报告
	 * @param projectId
	 * @return
	 */
	public Controlreport findProjectControlReport(Integer projectId) {
		Project project = this.findProjectById(projectId);
		
		Object[] objs = project.getControlreports().toArray();
		if(objs!=null && objs.length>0)
		{
			Controlreport controlReport = (Controlreport) objs[0];
			return controlReport;
		}
		
		return null;
	}

	/***
	 * 获取项目融资状况
	 * 
	 * @param project
	 * @return
	 */
	public Financestatus findProjectFinancialStatus(Integer projectId) {
		Financestatus financeStatus = getProjectDao().findById(projectId)
				.getFinancestatus();
		;
		return financeStatus;
	}
	
	/***
	 * 关注/取消关注收藏
	 * @param projectId
	 * @return flag:0:取消关注,1;关注
	 */
	public int projectCollect(Integer projectId,Users user)
	{
		int flag  = 0;
		Project project = this.findProjectById(projectId);
		
		Map map = new HashMap();
		map.put("users", user);
		map.put("project", project);
		
		List list = getCollectionDao().findByProperties(map);
		
		Collection collection = null;
		if(list!=null && list.size()>0){
			collection = (Collection) list.get(0);
			//取消关注
			getCollectionDao().delete(collection);
			
		}else{
			collection = new Collection();
			collection.setUsers(user);
			collection.setProject(project);
			
			//保存
			getCollectionDao().save(collection);
			flag = 1;
		}
		
		
		return flag;
	}
	
	/***
	 * 对项目评论
	 * @param projectId
	 * @param content
	 */
	public void projectComment(Integer projectId, String content,Users user){
		//获取项目
		Project project = this.findProjectById(projectId);
		
		//评论
		Projectcomment comment = new Projectcomment();
		comment.setUsers(user);
		comment.setProject(project);
		comment.setContent(content);
		comment.setCommentDate(new Date());
		
		//保存
		getProjectCommentDao().save(comment);
	}
	
	/***
	 * 删除项目评论
	 * @param projectId
	 * @param content
	 */
	public void deleteProjectComment(Integer commentId){
		
		//评论
		Projectcomment comment = getProjectCommentDao().findById(commentId);
		//删除
		getProjectCommentDao().delete(comment);
	}
	
	/***
	 * 获取项目现场信息
	 * @param projectId
	 * @return
	 */
	public List findSceneByProjectId(Integer projectId,Users user)
	{
		//项目
		Project project = this.findProjectById(projectId);
		//现场
		List list = getSceneDao().findByProperty("project", project);
		
		if(list!=null && list.size()>0)
		{
			for(int i = 0 ; i<list.size();i++ )
			{
				Scene scene = (Scene) list.get(i);
				scene.setScenecomments(null);
				scene.setAudiorecords(null);
				scene.setBeginTime(null);
				scene.setEndTime(null);
				
//				if(scene!=null)
//				{
//					Object[] obj = scene.getScenecomments().toArray();
//					Scenecomment comment = null;
//					for(int j = 0 ; j<list.size();j++)
//					{
//						 comment= (Scenecomment)obj[j];
//						 Users commentUser = comment.getUsers();
//						 Integer userId = commentUser.getUserId();
//						 if(userId.equals(user.getUserId()))
//						 {
//							 comment.setFlag(true);
//						 }
//						 
//						 commentUser.getName();
//						 commentUser.getHeadSculpture();
//						 commentUser.setPassword(null);
//						 commentUser.setTelephone(null);
//						 commentUser.setAuthentics(null);
//						 commentUser.setLastLoginDate(null);
//						 commentUser.setPlatform(null);
//						 
//					}
//				}
			}
			
		}
		return list;
	}
	
	/***
	 * 根据场景返回播放记录
	 * @param sceneId
	 * @param page
	 * @return
	 */
	public List findAudioRecordBySceneId(Integer sceneId,Integer page)
	{
		Scene scene = getSceneDao().findById(sceneId);
		
		List list = getAudioRecordDao().findByPropertyWithPage("scene", scene,page);
		
		return list;
	}
	
	/***
	 * 获取项目路演情况
	 * @param projectId
	 * @return
	 */
	public Roadshow findRoadShowByProject(Integer projectId)
	{
		Project project = this.findProjectById(projectId);
		
		List list  = getRoadShowDao().findByProperty("project", project);
		
		if(list != null && list.size()>0)
		{
			return (Roadshow) list.get(0);
		}
		return null;
	}
	
	/***
	 * 现场交流评论
	 * @param sceneId 现场id
	 * @param user 评论用户
	 * @param content 内容
	 */
	public void saveSceneComment(Integer sceneId, Users user,String content)
	{
		//获取当前现场
		Scene scene = getSceneDao().findById(sceneId);
		//现场评论
		Scenecomment comment = new Scenecomment();
		//设置属性
		comment.setContent(content);
		comment.setUsers(user);
		comment.setScene(scene);
		comment.setCommentDate(new Date());
		
		scene.getScenecomments().add(comment);
		//添加评论
//		getSceneDao().save(scene);
		getSceneCommentDao().save(comment);
		
	}
	/***
	 * 投资交易
	 * @param projectId 项目id
	 * @param user 投资用户
	 * @param amount 投资金额
	 * @param investCode 投资编号
	 */
	public void saveProjectInvest(Integer projectId, Users user,float amount,String investCode)
	{
		//获取项目
		Project project = this.findProjectById(projectId);
		//生成投资订单
		Investmentrecord investmentRecord = new Investmentrecord();
		
		//设置属性
		investmentRecord.setUsers(user);
		investmentRecord.setStatusId(0);
		investmentRecord.setProject(project);
		investmentRecord.setInvestAmount(amount);
		investmentRecord.setInvestCode(investCode);
		
		
		project.getInvestmentrecords().add(investmentRecord);
		//添加交易
		getInvestmentRecordDao().save(investmentRecord);
	}
	
	/***
	 * 获取用户所发布项目
	 * @param user
	 * @param page
	 * @return
	 */
	public List findProjectsByUser(Users user,Integer page)
	{
		List list  = getProjectDao().findByPropertyWithPage("userId", user.getUserId(),page);
		
		if(list != null && list.size()>0)
		{
			Project project = null;
			for(int i = 0;i<list.size();i++)
			{
				project = (Project) list.get(i);
				project.setControlreports(null);
				project.setFinancingexits(null);
				project.setFinancialstandings(null);
				project.setFinancingcases(null);
				project.setFinancingcases(null);
				project.setBusinessplans(null);
				project.setBorrowerUserNumber(null);
				project.setTeams(null);
				project.setProjectimageses(null);
			}
			return list;
		}
		
		return null;
	}
	
	/***
	 * 获取用户是否已关注项目
	 * @param project
	 * @param user
	 * @return
	 */
	public Collection findProjectCollectionByUser(Project project,Users user)
	{
		Collection collection = null;
		//获取
		Map map = new HashMap();
		map.put("users", user);
		map.put("project", project);
		
		List list = getCollectionDao().findByProperties(map);
		if(list!=null  && list.size()>0)
		{
			return (Collection) list.get(0);
		}
		return collection;
	}
	
	/**
	 * 获取投资人所投资的项目
	 * @param user 用户
	 * @param page 当前页
	 * @return
	 */
	public List findProjectsInvestmentWithUser(Users user,Integer page)
	{
		List list = new ArrayList();
		
		//封装
		Map map = new HashMap();
		map.put("users", user);
		
		List result = getInvestmentRecordDao().findByProperties(map, 0);
		
		if(result!=null && result.size()>0){
			for(int i=0;i<result.size();i++)
			{
				Investmentrecord record = (Investmentrecord) result.get(i);
				Integer projectId = getInvestmentRecordDao().findProjectIdByRecordId(record.getInvestId());
				if(projectId!=null)
				{
					Project project = this.projectDao.findById(projectId);
					
					project.setControlreports(null);
					project.setFinancingexits(null);
					project.setFinancialstandings(null);
					project.setFinancingcases(null);
					project.setFinancingcases(null);
					project.setBusinessplans(null);
					project.setBorrowerUserNumber(null);
					project.setTeams(null);
					project.setProjectimageses(null);
					
					list.add(project);
				}
				
			}
			
		}
		
		return list;
	}
	
	/***
	 * 获取用户收到项目提交
	 * @param user
	 * @param page
	 * @return
	 */
	public List findRecivedProjectCommitByUser(Users user ,Integer page)
	{
		List list = new ArrayList();
		
		//封装
		Map map = new HashMap();
		map.put("users", user);
		
				
		List result = getProjectCommitRecordDao().findByProperties(map,page);
		
		if(result!=null && result.size()>0){
			for(int i=0;i<result.size();i++)
			{
				Projectcommitrecord record = (Projectcommitrecord) result.get(i);
				Project project = record.getProject();
				
				//过滤
				project.setTeams(null);
				project.setControlreports(null);
				project.setFinancingexits(null);
				project.setFinancingcases(null);
				project.setFinancingcases(null);
				project.setBusinessplans(null);
				project.setProjectimageses(null);
				project.setFinancialstandings(null);
				project.setBorrowerUserNumber(null);
				
				list.add(project);
			}
			
		}
		
		return list;
	}
	/***
	 * 获取用户评论过项目
	 * @param user
	 * @param page
	 * @return
	 */
	public List findCommentProjectByUser(Users user ,Integer page)
	{
		List list = new ArrayList();
		
		//封装
		Map map = new HashMap();
		map.put("users", user);
		
		List result = getProjectCommentDao().findByProperties(map, page);
		
		if(result!=null && result.size()>0){
			for(int i=0;i<result.size();i++)
			{
				Projectcomment record = (Projectcomment) result.get(i);
				Project project = record.getProject();
				
				//过滤
				project.setControlreports(null);
				project.setFinancingexits(null);
				project.setFinancialstandings(null);
				project.setFinancingcases(null);
				project.setFinancingcases(null);
				project.setBusinessplans(null);
				project.setBorrowerUserNumber(null);
				project.setTeams(null);
				project.setProjectimageses(null);
				
				list.add(project);
			}
			
		}
		
		return list;
	}
	/***
	 * 获取现场评论列表
	 * @param sceneId
	 * @param page
	 * @return
	 */
	public List findProjectSceneCommentList(Integer sceneId ,Integer page,Integer userId)
	{
		List list = new ArrayList();
		Scene scene = getSceneDao().findById(sceneId);
		//封装
		Map map = new HashMap();
		map.put("scene", scene);
//		
		List result = getSceneCommentDao().findByPropertiesWithPage(map, page);
		
		if(result!=null && result.size()>0){
			for(int i=0;i<result.size();i++)
			{
				Scenecomment record = (Scenecomment) result.get(i);
				record.setIsvalid(null);
				
				Users user = record.getUsers();
				Users userInstance = new Users();
				if(user!=null && user.getUserId()!=null)
				{
					userInstance.setName(user.getName());
					userInstance.setUserId(user.getUserId());
					userInstance.setHeadSculpture(user.getHeadSculpture());
					userInstance.setAuthentics(null);
					
					if(userId.equals(user.getUserId()))
					{
						record.setFlag(true);
					}
					
					if(userInstance.getName()==null || userInstance.getName().equals(""))
					{
						String telephone = user.getTelephone();
						Integer length = telephone.length();
						String name = "用户"+user.getTelephone().substring(length-4, length);
						userInstance.setName(name);
					}
					
					
					record.setUsers(userInstance);
					
					list.add(record);
				}
				
			}
			
		}
		
		return list;
	}

	/***
	 * 根据用户 项目获取提交记录
	 * @param project
	 * @param user
	 * @return
	 */
	public Projectcommitrecord findProjectcommitByProject(Project project,Users user)
	{
		List list = null;
		Map map = new HashMap();
		map.put("project", project);
		map.put("users", user);
		list = getProjectCommitRecordDao().findByProperties(map, 0);
		if(list != null && list.size()>0)
		{
			return (Projectcommitrecord)list.get(0);
		}
		return null;
	}
	
	public List findProjectByName(String name)
	{
		List<Project> projects = getProjectDao().findByName(name);
		return projects;
	}

	/***
	 * 获取项目提交记录
	 * @param projectId
	 * @return
	 */
	public List findRecordByProjectId(Integer projectId,Integer page)
	{
		return getProjectCommitRecordDao().findRecordListByProjectId(projectId,page);
	}
	public PubliccontentDAO getPublicContentDao() {
		return publicContentDao;
	}

	@Autowired
	public void setPublicContentDao(PubliccontentDAO publicContentDao) {
		this.publicContentDao = publicContentDao;
	}

	public ContentpriseDAO getContentPriseDao() {
		return contentPriseDao;
	}

	@Autowired
	public void setContentPriseDao(ContentpriseDAO contentPriseDao) {
		this.contentPriseDao = contentPriseDao;
	}

	public ShareDAO getShareDao() {
		return shareDao;
	}

	@Autowired
	public void setShareDao(ShareDAO shareDao) {
		this.shareDao = shareDao;
	}

	public ProjectDAO getProjectDao() {
		return projectDao;
	}

	@Autowired
	public void setProjectDao(ProjectDAO projectDao) {
		this.projectDao = projectDao;
	}

	public CollectionDAO getCollectionDao() {
		return collectionDao;
	}

	@Autowired
	public void setCollectionDao(CollectionDAO collectionDao) {
		this.collectionDao = collectionDao;
	}

	public FinancestatusDAO getFinancestatusDao() {
		return financestatusDao;
	}

	@Autowired
	public void setFinancestatusDao(FinancestatusDAO financestatusDao) {
		this.financestatusDao = financestatusDao;
	}

	public ProjectcommentDAO getProjectCommentDao() {
		return projectCommentDao;
	}
	@Autowired
	public void setProjectCommentDao(ProjectcommentDAO projectCommentDao) {
		this.projectCommentDao = projectCommentDao;
	}

	public SceneDAO getSceneDao() {
		return sceneDao;
	}
	@Autowired
	public void setSceneDao(SceneDAO sceneDao) {
		this.sceneDao = sceneDao;
	}

	public AudiorecordDAO getAudioRecordDao() {
		return audioRecordDao;
	}
	@Autowired
	public void setAudioRecordDao(AudiorecordDAO audioRecordDao) {
		this.audioRecordDao = audioRecordDao;
	}

	public RoadshowDAO getRoadShowDao() {
		return roadShowDao;
	}
	@Autowired
	public void setRoadShowDao(RoadshowDAO roadShowDao) {
		this.roadShowDao = roadShowDao;
	}

	public ScenecommentDAO getSceneCommentDao() {
		return sceneCommentDao;
	}
	@Autowired
	public void setSceneCommentDao(ScenecommentDAO sceneCommentDao) {
		this.sceneCommentDao = sceneCommentDao;
	}

	public InvestmentrecordDAO getInvestmentRecordDao() {
		return investmentRecordDao;
	}
	@Autowired
	public void setInvestmentRecordDao(InvestmentrecordDAO investmentRecordDao) {
		this.investmentRecordDao = investmentRecordDao;
	}

	public ProjectcommitrecordDAO getProjectCommitRecordDao() {
		return projectCommitRecordDao;
	}
	@Autowired
	public void setProjectCommitRecordDao(ProjectcommitrecordDAO projectCommitRecordDao) {
		this.projectCommitRecordDao = projectCommitRecordDao;
	}

	public RoadshowplanDAO getRoadShowPlanDao() {
		return roadShowPlanDao;
	}
	@Autowired
	public void setRoadShowPlanDao(RoadshowplanDAO roadShowPlanDao) {
		this.roadShowPlanDao = roadShowPlanDao;
	}

}
