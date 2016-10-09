package com.jinzht.web.dao;

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

import com.jinzht.web.entity.Member;

/**
 * A data access object (DAO) providing persistence and search support for
 * Member entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.web.entity.Member
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class MemberDAO {
	private static final Logger log = LoggerFactory.getLogger(MemberDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String COMPANY = "company";
	public static final String POSITION = "position";
	public static final String ADDRESS = "address";
	public static final String INDUSTORY = "industory";
	public static final String EMIAL = "emial";

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

	public void save(Member transientInstance) {
		log.debug("saving Member instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Member transientInstance) {
		log.debug("saving Member instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Member persistentInstance) {
		log.debug("deleting Member instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Member findById(java.lang.Integer id) {
		log.debug("getting Member instance with id: " + id);
		try {
			Member instance = (Member) getCurrentSession().get(
					"com.jinzht.web.entity.Member", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Member> findByExample(Member instance) {
		log.debug("finding Member instance by example");
		try {
			List<Member> results = (List<Member>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Member")
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
		log.debug("finding Member instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Member as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Member> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Member> findByCompany(Object company) {
		return findByProperty(COMPANY, company);
	}

	public List<Member> findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List<Member> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Member> findByIndustory(Object industory) {
		return findByProperty(INDUSTORY, industory);
	}

	public List<Member> findByEmial(Object emial) {
		return findByProperty(EMIAL, emial);
	}

	public List findAll() {
		log.debug("finding all Member instances");
		try {
			String queryString = "from Member";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByPage(Integer start,Integer size) {
		log.debug("finding all Member instances");
		try {
			String queryString = "from Member";
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
		log.debug("finding all Member instances");
		try {
			String queryString = "select count(*) from member";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Member merge(Member detachedInstance) {
		log.debug("merging Member instance");
		try {
			Member result = (Member) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Member instance) {
		log.debug("attaching dirty Member instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Member instance) {
		log.debug("attaching clean Member instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MemberDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MemberDAO) ctx.getBean("MemberDAO");
	}
}