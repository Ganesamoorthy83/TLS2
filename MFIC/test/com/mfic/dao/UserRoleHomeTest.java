package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.UserRole;

public class UserRoleHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.UserRoleHome#listUserRole()}.
	 * This Test case tests whether the method ListUserRole successfully returns a list of user roles
	 */
	 
	public final void testListUserRole() {
		System.out.println("Testing List User Role function");
		UserRoleHome user = new UserRoleHome();
		List<UserRole> userroleresult = user.listUserRole();
		Boolean result = false;
        if (userroleresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
