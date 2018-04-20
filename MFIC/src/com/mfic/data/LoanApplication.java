package com.mfic.data;

//Generated Jun 17, 2010 11:00:29 AM by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mfic.util.LoanApplicationStatus;

/**
* LoanApplication generated by hbm2java 
*/
public class LoanApplication implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5738621452866788541L;
	private long laid;
	private LoanProduct lnprdt;
	private long lid;
	private int brwrnbr;
	private Date dtlstupdt;
	private Long lstupdtuid;
	private long assigneduid;
	private int step;
	private Long referenceuid;
	private Character recordCode;
	private Character paymentConfirm;
	private String creditReport;
	private String approvalNote;
	private String denialNote;
	private String approvalDoclink;
	private String denialDoclink;
	private String lndisburseDoclink;
	private String lndiscontReason;
	private Date dtlnDisburse;
	private Double lnamount;
	private Double interestRate;
	private String clientName;
	private Double monthPaymnt;
	private String dtmonPaymnt;
	private Double commAmount;
	private String institutionname;
	private String payDoclink;
	private String lnAnalysisDoclink;
	private String invoiceDoclink;
	
	private Set<LoanAttribute> lnattrbs = new HashSet<LoanAttribute>(0);
	private Set<LoanStatus> lnstatuses = new HashSet<LoanStatus>(0);
	private Set<LoanBorrower> lnbrwrs = new HashSet<LoanBorrower>(0);

	public LoanApplication() {
	}

	public LoanApplication(LoanProduct lnprdt, long lid, int brwrnbr, Date dtlstupdt,
			long assigneduid, int step) {
		this.lnprdt = lnprdt;
		this.lid = lid;
		this.brwrnbr = brwrnbr;
		this.dtlstupdt = dtlstupdt;
		this.assigneduid = assigneduid;
		this.step = step;
	}

	public LoanApplication(LoanProduct lnprdt, long lid, int brwrnbr, Date dtlstupdt,
			Long lstupdtuid, long assigneduid, int step, Long referenceuid,
			Character recordCode, Set<LoanAttribute> lnattrbs,
			Set<LoanStatus> lnstatuses, Set<LoanBorrower> lnbrwrs) {
		this.lnprdt = lnprdt;
		this.lid = lid;
		this.brwrnbr = brwrnbr;
		this.dtlstupdt = dtlstupdt;
		this.lstupdtuid = lstupdtuid;
		this.assigneduid = assigneduid;
		this.step = step;
		this.referenceuid = referenceuid;
		this.recordCode = recordCode;
		this.lnattrbs = lnattrbs;
		this.lnstatuses = lnstatuses;
		this.lnbrwrs = lnbrwrs;
	}

	/**
	 * @return the laid
	 */
	public long getLaid() {
		return laid;
	}

	/**
	 * @param laid the laid to set
	 */
	public void setLaid(long laid) {
		this.laid = laid;
	}

	/**
	 * @return the lnprdt
	 */
	public LoanProduct getLnprdt() {
		return lnprdt;
	}

	/**
	 * @param lnprdt the lnprdt to set
	 */
	public void setLnprdt(LoanProduct lnprdt) {
		this.lnprdt = lnprdt;
	}

	/**
	 * @return the lid
	 */
	public long getLid() {
		return lid;
	}

	/**
	 * @param lid the lid to set
	 */
	public void setLid(long lid) {
		this.lid = lid;
	}

	/**
	 * @return the brwrnbr
	 */
	public int getBrwrnbr() {
		return brwrnbr;
	}

	/**
	 * @param brwrnbr the brwrnbr to set
	 */
	public void setBrwrnbr(int brwrnbr) {
		this.brwrnbr = brwrnbr;
	}

	/**
	 * @return the dtlstupdt
	 */
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
	 * @return the assigneduid
	 */
	public long getAssigneduid() {
		return assigneduid;
	}

	/**
	 * @param assigneduid the assigneduid to set
	 */
	public void setAssigneduid(long assigneduid) {
		this.assigneduid = assigneduid;
	}

	/**
	 * @return the step
	 */
	public int getStep() {
		return step;
	}

	/**
	 * @param step the step to set
	 */
	public void setStep(int step) {
		this.step = step;
	}

	/**
	 * @return the referenceuid
	 */
	public Long getReferenceuid() {
		return referenceuid;
	}

	/**
	 * @param referenceuid the referenceuid to set
	 */
	public void setReferenceuid(Long referenceuid) {
		this.referenceuid = referenceuid;
	}

	/**
	 * @return the recordCode
	 */
	public Character getRecordCode() {
		return recordCode;
	}

	/**
	 * @param recordCode the recordCode to set
	 */
	public void setRecordCode(Character recordCode) {
		this.recordCode = recordCode;
	}



	/**
	 * @return the paymentConfirm
	 */
	public Character getPaymentConfirm() {
		return paymentConfirm;
	}

	/**
	 * @param paymentConfirm the paymentConfirm to set
	 */
	public void setPaymentConfirm(Character paymentConfirm) {
		this.paymentConfirm = paymentConfirm;
	}

	/**
	 * @return the creditReport
	 */
	public String getCreditReport() {
		return creditReport;
	}

	/**
	 * @param creditReport the creditReport to set
	 */
	public void setCreditReport(String creditReport) {
		this.creditReport = creditReport;
	}

	/**
	 * @return the approvalNote
	 */
	public String getApprovalNote() {
		return approvalNote;
	}

	/**
	 * @param approvalNote the approvalNote to set
	 */
	public void setApprovalNote(String approvalNote) {
		this.approvalNote = approvalNote;
	}

	/**
	 * @return the denialNote
	 */
	public String getDenialNote() {
		return denialNote;
	}

	/**
	 * @param denialNote the denialNote to set
	 */
	public void setDenialNote(String denialNote) {
		this.denialNote = denialNote;
	}

	/**
	 * @return the approvalDoclink
	 */
	public String getApprovalDoclink() {
		return approvalDoclink;
	}

	/**
	 * @param approvalDoclink the approvalDoclink to set
	 */
	public void setApprovalDoclink(String approvalDoclink) {
		this.approvalDoclink = approvalDoclink;
	}

	/**
	 * @return the denialDoclink
	 */
	public String getDenialDoclink() {
		return denialDoclink;
	}

	/**
	 * @param denialDoclink the denialDoclink to set
	 */
	public void setDenialDoclink(String denialDoclink) {
		this.denialDoclink = denialDoclink;
	}

	/**
	 * @return the lndisburseDoclink
	 */
	public String getLndisburseDoclink() {
		return lndisburseDoclink;
	}

	/**
	 * @param lndisburseDoclink the lndisburseDoclink to set
	 */
	public void setLndisburseDoclink(String lndisburseDoclink) {
		this.lndisburseDoclink = lndisburseDoclink;
	}

	/**
	 * @return the lndiscontReason
	 */
	public String getLndiscontReason() {
		return lndiscontReason;
	}

	/**
	 * @param lndiscontReason the lndiscontReason to set
	 */
	public void setLndiscontReason(String lndiscontReason) {
		this.lndiscontReason = lndiscontReason;
	}

	/**
	 * @return the dtlnDisburse
	 */
	public Date getDtlnDisburse() {
		return dtlnDisburse;
	}

	/**
	 * @param dtlnDisburse the dtlnDisburse to set
	 */
	public void setDtlnDisburse(Date dtlnDisburse) {
		this.dtlnDisburse = dtlnDisburse;
	}



	/**
	 * @return the lnamount
	 */
	public Double getLnamount() {
		return lnamount;
	}

	/**
	 * @param lnamount the lnamount to set
	 */
	public void setLnamount(Double lnamount) {
		this.lnamount = lnamount;
	}

	/**
	 * @return the interestRate
	 */
	public Double getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return the monthPaymnt
	 */
	public Double getMonthPaymnt() {
		return monthPaymnt;
	}

	/**
	 * @param monthPaymnt the monthPaymnt to set
	 */
	public void setMonthPaymnt(Double monthPaymnt) {
		this.monthPaymnt = monthPaymnt;
	}

	/**
	 * @return the dtmonPaymnt
	 */
	public String getDtmonPaymnt() {
		return dtmonPaymnt;
	}

	/**
	 * @param dtmonPaymnt the dtmonPaymnt to set
	 */
	public void setDtmonPaymnt(String dtmonPaymnt) {
		this.dtmonPaymnt = dtmonPaymnt;
	}

	/**
	 * @return the commAmount
	 */
	public Double getCommAmount() {
		return commAmount;
	}

	/**
	 * @param commAmount the commAmount to set
	 */
	public void setCommAmount(Double commAmount) {
		this.commAmount = commAmount;
	}


	/**
	 * @return the institutionname
	 */
	public String getInstitutionname() {
		return institutionname;
	}

	/**
	 * @param institutionname the institutionname to set
	 */
	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}

	/**
	 * @return the payDoclink
	 */
	public String getPayDoclink() {
		return payDoclink;
	}

	/**
	 * @param payDoclink the payDoclink to set
	 */
	public void setPayDoclink(String payDoclink) {
		this.payDoclink = payDoclink;
	}

	/**
	 * @return the lnAnalysisDoclink
	 */
	public String getLnAnalysisDoclink() {
		return lnAnalysisDoclink;
	}

	/**
	 * @param lnAnalysisDoclink the lnAnalysisDoclink to set
	 */
	public void setLnAnalysisDoclink(String lnAnalysisDoclink) {
		this.lnAnalysisDoclink = lnAnalysisDoclink;
	}

	/**
	 * @return the invoiceDoclink
	 */
	public String getInvoiceDoclink() {
		return invoiceDoclink;
	}

	/**
	 * @param invoiceDoclink the invoiceDoclink to set
	 */
	public void setInvoiceDoclink(String invoiceDoclink) {
		this.invoiceDoclink = invoiceDoclink;
	}

	/**
	 * @return the lnattrbs
	 */
	public Set<LoanAttribute> getLnattrbs() {
		return lnattrbs;
	}

	/**
	 * @param lnattrbs the lnattrbs to set
	 */
	public void setLnattrbs(Set<LoanAttribute> lnattrbs) {
		this.lnattrbs = lnattrbs;
	}

	/**
	 * @return the lnstatuses
	 */
	public Set<LoanStatus> getLnstatuses() {
		return lnstatuses;
	}

	/**
	 * @param lnstatuses the lnstatuses to set
	 */
	public void setLnstatuses(Set<LoanStatus> lnstatuses) {
		this.lnstatuses = lnstatuses;
	}

	/**
	 * @return the lnbrwrs
	 */
	public Set<LoanBorrower> getLnbrwrs() {
		return lnbrwrs;
	}

	/**
	 * @param lnbrwrs the lnbrwrs to set
	 */
	public void setLnbrwrs(Set<LoanBorrower> lnbrwrs) {
		this.lnbrwrs = lnbrwrs;
	}

	/**  
	  * Set the new loan borrower by loan application id. 
	  * @param Borrower the brwr to set.  
	 */
	public void addLoanBorrower(Borrower brwr){
		LoanBorrower lnbrwr = new LoanBorrower();
		LoanBorrowerId lnbrwrId = new LoanBorrowerId();
		lnbrwrId.setBrwrid(brwr.getBrwrid());
		lnbrwrId.setLid(getLid());
		
		lnbrwr.setId(lnbrwrId);
		lnbrwr.setBrwr(brwr);
		lnbrwr.setLnapp(this);
		lnbrwr.setDtlstupdt(getDtlstupdt());
		lnbrwr.setLstupdtuid(getLstupdtuid());
		this.getLnbrwrs().add(lnbrwr);
	}
	
	/**  
	  * @return the lnbrwr .  
	 */
	public LoanBorrower getLnbrwr(){
		if(lnbrwrs != null && !lnbrwrs.isEmpty()){
			return lnbrwrs.iterator().next();
		}
		return null;
	}
	
	/**  
	  * Set the new loan status by loan application id. 
	 */
	public void addLoanStatus(){
		LoanStatus lnsts = new LoanStatus();
		lnsts.setLnapp(this);
		lnsts.setDtlstupdt(getDtlstupdt());
		lnsts.setLstupdtuid(getLstupdtuid());
		lnsts.setStep(getStep());
		lnsts.setAssigneduid((long) 1);
		lnsts.setDtassigned(getDtlstupdt());
		lnsts.setDtcompleted(getDtlstupdt());
		lnsts.setRecordCode(getRecordCode());
		this.getLnstatuses().add(lnsts);
	}
	
	/**  
	  * @return the lnstatus .  
	 */
	public LoanStatus getLnstatus(){
		if(lnstatuses != null && !lnstatuses.isEmpty()){
			return lnstatuses.iterator().next();
		}
		return null;
	}

	/**  
	  * Set the loan attribute  by loan application id. 
	  * @param attribute the attribute to set
	  * @param application the application to set
	  * @param value a variable of Type String.
	 */
	public void addLoanAttribute(Attribute attribute, LoanApplication application, String value){
		Date currentDate = new Date();
		LoanAttribute loanAttribute = new LoanAttribute();
		loanAttribute.setAttrb(attribute);
		loanAttribute.setDoclink(value);
		loanAttribute.setValue(value);
		loanAttribute.setDtlstupdt(currentDate);
		loanAttribute.setLnapp(application);
		loanAttribute.setLstupdtuid(getLstupdtuid());
		loanAttribute.setRecordCode('Y');
		this.getLnattrbs().add(loanAttribute);
		
	}
	
	/**  
	  * @return the lnattrbs.  
	 */
	public LoanAttribute getLnattrb(){
		if(lnattrbs != null && !lnattrbs.isEmpty()){
			return lnattrbs.iterator().next();
		}
		return null;
	}
	
	public void addLoanApplication(Borrower borrower){
		Date currentDate = new Date();
		this.setLnprdt(borrower.getLoanProduct());
		this.setBrwrnbr(borrower.getBrwrnos());
		this.setDtlstupdt(currentDate);
		this.setLstupdtuid(borrower.getLstupdtuid());
		this.setAssigneduid(1);
		this.setStep(LoanApplicationStatus.CREATED.getCode());
		this.setReferenceuid((long) 1);
		this.setRecordCode(borrower.getRecordCode());
		this.setPaymentConfirm('N');
		this.addLoanBorrower(borrower);

	}
}

