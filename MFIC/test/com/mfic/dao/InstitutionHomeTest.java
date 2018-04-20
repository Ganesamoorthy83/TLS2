package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.Institution;

public class InstitutionHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.InstitutionHome#listInstitution()}.
	 * This Test case tests whether the method List successfully returns a list of Institution
	 */
	public final void testListInstitution() {
		System.out.println("Testing List Borrower function");
		InstitutionHome institution = new InstitutionHome();
		List<Institution> institutionresult = institution.listInstitution();
		Boolean result = false;
        if (institutionresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.InstitutionHome#listAllInstitution()}.
	 * This Test case tests whether the method List successfully returns a list of All Institution
	 */
	public final void testListAllInstitution() {
		System.out.println("Testing List All Institution function");
		InstitutionHome institution = new InstitutionHome();
		List<Institution> institutionresult = institution.listAllInstitution();
		Boolean result = false;
        if (institutionresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.InstitutionHome#listCountry()}.
	 * This Test case tests whether the method List successfully returns a list of Country
	 * 
	 */
	public final void testListCountry() {
		System.out.println("Testing List Country function");
		InstitutionHome institution = new InstitutionHome();
		List<Institution> institutionresult = institution.listCountry();
		Boolean result = false;
        if (institutionresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.InstitutionHome#findInstitutionById(int)}.
	 * This Test case tests whether the method FindInstitutionById successfully returns a Institution using InstitutionId
	 */
	public final void testFindInstitutionById() {
		System.out.println("Testing Find Institution By Id function");
		InstitutionHome institution = new InstitutionHome();
		Institution institutionresult = institution.findInstitutionById(1);
		Boolean result = false;
        if (institutionresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.InstitutionHome#findInstitutionByName(String)}.
	 * This Test case tests whether the method FindInstitutionByName successfully returns a Institution by passing Institution Name
	 */
	public final void testFindInstitutionByName() {
		System.out.println("Testing Find Institution By Name function");
		InstitutionHome institution = new InstitutionHome();
		Institution institutionresult = institution.findInstitutionByName("ICICI");
		Boolean result = false;
        if (institutionresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.InstitutionHome#listInstitutionByCountry(String)}.
	 * This Test case tests whether the method ListInstitutionByCountry successfully returns a Institution by passing Country Name
	 */
	public final void testListInstitutionByCountry() {
		System.out.println("Testing Find Institution By Country function");
		InstitutionHome institution = new InstitutionHome();
		List<Institution> institutionresult = institution.listInstitutionByCountry("India");
		Boolean result = false;
        if (institutionresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
