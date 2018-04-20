package com.mfic.core.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.mfic.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LocaleAction extends ActionSupport{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 2817966853627671504L;
	private static final Log log = LogFactory.getLog(LocaleAction.class);	

	private UserLang lang = new UserLang();
	private String curActionName;
	/**
	 * To set language .
	 * @return String
	 */
	public String execute() {
		log.debug("change language  action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			if(request.getParameter("request_language") == null){
				lang.sUserLang = "English";
				lang.language = new String("en");
				lang.country = new String("US");
			}else{
				lang.setUserlang(request.getParameter("request_language"));
			}
			//curActionName="membersLogin";
			if(!StringUtil.isNullOrBlank(lang.sUserLang)){
				if(lang.sUserLang.equalsIgnoreCase("English")){
					lang.language = new String("en");
					lang.country = new String("US");
				}
				if(lang.sUserLang.equalsIgnoreCase("Spanish")){
					lang.language = new String("es");
					lang.country = new String("ES");
				}
			}
		}catch(RuntimeException re) {
			lang.sUserLang = "English";
			log.error("change language action failed", re);
		}
		return "success";
	}

	/**
	 * @return the lang
	 */
	public UserLang getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(UserLang lang) {
		this.lang = lang;
	}

	/**
	 * @return the curActionName
	 */
	public String getCurActionName() {
		return curActionName;
	}

	/**
	 * @param curActionName the curActionName to set
	 */
	public void setCurActionName(String curActionName) {
		this.curActionName = curActionName;
	}
}