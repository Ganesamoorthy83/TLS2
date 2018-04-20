package com.mfic.core.action;

import junit.framework.TestCase;

public class LoanProductActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanProductAction#list()}.
	 * This Test case tests whether the method List successfully returns a list of Loan Products*
	 */
	public final void testList() {
		System.out.println("Testing List function");
		LoanProductAction loanapp = new LoanProductAction();
		String result = loanapp.list();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanProductAction#listByLoanProductId()}.
	 * This Test case tests whether the method ListByLoanProductId successfully returns a list of Loan Products by ID*
	 */
	public final void testListByLoanProductId() {
		System.out.println("Testing List ListByLoanProductId function");
		LoanProductAction loanapp = new LoanProductAction();
		String result = loanapp.listByLoanProductId();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanProductAction#listByInstitution()}.
	 * This Test case tests whether the method ListByInstitution successfully returns a list of Loan Products by Institution*
	 */
	public final void testListByInstitution() {
		System.out.println("Testing List ListByInstitution function");
		LoanProductAction loanapp = new LoanProductAction();
		String result = loanapp.listByInstitution();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanProductAction#listLoanList()}.
	 *  This Test case tests whether the method ListByInstitution successfully returns a list of Loan Products *
	 */
	public final void testListLoanList() {
		System.out.println("Testing List ListLoanList function");
		LoanProductAction loanapp = new LoanProductAction();
		String result = loanapp.listLoanList();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanProductAction#getAttributeAndInstitution()}.
	 *  This Test case tests whether the method ListByInstitution successfully returns a list of Attribute & Institution*
	 */

	public final void testGetAttributeAndInstitution() {
		System.out.println("Testing getAttributeAndInstitution function");
		LoanProductAction loanapp = new LoanProductAction();
		String result = loanapp.getAttributeAndInstitution();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

}
