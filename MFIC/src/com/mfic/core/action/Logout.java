package com.mfic.core.action;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import java.util.*;
public class Logout  extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1024445345787360982L;

	@SuppressWarnings("rawtypes")
	public String execute() throws Exception { 
	    Map session = ActionContext.getContext().getSession();
	    session.remove("logined"); 
	    session.remove("userRole");
	    if(session.get("TPInstitutionId") != null){
		    session.remove("TPInstitutionId");
	    }
	    return "success";
	    }

}
