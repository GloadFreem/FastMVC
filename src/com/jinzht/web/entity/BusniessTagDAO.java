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
 * BusniessTag entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusniessTag
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusniessTagDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusniessTagDAO.class);
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

	public void save(BusniessTag transientInstance) {
		log.debug("saving BusniessTag instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusniessTag persistentInstance) {
		log.debug("deleting BusniessTag instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusniessTag findById(java.lang.Integer id) {
		log.debug("getting BusniessTag instance with id: " + id);
		try {
			BusniessTag instance = (BusniessTag) getCurrentSession().get(
					"com.jinzht.web.entity.BusniessTag", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusniessTag> findByExample(BusniessTag instance) {
		log.debug("finding BusniessTag instance by example");
		try {
			List<BusniessTag> results = (List<BusniessTag>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.BusniessTag")
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
		log.debug("finding BusniessTag instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BusniessTag as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BusniessTag> findByTname(Object tname) {
		return findByProperty(TNAME, tname);
	}

	public List findAll() {
		log.debug("finding all BusniessTag instances");
		try {
			String queryString = "from BusniessTag";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusniessTag merge(BusniessTag detachedInstance) {
		log.debug("merging BusniessTag instance");
		try {
			BusniessTag result = (BusniessTag) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusniessTag instance) {
		log.debug("attaching dirty BusniessTag instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusniessTag instance) {
		log.debug("attaching clean BusniessTag instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusniessTagDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusniessTagDAO) ctx.getBean("BusniessTagDAO");
	}
}