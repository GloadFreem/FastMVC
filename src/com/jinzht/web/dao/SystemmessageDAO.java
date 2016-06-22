package com.jinzht.web.dao;

import java.util.Date;
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

import com.jinzht.tools.Config;
import com.jinzht.web.entity.Systemmessage;

/**
 * A data access object (DAO) providing persistence and search support for
 * Systemmessage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Systemmessage
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SystemmessageDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SystemmessageDAO.class);
	// property constants
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

	public void save(Systemmessage transientInstance) {
		log.debug("saving Systemmessage instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Systemmessage transientInstance) {
		log.debug("saving or updating Systemmessage instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Systemmessage persistentInstance) {
		log.debug("deleting Systemmessage instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Systemmessage findById(java.lang.Integer id) {
		log.debug("getting Systemmessage instance with id: " + id);
		try {
			Systemmessage instance = (Systemmessage) getCurrentSession().get(
					"com.jinzht.web.entity.Systemmessage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Systemmessage> findByExample(Systemmessage instance) {
		log.debug("finding Systemmessage instance by example");
		try {
			List<Systemmessage> results = (List<Systemmessage>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Systemmessage")
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
		log.debug("finding Systemmessage instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Systemmessage as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByPropertyWithPage(String propertyName, Object value,Integer page) {
		log.debug("finding Systemmessage instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Systemmessage as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setFirstResult(page*Config.STRING_INVESTOR_LIST_MAX_SIZE);
			queryObject.setMaxResults(Config.STRING_INVESTOR_LIST_MAX_SIZE);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Systemmessage> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Systemmessage instances");
		try {
			String queryString = "from Systemmessage";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Systemmessage merge(Systemmessage detachedInstance) {
		log.debug("merging Systemmessage instance");
		try {
			Systemmessage result = (Systemmessage) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Systemmessage instance) {
		log.debug("attaching dirty Systemmessage instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Systemmessage instance) {
		log.debug("attaching clean Systemmessage instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SystemmessageDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SystemmessageDAO) ctx.getBean("SystemmessageDAO");
	}
}