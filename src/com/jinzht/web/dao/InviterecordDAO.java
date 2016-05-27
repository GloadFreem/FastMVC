package com.jinzht.web.dao;

import java.util.Date;
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

import com.jinzht.web.entity.Inviterecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * Inviterecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Inviterecord
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class InviterecordDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InviterecordDAO.class);
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

	public void save(Inviterecord transientInstance) {
		log.debug("saving Inviterecord instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Inviterecord persistentInstance) {
		log.debug("deleting Inviterecord instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Inviterecord findById(java.lang.Integer id) {
		log.debug("getting Inviterecord instance with id: " + id);
		try {
			Inviterecord instance = (Inviterecord) getCurrentSession().get(
					"com.jinzht.web.hibernate.Inviterecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Inviterecord> findByExample(Inviterecord instance) {
		log.debug("finding Inviterecord instance by example");
		try {
			List<Inviterecord> results = (List<Inviterecord>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Inviterecord")
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
		log.debug("finding Inviterecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Inviterecord as model where model."
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
		log.debug("finding all Inviterecord instances");
		try {
			String queryString = "from Inviterecord";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Inviterecord merge(Inviterecord detachedInstance) {
		log.debug("merging Inviterecord instance");
		try {
			Inviterecord result = (Inviterecord) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Inviterecord instance) {
		log.debug("attaching dirty Inviterecord instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Inviterecord instance) {
		log.debug("attaching clean Inviterecord instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static InviterecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (InviterecordDAO) ctx.getBean("InviterecordDAO");
	}
}