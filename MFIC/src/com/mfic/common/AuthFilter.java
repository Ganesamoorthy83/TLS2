package com.mfic.common;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class AuthFilter implements Filter {
	private static final Logger logger = Logger.getLogger(AuthFilter.class);
	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;
	private static String contextPath;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest=null;
		String requestedUrl = null;
		if (request instanceof HttpServletRequest) {
			httpRequest = ((HttpServletRequest)request);
			requestedUrl = httpRequest.getRequestURI();
			HttpSession httpSession = httpRequest.getSession(false);
			if(contextPath == null){
				contextPath = httpRequest.getContextPath();
				if (!contextPath.endsWith("/")) {
					contextPath+="/";
				}
				openUrls.add(contextPath);
			}
	    	logger.debug("requested url = "+requestedUrl);
			logger.debug("isOpenUrl = "+openUrls);
			if(!isOpenUrl(requestedUrl) && (httpSession == null || (Long)httpSession.getAttribute("logined") == null)){
				System.out.println("httpRequest.getContextPath() = "+httpRequest.getContextPath());
				((HttpServletResponse) response).sendRedirect(contextPath+"membersLogin");
				return;
			}
		}
		chain.doFilter(request, response);
	}
    
    private boolean isOpenUrl(String requestedUrl){
    	for(String url : openUrls){
    		if(requestedUrl.toUpperCase().endsWith(url.toUpperCase())){
    			return true;
    		}
    	}
    	return false;
    }
    
	private static final ArrayList<String> openUrls = new ArrayList<String>();// JAAS is the correct option. But there is a time constraint.
	static{
		openUrls.add("membersLogin");
		openUrls.add("login");
		openUrls.add("logout.action");
		openUrls.add("index.action");
		openUrls.add("index");
		openUrls.add(".css");
		openUrls.add(".js");
		openUrls.add(".jpg");
		openUrls.add(".jpeg");
		openUrls.add(".gif");
		openUrls.add(".png");
		openUrls.add(".pdf");
		openUrls.add(".doc");
		openUrls.add(".docx");
		openUrls.add(".png");
		openUrls.add("loanDetails");
		openUrls.add("loanEnquiryForm");
		openUrls.add("loanEnquiryForm.action");
		openUrls.add("CountryList");
		openUrls.add("loanapply");
		openUrls.add("InstitutionList.action");
		openUrls.add("pwdforgotten");
	}
}
