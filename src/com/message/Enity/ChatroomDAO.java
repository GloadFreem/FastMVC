package com.message.Enity;

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

import com.jinzht.web.entity.BusinessSchool;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Project;

/**
 * A data access object (DAO) providing persistence and search support for
 * Chatroom entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.message.Enity.Chatroom
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ChatroomDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ChatroomDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String MAXUSERS = "maxusers";
	public static final String AFFILIATIONS_COUNT = "affiliationsCount";
	public static final String OWNER = "owner";
	public static final String CODE = "code";
	public static final String EXT = "ext";
	public static final String TYPE_ID = "typeId";

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

	public void save(Chatroom transientInstance) {
		log.debug("saving Chatroom instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Chatroom transientInstance) {
		log.debug("saving Chatroom instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Chatroom persistentInstance) {
		log.debug("deleting Chatroom instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Chatroom findById(java.lang.Integer id) {
		log.debug("getting Chatroom instance with id: " + id);
		try {
			Chatroom instance = (Chatroom) getCurrentSession().get(
					"com.message.Enity.Chatroom", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Chatroom> findByExample(Chatroom instance) {
		log.debug("finding Chatroom instance by example");
		try {
			List<Chatroom> results = (List<Chatroom>) getCurrentSession()
					.createCriteria("com.message.Enity.Chatroom")
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
		log.debug("finding Chatroom instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Chatroom as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Chatroom> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Chatroom> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Chatroom> findByMaxusers(Object maxusers) {
		return findByProperty(MAXUSERS, maxusers);
	}

	public List<Chatroom> findByAffiliationsCount(Object affiliationsCount) {
		return findByProperty(AFFILIATIONS_COUNT, affiliationsCount);
	}

	public List<Chatroom> findByOwner(Object owner) {
		return findByProperty(OWNER, owner);
	}

	public List<Chatroom> findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List<Chatroom> findByExt(Object ext) {
		return findByProperty(EXT, ext);
	}

	public List<Chatroom> findByTypeId(Object typeId) {
		return findByProperty(TYPE_ID, typeId);
	}

	public List findAll() {
		log.debug("finding all Chatroom instances");
		try {
			String queryString = "from Chatroom";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPage(Integer page,Integer size) {
		log.debug("finding all Chatroom instances");
		try {
			String queryString = "from Chatroom order by createDate desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(page*size);
			queryObject.setMaxResults(size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer countOfAllUsers() {
		log.debug("finding all Chatroom instances");
		try {
			String queryString = "select count(*) from chatroom order by  create_date desc";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	public List<Project> findByKeyName(Object name) {
		String sqlString = "select * from chatroom where name like ?";
		
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString).addEntity(Chatroom.class);
		queryObject.setParameter(0, "%"+name+"%");
		
		return queryObject.list();
	}

	public Chatroom merge(Chatroom detachedInstance) {
		log.debug("merging Chatroom instance");
		try {
			Chatroom result = (Chatroom) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Chatroom instance) {
		log.debug("attaching dirty Chatroom instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Chatroom instance) {
		log.debug("attaching clean Chatroom instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ChatroomDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ChatroomDAO) ctx.getBean("ChatroomDAO");
	}
}