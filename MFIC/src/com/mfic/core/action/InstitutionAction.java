package com.mfic.core.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mfic.core.helper.InstitutionManager;
import com.mfic.data.Institution;
import com.mfic.util.Country;
import com.mfic.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InstitutionAction extends ActionSupport  implements ModelDriven<Institution>,Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4567683171431531961L;
	private static final Log log = LogFactory.getLog(InstitutionAction.class);	

	private Institution institution = new Institution();
	private List<Institution> institutionList = new ArrayList<Institution>();
	private List<Institution> countryList = new ArrayList<Institution>();
	private InstitutionManager institutionManager = new InstitutionManager();
	
	private Country country = new Country();
	private List<String> countries;
	private String countryName;
	
	public Institution getModel()
	{
		return institution;
	}

	public void prepare(){
		listCountry();
	}
	
	/**
	 * To save or update Institution.
	 * @return String
	 */
	public String saveOrUpdate()
	{
		log.debug("saveOrUpdate action");
		try{
			institutionManager.saveOrUpdateInstitution(institution);
			return "success";
		
		}catch(RuntimeException re) {
			log.error("saveOrUpdate action failed", re);
			return "fail";
		}
	}
	

	/**
	 * To delete a Institution.
	 * @return String
	 */
	public String delete()
	{
		log.debug("delete action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			institutionManager.deleteInstitution(Long.parseLong(request.getParameter("institutionid")));
			return "success";
		}catch(RuntimeException re) {
			log.error("delete action failed", re);
			return "fail";
		}
	}

	/**
	 * To list a single Institution by Id.
	 * @return String
	 */
	@SuppressWarnings("static-access")
	@SkipValidation
	public String edit()
	{
		log.debug("edit action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			institution = institutionManager.findInstitutionById(Integer.parseInt(request.getParameter("institutionid")));
			countries = country.getCountryNames();
			return "success";
		}catch(RuntimeException re) {
			log.error("edit action failed", re);
			return "fail";
		}
	}	
	
	
	/**
	 * To list all Institution.
	 * @return String
	 */
	@SuppressWarnings("static-access")
	@SkipValidation
	public String list()
	{	
		log.debug("list action");
		try{
			institutionList = institutionManager.listInstitution();
			countries = country.getCountryNames();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}
	
	@SkipValidation
	public String listAll()
	{	
		log.debug("list action");
		try{
			institutionList = institutionManager.listAllInstitution();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}
	
	//@SuppressWarnings("static-access")
	@SkipValidation
	public String listCountry()
	{	
		log.debug("list action");
		try{
			//countries = country.getCountryNames();
			countryList = institutionManager.listCountry();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}

	/**
	 * To list Institutes by Country.
	 * @return String
	 */
	@SkipValidation
	public String listInstituteByCountry()
	{	
		log.debug("list Institute by Country action");
		try{
			if(!StringUtil.isNullOrBlank(getCountryName())){
				institutionList = institutionManager.listInstitutionByCountry(getCountryName());
			}
		return "populate";
		}catch(RuntimeException re) {
			log.error("list Institute by Country action failed", re);
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
					addActionError(getText("state.required"));
					log.error("State is required");
				}
			}
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

	public List<Institution> getCountryList() {
		return countryList;
	}

	/**
	 * @param institutionList the institutionList to set
	 */
	public void setCountryList(List<Institution> countryList) {
		this.countryList = countryList;
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
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}



}

