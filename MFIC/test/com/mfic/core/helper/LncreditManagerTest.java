package com.mfic.core.helper;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.Lncredit;

public class LncreditManagerTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LncreditManager#listCreditReports(long)}.
	 * This Test case tests whether the method ProcessCredit successfully returns the Credit Reports
	 */
	public final void testListCreditReports() {
		System.out.println("Testing List Credit Reports function");
		LncreditManager credit = new LncreditManager();
		List<Lncredit> lncredit = credit.listCreditReports(1);
		Boolean result = false;
        if (lncredit!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	

}
