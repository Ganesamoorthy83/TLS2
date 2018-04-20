package com.mfic.core.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mfic.core.helper.UserManager;
import com.mfic.data.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5295580551374148052L;
	private static final Log log = LogFactory.getLog(Login.class);
	private String password = null;
	private String userid = null;
	
	private User user  = new User();
	private UserManager userManager = new UserManager();
	private long uroleId;
	@SuppressWarnings("rawtypes")
	private Map session;
	private int institutionId = 0;

	/**
	 * Used to authenticate user id and  password
	 */
	@SuppressWarnings("unchecked")
	public String authenticate() {
		log.debug("Validating login");
		try{
			user = (User) userManager.findByUserId(userid, password);
			uroleId = user.getUrole().getRole().getRid();

			if(uroleId == 3){
				if(user.getInstitution() != null){
					institutionId = user.getInstitution().getInstitutionid();
				}
			}
			
			
			session = ActionContext.getContext().getSession();
			session.put("logined", user.getUid());
			session.put("userRole", uroleId);
			session.put("TPInstitutionId", institutionId);
			session.put("userId", userid);
			
			return "success";
			
		}catch(RuntimeException re) {
			log.error("authenticate action failed", re);
			addActionError(getText("invalid.userid.password"));
			System.out.println("Exception occured in authentication...");
			return "error";
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
	 * @return the uroleId
	 */
	public long getUroleId() {
		return uroleId;
	}



	/**
	 * @param uroleId the uroleId to set
	 */
	public void setUroleId(long uroleId) {
		this.uroleId = uroleId;
	}

	/**
	 * @return the session
	 */
	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	@SuppressWarnings("rawtypes")
	public void setSession(Map session) {
		this.session = session;
	}

	/**
	 * @return the institutionId
	 */
	public int getInstitutionId() {
		return institutionId;
	}

	/**
	 * @param institutionId the institutionId to set
	 */
	public void setInstitutionId(int institutionId) {
		this.institutionId = institutionId;
	}

	
}
