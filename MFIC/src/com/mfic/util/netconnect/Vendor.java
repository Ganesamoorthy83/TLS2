package com.mfic.util.netconnect;

public class Vendor {
	
	private String VendorNumber;
	
	public Vendor(){
		this.setVendorNumber("123");
	}

	public void setVendorNumber(String vendorNumber) {
		VendorNumber = vendorNumber;
	}

	public String getVendorNumber() {
		return VendorNumber;
	}

}
