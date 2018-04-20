package com.mfic.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public final class AppProperty {
	
	private static final Log log = LogFactory.getLog(AppProperty.class);
	
	public static String newApplicant;
	public static String jointApplicant;
	public static String SMTP_HOST_NAME;
	public static String SMTP_AUTH_USER;
	public static String SMTP_AUTH_PWD;
	public static String emailFromAddress;
	public static String mficemailList;
	public static String dataDir;
	public static String creditReport;
	public static String paymentFile;
	public static String lnAnalysisFile;
	public static String invoiceFile;

	static {
		Properties props = new Properties();
		try {
			props.load(AppProperty.class.getClassLoader().getResourceAsStream("inputvalues.properties"));
		} catch (IOException e) {
			log.error("Error Reading Propertiers File - Unable to set Project properties");
		}
		dataDir = props.getProperty("dataDir");
		newApplicant = props.getProperty("newApplicant");
		jointApplicant = props.getProperty("jointApplicant");
		SMTP_HOST_NAME = props.getProperty("SMTP_HOST_NAME");
		SMTP_AUTH_USER = props.getProperty("SMTP_AUTH_USER");
		SMTP_AUTH_PWD = props.getProperty("SMTP_AUTH_PWD");
		emailFromAddress = props.getProperty("emailFromAddress");
		mficemailList = props.getProperty("mficemailList");
		creditReport = props.getProperty("creditReport");
		paymentFile = props.getProperty("paymentFile");
		lnAnalysisFile = props.getProperty("lnAnalysisFile");
		invoiceFile = props.getProperty("invoiceFile");
	}

}