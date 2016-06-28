package com.jinzht.web.dao;
// default package

import java.util.List;
import java.util.Map;
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
import com.jinzht.web.entity.Authentic;

/**
 * A data access object (DAO) providing persistence and search support for
 * Authentic entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Authentic
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AuthenticDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AuthenticDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String IDENTIY_CAR_A = "identiyCarA";
	public static final String IDENTIY_CAR_B = "identiyCarB";
	public static final String IDENTIY_CAR_NO = "identiyCarNo";
	public static final String COMPANY_NAME = "companyName";
	public static final String COMPANY_ADDRESS = "companyAddress";
	public static final String POSITION = "position";
	public static final String BUINESS_LICENCE = "buinessLicence";
	public static final String BUINESS_LICENCE_NO = "buinessLicenceNo";
	public static final String INTRODUCE = "introduce";
	public static final String COMPANY_INTRODUCE = "companyIntroduce";
	public static final String OPTIONAL = "optional";

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

	public void save(Authentic transientInstance) {
		log.debug("saving Authentic instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Authentic persistentInstance) {
		log.debug("deleting Authentic instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Authentic findById(java.lang.Integer id) {
		log.debug("getting Authentic instance with id: " + id);
		try {
			Authentic instance = (Authentic) getCurrentSession().get(
					"Authentic", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Authentic> findByExample(Authentic instance) {
		log.debug("finding Authentic instance by example");
		try {
			List<Authentic> results = (List<Authentic>) getCurrentSession()
					.createCriteria("Authentic").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Authentic instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Authentic as model where model."
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
			String queryString = "from Authentic as model where";
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
	

	public List<Authentic> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Authentic> findByIdentiyCarA(Object identiyCarA) {
		return findByProperty(IDENTIY_CAR_A, identiyCarA);
	}

	public List<Authentic> findByIdentiyCarB(Object identiyCarB) {
		return findByProperty(IDENTIY_CAR_B, identiyCarB);
	}

	public List<Authentic> findByIdentiyCarNo(Object identiyCarNo) {
		return findByProperty(IDENTIY_CAR_NO, identiyCarNo);
	}

	public List<Authentic> findByCompanyName(Object companyName) {
		return findByProperty(COMPANY_NAME, companyName);
	}

	public List<Authentic> findByCompanyAddress(Object companyAddress) {
		return findByProperty(COMPANY_ADDRESS, companyAddress);
	}

	public List<Authentic> findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List<Authentic> findByBuinessLicence(Object buinessLicence) {
		return findByProperty(BUINESS_LICENCE, buinessLicence);
	}

	public List<Authentic> findByBuinessLicenceNo(Object buinessLicenceNo) {
		return findByProperty(BUINESS_LICENCE_NO, buinessLicenceNo);
	}

	public List<Authentic> findByIntroduce(Object introduce) {
		return findByProperty(INTRODUCE, introduce);
	}

	public List<Authentic> findByCompanyIntroduce(Object companyIntroduce) {
		return findByProperty(COMPANY_INTRODUCE, companyIntroduce);
	}

	public List<Authentic> findByOptional(Object optional) {
		return findByProperty(OPTIONAL, optional);
	}

	public List findAll() {
		log.debug("finding all Authentic instances");
		try {
			String queryString = "from Authentic";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAuthenticByUserId(Integer userId)
	{
		String sqlString = "select * from authentic where user_id=?";
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString).addEntity(Authentic.class);
		queryObject.setParameter(0, userId);
		
		return queryObject.list();
	}

	public Authentic merge(Authentic detachedInstance) {
		log.debug("merging Authentic instance");
		try {
			Authentic result = (Authentic) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Authentic instance) {
		log.debug("attaching dirty Authentic instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Authentic instance) {
		log.debug("attaching clean Authentic instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 扩展方法
	 * 根据authId获取用户id
	 */
	public Integer findUserIdByAuthId(Integer authId)
	{
		try {
			String queryString = "select user_id from authentic where auth_id =?";
			
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			queryObject.setParameter(0, authId);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public static AuthenticDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AuthenticDAO) ctx.getBean("AuthenticDAO");
	}
}