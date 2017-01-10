package com.jinzht.web.entity;

import java.sql.Timestamp;
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

/**
 * A data access object (DAO) providing persistence and search support for
 * BusniessJoin entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusniessJoin
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusniessJoinDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusniessJoinDAO.class);
	// property constants
	public static final String JTEL = "jtel";

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

	public void save(BusniessJoin transientInstance) {
		log.debug("saving BusniessJoin instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusniessJoin persistentInstance) {
		log.debug("deleting BusniessJoin instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusniessJoin findById(java.lang.Integer id) {
		log.debug("getting BusniessJoin instance with id: " + id);
		try {
			BusniessJoin instance = (BusniessJoin) getCurrentSession().get(
					"com.jinzht.web.entity.BusniessJoin", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusniessJoin> findByExample(BusniessJoin instance) {
		log.debug("finding BusniessJoin instance by example");
		try {
			List<BusniessJoin> results = (List<BusniessJoin>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.BusniessJoin")
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
		log.debug("finding BusniessJoin instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusniessJoin as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public List findBySchoolAndUser(Users user,BusinessSchool school,Integer page) {
		try {
			String queryString = "from BusniessJoin as model where model.users = ? and model.businessSchool=?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, user);
			queryObject.setParameter(1, school);
			queryObject.setFirstResult(page*10);
			queryObject.setMaxResults(10);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByUser(Users user,Integer page) {
		try {
			String queryString = "from BusniessJoin as model where model.users = ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, user);
			queryObject.setFirstResult(page*10);
			queryObject.setMaxResults(10);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BusniessJoin> findByJtel(Object jtel) {
		return findByProperty(JTEL, jtel);
	}

	public List findAll() {
		log.debug("finding all BusniessJoin instances");
		try {
			String queryString = "from BusniessJoin";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public List findByPage(int size,Integer page) {
		log.debug("finding all BusniessJoin instances");
		try {
			String queryString = "from BusniessJoin";
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
		log.debug("finding all BusniessJoin instances");
		try {
			String queryString = "select count(*) from busniess_join";
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

	public BusniessJoin merge(BusniessJoin detachedInstance) {
		log.debug("merging BusniessJoin instance");
		try {
			BusniessJoin result = (BusniessJoin) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusniessJoin instance) {
		log.debug("attaching dirty BusniessJoin instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusniessJoin instance) {
		log.debug("attaching clean BusniessJoin instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusniessJoinDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusniessJoinDAO) ctx.getBean("BusniessJoinDAO");
	}
}