package com.jinzht.stock.entity;

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
 * A data access object (DAO) providing persistence and search support for
 * SanCompany entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jinzht.stock.entity.SanCompany
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SanCompanyDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SanCompanyDAO.class);
	// property constants
	public static final String _CNAME = "CName";
	public static final String _CCODE = "CCode";
	public static final String _CADDRESS = "CAddress";
	public static final String _CTYPE = "CType";
	public static final String _CCREATE_DATE = "CCreateDate";
	public static final String _CCORPORATE = "CCorporate";
	public static final String _CSECRETARY = "CSecretary";
	public static final String _CTEL = "CTel";
	public static final String SHARES_NUM = "sharesNum";
	public static final String SECURITIES_TRADER = "securitiesTrader";
	public static final String ASSIGNMENT = "assignment";
	public static final String _CDESC = "CDesc";
	public static final String SHARES_TYPE = "sharesType";
	public static final String _CTEST = "CTest";
	public static final String SAN_COMPANYCOL = "sanCompanycol";
	public static final String _CFULLNAME = "CFullname";

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
	
	

	
	public int getCountByType(Object sharesType) {
		return getCout(SHARES_TYPE, sharesType);
	}
	
	
	public int getCout(String propertyName, Object value) {
		log.debug("finding SanCompany instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "select count(*)  from san_company as model where model.shares_type= ?";
			Query queryObject = getCurrentSession().createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			int count = 0;
			if(null != list && list.size() > 0){
			count = Integer.parseInt(list.get(0).toString());
			}
			return count;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public void save(SanCompany transientInstance) {
		log.debug("saving SanCompany instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SanCompany persistentInstance) {
		log.debug("deleting SanCompany instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public void deleteByPage(Integer page,Integer size) {
		try {
			String sqlString ="delete from SanCompany";
			Query queryObject = getCurrentSession().createQuery(sqlString);
			queryObject.setFirstResult(page*size);
			queryObject.setMaxResults(size);
			
			queryObject.executeUpdate();
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SanCompany findById(java.lang.Integer id) {
		log.debug("getting SanCompany instance with id: " + id);
		try {
			SanCompany instance = (SanCompany) getCurrentSession().get(
					"com.jinzht.stock.entity.SanCompany", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SanCompany> findByExample(SanCompany instance) {
		log.debug("finding SanCompany instance by example");
		try {
			List<SanCompany> results = (List<SanCompany>) getCurrentSession()
					.createCriteria("com.jinzht.stock.entity.SanCompany")
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
		log.debug("finding SanCompany instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SanCompany as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SanCompany> findByCName(Object CName) {
		return findByProperty(_CNAME, CName);
	}

	public List<SanCompany> findByCCode(Object CCode) {
		return findByProperty(_CCODE, CCode);
	}

	public List<SanCompany> findByCAddress(Object CAddress) {
		return findByProperty(_CADDRESS, CAddress);
	}

	public List<SanCompany> findByCType(Object CType) {
		return findByProperty(_CTYPE, CType);
	}

	public List<SanCompany> findByCCreateDate(Object CCreateDate) {
		return findByProperty(_CCREATE_DATE, CCreateDate);
	}

	public List<SanCompany> findByCCorporate(Object CCorporate) {
		return findByProperty(_CCORPORATE, CCorporate);
	}

	public List<SanCompany> findByCSecretary(Object CSecretary) {
		return findByProperty(_CSECRETARY, CSecretary);
	}

	public List<SanCompany> findByCTel(Object CTel) {
		return findByProperty(_CTEL, CTel);
	}

	public List<SanCompany> findBySharesNum(Object sharesNum) {
		return findByProperty(SHARES_NUM, sharesNum);
	}

	public List<SanCompany> findBySecuritiesTrader(Object securitiesTrader) {
		return findByProperty(SECURITIES_TRADER, securitiesTrader);
	}

	public List<SanCompany> findByAssignment(Object assignment) {
		return findByProperty(ASSIGNMENT, assignment);
	}

	public List<SanCompany> findByCDesc(Object CDesc) {
		return findByProperty(_CDESC, CDesc);
	}

	public List<SanCompany> findBySharesType(Object sharesType) {
		return findByProperty(SHARES_TYPE, sharesType);
	}

	public List<SanCompany> findByCTest(Object CTest) {
		return findByProperty(_CTEST, CTest);
	}

	public List<SanCompany> findBySanCompanycol(Object sanCompanycol) {
		return findByProperty(SAN_COMPANYCOL, sanCompanycol);
	}

	public List<SanCompany> findByCFullname(Object CFullname) {
		return findByProperty(_CFULLNAME, CFullname);
	}

	public List findAll() {
		log.debug("finding all SanCompany instances");
		try {
			String queryString = "from SanCompany";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPage(Integer page,Integer size) {
		log.debug("finding all SanCompany instances");
		try {
			String queryString = "from SanCompany order by CCreateDate desc";
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
		log.debug("finding all SanCompany instances");
		try {
			String queryString = "select count(*) from san_company order by  c_createDate desc";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			return Integer.parseInt((queryObject.list().get(0).toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SanCompany merge(SanCompany detachedInstance) {
		log.debug("merging SanCompany instance");
		try {
			SanCompany result = (SanCompany) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SanCompany instance) {
		log.debug("attaching dirty SanCompany instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SanCompany instance) {
		log.debug("attaching clean SanCompany instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SanCompanyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SanCompanyDAO) ctx.getBean("SanCompanyDAO");
	}
	
	

	public List findListByPage(Integer page) {
		// TODO Auto-generated method stub
		log.debug("finding all Msg instances");
		try {
			String queryString = "from SanCompany order by CCreateDate desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setFirstResult(page* 10);
			queryObject.setMaxResults(10);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
//		return null;
	}
	
	
	public List<SanCompany> findListBySearchPage(String search, 
			String type, Integer page) {
		// TODO Auto-generated method stub
		String queryString;
		Query q;

				log.debug("finding all Msg instances");
				try {
					if(search==null){
						 queryString = "from SanCompany san order by san.CCreateDate desc";
							 q = getCurrentSession().createQuery(queryString);
					}else{
						 queryString = "from SanCompany san where san.CName like :sname and san.sharesType="+type+"  or san.CCode like :scode and san.sharesType="+type+"  order  by san.CCreateDate desc";
						 q = getCurrentSession().createQuery(queryString);
						 q.setParameter("sname", "%"+ search+"%" );
						 q.setParameter("scode", "%"+ search+"%" );
					}
					q.setFirstResult(page* 10);
					q.setMaxResults(10);
					return q.list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
				}
	}
	

	public void deleteAll() {
		log.debug("delete all SanCompany");
		try {
			String queryString = "delete from san_asset where 1=1 ";
			getCurrentSession().createSQLQuery(queryString).executeUpdate();

			String queryString2 = "delete from san_income where 1=1 ";
			getCurrentSession().createSQLQuery(queryString2).executeUpdate();

			String queryString3 = "delete from san_liabilities where 1=1 ";
			getCurrentSession().createSQLQuery(queryString3).executeUpdate();

			String queryString4 = "delete from san_managementer where 1=1 ";
			getCurrentSession().createSQLQuery(queryString4).executeUpdate();

			String queryString5 = "delete from san_profit where 1=1 ";
			getCurrentSession().createSQLQuery(queryString5).executeUpdate();

			String queryString6 = "delete from san_shareholder where 1=1 ";
			getCurrentSession().createSQLQuery(queryString6).executeUpdate();

			String queryString7 = "delete from san_company where 1=1 ";
			getCurrentSession().createSQLQuery(queryString7).executeUpdate();
			
		} catch (RuntimeException re) {
			log.error("delete all failed", re);
			throw re;
		}
	}

}