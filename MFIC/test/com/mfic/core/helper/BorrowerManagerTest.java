package com.mfic.core.helper;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.Borrower;

public class BorrowerManagerTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.BorrowerManager#listBorrower()}.
	 * This Test case tests whether the method ListBorrower successfully returns a list of borrowers
	 */
	public final void testListBorrower() {
		System.out.println("Testing List Borrower function");
		BorrowerManager borrower = new BorrowerManager();
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
	 * Test method for {@link com.mfic.dao.BorrowerManager#findBorrowerById(long)}.
	 * This Test case tests whether the method FindBorrowerById successfully returns a borrower by passing borrowerid
	 */
	public final void testFindBorrowerById() {
		System.out.println("Testing Find Borrower by ID function");
		BorrowerManager borrower = new BorrowerManager();
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
	 * Test method for {@link com.mfic.dao.BorrowerManager#searchBorrower(String,String)}.
	 * This Test case tests whether the method search successfully returns a list of borrowers by using parameters first name & last name
	 */
	public final void testSearchBorrower() {
		System.out.println("Testing Search Borrower function");
		BorrowerManager borrower = new BorrowerManager();
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
	 * Test method for {@link com.mfic.dao.BorrowerManager#findBorrowerByUserId(long)}.
	 * This Test case tests whether the method FindBorrowerByUserId successfully returns a list of borrowers by passing userid
	 */
	public final void testFindBorrowerByUserId() {
		System.out.println("Testing Find Borrower by Userid function");
		BorrowerManager borrower = new BorrowerManager();
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
	 * Test method for {@link com.mfic.dao.BorrowerManager#listBorrowerByLid(long)}.
	 * This Test case tests whether the method ListBorrowerByLid successfully returns a list of borrowers by passing loanid
	 */
	public final void testListBorrowerByLid() {
		System.out.println("Testing List Borrower by Lid function");
		BorrowerManager borrower = new BorrowerManager();
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
