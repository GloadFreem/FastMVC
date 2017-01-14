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
 * SanShareholder entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.stock.entity.SanShareholder
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SanShareholderDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SanShareholderDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String STOCK = "stock";
	public static final String PERCENT = "percent";

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

	public void save(SanShareholder transientInstance) {
		log.debug("saving SanShareholder instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SanShareholder persistentInstance) {
		log.debug("deleting SanShareholder instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SanShareholder findById(java.lang.Integer id) {
		log.debug("getting SanShareholder instance with id: " + id);
		try {
			SanShareholder instance = (SanShareholder) getCurrentSession().get(
					"com.jinzht.stock.entity.SanShareholder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SanShareholder> findByExample(SanShareholder instance) {
		log.debug("finding SanShareholder instance by example");
		try {
			List<SanShareholder> results = (List<SanShareholder>) getCurrentSession()
					.createCriteria("com.jinzht.stock.entity.SanShareholder")
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
		log.debug("finding SanShareholder instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SanShareholder as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SanShareholder> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<SanShareholder> findByStock(Object stock) {
		return findByProperty(STOCK, stock);
	}

	public List<SanShareholder> findByPercent(Object percent) {
		return findByProperty(PERCENT, percent);
	}

	public List findAll() {
		log.debug("finding all SanShareholder instances");
		try {
			String queryString = "from SanShareholder";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SanShareholder merge(SanShareholder detachedInstance) {
		log.debug("merging SanShareholder instance");
		try {
			SanShareholder result = (SanShareholder) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SanShareholder instance) {
		log.debug("attaching dirty SanShareholder instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SanShareholder instance) {
		log.debug("attaching clean SanShareholder instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SanShareholderDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SanShareholderDAO) ctx.getBean("SanShareholderDAO");
	}
}