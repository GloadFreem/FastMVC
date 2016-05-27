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

import com.jinzht.web.entity.Preloadingpage;

/**
 * A data access object (DAO) providing persistence and search support for
 * Preloadingpage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Preloadingpage
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class PreloadingpageDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PreloadingpageDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String IMAGE_PATH = "imagePath";
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

	public void save(Preloadingpage transientInstance) {
		log.debug("saving Preloadingpage instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Preloadingpage persistentInstance) {
		log.debug("deleting Preloadingpage instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Preloadingpage findById(java.lang.Integer id) {
		log.debug("getting Preloadingpage instance with id: " + id);
		try {
			Preloadingpage instance = (Preloadingpage) getCurrentSession().get(
					"com.jinzht.web.hibernate.Preloadingpage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Preloadingpage> findByExample(Preloadingpage instance) {
		log.debug("finding Preloadingpage instance by example");
		try {
			List<Preloadingpage> results = (List<Preloadingpage>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Preloadingpage")
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
		log.debug("finding Preloadingpage instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Preloadingpage as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Preloadingpage> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Preloadingpage> findByImagePath(Object imagePath) {
		return findByProperty(IMAGE_PATH, imagePath);
	}

	public List<Preloadingpage> findByPlatform(Object platform) {
		return findByProperty(PLATFORM, platform);
	}

	public List findAll() {
		log.debug("finding all Preloadingpage instances");
		try {
			String queryString = "from Preloadingpage";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Preloadingpage merge(Preloadingpage detachedInstance) {
		log.debug("merging Preloadingpage instance");
		try {
			Preloadingpage result = (Preloadingpage) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Preloadingpage instance) {
		log.debug("attaching dirty Preloadingpage instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Preloadingpage instance) {
		log.debug("attaching clean Preloadingpage instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PreloadingpageDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PreloadingpageDAO) ctx.getBean("PreloadingpageDAO");
	}
}