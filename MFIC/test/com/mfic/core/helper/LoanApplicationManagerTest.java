package com.mfic.core.helper;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.LoanApplication;

public class LoanApplicationManagerTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanApplicationManager#listLoanApplication()}.
	 * This Test case tests whether the method ListLoanApplication successfully returns a list of Loan Application
	 */
	public final void testListLoanApplication() {
		System.out.println("Testing List Loan Application function");
		LoanApplicationManager loanappln = new LoanApplicationManager();
		List<LoanApplication> loanapplnresult = loanappln.listLoanApplication();
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationManager#listLoanApplicationInstitutionId(long)}.
	 * This Test case tests whether the method ListLoanApplicationInstitutionId successfully returns a list of Loan Applications by InstitutionID
	 */
	public final void testListLoanApplicationInstitutionId() {
		System.out.println("Testing List Loan Application By InstitutionId function");
		LoanApplicationManager loanappln = new LoanApplicationManager();
		long insid = 111;
		List<LoanApplication> loanapplnresult = loanappln.listLoanApplicationInstitutionId(insid);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationManager#findLoanApplicationById(long)}.
	 * This Test case tests whether the method ListLoanApplicationById successfully returns a Loan Application based on LoanID
	 */
	public final void testFindLoanApplicationById() {
		System.out.println("Testing List Loan Application By Id function");
		LoanApplicationManager loanappln = new LoanApplicationManager();
		LoanApplication loanapplnresult = loanappln.findLoanApplicationById(1);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationManager#listLoanApplicationByUser(int)}.
	 * This Test case tests whether the method ListLoanApplicationbyUser successfully returns a list of Loan Applications by User
	 */
	public final void testListLoanApplicationByUser() {
		System.out.println("Testing List Loan Application By User function");
		LoanApplicationManager loanappln = new LoanApplicationManager();
		List<LoanApplication> loanapplnresult = loanappln.listLoanApplicationByUser(1);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationManager#listLoanApplicationByBorrowerId(long)}.
	 */
	public final void testListLoanApplicationByBorrowerId() {
		System.out.println("Testing List Loan Application By BorrowerID function");
		LoanApplicationManager loanappln = new LoanApplicationManager();
		List<LoanApplication> loanapplnresult = loanappln.listLoanApplicationByBorrowerId(1);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationManager#listLoanApplicationByStatus(int)}.
	 */
	public final void testListLoanApplicationByStatus() {
		System.out.println("Testing List Loan Application By Status function");
		LoanApplicationManager loanappln = new LoanApplicationManager();
		List<LoanApplication> loanapplnresult = loanappln.listLoanApplicationByStatus(3);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationManager#listLoanApplicationByStatusAndInstitutionId(int,int)}.
	 */
	public final void testListLoanApplicationByStatusAndInstitutionId() {
		System.out.println("Testing List Loan Application By Status & InstitutionID function");
		LoanApplicationManager loanappln = new LoanApplicationManager();
		List<LoanApplication> loanapplnresult = loanappln.listLoanApplicationByStatusAndInstitutionId(3,1);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationManager#listLoanApplicationByPaymentComplete(int)}.
	 */
	public final void testListLoanApplicationByPaymentComplete() {
		System.out.println("Testing List Loan Application By Payment Complete function");
		LoanApplicationManager loanappln = new LoanApplicationManager();
		List<LoanApplication> loanapplnresult = loanappln.listLoanApplicationByPaymentComplete(1);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationManager#findLoanApplicationByLoanProductId(long)}.
	 */
	public final void testFindLoanApplicationByLoanProductId() {
		System.out.println("Testing Find Loan Application By Loan ProductID function");
		LoanApplicationManager loanappln = new LoanApplicationManager();
		List<LoanApplication> loanapplnresult = loanappln.findLoanApplicationByLoanProductId(1);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationManager#listJointLoanApplications(int)}.
	 */
	public final void testListJointLoanApplications() {
		System.out.println("Testing List Joint Loan Applications function");
		LoanApplicationManager loanappln = new LoanApplicationManager();
		List<LoanApplication> loanapplnresult = loanappln.listJointLoanApplications(1);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
