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

import com.jinzht.tools.Config;
import com.jinzht.web.entity.Actioncomment;

/**
 * A data access object (DAO) providing persistence and search support for
 * Actioncomment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Actioncomment
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ActioncommentDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ActioncommentDAO.class);
	// property constants
	public static final String CONTENT = "content";

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

	public void save(Actioncomment transientInstance) {
		log.debug("saving Actioncomment instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Actioncomment transientInstance) {
		log.debug("saving Actioncomment instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Actioncomment persistentInstance) {
		log.debug("deleting Actioncomment instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Actioncomment findById(java.lang.Integer id) {
		log.debug("getting Actioncomment instance with id: " + id);
		try {
			Actioncomment instance = (Actioncomment) getCurrentSession().get(
					"com.jinzht.web.entity.Actioncomment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Actioncomment> findByExample(Actioncomment instance) {
		log.debug("finding Actioncomment instance by example");
		try {
			List<Actioncomment> results = (List<Actioncomment>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Actioncomment")
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
		log.debug("finding Actioncomment instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Actioncomment as model where model."
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
		log.debug("finding Actioncomment instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Actioncomment as model where model."
					+ propertyName + "= ? order by model.commentId desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setFirstResult(page*Config.STRING_INVESTOR_LIST_MAX_SIZE);
			queryObject.setMaxResults(Config.STRING_INVESTOR_LIST_MAX_SIZE);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findUserIdByCommentId(Integer commentId)
	{
		String sqlString  = "select user_id from actioncomment where comment_id = ?";
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString);
		queryObject.setParameter(0, commentId);
		queryObject.setMaxResults(1);
		
		return queryObject.list();
	}
	public List findAtUserIdByCommentId(Integer commentId)
	{
		String sqlString  = "select at_user_id from actioncomment where comment_id = ?";
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString);
		queryObject.setParameter(0, commentId);
		queryObject.setMaxResults(1);
		
		return queryObject.list();
	}

	public List<Actioncomment> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Actioncomment instances");
		try {
			String queryString = "from Actioncomment";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Actioncomment merge(Actioncomment detachedInstance) {
		log.debug("merging Actioncomment instance");
		try {
			Actioncomment result = (Actioncomment) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Actioncomment instance) {
		log.debug("attaching dirty Actioncomment instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Actioncomment instance) {
		log.debug("attaching clean Actioncomment instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ActioncommentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ActioncommentDAO) ctx.getBean("ActioncommentDAO");
	}
}