package com.mfic.core.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.mfic.core.helper.LoanBorrowerManager;
import com.mfic.core.helper.LoanProductManager;
import com.mfic.core.helper.LoanStatusManager;
import com.mfic.core.helper.PBorrowerManager;
import com.mfic.core.helper.RoleManager;
import com.mfic.core.helper.UserManager;
import com.mfic.data.Borrower;
import com.mfic.data.Institution;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanBorrower;
import com.mfic.data.LoanBorrowerId;
import com.mfic.data.LoanProduct;
import com.mfic.data.LoanStatus;
import com.mfic.data.ProspectiveBorrower;
import com.mfic.data.Role;
import com.mfic.data.User;
import com.mfic.data.UserPassword;
import com.mfic.util.AppProperty;
import com.mfic.util.Country;
import com.mfic.util.MailNotification;
import com.mfic.util.StringUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ConvertBorrowerAction extends ActionSupport implements ModelDriven<ProspectiveBorrower>,Preparable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9096078090462182180L;

	/**
	 * 
	 */
	private static final Log log = LogFactory.getLog(ConvertBorrowerAction.class);
	private ProspectiveBorrower pborrower = new ProspectiveBorrower();
	private List<ProspectiveBorrower> pbList = new ArrayList<ProspectiveBorrower>();
	private PBorrowerManager pbManager = new PBorrowerManager();

	private LoanProduct lnProduct = new LoanProduct();
	private Institution institution = new Institution();
	private List<LoanProduct> lnPrdtList = new ArrayList<LoanProduct>();
    private List<LoanProduct> lnprdtnameList = new ArrayList<LoanProduct>();
	private LoanProductManager lnPrdtManager = new LoanProductManager();
	private List<Institution> institutionList = new ArrayList<Institution>();
	private InstitutionManager institutionManager = new InstitutionManager();
	
	private User user = new User();
	private List<User> userList = new ArrayList<User>();
	private UserManager userManager = new UserManager();
	
	private Role role = new Role();
	private List<Role> roleList = new ArrayList<Role>();
	private RoleManager roleManager = new RoleManager();
	private BorrowerManager borrowerManager = new BorrowerManager();
	private LoanProduct loanProduct = new LoanProduct();
	private LoanProductManager loanProductManager = new LoanProductManager();
	private UserPassword userPassword = new UserPassword();
	
	private LoanApplicationManager lnapplicationManager = new LoanApplicationManager();
	private LoanApplication loanApplication = new LoanApplication();
	
	private LoanStatusManager loanStatusManager = new LoanStatusManager();
	private LoanStatus loanStatus = new LoanStatus();
	
	private LoanBorrowerManager loanBorrowerManager = new LoanBorrowerManager();
	private LoanBorrower loanBorrower = new LoanBorrower();
	
	private String institutionName;
	
	private long id;
	private String password;
	private String confirmPassword;
	private String email = null;
	private String ssn = null;
	private String nid = null;
	private String lnPrdtName = null;
	private String userid = null;
	private String newApplicant = AppProperty.newApplicant;
	private String jointApplicant = AppProperty.jointApplicant;
	private long lid;
	private Country country = new Country();
	private List<String> countries;
	private int institutionId;
	private int lnprdtId;
	@SuppressWarnings("rawtypes")
	private Map session;
	
	public ProspectiveBorrower getModel()
	{
		return pborrower;
	}
	
	public void prepare(){
		listInstitute();
		listCountry();
		findInstitutionId();
	}

	
	/**
	 * To initialize password and role.
	 */
	public void init(){
		Date currentDate = new Date();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        institution =  institutionManager.findInstitutionByName(institutionName);

		String pwd= request.getParameter("password");
		email=request.getParameter("email");
		userid=request.getParameter("userid");

		user.setFname(request.getParameter("fname"));
		user.setMname(request.getParameter("mname"));
		user.setLname(request.getParameter("lname"));
		user.setAdd1(request.getParameter("add1"));
		user.setAdd2(request.getParameter("add2"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setCountry(request.getParameter("country"));
		user.setZip(request.getParameter("zip"));
		user.setEmail(request.getParameter("email"));
		user.setHphone(request.getParameter("hphone"));
		user.setMphone(request.getParameter("mphone"));
		user.setOphone(request.getParameter("ophone"));
		user.setLstupdtuid(Long.parseLong(request.getParameter("lstupdtuid")));
		user.setIsactive('Y');
		user.setInstitution(institution);
		user.setUserid(userid);
		
		
		
		if(user.getUid()==0){

			user.setDtlstupdt(currentDate);
			user.addNewPassword(pwd);

			int rid = 0;
			try{
				log.debug("find role id");
				role = roleManager.findRoleByDesc(request.getParameter("userrole"));
				if(role.getRid()!= 0){
					rid = role.getRid();
					user.addNewRole(rid);
				}
			}catch(RuntimeException re){
				log.debug("find role id failed");
			}
		}
	}
	
	/**
	 * To save or update user.
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public String saveOrUpdate()
	{
		log.debug("saveOrUpdate action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			pborrower = pbManager.findPborrowerById(Long.parseLong(request.getParameter("pbid")));
			if(request.getParameter("lid") != null){
				lid = Long.parseLong(request.getParameter("lid"));
			}else{
				lid =0;
			}
			String applicant = request.getParameter("optApplicant");
			String str="success";

			int brwrnos;
			ssn = request.getParameter("ssn");
			nid = request.getParameter("nid");
			if(request.getParameter("brwrnos").equalsIgnoreCase("")){
				brwrnos = 1;
			}else{
				brwrnos=Integer.parseInt(request.getParameter("brwrnos"));
			}
			
			
			if(findUserById(userid)== false){
				loanProduct = loanProductManager.findLoanProductByName(lnPrdtName);
				init();
				userManager.saveOrUpdateUser(user);
				if(loanProduct != null){
					Borrower borrower = new Borrower();
					borrower.addBorrower(user, loanProduct, ssn, nid, brwrnos);
					borrowerManager.saveOrUpdateBorrower(borrower);
					
					if(applicant.trim().equalsIgnoreCase(newApplicant)){
						LoanApplication la = new LoanApplication();
						la.addLoanApplication(borrower);
						lnapplicationManager.saveOrUpdateLoanApplication(la);
						
						LoanStatus loanStatus = new LoanStatus();
						loanStatus.addLoanStatus(la);
						loanStatusManager.saveOrUpdateLoanStatus(loanStatus);
						
					}else if(applicant.trim().equalsIgnoreCase(jointApplicant)){
						if(lid > 0){
							loanApplication = lnapplicationManager.findLoanApplicationById(lid);
							LoanBorrower lnbrwr = new LoanBorrower();
							LoanBorrowerId lnbrwrId = new LoanBorrowerId();
							lnbrwrId.setBrwrid(borrower.getBrwrid());
							lnbrwrId.setLid(loanApplication.getLid());
							
							lnbrwr.setId(lnbrwrId);
							lnbrwr.setBrwr(borrower);
							lnbrwr.setLnapp(loanApplication);
							lnbrwr.setDtlstupdt(user.getDtlstupdt());
							lnbrwr.setLstupdtuid(user.getLstupdtuid());
							loanBorrowerManager.saveOrUpdateLoanBorrower(lnbrwr);
						}
					}

					pborrower.setIsConverted('Y');
					pbManager.saveOrUpdatePBorrower(pborrower);

					if(!StringUtil.isNullOrBlank(email)){
						MailNotification notificationToBorrower = new MailNotification();
						notificationToBorrower.sendMailToNewBorrower(email, userid, password, lnPrdtName);
					}
				    Map session = ActionContext.getContext().getSession();
				    if(session.get("pbrwrid") != null){
					    session.remove("pbrwrid");
				    }

					str="success";
				}else{
					log.error("getting Loan Product Id failed");
					str="fail";
				}
			}else if(findUserById(userid)== true){
				log.error("saveOrUpdate action failed");
				addActionError(getText("existUserID"));
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
	 * To validate if user is exist or not .
	 * @return boolean
	 * @throws java.lang.Exception 
	 */
	public boolean findUserById(String userId){
		log.debug("validate existing user id");
		User userById=null;
		try{
			userById = (User) userManager.findUserByUserId(userId);
			if (userById == null || userById.getUid()==0){
				return false;
			}else {
				return true;
			}
		}catch(RuntimeException re) {
			log.error("validation by user id failed", re);
			return true;
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

	@SuppressWarnings({ "static-access" })
	@SkipValidation
	public String listCountry()
	{	
		log.debug("list action");
		try{
			countries = country.getCountryNames();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	/**
	 * To list a single ProspectiveBorrower by Id.
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String findProspectiveBorrower()
	{
		log.debug("edit action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			id =Long.parseLong(request.getParameter("id"));
			session = ActionContext.getContext().getSession();
			session.put("pbrwrid", id);

			pborrower = pbManager.findPborrowerById(Long.parseLong(request.getParameter("id")));
			listInstitute();
			return "success";
		}catch(RuntimeException re) {
			log.error("edit action failed", re);
			return "fail";
		}
	}	

	public void findInstitutionId(){
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			institutionName = request.getParameter("institutionName");
			lnPrdtName = request.getParameter("lnPrdtName");
			
			if(!StringUtil.isNullOrBlank(institutionName)){
		        institution =  institutionManager.findInstitutionByName(institutionName);
		        institutionId = institution.getInstitutionid();
			}else{
				institutionId = 1;
			}
		}catch(RuntimeException re) {
			log.error("edit action failed", re);
		}
	}
	
	/**
	 * Used to validate 
	 */
	public void validate(){
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			institutionName = request.getParameter("institutionName");
			lnPrdtName = request.getParameter("lnPrdtName");
			
			if(!StringUtil.isNullOrBlank(institutionName)){
		        institution =  institutionManager.findInstitutionByName(institutionName);
		        institutionId = institution.getInstitutionid();
			}else{
				institutionId = 1;
			}
	        
			if(!StringUtil.isNullOrBlank(lnPrdtName)){
		        lnProduct = loanProductManager.findLoanProductByName(lnPrdtName);
		        lnprdtId = (int) lnProduct.getLnprdtid();
			}
		}catch(RuntimeException re) {
			log.error("edit action failed", re);
		}
	}
	
	/**
	 * @return the pborrower
	 */
	public ProspectiveBorrower getPborrower() {
		return pborrower;
	}

	/**
	 * @param pborrower the pborrower to set
	 */
	public void setPborrower(ProspectiveBorrower pborrower) {
		this.pborrower = pborrower;
	}

	/**
	 * @return the pbList
	 */
	public List<ProspectiveBorrower> getPbList() {
		return pbList;
	}

	/**
	 * @param pbList the pbList to set
	 */
	public void setPbList(List<ProspectiveBorrower> pbList) {
		this.pbList = pbList;
	}

	/**
	 * @return the pbManager
	 */
	public PBorrowerManager getPbManager() {
		return pbManager;
	}

	/**
	 * @param pbManager the pbManager to set
	 */
	public void setPbManager(PBorrowerManager pbManager) {
		this.pbManager = pbManager;
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
	 * @return the lnprdtnameList
	 */
	public List<LoanProduct> getLnprdtnameList() {
		return lnprdtnameList;
	}

	/**
	 * @param lnprdtnameList the lnprdtnameList to set
	 */
	public void setLnprdtnameList(List<LoanProduct> lnprdtnameList) {
		this.lnprdtnameList = lnprdtnameList;
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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the roleList
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the roleManager
	 */
	public RoleManager getRoleManager() {
		return roleManager;
	}

	/**
	 * @param roleManager the roleManager to set
	 */
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
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
	 * @return the userPassword
	 */
	public UserPassword getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(UserPassword userPassword) {
		this.userPassword = userPassword;
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
	 * @return the institutionName
	 */
	public String getInstitutionName() {
		return institutionName;
	}

	/**
	 * @param institutionName the institutionName to set
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * @return the nid
	 */
	public String getNid() {
		return nid;
	}

	/**
	 * @param nid the nid to set
	 */
	public void setNid(String nid) {
		this.nid = nid;
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

	/**
	 * @return the lid
	 */
	public long getLid() {
		return lid;
	}

	/**
	 * @param lid the lid to set
	 */
	public void setLid(long lid) {
		this.lid = lid;
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
	 * @return the lnprdtId
	 */
	public int getLnprdtId() {
		return lnprdtId;
	}

	/**
	 * @param lnprdtId the lnprdtId to set
	 */
	public void setLnprdtId(int lnprdtId) {
		this.lnprdtId = lnprdtId;
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
	


	
}

