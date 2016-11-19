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
 * Newsbanner entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.message.Enity.Newsbanner
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class NewsbannerDAO {
	private static final Logger log = LoggerFactory
			.getLogger(NewsbannerDAO.class);
	// property constants
	public static final String IMAGE = "image";
	public static final String URL = "url";
	public static final String DESC = "desc";

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

	public void save(Newsbanner transientInstance) {
		log.debug("saving Newsbanner instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Newsbanner persistentInstance) {
		log.debug("deleting Newsbanner instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Newsbanner findById(java.lang.Integer id) {
		log.debug("getting Newsbanner instance with id: " + id);
		try {
			Newsbanner instance = (Newsbanner) getCurrentSession().get(
					"com.message.Enity.Newsbanner", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Newsbanner> findByExample(Newsbanner instance) {
		log.debug("finding Newsbanner instance by example");
		try {
			List<Newsbanner> results = (List<Newsbanner>) getCurrentSession()
					.createCriteria("com.message.Enity.Newsbanner")
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
		log.debug("finding Newsbanner instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Newsbanner as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Newsbanner> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List<Newsbanner> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<Newsbanner> findByDesc(Object desc) {
		return findByProperty(DESC, desc);
	}

	public List findAll() {
		log.debug("finding all Newsbanner instances");
		try {
			String queryString = "from Newsbanner";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Newsbanner merge(Newsbanner detachedInstance) {
		log.debug("merging Newsbanner instance");
		try {
			Newsbanner result = (Newsbanner) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Newsbanner instance) {
		log.debug("attaching dirty Newsbanner instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Newsbanner instance) {
		log.debug("attaching clean Newsbanner instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static NewsbannerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (NewsbannerDAO) ctx.getBean("NewsbannerDAO");
	}
}