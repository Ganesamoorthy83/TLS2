package com.mfic.core.action;

import junit.framework.TestCase;

public class UsersActionTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.mfic.dao.UsersAction#list()}.
	 *  This Test case tests whether the method List successfully returns a list of Users
	 */
	public final void testList() {
		System.out.println("Testing List function");
		UsersAction loanapp = new UsersAction();
		String result = loanapp.list();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UsersAction#listRole()}.
	 *  This Test case tests whether the method ListRole successfully returns a list of User Roles
	 */
	public final void testListRole() {
		System.out.println("Testing List Role function");
		UsersAction loanapp = new UsersAction();
		String result = loanapp.listRole();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UsersAction#findEmail(String)}.
	 *  This Test case tests whether the method FindEmail successfully identifies a user by passing email
	 */
	public final void testFindEmail() {
		System.out.println("Testing Find Email function");
		UsersAction loanapp = new UsersAction();
		Boolean result = loanapp.findEmail("mfic@mfic.com");
		
		Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UsersAction#findUserById(String)}.
	 * This Test case tests whether the method FindEmail successfully identifies a user by passing email
	 */
	public final void testFindUserById() {
		System.out.println("Testing FindUserById function");
		UsersAction loanapp = new UsersAction();
		Boolean result = loanapp.findEmail("a");
		
		Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UsersAction#listInstitute()}.
	 * This Test case tests whether the method ListInstitute successfully returns a list of Institutes
	 */
	public final void testListInstitute() {
		System.out.println("Testing List Institute function");
		UsersAction loanapp = new UsersAction();
		String result = loanapp.listInstitute();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UsersAction#listLoanList()}.
	 * This Test case tests whether the method ListLoanList successfully returns a list of LoanList
	 */
	public final void testListLoanList() {
		System.out.println("Testing List LoanList function");
		UsersAction loanapp = new UsersAction();
		String result = loanapp.listLoanList();
		
        String expresult = "populate";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UsersAction#listCountry()}.
	 * This Test case tests whether the method ListCountry successfully returns a list of Country
	 */
	public final void testListCountry() {
			System.out.println("Testing List Country function");
			UsersAction loanapp = new UsersAction();
			String result = loanapp.listCountry();
			
	        String expresult = "populate";

	    assertEquals(result,expresult);
	}

}
