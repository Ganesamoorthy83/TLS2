package com.mfic.core.helper;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.Role;
import com.mfic.data.UserRole;

public class RoleManagerTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.RoleManager#listRole()}.
	 */
	public final void testListRole() {
		System.out.println("Testing List Role function");
		RoleManager role = new RoleManager();
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
	 * Test method for {@link com.mfic.dao.RoleManager#listAllRole()}.
	 */
	public final void testListAllRole() {
		System.out.println("Testing List All Role function");
		RoleManager role = new RoleManager();
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
	 * Test method for {@link com.mfic.dao.RoleManager#findRoleByDesc(String)}.
	 */
	public final void testFindRoleByDesc() {
		System.out.println("Testing Find Role by Description function");
		RoleManager role = new RoleManager();
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
	 * Test method for {@link com.mfic.dao.RoleManager#listUserRole()}.
	 */
	public final void testListUserRole() {
		System.out.println("Testing List User Role function");
		RoleManager role = new RoleManager();
		List<UserRole> roleresult = role.listUserRole();
		Boolean result = false;
        if (roleresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.RoleManager#findRoleByUserId(long)}.
	 */
	public final void testFindRoleByUserId() {
		System.out.println("Testing Find Role by UserID function");
		RoleManager role = new RoleManager();
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
	 * Test method for {@link com.mfic.dao.RoleManager#findUserRoleByUserId(long)}.
	 */
	public final void testFindUserRoleByUserId() {
		System.out.println("Testing Find User Role by UserID function");
		RoleManager role = new RoleManager();
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
