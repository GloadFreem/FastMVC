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
 * UserWechat entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.UserWechat
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class UserWechatDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UserWechatDAO.class);
	// property constants
	public static final String WCODE = "wcode";
	public static final String WTEL = "wtel";

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

	public void save(UserWechat transientInstance) {
		log.debug("saving UserWechat instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserWechat persistentInstance) {
		log.debug("deleting UserWechat instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserWechat findById(java.lang.Integer id) {
		log.debug("getting UserWechat instance with id: " + id);
		try {
			UserWechat instance = (UserWechat) getCurrentSession().get(
					"com.jinzht.web.entity.UserWechat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<UserWechat> findByExample(UserWechat instance) {
		log.debug("finding UserWechat instance by example");
		try {
			List<UserWechat> results = (List<UserWechat>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.UserWechat")
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
		log.debug("finding UserWechat instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserWechat as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<UserWechat> findByWcode(Object wcode) {
		return findByProperty(WCODE, wcode);
	}

	public List<UserWechat> findByWtel(Object wtel) {
		return findByProperty(WTEL, wtel);
	}

	public List findAll() {
		log.debug("finding all UserWechat instances");
		try {
			String queryString = "from UserWechat";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserWechat merge(UserWechat detachedInstance) {
		log.debug("merging UserWechat instance");
		try {
			UserWechat result = (UserWechat) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserWechat instance) {
		log.debug("attaching dirty UserWechat instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserWechat instance) {
		log.debug("attaching clean UserWechat instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserWechatDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserWechatDAO) ctx.getBean("UserWechatDAO");
	}
}