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

import com.jinzht.web.entity.Tradestatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tradestatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Tradestatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TradestatusDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TradestatusDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String IS_VALID = "isValid";

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

	public void save(Tradestatus transientInstance) {
		log.debug("saving Tradestatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tradestatus persistentInstance) {
		log.debug("deleting Tradestatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tradestatus findById(java.lang.Integer id) {
		log.debug("getting Tradestatus instance with id: " + id);
		try {
			Tradestatus instance = (Tradestatus) getCurrentSession().get(
					"Tradestatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Tradestatus> findByExample(Tradestatus instance) {
		log.debug("finding Tradestatus instance by example");
		try {
			List<Tradestatus> results = (List<Tradestatus>) getCurrentSession()
					.createCriteria("Tradestatus").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tradestatus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tradestatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Tradestatus> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Tradestatus> findByIsValid(Object isValid) {
		return findByProperty(IS_VALID, isValid);
	}

	public List findAll() {
		log.debug("finding all Tradestatus instances");
		try {
			String queryString = "from Tradestatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tradestatus merge(Tradestatus detachedInstance) {
		log.debug("merging Tradestatus instance");
		try {
			Tradestatus result = (Tradestatus) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tradestatus instance) {
		log.debug("attaching dirty Tradestatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tradestatus instance) {
		log.debug("attaching clean Tradestatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TradestatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TradestatusDAO) ctx.getBean("TradestatusDAO");
	}
}