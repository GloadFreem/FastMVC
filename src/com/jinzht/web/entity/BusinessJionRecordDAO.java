package com.jinzht.web.entity;

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
 * BusinessJionRecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusinessJionRecord
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusinessJionRecordDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessJionRecordDAO.class);
	// property constants
	public static final String RISEND = "risend";
	public static final String RTIME = "rtime";

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

	public void save(BusinessJionRecord transientInstance) {
		log.debug("saving BusinessJionRecord instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessJionRecord persistentInstance) {
		log.debug("deleting BusinessJionRecord instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessJionRecord findById(java.lang.Integer id) {
		log.debug("getting BusinessJionRecord instance with id: " + id);
		try {
			BusinessJionRecord instance = (BusinessJionRecord) getCurrentSession()
					.get("com.jinzht.web.entity.BusinessJionRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusinessJionRecord> findByExample(BusinessJionRecord instance) {
		log.debug("finding BusinessJionRecord instance by example");
		try {
			List<BusinessJionRecord> results = (List<BusinessJionRecord>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.BusinessJionRecord")
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
		log.debug("finding BusinessJionRecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessJionRecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BusinessJionRecord> findByRisend(Object risend) {
		return findByProperty(RISEND, risend);
	}

	public List<BusinessJionRecord> findByRtime(Object rtime) {
		return findByProperty(RTIME, rtime);
	}

	public List findAll() {
		log.debug("finding all BusinessJionRecord instances");
		try {
			String queryString = "from BusinessJionRecord";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusinessJionRecord merge(BusinessJionRecord detachedInstance) {
		log.debug("merging BusinessJionRecord instance");
		try {
			BusinessJionRecord result = (BusinessJionRecord) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessJionRecord instance) {
		log.debug("attaching dirty BusinessJionRecord instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessJionRecord instance) {
		log.debug("attaching clean BusinessJionRecord instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessJionRecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessJionRecordDAO) ctx.getBean("BusinessJionRecordDAO");
	}
}