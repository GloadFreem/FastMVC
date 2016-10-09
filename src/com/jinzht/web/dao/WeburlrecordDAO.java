package com.jinzht.web.dao;
// default package

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

import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Weburlrecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * Weburlrecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Weburlrecord
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class WeburlrecordDAO {
	private static final Logger log = LoggerFactory
			.getLogger(WeburlrecordDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String TAG = "tag";
	public static final String URL = "url";
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

	public void save(Weburlrecord transientInstance) {
		log.debug("saving Weburlrecord instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Weburlrecord transientInstance) {
		log.debug("saving Weburlrecord instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Weburlrecord persistentInstance) {
		log.debug("deleting Weburlrecord instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Weburlrecord findById(java.lang.Integer id) {
		log.debug("getting Weburlrecord instance with id: " + id);
		try {
			Weburlrecord instance = (Weburlrecord) getCurrentSession().get(
					"com.jinzht.web.entity.Weburlrecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Weburlrecord> findByExample(Weburlrecord instance) {
		log.debug("finding Weburlrecord instance by example");
		try {
			List<Weburlrecord> results = (List<Weburlrecord>) getCurrentSession()
					.createCriteria("Weburlrecord").add(create(instance))
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
		log.debug("finding Weburlrecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Weburlrecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Weburlrecord> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Weburlrecord> findByTag(Object tag) {
		return findByProperty(TAG, tag);
	}

	public List<Weburlrecord> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<Weburlrecord> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Weburlrecord instances");
		try {
			String queryString = "from Weburlrecord";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public Integer countOfAllUsers() {
		log.debug("finding all Weburlrecord instances");
		try {
			String queryString = "select count(*) from weburlrecord where type_id<3 order by  create_date desc";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findKingCapital(Integer page ,Integer size) {
		log.debug("finding all Weburlrecord instances");
		try {
			String queryString = "select * from weburlrecord where type_id<3 order by  create_date desc";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString).addEntity(Weburlrecord.class);
			queryObject.setFirstResult(page*size);
			queryObject.setMaxResults(size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByPage(Integer page,Integer size) {
		log.debug("finding all Weburlrecord instances");
		try {
			Contenttype type  = new Contenttype();
			type.setTypeId(1);
			
			String queryString = "from Weburlrecord where contenttype=? order by createDate desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, type);
			queryObject.setFirstResult(page*size);
			queryObject.setMaxResults(size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Weburlrecord merge(Weburlrecord detachedInstance) {
		log.debug("merging Weburlrecord instance");
		try {
			Weburlrecord result = (Weburlrecord) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Weburlrecord instance) {
		log.debug("attaching dirty Weburlrecord instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Weburlrecord instance) {
		log.debug("attaching clean Weburlrecord instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static WeburlrecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (WeburlrecordDAO) ctx.getBean("WeburlrecordDAO");
	}
}