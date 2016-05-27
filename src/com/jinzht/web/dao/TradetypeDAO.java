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

import com.jinzht.web.entity.Tradetype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tradetype entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Tradetype
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TradetypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TradetypeDAO.class);
	// property constants
	public static final String NAME = "name";
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

	public void save(Tradetype transientInstance) {
		log.debug("saving Tradetype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tradetype persistentInstance) {
		log.debug("deleting Tradetype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tradetype findById(java.lang.Integer id) {
		log.debug("getting Tradetype instance with id: " + id);
		try {
			Tradetype instance = (Tradetype) getCurrentSession().get(
					"com.jinzht.web.hibernate.Tradetype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Tradetype> findByExample(Tradetype instance) {
		log.debug("finding Tradetype instance by example");
		try {
			List<Tradetype> results = (List<Tradetype>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Tradetype")
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
		log.debug("finding Tradetype instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tradetype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Tradetype> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Tradetype> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all Tradetype instances");
		try {
			String queryString = "from Tradetype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tradetype merge(Tradetype detachedInstance) {
		log.debug("merging Tradetype instance");
		try {
			Tradetype result = (Tradetype) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tradetype instance) {
		log.debug("attaching dirty Tradetype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tradetype instance) {
		log.debug("attaching clean Tradetype instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TradetypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TradetypeDAO) ctx.getBean("TradetypeDAO");
	}
}