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

import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Roadshow;

/**
 * A data access object (DAO) providing persistence and search support for
 * Roadshow entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Roadshow
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RoadshowDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RoadshowDAO.class);
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

	public void save(Roadshow transientInstance) {
		log.debug("saving Roadshow instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Roadshow transientInstance) {
		log.debug("saving Roadshow instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roadshow persistentInstance) {
		log.debug("deleting Roadshow instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roadshow findById(java.lang.Integer id) {
		log.debug("getting Roadshow instance with id: " + id);
		try {
			Roadshow instance = (Roadshow) getCurrentSession().get(
					"com.jinzht.web.entity.Roadshow", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Roadshow> findByExample(Roadshow instance) {
		log.debug("finding Roadshow instance by example");
		try {
			List<Roadshow> results = (List<Roadshow>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Roadshow")
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
		log.debug("finding Roadshow instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roadshow as model where model."
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
		log.debug("finding all Roadshow instances");
		try {
			String queryString = "from Roadshow";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Project> findByName(Object name) {
		String sqlString = "select * from roadshow where full_name like ?";
		
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString).addEntity(Project.class);
		queryObject.setParameter(0, "%"+name+"%");
		
		return queryObject.list();
	}
	public List findByPage(Integer start,Integer size) {
		log.debug("finding all Roadshow instances");
		try {
			String queryString = "from Roadshow";
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
		log.debug("finding all Roadshow instances");
		try {
			String queryString = "select count(*) from roadshow";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roadshow merge(Roadshow detachedInstance) {
		log.debug("merging Roadshow instance");
		try {
			Roadshow result = (Roadshow) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Roadshow instance) {
		log.debug("attaching dirty Roadshow instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roadshow instance) {
		log.debug("attaching clean Roadshow instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RoadshowDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RoadshowDAO) ctx.getBean("RoadshowDAO");
	}
}