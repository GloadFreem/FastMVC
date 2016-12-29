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
 * BusinessContentType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusinessContentType
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusinessContentTypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessContentTypeDAO.class);
	// property constants
	public static final String CNAME = "cname";

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

	public void save(BusinessContentType transientInstance) {
		log.debug("saving BusinessContentType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessContentType persistentInstance) {
		log.debug("deleting BusinessContentType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessContentType findById(java.lang.Integer id) {
		log.debug("getting BusinessContentType instance with id: " + id);
		try {
			BusinessContentType instance = (BusinessContentType) getCurrentSession()
					.get("com.jinzht.web.entity.BusinessContentType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusinessContentType> findByExample(BusinessContentType instance) {
		log.debug("finding BusinessContentType instance by example");
		try {
			List<BusinessContentType> results = (List<BusinessContentType>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.BusinessContentType")
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
		log.debug("finding BusinessContentType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessContentType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BusinessContentType> findByCname(Object cname) {
		return findByProperty(CNAME, cname);
	}

	public List findAll() {
		log.debug("finding all BusinessContentType instances");
		try {
			String queryString = "from BusinessContentType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusinessContentType merge(BusinessContentType detachedInstance) {
		log.debug("merging BusinessContentType instance");
		try {
			BusinessContentType result = (BusinessContentType) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessContentType instance) {
		log.debug("attaching dirty BusinessContentType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessContentType instance) {
		log.debug("attaching clean BusinessContentType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessContentTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessContentTypeDAO) ctx.getBean("BusinessContentTypeDAO");
	}
}