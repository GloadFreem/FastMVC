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

import com.jinzht.web.entity.Financingexit;

/**
 * A data access object (DAO) providing persistence and search support for
 * Financingexit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Financingexit
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FinancingexitDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FinancingexitDAO.class);
	// property constants
	public static final String URL = "url";
	public static final String PROJECT_ID = "projectId";
	public static final String CONTEXT = "context";

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

	public void save(Financingexit transientInstance) {
		log.debug("saving Financingexit instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Financingexit transientInstance) {
		log.debug("saving Financingexit instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Financingexit persistentInstance) {
		log.debug("deleting Financingexit instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Financingexit findById(java.lang.Integer id) {
		log.debug("getting Financingexit instance with id: " + id);
		try {
			Financingexit instance = (Financingexit) getCurrentSession().get(
					"com.jinzht.web.entity.Financingexit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Financingexit> findByExample(Financingexit instance) {
		log.debug("finding Financingexit instance by example");
		try {
			List<Financingexit> results = (List<Financingexit>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Financingexit")
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
		log.debug("finding Financingexit instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Financingexit as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Financingexit> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<Financingexit> findByProjectId(Object projectId) {
		return findByProperty(PROJECT_ID, projectId);
	}

	public List<Financingexit> findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

	public List findAll() {
		log.debug("finding all Financingexit instances");
		try {
			String queryString = "from Financingexit";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Financingexit merge(Financingexit detachedInstance) {
		log.debug("merging Financingexit instance");
		try {
			Financingexit result = (Financingexit) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Financingexit instance) {
		log.debug("attaching dirty Financingexit instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Financingexit instance) {
		log.debug("attaching clean Financingexit instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FinancingexitDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FinancingexitDAO) ctx.getBean("FinancingexitDAO");
	}
}