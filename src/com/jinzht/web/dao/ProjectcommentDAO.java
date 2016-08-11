package com.jinzht.web.dao;

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
import com.jinzht.web.entity.Projectcomment;

/**
 * A data access object (DAO) providing persistence and search support for
 * Projectcomment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Projectcomment
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ProjectcommentDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProjectcommentDAO.class);
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

	public void save(Projectcomment transientInstance) {
		log.debug("saving Projectcomment instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Projectcomment persistentInstance) {
		log.debug("deleting Projectcomment instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Projectcomment findById(java.lang.Integer id) {
		log.debug("getting Projectcomment instance with id: " + id);
		try {
			Projectcomment instance = (Projectcomment) getCurrentSession().get(
					"com.jinzht.web.entity.Projectcomment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Projectcomment> findByExample(Projectcomment instance) {
		log.debug("finding Projectcomment instance by example");
		try {
			List<Projectcomment> results = (List<Projectcomment>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Projectcomment")
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
		log.debug("finding Projectcomment instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Projectcomment as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public Integer counterByProperties(Map map) {
		try {
			String queryString = "select count(model.collectionId) as count from Collection as model where model.";
			Object[] keys = map.keySet().toArray();
			for(int i = 0;i<keys.length;i++){
				if(i==0){
					queryString += keys[i] + "= ?";
				}else{
					queryString +=" and " + keys[i] + "= ?";
				}
			}
			
			Query queryObject = getCurrentSession().createQuery(queryString);
			for(int i = 0;i<keys.length;i++){
				queryObject.setParameter(i,map.get( keys[i]));
			}
					
			return  ((Number) queryObject.iterate().next())
			         .intValue();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByProperties(Map requestMap,Integer page) {
		try {
//			String debugInfo= "finding Authentic instance with property: ";
			String queryString = "from Projectcomment as model where";
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
			queryObject.setFirstResult(page*Config.STRING_INVESTOR_LIST_MAX_SIZE);
			queryObject.setMaxResults(Config.STRING_INVESTOR_LIST_MAX_SIZE);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Projectcomment> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Projectcomment instances");
		try {
			String queryString = "from Projectcomment";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Projectcomment merge(Projectcomment detachedInstance) {
		log.debug("merging Projectcomment instance");
		try {
			Projectcomment result = (Projectcomment) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Projectcomment instance) {
		log.debug("attaching dirty Projectcomment instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Projectcomment instance) {
		log.debug("attaching clean Projectcomment instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProjectcommentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProjectcommentDAO) ctx.getBean("ProjectcommentDAO");
	}
}