/**  
  * User.java.
  * Data model class  
 */

package com.mfic.data;


import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6723893862515257031L;
	private long uid;
	private Institution institution;
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
	private Character isactive;
	private String userid;
	private String country;
	private Set<UserPassword> upwds = new HashSet<UserPassword>(0);
	private Set<Borrower> brwrs = new HashSet<Borrower>(0);
	private Set<UserRole> uroles = new HashSet<UserRole>(0);
	


	/**  
	  * no argument class constructor.  
	 */
	public User() {
	}

	/**  
	  * arguments constructor.  
	 */
	public User(long uid, String fname, String lname, String add1, String city,
			String state, String zip, String email) {
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.add1 = add1;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
	}

	/**  
	  * arguments constructor.  
	 */
	public User(long uid, Institution institution, String fname, String lname,
			String mname, String add1, String add2, String city, String state,
			String zip, String email, String hphone, String mphone,
			String ophone, Date dtlstupdt, Long lstupdtuid, Set<UserPassword> upwds,
			Set<Borrower> brwrs, Set<UserRole> uroles) {
		this.uid = uid;
		this.institution = institution;
		this.fname = fname;
		this.lname = lname;
		this.mname = mname;
		this.add1 = add1;
		this.add2 = add2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
		this.hphone = hphone;
		this.mphone = mphone;
		this.ophone = ophone;
		this.dtlstupdt = dtlstupdt;
		this.lstupdtuid = lstupdtuid;
		this.upwds = upwds;
		this.brwrs = brwrs;
		this.uroles = uroles;
	}


	/**  
	  * Retrieve the value of User Id.  
	  * @return A Long data type.  
	 */ 
	public long getUid() {
		return this.uid;
	}

	/**  
	  * Set the value of User Id.  
	  * @param uid A variable of type Long.  
	 */
	public void setUid(long uid) {
		this.uid = uid;
	}

	/**  
	  * Retrieve the value Institution.  
	  * @return A Institution class.  
	 */ 
	public Institution getInstitution() {
		return this.institution;
	}

	/**  
	  * Set the value of Institution.  
	  * @param institution A object of Institution class.  
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
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
	  * Retrieve the value of Middle Name.  
	  * @return A String data type.  
	 */ 
	public String getMname() {
		return this.mname;
	}

	/**  
	  * Set the value of Middle Name.  
	  * @param mname A variable of type String.  
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}

	/**  
	  * Retrieve the value of Address 1.  
	  * @return A String data type.  
	 */ 
	public String getAdd1() {
		return this.add1;
	}

	/**  
	  * Set the value of Address.  
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
	  * Retrieve the value of Zip Code.  
	  * @return A String data type.  
	 */ 
	public String getZip() {
		return this.zip;
	}

	/**  
	  * Set the value of Zip Code.  
	  * @param zip A variable of type String.  
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**  
	  * Retrieve the value of Email Address.  
	  * @return A String data type.  
	 */ 
	public String getEmail() {
		return this.email;
	}

	/**  
	  * Set the value of Email Address.  
	  * @param email A variable of type String.  
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**  
	  * Retrieve the value of Home Phone Number.  
	  * @return A String data type.  
	 */ 
	public String getHphone() {
		return this.hphone;
	}

	/**  
	  * Set the value of Home Phone Number.  
	  * @param hphone A variable of type String.  
	 */
	public void setHphone(String hphone) {
		this.hphone = hphone;
	}

	/**  
	  * Retrieve the value of Mobile Phone Number.  
	  * @return A String data type.  
	 */ 
	public String getMphone() {
		return this.mphone;
	}

	/**  
	  * Set the value of Mobile Phone Number.  
	  * @param mphone A variable of type String.  
	 */
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	/**  
	  * Retrieve the value of Other Phone Number.  
	  * @return A String data type.  
	 */ 
	public String getOphone() {
		return this.ophone;
	}

	/**  
	  * Set the value of Other Phone Number.  
	  * @param ophone A variable of type String.  
	 */
	public void setOphone(String ophone) {
		this.ophone = ophone;
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
	  * Retrieve the value of Last updated user id.  
	  * @return A Long data type.  
	 */ 
	public Long getLstupdtuid() {
		return this.lstupdtuid;
	}

	/**  
	  * Set the value of Last Update User id.  
	  * @param dtlstupdt A variable of type Long.  
	 */
	public void setLstupdtuid(Long lstupdtuid) {
		this.lstupdtuid = lstupdtuid;
	}

	
	/**
	 * @return the isactive
	 */
	public Character getIsactive() {
		return isactive;
	}

	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(Character isactive) {
		this.isactive = isactive;
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

	/**
	 * @return the upwds
	 */
	public Set<UserPassword> getUpwds() {
		return upwds;
	}

	/**
	 * @param upwds the upwds to set
	 */
	public void setUpwds(Set<UserPassword> upwds) {
		this.upwds = upwds;
	}

	/**
	 * @return the brwrs
	 */
	public Set<Borrower> getBrwrs() {
		return brwrs;
	}

	/**
	 * @param brwrs the brwrs to set
	 */
	public void setBrwrs(Set<Borrower> brwrs) {
		this.brwrs = brwrs;
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
	  * Set the new password by User id. 
	  * @param password A variable of type String.  
	 */
	public void addNewPassword(String password){
		UserPassword up = new UserPassword();
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);	//Adding 1 year to current date
		up.setDteff(currentDate);
		UserPasswordId upId = new UserPasswordId();
		upId.setDtexp(cal.getTime());
		up.setId(upId);
		up.setPwd(password);
		up.setDtlstupdt(currentDate);
		up.setLstupdtuid(getLstupdtuid());
		up.setUser(this);
		this.getUpwds().add(up);
	}

	/**  
	  * @return the upwd .  
	 */
	public UserPassword getUpwd(){
		if(upwds != null && !upwds.isEmpty()){
			return upwds.iterator().next();
		}
		return null;
	}

	/**  
	  * Set the new Role by User id.  
	  * @param rid A variable of type Integer.  
	 */
	public void addNewRole(int rid){
		UserRole ur = new UserRole();
		UserRoleId urId = new UserRoleId();
		Date currentDate = new Date();
		urId.setRid(rid);
		urId.setLnprdtid(1);
		ur.setId(urId);
		ur.setDtlstupdt(currentDate);
		ur.setLstupdtuid(getLstupdtuid());
		ur.setUser(this);
		this.getUroles().add(ur);
	}
	
	/**  
	  * @return the urole.  
	 */
	public UserRole getUrole(){
		if(uroles != null && !uroles.isEmpty()){
			return uroles.iterator().next();
		}
		return null;
		
	}
}
