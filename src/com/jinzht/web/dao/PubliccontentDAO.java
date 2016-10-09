package com.jinzht.web.dao;
// default package

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

import com.jinzht.tools.Config;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for
 * Publiccontent entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Publiccontent
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class PubliccontentDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PubliccontentDAO.class);
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

	public void save(Publiccontent transientInstance) {
		log.debug("saving Publiccontent instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Publiccontent transientInstance) {
		log.debug("saving Publiccontent instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Publiccontent persistentInstance) {
		log.debug("deleting Publiccontent instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Publiccontent findById(java.lang.Integer id) {
		log.debug("getting Publiccontent instance with id: " + id);
		try {
			Publiccontent instance = (Publiccontent) getCurrentSession().get(
					"com.jinzht.web.entity.Publiccontent", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Publiccontent> findByExample(Publiccontent instance) {
		log.debug("finding Publiccontent instance by example");
		try {
			List<Publiccontent> results = (List<Publiccontent>) getCurrentSession()
					.createCriteria("Publiccontent").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Publiccontent instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Publiccontent as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Publiccontent> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Publiccontent instances");
		try {
			String queryString = "from Publiccontent";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByPage(Integer start,Integer size) {
		log.debug("finding all Publiccontent instances");
		try {
			String queryString = "from Publiccontent";
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
		log.debug("finding all Publiccontent instances");
		try {
			String queryString = "select count(*) from publiccontent";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByCursor(int cursor){
		log.debug("finding Publiccontent instances by cursor");
		try {
			String queryString = "from Publiccontent  order by publicDate desc";
			Query queryObject = getCurrentSession().createQuery(queryString)
					.setFirstResult(cursor*Config.STRING_FEELING_PAGESIZE)
					.setMaxResults(Config.STRING_FEELING_PAGESIZE)
					;
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByUserAndCursor(Users user,int cursor){
		log.debug("finding Publiccontent instances by cursor");
		try {
			String queryString = "from Publiccontent where users=?  order by publicContentId desc";
			Query queryObject = getCurrentSession().createQuery(queryString)
					.setFirstResult(cursor*Config.STRING_FEELING_PAGESIZE)
					.setMaxResults(Config.STRING_FEELING_PAGESIZE)
					;
			queryObject.setParameter(0, user);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Publiccontent merge(Publiccontent detachedInstance) {
		log.debug("merging Publiccontent instance");
		try {
			Publiccontent result = (Publiccontent) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Publiccontent instance) {
		log.debug("attaching dirty Publiccontent instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Publiccontent instance) {
		log.debug("attaching clean Publiccontent instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PubliccontentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PubliccontentDAO) ctx.getBean("PubliccontentDAO");
	}
}