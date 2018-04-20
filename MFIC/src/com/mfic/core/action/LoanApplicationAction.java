package com.mfic.core.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mfic.core.helper.BorrowerManager;
import com.mfic.core.helper.InstitutionManager;
import com.mfic.core.helper.LoanApplicationManager;
import com.mfic.core.helper.LoanAttributeManager;
import com.mfic.core.helper.LoanBorrowerManager;
import com.mfic.core.helper.LoanProductManager;
import com.mfic.core.helper.LoanStatusManager;
import com.mfic.dao.LoanDetail;
import com.mfic.data.Borrower;
import com.mfic.data.Institution;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanAttribute;
import com.mfic.data.LoanBorrower;
import com.mfic.data.LoanProduct;
import com.mfic.data.LoanStatus;
import com.mfic.data.User;
import com.mfic.util.LoanApplicationStatus;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoanApplicationAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -900169750099759089L;
	private static final Log log = LogFactory.getLog(UsersAction.class);
	@SuppressWarnings("rawtypes")
	private Map session;
	private long uid;
	private String fname;
	private String lname;	
	
	private User user = new User();
	
	private LoanApplication loanApplication = new LoanApplication();
	private List<LoanApplication> loanApplnList = new ArrayList<LoanApplication>();
	private LoanApplicationManager loanApplicationManager = new LoanApplicationManager();

	private Borrower borrower = new Borrower();
	private List<Borrower> brwrList = new ArrayList<Borrower>();
	private BorrowerManager borrowerManager = new BorrowerManager();

	private LoanBorrower loanBorrower = new LoanBorrower();
	private List<LoanBorrower> loanBorrowers = new ArrayList<LoanBorrower>();
	private LoanBorrowerManager loanBorrowerManager = new LoanBorrowerManager();

	private LoanProduct lnProduct = new LoanProduct();
	private List<LoanProduct> lnPrdtList = new ArrayList<LoanProduct>();
	private LoanProductManager lnPrdtManager = new LoanProductManager();

	private Institution institution = new Institution();
	private List<Institution> institutionList = new ArrayList<Institution>();
	private InstitutionManager institutionManager = new InstitutionManager();

	private LoanAttribute loanAttribute = new LoanAttribute();
	private List<LoanAttribute> loanAttrbList = new ArrayList<LoanAttribute>();
	private LoanAttributeManager loanAttributeManager = new LoanAttributeManager();

	private LoanStatus loanStatus = new LoanStatus();
	private LoanStatusManager loanStatusManager = new LoanStatusManager();

	private LoanApplicationStatus lnstsDesc;
	private int institutionId;
	private List<LoanDetail> lnbwrList = new ArrayList<LoanDetail>();
	
	/**
	 * To list all loan application by borrower.
	 * @return String
	 */
	public String listLoanApplicationByUser()
	{
		log.debug("list action");
		try{
			session = ActionContext.getContext().getSession();
			uid= (Long) session.get("logined");

			loanApplnList = loanApplicationManager.listLoanApplicationByUser((int)uid);
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}
	
	/**
	 * To list all loan application.
	 * @return String
	 */
	public String listLoanApplications()
	{
		log.debug("list action");
		try{
			loanApplnList = loanApplicationManager.listLoanApplication();
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	/**
	 * To list all loan application.
	 * @return String
	 */
	public String listLoanApplicationsByCreatedOrInfoGatherStatus()
	{
		log.debug("list action");
		try{
			loanApplnList = loanApplicationManager.listLoanApplicationByStatusBelowStep(LoanApplicationStatus.INFORMATION_GATHERING.getCode());
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	/**
	 * To list all loan application by Institution Id.
	 * @return String
	 */
	public String listLnappsByInstIdAndCreatedOrInfoGatherStatus()
	{
		log.debug("list action");
		try{
			session = ActionContext.getContext().getSession();
			if(session.get("TPInstitutionId") != null){
			institutionId = (Integer) session.get("TPInstitutionId");
			loanApplnList = loanApplicationManager.listLoanApplicationByInstIdAndStatus(institutionId,LoanApplicationStatus.INFORMATION_GATHERING.getCode());
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
			}
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	/**
	 * To list all loan application by Institution Id.
	 * @return String
	 */
	public String listLoanApplicationsByInstitutionId()
	{
		log.debug("list action");
		try{
			session = ActionContext.getContext().getSession();
			if(session.get("TPInstitutionId") != null){
			institutionId = (Integer) session.get("TPInstitutionId");
			loanApplnList = loanApplicationManager.listLoanApplicationInstitutionId(institutionId);
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
			}
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}
	
	/**
	 * To list a LoanProduct by  Institution.
	 * @return String
	 */
	@SkipValidation
	public String listJointLoanApplications(){
		log.debug("list by Institution action");
		try{
			loanApplnList = loanApplicationManager.listJointLoanApplications(LoanApplicationStatus.INFORMATION_GATHERING.getCode());
			return "populate";
		}catch(RuntimeException re) {
			log.error("list By Institution action failed", re);
			return "fail";
		}
		
	}	
	/**
	 * To list all loan application.
	 * @return String
	 */
	public String listLoanApplicationsVerify()
	{
		log.debug("list action");
		try{
			loanApplnList = loanApplicationManager.listLoanApplicationByStatus(LoanApplicationStatus.APPLICATION_VERIFICATION.getCode());
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
			lnPrdtList = lnPrdtManager.listLoanProduct();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	/**
	 * To list all loan application.
	 * @return String
	 */
	public String listPaymentComplete()
	{
		log.debug("list action");
		try{
			loanApplnList = loanApplicationManager.listLoanApplicationByPaymentComplete(LoanApplicationStatus.APPLICATION_VERIFICATION.getCode());
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	/**
	 * To list all loan application.
	 * @return String
	 */
	public String listLoanApplicationsComplete()
	{
		log.debug("list action");
		try{
			session = ActionContext.getContext().getSession();
			institutionId = (Integer) session.get("TPInstitutionId");

			loanApplnList = loanApplicationManager.listLoanApplicationByStatusAndInstitutionId(LoanApplicationStatus.APPLICATION_COMPLETE.getCode(),institutionId);
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}
	
	/**
	 * To list all loan application.
	 * @return String
	 */
	public String listLoanApprovedOrDeniedForMFIC()
	{
		log.debug("list action");
		try{

			loanApplnList = loanApplicationManager.listLoanApplicationByStatusBetweenSteps(LoanApplicationStatus.LOAN_APPROVED.getCode(),LoanApplicationStatus.LOAN_DISBURSED.getCode());
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
			lnPrdtList = lnPrdtManager.listLoanProduct();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	/**
	 * To list all loan application.
	 * @return String
	 */
	public String listLoanApprovedOrDeniedForTP()
	{
		log.debug("list action");
		try{

			loanApplnList = loanApplicationManager.listLoanApplicationByStatusBetweenSteps(LoanApplicationStatus.LOAN_APPROVED.getCode(),LoanApplicationStatus.LOAN_DENIED.getCode());
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
			lnPrdtList = lnPrdtManager.listLoanProduct();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	/**
	 * To list all loan application.
	 * @return String
	 */
	public String listLoanDisbursed()
	{
		log.debug("list action");
		try{
			session = ActionContext.getContext().getSession();
			institutionId = (Integer) session.get("TPInstitutionId");

			loanApplnList = loanApplicationManager.listLoanApplicationByStatusAndInstitutionId(LoanApplicationStatus.LOAN_DISBURSED.getCode(),institutionId);
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
			lnPrdtList = lnPrdtManager.listLoanProduct();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}
	
	/**
	 * To list all loan application.
	 * @return String
	 */
	public String listLoanApproved()
	{
		log.debug("list action");
		try{
			session = ActionContext.getContext().getSession();
			institutionId = (Integer) session.get("TPInstitutionId");

			loanApplnList = loanApplicationManager.listLoanApplicationByStatusAndInstitutionId(LoanApplicationStatus.LOAN_APPROVED.getCode(),institutionId);
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	
	/**
	 * To list all loan application.
	 * @return String
	 */
	public String listLoanDenied()
	{
		log.debug("list action");
		try{
			session = ActionContext.getContext().getSession();
			institutionId = (Integer) session.get("TPInstitutionId");

			loanApplnList = loanApplicationManager.listLoanApplicationByStatusAndInstitutionId(LoanApplicationStatus.LOAN_DENIED.getCode(),institutionId);
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.listBorrower();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	/**
	 * To list loan applications by First Name or Last Name.
	 * @return String
	 */
	@SkipValidation
	public String search()
	{
		log.debug("Find by First Name or Last Name action");
		try{
			loanApplnList = loanApplicationManager.listLoanApplicationByStatus(LoanApplicationStatus.APPLICATION_VERIFICATION.getCode());
			//loanBorrowers = loanBorrowerManager.listLoanBorrower();
			lnbwrList = loanBorrowerManager.listLoanBorrowers();
			brwrList = borrowerManager.searchBorrower(fname, lname);
			return "success";
		}catch(RuntimeException re) {
			log.error("Find by First Name or Last Name action failed", re);
			addActionError(getText("searchNotFound"));
			return "fail";
		}
	}	
	
	/**
	 * To list all loan application.
	 * @return String
	 */
	public String findLoanApplicationById()
	{
		log.debug("list action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			loanApplication = loanApplicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			//loanBorrower = loanBorrowerManager.findLoanBorrowerById(Long.parseLong(request.getParameter("lid")));
			loanAttrbList = loanAttributeManager.findLoanAttributeById(Long.parseLong(request.getParameter("lid")));
			loanStatus = loanStatusManager.findLatestLoanStatusById(Long.parseLong(request.getParameter("lid")));
			borrower = borrowerManager.findBorrowerById(loanBorrower.getBrwr().getBrwrid());
			lnProduct = lnPrdtManager.findLoanProductById(loanApplication.getLnprdt().getLnprdtid());
			institution = institutionManager.findInstitutionById(lnProduct.getInstitution().getInstitutionid());
		return "success";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
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
	 * @return the loanApplication
	 */
	public LoanApplication getLoanApplication() {
		return loanApplication;
	}

	/**
	 * @param loanApplication the loanApplication to set
	 */
	public void setLoanApplication(LoanApplication loanApplication) {
		this.loanApplication = loanApplication;
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
	 * @return the loanBorrowers
	 */
	public List<LoanBorrower> getLoanBorrowers() {
		return loanBorrowers;
	}

	/**
	 * @param loanBorrowers the loanBorrowers to set
	 */
	public void setLoanBorrowers(List<LoanBorrower> loanBorrowers) {
		this.loanBorrowers = loanBorrowers;
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
	 * @return the lnPrdtList
	 */
	public List<LoanProduct> getLnPrdtList() {
		return lnPrdtList;
	}

	/**
	 * @param lnPrdtList the lnPrdtList to set
	 */
	public void setLnPrdtList(List<LoanProduct> lnPrdtList) {
		this.lnPrdtList = lnPrdtList;
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
	 * @return the institutionList
	 */
	public List<Institution> getInstitutionList() {
		return institutionList;
	}

	/**
	 * @param institutionList the institutionList to set
	 */
	public void setInstitutionList(List<Institution> institutionList) {
		this.institutionList = institutionList;
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
	 * @return the loanAttrbList
	 */
	public List<LoanAttribute> getLoanAttrbList() {
		return loanAttrbList;
	}

	/**
	 * @param loanAttrbList the loanAttrbList to set
	 */
	public void setLoanAttrbList(List<LoanAttribute> loanAttrbList) {
		this.loanAttrbList = loanAttrbList;
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
	 * @return the lnstsDesc
	 */
	public LoanApplicationStatus getLnstsDesc() {
		return lnstsDesc;
	}

	/**
	 * @param lnstsDesc the lnstsDesc to set
	 */
	public void setLnstsDesc(LoanApplicationStatus lnstsDesc) {
		this.lnstsDesc = lnstsDesc;
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

	/**
	 * @return the lnbwrList
	 */
	public List<LoanDetail> getLnbwrList() {
		return lnbwrList;
	}

	/**
	 * @param lnbwrList the lnbwrList to set
	 */
	public void setLnbwrList(List<LoanDetail> lnbwrList) {
		this.lnbwrList = lnbwrList;
	}


}
