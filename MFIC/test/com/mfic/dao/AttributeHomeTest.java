package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.Attribute;

public class AttributeHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.AttributeHome#listAttribute()}.
	 * This Test case tests whether the method List successfully returns a list of attributes
	 */
	public final void testListAttribute() {
		System.out.println("Testing List Attribute function");
		AttributeHome attribute = new AttributeHome();
		List<Attribute> attributeresult = attribute.listAttribute();
		Boolean result = false;
        if (attributeresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.AttributeHome#listAllAttribute()}.
	 * This Test case tests whether the method List successfully returns a list of all attributes
	 */
	public final void testListAllAttribute() {
		System.out.println("Testing List All Attribute function");
		AttributeHome attribute = new AttributeHome();
		List<Attribute> attributeresult = attribute.listAllAttribute();
		Boolean result = false;
        if (attributeresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.AttributeHome#findAttributeById(int)}.
	 * This Test case tests whether the method List successfully returns a single attribute by passing attributeid
	 */
	public final void testFindAttributeById() {
		System.out.println("Testing Find Attribute function");
		AttributeHome attribute = new AttributeHome();
		Attribute attributeresult = attribute.findAttributeById(1);
		Boolean result = false;
        if (attributeresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
