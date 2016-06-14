package com.jinzht.web.dao;
// default package

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

import com.jinzht.tools.Config;
import com.jinzht.web.entity.Foundation;

/**
 * A data access object (DAO) providing persistence and search support for
 * Foundation entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Foundation
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FoundationDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FoundationDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String IMAGE = "image";
	public static final String CONTENT = "content";
	public static final String URL = "url";

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

	public void save(Foundation transientInstance) {
		log.debug("saving Foundation instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Foundation persistentInstance) {
		log.debug("deleting Foundation instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Foundation findById(java.lang.Integer id) {
		log.debug("getting Foundation instance with id: " + id);
		try {
			Foundation instance = (Foundation) getCurrentSession().get(
					"Foundation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Foundation> findByExample(Foundation instance) {
		log.debug("finding Foundation instance by example");
		try {
			List<Foundation> results = (List<Foundation>) getCurrentSession()
					.createCriteria("Foundation").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Foundation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Foundation as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Foundation> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Foundation> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List<Foundation> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Foundation> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all Foundation instances");
		try {
			String queryString = "from Foundation";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findDefault() {
		log.debug("finding default Foundation instances");
		try {
			String queryString = "from Foundation";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(0);
			queryObject.setMaxResults(Config.STRING_FOUNDATION_LIST_MAX_SIZE);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Foundation merge(Foundation detachedInstance) {
		log.debug("merging Foundation instance");
		try {
			Foundation result = (Foundation) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Foundation instance) {
		log.debug("attaching dirty Foundation instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Foundation instance) {
		log.debug("attaching clean Foundation instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FoundationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FoundationDAO) ctx.getBean("FoundationDAO");
	}
}