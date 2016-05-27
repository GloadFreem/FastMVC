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

import com.jinzht.web.entity.Rewardtradetype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Rewardtradetype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Rewardtradetype
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RewardtradetypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RewardtradetypeDAO.class);
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

	public void save(Rewardtradetype transientInstance) {
		log.debug("saving Rewardtradetype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Rewardtradetype persistentInstance) {
		log.debug("deleting Rewardtradetype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rewardtradetype findById(java.lang.Integer id) {
		log.debug("getting Rewardtradetype instance with id: " + id);
		try {
			Rewardtradetype instance = (Rewardtradetype) getCurrentSession()
					.get("com.jinzht.web.hibernate.Rewardtradetype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Rewardtradetype> findByExample(Rewardtradetype instance) {
		log.debug("finding Rewardtradetype instance by example");
		try {
			List<Rewardtradetype> results = (List<Rewardtradetype>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Rewardtradetype")
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
		log.debug("finding Rewardtradetype instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Rewardtradetype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Rewardtradetype> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Rewardtradetype instances");
		try {
			String queryString = "from Rewardtradetype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Rewardtradetype merge(Rewardtradetype detachedInstance) {
		log.debug("merging Rewardtradetype instance");
		try {
			Rewardtradetype result = (Rewardtradetype) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Rewardtradetype instance) {
		log.debug("attaching dirty Rewardtradetype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rewardtradetype instance) {
		log.debug("attaching clean Rewardtradetype instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RewardtradetypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RewardtradetypeDAO) ctx.getBean("RewardtradetypeDAO");
	}
}