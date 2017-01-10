package com.jinzht.web.entity;

import java.util.List;
import java.util.Set;

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

/**
 * A data access object (DAO) providing persistence and search support for
 * BusinessWeichat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusinessWeichat
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusinessWeichatDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessWeichatDAO.class);
	// property constants
	public static final String WCODE = "wcode";

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

	public void save(BusinessWeichat transientInstance) {
		log.debug("saving BusinessWeichat instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(BusinessWeichat transientInstance) {
		log.debug("saving BusinessWeichat instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessWeichat persistentInstance) {
		log.debug("deleting BusinessWeichat instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessWeichat findById(java.lang.Integer id) {
		log.debug("getting BusinessWeichat instance with id: " + id);
		try {
			BusinessWeichat instance = (BusinessWeichat) getCurrentSession()
					.get("com.jinzht.web.entity.BusinessWeichat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusinessWeichat> findByExample(BusinessWeichat instance) {
		log.debug("finding BusinessWeichat instance by example");
		try {
			List<BusinessWeichat> results = (List<BusinessWeichat>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.BusinessWeichat")
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
		log.debug("finding BusinessWeichat instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessWeichat as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BusinessWeichat> findByWcode(Object wcode) {
		return findByProperty(WCODE, wcode);
	}

	public List findAll() {
		log.debug("finding all BusinessWeichat instances");
		try {
			String queryString = "from BusinessWeichat";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPage(int size,Integer page) {
		log.debug("finding all BusinessWeichat instances");
		try {
			String queryString = "from BusinessWeichat";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setMaxResults(size);
			queryObject.setFirstResult(size*page);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public Integer countOfInstance() {
		log.debug("finding all BusinessWeichat instances");
		try {
			String queryString = "select count(*) from business_weichat";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			if(queryObject.list()!=null)
			{
				return Integer.parseInt(queryObject.list().get(0).toString());
			}
			return 0;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusinessWeichat merge(BusinessWeichat detachedInstance) {
		log.debug("merging BusinessWeichat instance");
		try {
			BusinessWeichat result = (BusinessWeichat) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessWeichat instance) {
		log.debug("attaching dirty BusinessWeichat instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessWeichat instance) {
		log.debug("attaching clean BusinessWeichat instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessWeichatDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessWeichatDAO) ctx.getBean("BusinessWeichatDAO");
	}
}