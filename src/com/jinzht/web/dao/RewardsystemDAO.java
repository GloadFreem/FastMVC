package com.jinzht.web.dao;

import java.util.List;
import java.util.Map;
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

import com.jinzht.tools.Config;
import com.jinzht.web.entity.Rewardsystem;

/**
 * A data access object (DAO) providing persistence and search support for
 * Rewardsystem entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Rewardsystem
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RewardsystemDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RewardsystemDAO.class);
	// property constants
	public static final String COUNT = "count";

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

	public void save(Rewardsystem transientInstance) {
		log.debug("saving Rewardsystem instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Rewardsystem persistentInstance) {
		log.debug("deleting Rewardsystem instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rewardsystem findById(java.lang.Integer id) {
		log.debug("getting Rewardsystem instance with id: " + id);
		try {
			Rewardsystem instance = (Rewardsystem) getCurrentSession().get(
					"com.jinzht.web.hibernate.Rewardsystem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Rewardsystem> findByExample(Rewardsystem instance) {
		log.debug("finding Rewardsystem instance by example");
		try {
			List<Rewardsystem> results = (List<Rewardsystem>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Rewardsystem")
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
		log.debug("finding Rewardsystem instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Rewardsystem as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPropertiesWithPage(Map requestMap, Integer page) {
		try {
			// String debugInfo=
			// "finding Loginfailrecord instance with property: ";
			String queryString = "from Rewardsystem as model where";
			Object[] keys = requestMap.keySet().toArray();
			for (int i = 0; i < requestMap.size(); i++) {
				// debugInfo += keys[i].toString()+requestMap.get(keys[i]);
				if (i == 0) {
					queryString += " model." + keys[i].toString() + " =? ";
				} else {
					queryString += "and model." + keys[i].toString() + " =? ";
				}
			}
			// log.debug(debugInfo);

			Query queryObject = getCurrentSession().createQuery(queryString);
			for (int i = 0; i < requestMap.size(); i++) {
				queryObject.setParameter(i, requestMap.get(keys[i]));
			}
			queryObject.setFirstResult(page*Config.STRING_INVESTOR_LIST_MAX_SIZE);
			queryObject.setMaxResults(Config.STRING_INVESTOR_LIST_MAX_SIZE);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Rewardsystem> findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findAll() {
		log.debug("finding all Rewardsystem instances");
		try {
			String queryString = "from Rewardsystem";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Rewardsystem merge(Rewardsystem detachedInstance) {
		log.debug("merging Rewardsystem instance");
		try {
			Rewardsystem result = (Rewardsystem) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Rewardsystem instance) {
		log.debug("attaching dirty Rewardsystem instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rewardsystem instance) {
		log.debug("attaching clean Rewardsystem instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RewardsystemDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RewardsystemDAO) ctx.getBean("RewardsystemDAO");
	}
}