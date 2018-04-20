package com.mfic.core.action;

import junit.framework.TestCase;

public class ConvertBorrowerActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.ConvertBorrowerAction#findUserById(String)}.
	 * This Test case tests whether the method FindUserById successfully returns a User by passing userid
	 */
	public final void testFindUserById() {
		System.out.println("Testing Find User By ID function");
		ConvertBorrowerAction borr = new ConvertBorrowerAction();
		Boolean result = borr.findUserById("a");
		
		Boolean expresult = true;

	    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.ConvertBorrowerAction#listInstitute()}.
	 * This Test case tests whether the method ListInstitute successfully returns a list of Institution
	 */
	public final void testListInstitute() {
		System.out.println("Testing List Institute function");
		ConvertBorrowerAction borr = new ConvertBorrowerAction();
		String result = borr.listInstitute();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.ConvertBorrowerAction#listCountry()}.
	 * This Test case tests whether the method ListCountry successfully returns a list of Country
	 */
	public final void testListCountry() {
		System.out.println("Testing List Country function");
		ConvertBorrowerAction borr = new ConvertBorrowerAction();
		String result = borr.listCountry();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

}
