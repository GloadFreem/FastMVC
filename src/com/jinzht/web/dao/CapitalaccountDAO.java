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

import com.jinzht.web.entity.Capitalaccount;

/**
 * A data access object (DAO) providing persistence and search support for
 * Capitalaccount entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Capitalaccount
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CapitalaccountDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CapitalaccountDAO.class);
	// property constants
	public static final String MOUNT = "mount";
	public static final String USABLE_MONEY = "usableMoney";
	public static final String UNUSABLE_MONEY = "unusableMoney";

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

	public void save(Capitalaccount transientInstance) {
		log.debug("saving Capitalaccount instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Capitalaccount persistentInstance) {
		log.debug("deleting Capitalaccount instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Capitalaccount findById(java.lang.Integer id) {
		log.debug("getting Capitalaccount instance with id: " + id);
		try {
			Capitalaccount instance = (Capitalaccount) getCurrentSession().get(
					"com.jinzht.web.hibernate.Capitalaccount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Capitalaccount> findByExample(Capitalaccount instance) {
		log.debug("finding Capitalaccount instance by example");
		try {
			List<Capitalaccount> results = (List<Capitalaccount>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Capitalaccount")
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
		log.debug("finding Capitalaccount instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Capitalaccount as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Capitalaccount> findByMount(Object mount) {
		return findByProperty(MOUNT, mount);
	}

	public List<Capitalaccount> findByUsableMoney(Object usableMoney) {
		return findByProperty(USABLE_MONEY, usableMoney);
	}

	public List<Capitalaccount> findByUnusableMoney(Object unusableMoney) {
		return findByProperty(UNUSABLE_MONEY, unusableMoney);
	}

	public List findAll() {
		log.debug("finding all Capitalaccount instances");
		try {
			String queryString = "from Capitalaccount";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Capitalaccount merge(Capitalaccount detachedInstance) {
		log.debug("merging Capitalaccount instance");
		try {
			Capitalaccount result = (Capitalaccount) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Capitalaccount instance) {
		log.debug("attaching dirty Capitalaccount instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Capitalaccount instance) {
		log.debug("attaching clean Capitalaccount instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CapitalaccountDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CapitalaccountDAO) ctx.getBean("CapitalaccountDAO");
	}
}