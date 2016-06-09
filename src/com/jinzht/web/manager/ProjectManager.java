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
import com.jinzht.web.dao.IndustorytypeDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.ProjectDAO;
import com.jinzht.web.dao.ProjectcommentDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.PubliccontentDAO;
import com.jinzht.web.dao.RoadshowDAO;
import com.jinzht.web.dao.SceneDAO;
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
import com.jinzht.web.entity.Industorytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Projectcomment;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Roadshowplan;
import com.jinzht.web.entity.Scene;
import com.jinzht.web.entity.Users;

public class ProjectManager {

	private SceneDAO sceneDao;
	private ShareDAO shareDao;
	private ProjectDAO projectDao;
	private CollectionDAO collectionDao;
	private AudiorecordDAO audioRecordDao;
	private RoadshowDAO roadShowDao;
	private PubliccontentDAO publicContentDao;
	private ContentpriseDAO contentPriseDao;
	private FinancestatusDAO financestatusDao;
	private ProjectcommentDAO projectCommentDao;
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
				project.setProjectcomments(null);
				project.setProjectcommitrecords(null);
				project.setProjectimageses(null);
			}
			return list;
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
	 * 获取项目融资状况
	 * 
	 * @param project
	 * @return
	 */
	public Financialstanding findProjectFinancialStanding(Integer projectId) {
		Project project = this.findProjectById(projectId);
		Financialstanding standing = project.getFinancialstanding();
		return standing;
	}

	/***
	 * 融资方案
	 * 
	 * @param projectId
	 * @return
	 */
	public Financingcase findProjectFinancialCase(Integer projectId) {
		Project project = this.findProjectById(projectId);
		Financingcase financeCase = project.getFinancingcase();
		return financeCase;
	}

	/***
	 * 获取项目商业计划书
	 * 
	 * @param projectId
	 * @return
	 */
	public Businessplan findProjectBusinessPlan(Integer projectId) {
		Project project = this.findProjectById(projectId);
		Businessplan businessPlan = project.getBusinessplan();
		return businessPlan;
	}

	/***
	 * 退出渠道
	 * @param projectId
	 * @return
	 */
	public Financingexit findProjectFinanceExit(Integer projectId) {
		Project project = this.findProjectById(projectId);
		Financingexit Financingexit = project.getFinancingexit();
		return Financingexit;
	}
	/***
	 * 获取风控报告
	 * @param projectId
	 * @return
	 */
	public Controlreport findProjectControlReport(Integer projectId) {
		Project project = this.findProjectById(projectId);
		Controlreport controlReport = project.getControlreport();
		return controlReport;
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
		
		//保存
		getProjectCommentDao().save(comment);
	}
	
	/***
	 * 获取项目现场信息
	 * @param projectId
	 * @return
	 */
	public List findSceneByProjectId(Integer projectId)
	{
		//项目
		Project project = this.findProjectById(projectId);
		//现场
		List list = getSceneDao().findByProperty("project", project);
		
		return list;
	}
	
	/***
	 * 根据场景返回播放记录
	 * @param sceneId
	 * @return
	 */
	public List findAudioRecordBySceneId(Integer sceneId)
	{
		Scene scene = getSceneDao().findById(sceneId);
		
		List list = getAudioRecordDao().findByProperty("scene", scene);
		
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

}
