package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.Borrower;

public class BorrowerHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.BorrowerHome#listBorrower()}.
	 * This Test case tests whether the method ListBorrower successfully returns a list of borrowers
	 */
	public final void testListBorrower() {
		System.out.println("Testing List Borrower function");
		BorrowerHome borrower = new BorrowerHome();
		List<Borrower> borrowerresult = borrower.listBorrower();
		Boolean result = false;
        if (borrowerresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerHome#findBorrowerById(long)}.
	 * This Test case tests whether the method FindBorrowerById successfully returns a borrower by passing borrowerid
	 */
	public final void testFindBorrowerById() {
		System.out.println("Testing Find Borrower by ID function");
		BorrowerHome borrower = new BorrowerHome();
		Borrower borrowerresult = borrower.findBorrowerById(1);
		Boolean result = false;
        if (borrowerresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerHome#searchBorrower(String,String)}.
	 * This Test case tests whether the method search successfully returns a list of borrowers by using parameters first name & last name
	 */
	public final void testSearchBorrower() {
		System.out.println("Testing Search Borrower function");
		BorrowerHome borrower = new BorrowerHome();
		List<Borrower> borrowerresult = borrower.searchBorrower("John","Thomas");
		Boolean result = false;
        if (borrowerresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerHome#findBorrowerByUserId(long)}.
	 * This Test case tests whether the method search successfully returns a list of borrowers by passing userid
	 */
	public final void testFindBorrowerByUserId() {
		System.out.println("Testing Find Borrower by Userid function");
		BorrowerHome borrower = new BorrowerHome();
	    Borrower borrowerresult = borrower.findBorrowerByUserId(1);
		Boolean result = false;
        if (borrowerresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerHome#findMaxBrwrid()}.
	 * This Test case tests whether the method FindMaxBrwrid successfully returns the maximum borrowerid
	 */
	public final void testFindMaxBrwrid() {
		System.out.println("Testing Find Borrower by Userid function");
		BorrowerHome borrower = new BorrowerHome();
	    long result = borrower.findMaxBrwrid();
	        
        long expresult = 100;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.BorrowerHome#listBorrowerByLid(long)}.
	 */
	public final void testListBorrowerByLid() {
		System.out.println("Testing List Borrower by Lid function");
		BorrowerHome borrower = new BorrowerHome();
		List<Borrower> borrowerresult = borrower.listBorrowerByLid(1);
		Boolean result = false;
        if (borrowerresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
