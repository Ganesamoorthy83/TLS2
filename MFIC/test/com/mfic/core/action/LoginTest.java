package com.mfic.core.action;

import junit.framework.TestCase;

public class LoginTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.mfic.dao.Login#authenticate()}.
	 * This Test case tests whether the method Authenticate successfully authenticates user id and  password
	 */
	public final void testAuthenticate() {
		System.out.println("Testing Authenticate function");
		Login authenticate = new Login();
		String result = authenticate.authenticate();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

}
