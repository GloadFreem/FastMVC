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
 * BusinessPayType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusinessPayType
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusinessPayTypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessPayTypeDAO.class);
	// property constants
	public static final String NAME = "name";

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

	public void save(BusinessPayType transientInstance) {
		log.debug("saving BusinessPayType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessPayType persistentInstance) {
		log.debug("deleting BusinessPayType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessPayType findById(java.lang.Integer id) {
		log.debug("getting BusinessPayType instance with id: " + id);
		try {
			BusinessPayType instance = (BusinessPayType) getCurrentSession()
					.get("com.jinzht.web.entity.BusinessPayType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusinessPayType> findByExample(BusinessPayType instance) {
		log.debug("finding BusinessPayType instance by example");
		try {
			List<BusinessPayType> results = (List<BusinessPayType>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.BusinessPayType")
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
		log.debug("finding BusinessPayType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessPayType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BusinessPayType> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all BusinessPayType instances");
		try {
			String queryString = "from BusinessPayType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusinessPayType merge(BusinessPayType detachedInstance) {
		log.debug("merging BusinessPayType instance");
		try {
			BusinessPayType result = (BusinessPayType) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessPayType instance) {
		log.debug("attaching dirty BusinessPayType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessPayType instance) {
		log.debug("attaching clean BusinessPayType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessPayTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessPayTypeDAO) ctx.getBean("BusinessPayTypeDAO");
	}
}