package com.jinzht.web.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.jinzht.web.entity.Businessplan;

/**
 * A data access object (DAO) providing persistence and search support for
 * Businessplan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Businessplan
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusinessplanDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessplanDAO.class);
	// property constants
	public static final String URL = "url";
	public static final String CONTENT = "content";

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

	public void save(Businessplan transientInstance) {
		log.debug("saving Businessplan instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Businessplan transientInstance) {
		log.debug("saving Businessplan instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Businessplan persistentInstance) {
		log.debug("deleting Businessplan instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Businessplan findById(java.lang.Integer id) {
		log.debug("getting Businessplan instance with id: " + id);
		try {
			Businessplan instance = (Businessplan) getCurrentSession().get(
					"com.jinzht.web.entity.Businessplan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Businessplan> findByExample(Businessplan instance) {
		log.debug("finding Businessplan instance by example");
		try {
			List<Businessplan> results = (List<Businessplan>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Businessplan")
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
		log.debug("finding Businessplan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Businessplan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Businessplan> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<Businessplan> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Businessplan instances");
		try {
			String queryString = "from Businessplan";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByPage(Integer start,Integer size) {
		log.debug("finding all Businessplan instances");
		try {
			String queryString = "from Businessplan";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(start*size);
			queryObject.setMaxResults(size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public Integer countOfAllUsers() {
		log.debug("finding all Businessplan instances");
		try {
			String queryString = "select count(*) from businessplan";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Businessplan merge(Businessplan detachedInstance) {
		log.debug("merging Businessplan instance");
		try {
			Businessplan result = (Businessplan) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Businessplan instance) {
		log.debug("attaching dirty Businessplan instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Businessplan instance) {
		log.debug("attaching clean Businessplan instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessplanDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessplanDAO) ctx.getBean("BusinessplanDAO");
	}
}