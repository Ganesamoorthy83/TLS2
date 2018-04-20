package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.LoanAttribute;

public class LoanAttributeHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanAttributeHome#listLoanAttributeByLoanId(long)}.
	 * This Test case tests whether the method ListLoanAttributeByLoanId successfully returns a list of Loan Attribute by LoanID
	 */
	public final void testListLoanAttributeByLoanId() {
		System.out.println("Testing List Loan Attribute By LoanId function");
		LoanAttributeHome loanattrb = new LoanAttributeHome();
		List<LoanAttribute> loanAttributeresult = loanattrb.listLoanAttributeByLoanId(1);
		Boolean result = false;
        if (loanAttributeresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanAttributeHome#findLoanAttributeById(long)}.
	 * This Test case tests whether the method findLoanAttributeById successfully returns a Loan Attribute by ID
	 */
	public final void testFindLoanAttributeById() {
		System.out.println("Testing Find Loan Attribute By Id function");
		LoanAttributeHome loanattrb = new LoanAttributeHome();
		List<LoanAttribute> loanAttributeresult = loanattrb.findLoanAttributeById(1);
		Boolean result = false;
        if (loanAttributeresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanAttributeHome#findLoanAttributeByLoanId(long,int)}.
	 * This Test case tests whether the method findLoanAttributeByLoanId successfully returns a Loan Attribute by LoanID
	 */
	public final void testFindLoanAttributeByLoanId() {
		System.out.println("Testing Find Loan Attribute By LoanId function");
		LoanAttributeHome loanattrb = new LoanAttributeHome();
		LoanAttribute loanAttributeresult = loanattrb.findLoanAttributeByLoanId(1,2);
		Boolean result = false;
        if (loanAttributeresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
