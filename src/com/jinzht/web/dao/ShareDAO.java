package com.jinzht.web.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.jinzht.web.entity.Share;

/**
 * A data access object (DAO) providing persistence and search support for Share
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.jinzht.web.entity.Share
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ShareDAO {
	private static final Logger log = LoggerFactory.getLogger(ShareDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String URL = "url";

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

	public void save(Share transientInstance) {
		log.debug("saving Share instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Share persistentInstance) {
		log.debug("deleting Share instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Share findById(java.lang.Integer id) {
		log.debug("getting Share instance with id: " + id);
		try {
			Share instance = (Share) getCurrentSession().get(
					"com.jinzht.web.hibernate.Share", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Share> findByExample(Share instance) {
		log.debug("finding Share instance by example");
		try {
			List<Share> results = (List<Share>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Share")
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
		log.debug("finding Share instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Share as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public Integer counterByProperties(Map map) {
		try {
			String queryString = "select count(model.shareId) as count from Share as model where model.";
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
	public List<Share> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Share> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all Share instances");
		try {
			String queryString = "from Share";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Share merge(Share detachedInstance) {
		log.debug("merging Share instance");
		try {
			Share result = (Share) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Share instance) {
		log.debug("attaching dirty Share instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Share instance) {
		log.debug("attaching clean Share instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ShareDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ShareDAO) ctx.getBean("ShareDAO");
	}
}