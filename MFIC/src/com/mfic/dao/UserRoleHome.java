package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mfic.data.UserRole;

/**
 * Home object for domain model class Urole.
 * @see com.mfic.dao.Urole
 * @author Hibernate Tools
 */
public class UserRoleHome  extends BaseHome {

	private static final Log log = LogFactory.getLog(UserRoleHome.class);


	/**
	 * Used to save or update a user role.
	 */	
	public void saveOrUpdate(UserRole uRole, boolean isInsert){
		log.debug("persisting user role instance");
		try {
				if(isInsert){
					getSession().save(uRole);
				}else {
					getSession().update(uRole);
				}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
		
	/**
	 * Used to update a user role.
	 */	
	public void update(UserRole uRole){
		log.debug("persisting user role instance");
		try {
			String sqlQuery="update urole set rid="+uRole.getRole().getRid()+" where uid="+uRole.getUser().getUid()+"";
			getSession().createSQLQuery(sqlQuery).executeUpdate();
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Used to list all the user roles.
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> listUserRole()
	{
		log.debug("List User role instance");
		try
		{
			List<UserRole> urole=null;
			urole = getSession().createQuery("from UserRole").list();
			return urole;
		}
		catch(RuntimeException re) {
			log.error("list all User role failed", re);
			throw re;
		}
		
		
	}	
/*	
	private EntityManager entityManager;

	public void persist(UserRole transientInstance) {
		log.debug("persisting UserRole instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserRole persistentInstance) {
		log.debug("removing UserRole instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserRole merge(UserRole detachedInstance) {
		log.debug("merging UserRole instance");
		try {
			UserRole result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserRole findById(UserRoleId id) {
		log.debug("getting UserRole instance with id: " + id);
		try {
			UserRole instance = entityManager.find(UserRole.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
*/	
}
