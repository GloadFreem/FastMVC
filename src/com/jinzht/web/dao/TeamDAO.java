package com.jinzht.web.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.jinzht.web.entity.Team;

/**
 * A data access object (DAO) providing persistence and search support for Team
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.jinzht.web.entity.Team
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TeamDAO {
	private static final Logger log = LoggerFactory.getLogger(TeamDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String ICON = "icon";
	public static final String POSITION = "position";
	public static final String COMPANY = "company";
	public static final String ADDRESS = "address";
	public static final String INTRODUCE = "introduce";
	public static final String URL = "url";

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

	public void save(Team transientInstance) {
		log.debug("saving Team instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Team persistentInstance) {
		log.debug("deleting Team instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Team findById(java.lang.Integer id) {
		log.debug("getting Team instance with id: " + id);
		try {
			Team instance = (Team) getCurrentSession().get(
					"com.jinzht.web.hibernate.Team", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Team> findByExample(Team instance) {
		log.debug("finding Team instance by example");
		try {
			List<Team> results = (List<Team>) getCurrentSession()
					.createCriteria("com.jinzht.web.hibernate.Team")
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
		log.debug("finding Team instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Team as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Team> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Team> findByIcon(Object icon) {
		return findByProperty(ICON, icon);
	}

	public List<Team> findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List<Team> findByCompany(Object company) {
		return findByProperty(COMPANY, company);
	}

	public List<Team> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Team> findByIntroduce(Object introduce) {
		return findByProperty(INTRODUCE, introduce);
	}

	public List<Team> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all Team instances");
		try {
			String queryString = "from Team";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Team merge(Team detachedInstance) {
		log.debug("merging Team instance");
		try {
			Team result = (Team) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Team instance) {
		log.debug("attaching dirty Team instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Team instance) {
		log.debug("attaching clean Team instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TeamDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TeamDAO) ctx.getBean("TeamDAO");
	}
}