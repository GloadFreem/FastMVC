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

import com.jinzht.web.entity.Industorytype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Industorytype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Industorytype
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class IndustorytypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(IndustorytypeDAO.class);
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

	public void save(Industorytype transientInstance) {
		log.debug("saving Industorytype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Industorytype persistentInstance) {
		log.debug("deleting Industorytype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Industorytype findById(java.lang.Integer id) {
		log.debug("getting Industorytype instance with id: " + id);
		try {
			Industorytype instance = (Industorytype) getCurrentSession().get(
					"com.jinzht.web.entity.Industorytype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Industorytype> findByExample(Industorytype instance) {
		log.debug("finding Industorytype instance by example");
		try {
			List<Industorytype> results = (List<Industorytype>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.Industorytype")
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
		log.debug("finding Industorytype instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Industorytype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Industorytype> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Industorytype instances");
		try {
			String queryString = "from Industorytype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Industorytype merge(Industorytype detachedInstance) {
		log.debug("merging Industorytype instance");
		try {
			Industorytype result = (Industorytype) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Industorytype instance) {
		log.debug("attaching dirty Industorytype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Industorytype instance) {
		log.debug("attaching clean Industorytype instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IndustorytypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IndustorytypeDAO) ctx.getBean("IndustorytypeDAO");
	}
}