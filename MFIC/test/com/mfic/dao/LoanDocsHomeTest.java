package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.LoanDocs;

public class LoanDocsHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanDocsHome#listLoanDocs()}.
	 * This Test case tests whether the method FindLoanBorrowerById successfully returns Loan Documents
	 */
	public final void testListLoanDocs() {
		System.out.println("Testing List Loan Docs function");
		LoanDocsHome loandocs = new LoanDocsHome();
		List<LoanDocs> loandocsresult = loandocs.listLoanDocs();
		Boolean result = false;
        if (loandocsresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanDocsHome#findLoanDocsById(int)}.
	 * This Test case tests whether the method FindLoanDocsById successfully returns a Loan Document by docid
	 */
	public final void testFindLoanDocsById() {
		System.out.println("Testing Find Loan Docs By Id function");
		LoanDocsHome loandocs = new LoanDocsHome();
		LoanDocs loandocsresult = loandocs.findLoanDocsById(1);
		Boolean result = false;
        if (loandocsresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
