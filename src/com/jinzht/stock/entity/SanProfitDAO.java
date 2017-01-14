package com.jinzht.stock.entity;

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

/**
 * A data access object (DAO) providing persistence and search support for
 * SanProfit entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.stock.entity.SanProfit
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SanProfitDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SanProfitDAO.class);
	// property constants
	public static final String PROFIT = "profit";
	public static final String YEAR = "year";

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

	public void save(SanProfit transientInstance) {
		log.debug("saving SanProfit instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SanProfit persistentInstance) {
		log.debug("deleting SanProfit instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SanProfit findById(java.lang.Integer id) {
		log.debug("getting SanProfit instance with id: " + id);
		try {
			SanProfit instance = (SanProfit) getCurrentSession().get(
					"com.jinzht.stock.entity.SanProfit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SanProfit> findByExample(SanProfit instance) {
		log.debug("finding SanProfit instance by example");
		try {
			List<SanProfit> results = (List<SanProfit>) getCurrentSession()
					.createCriteria("com.jinzht.stock.entity.SanProfit")
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
		log.debug("finding SanProfit instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SanProfit as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SanProfit> findByProfit(Object profit) {
		return findByProperty(PROFIT, profit);
	}

	public List<SanProfit> findByYear(Object year) {
		return findByProperty(YEAR, year);
	}

	public List findAll() {
		log.debug("finding all SanProfit instances");
		try {
			String queryString = "from SanProfit";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SanProfit merge(SanProfit detachedInstance) {
		log.debug("merging SanProfit instance");
		try {
			SanProfit result = (SanProfit) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SanProfit instance) {
		log.debug("attaching dirty SanProfit instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SanProfit instance) {
		log.debug("attaching clean SanProfit instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SanProfitDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SanProfitDAO) ctx.getBean("SanProfitDAO");
	}
}