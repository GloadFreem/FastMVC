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

import com.jinzht.web.entity.Messagetype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Messagetype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Messagetype
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class MessagetypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MessagetypeDAO.class);
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

	public void save(Messagetype transientInstance) {
		log.debug("saving Messagetype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Messagetype persistentInstance) {
		log.debug("deleting Messagetype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Messagetype findById(java.lang.Integer id) {
		log.debug("getting Messagetype instance with id: " + id);
		try {
			Messagetype instance = (Messagetype) getCurrentSession().get(
					"com.jinzht.web.hibernate.Messagetype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Messagetype> findByExample(Messagetype instance) {
		log.debug("finding Messagetype instance by example");
		try {
			List<Messagetype> results = (List<Messagetype>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Messagetype")
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
		log.debug("finding Messagetype instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Messagetype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Messagetype> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Messagetype instances");
		try {
			String queryString = "from Messagetype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Messagetype merge(Messagetype detachedInstance) {
		log.debug("merging Messagetype instance");
		try {
			Messagetype result = (Messagetype) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Messagetype instance) {
		log.debug("attaching dirty Messagetype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Messagetype instance) {
		log.debug("attaching clean Messagetype instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MessagetypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MessagetypeDAO) ctx.getBean("MessagetypeDAO");
	}
}