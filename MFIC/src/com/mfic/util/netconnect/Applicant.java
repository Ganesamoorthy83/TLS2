package com.mfic.util.netconnect;

public class Applicant {
	
	private ApplicantName Name;
	private String SSN;
	private Address CurrentAddress;
	
	public Applicant() {
		// TODO Auto-generated constructor stub
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getSSN() {
		return SSN;
	}

	public void setName(ApplicantName name) {
		Name = name;
	}

	public ApplicantName getName() {
		return Name;
	}

	public void setCurrentAddress(Address currentAddress) {
		CurrentAddress = currentAddress;
	}

	public Address getCurrentAddress() {
		return CurrentAddress;
	}

}
