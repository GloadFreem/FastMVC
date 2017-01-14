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
 * SanIncome entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.stock.entity.SanIncome
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SanIncomeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SanIncomeDAO.class);
	// property constants
	public static final String INCOME = "income";
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

	public void save(SanIncome transientInstance) {
		log.debug("saving SanIncome instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SanIncome persistentInstance) {
		log.debug("deleting SanIncome instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SanIncome findById(java.lang.Integer id) {
		log.debug("getting SanIncome instance with id: " + id);
		try {
			SanIncome instance = (SanIncome) getCurrentSession().get(
					"com.jinzht.stock.entity.SanIncome", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SanIncome> findByExample(SanIncome instance) {
		log.debug("finding SanIncome instance by example");
		try {
			List<SanIncome> results = (List<SanIncome>) getCurrentSession()
					.createCriteria("com.jinzht.stock.entity.SanIncome")
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
		log.debug("finding SanIncome instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SanIncome as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SanIncome> findByIncome(Object income) {
		return findByProperty(INCOME, income);
	}

	public List<SanIncome> findByYear(Object year) {
		return findByProperty(YEAR, year);
	}

	public List findAll() {
		log.debug("finding all SanIncome instances");
		try {
			String queryString = "from SanIncome";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SanIncome merge(SanIncome detachedInstance) {
		log.debug("merging SanIncome instance");
		try {
			SanIncome result = (SanIncome) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SanIncome instance) {
		log.debug("attaching dirty SanIncome instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SanIncome instance) {
		log.debug("attaching clean SanIncome instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SanIncomeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SanIncomeDAO) ctx.getBean("SanIncomeDAO");
	}
}