package com.mfic.core.helper;

import java.util.List;

import junit.framework.TestCase;

import com.mfic.data.User;
import com.mfic.data.UserPassword;

public class UserManagerTest extends TestCase {

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
	 * Test method for {@link com.mfic.dao.UserManager#listUser()}.
	 *  This Test case tests whether the method ListUser successfully returns a list of User 
	 */
	public final void testListUser() {
		System.out.println("Testing List User function");
		UserManager user = new UserManager();
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
	 * Test method for {@link com.mfic.dao.UserManager#listBorrowerUserId()}.
	 * This Test case tests whether the method BorrowerUserId successfully returns a list of Userod of Borrower type
	 */
	public final void testListBorrowerUserId() {
		System.out.println("Testing List Borrower UserID function");
		UserManager user = new UserManager();
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
	 * Test method for {@link com.mfic.dao.UserManager#findUserById(long)}.
	 * This Test case tests whether the method FindUserById successfully returns a User by ID
	 */
	public final void testFindUserById() {
		System.out.println("Testing Find User By Id function");
		UserManager user = new UserManager();
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
	 * Test method for {@link com.mfic.dao.UserManager#getUser(String,String)}.
	 * This Test case tests whether the method getUser successfully returns a User
	 */
	public final void testGetUser() {
		System.out.println("Testing Get User function");
		UserManager user = new UserManager();
		User userresult = user.getUser("mfic@mfic.com","mfic@1Mfic");
		Boolean result = false;
        if (userresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UserManager#findUserPasswordById(long)}.
	 * This Test case tests whether the method FindUserPasswordById successfully returns a User password by userid
	 */
	public final void testFindUserPasswordById() {
		System.out.println("Testing Find UserPassword By uid function");
		UserManager user = new UserManager();
		long uid = 111;
		UserPassword userpwdresult = user.findUserPasswordById(uid);
		Boolean result = false;
        if (userpwdresult!= null)
        {
        	result = true;
        }
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.UserManager#findByUserId(String,String)}.
	 * This Test case tests whether the method FindByUserId successfully returns a User by userid
	 */
	public final void testFindByUserId() {
		System.out.println("Testing Find User By userid & password function");
		UserManager user = new UserManager();
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
	 * Test method for {@link com.mfic.dao.UserManager#findUserByUserId(String)}.
	 * This Test case tests whether the method FindByUserId successfully returns a User by userid
	 */
	public final void testFindUserByUserId() {
		System.out.println("Testing Find User By userid function");
		UserManager user = new UserManager();
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
