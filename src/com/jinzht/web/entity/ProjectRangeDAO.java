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
 * ProjectRange entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.ProjectRange
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ProjectRangeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProjectRangeDAO.class);
	// property constants
	public static final String DESC = "desc";
	public static final String FROM = "from";
	public static final String TO = "to";
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

	public void save(ProjectRange transientInstance) {
		log.debug("saving ProjectRange instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProjectRange persistentInstance) {
		log.debug("deleting ProjectRange instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProjectRange findById(java.lang.Integer id) {
		log.debug("getting ProjectRange instance with id: " + id);
		try {
			ProjectRange instance = (ProjectRange) getCurrentSession().get(
					"com.jinzht.web.entity.ProjectRange", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ProjectRange> findByExample(ProjectRange instance) {
		log.debug("finding ProjectRange instance by example");
		try {
			List<ProjectRange> results = (List<ProjectRange>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.ProjectRange")
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
		log.debug("finding ProjectRange instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProjectRange as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ProjectRange> findByDesc(Object desc) {
		return findByProperty(DESC, desc);
	}

	public List<ProjectRange> findByFrom(Object from) {
		return findByProperty(FROM, from);
	}

	public List<ProjectRange> findByTo(Object to) {
		return findByProperty(TO, to);
	}

	public List<ProjectRange> findByKey(Object key) {
		return findByProperty(KEY, key);
	}

	public List findAll() {
		log.debug("finding all ProjectRange instances");
		try {
			String queryString = "from ProjectRange";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProjectRange merge(ProjectRange detachedInstance) {
		log.debug("merging ProjectRange instance");
		try {
			ProjectRange result = (ProjectRange) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProjectRange instance) {
		log.debug("attaching dirty ProjectRange instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProjectRange instance) {
		log.debug("attaching clean ProjectRange instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProjectRangeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProjectRangeDAO) ctx.getBean("ProjectRangeDAO");
	}
}