package com.jinzht.web.dao;
// default package

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

import com.jinzht.web.entity.Actionintroduce;

/**
 * A data access object (DAO) providing persistence and search support for
 * Actionintroduce entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Actionintroduce
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ActionintroduceDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ActionintroduceDAO.class);
	// property constants
	public static final String TYPE = "type";
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

	public void save(Actionintroduce transientInstance) {
		log.debug("saving Actionintroduce instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Actionintroduce persistentInstance) {
		log.debug("deleting Actionintroduce instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Actionintroduce findById(java.lang.Integer id) {
		log.debug("getting Actionintroduce instance with id: " + id);
		try {
			Actionintroduce instance = (Actionintroduce) getCurrentSession()
					.get("Actionintroduce", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Actionintroduce> findByExample(Actionintroduce instance) {
		log.debug("finding Actionintroduce instance by example");
		try {
			List<Actionintroduce> results = (List<Actionintroduce>) getCurrentSession()
					.createCriteria("Actionintroduce").add(create(instance))
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
		log.debug("finding Actionintroduce instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Actionintroduce as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Actionintroduce> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Actionintroduce> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Actionintroduce instances");
		try {
			String queryString = "from Actionintroduce";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Actionintroduce merge(Actionintroduce detachedInstance) {
		log.debug("merging Actionintroduce instance");
		try {
			Actionintroduce result = (Actionintroduce) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Actionintroduce instance) {
		log.debug("attaching dirty Actionintroduce instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Actionintroduce instance) {
		log.debug("attaching clean Actionintroduce instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ActionintroduceDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ActionintroduceDAO) ctx.getBean("ActionintroduceDAO");
	}
}