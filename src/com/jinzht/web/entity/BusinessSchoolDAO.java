package com.jinzht.web.entity;

import java.sql.Timestamp;
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

/**
 * A data access object (DAO) providing persistence and search support for
 * BusinessSchool entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.BusinessSchool
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BusinessSchoolDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessSchoolDAO.class);
	// property constants
	public static final String BNAME = "bname";
	public static final String BIMAGE = "bimage";
	public static final String BDESC = "bdesc";
	public static final String BSPEECH_MARKER = "bspeechMarker";
	public static final String BLIMIT = "blimit";
	public static final String BPRICE_BASE = "bpriceBase";
	public static final String BPRICE_NOW = "bpriceNow";
	public static final String BEXTR = "bextr";
	public static final String BEXTR2 = "bextr2";

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

	public void save(BusinessSchool transientInstance) {
		log.debug("saving BusinessSchool instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(BusinessSchool transientInstance) {
		log.debug("saving BusinessSchool instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessSchool persistentInstance) {
		log.debug("deleting BusinessSchool instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessSchool findById(java.lang.Integer id) {
		log.debug("getting BusinessSchool instance with id: " + id);
		try {
			BusinessSchool instance = (BusinessSchool) getCurrentSession().get(
					"com.jinzht.web.entity.BusinessSchool", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BusinessSchool> findByExample(BusinessSchool instance) {
		log.debug("finding BusinessSchool instance by example");
		try {
			List<BusinessSchool> results = (List<BusinessSchool>) getCurrentSession()
					.createCriteria("com.jinzht.web.entity.BusinessSchool")
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
		log.debug("finding BusinessSchool instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessSchool as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Project> findByKeyName(Object name) {
		String sqlString = "select * from business_school where bname like ?";
		
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString).addEntity(BusinessSchool.class);
		queryObject.setParameter(0, "%"+name+"%");
		
		return queryObject.list();
	}
	
	public List<BusinessSchool> findByBname(Object bname) {
		return findByProperty(BNAME, bname);
	}

	public List<BusinessSchool> findByBimage(Object bimage) {
		return findByProperty(BIMAGE, bimage);
	}

	public List<BusinessSchool> findByBdesc(Object bdesc) {
		return findByProperty(BDESC, bdesc);
	}

	public List<BusinessSchool> findByBspeechMarker(Object bspeechMarker) {
		return findByProperty(BSPEECH_MARKER, bspeechMarker);
	}

	public List<BusinessSchool> findByBlimit(Object blimit) {
		return findByProperty(BLIMIT, blimit);
	}

	public List<BusinessSchool> findByBpriceBase(Object bpriceBase) {
		return findByProperty(BPRICE_BASE, bpriceBase);
	}

	public List<BusinessSchool> findByBpriceNow(Object bpriceNow) {
		return findByProperty(BPRICE_NOW, bpriceNow);
	}

	public List<BusinessSchool> findByBextr(Object bextr) {
		return findByProperty(BEXTR, bextr);
	}

	public List<BusinessSchool> findByBextr2(Object bextr2) {
		return findByProperty(BEXTR2, bextr2);
	}

	public List findAll() {
		log.debug("finding all BusinessSchool instances");
		try {
			String queryString = "from BusinessSchool as model order by model.bposition asc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public List findByPage(Integer size,Integer page) {
		log.debug("finding all BusinessSchool instances");
		try {
			String queryString = "from BusinessSchool";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(page*size);
			queryObject.setMaxResults(size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer countOfInstance() {
		log.debug("finding all BusinessSchool instances");
		try {
			String queryString = "select count(*) from business_school";
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
	
	
	public List findByValidPage(Integer page) {
		log.debug("finding all BusinessSchool instances");
		try {
			String queryString = "from BusinessSchool as model  where model.bValid=?  order by model.bposition asc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, true);
			queryObject.setFirstResult(page*Config.Page_Data_Size);
			queryObject.setMaxResults(Config.Page_Data_Size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public List findByUserAndPage(Users user,Integer page) {
		log.debug("finding all BusinessSchool instances");
		try {
			String queryString = "from BusinessSchool";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(page*Config.Page_Data_Size);
			queryObject.setMaxResults(Config.Page_Data_Size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusinessSchool merge(BusinessSchool detachedInstance) {
		log.debug("merging BusinessSchool instance");
		try {
			BusinessSchool result = (BusinessSchool) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessSchool instance) {
		log.debug("attaching dirty BusinessSchool instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessSchool instance) {
		log.debug("attaching clean BusinessSchool instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessSchoolDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessSchoolDAO) ctx.getBean("BusinessSchoolDAO");
	}
}