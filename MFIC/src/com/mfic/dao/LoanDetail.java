package com.mfic.dao;

public class LoanDetail {

	
	String lid;
	String brwrid;

	public LoanDetail(String lid, String brwrid) {
		super();
		this.lid = lid;
		this.brwrid = brwrid;
	}

	/**
	 * @return the lid
	 */
	public String getLid() {
		return lid;
	}

	/**
	 * @param lid the lid to set
	 */
	public void setLid(String lid) {
		this.lid = lid;
	}

	/**
	 * @return the brwrid
	 */
	public String getBrwrid() {
		return brwrid;
	}

	/**
	 * @param brwrid the brwrid to set
	 */
	public void setBrwrid(String brwrid) {
		this.brwrid = brwrid;
	}
	
}
