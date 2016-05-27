package com.jinzht.web.dao;

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

import com.jinzht.web.entity.Contentprise;

/**
 * A data access object (DAO) providing persistence and search support for
 * Contentprise entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Contentprise
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ContentpriseDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ContentpriseDAO.class);
	// property constants

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

	public void save(Contentprise transientInstance) {
		log.debug("saving Contentprise instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Contentprise persistentInstance) {
		log.debug("deleting Contentprise instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Contentprise findById(java.lang.Integer id) {
		log.debug("getting Contentprise instance with id: " + id);
		try {
			Contentprise instance = (Contentprise) getCurrentSession().get(
					"com.jinzht.web.hibernate.Contentprise", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Contentprise> findByExample(Contentprise instance) {
		log.debug("finding Contentprise instance by example");
		try {
			List<Contentprise> results = (List<Contentprise>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Contentprise")
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
		log.debug("finding Contentprise instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Contentprise as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Contentprise instances");
		try {
			String queryString = "from Contentprise";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Contentprise merge(Contentprise detachedInstance) {
		log.debug("merging Contentprise instance");
		try {
			Contentprise result = (Contentprise) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Contentprise instance) {
		log.debug("attaching dirty Contentprise instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Contentprise instance) {
		log.debug("attaching clean Contentprise instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ContentpriseDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ContentpriseDAO) ctx.getBean("ContentpriseDAO");
	}
}