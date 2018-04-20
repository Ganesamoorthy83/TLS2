package com.mfic.core.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

public class ShowAction  extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1782666303902037069L;
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ShowAction.class);	

	public String execute() {
		return "success";
	}
	
}
