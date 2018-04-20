package com.mfic.dao;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.User;

public class UserHomeTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.UserHome#listUser()}.
	 * This Test case tests whether the method ListUser successfully returns a list of User 
	 */
	public final void testListUser() {
		System.out.println("Testing List User function");
		UserHome user = new UserHome();
		List<User> userresult = user.listUser();
		Boolean result = false;
        if (userresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UserHome#listBorrowerUserId()}.
	 * This Test case tests whether the method BorrowerUserId successfully returns a list of Userod of Borrower type
	 */
	public final void testListBorrowerUserId() {
		System.out.println("Testing List Borrower UserID function");
		UserHome user = new UserHome();
		List<User> userresult = user.listBorrowerUserId();
		Boolean result = false;
        if (userresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UserHome#findUserById(long)}.
	 * This Test case tests whether the method FindUserById successfully returns a User by ID
	 */
	public final void testFindUserById() {
		System.out.println("Testing Find User By Id function");
		UserHome user = new UserHome();
		long uid = 111;
		User userresult = user.findUserById(uid);
		Boolean result = false;
        if (userresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UserHome#findUser(String,String)}.
	 * This Test case tests whether the method FindUsersuccessfully returns a User by passing mail & password
	 */
	public final void testFindUser() {
		System.out.println("Testing Find User By email & password function");
		UserHome user = new UserHome();
		User userresult = user.findUser("mfic@mfic.com","mfic@1Mfic");
		Boolean result = false;
        if (userresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UserHome#findUserByEmail(String)}.
	 * This Test case tests whether the method FindUserByEmail returns a User by passing mail 
	 */
	public final void testFindUserByEmail() {
		System.out.println("Testing Find User By email function");
		UserHome user = new UserHome();
		User userresult = user.findUserByEmail("mfic@mfic.com");
		Boolean result = false;
        if (userresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UserHome#findByUserId(String,String)}.
	 * This Test case tests whether the method FindByUserId returns a Userid
	 */
	public final void testFindByUserId() {
		System.out.println("Testing Find User By userid & password function");
		UserHome user = new UserHome();
		User userresult = user.findByUserId("a","mfic@1Mfic");
		Boolean result = false;
        if (userresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UserHome#findUserByUserId(String)}.
	 * This Test case tests whether the method FindByUserId returns a Userid
	 */
	public final void testFindUserByUserId() {
		System.out.println("Testing Find User By userid function");
		UserHome user = new UserHome();
		User userresult = user.findUserByUserId("a");
		Boolean result = false;
        if (userresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
