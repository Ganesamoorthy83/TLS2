package com.mfic.dao;

import junit.framework.TestCase;

import com.mfic.data.UserPassword;

public class UserPasswordHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.UserManager#findUserPasswordById(long)}.
	 * This Test case tests whether the method FindUserPasswordById successfully returns a User password by userid
	 */
	public final void testFindUserPasswordById() {
		System.out.println("Testing Find UserPassword By uid function");
		UserPasswordHome user = new UserPasswordHome();
		long uid = 111;
		UserPassword userpwdresult = user.findUserPasswordById(uid);
		Boolean result = false;
        if (userpwdresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
