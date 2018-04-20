package com.mfic.core.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.mfic.data.Institution;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanBorrower;
import com.mfic.data.LoanProduct;
import com.mfic.data.LoanStatus;
import com.mfic.data.ProspectiveBorrower;
import com.mfic.data.Role;
import com.mfic.data.User;
import com.mfic.data.UserPassword;
import com.mfic.data.UserRole;
import com.mfic.util.Country;
import com.mfic.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class UsersAction extends ActionSupport implements ModelDriven<User>,Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3095147257306221120L;
	private static final Log log = LogFactory.getLog(UsersAction.class);
	private User user = new User();
	private List<User> userList = new ArrayList<User>();
	private UserManager userManager = new UserManager();

	private UserRole userRole = new UserRole();
	private List<UserRole> uroleList = new ArrayList<UserRole>();
	
	private Role role = new Role();
	private List<Role> roleList = new ArrayList<Role>();
	private RoleManager roleManager = new RoleManager();
	private BorrowerManager borrowerManager = new BorrowerManager();
	private LoanProduct loanProduct = new LoanProduct();
	private LoanProductManager loanProductManager = new LoanProductManager();
	private UserPassword userPassword = new UserPassword();
	
	private LoanApplicationManager lnapplicationManager = new LoanApplicationManager();
	private LoanApplication loanApplication = new LoanApplication();
	private List<LoanApplication> loanApplicationsList = new ArrayList<LoanApplication>();
	
	private LoanStatusManager loanStatusManager = new LoanStatusManager();
	private LoanStatus loanStatus = new LoanStatus();
	
	private LoanBorrowerManager loanBorrowerManager = new LoanBorrowerManager();
	private LoanBorrower loanBorrower = new LoanBorrower();
	
	private ProspectiveBorrower pborrower = new ProspectiveBorrower();
	private PBorrowerManager pbManager = new PBorrowerManager();

	private List<LoanProduct> lnPrdtList = new ArrayList<LoanProduct>();
	private LoanProductManager lnPrdtManager = new LoanProductManager();
	private Institution institution = new Institution();
	private List<Institution> institutionList = new ArrayList<Institution>();
	private InstitutionManager institutionManager = new InstitutionManager();
	private String institutionName;
	private int institutionId;
	
	private long id;
	private String password;
	private String confirmPassword;
	private String email = null;
	private String ssn = null;
	private String nid = null;
	private String lnPrdtName = null;
	private String userid = null;
	private String userrole;
	private Country country = new Country();
	private List<String> countries;
	
	public User getModel()
	{
		return user;
	}

	public void prepare(){
		roleList = roleManager.listRole();
		institutionList = institutionManager.listInstitution();
		listCountry();
	}
	
	/**
	 * To initialize password and role.
	 */
	public void init(){
		Date currentDate = new Date();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String pwd= request.getParameter("password");
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
		}else if(user.getUid()>0){
			userPassword =userManager.findUserPasswordById(user.getUid());
			role = roleManager.findRoleByDesc(request.getParameter("userrole"));
			userRole = roleManager.findUserRoleByUserId(user.getUid());
			
			userPassword.setPwd(password);
			userManager.updateUserPassword(userPassword);

			userRole.setUser(user);
			userRole.setRole(role);
			userManager.updateUserRole(userRole);
			
			/*if(userPassword.getPwd().equals(existPwd)){
				userPassword.setPwd(password);
				userManager.updateUserPassword(userPassword);
			}*/

		}
	}

	/**
	 * To save or update user.
	 * @return String
	 */
	public String saveOrUpdate()
	{
		log.debug("saveOrUpdate action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			email=request.getParameter("email");
			userid=request.getParameter("userid");
			userrole = request.getParameter("userrole");
			String str="success";

				if(findUserById(userid)== false){
					init();
					if(!StringUtil.isNullOrBlank(institutionName)){
						institution = institutionManager.findInstitutionByName(institutionName);
						user.setInstitution(institution);
					}
					userManager.saveOrUpdateUser(user);
					str="success";
				}else{
					addActionError(getText("existUserID"));
					log.error("This user id already taken by another user !!!");
					str="input";
				}
			return str;

		}catch(RuntimeException re) {
			log.error("saveOrUpdate action failed", re);
			return "fail";
		}
	}
	
	/**
	 * To delete a user.
	 * @return String
	 */
	@SkipValidation
	public String delete()
	{
		log.debug("delete action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			userManager.deleteUser(Long.parseLong(request.getParameter("id")));
			return "success";
		}catch(RuntimeException re) {
			log.error("delete action failed", re);
			return "fail";
		}
	}

	/**
	 * To list a single user by Id.
	 * @return String
	 */
	@SkipValidation
	public String edit()
	{
		log.debug("edit action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			user = (User) userManager.findUserById(Long.parseLong(request.getParameter("id")));
			userPassword = userManager.findUserPasswordById(user.getUid());
			password = userPassword.getPwd();
			confirmPassword = userPassword.getPwd();
			roleList = roleManager.listRole();
			institutionList = institutionManager.listInstitution();
			
			role = roleManager.findRoleByUserId(user.getUid());
			userrole = role.getDescription();
			if(user.getInstitution().getInstitutionid()> 0){
				institution = institutionManager.findInstitutionById(user.getInstitution().getInstitutionid());
				institutionName = institution.getInstitutionname();
				institutionId=institution.getInstitutionid();
			}
			return "success";
		}catch(RuntimeException re) {
			log.error("edit action failed", re);
			return "fail";
		}
	}	
	
	

	/**
	 * To list all users.
	 * @return String
	 */
	@SkipValidation
	public String list()
	{
		log.debug("list action");
		try{
			userList = userManager.listUser();
			uroleList = roleManager.listUserRole();
			roleList = roleManager.listAllRole();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}
	
	/**
	 * To list all Role.
	 * @return String
	 */
	@SkipValidation
	public String listRole()
	{
		log.debug("list role action");
		try{
			roleList = roleManager.listRole();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list role action failed", re);
			return "fail";
		}
	}

	/**
	 * To validate if email is exist or not .
	 * @return boolean
	 * @throws java.lang.Exception 
	 */
	public boolean findEmail(String emailId){
		log.debug("validate existing email id");
		User userByEmail=null;
		try{
			userByEmail = (User) userManager.findUserByEmail(emailId.trim());
			if (userByEmail == null || userByEmail.getUid()==0){
				return false;
			}else {
				if(userByEmail.getUid() == user.getUid()){
					return false;
				}else{
					return true;
				}
			}
		}catch(RuntimeException re) {
			log.error("validation by email id failed", re);
			return true;
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
				if(userById.getUid() == user.getUid()){
					return false;
				}else{
					return true;
				}
			}
		}catch(RuntimeException re) {
			log.error("validation by user id failed", re);
			return true;
		}
	}

	/**
	 * To disable by user Id.
	 * @return String
	 */
	@SkipValidation
	public String disableUser(){
		log.debug("disable User action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			user = (User) userManager.findUserById(Long.parseLong(request.getParameter("id")));
			if(user.getUid() > 0){
				user.setIsactive('N');
				userManager.updateUserOnly(user);
			}else{
				log.error("Invalid User id action failed");
			}
			return "success";
		}catch(RuntimeException re) {
			log.error("disableUser action failed", re);
			return "fail";
		}
	
	}

	/**
	 * To enable by user Id.
	 * @return String
	 */
	@SkipValidation
	public String enableUser(){
		log.debug("enable User action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			user = (User) userManager.findUserById(Long.parseLong(request.getParameter("id")));
			if(user.getUid() > 0){
				user.setIsactive('Y');
				userManager.updateUserOnly(user);
			}else{
				log.error("Invalid User id action failed");
			}
			return "success";
		}catch(RuntimeException re) {
			log.error("enableUser action failed", re);
			return "fail";
		}
	
	}


	/**
	 * To list a single ProspectiveBorrower by Id.
	 * @return String
	 */
	@SkipValidation
	public String findProspectiveBorrower()
	{
		log.debug("edit action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			id =Long.parseLong(request.getParameter("id"));
			pborrower = pbManager.findPborrowerById(Long.parseLong(request.getParameter("id")));
			listInstitute();
			return "success";
		}catch(RuntimeException re) {
			log.error("edit action failed", re);
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
	 * To list a LoanProduct by  Institution.
	 * @return String
	 */
	@SkipValidation
	public String listLoanList(){
		log.debug("list by Institution action");
		try{
			if(getInstitutionName() != null && !getInstitutionName().equals("")){
				populateLoanProduct(getInstitutionName());
				return "populate";
			}else{
	        	log.debug("listLoanList action failed");
				return "fail";
	        }
		}catch(RuntimeException re) {
			log.error("list By Institution action failed", re);
			return "fail";
		}
		
	}
	
	
	/**
	 * To list a LoanProduct by  Institution.
	 * @return String
	 */
	public void populateLoanProduct(String institutionName)
	{
		log.debug("list by Institution action");
		try{
	        institution =  institutionManager.findInstitutionByName(institutionName);
	        if (institution.getInstitutionid() !=0 ){
		        long insId = institution.getInstitutionid(); 
				lnPrdtList =  lnPrdtManager.findLoanProductNameByInstitutionId(insId);
	        }else{
	        	log.debug("populateLoanProduct action failed");
	        }
		}catch(RuntimeException re) {
			log.error("list By Institution action failed", re);
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
	 * Used to validate 
	 */
	public void validate(){
		if(!StringUtil.isNullOrBlank(userrole)){
			if(userrole.equalsIgnoreCase("Third Party Officer")){
				if(StringUtil.isNullOrBlank(institutionName)){
					addActionError(getText("institutionName.required"));
					log.error("Institution Name is required");
				}
			}
		}
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
	 * @return the loanApplicationsList
	 */
	public List<LoanApplication> getLoanApplicationsList() {
		return loanApplicationsList;
	}

	/**
	 * @param loanApplicationsList the loanApplicationsList to set
	 */
	public void setLoanApplicationsList(List<LoanApplication> loanApplicationsList) {
		this.loanApplicationsList = loanApplicationsList;
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
	 * @return the userRole
	 */
	public UserRole getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return the uroleList
	 */
	public List<UserRole> getUroleList() {
		return uroleList;
	}

	/**
	 * @param uroleList the uroleList to set
	 */
	public void setUroleList(List<UserRole> uroleList) {
		this.uroleList = uroleList;
	}

	/**
	 * @return the userrole
	 */
	public String getUserrole() {
		return userrole;
	}

	/**
	 * @param userrole the userrole to set
	 */
	public void setUserrole(String userrole) {
		this.userrole = userrole;
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
	
}
