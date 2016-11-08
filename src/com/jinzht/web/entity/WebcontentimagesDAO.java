package com.jinzht.web.entity;

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
 * Webcontentimages entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Webcontentimages
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class WebcontentimagesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(WebcontentimagesDAO.class);
	// property constants
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

	public void save(Webcontentimages transientInstance) {
		log.debug("saving Webcontentimages instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Webcontentimages persistentInstance) {
		log.debug("deleting Webcontentimages instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Webcontentimages findById(java.lang.Integer id) {
		log.debug("getting Webcontentimages instance with id: " + id);
		try {
			Webcontentimages instance = (Webcontentimages) getCurrentSession()
					.get("com.jinzht.web.entity.Webcontentimages", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Webcontentimages> findByExample(Webcontentimages instance) {
		log.debug("finding Webcontentimages instance by example");
		try {
			List<Webcontentimages> results = (List<Webcontentimages>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.Webcontentimages")
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
		log.debug("finding Webcontentimages instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Webcontentimages as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Webcontentimages> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all Webcontentimages instances");
		try {
			String queryString = "from Webcontentimages";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Webcontentimages merge(Webcontentimages detachedInstance) {
		log.debug("merging Webcontentimages instance");
		try {
			Webcontentimages result = (Webcontentimages) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Webcontentimages instance) {
		log.debug("attaching dirty Webcontentimages instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Webcontentimages instance) {
		log.debug("attaching clean Webcontentimages instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static WebcontentimagesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (WebcontentimagesDAO) ctx.getBean("WebcontentimagesDAO");
	}
}