package com.mfic.core.action;

import junit.framework.TestCase;

public class BorrowerActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.BorrowerAction#list()}.
	 * This Test case tests whether the method List successfully returns a list of borrowers
	 */
	public final void testList() {
		System.out.println("Testing List All function");
		BorrowerAction borr = new BorrowerAction();
		String result = borr.list();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerAction#search()}.
	 * This Test case tests whether the method search successfully returns a list of borrowers by using parameters first name & last name
	 */
	public final void testSearch() {
		System.out.println("Testing Search function");
		BorrowerAction borr = new BorrowerAction();
		String result = borr.list();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerAction#changeStatusToInfoGathering()}.
	 * This Test case tests whether the method ChangeStatusToInfoGathering successfully changes the status to Information Gathering
	 */
	public final void testChangeStatusToInfoGathering() {
	System.out.println("Testing Change Status To InfoGathering function");
	BorrowerAction borr = new BorrowerAction();
	String result = borr.changeStatusToInfoGathering();
	
    String expresult = "success";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerAction#changeStatusToApplicationVerification()}.
	 * This Test case tests whether the method ChangeStatusToApplicationVerification successfully changes the status to Application Verification
	 */
	public final void testChangeStatusToApplicationVerification() {
		System.out.println("Testing Change Status To ApplicationVerification function");
		BorrowerAction borr = new BorrowerAction();
		String result = borr.changeStatusToApplicationVerification();
		
	    String expresult = "success";

	    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerAction#changeStatusToApplicationComplete()}.
	 * This Test case tests whether the method ChangeStatusToApplicationComplete successfully changes the status to Application Complete
	 */
	public final void testChangeStatusToApplicationComplete() {
		System.out.println("Testing Change Status To ApplicationComplete function");
		BorrowerAction borr = new BorrowerAction();
		String result = borr.changeStatusToApplicationComplete();
		
	    String expresult = "success";

	    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerAction#changeStatusToLoanDiscontinued()}.
	 * This Test case tests whether the method ChangeStatusToLoanDiscontinued successfully changes the status to Loan Discontinued
	 */
	public final void testChangeStatusToLoanDiscontinued() {
		System.out.println("Testing Change Status To LoanDiscontinued function");
		BorrowerAction borr = new BorrowerAction();
		String result = borr.changeStatusToLoanDiscontinued();
		
	    String expresult = "success";

	    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerAction#setPaymentComplete()}.
	 * This Test case tests whether the method SetPaymentComplete successfully sets the status to Payment Complete
	 */
	public final void testSetPaymentComplete() {
		System.out.println("Testing Change Status To SetPaymentComplete function");
		BorrowerAction borr = new BorrowerAction();
		String result = borr.setPaymentComplete();
		
	    String expresult = "success";

	    assertEquals(result,expresult);
	}

}
