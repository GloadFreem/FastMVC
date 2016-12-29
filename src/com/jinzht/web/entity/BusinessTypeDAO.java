package com.jinzht.web.entity;

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

/**
 * A data access object (DAO) providing persistence and search support for
 * BusinessType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusinessType
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusinessTypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessTypeDAO.class);
	// property constants
	public static final String TNAME = "tname";

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

	public void save(BusinessType transientInstance) {
		log.debug("saving BusinessType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessType persistentInstance) {
		log.debug("deleting BusinessType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessType findById(java.lang.Integer id) {
		log.debug("getting BusinessType instance with id: " + id);
		try {
			BusinessType instance = (BusinessType) getCurrentSession().get(
					"com.jinzht.web.entity.BusinessType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusinessType> findByExample(BusinessType instance) {
		log.debug("finding BusinessType instance by example");
		try {
			List<BusinessType> results = (List<BusinessType>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.BusinessType")
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
		log.debug("finding BusinessType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BusinessType> findByTname(Object tname) {
		return findByProperty(TNAME, tname);
	}

	public List findAll() {
		log.debug("finding all BusinessType instances");
		try {
			String queryString = "from BusinessType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusinessType merge(BusinessType detachedInstance) {
		log.debug("merging BusinessType instance");
		try {
			BusinessType result = (BusinessType) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessType instance) {
		log.debug("attaching dirty BusinessType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessType instance) {
		log.debug("attaching clean BusinessType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessTypeDAO) ctx.getBean("BusinessTypeDAO");
	}
}