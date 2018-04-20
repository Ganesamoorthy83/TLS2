package com.mfic.util;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mfic.data.ProspectiveBorrower;


public class MailNotification {
	
	private static final Log log = LogFactory.getLog(MailNotification.class);
	/**
	 * email from address
	 * user mail subject line
	 * user mail body of the message
	 * mfic email list
	 */	
	private String userSubjectTxt;
	private String userMsgTxt;
	private String mficSubjectTxt;
	private String mficMsgTxt;
	


	/**
	 * send mail to mfic officer
	 */
	public void sendMailToMFIC(ProspectiveBorrower pborrower )
	{
		mficSubjectTxt ="New Loan Request Received! " + pborrower.getFname() +" " + pborrower.getMname()  +" " + pborrower.getLname();

		mficMsgTxt="\nName of the Applicant:" + pborrower.getFname() +" " + pborrower.getMname()  +" " + pborrower.getLname() + " ";
		mficMsgTxt=mficMsgTxt + "\nLoan Country :" + pborrower.getLoancountry() + " ";
		mficMsgTxt=mficMsgTxt + "\nAddress :" + pborrower.getAdd1() + " " + pborrower.getAdd2() + " "+ pborrower.getCity() + " " + pborrower.getState()+ " " + pborrower.getCountry() + " " + pborrower.getZip() + " ";
		mficMsgTxt=mficMsgTxt + "\nEmail :" + pborrower.getEmail() + " ";
		mficMsgTxt=mficMsgTxt + "\nContact Nos :" + pborrower.getHphone() + " " + pborrower.getMphone() + " " + pborrower.getOphone() + " ";
		mficMsgTxt=mficMsgTxt + "\nLoan Contact :" + pborrower.getLoancontact() + " ";
		mficMsgTxt=mficMsgTxt + "\nReference :" + pborrower.getReference() + " ";

		SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
		try 
		{
			smtpMailSender.postMail( smtpMailSender.mficemailList, mficSubjectTxt, mficMsgTxt, smtpMailSender.emailFromAddress);
			log.debug("Sucessfully Sent mail to MFIC officer");
		}
		catch (MessagingException e) 
		{
			log.error("Senting Mail failed to MFIC officer!!!"+ e);
		}
	}
	
	/**
	 * send mail to loan applicant
	 */
	public Boolean sendMailToLoanApplicant(String email, Boolean isTest) throws MessagingException{
		  sendMailToLoanApplicant(email);
		  return Boolean.TRUE;
	  }
	public void sendMailToLoanApplicant(String email)
	{
		
		String[] useremailList={email};	
		userSubjectTxt ="Acknowledgement From MFIC";
		userMsgTxt = "Thanks for contacting you. You will receive the confirmation mail soon. Our customer representative will contact you as soon as possible.";

		SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
		try 
		{
			smtpMailSender.postMail( useremailList, userSubjectTxt, userMsgTxt, smtpMailSender.emailFromAddress);
			log.debug("Sucessfully Sent mail to Loan Applicant");
		}
		catch (MessagingException e) 
		{
			log.error("Senting Mail failed to Loan Applicant!!!"+ e);
		}
	}

	/**
	 * send mail to new borrower
	 */
	public Boolean sendMailToNewBorrower(String email, String userid, String password, String loanProductName, Boolean isTest) throws MessagingException{
	
		sendMailToNewBorrower(email,userid,password, loanProductName);
		  return Boolean.TRUE;
	
	}
	public void sendMailToNewBorrower(String email, String userid, String password, String loanProductName)
	{
		userSubjectTxt ="Dear Customer You are Invited to ...";
		userMsgTxt = "Dear Customer You are Invited to ";
		String[] useremailList={email};	
		userMsgTxt = userMsgTxt + loanProductName + " you can start to fill up the loan application by your login id. Here follows your login id and password.";
		userMsgTxt = userMsgTxt+ "\nUser Id: "+userid + "\n" + "Password :" + password;
		SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
		try 
		{
			smtpMailSender.postMail( useremailList, userSubjectTxt, userMsgTxt, smtpMailSender.emailFromAddress);
			log.debug("Sucessfully Sent mail to Loan Applicant");
		}
		catch (MessagingException e) 
		{
			log.error("Senting Mail failed to Loan Applicant!!!"+ e);
		}
	}

