package com.mfic.util.netconnect;

public class Product {
	
	private CreditProfileRequest CreditProfile;
	
	public Product() {
		this.setCreditProfileRequest(new CreditProfileRequest());
	}

	public void setCreditProfileRequest(CreditProfileRequest creditProfileRequest) {
		CreditProfile = creditProfileRequest;
	}

	public CreditProfileRequest getCreditProfileRequest() {
		return CreditProfile;
	}

}
