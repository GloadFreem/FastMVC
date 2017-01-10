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
 * BusinessInvitationCode entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusinessInvitationCode
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusinessInvitationCodeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessInvitationCodeDAO.class);
	// property constants
	public static final String RESOURCEID = "resourceid";
	public static final String CCODE = "ccode";
	public static final String CVALID = "cvalid";

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

	public void save(BusinessInvitationCode transientInstance) {
		log.debug("saving BusinessInvitationCode instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(BusinessInvitationCode transientInstance) {
		log.debug("saving BusinessInvitationCode instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessInvitationCode persistentInstance) {
		log.debug("deleting BusinessInvitationCode instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessInvitationCode findById(java.lang.Integer id) {
		log.debug("getting BusinessInvitationCode instance with id: " + id);
		try {
			BusinessInvitationCode instance = (BusinessInvitationCode) getCurrentSession()
					.get("com.jinzht.web.entity.BusinessInvitationCode", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusinessInvitationCode> findByExample(
			BusinessInvitationCode instance) {
		log.debug("finding BusinessInvitationCode instance by example");
		try {
			List<BusinessInvitationCode> results = (List<BusinessInvitationCode>) getCurrentSession()
					.createCriteria(
							"com.jinzht.web.entity.BusinessInvitationCode")
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
		log.debug("finding BusinessInvitationCode instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessInvitationCode as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BusinessInvitationCode> findByResourceid(Object resourceid) {
		return findByProperty(RESOURCEID, resourceid);
	}

	public List<BusinessInvitationCode> findByCcode(Object ccode) {
		return findByProperty(CCODE, ccode);
	}

	public List<BusinessInvitationCode> findByCvalid(Object cvalid) {
		return findByProperty(CVALID, cvalid);
	}

	public List findAll() {
		log.debug("finding all BusinessInvitationCode instances");
		try {
			String queryString = "from BusinessInvitationCode";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPage(int size,Integer page) {
		log.debug("finding all BusinessInvitationCode instances");
		try {
			String queryString = "from BusinessInvitationCode";
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
		log.debug("finding all BusinessInvitationCode instances");
		try {
			String queryString = "select count(*) from business_invitation_code";
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

	public BusinessInvitationCode merge(BusinessInvitationCode detachedInstance) {
		log.debug("merging BusinessInvitationCode instance");
		try {
			BusinessInvitationCode result = (BusinessInvitationCode) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessInvitationCode instance) {
		log.debug("attaching dirty BusinessInvitationCode instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessInvitationCode instance) {
		log.debug("attaching clean BusinessInvitationCode instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessInvitationCodeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessInvitationCodeDAO) ctx
				.getBean("BusinessInvitationCodeDAO");
	}
}