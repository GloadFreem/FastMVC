package com.jinzht.web.entity;

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
 * BusinessVideo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusinessVideo
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusinessVideoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessVideoDAO.class);
	// property constants
	public static final String VDESC = "vdesc";
	public static final String VNAME = "vname";
	public static final String VPOSITION = "vposition";
	public static final String VIMAGE = "vimage";
	public static final String VTIMELONG = "vtimelong";

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

	public void save(BusinessVideo transientInstance) {
		log.debug("saving BusinessVideo instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(BusinessVideo transientInstance) {
		log.debug("saving BusinessVideo instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessVideo persistentInstance) {
		log.debug("deleting BusinessVideo instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessVideo findById(java.lang.Integer id) {
		log.debug("getting BusinessVideo instance with id: " + id);
		try {
			BusinessVideo instance = (BusinessVideo) getCurrentSession().get(
					"com.jinzht.web.entity.BusinessVideo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusinessVideo> findByExample(BusinessVideo instance) {
		log.debug("finding BusinessVideo instance by example");
		try {
			List<BusinessVideo> results = (List<BusinessVideo>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.BusinessVideo")
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
		log.debug("finding BusinessVideo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessVideo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BusinessVideo> findByVdesc(Object vdesc) {
		return findByProperty(VDESC, vdesc);
	}

	public List<BusinessVideo> findByVname(Object vname) {
		return findByProperty(VNAME, vname);
	}

	public List<BusinessVideo> findByVposition(Object vposition) {
		return findByProperty(VPOSITION, vposition);
	}

	public List<BusinessVideo> findByVimage(Object vimage) {
		return findByProperty(VIMAGE, vimage);
	}

	public List<BusinessVideo> findByVtimelong(Object vtimelong) {
		return findByProperty(VTIMELONG, vtimelong);
	}

	public List findAll() {
		log.debug("finding all BusinessVideo instances");
		try {
			String queryString = "from BusinessVideo";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPage(int size,Integer page) {
		log.debug("finding all BusinessVideo instances");
		try {
			String queryString = "from BusinessVideo";
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
		log.debug("finding all BusinessVideo instances");
		try {
			String queryString = "select count(*) from business_video";
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
	
	
	public Integer findSchoolIdByVideoId(Integer vId) {
		log.debug("finding all BusinessVideo instances");
		try {
			String queryString = "select resourceid from business_video";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			List list= queryObject.list();
			if(list!=null && list.size()>0)
			{
				return Integer.parseInt(list.get(0).toString());
			}
			
			return -1;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusinessVideo merge(BusinessVideo detachedInstance) {
		log.debug("merging BusinessVideo instance");
		try {
			BusinessVideo result = (BusinessVideo) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessVideo instance) {
		log.debug("attaching dirty BusinessVideo instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessVideo instance) {
		log.debug("attaching clean BusinessVideo instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessVideoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessVideoDAO) ctx.getBean("BusinessVideoDAO");
	}
}