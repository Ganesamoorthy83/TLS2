package com.mfic.core.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mfic.core.helper.AttributeManager;
import com.mfic.core.helper.InstitutionManager;
import com.mfic.core.helper.LoanApplicationManager;
import com.mfic.core.helper.LoanProductManager;
import com.mfic.core.helper.ProgramAttributeManager;
import com.mfic.data.Attribute;
import com.mfic.data.Institution;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanProduct;
import com.mfic.data.ProgramAttribute;
import com.mfic.util.Country;
import com.mfic.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;




public class LoanProductAction  extends ActionSupport implements ModelDriven<LoanProduct> ,Preparable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3949236828203416365L;
	private static final Log log = LogFactory.getLog(LoanProductAction.class);	

    
	private long id;
	private LoanProduct lnProduct = new LoanProduct();
	private Institution institution = new Institution();
	private List<LoanProduct> lnPrdtList = new ArrayList<LoanProduct>();
	private LoanProductManager lnPrdtManager = new LoanProductManager();

	private List<Attribute> attrbList = new ArrayList<Attribute>();
	private AttributeManager attrbManager = new AttributeManager();
	private List<Institution> institutionList = new ArrayList<Institution>();
	private InstitutionManager institutionManager = new InstitutionManager();
	
	private ProgramAttributeManager prgmAttributeManager = new ProgramAttributeManager();
	private List<ProgramAttribute> prgmAttrbList = new ArrayList<ProgramAttribute>();

	private LoanApplicationManager loanApplicationManager = new LoanApplicationManager();
	private List<LoanApplication> loanApplicationList = new ArrayList<LoanApplication>();
	private Country country = new Country();
	private List<String> countries;
	private String institutionName;
	private int institutionId;

	public LoanProduct getModel()
	{
		return lnProduct;
	}
	
	

	public void prepare(){
		getAttributeAndInstitution();
		listInstitute();
		listCountry();
	}

	/**
	 * To save Collections of  LoanProduct Attributes.
	 * @return HashMap collection set
	 */
	public HashMap<Integer, String> getAttributes(){
		log.debug("getting attribute values start");
		HashMap<Integer, String> selectedProdAttrs = new LinkedHashMap<Integer, String>();
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
				List<Attribute> attrList = attrbManager.listAttribute();
				for(java.util.Iterator<Attribute> itr= attrList.iterator(); itr.hasNext();){
					int i =  itr.next().getAttrbid();
					if (request.getParameter("attrb["+ i + "]") != null)
					{
						int selectedAttrbId = Integer.parseInt(request.getParameter("attrb["+ i + "]"));
						String scope = "O";
						if (request.getParameter("scope["+ i + "]") != null){
							 scope = "M";
						}
						selectedProdAttrs.put(selectedAttrbId, scope);
					}
				}
		}catch(RuntimeException re) {
			log.error("getting attribute values action failed", re);
		}
		return selectedProdAttrs;
	}
	
	/**
	 * To save or update LoanProduct.
	 * @return String
	 */
	public String saveOrUpdate()
	{
		log.debug("saveOrUpdate action");
		try{
			institution = institutionManager.findInstitutionByName(institutionName);
			lnProduct.addNewLoanProduct(institution);
			HashMap<Integer, String> selectedProdAttrs = getAttributes();
			lnPrdtManager.saveOrUpdateLoanProduct(lnProduct, selectedProdAttrs);
		return "success";
		}catch(RuntimeException re) {
			log.error("saveOrUpdate action failed", re);
			return "fail";
		}
	}
	
	/**
	 * To delete a LoanProduct.
	 * @return String
	 */
	@SkipValidation
	public String delete()
	{
		log.debug("delete action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			lnPrdtManager.deleteLoanProduct(Long.parseLong(request.getParameter("id")));
			return "success";
		}catch(RuntimeException re) {
			log.error("delete action failed", re);
			return "fail";
		}
	}
	
	/**
	 * To list a single LoanProduct by Id.
	 * @return String
	 */
	@SuppressWarnings("static-access")
	@SkipValidation
	public String edit()
	{
		log.debug("edit action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			lnProduct = lnPrdtManager.findLoanProductById(Long.parseLong(request.getParameter("id")));
			institutionList = institutionManager.listInstitution();
			attrbList = attrbManager.listAttribute();
			prgmAttrbList =  prgmAttributeManager.findProgramAttributeByLoanProduct(lnProduct);
			institution = institutionManager.findInstitutionById(lnProduct.getInstitution().getInstitutionid());
			institutionId=institution.getInstitutionid();
			countries = country.getCountryNames();
			return "success";
		}catch(RuntimeException re) {
			log.error("edit action failed", re);
			return "fail";
		}
	}	
	
	/**
	 * To list all LoanProduct.
	 * @return String
	 */
	@SkipValidation
	public String list()
	{
		log.debug("list action");
		try{
			lnPrdtList = lnPrdtManager.listLoanProduct();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}	

	/**
	 * To list all LoanProduct.
	 * @return String
	 */
	@SkipValidation
	public String listByLoanProductId()
	{
		log.debug("list action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			lnProduct = lnPrdtManager.findLoanProductById(Long.parseLong(request.getParameter("id")));
			attrbList = attrbManager.listAttribute();
			prgmAttrbList =  prgmAttributeManager.findProgramAttributeByLoanProduct(lnProduct);
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
	public String listByInstitution()
	{
		log.debug("list by Institution action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			if((request.getParameter("institutionName"))!= null) 
			{			
				institution =  institutionManager.findInstitutionByName(request.getParameter("institutionName"));
				if (institution != null) {
			        if (institution.getInstitutionid() !=0 ){
				        long insId = institution.getInstitutionid(); 
						lnPrdtList =  lnPrdtManager.findLoanProductByInstitutionId(insId);
				        return "populate";
			        }else{
			        	log.debug("getInstitutionid action failed");
						return "fail";
			        }
				}
				else{
					return "populate";
				}
			}
		
			else
			{
				//lnPrdtList =  lnPrdtManager.listLoanProduct();
				return "populate";
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
	@SkipValidation
	public String listLoanList(){
		log.debug("list by Institution action");
		try{
			if(getInstitutionName() != null && !getInstitutionName().equals("")){
				populateLoanProduct(getInstitutionName());
				return "populate";
			}else{
	        	log.debug("lisLoanList action failed");
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
	
	/**
	 * To list all LoanProduct.
	 * @return String
	 */
	@SkipValidation
	public String getAttributeAndInstitution()
	{
		log.debug("getAttributeAndInstitution list action");
		try{
			institutionList = institutionManager.listInstitution();
			attrbList = attrbManager.listAttribute();
		return "populate";
		}catch(RuntimeException re) {
			log.error("getAttributeAndInstitution list action failed", re);
			return "fail";
		}
	}	


	/**
	 * Used to validate 
	 */
	public void validate(){
		
		if ( lnProduct.getLnprdtname().length() == 0 ){	
			addFieldError("lnprdtname", getText("loanProduct.required"));
		}
		
		/*		
		if ( lnProduct.getCountry().length() == 0 ){	
			addFieldError("country", getText("country.required"));
		}
		*/
	}

	/**
	 * To enable a LoanProduct .
	 * @return String
	 */
	@SkipValidation
	public String enableLoanProduct()
	{
		log.debug("Enable LoanProduct action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			lnProduct = lnPrdtManager.findLoanProductById(Long.parseLong(request.getParameter("id")));
			if(lnProduct.getLnprdtid()>0){
					lnProduct.setIsactive('Y');
					lnPrdtManager.enableOrDisableLoanProduct(lnProduct);
			}
			return "success";
		}catch(RuntimeException re) {
			log.error("Enable LoanProduct action failed", re);
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
	 * To list Institutes by Country.
	 * @return String
	 */
	@SkipValidation
	public String listInstituteByCountry()
	{	
		log.debug("list Institute by Country action");
		try{

			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			String cntry = request.getParameter("country");
/*			
			if(!StringUtil.isNullOrBlank(countryName)){
				cntry = countryName;
			}
*/			if(!StringUtil.isNullOrBlank(cntry)){
				institutionList = institutionManager.listInstitutionByCountry(cntry);
			}
/*			if(!StringUtil.isNullOrBlank(getCountryName())){
				institutionList = institutionManager.listInstitutionByCountry(getCountryName());
			}
*/
			return "populate";
		}catch(RuntimeException re) {
			log.error("list Institute by Country action failed", re);
			return "fail";
		}
	}	
	
	/**
	 * To Disable a LoanProduct .
	 * @return String
	 */
	/*
	@SkipValidation
	public String disableLoanProduct()
	{
		log.debug("Disable LoanProduct action");
		String str = null;
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			lnProduct = lnPrdtManager.findLoanProductById(Long.parseLong(request.getParameter("id")));
			if(lnProduct.getLnprdtid()>0){
				loanApplicationList = loanApplicationManager.findLoanApplicationByLoanProductId(lnProduct.getLnprdtid());
				if(loanApplicationList == null || loanApplicationList.size() == 0){
					lnProduct.setIsactive('N');
					lnPrdtManager.enableOrDisableLoanProduct(lnProduct);
					str ="success";
				}else{
					log.error("can't disable this LoanProduct " + lnProduct.getLnprdtname());
					str ="fail";
				}
			}
		}catch(RuntimeException re) {
			log.error("Disable LoanProduct action failed", re);
			str ="fail";
		}
		return str;
	}	
	*/
	@SkipValidation
	public String disableLoanProduct()
	{
		log.debug("Disable LoanProduct action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			lnProduct = lnPrdtManager.findLoanProductById(Long.parseLong(request.getParameter("id")));
			if(lnProduct.getLnprdtid()>0){
					lnProduct.setIsactive('N');
					lnPrdtManager.enableOrDisableLoanProduct(lnProduct);
			}
			return "success";
		}catch(RuntimeException re) {
			log.error("Disable LoanProduct action failed", re);
			return "fail";
		}
	}	

	/**  
	  * Retrieve the value of Loan Product Id.  
	  * @return A Long data type.  
	 */ 
	public long getId() {
		return id;
	}

	/**  
	  * Set the value of Loan Product Id.  
	  * @param id A variable of type Long.  
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the attrbManager
	 */
	public AttributeManager getAttrbManager() {
		return attrbManager;
	}

	/**
	 * @param attrbManager the attrbManager to set
	 */
	public void setAttrbManager(AttributeManager attrbManager) {
		this.attrbManager = attrbManager;
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
	 * @return the attrbList
	 */
	public List<Attribute> getAttrbList() {
		return attrbList;
	}

	/**
	 * @param attrbList the attrbList to set
	 */
	public void setAttrbList(List<Attribute> attrbList) {
		this.attrbList = attrbList;
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
	 * @return the loanApplicationList
	 */
	public List<LoanApplication> getLoanApplicationList() {
		return loanApplicationList;
	}



	/**
	 * @param loanApplicationList the loanApplicationList to set
	 */
	public void setLoanApplicationList(List<LoanApplication> loanApplicationList) {
		this.loanApplicationList = loanApplicationList;
	}






	/**
	 * @return the countrys
	 */
	public Country getCountrys() {
		return country;
	}



	/**
	 * @param countrys the countrys to set
	 */
	public void setCountrys(Country country) {
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


}
