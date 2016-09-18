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

import com.jinzht.web.entity.Financialstanding;

/**
 * A data access object (DAO) providing persistence and search support for
 * Financialstanding entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Financialstanding
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FinancialstandingDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FinancialstandingDAO.class);
	// property constants
	public static final String PROJECT_ID = "projectId";
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

	public void save(Financialstanding transientInstance) {
		log.debug("saving Financialstanding instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Financialstanding transientInstance) {
		log.debug("saving Financialstanding instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Financialstanding persistentInstance) {
		log.debug("deleting Financialstanding instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Financialstanding findById(java.lang.Integer id) {
		log.debug("getting Financialstanding instance with id: " + id);
		try {
			Financialstanding instance = (Financialstanding) getCurrentSession()
					.get("com.jinzht.web.entity.Financialstanding", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Financialstanding> findByExample(Financialstanding instance) {
		log.debug("finding Financialstanding instance by example");
		try {
			List<Financialstanding> results = (List<Financialstanding>) getCurrentSession()
					.createCriteria(
							"com.jinzht.web.hibernate.Financialstanding")
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
		log.debug("finding Financialstanding instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Financialstanding as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Financialstanding> findByProjectId(Object projectId) {
		return findByProperty(PROJECT_ID, projectId);
	}

	public List<Financialstanding> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<Financialstanding> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Financialstanding instances");
		try {
			String queryString = "from Financialstanding";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Financialstanding merge(Financialstanding detachedInstance) {
		log.debug("merging Financialstanding instance");
		try {
			Financialstanding result = (Financialstanding) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Financialstanding instance) {
		log.debug("attaching dirty Financialstanding instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Financialstanding instance) {
		log.debug("attaching clean Financialstanding instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FinancialstandingDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FinancialstandingDAO) ctx.getBean("FinancialstandingDAO");
	}
}