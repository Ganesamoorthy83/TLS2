package com.mfic.core.helper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.RoleHome;
import com.mfic.dao.UserRoleHome;
import com.mfic.data.Role;
import com.mfic.data.UserRole;

public class RoleManager {
	private static final Log log = LogFactory.getLog(RoleManager.class);
	RoleHome roleHome = new RoleHome();
	UserRoleHome userRoleHome = new UserRoleHome();
	
	/**
	 * Used to list all the Role.
	 */
	public List<Role> listRole()
	{
		log.debug("list all Role");
		Session session = RoleHome.getSession();
		List<Role> roleList=null;
		try
		{
		session.beginTransaction();
		roleList = roleHome.listRole();
		session.getTransaction().commit();
		return roleList;
		}
		catch(RuntimeException re) {
			log.error("list all Role failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	
	
	/**
	 * Used to list all the Role.
	 */
	public List<Role> listAllRole()
	{
		log.debug("list all Role");
		Session session = RoleHome.getSession();
		List<Role> roleList=null;
		try
		{
		session.beginTransaction();
		roleList = roleHome.listAllRole();
		session.getTransaction().commit();
		return roleList;
		}
		catch(RuntimeException re) {
			log.error("list all Role failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	/**
	 * Used to list a single Role by Description.
	 */
	public Role findRoleByDesc(String descrp) {
		log.debug("select single Institution by Name");
		Session session = RoleHome.getSession();
		Role role=null;
		try
		{
			session.beginTransaction();
			role=roleHome.findRoleByDesc(descrp);
			session.getTransaction().commit();
		return role;
		} catch(RuntimeException re) {
			log.error("select single Role by Description failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		

	/**
	 * Used to list all the Role.
	 */
	public List<UserRole> listUserRole()
	{
		log.debug("list all Role");
		Session session = RoleHome.getSession();
		List<UserRole> uroleList=null;
		try
		{
		session.beginTransaction();
		uroleList = userRoleHome.listUserRole();
		session.getTransaction().commit();
		return uroleList;
		}
		catch(RuntimeException re) {
			log.error("list all Role failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	/**
	 * Used to list a single user role by Id.
	 * @return 
	 */
	public Role findRoleByUserId(Long userId) {
		log.debug("select single User");
		Session session = RoleHome.getSession();
		Role role=null;
		try
		{		
			session.beginTransaction();
			role=roleHome.findRoleByUserId(userId);
			session.getTransaction().commit();
		return role;
		} catch(RuntimeException re) {
			log.error("select single user role failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to list a single user role by Id.
	 * @return 
	 */
	public UserRole findUserRoleByUserId(Long userId) {
		log.debug("select single User");
		Session session = RoleHome.getSession();
		UserRole userRole=null;
		try
		{		
			session.beginTransaction();
			userRole=roleHome.findUserRoleByUserId(userId);
			session.getTransaction().commit();
		return userRole;
		} catch(RuntimeException re) {
			log.error("select single user role failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
	
}
