package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.LoanBorrower;

public class LoanBorrowerHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanBorrowerHome#listLoanBorrower()}.
	 * This Test case tests whether the method ListLoanBorrower successfully returns a list of Loan Borrower* 
	 * */
	public final void testListLoanBorrower() {
		System.out.println("Testing List Loan Borrower function");
		LoanBorrowerHome loanborr = new LoanBorrowerHome();
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
	 * Test method for {@link com.mfic.dao.LoanBorrowerHome#listLoanBorrowers()}.
	 * This Test case tests whether the method ListLoanBorrowers successfully returns a list of Loan Borrowers
	 */
	public final void testListLoanBorrowers() {
		System.out.println("Testing List Loan Borrowers function");
		LoanBorrowerHome loanborr = new LoanBorrowerHome();
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
	 * Test method for {@link com.mfic.dao.LoanBorrowerHome#findLoanBorrowerById(long)}.
	 * This Test case tests whether the method FindLoanBorrowerById successfully returns a Loan Borrower by borrowerid
	 */
	public final void testFindLoanBorrowerById() {
		System.out.println("Testing List Loan Borrower By Id function");
		LoanBorrowerHome loanborr = new LoanBorrowerHome();
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
