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
 * SanLiabilities entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.stock.entity.SanLiabilities
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SanLiabilitiesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SanLiabilitiesDAO.class);
	// property constants
	public static final String LIABILITIES = "liabilities";
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

	public void save(SanLiabilities transientInstance) {
		log.debug("saving SanLiabilities instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SanLiabilities persistentInstance) {
		log.debug("deleting SanLiabilities instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SanLiabilities findById(java.lang.Integer id) {
		log.debug("getting SanLiabilities instance with id: " + id);
		try {
			SanLiabilities instance = (SanLiabilities) getCurrentSession().get(
					"com.jinzht.stock.entity.SanLiabilities", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SanLiabilities> findByExample(SanLiabilities instance) {
		log.debug("finding SanLiabilities instance by example");
		try {
			List<SanLiabilities> results = (List<SanLiabilities>) getCurrentSession()
					.createCriteria("com.jinzht.stock.entity.SanLiabilities")
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
		log.debug("finding SanLiabilities instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SanLiabilities as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SanLiabilities> findByLiabilities(Object liabilities) {
		return findByProperty(LIABILITIES, liabilities);
	}

	public List<SanLiabilities> findByYear(Object year) {
		return findByProperty(YEAR, year);
	}

	public List findAll() {
		log.debug("finding all SanLiabilities instances");
		try {
			String queryString = "from SanLiabilities";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SanLiabilities merge(SanLiabilities detachedInstance) {
		log.debug("merging SanLiabilities instance");
		try {
			SanLiabilities result = (SanLiabilities) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SanLiabilities instance) {
		log.debug("attaching dirty SanLiabilities instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SanLiabilities instance) {
		log.debug("attaching clean SanLiabilities instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SanLiabilitiesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SanLiabilitiesDAO) ctx.getBean("SanLiabilitiesDAO");
	}
}