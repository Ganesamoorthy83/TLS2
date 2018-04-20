package com.mfic.util.netconnect;

public class ApplicantName {
	
	private String Surname;
	private String First;
	private String Middle;
	private String Gen;
	
	public ApplicantName(String first, String middle, String sur, String gen) {
		this.First = first;
		this.Middle = middle;
		this.Surname = sur;
		this.Gen = gen;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getSurname() {
		return Surname;
	}

	public void setFirst(String first) {
		First = first;
	}

	public String getFirst() {
		return First;
	}

	public void setGen(String gen) {
		Gen = gen;
	}

	public String getGen() {
		return Gen;
	}

	public void setMiddle(String middle) {
		Middle = middle;
	}

	public String getMiddle() {
		return Middle;
	}

}
