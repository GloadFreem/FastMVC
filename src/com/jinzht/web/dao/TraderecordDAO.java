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

import com.jinzht.tools.Config;
import com.jinzht.web.entity.Traderecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * Traderecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Traderecord
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TraderecordDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TraderecordDAO.class);
	// property constants
	public static final String MOUNT = "mount";
	public static final String TRADE_TYPE = "tradeType";
	public static final String TRADE_DATE = "tradeDate";

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

	public void save(Traderecord transientInstance) {
		log.debug("saving Traderecord instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Traderecord persistentInstance) {
		log.debug("deleting Traderecord instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Traderecord findById(java.lang.Integer id) {
		log.debug("getting Traderecord instance with id: " + id);
		try {
			Traderecord instance = (Traderecord) getCurrentSession().get(
					"com.jinzht.web.hibernate.Traderecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Traderecord> findByExample(Traderecord instance) {
		log.debug("finding Traderecord instance by example");
		try {
			List<Traderecord> results = (List<Traderecord>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Traderecord")
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
		log.debug("finding Traderecord instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Traderecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPropertyWithPage(String propertyName, Object value,Integer page) {
		log.debug("finding Traderecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Traderecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setFirstResult(page);
			queryObject.setMaxResults(Config.STRING_INVESTOR_LIST_MAX_SIZE);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Traderecord> findByMount(Object mount) {
		return findByProperty(MOUNT, mount);
	}

	public List<Traderecord> findByTradeType(Object tradeType) {
		return findByProperty(TRADE_TYPE, tradeType);
	}

	public List<Traderecord> findByTradeDate(Object tradeDate) {
		return findByProperty(TRADE_DATE, tradeDate);
	}

	public List findAll() {
		log.debug("finding all Traderecord instances");
		try {
			String queryString = "from Traderecord";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Traderecord merge(Traderecord detachedInstance) {
		log.debug("merging Traderecord instance");
		try {
			Traderecord result = (Traderecord) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Traderecord instance) {
		log.debug("attaching dirty Traderecord instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Traderecord instance) {
		log.debug("attaching clean Traderecord instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TraderecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TraderecordDAO) ctx.getBean("TraderecordDAO");
	}
}