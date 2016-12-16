package com.jinzht.web.entity;

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

/**
 * A data access object (DAO) providing persistence and search support for
 * Propertycharges entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Propertycharges
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class PropertychargesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PropertychargesDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PRICE = "price";
	public static final String STATUS = "status";

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

	public void save(Propertycharges transientInstance) {
		log.debug("saving Propertycharges instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Propertycharges persistentInstance) {
		log.debug("deleting Propertycharges instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Propertycharges findById(java.lang.Integer id) {
		log.debug("getting Propertycharges instance with id: " + id);
		try {
			Propertycharges instance = (Propertycharges) getCurrentSession()
					.get("com.jinzht.web.entity.Propertycharges", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Propertycharges> findByExample(Propertycharges instance) {
		log.debug("finding Propertycharges instance by example");
		try {
			List<Propertycharges> results = (List<Propertycharges>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.Propertycharges")
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
		log.debug("finding Propertycharges instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Propertycharges as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Propertycharges> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Propertycharges> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List<Propertycharges> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all Propertycharges instances");
		try {
			String queryString = "from Propertycharges";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Propertycharges merge(Propertycharges detachedInstance) {
		log.debug("merging Propertycharges instance");
		try {
			Propertycharges result = (Propertycharges) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Propertycharges instance) {
		log.debug("attaching dirty Propertycharges instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Propertycharges instance) {
		log.debug("attaching clean Propertycharges instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PropertychargesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PropertychargesDAO) ctx.getBean("PropertychargesDAO");
	}
}