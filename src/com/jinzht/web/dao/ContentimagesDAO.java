package com.jinzht.web.dao;

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

import com.jinzht.web.entity.Contentimages;

/**
 * A data access object (DAO) providing persistence and search support for
 * Contentimages entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Contentimages
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ContentimagesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ContentimagesDAO.class);
	// property constants
	public static final String ISVALID = "isvalid";

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

	public void save(Contentimages transientInstance) {
		log.debug("saving Contentimages instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Contentimages persistentInstance) {
		log.debug("deleting Contentimages instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Contentimages findById(java.lang.Integer id) {
		log.debug("getting Contentimages instance with id: " + id);
		try {
			Contentimages instance = (Contentimages) getCurrentSession().get(
					"com.jinzht.web.hibernate.Contentimages", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Contentimages> findByExample(Contentimages instance) {
		log.debug("finding Contentimages instance by example");
		try {
			List<Contentimages> results = (List<Contentimages>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Contentimages")
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
		log.debug("finding Contentimages instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Contentimages as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Contentimages> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all Contentimages instances");
		try {
			String queryString = "from Contentimages";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Contentimages merge(Contentimages detachedInstance) {
		log.debug("merging Contentimages instance");
		try {
			Contentimages result = (Contentimages) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Contentimages instance) {
		log.debug("attaching dirty Contentimages instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Contentimages instance) {
		log.debug("attaching clean Contentimages instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ContentimagesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ContentimagesDAO) ctx.getBean("ContentimagesDAO");
	}
}