package com.mfic.core.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.mfic.util.Country;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class AddtionalLoanApplicationAction extends ActionSupport implements Preparable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7048571786975403630L;

	private static final Log log = LogFactory.getLog(AddtionalLoanApplicationAction.class);

	private Institution institution = new Institution();
	private List<Institution> institutionList = new ArrayList<Institution>();
	private InstitutionManager institutionManager = new InstitutionManager();

	private LoanProduct loanProduct = new LoanProduct();
	private List<LoanProduct> lnPrdtList = new ArrayList<LoanProduct>();
	private LoanProductManager loanProductManager = new LoanProductManager();

	private LoanApplicationManager lnapplicationManager = new LoanApplicationManager();
	private LoanApplication loanApplication = new LoanApplication();
	
	private LoanStatusManager loanStatusManager = new LoanStatusManager();
	private LoanStatus loanStatus = new LoanStatus();
	
	private LoanBorrowerManager loanBorrowerManager = new LoanBorrowerManager();
	private LoanBorrower loanBorrower = new LoanBorrower();

	private Borrower borrower = new Borrower();
	private List<Borrower> brwrList = new ArrayList<Borrower>();
	private BorrowerManager borrowerManager = new BorrowerManager();

	private User user = new User();
	private List<User> userList = new ArrayList<User>();
	private UserManager userManager = new UserManager();
	
	private Country country = new Country();
	private List<String> countries;

	private String lnPrdtName = null;
	private String userid = null;
	
	public void prepare(){
		 listUser();
		 listInstitute();
	}
	
	/**
	 * To save or update user.
	 * @return String
	 */
	public String saveOrUpdate()
	{
		log.debug("saveOrUpdate action");
		try{
			String str="success";

				user = userManager.findUserByUserId(userid);
				borrower = borrowerManager.findBorrowerByUserId(user.getUid());
				if(borrower != null){
					loanProduct = loanProductManager.findLoanProductByName(lnPrdtName);
					if(loanProduct != null && borrower != null){
							borrower.setLoanProduct(loanProduct);
							LoanApplication la = new LoanApplication();
							la.addLoanApplication(borrower);
							lnapplicationManager.saveOrUpdateLoanApplication(la);

							LoanStatus loanStatus = new LoanStatus();
							loanStatus.addLoanStatus(la);
							loanStatusManager.saveOrUpdateLoanStatus(loanStatus);
							str="success";
					}else{
						log.error("getting Loan Product Id failed");
						str="fail";
					}
				}else{
					log.error("find Borrower By UserId failed");
					str="fail";
				}

			return str;

		}catch(RuntimeException re) {
			log.error("saveOrUpdate action failed", re);
			addActionError(getText("convertBorrower.fail"));
			return "fail";
		}
	}


	/**
	 * To list all  Borrowers User Id.
	 * @return String
	 */
	@SkipValidation
	public String listUser()
	{	
		log.debug("list Borrowers User Id action");
		try{
			userList = userManager.listBorrowerUserId();
			listInstitute();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list  Borrowers User Id action failed", re);
			return "fail";
		}
	}	


	/**
	 * To list all Institutes.
	 * @return String
	 */
	@SkipValidation
	public String listInstitute()
	{	
		log.debug("list Institute action");
		try{
			institutionList = institutionManager.listInstitution();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list Institute action failed", re);
			return "fail";
		}
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
	 * @return the loanProduct
	 */
	public LoanProduct getLoanProduct() {
		return loanProduct;
	}


	/**
	 * @param loanProduct the loanProduct to set
	 */
	public void setLoanProduct(LoanProduct loanProduct) {
		this.loanProduct = loanProduct;
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
	 * @return the loanProductManager
	 */
	public LoanProductManager getLoanProductManager() {
		return loanProductManager;
	}


	/**
	 * @param loanProductManager the loanProductManager to set
	 */
	public void setLoanProductManager(LoanProductManager loanProductManager) {
		this.loanProductManager = loanProductManager;
	}


	/**
	 * @return the lnapplicationManager
	 */
	public LoanApplicationManager getLnapplicationManager() {
		return lnapplicationManager;
	}


	/**
	 * @param lnapplicationManager the lnapplicationManager to set
	 */
	public void setLnapplicationManager(LoanApplicationManager lnapplicationManager) {
		this.lnapplicationManager = lnapplicationManager;
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
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}


	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
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
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}


	/**
	 * @return the countries
	 */
	public List<String> getCountries() {
		return countries;
	}


	/**
	 * @param countries the countries to set
	 */
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	/**
	 * @return the lnPrdtName
	 */
	public String getLnPrdtName() {
		return lnPrdtName;
	}

	/**
	 * @param lnPrdtName the lnPrdtName to set
	 */
	public void setLnPrdtName(String lnPrdtName) {
		this.lnPrdtName = lnPrdtName;
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

}
