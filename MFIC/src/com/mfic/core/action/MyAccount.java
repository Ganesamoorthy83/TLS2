package com.mfic.core.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mfic.core.helper.BorrowerManager;
import com.mfic.core.helper.UserManager;
import com.mfic.data.Borrower;
import com.mfic.data.User;
import com.mfic.data.UserPassword;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MyAccount extends ActionSupport {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8118967793293348240L;
	private static final Log log = LogFactory.getLog(UsersAction.class);
	private User user = new User();
	private List<User> userList = new ArrayList<User>();
	private UserManager userManager = new UserManager();
	private UserPassword userPassword = new UserPassword();
	private Borrower borrower = new Borrower();
	private BorrowerManager borrowerManager = new BorrowerManager();
	
	private long uid;
	private String password;
	private String confirmPassword;
	private String email;
	private String add1;
	private String add2;
	private String hphone;
	
	
	/**
	 * To change  user password by user Id.
	 * @return String
	 */
	public String changePassword()
	{
		log.debug("change  user password action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			user = (User) userManager.findUserById(Long.parseLong(request.getParameter("uid")));
			password= request.getParameter("password");
			String existPwd = request.getParameter("existpwd");
			
			userPassword =userManager.findUserPasswordById(user.getUid());
			if(userPassword.getPwd().equals(existPwd)){
				userPassword.setPwd(password);
				userManager.updateUserPassword(userPassword);
			}
			return "success";
		}catch(RuntimeException re) {
			log.error("change  user password action failed", re);
			return "fail";
		}
	}	

	/**
	 * To change  user password by user Id.
	 * @return String
	 */
	public String changePersonalDetail()
	{
		log.debug("change  user password action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			user = (User) userManager.findUserById(Long.parseLong(request.getParameter("uid")));
			borrower = borrowerManager.findBorrowerByUserId(uid);
			
			if(user.getUid() != 0 ){
				user.setEmail(email);
				user.setAdd1(add1);
				user.setAdd2(add2);
				user.setHphone(hphone);
				userManager.updateUserOnly(user);
			}

			if(borrower.getBrwrid() != 0 ){
				borrower.setEmail(email);
				borrower.setAdd1(add1);
				borrower.setAdd2(add2);
				borrower.setHphone(hphone);
				borrowerManager.updateBorrowerOnly(borrower);
			}
			return "success";
		}catch(RuntimeException re) {
			log.error("change  user password action failed", re);
			return "fail";
		}
	}	
	

	/**
	 * @return the string none
	 */
public void validate(){
		
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	user = (User) userManager.findUserById(Long.parseLong(request.getParameter("uid")));
	
	String existPwd = request.getParameter("existpwd");
	userPassword =userManager.findUserPasswordById(user.getUid());
	userPassword.setPwd(existPwd);
		if ( userPassword.getPwd().length() == 0 ){	
			addFieldError("existpwd", getText("existPassword.required"));
		}
		
				
		if(!(userPassword.getPwd().equals(existPwd))){	
			addFieldError("existpwd", getText("existPassword.invalid"));
		}
		
	}
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
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
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
	 * @return the borrower
	 */
	public Borrower getBorrower() {
		return borrower;
	}

	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	/**
	 * @return the borrowerManager
	 */
	public BorrowerManager getBorrowerManager() {
		return borrowerManager;
	}

	/**
	 * @param borrowerManager the borrowerManager to set
	 */
	public void setBorrowerManager(BorrowerManager borrowerManager) {
		this.borrowerManager = borrowerManager;
	}

	/**
	 * @return the uid
	 */
	public long getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(long uid) {
		this.uid = uid;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	/**
	 * @return the add1
	 */
	public String getAdd1() {
		return add1;
	}

	/**
	 * @param add1 the add1 to set
	 */
	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	/**
	 * @return the add2
	 */
	public String getAdd2() {
		return add2;
	}

	/**
	 * @param add2 the add2 to set
	 */
	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	/**
	 * @return the hphone
	 */
	public String getHphone() {
		return hphone;
	}

	/**
	 * @param hphone the hphone to set
	 */
	public void setHphone(String hphone) {
		this.hphone = hphone;
	}



}
