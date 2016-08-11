package com.jinzht.web.dao;

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
import com.jinzht.web.entity.Actionprise;

/**
 * A data access object (DAO) providing persistence and search support for
 * Actionprise entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Actionprise
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ActionpriseDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ActionpriseDAO.class);
	// property constants

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

	public void save(Actionprise transientInstance) {
		log.debug("saving Actionprise instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Actionprise persistentInstance) {
		log.debug("deleting Actionprise instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Actionprise findById(java.lang.Integer id) {
		log.debug("getting Actionprise instance with id: " + id);
		try {
			Actionprise instance = (Actionprise) getCurrentSession().get(
					"com.jinzht.web.hibernate.Actionprise", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Actionprise> findByExample(Actionprise instance) {
		log.debug("finding Actionprise instance by example");
		try {
			List<Actionprise> results = (List<Actionprise>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Actionprise")
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
		log.debug("finding Actionprise instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Actionprise as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByProperties(Map requestMap,Integer page) {
		try {
//			String debugInfo= "finding Authentic instance with property: ";
			String queryString = "from Actionprise as model where";
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
	
	public List findUsersIdByPriseId(Integer priseId)
	{
		String sqlString = "select user_id from actionprise where prise_id = ?";
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString);
		queryObject.setParameter(0, priseId);
		queryObject.setMaxResults(1);
		
		return queryObject.list();
		
	}

	public List findAll() {
		log.debug("finding all Actionprise instances");
		try {
			String queryString = "from Actionprise";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Actionprise merge(Actionprise detachedInstance) {
		log.debug("merging Actionprise instance");
		try {
			Actionprise result = (Actionprise) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Actionprise instance) {
		log.debug("attaching dirty Actionprise instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Actionprise instance) {
		log.debug("attaching clean Actionprise instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ActionpriseDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ActionpriseDAO) ctx.getBean("ActionpriseDAO");
	}
}