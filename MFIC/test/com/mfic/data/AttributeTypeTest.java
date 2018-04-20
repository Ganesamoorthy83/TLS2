package com.mfic.data;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.util.AttributeType;

public class AttributeTypeTest extends TestCase {

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
	 * Test method for {@link com.mfic.util.AttributeType#getAttributetypelist()}.
	 * This Test case tests whether the method GetAttributetypelist successfully returns an appropriate size of attribute list
	 */
	public void testGetAttributetypelist() {
	
		System.out.println("Testing Get Attribute function");
		List<String> result = AttributeType.getAttributetypelist();
		assertEquals(result.size(),2);
	}

}
