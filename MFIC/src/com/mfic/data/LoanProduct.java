/**  
  * LoanProduct.java.
  * Data model class  
 */

package com.mfic.data;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class LoanProduct implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long lnprdtid;
	private Institution institution;
	private String country;
	private Date dteff;
	private Date dtexp;
	private Character isactive;
	private Long lstupdtuid;
	private Date dtlstupdt;
	private String lnprdtname;
	private String url;
	private Set<UserRole> uroles = new HashSet<UserRole>(0);
	private Set<LoanApplication> lnapps = new HashSet<LoanApplication>(0);
	private Set<ProgramAttribute> prgrmattrbs = new HashSet<ProgramAttribute>(0);
	
	

	/**  
	  * no argument constructor  
	 */
	public LoanProduct() {
	}

	/**  
	  * arguments constructor   
	 */
	public LoanProduct(long lnprdtid, Institution institution) {
		this.lnprdtid = lnprdtid;
		this.institution = institution;
	}

	/**  
	  * arguments constructor   
	 */
	public LoanProduct(long lnprdtid, Institution institution, String country,
			Date dteff, Date dtexp, Character isactive, Long lstupdtuid,
			Date dtlstupdt, Set<UserRole> uroles, Set<LoanApplication> lnapps,
			Set<ProgramAttribute> prgrmattrbs) {
		this.lnprdtid = lnprdtid;
		this.institution = institution;
		this.country = country;
		this.dteff = dteff;
		this.dtexp = dtexp;
		this.isactive = isactive;
		this.lstupdtuid = lstupdtuid;
		this.dtlstupdt = dtlstupdt;
		this.uroles = uroles;
		this.lnapps = lnapps;
		this.prgrmattrbs = prgrmattrbs;
	}

	/**  
	  * Retrieve the value of Loan Product Id.  
	  * @return A Long data type.  
	 */ 
	public long getLnprdtid() {
		return this.lnprdtid;
	}

	/**  
	  * Set the value of Loan Product Id.  
	  * @param lnprdtid A variable of type Long.  
	 */
	public void setLnprdtid(long lnprdtid) {
		this.lnprdtid = lnprdtid;
	}

	/**  
	  * Mapping @ManyToOne relationship.  
	 */ 
	public Institution getInstitution() {
		return this.institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}


	/**  
	  * Retrieve the value of Country.  
	  * @return A String data type.  
	 */ 
	public String getCountry() {
		return this.country;
	}

	/**  
	  * Set the value of Country.  
	  * @param country A variable of type String.  
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**  
	  * Retrieve the value of Effective Date.  
	  * @return A String data type.  
	 */ 
	public Date getDteff() {
		return this.dteff;
	}

	/**  
	  * Set the value of Effective Date.  
	  * @param dteff A variable of type Date.  
	 */
	public void setDteff(Date dteff) {
		this.dteff = dteff;
	}

	/**  
	  * Retrieve the value of Expired Date.  
	  * @return A String data type.  
	 */ 
	public Date getDtexp() {
		return this.dtexp;
	}

	/**  
	  * Set the value of Expired Date.  
	  * @param dtexp A variable of type Date.  
	 */
	public void setDtexp(Date dtexp) {
		this.dtexp = dtexp;
	}

	/**  
	  * Retrieve the value of Loan Product is active or not.  
	  * @return A Character data type.  
	 */ 
	public Character getIsactive() {
		return this.isactive;
	}

	/**  
	  * Set the value of Loan Product is active or not.  
	  * @param isactive A variable of type Character.  
	 */
	public void setIsactive(Character isactive) {
		this.isactive = isactive;
	}

	/**  
	  * Retrieve the value of Last updated user id.  
	  * @return A Long data type.  
	 */ 
	public Long getLstupdtuid() {
		return this.lstupdtuid;
	}

	/**  
	  * Set the value of Last updated user id.  
	  * @param lstupdtuid A variable of type Long.  
	 */
	public void setLstupdtuid(Long lstupdtuid) {
		this.lstupdtuid = lstupdtuid;
	}

	/**  
	  * Retrieve the value of Last updated date.  
	  * @return A Date data type.  
	 */ 
	public Date getDtlstupdt() {
		return this.dtlstupdt;
	}

	/**  
	  * Set the value of Last Update Date.  
	  * @param dtlstupdt A variable of type Date.  
	 */
	public void setDtlstupdt(Date dtlstupdt) {
		this.dtlstupdt = dtlstupdt;
	}


	/**  
	  * Retrieve the value of Loan Product Name.  
	  * @return A String data type.  
	 */ 
	public String getLnprdtname() {
		return lnprdtname;
	}

	/**  
	  * Set the value of Loan Product Name.  
	  * @param lnprdtname A variable of type String.  
	 */
	public void setLnprdtname(String lnprdtname) {
		this.lnprdtname = lnprdtname;
	}

	

	/**  
	  * Retrieve the value of url link (Loan Product Detail static page).  
	  * @return A String data type.  
	 */ 
	public String getUrl() {
		return url;
	}

	/**  
	  * Set the value of url link (Loan Product Detail static page).  
	  * @param url A variable of type String.  
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the uroles
	 */
	public Set<UserRole> getUroles() {
		return uroles;
	}

	/**
	 * @param uroles the uroles to set
	 */
	public void setUroles(Set<UserRole> uroles) {
		this.uroles = uroles;
	}

	/**
	 * @return the lnapps
	 */
	public Set<LoanApplication> getLnapps() {
		return lnapps;
	}

	/**
	 * @param lnapps the lnapps to set
	 */
	public void setLnapps(Set<LoanApplication> lnapps) {
		this.lnapps = lnapps;
	}

	/**
	 * @return the prgrmattrbs
	 */
	public Set<ProgramAttribute> getPrgrmattrbs() {
		return prgrmattrbs;
	}

	/**
	 * @param prgrmattrbs the prgrmattrbs to set
	 */
	public void setPrgrmattrbs(Set<ProgramAttribute> prgrmattrbs) {
		this.prgrmattrbs = prgrmattrbs;
	}


	/**  
	  * used to set new loan product. 
	 */
	public void addNewLoanProduct(Institution institute){
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);	//Adding 1 year to current date
		this.setDteff(currentDate);
		this.setDtexp(cal.getTime());
		this.setDtlstupdt(currentDate);
		this.setLstupdtuid(getLstupdtuid());
		this.setInstitution(institute);

	}
	
	/**  
	  * Set the new loan program  by loan product id. 
	  * @param attrbId the attrbid to set
	  * @param attrbScope a variable of Type char.
	 */
	public void addNewProgramAttribute(Attribute attrb,char attrbScope){
		ProgramAttribute pa = new ProgramAttribute();
		Date currentDate = new Date();
		ProgramAttributeId prgrmId = new ProgramAttributeId();
		prgrmId.setAttrbid(attrb.getAttrbid());
		prgrmId.setLnprgid(lnprdtid);
		
		
		pa.setId(prgrmId);
		pa.setAttrb(attrb);
		pa.setScope(attrbScope);
		pa.setDtlstupdt(currentDate);
		pa.setLstupdtuid(getLstupdtuid());
		pa.setLnprdt(this);
		this.getPrgrmattrbs().add(pa);
		
	}
	
	/**  
	  * @return the prgrmattrb.  
	 */
	public ProgramAttribute getPrgrmattrb(){
		if(prgrmattrbs != null && !prgrmattrbs.isEmpty()){
			return prgrmattrbs.iterator().next();
		}
		return null;
	}
	


}
