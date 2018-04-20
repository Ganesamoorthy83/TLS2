package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.Lncredit;

public class LncreditHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LncreditHome#findByLoanId(long)}.
	 * This Test case tests whether the method FindByLoanId successfully finds & returns the Credit Report by passing loanid
	 */
	public final void testFindByLoanId() {
		System.out.println("Testing Find By Loan Id function");
		LncreditHome credit = new LncreditHome();
		List<Lncredit> lncredit = credit.findByLoanId(1);
		Boolean result = false;
        if (lncredit!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
