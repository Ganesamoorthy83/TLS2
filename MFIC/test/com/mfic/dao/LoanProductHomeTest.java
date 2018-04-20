package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.LoanProduct;

public class LoanProductHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.LoanProductHome#listLoanProduct()}.
	 * This Test case tests whether the method List successfully returns a list of Loan Products*
	 */
	public final void testListLoanProduct() {
		System.out.println("Testing List Loan Product function");
		LoanProductHome loanprod = new LoanProductHome();
		List<LoanProduct> loanproductresult = loanprod.listLoanProduct();
		Boolean result = false;
        if (loanproductresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanProductHome#findLoanProductById(long)}.
	 * This Test case tests whether the method List successfully returns a list of Loan Products by ID*
	 */
	public final void testFindLoanProductById() {
		System.out.println("Testing Find Loan Product By Id function");
		LoanProductHome loanprod = new LoanProductHome();
		long prdtid = 11;
		LoanProduct loanproductresult = loanprod.findLoanProductById(prdtid);
		Boolean result = false;
        if (loanproductresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}
	
	/**
	 * Test method for {@link com.mfic.dao.LoanProductHome#findLoanProductByName(String)}.
	 * This Test case tests whether the method List successfully returns a Loan Product by ID
	 */
	public final void testFindLoanProductByName() {
		System.out.println("Testing Find Loan Product By Name function");
		LoanProductHome loanprod = new LoanProductHome();
		LoanProduct loanprodresult = loanprod.findLoanProductByName("MFIC");
		Boolean result = false;
        if (loanprodresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanProductHome#findLoanProductByInstitutionId(long)}.
	 * This Test case tests whether the method List successfully returns a Loan Product by InstitutionID
	 */
	public final void testFindLoanProductByInstitutionId() {
		System.out.println("Testing Find Loan Product by Id function");
		LoanProductHome loanprod = new LoanProductHome();
		long Insid = 11;
		List<LoanProduct> loanprodresult = loanprod.findLoanProductByInstitutionId(Insid);
		Boolean result = false;
        if (loanprodresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.LoanProductHome#findLoanProductNameByInstitutionId(long)}.
	 */
	public final void testFindLoanProductNameByInstitutionId() {
		System.out.println("Testing Find Loan Product Name By InstitutionId function");
		LoanProductHome loanprod = new LoanProductHome();
		long Insid = 11;
		List<LoanProduct> loanproductresult = loanprod.findLoanProductNameByInstitutionId(Insid);
		Boolean result = false;
        if (loanproductresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
