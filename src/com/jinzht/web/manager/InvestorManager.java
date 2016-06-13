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
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.ProjectDAO;
import com.jinzht.web.dao.ProjectcommitrecordDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.PubliccontentDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Projectcommitrecord;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Sharetype;
import com.jinzht.web.entity.Status;
import com.jinzht.web.entity.Users;

public class InvestorManager {

	private UsersDAO usersDao;
	private ProjectcommitrecordDAO projectCommitRecordDao;
	private AuthenticDAO authenticDao;
	private ProjectDAO projectDao;
	private IndustoryareaDAO industoryAreaDao;

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
		map.put("identiytype", identityType);

		List list = getAuthenticDao().findByProperties(map, currentPage);

		// 封装返回数据
		map = new HashMap();
		if (list != null && list.size() > 0) {
			Authentic authentic = null;
			for (int i = 0; i < list.size(); i++) {
				authentic = (Authentic) list.get(i);

				Users u = authentic.getUsers();
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
				if (industoryArea != null && industoryArea!= "") {
					String[] aa = industoryArea.split(",");
		            
					String str = "";
					for (int j = 0; j < aa.length; j++) {
						System.out.println("--" + aa[j]);
						Industoryarea area = getIndustoryAreaDao().findById(Integer.parseInt(aa[j].toString()));
						l.add(area.getName());
					}
				}

				u.setUserstatus(null);
				u.setTelephone(null);
				u.setPassword(null);
				u.setPlatform(null);
				u.setLastLoginDate(null);
				u.setHeadSculpture(u.getHeadSculpture());
				u.setName(authentic.getName());

				map.put("user", u);
				map.put("areas", l);
				map.put("collectCount", 1000);
				map.put("collected", true);
				map.put("commited", true);
				
				listResult.add(map);

			}

		}

		return listResult;
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

}
