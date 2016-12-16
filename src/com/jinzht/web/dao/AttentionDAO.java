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
import com.jinzht.web.entity.Attention;
import com.jinzht.web.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for
 * Attention entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Attention
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AttentionDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AttentionDAO.class);
	// property constants
	public static final String CONTENT = "content";

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

	public void save(Attention transientInstance) {
		log.debug("saving Attention instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Attention persistentInstance) {
		log.debug("deleting Attention instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Attention findById(java.lang.Integer id) {
		log.debug("getting Attention instance with id: " + id);
		try {
			Attention instance = (Attention) getCurrentSession().get(
					"com.jinzht.web.hibernate.Attention", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Attention> findByExample(Attention instance) {
		log.debug("finding Attention instance by example");
		try {
			List<Attention> results = (List<Attention>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Attention")
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
		log.debug("finding Attention instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Attention as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//根据活动及用户获取用户是否报名
	public List findAttentionByUserIdAndActionId(Integer actionId,Users user)
	{
		String sqlString ="select * from attention where  action_id=?";
		if(user!=null){
			sqlString= "select * from attention where  action_id=? and user_id=?";
		}
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString).addEntity(Attention.class);
		queryObject.setParameter(0, actionId);
		if(user!=null)
		{
			queryObject.setParameter(1, user.getUserId());
		}
		queryObject.setMaxResults(1);
		
		return queryObject.list();
	}
	
	public List findByPropertyWithPage(String propertyName, Object value,Integer page) {
		log.debug("finding Attention instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Attention as model where model."
					+ propertyName + " = ? order by attendUid desc";
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
	public List findByAttendWithActionPage(Object value,Integer page) {
		try {
			String queryString = "select * from  attention as model where model.action_id = ? order by attend_uid desc";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString).addEntity(Attention.class);
			queryObject.setParameter(0, value);
			queryObject.setFirstResult(page*Config.STRING_INVESTOR_LIST_MAX_SIZE);
			queryObject.setMaxResults(Config.STRING_INVESTOR_LIST_MAX_SIZE);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Attention> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Attention instances");
		try {
			String queryString = "from Attention";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByPropertiesWithPage(Map requestMap,Integer page) {
		try {
//			String debugInfo= "finding Loginfailrecord instance with property: ";
			String queryString = "from Attention as model where";
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
	public List findBySQLPropertiesWithPage(Map requestMap,Integer page) {
		try {
//			String debugInfo= "finding Loginfailrecord instance with property: ";
			String queryString = "select action_id from attention as model where";
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
			
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
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
	
	public List findUserIdByAttentionId(Integer attentionId)
	{
		log.debug("find userId by attentionId");
		String sqlString = "select user_id from attention where attend_uid = ?";
		
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString);
		queryObject.setParameter(0, attentionId);
		queryObject.setMaxResults(1);
		
		return queryObject.list();
		
	}
	public Attention merge(Attention detachedInstance) {
		log.debug("merging Attention instance");
		try {
			Attention result = (Attention) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Attention instance) {
		log.debug("attaching dirty Attention instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Attention instance) {
		log.debug("attaching clean Attention instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AttentionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AttentionDAO) ctx.getBean("AttentionDAO");
	}
}