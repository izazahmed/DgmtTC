/**
 * Created Date: 4/15/10 7:22 PM
 * Class Name  : AbstractSpringDao.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:22 PM
 * @author CTE
 * @since DGMT 1.0
 */
public abstract class AbstractSpringDao extends HibernateDaoSupport
{
	/**
	 * Creates a new AbstractSpringDao object.
	 */
	public AbstractSpringDao()
	{
	}

	/**
	 * @param obj
	 */
	protected void saveOrUpdate(Object obj)
	{
		getHibernateTemplate().saveOrUpdate(obj);
	}

	/**
	 * @param obj
	 */
	protected void delete(Object obj)
	{
		obj = getHibernateTemplate().merge(obj);
		getHibernateTemplate().delete(obj);
	}

	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	protected Object find(Class clazz, Long id)
	{
		return getHibernateTemplate().load(clazz, id);
	}

	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	protected Object findGet(Class clazz, Long id)
	{
		return getHibernateTemplate().get(clazz, id);
	}

	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	protected Object findGet(Class clazz, String id)
	{
		return getHibernateTemplate().get(clazz, id);
	}

	/**
	 * @param clazz
	 * @return
	 */
	protected List findAll(Class clazz)
	{
		return getHibernateTemplate().find("from " + clazz.getName());
	}

	/**
	 * @param clazz
	 * @return
	 */
	protected Object findGet(Class clazz)
	{
		return getHibernateTemplate().find("from " + clazz.getName());
	}

	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	protected Object get(Class clazz, Long id)
	{
		return getHibernateTemplate().get(clazz, id);
	}
}
