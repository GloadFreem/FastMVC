package com.jinzht.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.jinzht.tools.Config;
import com.jinzht.web.entity.Projectcommitrecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * Projectcommitrecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Projectcommitrecord
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ProjectcommitrecordDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProjectcommitrecordDAO.class);
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

	public void save(Projectcommitrecord transientInstance) {
		log.debug("saving Projectcommitrecord instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Projectcommitrecord persistentInstance) {
		log.debug("deleting Projectcommitrecord instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Projectcommitrecord findById(java.lang.Integer id) {
		log.debug("getting Projectcommitrecord instance with id: " + id);
		try {
			Projectcommitrecord instance = (Projectcommitrecord) getCurrentSession()
					.get("com.jinzht.web.hibernate.Projectcommitrecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Projectcommitrecord> findByExample(Projectcommitrecord instance) {
		log.debug("finding Projectcommitrecord instance by example");
		try {
			List<Projectcommitrecord> results = (List<Projectcommitrecord>) getCurrentSession()
					.createCriteria(
							"com.jinzht.web.hibernate.Projectcommitrecord")
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
		log.debug("finding Projectcommitrecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Projectcommitrecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByProperties(Map requestMap,Integer page) {
		try {
//			String debugInfo= "finding Authentic instance with property: ";
			String queryString = "from Projectcommitrecord as model where";
			Object[] keys= requestMap.keySet().toArray();
			for(int i = 0;i<requestMap.size();i++){
//				debugInfo += keys[i].toString()+requestMap.get(keys[i]);
				if(i==0){
					queryString+=" model."+keys[i].toString()+" =? ";
				}else{
					queryString+="and model."+keys[i].toString()+" =? ";
				}
			}
//			log.debug(debugInfo);
			
			Query queryObject = getCurrentSession().createQuery(queryString);
			for(int i = 0;i<requestMap.size();i++){
				queryObject.setParameter(i, requestMap.get(keys[i]));
			}
			queryObject.setFirstResult(page);
			queryObject.setMaxResults(Config.STRING_INVESTOR_LIST_MAX_SIZE);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Projectcommitrecord instances");
		try {
			String queryString = "from Projectcommitrecord";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Projectcommitrecord merge(Projectcommitrecord detachedInstance) {
		log.debug("merging Projectcommitrecord instance");
		try {
			Projectcommitrecord result = (Projectcommitrecord) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Projectcommitrecord instance) {
		log.debug("attaching dirty Projectcommitrecord instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Projectcommitrecord instance) {
		log.debug("attaching clean Projectcommitrecord instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProjectcommitrecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProjectcommitrecordDAO) ctx.getBean("ProjectcommitrecordDAO");
	}
}