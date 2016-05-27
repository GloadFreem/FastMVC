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

import com.jinzht.web.entity.Sustomservice;

/**
 * A data access object (DAO) providing persistence and search support for
 * Sustomservice entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Sustomservice
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SustomserviceDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SustomserviceDAO.class);
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

	public void save(Sustomservice transientInstance) {
		log.debug("saving Sustomservice instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Sustomservice persistentInstance) {
		log.debug("deleting Sustomservice instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sustomservice findById(java.lang.Integer id) {
		log.debug("getting Sustomservice instance with id: " + id);
		try {
			Sustomservice instance = (Sustomservice) getCurrentSession().get(
					"com.jinzht.web.hibernate.Sustomservice", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Sustomservice> findByExample(Sustomservice instance) {
		log.debug("finding Sustomservice instance by example");
		try {
			List<Sustomservice> results = (List<Sustomservice>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Sustomservice")
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
		log.debug("finding Sustomservice instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Sustomservice as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Sustomservice> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Sustomservice instances");
		try {
			String queryString = "from Sustomservice";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Sustomservice merge(Sustomservice detachedInstance) {
		log.debug("merging Sustomservice instance");
		try {
			Sustomservice result = (Sustomservice) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Sustomservice instance) {
		log.debug("attaching dirty Sustomservice instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sustomservice instance) {
		log.debug("attaching clean Sustomservice instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SustomserviceDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SustomserviceDAO) ctx.getBean("SustomserviceDAO");
	}
}