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
 * ProjectAddress entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.ProjectAddress
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ProjectAddressDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProjectAddressDAO.class);
	// property constants
	public static final String VALUE = "value";
	public static final String KEY = "key";

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

	public void save(ProjectAddress transientInstance) {
		log.debug("saving ProjectAddress instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProjectAddress persistentInstance) {
		log.debug("deleting ProjectAddress instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProjectAddress findById(java.lang.Integer id) {
		log.debug("getting ProjectAddress instance with id: " + id);
		try {
			ProjectAddress instance = (ProjectAddress) getCurrentSession().get(
					"com.jinzht.web.entity.ProjectAddress", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ProjectAddress> findByExample(ProjectAddress instance) {
		log.debug("finding ProjectAddress instance by example");
		try {
			List<ProjectAddress> results = (List<ProjectAddress>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.ProjectAddress")
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
		log.debug("finding ProjectAddress instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProjectAddress as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ProjectAddress> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List<ProjectAddress> findByKey(Object key) {
		return findByProperty(KEY, key);
	}

	public List findAll() {
		log.debug("finding all ProjectAddress instances");
		try {
			String queryString = "from ProjectAddress";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProjectAddress merge(ProjectAddress detachedInstance) {
		log.debug("merging ProjectAddress instance");
		try {
			ProjectAddress result = (ProjectAddress) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProjectAddress instance) {
		log.debug("attaching dirty ProjectAddress instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProjectAddress instance) {
		log.debug("attaching clean ProjectAddress instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProjectAddressDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProjectAddressDAO) ctx.getBean("ProjectAddressDAO");
	}
}