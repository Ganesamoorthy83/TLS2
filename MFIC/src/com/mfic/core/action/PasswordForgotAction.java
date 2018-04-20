package com.mfic.core.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mfic.core.helper.UserManager;
import com.mfic.data.User;
import com.mfic.data.UserPassword;
import com.mfic.util.MailNotification;
import com.mfic.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class PasswordForgotAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2278360760304129517L;
	private static final Log log = LogFactory.getLog(PasswordForgotAction.class);

	private User user = new User();
	private UserManager userManager = new UserManager();
	private UserPassword userPassword = new UserPassword();
	private String userid = null;
	private String email;

	/**
	 * Used to authenticate valid email id and send temp password to email if valid
	 */
	public String authenticate() {
		log.debug("Validating login");
		try{
			if(findUserById(userid)==false){
				log.error("authenticate action failed");
				addActionError(getText("userid.invalid"));
				return "fail";
			}else if(findUserById(userid)==true){
				if(StringUtil.isNullOrBlank(user.getEmail())){
					log.error("authenticate action failed");
					addActionError(getText("pwdforgot.email.NotFound"));
					return "fail";
				}else if(!StringUtil.isNullOrBlank(user.getEmail())){
					email = user.getEmail();
					String password = StringUtil.generateRandomPassword();
					userPassword =userManager.findUserPasswordById(user.getUid());
					userPassword.setPwd(password);
					userManager.updateUserPassword(userPassword);

					MailNotification notificationToBorrower = new MailNotification();
					notificationToBorrower.sendMailToUser(email, password);
					addActionMessage(getText("pwdforgot.msg"));
				}
			}
			return "success";
		}catch(RuntimeException re) {
			log.error("authenticate action failed", re);
			addActionError(getText("pwdforgot.email.NotFound"));
			System.out.println("Exception occured in authentication...");
			return "fail";
		}
	}

	/**
	 * To validate if user is exist or not .
	 * @return boolean
	 * @throws java.lang.Exception 
	 */
	public boolean findUserById(String userId){
		log.debug("validate existing user id");
		try{
			user = (User) userManager.findUserByUserId(userId);
			if (user == null || user.getUid()==0){
				return false;
			}else {
					return true;
			}
		}catch(RuntimeException re) {
			log.error("validation by user id failed", re);
			return true;
		}
	}

	/**
	 * @return the string none
	 */
	@SkipValidation
	public String show(){
		return "none";
	}
	
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * @return the userPassword
	 */
	public UserPassword getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(UserPassword userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
