package com.jinzht.web.entity;

import java.sql.Timestamp;
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
 * BusinessOrder entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusinessOrder
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusinessOrderDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessOrderDAO.class);
	// property constants
	public static final String ORDER_CODE = "orderCode";
	public static final String TOTAL_FEE = "totalFee";

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

	public void save(BusinessOrder transientInstance) {
		log.debug("saving BusinessOrder instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(BusinessOrder transientInstance) {
		log.debug("saving BusinessOrder instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessOrder persistentInstance) {
		log.debug("deleting BusinessOrder instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessOrder findById(java.lang.Integer id) {
		log.debug("getting BusinessOrder instance with id: " + id);
		try {
			BusinessOrder instance = (BusinessOrder) getCurrentSession().get(
					"com.jinzht.web.entity.BusinessOrder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusinessOrder> findByExample(BusinessOrder instance) {
		log.debug("finding BusinessOrder instance by example");
		try {
			List<BusinessOrder> results = (List<BusinessOrder>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.BusinessOrder")
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
		log.debug("finding BusinessOrder instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessOrder as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BusinessOrder> findByOrderCode(Object orderCode) {
		return findByProperty(ORDER_CODE, orderCode);
	}

	public List<BusinessOrder> findByTotalFee(Object totalFee) {
		return findByProperty(TOTAL_FEE, totalFee);
	}

	public List findAll() {
		log.debug("finding all BusinessOrder instances");
		try {
			String queryString = "from BusinessOrder";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusinessOrder merge(BusinessOrder detachedInstance) {
		log.debug("merging BusinessOrder instance");
		try {
			BusinessOrder result = (BusinessOrder) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessOrder instance) {
		log.debug("attaching dirty BusinessOrder instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessOrder instance) {
		log.debug("attaching clean BusinessOrder instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessOrderDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessOrderDAO) ctx.getBean("BusinessOrderDAO");
	}
}