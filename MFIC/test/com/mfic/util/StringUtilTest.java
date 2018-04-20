package com.mfic.util;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {

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
	 * Test method for {@link com.mfic.util.StringUtil#isNullOrBlank(String)}.
	 * This test case tests whether the value returned is appropriate based on the parameters passed
	 */
	public void testIsNullOrBlank() {
		System.out.println("Testing testIsNullOrBlank function");
		Boolean result1 = StringUtil.isNullOrBlank("");
		String result = result1.toString();
        String expresult = "true";

    assertEquals(result,expresult);
		}

	/**
	 * Test method for {@link com.mfic.util.StringUtil#isEqual(String,String)}.
	 * This test case tests whether the value returned is appropriate based on the parameters passed
	 */
	public void testIsEqual() {
		System.out.println("Testing testIsEqual function");
		Boolean result1 = StringUtil.isEqual("a","a");
		String result = result1.toString();
        String expresult = "true";

    assertEquals(result,expresult);
	}

}
