package com.mfic.core.action;

import junit.framework.TestCase;

public class AddtionalLoanApplicationActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.AddtionalLoanApplicationAction#listUser()}.
	 * This Test case tests whether the method listUser returns a populated user list & compares with expresult which has value "populate"
	 */
	public final void testListUser() {
		System.out.println("Testing List User function");
		AddtionalLoanApplicationAction addloan = new AddtionalLoanApplicationAction();
		String result = addloan.listUser();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.AddtionalLoanApplicationAction#listInstitute()}.
	 * This Test case tests whether the method listInstitution returns a populated Institution list & compares with expresult which has value "populate"
	 */
	public final void testListInstitute() {
		System.out.println("Testing List Institute function");
		AddtionalLoanApplicationAction addloan = new AddtionalLoanApplicationAction();
		String result = addloan.listInstitute();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

}
