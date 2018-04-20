package com.mfic.util;

import javax.mail.MessagingException;

import junit.framework.TestCase;

public class SendMailUsingAuthenticationTest extends TestCase {

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
	 * Test method for {@link com.mfic.util.SendMailUsingAuthentication#postMail(String[],String,String,String)}.
	 * This Test case tests whether the method PostMailtest successfully delivers mail to all recipients
	 */
	public final void testPostMailtest() throws MessagingException {
		System.out.println("Testing Post Mail function");
		
		String mailrecipients[] = {"mfic@mfic.com,test@mfic.com,a@mfic.com"};
		SendMailUsingAuthentication sendmail = new SendMailUsingAuthentication();
		Boolean result = sendmail.postMail(mailrecipients,"Mail Received","XYZ XYZ","admin@mfic.com", Boolean.TRUE);
		Boolean expresult = true;
		
        assertEquals(result,expresult);
	}

}
