package com.message.Enity;

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
 * Imsetting entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.message.Enity.Imsetting
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ImsettingDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ImsettingDAO.class);
	// property constants
	public static final String ACCESS_TOKEAN = "accessTokean";
	public static final String EXPIRES = "expires";

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

	public void save(Imsetting transientInstance) {
		log.debug("saving Imsetting instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Imsetting persistentInstance) {
		log.debug("deleting Imsetting instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Imsetting findById(java.lang.Integer id) {
		log.debug("getting Imsetting instance with id: " + id);
		try {
			Imsetting instance = (Imsetting) getCurrentSession().get(
					"com.message.Enity.Imsetting", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Imsetting> findByExample(Imsetting instance) {
		log.debug("finding Imsetting instance by example");
		try {
			List<Imsetting> results = (List<Imsetting>) getCurrentSession()
					.createCriteria("com.message.Enity.Imsetting")
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
		log.debug("finding Imsetting instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Imsetting as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Imsetting> findByAccessTokean(Object accessTokean) {
		return findByProperty(ACCESS_TOKEAN, accessTokean);
	}

	public List<Imsetting> findByExpires(Object expires) {
		return findByProperty(EXPIRES, expires);
	}

	public List findAll() {
		log.debug("finding all Imsetting instances");
		try {
			String queryString = "from Imsetting";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Imsetting merge(Imsetting detachedInstance) {
		log.debug("merging Imsetting instance");
		try {
			Imsetting result = (Imsetting) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Imsetting instance) {
		log.debug("attaching dirty Imsetting instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Imsetting instance) {
		log.debug("attaching clean Imsetting instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ImsettingDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ImsettingDAO) ctx.getBean("ImsettingDAO");
	}
}