package com.message.Enity;

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
 * OriginalDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.message.Enity.OriginalDetail
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class OriginalDetailDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OriginalDetailDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String SOURCE = "source";
	public static final String SOURCE_URL = "sourceUrl";

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

	public void save(OriginalDetail transientInstance) {
		log.debug("saving OriginalDetail instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OriginalDetail persistentInstance) {
		log.debug("deleting OriginalDetail instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OriginalDetail findById(java.lang.Integer id) {
		log.debug("getting OriginalDetail instance with id: " + id);
		try {
			OriginalDetail instance = (OriginalDetail) getCurrentSession().get(
					"com.message.Enity.OriginalDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<OriginalDetail> findByExample(OriginalDetail instance) {
		log.debug("finding OriginalDetail instance by example");
		try {
			List<OriginalDetail> results = (List<OriginalDetail>) getCurrentSession()
					.createCriteria("com.message.Enity.OriginalDetail")
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
		log.debug("finding OriginalDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OriginalDetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<OriginalDetail> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<OriginalDetail> findBySource(Object source) {
		return findByProperty(SOURCE, source);
	}

	public List<OriginalDetail> findBySourceUrl(Object sourceUrl) {
		return findByProperty(SOURCE_URL, sourceUrl);
	}

	public List findAll() {
		log.debug("finding all OriginalDetail instances");
		try {
			String queryString = "from OriginalDetail";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OriginalDetail merge(OriginalDetail detachedInstance) {
		log.debug("merging OriginalDetail instance");
		try {
			OriginalDetail result = (OriginalDetail) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OriginalDetail instance) {
		log.debug("attaching dirty OriginalDetail instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OriginalDetail instance) {
		log.debug("attaching clean OriginalDetail instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OriginalDetailDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OriginalDetailDAO) ctx.getBean("OriginalDetailDAO");
	}
}