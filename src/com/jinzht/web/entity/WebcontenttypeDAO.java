package com.jinzht.web.entity;

import java.util.List;
import java.util.Set;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Webcontenttype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Webcontenttype
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class WebcontenttypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(WebcontenttypeDAO.class);
	// property constants
	public static final String NAME = "name";

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

	public void save(Webcontenttype transientInstance) {
		log.debug("saving Webcontenttype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Webcontenttype persistentInstance) {
		log.debug("deleting Webcontenttype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Webcontenttype findById(java.lang.Integer id) {
		log.debug("getting Webcontenttype instance with id: " + id);
		try {
			Webcontenttype instance = (Webcontenttype) getCurrentSession().get(
					"com.jinzht.web.entity.Webcontenttype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Webcontenttype> findByExample(Webcontenttype instance) {
		log.debug("finding Webcontenttype instance by example");
		try {
			List<Webcontenttype> results = (List<Webcontenttype>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.Webcontenttype")
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
		log.debug("finding Webcontenttype instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Webcontenttype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Webcontenttype> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Webcontenttype instances");
		try {
			String queryString = "from Webcontenttype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Webcontenttype merge(Webcontenttype detachedInstance) {
		log.debug("merging Webcontenttype instance");
		try {
			Webcontenttype result = (Webcontenttype) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Webcontenttype instance) {
		log.debug("attaching dirty Webcontenttype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Webcontenttype instance) {
		log.debug("attaching clean Webcontenttype instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static WebcontenttypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (WebcontenttypeDAO) ctx.getBean("WebcontenttypeDAO");
	}
}