package com.message.Enity;

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

/**
 * A data access object (DAO) providing persistence and search support for Msg
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.message.Enity.Msg
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class MsgDAO {
	private static final Logger log = LoggerFactory.getLogger(MsgDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String IMAGE = "image";
	public static final String ORINGL = "oringl";
	public static final String TAB = "tab";
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

	public void save(Msg transientInstance) {
		log.debug("saving Msg instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Msg persistentInstance) {
		log.debug("deleting Msg instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Msg findById(java.lang.Integer id) {
		log.debug("getting Msg instance with id: " + id);
		try {
			Msg instance = (Msg) getCurrentSession().get(
					"com.message.Enity.Msg", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Msg> findByExample(Msg instance) {
		log.debug("finding Msg instance by example");
		try {
			List<Msg> results = (List<Msg>) getCurrentSession()
					.createCriteria("com.message.Enity.Msg")
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
		log.debug("finding Msg instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Msg as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Msg> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Msg> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List<Msg> findByOringl(Object oringl) {
		return findByProperty(ORINGL, oringl);
	}

	public List<Msg> findByTab(Object tab) {
		return findByProperty(TAB, tab);
	}

	public List<Msg> findByHot(Object hot) {
		return findByProperty(HOT, hot);
	}

	public List findAll() {
		log.debug("finding all Msg instances");
		try {
			String queryString = "from Msg";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Msg merge(Msg detachedInstance) {
		log.debug("merging Msg instance");
		try {
			Msg result = (Msg) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Msg instance) {
		log.debug("attaching dirty Msg instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Msg instance) {
		log.debug("attaching clean Msg instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	public Integer countOfAllRecords() {
		log.debug("finding all Msg instances");
		try {
			String queryString = "select count(*) from msg";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPage(Integer start,Integer size) {
		log.debug("finding all Msg instances");
		try {
			String queryString = "from Msg order by publicDate desc";
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
		log.debug("finding all Msg instances");
		try {
			String queryString = "select count(*) from msg";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void saveOrUpdate(Msg transientInstance) {
		log.debug("saving Msg instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	

	public List<Msg> findByName(Object name) {
		String sqlString = "select * from msg where title like ?";
		
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString).addEntity(Msg.class);
		queryObject.setParameter(0, "%"+name+"%");
		
		return queryObject.list();
	}

	public static MsgDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MsgDAO) ctx.getBean("MsgDAO");
	}
	
	

	public List<Msg> findListBySearch(int pageId, int pageNum, String likeWords) {
		String queryString;
		Query q ;
		
		try {
		if(likeWords==null){
			 queryString = "from Msg msg order by msg.publicDate desc";
				 q = getCurrentSession().createQuery(queryString);
		}else{
			 queryString = "from Msg msg where msg.title like :stitle order by msg.publicDate desc";
			 q = getCurrentSession().createQuery(queryString);
			 q.setParameter("stitle", "%"+ likeWords+"%" );
		}
			q.setFirstResult(pageId*pageNum);
			q.setMaxResults(pageNum);
			return q.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	
	}
    
	/**
	 * 
	 * @return
	 */
	public List<Msg> findHotMsg() {
		String queryString;
		Query q ;
		try {
			 queryString = "from Msg msg order by msg.hot desc";
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