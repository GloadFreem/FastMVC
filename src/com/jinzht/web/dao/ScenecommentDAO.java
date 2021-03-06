package com.jinzht.web.dao;
// default package

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
import com.jinzht.web.entity.Scenecomment;

/**
 * A data access object (DAO) providing persistence and search support for
 * Scenecomment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Scenecomment
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ScenecommentDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ScenecommentDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String ISVALID = "isvalid";

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

	public void save(Scenecomment transientInstance) {
		log.debug("saving Scenecomment instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Scenecomment persistentInstance) {
		log.debug("deleting Scenecomment instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Scenecomment findById(java.lang.Integer id) {
		log.debug("getting Scenecomment instance with id: " + id);
		try {
			Scenecomment instance = (Scenecomment) getCurrentSession().get(
					"com.jinzht.web.Scenecomment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByPropertiesWithPage(Map requestMap,Integer page) {
		try {
//			String debugInfo= "finding Loginfailrecord instance with property: ";
			String queryString = "from Scenecomment as model where";
			Object[] keys= requestMap.keySet().toArray();
			for(int i = 0;i<requestMap.size();i++){
//				debugInfo += keys[i].toString()+requestMap.get(keys[i]);
				if(i==0){
					queryString+=" model."+keys[i].toString()+" =? ";
				}else{
					queryString+="and model."+keys[i].toString()+" =? ";
				}
			}
			
			queryString+=" order by model.commentId desc";
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

	public List<Scenecomment> findByExample(Scenecomment instance) {
		log.debug("finding Scenecomment instance by example");
		try {
			List<Scenecomment> results = (List<Scenecomment>) getCurrentSession()
					.createCriteria("Scenecomment").add(create(instance))
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
		log.debug("finding Scenecomment instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Scenecomment as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Scenecomment> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Scenecomment> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all Scenecomment instances");
		try {
			String queryString = "from Scenecomment";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Scenecomment merge(Scenecomment detachedInstance) {
		log.debug("merging Scenecomment instance");
		try {
			Scenecomment result = (Scenecomment) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Scenecomment instance) {
		log.debug("attaching dirty Scenecomment instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Scenecomment instance) {
		log.debug("attaching clean Scenecomment instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ScenecommentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ScenecommentDAO) ctx.getBean("ScenecommentDAO");
	}
}