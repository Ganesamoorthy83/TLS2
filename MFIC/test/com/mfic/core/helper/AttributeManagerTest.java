package com.mfic.core.helper;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.Attribute;

public class AttributeManagerTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.AttributeManager#listAttribute()}.
	 * This Test case tests whether the method ListAttribute successfully returns a list of attributes
	 */
	public final void testListAttribute() {
		System.out.println("Testing List Attribute function");
		AttributeManager attribute = new AttributeManager();
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
	 * Test method for {@link com.mfic.dao.AttributeManager#listAllAttribute()}.
	 * This Test case tests whether the method ListAllAttribute successfully returns a list of all attributes
	 */
	public final void testListAllAttribute() {
		System.out.println("Testing List All Attribute function");
		AttributeManager attribute = new AttributeManager();
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
	 * Test method for {@link com.mfic.dao.AttributeManager#findAttributeById(int)}.
	 * This Test case tests whether the method List successfully returns a single attribute using attributeid
	 */
	public final void testFindAttributeById() {
		System.out.println("Testing Find Attribute function");
		AttributeManager attribute = new AttributeManager();
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
