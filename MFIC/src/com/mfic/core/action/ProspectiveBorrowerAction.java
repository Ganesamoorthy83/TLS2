package com.mfic.core.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mfic.core.helper.InstitutionManager;
import com.mfic.core.helper.LoanProductManager;
import com.mfic.core.helper.PBorrowerManager;
import com.mfic.data.Institution;
import com.mfic.data.LoanProduct;
import com.mfic.data.ProspectiveBorrower;
import com.mfic.util.Country;
import com.mfic.util.MailNotification;
import com.mfic.util.StringUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ProspectiveBorrowerAction extends ActionSupport implements ModelDriven<ProspectiveBorrower>,Preparable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2633595859723962424L;
	private static final Log log = LogFactory.getLog(ProspectiveBorrowerAction.class);

	private long id;
	private String fname;
	private String lname;


	private String email;
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
	private List<Institution> countryList = new ArrayList<Institution>();
	
	private Country country = new Country();
	private List<String> countries;

	

	public ProspectiveBorrower getModel()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		fname =request.getParameter("fname");
		lname =request.getParameter("lname");
		return pborrower;
	}
	
	public void prepare(){
		listInstitute();
		listCountry();
	}

	/**
	 * To save or update user.
	 * @return String
	 */
	public String save()
	{
		return "success";
	}
	
	/**
	 * To save or update ProspectiveBorrower.
	 * @return String
	 */
	public String saveOrUpdate()
	{
		
		
		log.debug("saveOrUpdate action");
		try{
			pborrower.setIsConverted('N');
			pbManager.saveOrUpdatePBorrower(pborrower);
			email=pborrower.getEmail();
			MailNotification notification = new MailNotification();
			notification.sendMailToMFIC(pborrower);
			if(!StringUtil.isNullOrBlank(email)){
				notification.sendMailToLoanApplicant(email);
			}
		return "success";
		}catch(RuntimeException re) {
			log.error("saveOrUpdate action failed", re);
			return "fail";
		}
	}
	
	/**
	 * To update ProspectiveBorrower as rejected.
	 * @return String
	 */
	@SkipValidation
	public String RejectProspectiveBorrower()
	{
		log.debug("Update action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			pborrower = pbManager.findPborrowerById(Long.parseLong(request.getParameter("id")));
			if(pborrower != null){
				pborrower.setIsConverted('R');
				pbManager.saveOrUpdatePBorrower(pborrower);
			}
		return "success";
		}catch(RuntimeException re) {
			log.error("Update action failed", re);
			return "fail";
		}
	}

	/**
	 * To delete a ProspectiveBorrower.
	 * @return String
	 */
	@SkipValidation
	public String delete()
	{
		log.debug("delete action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			pbManager.deletePBorrower(Long.parseLong(request.getParameter("id")));
			return "success";
		}catch(RuntimeException re) {
			log.error("delete action failed", re);
			return "fail";
		}
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
			pborrower = pbManager.findPborrowerById(Long.parseLong(request.getParameter("id")));
			listInstitute();
			return "success";
		}catch(RuntimeException re) {
			log.error("edit action failed", re);
			return "fail";
		}
	}	

	
	/**
	 * To list all ProspectiveBorrowers.
	 * @return String
	 */
	@SkipValidation
	public String list()
	{	
		log.debug("list action");
		try{
			pbList = pbManager.listPborrower();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
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
	 * To list ProspectiveBorrowers by First Name or Last Name.
	 * @return String
	 */
	@SkipValidation
	public String search()
	{
		log.debug("Find by First Name or Last Name action");
		try{
			pbList = pbManager.searchPBorrower(fname, lname);
			return "success";
		}catch(RuntimeException re) {
			log.error("Find by First Name or Last Name action failed", re);
			addActionError(getText("searchNotFound"));
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
	 * Used to validate 
	 */
	@SuppressWarnings("static-access")
	public void validate(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String cntry = request.getParameter("country");
		String st = request.getParameter("state");

		if(!StringUtil.isNullOrBlank(cntry)){
			if(cntry.equalsIgnoreCase(country.getCountryName("USA"))){
				if(StringUtil.isNullOrBlank(st)){
					addFieldError("state", getText("state.required"));
					log.error("State is required");
				}
			}
		}
	}
	
	/**  
	  * Retrieve the value of Id.  
	  * @return A Long data type.  
	 */ 
	public Long getId()
	{
		return id;
	}
	
	/**  
	  * Set the value of Id.  
	  * @param Id A variable of type Long.  
	 */  
	public void setId(Long id)
	{
		this.id=id;
	}

	/**  
	  * Retrieve the value of First Name.  
	  * @return A String data type.  
	 */ 
	public String getFname() {
		return this.fname;
	}
	
	/**  
	  * Set the value of First Name.  
	  * @param fname A variable of type String.  
	 */  
	public void setFname(String fname) {
		this.fname = fname;
	}


	/**  
	  * Retrieve the value of Last Name.  
	  * @return A String data type.  
	 */ 
	public String getLname() {
		return this.lname;
	}

	/**  
	  * Set the value of Last Name.  
	  * @param lname A variable of type String.  
	 */  
	public void setLname(String lname) {
		this.lname = lname;
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
	 * @return the countryList
	 */
	public List<Institution> getCountryList() {
		return countryList;
	}

	/**
	 * @param countryList the countryList to set
	 */
	public void setCountryList(List<Institution> countryList) {
		this.countryList = countryList;
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

