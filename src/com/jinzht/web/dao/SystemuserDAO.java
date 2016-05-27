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

import com.jinzht.web.entity.Systemuser;

/**
 * A data access object (DAO) providing persistence and search support for
 * Systemuser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Systemuser
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SystemuserDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SystemuserDAO.class);
	// property constants
	public static final String PASSWORD = "password";

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

	public void save(Systemuser transientInstance) {
		log.debug("saving Systemuser instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Systemuser persistentInstance) {
		log.debug("deleting Systemuser instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Systemuser findById(java.lang.Integer id) {
		log.debug("getting Systemuser instance with id: " + id);
		try {
			Systemuser instance = (Systemuser) getCurrentSession().get(
					"com.jinzht.web.hibernate.Systemuser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Systemuser> findByExample(Systemuser instance) {
		log.debug("finding Systemuser instance by example");
		try {
			List<Systemuser> results = (List<Systemuser>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Systemuser")
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
		log.debug("finding Systemuser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Systemuser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Systemuser> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findAll() {
		log.debug("finding all Systemuser instances");
		try {
			String queryString = "from Systemuser";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Systemuser merge(Systemuser detachedInstance) {
		log.debug("merging Systemuser instance");
		try {
			Systemuser result = (Systemuser) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Systemuser instance) {
		log.debug("attaching dirty Systemuser instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Systemuser instance) {
		log.debug("attaching clean Systemuser instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SystemuserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SystemuserDAO) ctx.getBean("SystemuserDAO");
	}
}