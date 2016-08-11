package com.jinzht.web.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
import com.jinzht.web.entity.Investmentrecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * Investmentrecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Investmentrecord
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class InvestmentrecordDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InvestmentrecordDAO.class);
	// property constants
	public static final String STATUS_ID = "statusId";
	public static final String PROJECT_ID = "projectId";
	public static final String INVEST_AMOUNT = "investAmount";

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

	public void save(Investmentrecord transientInstance) {
		log.debug("saving Investmentrecord instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Investmentrecord persistentInstance) {
		log.debug("deleting Investmentrecord instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Investmentrecord findById(java.lang.Integer id) {
		log.debug("getting Investmentrecord instance with id: " + id);
		try {
			Investmentrecord instance = (Investmentrecord) getCurrentSession()
					.get("com.jinzht.web.hibernate.Investmentrecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Investmentrecord> findByExample(Investmentrecord instance) {
		log.debug("finding Investmentrecord instance by example");
		try {
			List<Investmentrecord> results = (List<Investmentrecord>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Investmentrecord")
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
		log.debug("finding Investmentrecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Investmentrecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public Integer findProjectIdByRecordId(Integer recordId)
	{
		List list = null;
		
		//查询
		String sqlString ="select project_id from Investmentrecord where invest_id=?";
		//查询对象 	
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString);
		queryObject.setParameter(0, recordId);
		queryObject.setMaxResults(1);
		list = queryObject.list();
		if(list!=null && list.size()>0)
		{
			return (Integer)list.get(0);
		}
		return null;
	}
	
	public List findByProperties(Map requestMap,Integer page) {
		try {
//			String debugInfo= "finding Authentic instance with property: ";
			String queryString = "from Investmentrecord as model where";
			Object[] keys= requestMap.keySet().toArray();
			for(int i = 0;i<requestMap.size();i++){
//				debugInfo += keys[i].toString()+requestMap.get(keys[i]);
				if(i==0){
					queryString+=" model."+keys[i].toString()+" =? ";
				}else{
					queryString+="and model."+keys[i].toString()+" =? ";
				}
			}
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

	public List<Investmentrecord> findByStatusId(Object statusId) {
		return findByProperty(STATUS_ID, statusId);
	}

	public List<Investmentrecord> findByProjectId(Object projectId) {
		return findByProperty(PROJECT_ID, projectId);
	}

	public List<Investmentrecord> findByInvestAmount(Object investAmount) {
		return findByProperty(INVEST_AMOUNT, investAmount);
	}

	public List findAll() {
		log.debug("finding all Investmentrecord instances");
		try {
			String queryString = "from Investmentrecord";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Investmentrecord merge(Investmentrecord detachedInstance) {
		log.debug("merging Investmentrecord instance");
		try {
			Investmentrecord result = (Investmentrecord) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Investmentrecord instance) {
		log.debug("attaching dirty Investmentrecord instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Investmentrecord instance) {
		log.debug("attaching clean Investmentrecord instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static InvestmentrecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (InvestmentrecordDAO) ctx.getBean("InvestmentrecordDAO");
	}
}