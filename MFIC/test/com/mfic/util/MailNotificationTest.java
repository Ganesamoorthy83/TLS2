package com.mfic.util;

import junit.framework.TestCase;

public class MailNotificationTest extends TestCase {

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
	 * Test method for {@link com.mfic.util.MailNotification#sendMailToLoanApplicant(String)}.
	 * This Test case tests whether the method SendMailToLoanApplicant successfully sends mail to Loan Applicants
	 */
	public final void testSendMailToLoanApplicant() throws Exception {
		System.out.println("Testing Send mail to Loan Applicant function");
		
		String mail = "mfic@mfic.com,test@mfic.com,a@mfic.com";
		MailNotification notify = new MailNotification();
		Boolean result = notify.sendMailToLoanApplicant(mail,Boolean.TRUE);
		Boolean expresult = true;
		
        assertEquals(result,expresult);
        
	}

	/**
	 * Test method for {@link com.mfic.util.MailNotification#sendMailToNewBorrower(String,String,String,String)}.
	 * This Test case tests whether the method SendMailToNewBorrower successfully sends mail to New Borrower
	 */
	public final void testSendMailToNewBorrower() throws Exception {
		System.out.println("Testing Send mail to New Borrower function");
		MailNotification notify = new MailNotification();
		Boolean result = notify.sendMailToNewBorrower("mfic@mfic.com","mfic","mfic@1Mfic","LP1",Boolean.TRUE);
		Boolean expresult = true;
		
        assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.util.MailNotification#sendMailToNewBorrowertest(String,String,String,String)}.
	 * This Test case tests whether the method SendMailToExistBorrower successfully sends mail to Existing Borrower
	 */
	public final void testSendMailToExistBorrower() throws Exception {
		System.out.println("Testing Send mail to Exist Borrower function");
		MailNotification notify = new MailNotification();
		Boolean result = notify.sendMailToExistBorrower("mfic@mfic.com","LP1",Boolean.TRUE);
		Boolean expresult = true;
		
        assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.util.MailNotification#sendMailToBorrowerApprovaltest(String[])}.
	 * This Test case tests whether the method SendMailToBorrowerApproval successfully sends mail to Borrower on Loan Approval
	 */
	public final void testSendMailToBorrowerApproval() throws Exception{
       System.out.println("Testing Send mail to Borrower Approval function");
       MailNotification notify = new MailNotification();
       String mail[]={"mfic@mfic.com","test@mfic.com"};
	   Boolean result = notify.sendMailToBorrowerApproval(mail,Boolean.TRUE);
	   
       Boolean expresult = true;
		
        assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.util.MailNotification#sendMailToBorrowerDenialtest(String[])}.
	 *  This Test case tests whether the method SendMailToBorrowerDenial successfully sends mail to Borrower on Loan Denial
	 */
	public final void testSendMailToBorrowerDenial() throws Exception {
		
		System.out.println("Testing Send mail to Borrower Denial function");
		String mail[]={"mfic@mfic.com","test@mfic.com"};
		    MailNotification notify = new MailNotification();
	        Boolean result = notify.sendMailToBorrowerDenial(mail,Boolean.TRUE);
	        
		    Boolean expresult = true;
			
	        assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.util.MailNotification#sendMailToBorrowerDisbursetest(String[])}.
	 * This Test case tests whether the method SendMailToBorrowerDisburse successfully sends mail to Borrower on Loan Disbursement
	 */
	public final void testSendMailToBorrowerDisburse() throws Exception {
		System.out.println("Testing Send mail to Borrower Denial function");
		String mail[]={"mfic@mfic.com","test@mfic.com"};
		MailNotification notify = new MailNotification();
		Boolean result = notify.sendMailToBorrowerDisburse(mail,Boolean.TRUE);
	       Boolean expresult = true;
			
	        assertEquals(result,expresult);
	}

}
