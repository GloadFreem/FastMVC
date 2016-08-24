package com.jinzht.web.dao;
// default package

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

import com.jinzht.web.entity.Contentshare;

/**
 * A data access object (DAO) providing persistence and search support for
 * Contentshare entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Contentshare
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ContentshareDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ContentshareDAO.class);
	// property constants
	public static final String IMAGE = "image";
	public static final String DESC = "desc";
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

	public void save(Contentshare transientInstance) {
		log.debug("saving Contentshare instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Contentshare persistentInstance) {
		log.debug("deleting Contentshare instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Contentshare findById(java.lang.Integer id) {
		log.debug("getting Contentshare instance with id: " + id);
		try {
			Contentshare instance = (Contentshare) getCurrentSession().get(
					"Contentshare", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Contentshare> findByExample(Contentshare instance) {
		log.debug("finding Contentshare instance by example");
		try {
			List<Contentshare> results = (List<Contentshare>) getCurrentSession()
					.createCriteria("Contentshare").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Contentshare instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Contentshare as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Contentshare> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List<Contentshare> findByDesc(Object desc) {
		return findByProperty(DESC, desc);
	}

	public List<Contentshare> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Contentshare instances");
		try {
			String queryString = "from Contentshare";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Contentshare merge(Contentshare detachedInstance) {
		log.debug("merging Contentshare instance");
		try {
			Contentshare result = (Contentshare) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Contentshare instance) {
		log.debug("attaching dirty Contentshare instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Contentshare instance) {
		log.debug("attaching clean Contentshare instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ContentshareDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ContentshareDAO) ctx.getBean("ContentshareDAO");
	}
}