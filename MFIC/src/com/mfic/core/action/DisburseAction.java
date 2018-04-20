package com.mfic.core.action;

import java.io.File;
import java.io.IOException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
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
import com.mfic.core.helper.InstitutionManager;
import com.mfic.core.helper.LoanApplicationManager;
import com.mfic.core.helper.LoanBorrowerManager;
import com.mfic.core.helper.LoanProductManager;
import com.mfic.core.helper.LoanStatusManager;
import com.mfic.core.helper.UserManager;
import com.mfic.data.Borrower;
import com.mfic.data.Institution;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanBorrower;
import com.mfic.data.LoanProduct;
import com.mfic.data.LoanStatus;
import com.mfic.data.User;
import com.mfic.util.AppProperty;
import com.mfic.util.LoanApplicationStatus;
import com.mfic.util.MailNotification;
import com.mfic.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DisburseAction  extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 822571911511866267L;
	private static final Log log = LogFactory.getLog(DisburseAction.class);	
	
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
	private Institution institution = new Institution();
	private InstitutionManager institutionManager = new InstitutionManager();
	
	private Date currentDate = new Date();
	private long uid;
	@SuppressWarnings("rawtypes")
	private Map session;	
	private File doc;
	private String contentType;
	private String docFileName;
	private String dataDir = AppProperty.dataDir;
	private ServletContext servletContext; 
	private double lnamount;
	private double interestRate;
	private String clientName;
	private double monthPaymnt;
	private double commAmount;
	private String institutionname;
	private Date dtlnDisburse;
	private String dtmonPaymnt;
	
	public void docUpload(){
		try {
		dataDir= dataDir + "LID-" + application.getLid() + "-" + "LOAN_DISBURSEMENT_LETTER" + "-" + docFileName;
		File savedFile = new File(dataDir);
		
			FileUtils.copyFile(this.doc,savedFile);
		} catch (IOException e) {
			addActionError(getText("uploadFail"));
			log.error("File Uploand failed");
		}
	}
	
	/**
	 * To change status to loan Disbursed .
	 * @return String
	 */
	@SuppressWarnings({ "rawtypes" })
	public String changeStatusToLoanDisbursed(){
		log.debug("change status to loan Disbursed  action");
		String str;
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			institutionname =  request.getParameter("institutionname");
			clientName =  request.getParameter("clientName");
			double lnamount = StringUtil.convertStringToDouble((request.getParameter("lnamount")));
			double interestRate = StringUtil.convertStringToDouble((request.getParameter("interestRate")));
			double monthPaymnt = StringUtil.convertStringToDouble((request.getParameter("monthPaymnt")));
			double commAmount = StringUtil.convertStringToDouble((request.getParameter("commAmount")));

			//DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
			/*try {
				dtlnDisburse = df.parse(request.getParameter("dtlndisburse"));
			} catch (ParseException e) {
				log.debug("dtlndisburse input failed",e);
			}
			try {
				dtmonPaymnt = df.parse(request.getParameter("dtmonPaymnt"));
			} catch (ParseException e) {
				log.debug("dtmonPaymnt input failed",e);
			}*/

			application= loanApplicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			loanStatus = loanStatusManager.findLatestLoanStatusById(Long.parseLong(request.getParameter("lid")));
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			session = ActionContext.getContext().getSession();
			uid= (Long) session.get("logined");
			
			
			if(doc != null){
				docUpload();
			}else{
				addActionError(getText("disburseLetter.required"));
				log.error("Disbursement Document should not be empty");
				str =  "fail";
				return str;
			}

			// To update loan application
			if (application != null){
				if(application.getStep() == LoanApplicationStatus.LOAN_APPROVED.getCode()){
					application.setLnprdt(lnProduct);
					application.setDtlstupdt(currentDate);
					application.setLstupdtuid(uid);
					
					application.setInstitutionname(institutionname);
					application.setDtlnDisburse(currentDate);
					application.setLnamount(lnamount);
					application.setInterestRate(interestRate);
					application.setClientName(clientName);
					application.setMonthPaymnt(monthPaymnt);
					application.setDtmonPaymnt(dtmonPaymnt);
					application.setCommAmount(commAmount);
					application.setLndisburseDoclink(dataDir);
					application.setStep(LoanApplicationStatus.LOAN_DISBURSED.getCode());
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
						notificationToBorrower.sendMailToBorrowerDisburse(emailList);
					}

					
				}else{
					log.debug("status not update");
				}
			}else{
				log.debug("Loan Application object not found action failed");
			}

			// To update loan status
			if (loanStatus.getLstid() > 0){
				if(loanStatus.getStep() == (int)LoanApplicationStatus.LOAN_APPROVED.getCode()){
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
		}catch(RuntimeException re) {
			addActionError(getText("disburse.fail"));
			log.error("change status to loan Disbursed  action failed", re);
			str = "fail";
		}
		return str;
	}

	/**
	 * @return the string none
	 */
	@SuppressWarnings("rawtypes")
	@SkipValidation
	public String show(){
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			application= loanApplicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			institution = institutionManager.findInstitutionById(lnProduct.getInstitution().getInstitutionid());
			institutionname = institution.getInstitutionname();
			brwrList = borrowerManager.listBorrowerByLid(Long.parseLong(request.getParameter("lid")));

			clientName="";
			for(Iterator iterator=brwrList.iterator();iterator.hasNext();)
			{
				Borrower borrower= (Borrower) iterator.next();
				if(!StringUtil.isNullOrBlank(clientName) && iterator.hasNext()){
					clientName = clientName +",";
				}
				clientName = clientName + borrower.getFname()+" "+borrower.getLname();
			}
			return "success";
			
		}catch(RuntimeException re) {
			addActionError(getText("disburse.fail"));
			log.error("change status to loan Disbursed  action failed", re);
			return "fail";
		}
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
	 * @return the institution
	 */
	public Institution getInstitution() {
		return institution;
	}

	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	/**
	 * @return the institutionManager
	 */
	public InstitutionManager getInstitutionManager() {
		return institutionManager;
	}

	/**
	 * @param institutionManager the institutionManager to set
	 */
	public void setInstitutionManager(InstitutionManager institutionManager) {
		this.institutionManager = institutionManager;
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
	 * @return the lnamount
	 */
	public double getLnamount() {
		return lnamount;
	}

	/**
	 * @param lnamount the lnamount to set
	 */
	public void setLnamount(double lnamount) {
		this.lnamount = lnamount;
	}

	/**
	 * @return the interestRate
	 */
	public double getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return the monthPaymnt
	 */
	public double getMonthPaymnt() {
		return monthPaymnt;
	}

	/**
	 * @param monthPaymnt the monthPaymnt to set
	 */
	public void setMonthPaymnt(double monthPaymnt) {
		this.monthPaymnt = monthPaymnt;
	}

	/**
	 * @return the commAmount
	 */
	public double getCommAmount() {
		return commAmount;
	}

	/**
	 * @param commAmount the commAmount to set
	 */
	public void setCommAmount(double commAmount) {
		this.commAmount = commAmount;
	}

	/**
	 * @return the institutionname
	 */
	public String getInstitutionname() {
		return institutionname;
	}

	/**
	 * @param institutionname the institutionname to set
	 */
	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}


	/**
	 * @return the dtlnDisburse
	 */
	public Date getDtlnDisburse() {
		return dtlnDisburse;
	}

	/**
	 * @param dtlnDisburse the dtlnDisburse to set
	 */
	public void setDtlnDisburse(Date dtlnDisburse) {
		this.dtlnDisburse = dtlnDisburse;
	}

	/**
	 * @return the dtmonPaymnt
	 */
	public String getDtmonPaymnt() {
		return dtmonPaymnt;
	}

	/**
	 * @param dtmonPaymnt the dtmonPaymnt to set
	 */
	public void setDtmonPaymnt(String dtmonPaymnt) {
		this.dtmonPaymnt = dtmonPaymnt;
	}


}
