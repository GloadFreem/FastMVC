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

import com.jinzht.web.entity.Authenticstatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * Authenticstatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Authenticstatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AuthenticstatusDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AuthenticstatusDAO.class);
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

	public void save(Authenticstatus transientInstance) {
		log.debug("saving Authenticstatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Authenticstatus persistentInstance) {
		log.debug("deleting Authenticstatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Authenticstatus findById(java.lang.Integer id) {
		log.debug("getting Authenticstatus instance with id: " + id);
		try {
			Authenticstatus instance = (Authenticstatus) getCurrentSession()
					.get("Authenticstatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Authenticstatus> findByExample(Authenticstatus instance) {
		log.debug("finding Authenticstatus instance by example");
		try {
			List<Authenticstatus> results = (List<Authenticstatus>) getCurrentSession()
					.createCriteria("Authenticstatus").add(create(instance))
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
		log.debug("finding Authenticstatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Authenticstatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Authenticstatus> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Authenticstatus instances");
		try {
			String queryString = "from Authenticstatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Authenticstatus merge(Authenticstatus detachedInstance) {
		log.debug("merging Authenticstatus instance");
		try {
			Authenticstatus result = (Authenticstatus) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Authenticstatus instance) {
		log.debug("attaching dirty Authenticstatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Authenticstatus instance) {
		log.debug("attaching clean Authenticstatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AuthenticstatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AuthenticstatusDAO) ctx.getBean("AuthenticstatusDAO");
	}
}