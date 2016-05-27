package com.jinzht.web.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.jinzht.web.entity.Loginfailrecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * Loginfailrecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Loginfailrecord
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class LoginfailrecordDAO {
	private static final Logger log = LoggerFactory
			.getLogger(LoginfailrecordDAO.class);
	// property constants
	public static final String COUNT = "count";
	public static final String PLATFORM = "platform";

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

	public void save(Loginfailrecord transientInstance) {
		log.debug("saving Loginfailrecord instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Loginfailrecord persistentInstance) {
		log.debug("deleting Loginfailrecord instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Loginfailrecord findById(java.lang.Integer id) {
		log.debug("getting Loginfailrecord instance with id: " + id);
		try {
			Loginfailrecord instance = (Loginfailrecord) getCurrentSession()
					.get("com.jinzht.web.hibernate.Loginfailrecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Loginfailrecord> findByExample(Loginfailrecord instance) {
		log.debug("finding Loginfailrecord instance by example");
		try {
			List<Loginfailrecord> results = (List<Loginfailrecord>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Loginfailrecord")
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
		log.debug("finding Loginfailrecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Loginfailrecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Loginfailrecord> findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List<Loginfailrecord> findByPlatform(Object platform) {
		return findByProperty(PLATFORM, platform);
	}

	public List findAll() {
		log.debug("finding all Loginfailrecord instances");
		try {
			String queryString = "from Loginfailrecord";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Loginfailrecord merge(Loginfailrecord detachedInstance) {
		log.debug("merging Loginfailrecord instance");
		try {
			Loginfailrecord result = (Loginfailrecord) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Loginfailrecord instance) {
		log.debug("attaching dirty Loginfailrecord instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Loginfailrecord instance) {
		log.debug("attaching clean Loginfailrecord instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LoginfailrecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LoginfailrecordDAO) ctx.getBean("LoginfailrecordDAO");
	}
}