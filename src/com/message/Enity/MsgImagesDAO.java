package com.message.Enity;

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

/**
 	* A data access object (DAO) providing persistence and search support for MsgImages entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.message.Enity.MsgImages
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class MsgImagesDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MsgImagesDAO.class);
		//property constants
	public static final String URL = "url";



    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
       this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession(){
     return sessionFactory.getCurrentSession(); 
    }
	protected void initDao() {
		//do nothing
	}
    
    public void save(MsgImages transientInstance) {
        log.debug("saving MsgImages instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MsgImages persistentInstance) {
        log.debug("deleting MsgImages instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MsgImages findById( java.lang.Integer id) {
        log.debug("getting MsgImages instance with id: " + id);
        try {
            MsgImages instance = (MsgImages) getCurrentSession()
                    .get("com.message.Enity.MsgImages", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<MsgImages> findByExample(MsgImages instance) {
        log.debug("finding MsgImages instance by example");
        try {
            List<MsgImages> results = (List<MsgImages>) getCurrentSession() .createCriteria("com.message.Enity.MsgImages").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding MsgImages instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MsgImages as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<MsgImages> findByUrl(Object url
	) {
		return findByProperty(URL, url
		);
	}
	

	public List findAll() {
		log.debug("finding all MsgImages instances");
		try {
			String queryString = "from MsgImages";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MsgImages merge(MsgImages detachedInstance) {
        log.debug("merging MsgImages instance");
        try {
            MsgImages result = (MsgImages) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MsgImages instance) {
        log.debug("attaching dirty MsgImages instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MsgImages instance) {
        log.debug("attaching clean MsgImages instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MsgImagesDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MsgImagesDAO) ctx.getBean("MsgImagesDAO");
	}
}