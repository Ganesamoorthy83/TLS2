package com.mfic.core.helper;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.LoanAttribute;

public class LoanAttributeManagerTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanAttributeManager#listLoanAttributeByLoanId(long)}.
	 * This Test case tests whether the method ListLoanAttributeByLoanId successfully returns a list of Loan Attribute by LoanID
	 */
	public final void testListLoanAttributeByLoanId() {
		System.out.println("Testing List Loan Attribute By LoanId function");
		LoanAttributeManager loanattrb = new LoanAttributeManager();
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
	 * Test method for {@link com.mfic.dao.LoanAttributeManager#findLoanAttributeById(long)}.
	 * This Test case tests whether the method findLoanAttributeById successfully returns a Loan Attribute by ID
	 */
	public final void testFindLoanAttributeById() {
		System.out.println("Testing Find Loan Attribute By Id function");
		LoanAttributeManager loanattrb = new LoanAttributeManager();
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
	 * Test method for {@link com.mfic.dao.LoanAttributeManager#findLoanAttributeByLoanId(long,int)}.
	 * This Test case tests whether the method findLoanAttributeByLoanId successfully returns a Loan Attribute by LoanID
	 */
	public final void testFindLoanAttributeByLoanId() {
		System.out.println("Testing Find Loan Attribute By LoanId function");
		LoanAttributeManager loanattrb = new LoanAttributeManager();
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