	/**
	 * send mail to exist borrower
	 */
	public Boolean sendMailToExistBorrower(String email, String loanProductName,Boolean isTest) throws MessagingException{
	
		sendMailToExistBorrower(email,loanProductName);
		 return Boolean.TRUE;
	}
	public void sendMailToExistBorrower(String email, String loanProductName)
	{
		
		userSubjectTxt ="Dear Customer You are Invited to ...";
		userMsgTxt = "Dear Customer You are Invited to ";
		String[] useremailList={email};	
		userMsgTxt = userMsgTxt + loanProductName + " you can start to fill up the loan application.";
		SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
		try 
		{
			smtpMailSender.postMail( useremailList, userSubjectTxt, userMsgTxt, smtpMailSender.emailFromAddress);
			log.debug("Sucessfully Sent mail to Loan Applicant");
		}
		catch (MessagingException e) 
		{
			log.error("Senting Mail failed to Loan Applicant!!!"+ e);
		}
	}
	
	/**
	 * send mail to exist borrower
	 */
	public Boolean sendMailToBorrowerApproval(String[] email,Boolean isTest) throws MessagingException{
		
		sendMailToBorrowerApproval(email);
		 return Boolean.TRUE;
		 
	}
	public void sendMailToBorrowerApproval(String[] email)
	{
		
		userSubjectTxt ="Dear Customer You has been Approved ...";
		userMsgTxt = "Dear Customer You has been Approved";
		String[] useremailList=email;	
		
		SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
		try 
		{
			smtpMailSender.postMail( useremailList, userSubjectTxt, userMsgTxt, smtpMailSender.emailFromAddress);
			log.debug("Sucessfully Sent mail to Loan Applicant");
		}
		catch (MessagingException e) 
		{
			log.error("Senting Mail failed to Loan Applicant!!!"+ e);
		}
	}

	/**
	 * send mail to exist borrower
	 */
	public Boolean sendMailToBorrowerDenial(String[] email,Boolean isTest) throws MessagingException{
		
		sendMailToBorrowerDenial(email);
		 return Boolean.TRUE;
		 
	}
	
	public void sendMailToBorrowerDenial(String[] email)
	{
		
		userSubjectTxt ="Dear Customer You has been Denied...";
		userMsgTxt = "Dear Customer You has been Denied due to the reason";
		String[] useremailList=email;	
		
		SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
		try 
		{
			smtpMailSender.postMail( useremailList, userSubjectTxt, userMsgTxt, smtpMailSender.emailFromAddress);
			log.debug("Sucessfully Sent mail to Loan Applicant");
		}
		catch (MessagingException e) 
		{
			log.error("Senting Mail failed to Loan Applicant!!!"+ e);
		}
	}

	/**
	 * send mail to exist borrower
	 */
public Boolean sendMailToBorrowerDisburse(String[] email,Boolean isTest) throws MessagingException{
		
	     sendMailToBorrowerDisburse(email);
		 return Boolean.TRUE;
		 
	}
	public void sendMailToBorrowerDisburse(String[] email)
	{
		
		userSubjectTxt ="Dear Customer You has been Disbursed...";
		userMsgTxt = "Dear Customer You has been Disbursed....";
		String[] useremailList=email;	
		
		SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
		try 
		{
			smtpMailSender.postMail( useremailList, userSubjectTxt, userMsgTxt, smtpMailSender.emailFromAddress);
			log.debug("Sucessfully Sent mail to Loan Applicant");
		}
		catch (MessagingException e) 
		{
			log.error("Senting Mail failed to Loan Applicant!!!"+ e);
		}
	}

	/**
	 * send mail to loan applicant
	 */
	public Boolean sendMailToUser(String email,String pwd, Boolean isTest) throws MessagingException{
		sendMailToUser(email,pwd);
		  return Boolean.TRUE;
	  }
	public void sendMailToUser(String email,String pwd)
	{
		
		String[] useremailList={email};	
		userSubjectTxt ="Password Change";
		userMsgTxt = "Your password has been changed temporarily.";
		userMsgTxt = userMsgTxt +"\nPassword:"+ pwd;
		SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
		try 
		{
			smtpMailSender.postMail( useremailList, userSubjectTxt, userMsgTxt, smtpMailSender.emailFromAddress);
			log.debug("Sucessfully Sent mail to User");
		}
		catch (MessagingException e) 
		{
			log.error("Senting Mail failed to User!!!"+ e);
		}
	}
	

}
