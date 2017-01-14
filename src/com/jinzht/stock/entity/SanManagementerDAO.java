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
 * SanManagementer entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.stock.entity.SanManagementer
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SanManagementerDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SanManagementerDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String POSITION = "position";

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

	public void save(SanManagementer transientInstance) {
		log.debug("saving SanManagementer instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SanManagementer persistentInstance) {
		log.debug("deleting SanManagementer instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SanManagementer findById(java.lang.Integer id) {
		log.debug("getting SanManagementer instance with id: " + id);
		try {
			SanManagementer instance = (SanManagementer) getCurrentSession()
					.get("com.jinzht.stock.entity.SanManagementer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SanManagementer> findByExample(SanManagementer instance) {
		log.debug("finding SanManagementer instance by example");
		try {
			List<SanManagementer> results = (List<SanManagementer>) getCurrentSession()
					.createCriteria("com.jinzht.stock.entity.SanManagementer")
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
		log.debug("finding SanManagementer instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SanManagementer as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SanManagementer> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<SanManagementer> findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List findAll() {
		log.debug("finding all SanManagementer instances");
		try {
			String queryString = "from SanManagementer";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SanManagementer merge(SanManagementer detachedInstance) {
		log.debug("merging SanManagementer instance");
		try {
			SanManagementer result = (SanManagementer) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SanManagementer instance) {
		log.debug("attaching dirty SanManagementer instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SanManagementer instance) {
		log.debug("attaching clean SanManagementer instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SanManagementerDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SanManagementerDAO) ctx.getBean("SanManagementerDAO");
	}
}