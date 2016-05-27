package com.jinzht.web.dao;

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

import com.jinzht.web.entity.Userstatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * Userstatus entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Userstatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class UserstatusDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UserstatusDAO.class);
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

	public void save(Userstatus transientInstance) {
		log.debug("saving Userstatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Userstatus persistentInstance) {
		log.debug("deleting Userstatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Userstatus findById(java.lang.Integer id) {
		log.debug("getting Userstatus instance with id: " + id);
		try {
			Userstatus instance = (Userstatus) getCurrentSession().get(
					"com.jinzht.web.hibernate.Userstatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Userstatus> findByExample(Userstatus instance) {
		log.debug("finding Userstatus instance by example");
		try {
			List<Userstatus> results = (List<Userstatus>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Userstatus")
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
		log.debug("finding Userstatus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Userstatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Userstatus> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Userstatus instances");
		try {
			String queryString = "from Userstatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Userstatus merge(Userstatus detachedInstance) {
		log.debug("merging Userstatus instance");
		try {
			Userstatus result = (Userstatus) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Userstatus instance) {
		log.debug("attaching dirty Userstatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Userstatus instance) {
		log.debug("attaching clean Userstatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserstatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserstatusDAO) ctx.getBean("UserstatusDAO");
	}
}