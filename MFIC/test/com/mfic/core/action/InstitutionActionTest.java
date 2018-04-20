package com.mfic.core.action;

import junit.framework.TestCase;

public class InstitutionActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.InstitutionAction#list()}.
	 * This Test case tests whether the method List successfully returns a list of Institution
	 */
	public final void testList() {
		System.out.println("Testing List function");
		InstitutionAction borr = new InstitutionAction();
		String result = borr.list();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.InstitutionAction#listAll()}.
	 * This Test case tests whether the method List successfully returns a list of All Institution
	 */
	public final void testListAll() {
		System.out.println("Testing List All function");
		InstitutionAction borr = new InstitutionAction();
		String result = borr.listAll();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.InstitutionAction#listCountry()}.
	 * This Test case tests whether the method List successfully returns a list of Country
	 */
	public final void testListCountry() {
		System.out.println("Testing List Country function");
		InstitutionAction borr = new InstitutionAction();
		String result = borr.listCountry();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.InstitutionAction#listInstituteByCountry()}.
	 * This Test case tests whether the method List successfully returns a list of Institution using Country
	 */
	public final void testListInstituteByCountry() {
		System.out.println("Testing List Institute By Country function");
		InstitutionAction borr = new InstitutionAction();
		String result = borr.listInstituteByCountry();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

}
