package com.mfic.core.helper;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.dao.LoanDetail;
import com.mfic.data.LoanBorrower;

public class LoanBorrowerManagerTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanBorrowerManager#listLoanBorrower()}.
	 * This Test case tests whether the method ListLoanBorrower successfully returns a list of Loan Borrower*
	 */
	public final void testListLoanBorrower() {
		System.out.println("Testing List Loan Borrower function");
		LoanBorrowerManager loanborr = new LoanBorrowerManager();
		List<LoanBorrower> loanborrresult = loanborr.listLoanBorrower();
		Boolean result = false;
        if (loanborrresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanBorrowerManager#listLoanBorrowers()}.
	 * This Test case tests whether the method ListLoanBorrowers successfully returns a list of Loan Borrowers*
	 */
	public final void testListLoanBorrowers() {
		System.out.println("Testing List Loan Borrowers function");
		LoanBorrowerManager loanborr = new LoanBorrowerManager();
		List<LoanDetail> loandetailresult = loanborr.listLoanBorrowers();
		Boolean result = false;
        if (loandetailresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanBorrowerManager#findLoanBorrowerById(long)}.
	 * This Test case tests whether the method FindLoanBorrowerById successfully returns a Loan Borrower by borrowerid
	 */
	public final void testFindLoanBorrowerById() {
		System.out.println("Testing List Loan Borrower By Id function");
		LoanBorrowerManager loanborr = new LoanBorrowerManager();
		LoanBorrower loanborrresult = loanborr.findLoanBorrowerById(1);
		Boolean result = false;
        if (loanborrresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
