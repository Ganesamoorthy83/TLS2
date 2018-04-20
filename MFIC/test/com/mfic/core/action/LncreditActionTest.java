package com.mfic.core.action;

import junit.framework.TestCase;

public class LncreditActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LncreditAction#processCredit()}.
	 * This Test case tests whether the method ProcessCredit successfully returns the Credit Reports
	 */
	public final void testProcessCredit() {
		System.out.println("Testing Process Credit function");
		LncreditAction lncredit = new LncreditAction();
		String result = lncredit.processCredit();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LncreditAction#list()}.
	 * This Test case tests whether the method List successfully returns a lsit of credits
	 */
	public final void testList() {
		System.out.println("Testing List function");
		LncreditAction lncredit = new LncreditAction();
		String result = lncredit.list();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

}
