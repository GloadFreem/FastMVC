package com.jinzht.web.dao;

import java.sql.Timestamp;
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

import com.jinzht.web.entity.Versioncontroll;

/**
 * A data access object (DAO) providing persistence and search support for
 * Versioncontroll entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Versioncontroll
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class VersioncontrollDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VersioncontrollDAO.class);
	// property constants
	public static final String VERSION_STR = "versionStr";
	public static final String CONTENT = "content";
	public static final String URL = "url";
	public static final String IS_FORCE = "isForce";
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

	public void save(Versioncontroll transientInstance) {
		log.debug("saving Versioncontroll instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Versioncontroll persistentInstance) {
		log.debug("deleting Versioncontroll instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Versioncontroll findById(java.lang.Integer id) {
		log.debug("getting Versioncontroll instance with id: " + id);
		try {
			Versioncontroll instance = (Versioncontroll) getCurrentSession()
					.get("com.jinzht.web.hibernate.Versioncontroll", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Versioncontroll> findByExample(Versioncontroll instance) {
		log.debug("finding Versioncontroll instance by example");
		try {
			List<Versioncontroll> results = (List<Versioncontroll>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Versioncontroll")
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
		log.debug("finding Versioncontroll instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Versioncontroll as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Versioncontroll> findByVersionStr(Object versionStr) {
		return findByProperty(VERSION_STR, versionStr);
	}

	public List<Versioncontroll> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Versioncontroll> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<Versioncontroll> findByIsForce(Object isForce) {
		return findByProperty(IS_FORCE, isForce);
	}

	public List<Versioncontroll> findByPlatform(Object platform) {
		return findByProperty(PLATFORM, platform);
	}

	public List findAll() {
		log.debug("finding all Versioncontroll instances");
		try {
			String queryString = "from Versioncontroll";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Versioncontroll merge(Versioncontroll detachedInstance) {
		log.debug("merging Versioncontroll instance");
		try {
			Versioncontroll result = (Versioncontroll) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Versioncontroll instance) {
		log.debug("attaching dirty Versioncontroll instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Versioncontroll instance) {
		log.debug("attaching clean Versioncontroll instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static VersioncontrollDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (VersioncontrollDAO) ctx.getBean("VersioncontrollDAO");
	}
}