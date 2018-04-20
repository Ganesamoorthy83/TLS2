/**  
  * ProspectiveBorrower.java.
  * Data model class  
 */

package com.mfic.data;

import java.util.HashSet;
import java.util.Set;

public class Institution implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5656800552818456723L;
	private int institutionid;
	private String institutionname;
	private String add1;
	private String add2;
	private String city;
	private String state;
	private String country;
	private String contactName;
	private String contactPhone;
	private String contactEmail;
	private Set<LoanProduct> lnprdts = new HashSet<LoanProduct>(0);
	private Set<User> users = new HashSet<User>(0);
	
	/**  
	  * no argument class constructor
	 */
	public Institution() {
	}

	/**  
	  * arguments constructor  
	 */
	public Institution(int institutionid) {
		this.institutionid = institutionid;
	}

	/**  
	  * arguments constructor   
	 */
	public Institution(int institutionid, String institutionname, String add1,
			String add2, String city, String state, String country,
			String contactName, String contactPhone, String contactEmail,
			Set<LoanProduct> lnprdts, Set<User> users) {
		this.institutionid = institutionid;
		this.institutionname = institutionname;
		this.add1 = add1;
		this.add2 = add2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.lnprdts = lnprdts;
		this.users = users;
	}


	/**  
	  * Retrieve the value of Institution Id.  
	  * @return A Int data type.  
	 */ 
	public int getInstitutionid() {
		return this.institutionid;
	}

	/**  
	  * Set the value of Institution Id.  
	  * @param institutionid A variable of type int.  
	 */
	public void setInstitutionid(int institutionid) {
		this.institutionid = institutionid;
	}


	/**  
	  * Retrieve the value of Institution Name.  
	  * @return A String data type.  
	 */ 
	public String getInstitutionname() {
		return this.institutionname;
	}

	/**  
	  * Set the value of Institution Name.  
	  * @param institutionname A variable of type String.  
	 */
	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}


	/**  
	  * Retrieve the value of Address 1.  
	  * @return A String data type.  
	 */ 
	public String getAdd1() {
		return this.add1;
	}

	/**  
	  * Set the value of Address 1.  
	  * @param add1 A variable of type String.  
	 */
	public void setAdd1(String add1) {
		this.add1 = add1;
	}


	/**  
	  * Retrieve the value of Address 2.  
	  * @return A String data type.  
	 */ 
	public String getAdd2() {
		return this.add2;
	}

	/**  
	  * Set the value of Address 2.  
	  * @param add2 A variable of type String.  
	 */
	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	
	/**  
	  * Retrieve the value of City.  
	  * @return A String data type.  
	 */ 
	public String getCity() {
		return this.city;
	}

	/**  
	  * Set the value of City.  
	  * @param city A variable of type String.  
	 */
	public void setCity(String city) {
		this.city = city;
	}

	
	/**  
	  * Retrieve the value of State.  
	  * @return A String data type.  
	 */ 
	public String getState() {
		return this.state;
	}

	/**  
	  * Set the value of State.  
	  * @param state A variable of type String.  
	 */
	public void setState(String state) {
		this.state = state;
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
	  * Retrieve the value of Contact Name.  
	  * @return A String data type.  
	 */ 
	public String getContactName() {
		return this.contactName;
	}

	/**  
	  * Set the value of Contact Name.  
	  * @param contactname A variable of type String.  
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	
	/**  
	  * Retrieve the value of Contact phone.  
	  * @return A String data type.  
	 */ 
	public String getContactPhone() {
		return this.contactPhone;
	}

	/**  
	  * Set the value of Contact Phone.  
	  * @param contactPhone A variable of type String.  
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	
	/**  
	  * Retrieve the value of Email Address.  
	  * @return A String data type.  
	 */ 
	public String getContactEmail() {
		return this.contactEmail;
	}

	/**  
	  * Set the value of Email Address.  
	  * @param contactEmail A variable of type String.  
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the lnprdts
	 */
	public Set<LoanProduct> getLnprdts() {
		return lnprdts;
	}

	/**
	 * @param lnprdts the lnprdts to set
	 */
	public void setLnprdts(Set<LoanProduct> lnprdts) {
		this.lnprdts = lnprdts;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	

}
