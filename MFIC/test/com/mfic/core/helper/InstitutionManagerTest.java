package com.mfic.core.helper;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.Institution;

public class InstitutionManagerTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.InstitutionManager#listInstitution()}.
	 * This Test case tests whether the method ListInstitution successfully returns the list of Institution
	 */
	public final void testListInstitution() {
		System.out.println("Testing List Borrower function");
		InstitutionManager institution = new InstitutionManager();
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
	 * Test method for {@link com.mfic.dao.InstitutionManager#listAllInstitution()}.
	 * This Test case tests whether the method ListInstitution successfully returns the list of all Institution
	 */
	public final void testListAllInstitution() {
		System.out.println("Testing List All Institution function");
		InstitutionManager institution = new InstitutionManager();
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
	 * Test method for {@link com.mfic.dao.InstitutionManager#listCountry()}.
	 * This Test case tests whether the method ListCountry successfully returns the list of Country
	 */
	public final void testListCountry() {
		System.out.println("Testing List Country function");
		InstitutionManager institution = new InstitutionManager();
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
	 * Test method for {@link com.mfic.dao.InstitutionManager#findInstitutionById(int)}.
	 * This Test case tests whether the method FindInstitutionById successfully returns the Institution by passing InstitutionID
	 */
	public final void testFindInstitutionById() {
		System.out.println("Testing Find Institution By Id function");
		InstitutionManager institution = new InstitutionManager();
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
	 * Test method for {@link com.mfic.dao.InstitutionManager#findInstitutionByName(String)}.
	 * This Test case tests whether the method FindInstitutionByName successfully returns the Institution by passing InstitutionName
	 */
	public final void testFindInstitutionByName() {
		System.out.println("Testing Find Institution By Name function");
		InstitutionManager institution = new InstitutionManager();
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
	 * Test method for {@link com.mfic.dao.InstitutionManager#listInstitutionByCountry(String)}.
	 * This Test case tests whether the method ListInstitutionByCountry successfully returns the Institution by passing Country
	 */
	public final void testListInstitutionByCountry() {
		System.out.println("Testing Find Institution By Country function");
		InstitutionManager institution = new InstitutionManager();
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
