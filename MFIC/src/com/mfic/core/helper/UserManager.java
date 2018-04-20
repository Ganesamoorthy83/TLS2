package com.mfic.core.helper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;


import com.mfic.dao.UserHome;
import com.mfic.dao.UserPasswordHome;
import com.mfic.dao.UserRoleHome;

import com.mfic.data.User;
import com.mfic.data.UserPassword;
import com.mfic.data.UserRole;


public class UserManager
{
	private static final Log log = LogFactory.getLog(UserManager.class);
	UserHome userHome = new UserHome();
	UserPasswordHome userPasswordHome = new UserPasswordHome();
	UserRoleHome userRoleHome = new UserRoleHome();
	
	/**
	 * Used to save or update a user.
	 */
	public void saveOrUpdateUser(User user)
	{
		log.debug("save Or Updating User instance");
		Session session = UserHome.getSession();
		try{
		session.beginTransaction();
		UserPassword up = user.getUpwd();
		UserRole role = user.getUrole();
		boolean isInsert = (user.getUid()==0);
		userHome.saveOrUpdate(user);
		
		if(isInsert){
			up.getId().setUid(user.getUid());
			role.getId().setUid(user.getUid());
			savePassword(up);
			saveRole(role);
		}

		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}


	/**
	 * Used to save user password.
	 */	
	public void savePassword(UserPassword userPassword){
		userPasswordHome.saveOrUpdate(userPassword, true);
	}
	
	/**
	 * Used to update user password.
	 */	
	public void updatePassword(UserPassword userPassword){
		userPasswordHome.saveOrUpdate(userPassword, false);
	}

	/**
	 * Used to update user password.
	 */	
	public void updateUserPassword(UserPassword userPassword){
		log.debug("Updating User password instance");
		Session session = UserHome.getSession();
		try{
		session.beginTransaction();
		userPasswordHome.saveOrUpdate(userPassword, false);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("updatePassword failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to update user password.
	 */	
	public void updateUserOnly(User user){
		log.debug("Updating User password instance");
		Session session = UserHome.getSession();
		try{
		session.beginTransaction();
		userHome.updateUserOnly(user);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("updatePassword failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to update user password.
	 */	
	public void updateUserRole(UserRole userRole){
		log.debug("Updating User password instance");
		Session session = UserRoleHome.getSession();
		try{
		session.beginTransaction();
		userRoleHome.update(userRole);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("updatePassword failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
	
	
	/**
	 * Used to save user role.
	 */	
	public void saveRole(UserRole role){
		userRoleHome.saveOrUpdate(role, true);
	}
	
	/**
	 * Used to update user role.
	 */	
	public void updateRole(UserRole role){
		userRoleHome.saveOrUpdate(role, false);
	}
	

	/**
	 * Used to delete a user.
	 */
	public void deleteUser(Long id)
	{
		log.debug("delete User");
		Session session = UserHome.getSession();
		try{
		session.beginTransaction();
		userHome.deleteUser(id);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
	
	/**
	 * Used to list all the users.
	 */
	public List<User> listUser()
	{
		log.debug("list all User");
		Session session = UserHome.getSession();
		List<User> userList=null;
		try
		{
		session.beginTransaction();
		userList = userHome.listUser();
		session.getTransaction().commit();
		return userList;
		}
		catch(RuntimeException re) {
			log.error("list all user failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
		
	}

	/**
	 * Used to list all the users by borrower.
	 */
	public List<User> listBorrowerUserId()
	{
		log.debug("list all User");
		Session session = UserHome.getSession();
		List<User> userList=null;
		try
		{
		session.beginTransaction();
		userList = userHome.listBorrowerUserId();
		session.getTransaction().commit();
		return userList;
		}
		catch(RuntimeException re) {
			log.error("list all user failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
		
	}	
	/**
	 * Used to list a single user by Id.
	 * @return 
	 */
	public User findUserById(Long userId) {
		log.debug("select single User");
		Session session = UserHome.getSession();
		User user=null;
		try
		{		
			session.beginTransaction();
			user=userHome.findUserById(userId);
			session.getTransaction().commit();
		return user;
		} catch(RuntimeException re) {
			log.error("select single user failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}


	/**
	 * Used to get a user email and password.
	 * @return User object
	 */
	public User getUser(String email, String password){
		Session session = UserHome.getSession();
		User user=null;
		try
		{		
			session.beginTransaction();
			user=userHome.findUser(email, password);
			session.getTransaction().commit();
		return user;
		} catch(RuntimeException re) {
			log.error("select single user by email id and  failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to find a user email.
	 * @return User object
	 */
	public User findUserByEmail(String email){
		Session session = UserHome.getSession();
		User user=null;
		try
		{		
			session.beginTransaction();
			user=userHome.findUserByEmail(email);
			session.getTransaction().commit();
		return user;
		} catch(RuntimeException re) {
			log.error("select single user by email id  failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
	
	/**
	 * Used to list a single user password by Id.
	 * @return 
	 */
	public UserPassword findUserPasswordById(Long userId) {
		log.debug("select single User");
		Session session = UserHome.getSession();
		UserPassword userPassword=null;
		try
		{		
			session.beginTransaction();
			userPassword=userPasswordHome.findUserPasswordById(userId);
			session.getTransaction().commit();
		return userPassword;
		} catch(RuntimeException re) {
			log.error("select single user password failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to get a user user id and password.
	 * @return User object
	 */
	public User findByUserId(String userid, String password){
		Session session = UserHome.getSession();
		User user=null;
		try
		{		
			session.beginTransaction();
			user=userHome.findByUserId(userid, password);
			session.getTransaction().commit();
		return user;
		} catch(RuntimeException re) {
			log.error("select single user by userId  and  failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to find a user by userId.
	 * @return User object
	 */
	public User findUserByUserId(String userid){
		Session session = UserHome.getSession();
		User user=null;
		try
		{		
			session.beginTransaction();
			user=userHome.findUserByUserId(userid);
			session.getTransaction().commit();
		return user;
		} catch(RuntimeException re) {
			log.error("select single user by userId  failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

}
