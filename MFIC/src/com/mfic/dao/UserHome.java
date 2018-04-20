package com.mfic.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.mfic.data.User;



/**
 * Home object for domain model class User.
 * @see com.mfic.dao.User
 * @author Hibernate Tools
 */

public class UserHome extends BaseHome {

	private static final Log log = LogFactory.getLog(UserHome.class);

	/**
	 * Used to save or update a user.
	 */	
	public void saveOrUpdate(User user){
		log.debug("persisting User instance");
		try {
			if (user.getUid() == 0)
			{
				getSession().save(user);
			}else{
				getSession().update(user);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Used to update a user.
	 */	
	public void updateUserOnly(User user){
		log.debug("persisting User instance");
		try {
				getSession().update(user);
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	/**
	 * Used to delete a user.
	 */
	public void deleteUser(Long id)
	{
		log.debug("removing User instance");
		try{
		User user= (User) getSession().load(User.class, id);
		if (user.getUid() > 0)
		{
			getSession().delete(user);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}	

	/**
	 * Used to list all the users.
	 */
	@SuppressWarnings("unchecked")
	public List<User> listUser()
	{
		log.debug("List User instance");
		try
		{
			List<User> user=null;
			String SQL_QUERY ="select u from User u left join fetch u.institution";
			user = getSession().createQuery(SQL_QUERY).list();
			return user;
		}
		catch(RuntimeException re) {
			log.error("list all User failed", re);
			throw re;
		}
	}
	
	/**
	 * Used to list all the users.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> listBorrowerUserId()
	{
		log.debug("List User instance");
		try
		{
			String sqlQuery="select uid from brwr";
			List uids = getSession().createSQLQuery(sqlQuery).list();

			List<User> user=null;
			String SQL_QUERY ="select u.userid from User u  where u.uid in (:uid)";
			user = getSession().createQuery(SQL_QUERY)
			.setParameterList("uid", uids)
			.list();
			return user;
		}
		catch(RuntimeException re) {
			log.error("list all User failed", re);
			throw re;
		}
	}

	/**
	 * Used to list a single user by Id.
	 */
	public User findUserById(Long id) {
		log.debug("Find by User id");
		try {
			User user=null;
			user = (User) getSession().get(User.class, id);
			return user;
		}catch(RuntimeException re) {
			log.error("find User failed", re);
			throw re;
		}
	}
	
	/**
	 * Used to find user by email and password.
	 * @param email and password
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public User findUser(String email, String password) {
		log.debug("Find by email and password");
		try {
			char isActive = 'Y';
			Criteria criteria = getSession().createCriteria(User.class);
			criteria.createAlias("upwds", "up");
			criteria.createAlias("uroles", "ur");
			criteria.add(Restrictions.eq("email", email).ignoreCase());
			criteria.add(Restrictions.eq("up.pwd", password));
			criteria.add(Restrictions.eq("isactive", isActive));
			List<User> users = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			
			
			for (Iterator iditer = users.iterator(); iditer.hasNext();) 
			{    
				User record = (User) iditer.next();    
				log.debug("User " +record.getUrole().getRole().getRolename()+" logged on");
			}
			
			if(users == null || users.size() == 0){
				throw new RuntimeException("Invalid User Id or Password.");
			}if(users.size() >1){
				throw new RuntimeException("More than one user found.");
			}else{
				return users.get(0);
			}
		}catch(RuntimeException re) {
			log.error("find User failed", re);
			throw re;
		}
	}

	/**
	 * Used to list a single User by Email.
	 */
	public User findUserByEmail(String emailId) {
		try {
			User user=null;
			String SQL_QUERY ="select u from User u  where u.email = :email";
			user =  (User) getSession().createQuery(SQL_QUERY).setParameter("email", emailId).uniqueResult();
			return user;
		}catch(RuntimeException re) {
			log.error("find User failed", re);
			throw re;
		}
	}		

	/**
	 * Used to find user by userid and password.
	 * @param email and password
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public User findByUserId(String userid, String password) {
		log.debug("Find by userid and password");
		try {
			char isActive = 'Y';
			Criteria criteria = getSession().createCriteria(User.class);
			criteria.createAlias("upwds", "up");
			criteria.add(Restrictions.eq("userid", userid).ignoreCase());
			criteria.add(Restrictions.eq("up.pwd", password));
			criteria.add(Restrictions.eq("isactive", isActive));
			List<User> users = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			
			
			for (Iterator iditer = users.iterator(); iditer.hasNext();) 
			{    
				User record = (User) iditer.next();    
				log.info("User " +record.getUrole().getRole().getRolename()+" logged on");
			}
			
			if(users == null || users.size() == 0){
				throw new RuntimeException("Invalid User Id or Password.");
			}if(users.size() >1){
				throw new RuntimeException("More than one user found.");
			}else{
				return users.get(0);
			}
		}catch(RuntimeException re) {
			log.error("find User failed", re);
			throw re;
		}
	}


	/**
	 * find user by user Id.
	 */
	public User findUserByUserId(String userid) {
		log.debug("Find user by User id");
		try {
			User user=null;
			String SQL_QUERY ="select u from User u  where u.userid = :userId";
			user =  (User) getSession().createQuery(SQL_QUERY).setParameter("userId", userid).uniqueResult();
			return user;
		}catch(RuntimeException re) {
			log.error("find User failed", re);
			throw re;
		}
	}

	/*
	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(User persistentInstance) {
		log.debug("removing User instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(long id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = entityManager.find(User.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
*/

}
