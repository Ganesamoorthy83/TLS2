package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.LoanStatus;

public class LoanStatusHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanStatusHome#listLoanStatus()}.
	 * This Test case tests whether the method ListLoanStatus successfully returns a list of Loan Status*
	 */
	public final void testListLoanStatus() {
		System.out.println("Testing List Loan Status function");
		LoanStatusHome loanprod = new LoanStatusHome();
		List<LoanStatus> loanstatusresult = loanprod.listLoanStatus();
		Boolean result = false;
        if (loanstatusresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanStatusHome#findLoanStatusById(long)}.
	 * This Test case tests whether the method findLoanStatusByID successfully returns a Loan Status by ID
	 */
	public final void testFindLoanStatusById() {
		System.out.println("Testing Find Loan Status By Id function");
		LoanStatusHome loanstatus = new LoanStatusHome();
		long lid = 11;
		LoanStatus loanstatusresult = loanstatus.findLoanStatusById(lid);
		Boolean result = false;
        if (loanstatusresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanStatusHome#findLatestLoanStatusById(long)}.
	 * This Test case tests whether the method findLatestLoanStatusByID successfully returns a Latest Loan Status by ID
	 */
	public final void testFindLatestLoanStatusById() {
		System.out.println("Testing Find Latest Loan Status By Id function");
		LoanStatusHome loanstatus = new LoanStatusHome();
		long lid = 11;
		LoanStatus loanstatusresult = loanstatus.findLatestLoanStatusById(lid);
		Boolean result = false;
        if (loanstatusresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
