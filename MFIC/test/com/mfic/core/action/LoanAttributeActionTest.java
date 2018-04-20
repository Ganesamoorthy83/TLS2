package com.mfic.core.action;

import junit.framework.TestCase;

public class LoanAttributeActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanAttributeAction#findByLoanId()}.
	 * This Test case tests whether the method FindByLoanId successfully returns a Loan Applications by LoanID
	 */
	public final void testFindByLoanId() {
		System.out.println("Testing List LoanApplication By User function");
		LoanAttributeAction loanapp = new LoanAttributeAction();
		String result = loanapp.findByLoanId();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanAttributeAction#getAttribute()}.
	 * This Test case tests whether the method GetAttribute successfully returns a Loan Attribute
	 */
	public final void testGetAttribute() {
		System.out.println("Testing GetAttribute function");
		LoanAttributeAction loanapp = new LoanAttributeAction();
		String result = loanapp.getAttribute();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanAttributeAction#getFileLabelName()}.
	 * This Test case tests whether the method GetFileLabelName successfully returns a Label Name
	 */
	public final void testGetFileLabelName() {
		System.out.println("Testing GetFileLabelName function");
		LoanAttributeAction loanapp = new LoanAttributeAction();
		String result = loanapp.getFileLabelName();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanAttributeAction#saveUploadFile()}.
	 * This Test case tests whether the method SaveUploadFile successfully saves & uploads file
	 */
	public final void testSaveUploadFile() {
		System.out.println("Testing SaveUploadFile function");
		LoanAttributeAction loanapp = new LoanAttributeAction();
		String result = loanapp.saveUploadFile();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanAttributeAction#getStatusDescription()}.
	 * This Test case tests whether the method GetStatusDescription successfully returns the status description
	 */
	public final void testGetStatusDescription() {
		System.out.println("Testing getStatusDescription function");
		LoanAttributeAction loanapp = new LoanAttributeAction();
		String loanappresult = loanapp.getStatusDescription(1);
	
        String expresult = "success";

    assertEquals(loanappresult,expresult);
	}

}
