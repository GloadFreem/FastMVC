package com.jinzht.web.dao;

import java.sql.Timestamp;
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

import com.jinzht.web.entity.Roadshowplan;

/**
 * A data access object (DAO) providing persistence and search support for
 * Roadshowplan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Roadshowplan
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RoadshowplanDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RoadshowplanDAO.class);
	// property constants
	public static final String FINANCE_TOTAL = "financeTotal";
	public static final String FINANCED_MOUNT = "financedMount";

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

	public void save(Roadshowplan transientInstance) {
		log.debug("saving Roadshowplan instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roadshowplan persistentInstance) {
		log.debug("deleting Roadshowplan instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roadshowplan findById(java.lang.Integer id) {
		log.debug("getting Roadshowplan instance with id: " + id);
		try {
			Roadshowplan instance = (Roadshowplan) getCurrentSession().get(
					"com.jinzht.web.hibernate.Roadshowplan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Roadshowplan> findByExample(Roadshowplan instance) {
		log.debug("finding Roadshowplan instance by example");
		try {
			List<Roadshowplan> results = (List<Roadshowplan>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Roadshowplan")
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
		log.debug("finding Roadshowplan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Roadshowplan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Roadshowplan> findByFinanceTotal(Object financeTotal) {
		return findByProperty(FINANCE_TOTAL, financeTotal);
	}

	public List<Roadshowplan> findByFinancedMount(Object financedMount) {
		return findByProperty(FINANCED_MOUNT, financedMount);
	}

	public List findAll() {
		log.debug("finding all Roadshowplan instances");
		try {
			String queryString = "from Roadshowplan";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roadshowplan merge(Roadshowplan detachedInstance) {
		log.debug("merging Roadshowplan instance");
		try {
			Roadshowplan result = (Roadshowplan) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Roadshowplan instance) {
		log.debug("attaching dirty Roadshowplan instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roadshowplan instance) {
		log.debug("attaching clean Roadshowplan instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RoadshowplanDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RoadshowplanDAO) ctx.getBean("RoadshowplanDAO");
	}
}