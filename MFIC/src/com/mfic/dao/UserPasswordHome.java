package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mfic.data.UserPassword;

/**
 * Home object for domain model class Upwd.
 * @see com.mfic.dao.Upwd
 * @author Hibernate Tools
 */

public class UserPasswordHome extends BaseHome {

	private static final Log log = LogFactory.getLog(UserPasswordHome.class);

	
	/**
	 * Used to save or update a user passwords.
	 */	
	public void saveOrUpdate(UserPassword userPassword, boolean isInsert){
		log.debug("persisting UserPassword instance");
		try {
				if(isInsert){
					getSession().save(userPassword);
				}else {
					getSession().update(userPassword);
				}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void updatepwd(UserPassword userPassword){
		log.debug("persisting UserPassword instance");
		try {
				getSession().update(userPassword);
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Used to list a single UserPassword by Id.
	 */
	public UserPassword findUserPasswordById(long uid) {
		try {
			UserPassword userPassword=null;
			String SQL_QUERY ="select up from UserPassword up left join fetch up.user where up.user.uid = :userId";
			userPassword = (UserPassword) getSession().createQuery(SQL_QUERY).setParameter("userId", uid).uniqueResult();
			return userPassword;
		}catch(RuntimeException re) {
			log.error("find LoanStatus failed", re);
			throw re;
		}
	}	
	
	/*
	public void persist(UserPassword transientInstance) {
		log.debug("persisting UserPassword instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserPassword persistentInstance) {
		log.debug("removing UserPassword instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserPassword merge(UserPassword detachedInstance) {
		log.debug("merging UserPassword instance");
		try {
			UserPassword result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserPassword findById(UserPasswordId id) {
		log.debug("getting UserPassword instance with id: " + id);
		try {
			UserPassword instance = entityManager.find(UserPassword.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	*/
}
