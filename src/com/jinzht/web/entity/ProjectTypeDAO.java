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
 * ProjectType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.ProjectType
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ProjectTypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProjectTypeDAO.class);
	// property constants
	public static final String KEY = "key";
	public static final String VALUE = "value";

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

	public void save(ProjectType transientInstance) {
		log.debug("saving ProjectType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProjectType persistentInstance) {
		log.debug("deleting ProjectType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProjectType findById(java.lang.Integer id) {
		log.debug("getting ProjectType instance with id: " + id);
		try {
			ProjectType instance = (ProjectType) getCurrentSession().get(
					"com.jinzht.web.entity.ProjectType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ProjectType> findByExample(ProjectType instance) {
		log.debug("finding ProjectType instance by example");
		try {
			List<ProjectType> results = (List<ProjectType>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.ProjectType")
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
		log.debug("finding ProjectType instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ProjectType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ProjectType> findByKey(Object key) {
		return findByProperty(KEY, key);
	}

	public List<ProjectType> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findAll() {
		log.debug("finding all ProjectType instances");
		try {
			String queryString = "from ProjectType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProjectType merge(ProjectType detachedInstance) {
		log.debug("merging ProjectType instance");
		try {
			ProjectType result = (ProjectType) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProjectType instance) {
		log.debug("attaching dirty ProjectType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProjectType instance) {
		log.debug("attaching clean ProjectType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProjectTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProjectTypeDAO) ctx.getBean("ProjectTypeDAO");
	}
}