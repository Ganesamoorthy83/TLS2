package com.mfic.core.action;

import junit.framework.TestCase;

public class LoanApplicationActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#listLoanApplicationByUser()}.
	 * This Test case tests whether the method ListLoanApplicationByUser successfully returns a list of Loan Applications by Borrower
	 */
	public final void testListLoanApplicationByUser() {
		System.out.println("Testing List LoanApplication By User function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.listLoanApplicationByUser();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#listLoanApplications()}.
	 * This Test case tests whether the method ListLoanApplications successfully returns a list of Loan Applications 
	 */
	public final void testListLoanApplications() {
		System.out.println("Testing List LoanApplications function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.listLoanApplications();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#listLoanApplicationsByInstitutionId()}.
	 * This Test case tests whether the method ListLoanApplicationsByInstitutionId successfully returns a list of Loan Applications by passing InstitutionID
	 */
	public final void testListLoanApplicationsByInstitutionId() {
		System.out.println("Testing List LoanApplicationsByInstitutionId function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.listLoanApplicationsByInstitutionId();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#listJointLoanApplications()}.
	 * This Test case tests whether the method ListJointLoanApplications successfully returns a list of Joint Loan Applications
	 */
	public final void testListJointLoanApplications() {
		System.out.println("Testing List JointLoanApplications function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.listJointLoanApplications();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#listLoanApplicationsVerify()}.
	 * This Test case tests whether the method ListLoanApplicationsVerify successfully returns a list of Loan Applications with status as Application Verification
	 */
	public final void testListLoanApplicationsVerify() {
		System.out.println("Testing List LoanApplicationsVerify function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.listLoanApplicationsVerify();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#listPaymentComplete()}.
	 * This Test case tests whether the method ListLoanApplicationsVerify successfully returns a list of Loan Applications with status as Payment Complete
	 */
	public final void testListPaymentComplete() {
		System.out.println("Testing List PaymentComplete function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.listPaymentComplete();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#listLoanApplicationsComplete()}.
	 * This Test case tests whether the method ListLoanApplicationsVerify successfully returns a list of Loan Applications with status as Application Complete
	 */
	public final void testListLoanApplicationsComplete() {
		System.out.println("Testing List LoanApplicationsComplete function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.listLoanApplicationsComplete();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#listLoanApproved()}.
	 * This Test case tests whether the method ListLoanApplicationsVerify successfully returns a list of Loan Applications with status as Loan Approved
	 */
	public final void testListLoanApproved() {
		System.out.println("Testing List LoanApproved function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.listLoanApproved();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#listLoanDenied()}.
	 * This Test case tests whether the method ListLoanApplicationsVerify successfully returns a list of Loan Applications with status as Loan Approved
	 */
	public final void testListLoanDenied() {
		System.out.println("Testing List LoanDenied function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.listLoanDenied();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#search()}.
	 * This Test case tests whether the method search successfully returns a list of Loan Applications using first name & last name
	 */
	public final void testSearch() {
		System.out.println("Testing List Search function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.search();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationAction#findLoanApplicationById()}.
	 * This Test case tests whether the method FindLoanApplicationById successfully returns a Loan Application by LoanID
	 */
	public final void testFindLoanApplicationById() {
		System.out.println("Testing findLoanApplicationById function");
		LoanApplicationAction loanapp = new LoanApplicationAction();
		String result = loanapp.findLoanApplicationById();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

}
