package com.jinzht.web.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.jinzht.tools.Config;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.ProjectRange;
import com.jinzht.web.entity.ProjectSearchBean;
import com.jinzht.web.entity.ProjectSearchBean.MapResult;

/**
 * A data access object (DAO) providing persistence and search support for
 * Project entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Project
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ProjectDAO {
	private static final Logger log = LoggerFactory.getLogger(ProjectDAO.class);
	// property constants
	public static final String ABBREV_NAME = "abbrevName";
	public static final String FULL_NAME = "fullName";
	public static final String DESCRIPTION = "description";
	public static final String PROJECT_TYPE = "projectType";
	public static final String ADDRESS = "address";
	public static final String START_PAGE_IMAGE = "startPageImage";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Project transientInstance) {
		log.debug("saving Project instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void saveOrUpdate(Project transientInstance) {
		log.debug("saving Or updating Project instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Project persistentInstance) {
		log.debug("deleting Project instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Project findById(java.lang.Integer id) {
		log.debug("getting Project instance with id: " + id);
		try {
			Project instance = (Project) getCurrentSession().get(
					"com.jinzht.web.entity.Project", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public Integer counterByProperties(Map map) {
		try {
			String queryString = "select count(model.projectId) as count from Project as model where model.";
			Object[] keys = map.keySet().toArray();
			for(int i = 0;i<keys.length;i++){
				if(i==0){
					queryString += keys[i] + "= ?";
				}else{
					queryString +=" and " + keys[i] + "= ?";
				}
			}
			
			Query queryObject = getCurrentSession().createQuery(queryString);
			for(int i = 0;i<keys.length;i++){
				queryObject.setParameter(i,map.get( keys[i]));
			}
					
			return  ((Number) queryObject.iterate().next())
			         .intValue();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Project> findByExample(Project instance) {
		log.debug("finding Project instance by example");
		try {
			List<Project> results = (List<Project>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.Project")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Project instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Project as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByPropertyWithPage(String propertyName, Object value,Integer page) {
		log.debug("finding Project instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Project as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setFirstResult(page*Config.STRING_INVESTOR_LIST_MAX_SIZE);
			queryObject.setMaxResults(Config.STRING_INVESTOR_LIST_MAX_SIZE);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Project> findByName(Object name) {
		String sqlString = "select * from project where full_name like ?";
		
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString).addEntity(Project.class);
		queryObject.setParameter(0, "%"+name+"%");
		
		return queryObject.list();
	}
	

	public List<Project> findByAbbrevName(Object abbrevName) {
		return findByProperty(ABBREV_NAME, abbrevName);
	}

	public List<Project> findByFullName(Object fullName) {
		return findByProperty(FULL_NAME, fullName);
	}

	public List<Project> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Project> findByProjectType(Object projectType) {
		return findByProperty(PROJECT_TYPE, projectType);
	}

	public List<Project> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Project> findByStartPageImage(Object startPageImage) {
		return findByProperty(START_PAGE_IMAGE, startPageImage);
	}

	public List findAll() {
		log.debug("finding all Project instances");
		try {
			String queryString = "from Project";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByPage(Integer start,Integer size) {
		log.debug("finding all Projects instances");
		try {
			String queryString = "from Project";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(start*size);
			queryObject.setMaxResults(size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer countOfAllUsers() {
		log.debug("finding all Users instances");
		try {
			String queryString = "select count(*) from project";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByCursor(int cursor){
		log.debug("finding Project instances by cursor");
		try {
			String queryString = "from Project";
			Query queryObject = getCurrentSession().createQuery(queryString)
					.setFirstResult(cursor*Config.STRING_FEELING_PAGESIZE)
					.setMaxResults(Config.STRING_FEELING_PAGESIZE)
					;
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPropertiesWithPage(Map requestMap,Integer page) {
		try {
//			String debugInfo= "finding Loginfailrecord instance with property: ";
			String queryString = "from Project as model where";
			Object[] keys= requestMap.keySet().toArray();
			for(int i = 0;i<requestMap.size();i++){
//				debugInfo += keys[i].toString()+requestMap.get(keys[i]);
				if(i==0){
					queryString+=" model."+keys[i].toString()+" =? ";
				}else{
					queryString+="and model."+keys[i].toString()+" =? ";
				}
			}
			
			queryString +=" order by model.projectId desc";
//			log.debug(debugInfo);
			
			Query queryObject = getCurrentSession().createQuery(queryString);
			for(int i = 0;i<requestMap.size();i++){
				queryObject.setParameter(i, requestMap.get(keys[i]));
			}
			queryObject.setFirstResult(page*Config.STRING_INVESTOR_LIST_MAX_SIZE);
			queryObject.setMaxResults(Config.STRING_INVESTOR_LIST_MAX_SIZE);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findProjectHomeList(Integer page,Integer type,Integer size)
	{
		String sqlString = "select * from project where status_id <>3 order by status_id desc , sort_index desc";
		
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString).addEntity(Project.class);
//		queryObject.setParameter(0, type);
		queryObject.setFirstResult(page*size);
		queryObject.setMaxResults(size);
		
		return queryObject.list();
	}

	public Project merge(Project detachedInstance) {
		log.debug("merging Project instance");
		try {
			Project result = (Project) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Project instance) {
		log.debug("attaching dirty Project instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Project instance) {
		log.debug("attaching clean Project instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProjectDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProjectDAO) ctx.getBean("ProjectDAO");
	}
	
	
	public List findProjectSearchList(Integer page, ProjectSearchBean type,
			List range, ProjectSearchBean address) {
		String sqlString = "select distinct pro from Project  pro left join pro.roadshows b  where ";
		List<MapResult> typeResults = new ArrayList<ProjectSearchBean.MapResult>();
		List<ProjectRange> ranlList = range;
		List<MapResult> addressResults = new ArrayList<ProjectSearchBean.MapResult>();
		if (type != null) {
			typeResults = type.getcData();
		}
		if(ranlList==null){
			ranlList = new ArrayList<ProjectRange>();
		}
	
		if (address != null) {
			addressResults = address.getcData();
		}
		if(ranlList.size()>0){
			for(int j = 0;j<ranlList.size();j++){
				sqlString += " b.roadshowplan.financeTotal between "+ranlList.get(j).getFrom()+" and "+ranlList.get(j).getTo() ;
				if(j!=ranlList.size()-1&&ranlList.size()>1){
					sqlString += " or ";
				}
			}
		}
		if(addressResults.size()+typeResults.size()>0&&ranlList.size()>0){
			sqlString += " and ";
		}
		
		if (typeResults.size() > 0) {
			for (int i = 0; i < typeResults.size(); i++) {
				System.out.println("value::" + typeResults.get(i).getValue());
				sqlString += "pro.industoryType like :stype"
						+ typeResults.get(i).getItemKey() + " ";
				if (i != typeResults.size() - 1 && typeResults.size() > 1) {
					sqlString += " or ";
				}
			}
		}
		if(addressResults.size()>0&&typeResults.size()>0){
			sqlString += " and ";
		}
		
		if (addressResults.size() > 0) {
			for (int i = 0; i < addressResults.size(); i++) {
				System.out.println("address::" + addressResults.get(i).getValue());
				sqlString += "pro.address like :saddress"
						+ addressResults.get(i).getItemKey() + " ";
				if (i != addressResults.size() - 1 && addressResults.size() > 1) {
					sqlString += " or ";
				}
			}
		}
		
//		//筛选3
		if(addressResults.size()+typeResults.size()+ranlList.size()>0){
			sqlString += " and ";
		}
//		
//		if (ranlList.size() > 0) {
//			for (int i = 0; i < ranlList.size(); i++) {
//				System.out.println("range::" + ranlList.get(i).getValue());
//				sqlString += "pro.address like :saddress"
//						+ ranlList.get(i).getItemKey() + " ";
//				if (i != ranlList.size() - 1 && ranlList.size() > 1) {
//					sqlString += " or ";
//				}
//			}
//		}
		

		// queryString =
		// "from Msg msg where msg.title like :stitle order by msg.publicDate desc";
		// q = getCurrentSession().createQuery(queryString);
		// q.setParameter("stitle", "%"+ likeWords+"%" );
		//
//		if (addressResults.size() > 0) {
//			sqlString += " and ";
//		}
		
		 sqlString +=
		 " pro.financestatus.statusId between 3 and 6  order by pro.financestatus.statusId desc";
//		 " pro.financestatus.statusId=3 or pro.financestatus.statusId=6";
		System.out.println("sql:" + sqlString);
		Query queryObject = getCurrentSession().createQuery(sqlString);

		//setParameter type
		for (int i = 0; i < typeResults.size(); i++) {
			queryObject.setParameter("stype" + typeResults.get(i).getItemKey(),
					"%" + typeResults.get(i).getValue() + "%");
		}
		
		//setParameter type
		for (int i = 0; i < addressResults.size(); i++) {
			queryObject.setParameter("saddress" + addressResults.get(i).getItemKey(),
					"%" + addressResults.get(i).getValue() + "%");
		}
		queryObject.setFirstResult(page * 12);
		queryObject.setMaxResults(12);

		return queryObject.list();
	}
	
	public List<Project> findProjectSearchFromStrList(Integer page,
			String search) {
		String sqlString = "from Project  pro where  pro.abbrevName like :fullname or pro.fullName like :comname or pro.description like :desc";

//		if (typeResults.size() > 0) {
//			for (int i = 0; i < typeResults.size(); i++) {
//				System.out.println("value::" + typeResults.get(i).getValue());
//				sqlString += "pro.industoryType like :stype"
//						+ typeResults.get(i).getItemKey() + " ";
//				if (i != typeResults.size() - 1 && typeResults.size() > 1) {
//					sqlString += " or ";
//				}
//			}
//		}
		
		 sqlString +=
		 " and  pro.financestatus.statusId between 3 and 6  order by pro.financestatus.statusId desc";
//		 " pro.financestatus.statusId=3 or pro.financestatus.statusId=6";
		System.out.println("sql:" + sqlString);
		Query queryObject = getCurrentSession().createQuery(sqlString);
		
		queryObject.setParameter("fullname" ,"%" + search + "%");
		queryObject.setParameter("comname" ,"%" + search + "%");
		queryObject.setParameter("desc" ,"%" + search + "%");
		
		queryObject.setFirstResult(page * 12);
		queryObject.setMaxResults(12);

		return queryObject.list();
	}
}