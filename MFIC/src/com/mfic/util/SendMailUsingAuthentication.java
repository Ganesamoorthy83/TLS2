/**  
  * SendMailUsingAuthentication.java.
  * send mail using SMTP  
 */
package com.mfic.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailUsingAuthentication  implements Serializable 
{
  /**
	 * 
	 */
	private static final long serialVersionUID = -6692431939746740390L;

	/**  
	  * smtp host name.
	  * smtp authenticated user
	  * smtp authenticated password   
	 */
	
	  public  String emailFromAddress = AppProperty.emailFromAddress;
	  public List<String> mailArray = new ArrayList<String>();
	  public  String[] mficemailList = {AppProperty.mficemailList};
	  

  public Boolean postMail(String[] recipients, String subject, String message, String from, Boolean isTest) throws MessagingException{
	  postMail(recipients, subject, message, from);
	  return Boolean.TRUE;
  }
  
  public void postMail( String[] recipients, String subject,
                            String message , String from) throws MessagingException
  {
    boolean debug = false;

     //Set the host smtp address
     Properties props = new Properties();
     props.put("mail.smtp.host", AppProperty.SMTP_HOST_NAME);
     props.put("mail.smtp.auth", "true");

    Authenticator auth = new SMTPAuthenticator();
    //Session session = Session.getDefaultInstance(props, auth);
    Session session = Session.getInstance(props, auth);
    session.setDebug(debug);

    // create a message
    Message msg = new MimeMessage(session);

    // set the from and to address
    InternetAddress addressFrom = new InternetAddress(from);
    msg.setFrom(addressFrom);

    InternetAddress[] addressTo = new InternetAddress[recipients.length];
    for (int i = 0; i < recipients.length; i++)
    {
        addressTo[i] = new InternetAddress(recipients[i]);
    }
    msg.setRecipients(Message.RecipientType.TO, addressTo);


    // Setting the Subject and Content Type
    msg.setSubject(subject);
    msg.setContent(message, "text/plain");
    Transport.send(msg);
 }


/**
* SimpleAuthenticator is used to do simple authentication
* when the SMTP server requires it.
*/
private class SMTPAuthenticator extends javax.mail.Authenticator
{

    public PasswordAuthentication getPasswordAuthentication()
    {
        String username = AppProperty.SMTP_AUTH_USER;
        String password = AppProperty.SMTP_AUTH_PWD;
        return new PasswordAuthentication(username, password);
    }
}

}



