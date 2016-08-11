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

import com.jinzht.web.entity.Banner;

/**
 * A data access object (DAO) providing persistence and search support for
 * Banner entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Banner
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BannerDAO {
	private static final Logger log = LoggerFactory.getLogger(BannerDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String IMAGE = "image";
	public static final String BANNER_TYPE = "bannerType";
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

	public void save(Banner transientInstance) {
		log.debug("saving Banner instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Banner transientInstance) {
		log.debug("saving Banner instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Banner persistentInstance) {
		log.debug("deleting Banner instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Banner findById(java.lang.Integer id) {
		log.debug("getting Banner instance with id: " + id);
		try {
			Banner instance = (Banner) getCurrentSession().get(
					"com.jinzht.web.entity.Banner", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Banner> findByExample(Banner instance) {
		log.debug("finding Banner instance by example");
		try {
			List<Banner> results = (List<Banner>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Banner")
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
		log.debug("finding Banner instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Banner as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Banner> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Banner> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Banner> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List<Banner> findByBannerType(Object bannerType) {
		return findByProperty(BANNER_TYPE, bannerType);
	}

	public List<Banner> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all Banner instances");
		try {
			String queryString = "from Banner";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Banner merge(Banner detachedInstance) {
		log.debug("merging Banner instance");
		try {
			Banner result = (Banner) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Banner instance) {
		log.debug("attaching dirty Banner instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Banner instance) {
		log.debug("attaching clean Banner instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BannerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BannerDAO) ctx.getBean("BannerDAO");
	}
}