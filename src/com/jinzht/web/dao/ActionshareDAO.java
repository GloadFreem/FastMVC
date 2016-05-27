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

import com.jinzht.web.entity.Actionshare;

/**
 * A data access object (DAO) providing persistence and search support for
 * Actionshare entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Actionshare
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ActionshareDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ActionshareDAO.class);
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

	public void save(Actionshare transientInstance) {
		log.debug("saving Actionshare instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Actionshare persistentInstance) {
		log.debug("deleting Actionshare instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Actionshare findById(java.lang.Integer id) {
		log.debug("getting Actionshare instance with id: " + id);
		try {
			Actionshare instance = (Actionshare) getCurrentSession().get(
					"com.jinzht.web.hibernate.Actionshare", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Actionshare> findByExample(Actionshare instance) {
		log.debug("finding Actionshare instance by example");
		try {
			List<Actionshare> results = (List<Actionshare>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Actionshare")
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
		log.debug("finding Actionshare instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Actionshare as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Actionshare> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Actionshare instances");
		try {
			String queryString = "from Actionshare";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Actionshare merge(Actionshare detachedInstance) {
		log.debug("merging Actionshare instance");
		try {
			Actionshare result = (Actionshare) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Actionshare instance) {
		log.debug("attaching dirty Actionshare instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Actionshare instance) {
		log.debug("attaching clean Actionshare instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ActionshareDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ActionshareDAO) ctx.getBean("ActionshareDAO");
	}
}