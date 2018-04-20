/**  
  * ProspectiveBorrower.java.
  * Data model class  
 */

package com.mfic.data;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ProspectiveBorrower {

	private long id;
	private String fname;
	private String lname;
	private String mname;
	private String add1;
	private String add2;
	private String city;
	private String state;
	private String zip;
	private String email;
	private String hphone;
	private String mphone;
	private String ophone;
	private Date dtlstupdt;
	private Long lstupdtuid;
	private String loancountry;
	private String loancontact;
	private String reference;
	private Character isConverted;
	private String country;
	
	/**  
	  * class constructor.  
	 */
	public ProspectiveBorrower() {
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
	 * @return the mname
	 */
	public String getMname() {
		return mname;
	}

	/**
	 * @param mname the mname to set
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}

	/**
	 * @return the add1
	 */
	public String getAdd1() {
		return add1;
	}

	/**
	 * @param add1 the add1 to set
	 */
	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	/**
	 * @return the add2
	 */
	public String getAdd2() {
		return add2;
	}

	/**
	 * @param add2 the add2 to set
	 */
	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
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
	 * @return the hphone
	 */
	public String getHphone() {
		return hphone;
	}

	/**
	 * @param hphone the hphone to set
	 */
	public void setHphone(String hphone) {
		this.hphone = hphone;
	}

	/**
	 * @return the mphone
	 */
	public String getMphone() {
		return mphone;
	}

	/**
	 * @param mphone the mphone to set
	 */
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	/**
	 * @return the ophone
	 */
	public String getOphone() {
		return ophone;
	}

	/**
	 * @param ophone the ophone to set
	 */
	public void setOphone(String ophone) {
		this.ophone = ophone;
	}

	/**
	 * @return the dtlstupdt
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtlstupdt() {
		return dtlstupdt;
	}

	/**
	 * @param dtlstupdt the dtlstupdt to set
	 */
	public void setDtlstupdt(Date dtlstupdt) {
		this.dtlstupdt = dtlstupdt;
	}

	/**
	 * @return the lstupdtuid
	 */
	public Long getLstupdtuid() {
		return lstupdtuid;
	}

	/**
	 * @param lstupdtuid the lstupdtuid to set
	 */
	public void setLstupdtuid(Long lstupdtuid) {
		this.lstupdtuid = lstupdtuid;
	}

	/**
	 * @return the loancountry
	 */
	public String getLoancountry() {
		return loancountry;
	}

	/**
	 * @param loancountry the loancountry to set
	 */
	public void setLoancountry(String loancountry) {
		this.loancountry = loancountry;
	}

	/**
	 * @return the loancontact
	 */
	public String getLoancontact() {
		return loancontact;
	}

	/**
	 * @param loancontact the loancontact to set
	 */
	public void setLoancontact(String loancontact) {
		this.loancontact = loancontact;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the isConverted
	 */
	public Character getIsConverted() {
		return isConverted;
	}

	/**
	 * @param isConverted the isConverted to set
	 */
	public void setIsConverted(Character isConverted) {
		this.isConverted = isConverted;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


}
