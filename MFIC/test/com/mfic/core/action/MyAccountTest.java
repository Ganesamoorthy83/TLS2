package com.mfic.core.action;

import junit.framework.TestCase;

public class MyAccountTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.MyAccount#changePassword()}.
	 * This Test case tests whether the method ChangePassword successfully allows user to change his password 
	 */
	public final void testChangePassword() {
		System.out.println("Testing ChangePassword function");
		MyAccount accnt = new MyAccount();
		String result = accnt.changePassword();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.MyAccount#changePersonalDetail()}.
	 * This Test case tests whether the method ChangePersonalDetail successfully allows user to change his personal details
	 */	
	public final void testChangePersonalDetail() {
		System.out.println("Testing ChangePersonalDetail function");
		MyAccount accnt = new MyAccount();
		String result = accnt.changePersonalDetail();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

}
