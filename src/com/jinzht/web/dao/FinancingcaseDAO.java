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

import com.jinzht.web.entity.Financingcase;

/**
 * A data access object (DAO) providing persistence and search support for
 * Financingcase entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Financingcase
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FinancingcaseDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FinancingcaseDAO.class);
	// property constants
	public static final String URL = "url";
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

	public void save(Financingcase transientInstance) {
		log.debug("saving Financingcase instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Financingcase persistentInstance) {
		log.debug("deleting Financingcase instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Financingcase findById(java.lang.Integer id) {
		log.debug("getting Financingcase instance with id: " + id);
		try {
			Financingcase instance = (Financingcase) getCurrentSession().get(
					"com.jinzht.web.entity.Financingcase", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Financingcase> findByExample(Financingcase instance) {
		log.debug("finding Financingcase instance by example");
		try {
			List<Financingcase> results = (List<Financingcase>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.Financingcase")
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
		log.debug("finding Financingcase instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Financingcase as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Financingcase> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<Financingcase> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Financingcase instances");
		try {
			String queryString = "from Financingcase";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Financingcase merge(Financingcase detachedInstance) {
		log.debug("merging Financingcase instance");
		try {
			Financingcase result = (Financingcase) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Financingcase instance) {
		log.debug("attaching dirty Financingcase instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Financingcase instance) {
		log.debug("attaching clean Financingcase instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FinancingcaseDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FinancingcaseDAO) ctx.getBean("FinancingcaseDAO");
	}
}