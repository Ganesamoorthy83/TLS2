package com.mfic.core.action;

import junit.framework.TestCase;

public class DenialActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.DenialAction#changeStatusToLoanDenied(String)}.
	 * This Test case tests whether the method ChangeStatusToLoanDenied successfully changes the status to Loan Denied
	 */
	public final void testChangeStatusToLoanDenied() {
		System.out.println("Testing List Country function");
		DenialAction denial = new DenialAction();
		String result = denial.changeStatusToLoanDenied();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

}
