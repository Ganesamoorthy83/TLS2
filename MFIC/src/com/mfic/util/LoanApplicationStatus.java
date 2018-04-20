package com.mfic.util;

/**
 * This class contains all the possible loan application status.
 */
public enum LoanApplicationStatus {
	
	CREATED(1, "Created"),
	INFORMATION_GATHERING(2, "Information Gathering"),
	APPLICATION_VERIFICATION(3, "Application Verification"),
	APPLICATION_COMPLETE(4, "Application Complete"),
	LOAN_APPROVED(5, "Loan Decision (Approved)"),
	LOAN_DENIED(6, "Loan Decision (Denied)"),
	LOAN_DISBURSED(7, "Loan Disbursed"),
	DISCONTINUED(8, "Discontinued");
	
	private Integer code;
	private String description;
	/**
	 * @param code
	 * @param description
	 */
	private LoanApplicationStatus(Integer code, String description){
		this.code = code;
		this.description = description;
	}
	
	/**
	 * @return Code of an enumeration.
	 */
	public Integer getCode(){
		return code;	}
	
	
	/**
	 * @return Description of an enumeration
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * @param code
	 * @return Description for the given code.
	 */
	public static String getDescription(Integer code){
		for(LoanApplicationStatus loanApplicationStatus : LoanApplicationStatus.values()){
			if(loanApplicationStatus.getCode().equals(code)){
				return loanApplicationStatus.getDescription();
			}
		}
		return null;
	}
}