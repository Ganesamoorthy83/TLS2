package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.Role;
import com.mfic.data.UserRole;

public class RoleHomeTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.mfic.dao.RoleHome#listRole()}.
	 * This Test case tests whether the method ListRole successfully returns a list of User Roles
	 */
	public final void testListRole() {
		System.out.println("Testing List Role function");
		RoleHome role = new RoleHome();
		List<Role> roleresult = role.listRole();
		Boolean result = false;
        if (roleresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.RoleHome#listAllRole()}.
	 * This Test case tests whether the method ListAllRole successfully returns a list of All User Roles
	 */
	public final void testListAllRole() {
		System.out.println("Testing List All Role function");
		RoleHome role = new RoleHome();
		List<Role> roleresult = role.listAllRole();
		Boolean result = false;
        if (roleresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.RoleHome#findRoleByDesc(String)}.
	 * This Test case tests whether the method FindRoleByDesc successfully returns a Role Description by passing user role
	 */
	public final void testFindRoleByDesc() {
		System.out.println("Testing Find Role by Description function");
		RoleHome role = new RoleHome();
		Role roleresult = role.findRoleByDesc("Admin");
		Boolean result = false;
        if (roleresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.RoleHome#findRoleByUserId(long)}.
	 * This Test case tests whether the method FindRoleByUserId successfully returns a Role by passing user id
	 */
	public final void testFindRoleByUserId() {
		System.out.println("Testing Find Role by UserID function");
		RoleHome role = new RoleHome();
		long uid = 111;
		Role roleresult = role.findRoleByUserId(uid);
		Boolean result = false;
        if (roleresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.RoleHome#findUserRoleByUserId(long)}.
	 * This Test case tests whether the method FindUserRoleByUserId successfully returns a User Role by passing user id
	 */
	public final void testFindUserRoleByUserId() {
		System.out.println("Testing Find User Role by UserID function");
		RoleHome role = new RoleHome();
		long uid = 111;
		UserRole roleresult = role.findUserRoleByUserId(uid);
		Boolean result = false;
        if (roleresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
