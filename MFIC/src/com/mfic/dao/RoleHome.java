package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mfic.data.Role;
import com.mfic.data.UserRole;


/**
 * Home object for domain model class Role.
 * @see com.mfic.dao.Role
 * @author Hibernate Tools
 */
public class RoleHome  extends BaseHome {

	private static final Log log = LogFactory.getLog(RoleHome.class);

	
	/**
	 * Used to save or update a role.
	 */	
	public void saveOrUpdate(Role role, boolean isInsert){
		log.debug("persisting role instance");
		try {
				if(isInsert){
					getSession().save(role);
				}else {
					getSession().update(role);
				}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	
	/**
	 * Used to list all the Role.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Role> listRole()
	{
		log.debug("List Role instance");
		try
		{
			List<Role> role=null;
			String SQL_QUERY ="Select r.description from Role r";
			role = getSession().createQuery(SQL_QUERY).list();
			return role;
		}
		catch(RuntimeException re) {
			log.error("list all Role failed", re);
			throw re;
		}
	}		
	
	/**
	 * Used to list all the Role.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Role> listAllRole()
	{
		log.debug("List Role instance");
		try
		{
			List<Role> role=null;
			String SQL_QUERY ="Select r from Role r";
			role = getSession().createQuery(SQL_QUERY).list();
			return role;
		}
		catch(RuntimeException re) {
			log.error("list all Role failed", re);
			throw re;
		}
	}		
	
	/**
	 * Used to list a single Role by Description.
	 */
	public Role findRoleByDesc(String descrp) {
		try {
			Role role=null;
			String SQL_QUERY ="select r from Role r  where r.description = :desc";
			role = (Role) getSession().createQuery(SQL_QUERY).setParameter("desc", descrp).uniqueResult();
			return role;
		}catch(RuntimeException re) {
			log.error("find Role failed", re);
			throw re;
		}
	}		
	
	
	/**
	 * Used to list a single UserRole by Id.
	 */
	public Role findRoleByUserId(long uid) {
		try {
			UserRole userRole=null;
			String SQL_QUERY ="select ur from UserRole ur left join fetch ur.user where ur.user.uid = :userId";
			userRole =  (UserRole) getSession().createQuery(SQL_QUERY).setParameter("userId", uid).uniqueResult();

			int roleid = userRole.getRole().getRid();
			String SQL_QUERY1 ="select r from Role r  where r.rid = :roleId";
			Role role = (Role) getSession().createQuery(SQL_QUERY1).setParameter("roleId", roleid).uniqueResult();
			return role;
		}catch(RuntimeException re) {
			log.error("find UserRole failed", re);
			throw re;
		}
	}		
	
	/**
	 * Used to list a single UserRole by Id.
	 */
	public UserRole findUserRoleByUserId(long uid) {
		try {
			UserRole userRole=null;
			String SQL_QUERY ="select ur from UserRole ur left join fetch ur.user where ur.user.uid = :userId";
			userRole =  (UserRole) getSession().createQuery(SQL_QUERY).setParameter("userId", uid).uniqueResult();

			return userRole;
		}catch(RuntimeException re) {
			log.error("find UserRole failed", re);
			throw re;
		}
	}		
		
	
	
/*	
	private EntityManager entityManager;

	public void persist(Role transientInstance) {
		log.debug("persisting Role instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Role persistentInstance) {
		log.debug("removing Role instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Role merge(Role detachedInstance) {
		log.debug("merging Role instance");
		try {
			Role result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Role findById(int id) {
		log.debug("getting Role instance with id: " + id);
		try {
			Role instance = entityManager.find(Role.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
*/	
}
