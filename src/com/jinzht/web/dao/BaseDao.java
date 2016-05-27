package com.jinzht.web.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

public class BaseDao<T> {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public Class entityClass;

	public BaseDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public T get(Serializable id)
	{
		return (T)getHibernateTemplate().get(entityClass, id);
	}
	
	public void save(T entity)
	{
		hibernateTemplate.save(entity);
	}
	
	public void update(T entity){
		hibernateTemplate.update(entity);
	}
	
	
}
