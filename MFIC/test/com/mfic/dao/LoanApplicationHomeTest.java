package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.LoanApplication;

public class LoanApplicationHomeTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#listLoanApplication()}.
	 * This Test case tests whether the method ListLoanApplication successfully returns a list of Loan Applications 
	 */
	public final void testListLoanApplication() {
		System.out.println("Testing List Loan Application function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
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
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#listLoanApplicationByInstitutionId(long)}.
	 * This Test case tests whether the method ListLoanApplications successfully returns a list of Loan Applications by InstitutionID
	 */
	public final void testListLoanApplicationByInstitutionId() {
		System.out.println("Testing List Loan Application By InstitutionId function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
		List<LoanApplication> loanapplnresult = loanappln.listLoanApplicationByInstitutionId(1);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#findLoanApplicationById(long)}.
	 * This Test case tests whether the method ListLoanApplicationById successfully returns a Loan Application based on LoanID
	 */
	public final void testFindLoanApplicationById() {
		System.out.println("Testing List Loan Application By Id function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
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
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#listLoanApplicationByUser(int)}.
	 * This Test case tests whether the method ListLoanApplicationbyUser successfully returns a list of Loan Applications by User
	 */
	public final void testListLoanApplicationByUser() {
		System.out.println("Testing List Loan Application By User function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
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
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#listLoanApplicationByBorrwerId(long)}.
	 */
	public final void testListLoanApplicationByBorrwerId() {
		System.out.println("Testing List Loan Application By BorrowerID function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
		List<LoanApplication> loanapplnresult = loanappln.listLoanApplicationByBorrwerId(1);
		Boolean result = false;
        if (loanapplnresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#listLoanApplicationByStatus(int)}.
	 */
	public final void testListLoanApplicationByStatus() {
		System.out.println("Testing List Loan Application By Status function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
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
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#listLoanApplicationByStatusAndInstitutionId(int,int)}.
	 */
	public final void testListLoanApplicationByStatusAndInstitutionId() {
		System.out.println("Testing List Loan Application By Status & InstitutionID function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
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
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#listLoanApplicationByPaymentComplete(int)}.
	 */
	public final void testListLoanApplicationByPaymentComplete() {
		System.out.println("Testing List Loan Application By Payment Complete function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
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
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#findLoanApplicationByLoanProductId(long)}.
	 */
	public final void testFindLoanApplicationByLoanProductId() {
		System.out.println("Testing Find Loan Application By Loan ProductID function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
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
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#findMaxLid()}.
	 */
	public final void testFindMaxLid() {
		System.out.println("Testing Find MaxLid function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
		long result = loanappln.findMaxLid();
		//Boolean result = false;
        long expresult = 1;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanApplicationHome#listJointLoanApplications(int)}.
	 */
	public final void testListJointLoanApplications() {
		System.out.println("Testing List Joint Loan Applications function");
		LoanApplicationHome loanappln = new LoanApplicationHome();
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
