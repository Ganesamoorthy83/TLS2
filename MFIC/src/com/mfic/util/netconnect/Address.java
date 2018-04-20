package com.mfic.util.netconnect;

public class Address {
	
	private String Street;
	private String City;
	private String State;
	private String Zip;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	public Address(String street, String city, String state, String zip) {
		this.Street = street;
		this.City = city;
		this.State = state;
		this.Zip = zip;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getStreet() {
		return Street;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCity() {
		return City;
	}

	public void setState(String state) {
		State = state;
	}

	public String getState() {
		return State;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public String getZip() {
		return Zip;
	}

}
