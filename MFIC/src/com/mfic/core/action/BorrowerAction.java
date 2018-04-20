package com.mfic.core.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mfic.core.helper.BorrowerManager;
import com.mfic.core.helper.LoanApplicationManager;
import com.mfic.core.helper.LoanBorrowerManager;
import com.mfic.core.helper.LoanProductManager;
import com.mfic.core.helper.LoanStatusManager;
import com.mfic.core.helper.UserManager;
import com.mfic.data.Borrower;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanBorrower;
import com.mfic.data.LoanProduct;
import com.mfic.data.LoanStatus;
import com.mfic.data.User;
import com.mfic.util.AppProperty;
import com.mfic.util.LoanApplicationStatus;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BorrowerAction  extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3303415428553592552L;
	private static final Log log = LogFactory.getLog(BorrowerAction.class);	
	
	private long id;
	private String fname;
	private String lname;	

	private Borrower borrower = new Borrower();
	private List<Borrower> brwrList = new ArrayList<Borrower>();
	private BorrowerManager borrowerManager = new BorrowerManager();
	private List<LoanApplication> loanApplnList = new ArrayList<LoanApplication>();
	private LoanApplicationManager loanApplicationManager = new LoanApplicationManager();
	private LoanApplication application = new LoanApplication();
	
	private LoanStatus loanStatus = new LoanStatus();
	private LoanStatusManager loanStatusManager = new LoanStatusManager();
	private LoanProduct lnProduct = new LoanProduct();
	private LoanProductManager lnPrdtManager = new LoanProductManager();
	private LoanBorrower loanBorrower = new LoanBorrower();
	private LoanBorrowerManager loanBorrowerManager = new LoanBorrowerManager();
	private User user = new User();
	private UserManager userManager = new UserManager();
	
	
	private Date currentDate = new Date();
	private long uid;
	@SuppressWarnings("rawtypes")
	private Map session;	
	private File doc;
	private String contentType;
	private String docFileName;
	private String dataDir = AppProperty.dataDir;
	private ServletContext servletContext; 
	private List<String> discontinueReasons;
	private String lndiscontReason;
	private String uploadLabelName;
	private String lnAnalysisFile = AppProperty.lnAnalysisFile;

	public void docUpload(){
		try {
		dataDir= dataDir + "LID-" + application.getLid() + "-" + "APPROVAL_LETTER" + "-" + docFileName;
		File savedFile = new File(dataDir);
		
			FileUtils.copyFile(this.doc,savedFile);
		} catch (IOException e) {
			log.debug("File Uploand failed");
		}
	}

	public void fileUpload(){
		try {
		dataDir= dataDir + "LID-" + application.getLid() + "-" + this.uploadLabelName + "-" + docFileName;
		File savedFile = new File(dataDir);
		
			FileUtils.copyFile(this.doc,savedFile);
		} catch (IOException e) {
			log.debug("File Uploand failed");
		}
	}
	
	public BorrowerAction(){
		discontinueReasons = new ArrayList<String>();
		discontinueReasons.add("Interest rate is too high");
		discontinueReasons.add("Collateral is not sufficient");
		discontinueReasons.add("Does not have down payment");
		discontinueReasons.add("Does not have legal documents");
		discontinueReasons.add("Labor Instability");
		discontinueReasons.add("Housing Instability");
		discontinueReasons.add("Missing documents/requirements");
		discontinueReasons.add("Does not have power of attorney");
		discontinueReasons.add("Does not have property as collateral");
	}
	
	public String defaultReason(){
		return "Collateral is not sufficient";
	}
	/**
	 * To list a single ProspectiveBorrower by Id.
	 * @return String
	 */
	@SkipValidation
	public String edit()
	{
		log.debug("edit action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			loanApplnList = loanApplicationManager.listLoanApplicationByBorrowerId(Integer.parseInt(request.getParameter("brwrid")));
			return "success";
		}catch(RuntimeException re) {
			log.error("edit action failed", re);
			return "fail";
		}
	}	
	

	/**
	 * To list all Borrower.
	 * @return String
	 */
	public String list(){
		log.debug("list action");
		try{
			brwrList = borrowerManager.listBorrower();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}

	}
	

	/**
	 * To list Borrowers by First Name or Last Name.
	 * @return String
	 */
	@SkipValidation
	public String search()
	{
		log.debug("Find by First Name or Last Name action");
		try{
			brwrList = borrowerManager.searchBorrower(fname, lname);
			return "success";
		}catch(RuntimeException re) {
			log.error("Find by First Name or Last Name action failed", re);
			addActionError(getText("searchNotFound"));
			return "fail";
		}
	}	

	/**
	 * To change status to Information Gathering.
	 * @return String
	 */
	@SkipValidation
	public String changeStatusToInfoGathering(){
		log.debug("change status to Information Gathering action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			application= loanApplicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			loanStatus = loanStatusManager.findLatestLoanStatusById(Long.parseLong(request.getParameter("lid")));
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			session = ActionContext.getContext().getSession();
			uid= (Long) session.get("logined");

			// To update loan application
			if (application != null){
				if(application.getStep() == LoanApplicationStatus.APPLICATION_VERIFICATION.getCode()){
					application.setLnprdt(lnProduct);
					application.setDtlstupdt(currentDate);
					application.setLstupdtuid(uid);
					application.setStep(LoanApplicationStatus.INFORMATION_GATHERING.getCode());
					loanApplicationManager.updateLoanApplication(application);
				}else{
					log.debug("status not update");
				}
			}else{
				log.debug("Loan Application object not found action failed");
			}


			// To update loan status
			if (loanStatus.getLstid() > 0){
				if(loanStatus.getStep() == (int)LoanApplicationStatus.APPLICATION_VERIFICATION.getCode()){
					// To update loan status in old record
					loanStatus.setDtcompleted(currentDate);
					loanStatus.setRecordCode('N');
					loanStatusManager.saveOrUpdateLoanStatus(loanStatus);
					
					// To insert new record loan status
					LoanStatus nloanStatus = new LoanStatus();
					nloanStatus.addLoanStatus(application);
					loanStatusManager.saveOrUpdateLoanStatus(nloanStatus);
				}else{
					log.debug("status not update");
				}

			}else{
				log.error("Loan Status object not found action failed");
			}
			
			return "success";
		}catch(RuntimeException re) {
			log.error("change status to Information Gathering action failed", re);
			return "fail";
		}

	}


	/**
	 * To change status to application verification.
	 * @return String
	 */
	@SkipValidation
	public String changeStatusToApplicationVerification(){
		log.debug("change status to application verification action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			application= loanApplicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			loanStatus = loanStatusManager.findLatestLoanStatusById(Long.parseLong(request.getParameter("lid")));
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			session = ActionContext.getContext().getSession();
			uid= (Long) session.get("logined");

			// To update loan application
			if (application != null){
				if(application.getStep() == LoanApplicationStatus.INFORMATION_GATHERING.getCode()){
					application.setLnprdt(lnProduct);
					application.setDtlstupdt(currentDate);
					application.setLstupdtuid(uid);
					application.setStep(LoanApplicationStatus.APPLICATION_VERIFICATION.getCode());
					loanApplicationManager.updateLoanApplication(application);
				}else{
					log.debug("status not update");
				}
			}else{
				log.debug("Loan Application object not found action failed");
			}


			// To update loan status
			if (loanStatus.getLstid() > 0){
				if(loanStatus.getStep() == (int) LoanApplicationStatus.INFORMATION_GATHERING.getCode()){
					// To update loan status in old record
					loanStatus.setDtcompleted(currentDate);
					loanStatus.setRecordCode('N');
					loanStatusManager.saveOrUpdateLoanStatus(loanStatus);
					
					// To insert new record loan status
					LoanStatus nloanStatus = new LoanStatus();
					nloanStatus.addLoanStatus(application);
					loanStatusManager.saveOrUpdateLoanStatus(nloanStatus);
				}else{
					log.debug("status not update");
				}

			}else{
				log.error("Loan Status object not found action failed");
			}

			
			return "success";
		}catch(RuntimeException re) {
			log.error("change status to application verification action failed", re);
			return "fail";
		}

	}

	/**
	 * To change status to application complete.
	 * @return String
	 */
	@SkipValidation
	public String changeStatusToApplicationComplete(){
		log.debug("change status to application complete action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			application= loanApplicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			loanStatus = loanStatusManager.findLatestLoanStatusById(Long.parseLong(request.getParameter("lid")));
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			session = ActionContext.getContext().getSession();
			uid= (Long) session.get("logined");

			String uploadFileType = (String) session.get("lnAnalysisfileUploadLabel");
			if(doc != null){
				fileUpload();
			}else if(doc == null){
				addActionError(getText("uploadFile.required"));
				return "fail";
			}
			
			// To update loan application
			if (application != null){
				if(application.getStep() == LoanApplicationStatus.APPLICATION_VERIFICATION.getCode()){
					application.setLnprdt(lnProduct);
					application.setDtlstupdt(currentDate);
					application.setLstupdtuid(uid);
					application.setStep(LoanApplicationStatus.APPLICATION_COMPLETE.getCode());
					if(uploadFileType.trim().equalsIgnoreCase(lnAnalysisFile)){
						application.setLnAnalysisDoclink(dataDir);
					}
					loanApplicationManager.updateLoanApplication(application);
				}else{
					log.debug("status not update");
				}
			}else{
				log.debug("Loan Application object not found action failed");
			}


			// To update loan status
			if (loanStatus.getLstid() > 0){
				if(loanStatus.getStep() == (int)LoanApplicationStatus.APPLICATION_VERIFICATION.getCode()){
					// To update loan status in old record
					loanStatus.setDtcompleted(currentDate);
					loanStatus.setRecordCode('N');
					loanStatusManager.saveOrUpdateLoanStatus(loanStatus);
					
					// To insert new record loan status
					LoanStatus nloanStatus = new LoanStatus();
					nloanStatus.addLoanStatus(application);
					loanStatusManager.saveOrUpdateLoanStatus(nloanStatus);
				}else{
					log.debug("status not update");
				}

			}else{
				log.error("Loan Status object not found action failed");
			}

			if(session.get("lnAnalysisfileUploadLabel") != null){
			    session.remove("lnAnalysisfileUploadLabel");
			}
			
			return "success";
		}catch(RuntimeException re) {
			log.error("change status to application complete action failed", re);
			return "fail";
		}

	}




	/**
	 * To change status to loan Discontinued .
	 * @return String
	 */
	@SkipValidation
	public String changeStatusToLoanDiscontinued(){
		log.debug("change status to loan Discontinued  action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			application= loanApplicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			loanStatus = loanStatusManager.findLatestLoanStatusById(Long.parseLong(request.getParameter("lid")));
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			session = ActionContext.getContext().getSession();
			uid= (Long) session.get("logined");
			String lndiscontReason = request.getParameter("lndiscontReason");
			
			// To update loan application
			if (application != null){
					application.setLnprdt(lnProduct);
					application.setDtlstupdt(currentDate);
					application.setLstupdtuid(uid);
					application.setLndiscontReason(lndiscontReason);
					application.setStep(LoanApplicationStatus.DISCONTINUED.getCode());
					loanApplicationManager.updateLoanApplication(application);
			}else{
				log.debug("Loan Application object not found action failed");
			}


			// To update loan status
			if (loanStatus != null){
					// To update loan status in old record
					loanStatus.setDtcompleted(currentDate);
					loanStatus.setRecordCode('N');
					loanStatusManager.saveOrUpdateLoanStatus(loanStatus);
					
					// To insert new record loan status
					LoanStatus nloanStatus = new LoanStatus();
					nloanStatus.addLoanStatus(application);
					loanStatusManager.saveOrUpdateLoanStatus(nloanStatus);
			}else{
				log.error("Loan Status object not found action failed");
			}
			
			return "success";
		}catch(RuntimeException re) {
			log.error("change status to loan Discontinued  action failed", re);
			return "fail";
		}

	}

	
	/**
	 * To set payment confirmation as complete.
	 * @return String
	 */
	@SkipValidation
	public String setPaymentComplete(){
		log.debug("payment confirmation set to complete action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			application= loanApplicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			session = ActionContext.getContext().getSession();
			uid= (Long) session.get("logined");

			// To update loan application
			if (application != null){
				if(application.getStep() == LoanApplicationStatus.APPLICATION_VERIFICATION.getCode()){
					application.setLnprdt(lnProduct);
					application.setDtlstupdt(currentDate);
					application.setLstupdtuid(uid);
					application.setPaymentConfirm('Y');
					loanApplicationManager.updateLoanApplication(application);
				}else{
					log.error("payment confirmation set to complete  action failed");
					addActionError(getText("appComplete.Fail"));
					return "fail";
				}
			}else{
				log.debug("Loan Application object not found action failed");
			}
			
			return "success";
		}catch(RuntimeException re) {
			log.error("payment confirmation set to complete  action failed", re);
			return "fail";
		}
		
	}

	/**
	 * @return the string none
	 */
	@SkipValidation
	public String show(){
		return "none";
	}

	@SuppressWarnings("unchecked")
	public String getFileLabelName(){
		log.debug("list action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			uploadLabelName = request.getParameter("labelName");
			
			session = ActionContext.getContext().getSession();
			session.put("lnAnalysisfileUploadLabel", uploadLabelName);
		return "success";
		}catch(RuntimeException re) {
			log.error("get File Label Name action failed", re);
			return "fail";
		}
		
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
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
	 * @return the brwrList
	 */
	public List<Borrower> getBrwrList() {
		return brwrList;
	}
	/**
	 * @param brwrList the brwrList to set
	 */
	public void setBrwrList(List<Borrower> brwrList) {
		this.brwrList = brwrList;
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
	 * @return the loanApplnList
	 */
	public List<LoanApplication> getLoanApplnList() {
		return loanApplnList;
	}

	/**
	 * @param loanApplnList the loanApplnList to set
	 */
	public void setLoanApplnList(List<LoanApplication> loanApplnList) {
		this.loanApplnList = loanApplnList;
	}

	/**
	 * @return the loanApplicationManager
	 */
	public LoanApplicationManager getLoanApplicationManager() {
		return loanApplicationManager;
	}

	/**
	 * @param loanApplicationManager the loanApplicationManager to set
	 */
	public void setLoanApplicationManager(
			LoanApplicationManager loanApplicationManager) {
		this.loanApplicationManager = loanApplicationManager;
	}


	/**
	 * @return the application
	 */
	public LoanApplication getApplication() {
		return application;
	}


	/**
	 * @param application the application to set
	 */
	public void setApplication(LoanApplication application) {
		this.application = application;
	}


	/**
	 * @return the loanStatus
	 */
	public LoanStatus getLoanStatus() {
		return loanStatus;
	}


	/**
	 * @param loanStatus the loanStatus to set
	 */
	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}


	/**
	 * @return the loanStatusManager
	 */
	public LoanStatusManager getLoanStatusManager() {
		return loanStatusManager;
	}


	/**
	 * @param loanStatusManager the loanStatusManager to set
	 */
	public void setLoanStatusManager(LoanStatusManager loanStatusManager) {
		this.loanStatusManager = loanStatusManager;
	}


	/**
	 * @return the lnProduct
	 */
	public LoanProduct getLnProduct() {
		return lnProduct;
	}


	/**
	 * @param lnProduct the lnProduct to set
	 */
	public void setLnProduct(LoanProduct lnProduct) {
		this.lnProduct = lnProduct;
	}


	/**
	 * @return the lnPrdtManager
	 */
	public LoanProductManager getLnPrdtManager() {
		return lnPrdtManager;
	}


	/**
	 * @param lnPrdtManager the lnPrdtManager to set
	 */
	public void setLnPrdtManager(LoanProductManager lnPrdtManager) {
		this.lnPrdtManager = lnPrdtManager;
	}


	/**
	 * @return the loanBorrower
	 */
	public LoanBorrower getLoanBorrower() {
		return loanBorrower;
	}

	/**
	 * @param loanBorrower the loanBorrower to set
	 */
	public void setLoanBorrower(LoanBorrower loanBorrower) {
		this.loanBorrower = loanBorrower;
	}

	/**
	 * @return the loanBorrowerManager
	 */
	public LoanBorrowerManager getLoanBorrowerManager() {
		return loanBorrowerManager;
	}

	/**
	 * @param loanBorrowerManager the loanBorrowerManager to set
	 */
	public void setLoanBorrowerManager(LoanBorrowerManager loanBorrowerManager) {
		this.loanBorrowerManager = loanBorrowerManager;
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
	 * @return the doc
	 */
	public File getDoc() {
		return doc;
	}


	/**
	 * @param doc the doc to set
	 */
	public void setDoc(File doc) {
		this.doc = doc;
	}


	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}


	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	/**
	 * @return the docFileName
	 */
	public String getDocFileName() {
		return docFileName;
	}


	/**
	 * @param docFileName the docFileName to set
	 */
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}


	/**
	 * @return the dataDir
	 */
	public String getDataDir() {
		return dataDir;
	}


	/**
	 * @param dataDir the dataDir to set
	 */
	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}


	/**
	 * @return the servletContext
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}


	/**
	 * @param servletContext the servletContext to set
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * @return the discontinueReasons
	 */
	public List<String> getDiscontinueReasons() {
		return discontinueReasons;
	}

	/**
	 * @param discontinueReasons the discontinueReasons to set
	 */
	public void setDiscontinueReasons(List<String> discontinueReasons) {
		this.discontinueReasons = discontinueReasons;
	}

	/**
	 * @return the lndiscontReason
	 */
	public String getLndiscontReason() {
		return lndiscontReason;
	}

	/**
	 * @param lndiscontReason the lndiscontReason to set
	 */
	public void setLndiscontReason(String lndiscontReason) {
		this.lndiscontReason = lndiscontReason;
	}

	/**
	 * @return the uploadLabelName
	 */
	public String getUploadLabelName() {
		return uploadLabelName;
	}

	/**
	 * @param uploadLabelName the uploadLabelName to set
	 */
	public void setUploadLabelName(String uploadLabelName) {
		this.uploadLabelName = uploadLabelName;
	}

}
