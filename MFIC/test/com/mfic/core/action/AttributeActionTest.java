package com.mfic.core.action;

import junit.framework.TestCase;

public class AttributeActionTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.AttributeAction#list()}.
	 * This Test case tests whether the method List successfully returns a list of attributes
	 */
	public final void testList() {
		System.out.println("Testing List function");
		AttributeAction attrb = new AttributeAction();
		String result = attrb.list();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.AttributeAction#listAll()}.
	 * This Test case tests whether the method ListAll successfully returns a list of all attributes
	 */
	public final void testListAll() {
		System.out.println("Testing List All function");
		AttributeAction attrb = new AttributeAction();
		String result = attrb.listAll();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.AttributeAction#lislistByAttributeId()}.
	 * This Test case tests whether the method List successfully returns a single attribute using attributeid
	 */
	public final void testListByAttributeId() {
		System.out.println("Testing List All function");
		AttributeAction attrb = new AttributeAction();
		String result = attrb.listByAttributeId();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

}
