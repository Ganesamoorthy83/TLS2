package com.mfic.core.action;

import junit.framework.TestCase;

public class DisburseActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.DisburseAction#changeStatusToLoanDisbursed()}.
	 * This Test case tests whether the method ChangeStatusToLoanDisbursed successfully changes the status to Loan Disbursed 
	 */
	public final void testChangeStatusToLoanDisbursed() {
		System.out.println("Testing Change Status To Loan Disbursed function");
		DisburseAction disburse = new DisburseAction();
		String result = disburse.changeStatusToLoanDisbursed();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

}
