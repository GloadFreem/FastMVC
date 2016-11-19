package com.message.Enity;

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

/**
 * A data access object (DAO) providing persistence and search support for
 * Original entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.message.Enity.Original
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class OriginalDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OriginalDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String ORINGL = "oringl";
	public static final String PUBLIC_DATE = "publicDate";
	public static final String TAG = "tag";
	public static final String HOT = "hot";

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

	public void save(Original transientInstance) {
		log.debug("saving Original instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Original persistentInstance) {
		log.debug("deleting Original instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Original findById(java.lang.Integer id) {
		log.debug("getting Original instance with id: " + id);
		try {
			Original instance = (Original) getCurrentSession().get(
					"com.message.Enity.Original", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Original> findByExample(Original instance) {
		log.debug("finding Original instance by example");
		try {
			List<Original> results = (List<Original>) getCurrentSession()
					.createCriteria("com.message.Enity.Original")
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
		log.debug("finding Original instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Original as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Original> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Original> findByOringl(Object oringl) {
		return findByProperty(ORINGL, oringl);
	}

	public List<Original> findByPublicDate(Object publicDate) {
		return findByProperty(PUBLIC_DATE, publicDate);
	}

	public List<Original> findByTag(Object tag) {
		return findByProperty(TAG, tag);
	}

	public List<Original> findByHot(Object hot) {
		return findByProperty(HOT, hot);
	}

	public List findAll() {
		log.debug("finding all Original instances");
		try {
			String queryString = "from Original";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Original merge(Original detachedInstance) {
		log.debug("merging Original instance");
		try {
			Original result = (Original) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Original instance) {
		log.debug("attaching dirty Original instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Original instance) {
		log.debug("attaching clean Original instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OriginalDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OriginalDAO) ctx.getBean("OriginalDAO");
	}

	public List findByPage(int pageIndex, int pageSize) {
		try {
			Query q = getCurrentSession().createQuery(
					"from Original obj order by obj.publicDate desc");
			q.setFirstResult(pageIndex * pageSize);
			q.setMaxResults(pageSize);
			return q.list();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List searchByKeyWords(int pageIndex, int pageSize, String keyWords) {
		try {
			String queryString = "from Original obj where obj.title like'%"
					+ keyWords + "%' order by obj.publicDate desc";
			Query q = getCurrentSession().createQuery(queryString);
			q.setFirstResult(pageIndex);
			q.setMaxResults(pageSize);
			return q.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByPage(Integer start, Integer size) {
		log.debug("finding all original instances");
		try {
			String queryString = "from Original order by publicDate desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(start * size);
			queryObject.setMaxResults(size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Integer countOfAllRecords() {
		log.debug("finding all Original instances");
		try {
			String queryString = "select count(*) from original";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(
					queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Original transientInstance) {
		log.debug("saving Original instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}


	public List findByName(Object name) {
		String sqlString = "select * from original where title like ?";

		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString)
				.addEntity(Original.class);
		queryObject.setParameter(0, "%" + name + "%");

		return queryObject.list();
	}
	
	/**
	 * 
	 * @return
	 */
	public List findHotOri() {
		String queryString;
		Query q ;
		try {
			 queryString = "from Original original order by original.hot desc";
			 q = getCurrentSession().createQuery(queryString);
			q.setFirstResult(0);
			q.setMaxResults(5);
			return q.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}