package com.mfic.core.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.mfic.core.helper.LoanAttributeManager;
import com.mfic.core.helper.LoanBorrowerManager;
import com.mfic.core.helper.LoanProductManager;
import com.mfic.core.helper.LoanStatusManager;
import com.mfic.core.helper.ProgramAttributeManager;
import com.mfic.core.helper.UserManager;
import com.mfic.data.Borrower;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanAttribute;
import com.mfic.data.LoanBorrower;
import com.mfic.data.LoanProduct;
import com.mfic.data.LoanStatus;
import com.mfic.data.ProgramAttribute;
import com.mfic.data.User;
import com.mfic.util.AppProperty;
import com.mfic.util.LoanApplicationStatus;
import com.mfic.util.MailNotification;
import com.mfic.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ApprovalAction  extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 822571911511866267L;
	private static final Log log = LogFactory.getLog(ApprovalAction.class);	
	
	private Borrower borrower = new Borrower();
	private List<Borrower> brwrList = new ArrayList<Borrower>();
	private BorrowerManager borrowerManager = new BorrowerManager();
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
	private ProgramAttributeManager prgmAttributeManager = new ProgramAttributeManager();
	private List<ProgramAttribute> prgmAttrbList = new ArrayList<ProgramAttribute>();
	
	private LoanAttribute loanAttribute = new LoanAttribute();
	private LoanAttributeManager loanAttributeManager = new LoanAttributeManager();
	
	private Date currentDate = new Date();
	private long uid;
	@SuppressWarnings("rawtypes")
	private Map session;	
	private File doc;
	private String contentType;
	private String docFileName;
	private String dataDir = AppProperty.dataDir;
	private ServletContext servletContext; 
	private String approvalNote;

	public void docUpload(){
		try {
		dataDir= dataDir + "LID-" + application.getLid() + "-" + "LOAN_APPROVAL_LETTER" + "-" + docFileName;
		File savedFile = new File(dataDir);
		
			FileUtils.copyFile(this.doc,savedFile);
		} catch (IOException e) {
			addActionError(getText("uploadFail"));
			log.error("File Uploand failed");
		}
	}
	
	/**
	 * To change status to loan approved .
	 * @return String
	 */
	@SuppressWarnings({ "rawtypes" })
	public String changeStatusToLoanApproved(){
		log.debug("change status to loan approved  action");
		String str;
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			application= loanApplicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			loanStatus = loanStatusManager.findLatestLoanStatusById(Long.parseLong(request.getParameter("lid")));
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			session = ActionContext.getContext().getSession();
			uid= (Long) session.get("logined");
			
			if(doc != null){
				docUpload();
			}else{
				addActionError(getText("approvalLetter.required"));
				log.error("Approval letter upload should not be empty");
				str =  "fail";
				return str;
			}

			long lid= Long.parseLong(request.getParameter("lid"));

			if(isMandatoryAttrbCompleted(lid,lnProduct)== true){
				// To update loan application
				if (application != null){
					if(application.getStep() == LoanApplicationStatus.APPLICATION_COMPLETE.getCode()){
						application.setLnprdt(lnProduct);
						application.setDtlstupdt(currentDate);
						application.setLstupdtuid(uid);
						application.setApprovalNote(approvalNote);
						application.setApprovalDoclink(dataDir);
						application.setStep(LoanApplicationStatus.LOAN_APPROVED.getCode());
						loanApplicationManager.updateLoanApplication(application);
						
						brwrList = borrowerManager.listBorrowerByLid(application.getLid());
						ArrayList<String> email = new ArrayList<String>();
						String[] emailList= new String[0];
						for(Iterator it = brwrList.iterator();it.hasNext();){
							Borrower brwr = (Borrower) it.next();
							if(!StringUtil.isNullOrBlank(brwr.getEmail()))
							{
								String e = brwr.getEmail();
								email.add(e);
							}
						}
						if(email.size()>0){
							emailList = (String[]) email.toArray(new String[email.size()] );
						}

						if(emailList != null){
							MailNotification notificationToBorrower = new MailNotification();
							notificationToBorrower.sendMailToBorrowerApproval(emailList);
						}
						
					}else{
						log.debug("status not update");
					}
				}else{
					log.debug("Loan Application object not found action failed");
				}

				// To update loan status
				if (loanStatus.getLstid() > 0){
					if(loanStatus.getStep() == (int)LoanApplicationStatus.APPLICATION_COMPLETE.getCode()){
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
				str = "success";
			}else{
				addActionError(getText("loanApproval.fail"));
				log.error("change status to loan approved  action failed");
				str="fail";
			}
		}catch(RuntimeException re) {
			addActionError(getText("approval.fail"));
			log.error("change status to loan approved  action failed", re);
			str = "fail";
		}
		return str;
	}

	/**
	 * To validate if Mandatory attributes are completed or not .
	 * @return boolean
	 * @throws java.lang.Exception 
	 */
	@SuppressWarnings("rawtypes")
	public boolean isMandatoryAttrbCompleted(long lid, LoanProduct loanProduct){
		boolean isFilled = false;
		log.debug("change status to loan approved  action");
		try{
			prgmAttrbList =  prgmAttributeManager.findProgramAttributeByLoanProduct(lnProduct);
			for(Iterator it= prgmAttrbList.iterator();it.hasNext();){
				ProgramAttribute programAttribute = (ProgramAttribute) it.next();
				if(programAttribute.getScope() == 'M'){
					loanAttribute = loanAttributeManager.findLoanAttributeByLoanId(lid, programAttribute.getAttrb().getAttrbid());
					if(loanAttribute != null){
						isFilled=true;
					}else if(loanAttribute == null){
						isFilled=false;
						break;
					}
				}
			}
		}catch(RuntimeException re) {
			addActionError(getText("approval.fail"));
			log.error("change status to loan approved  action failed", re);
		}
		return isFilled;
	}

	/**
	 * @return the string none
	 */
	@SkipValidation
	public String show(){
		return "none";
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
	 * @return the prgmAttributeManager
	 */
	public ProgramAttributeManager getPrgmAttributeManager() {
		return prgmAttributeManager;
	}

	/**
	 * @param prgmAttributeManager the prgmAttributeManager to set
	 */
	public void setPrgmAttributeManager(ProgramAttributeManager prgmAttributeManager) {
		this.prgmAttributeManager = prgmAttributeManager;
	}

	/**
	 * @return the prgmAttrbList
	 */
	public List<ProgramAttribute> getPrgmAttrbList() {
		return prgmAttrbList;
	}

	/**
	 * @param prgmAttrbList the prgmAttrbList to set
	 */
	public void setPrgmAttrbList(List<ProgramAttribute> prgmAttrbList) {
		this.prgmAttrbList = prgmAttrbList;
	}

	/**
	 * @return the loanAttribute
	 */
	public LoanAttribute getLoanAttribute() {
		return loanAttribute;
	}

	/**
	 * @param loanAttribute the loanAttribute to set
	 */
	public void setLoanAttribute(LoanAttribute loanAttribute) {
		this.loanAttribute = loanAttribute;
	}

	/**
	 * @return the loanAttributeManager
	 */
	public LoanAttributeManager getLoanAttributeManager() {
		return loanAttributeManager;
	}

	/**
	 * @param loanAttributeManager the loanAttributeManager to set
	 */
	public void setLoanAttributeManager(LoanAttributeManager loanAttributeManager) {
		this.loanAttributeManager = loanAttributeManager;
	}

	/**
	 * @return the currentDate
	 */
	public Date getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
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
	 * @return the approvalNote
	 */
	public String getApprovalNote() {
		return approvalNote;
	}

	/**
	 * @param approvalNote the approvalNote to set
	 */
	public void setApprovalNote(String approvalNote) {
		this.approvalNote = approvalNote;
	}

}
