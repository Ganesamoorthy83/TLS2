package com.mfic.core.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.mfic.core.helper.BorrowerManager;
import com.mfic.core.helper.InstitutionManager;
import com.mfic.core.helper.LoanApplicationManager;
import com.mfic.core.helper.LoanBorrowerManager;
import com.mfic.core.helper.LoanProductManager;
import com.mfic.core.helper.LoanStatusManager;
import com.mfic.core.helper.PBorrowerManager;
import com.mfic.core.helper.UserManager;
import com.mfic.data.Borrower;
import com.mfic.data.Institution;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanBorrower;
import com.mfic.data.LoanProduct;
import com.mfic.data.LoanStatus;
import com.mfic.data.ProspectiveBorrower;
import com.mfic.data.User;
import com.mfic.util.LoanApplicationStatus;
import com.mfic.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReportAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2660796975416328466L;
	private static final Log log = LogFactory.getLog(ReportAction.class);	
	private ProspectiveBorrower pborrower = new ProspectiveBorrower();
	private List<ProspectiveBorrower> pbList = new ArrayList<ProspectiveBorrower>();
	private PBorrowerManager pbManager = new PBorrowerManager();
	
	private User user = new User();
	private List<User> userList = new ArrayList<User>();
	private UserManager userManager = new UserManager();

	private LoanApplication loanApplication = new LoanApplication();
	private List<LoanApplication> loanApplnList = new ArrayList<LoanApplication>();
	private LoanApplicationManager loanApplicationManager = new LoanApplicationManager();

	private LoanProduct lnProduct = new LoanProduct();
	private List<LoanProduct> lnPrdtList = new ArrayList<LoanProduct>();
	private LoanProductManager lnPrdtManager = new LoanProductManager();

	private LoanStatus loanStatus = new LoanStatus();
	private List<LoanStatus> lnstsList = new ArrayList<LoanStatus>();
	private LoanStatusManager loanStatusManager = new LoanStatusManager();

	private Borrower borrower = new Borrower();
	private List<Borrower> brwrList = new ArrayList<Borrower>();
	private BorrowerManager borrowerManager = new BorrowerManager();

	private LoanBorrower loanBorrower = new LoanBorrower();
	private List<LoanBorrower> loanBorrowers = new ArrayList<LoanBorrower>();
	private LoanBorrowerManager loanBorrowerManager = new LoanBorrowerManager();

	private Institution institution = new Institution();
	private List<Institution> institutionList = new ArrayList<Institution>();
	private InstitutionManager institutionManager = new InstitutionManager();
	
	private String rptFileName = "report.csv";
	private InputStream fileInputStream;
	private String docName;

	private String ContentType;
	private String BufferSize;
	
    private static final SimpleDateFormat TIMESTAMP_FORMATTER = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMATTER =  new SimpleDateFormat("dd-MMM-yyyy");

	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	
	public void docDownload() throws Exception {
		fileInputStream = new FileInputStream(new File(rptFileName));
		String path = rptFileName;
		String[] dirs = path.split("/");
		for(String dir : dirs){
			docName = dir;
		}
	}	
	
	public String getDocName(){
		return docName;
	}
	/**
	 * @param docName the docName to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}
    
	/**
	 * To generate a report Prospective Borrowers .
	 * @return String
	 * @throws IOException 
	 */
	@SuppressWarnings("rawtypes")
	public String listAllProspectiveBorrowers() throws IOException{
		try
		{
			pbList = pbManager.listAllPborrower();
		    FileWriter writer = new FileWriter(rptFileName);
		    String add1;
		    String add2;
	 
		    //Header Part
		    writer.append("Id"); writer.append(',');
		    writer.append("First Name"); writer.append(',');
		    writer.append("Last Name"); writer.append(',');
		    writer.append("Middle Name"); writer.append(',');
		    writer.append("Address Line 1"); writer.append(',');
		    writer.append("Address Line 2"); writer.append(',');
		    writer.append("City"); writer.append(',');
		    writer.append("State"); writer.append(',');
		    writer.append("ZIP"); writer.append(',');
		    writer.append("Country"); writer.append(',');
		    writer.append("Email"); writer.append(',');
		    writer.append("Home Phone"); writer.append(',');
		    writer.append("Mobile Phone"); writer.append(',');
		    writer.append("Other Phone"); writer.append(',');
		    writer.append("Loan Country"); writer.append(',');
		    writer.append("Loan Contact"); writer.append(',');
		    writer.append("Reference"); writer.append(',');
		    writer.append("Converted to Borrower");
		    writer.append('\n');

		    //Detail Part
		    for(Iterator iterator= pbList.iterator();iterator.hasNext();){
		    	ProspectiveBorrower pb = (ProspectiveBorrower) iterator.next();
		    	int id = (int) pb.getId();
		        char isConverted = pb.getIsConverted();
		        String stsConverted;
		        if(isConverted == 'Y'){
		        	stsConverted = "Yes";
		        }else{
		        	stsConverted = "No";
		        }
		        add1 = pb.getAdd1();
		        add2 = pb.getAdd2();
		        if(add1 != null){
				    if(add1.indexOf(" ")>0){
				    	add1 = add1.trim();
				    }
				    if(add1.indexOf(",")>0){
				    	add1 = add1.replace(",", "");
				    }
		        }else{
		        	add1="";
		        }

		        if(add2 != null){
		        	if(add2.indexOf(" ")>0){
		        		add2 = add2.trim();
		        	}
		        	if(add2.indexOf(",")>0){
		        		add2 = add2.replace(",", "");
		        	}
		        }else{
		        	add2="";
		        }
		        
			    writer.append(String.valueOf(id)); writer.append(',');
			    writer.append(String.valueOf(pb.getFname())); writer.append(',');
			    writer.append(String.valueOf(pb.getLname())); writer.append(',');
			    writer.append(String.valueOf(pb.getMname())); writer.append(',');
			    writer.append(String.valueOf(add1)); writer.append(',');
			    writer.append(String.valueOf(add2)); writer.append(',');
			    writer.append(String.valueOf(pb.getCity())); writer.append(',');
			    writer.append(String.valueOf(pb.getState())); writer.append(',');
			    writer.append(String.valueOf(pb.getZip())); writer.append(',');
			    writer.append(String.valueOf(pb.getCountry())); writer.append(',');
			    writer.append(String.valueOf(pb.getEmail())); writer.append(',');
			    writer.append(String.valueOf(pb.getHphone())); writer.append(',');
			    writer.append(String.valueOf(pb.getMphone())); writer.append(',');
			    writer.append(String.valueOf(pb.getOphone())); writer.append(',');
			    writer.append(String.valueOf(pb.getLoancountry())); writer.append(',');
			    writer.append(String.valueOf(pb.getLoancontact())); writer.append(',');
			    writer.append(String.valueOf(pb.getReference())); writer.append(',');
			    writer.append(String.valueOf(stsConverted));
	            writer.append('\n');
		    }
	 
		    writer.flush();
		    writer.close();
		    
	    	fileInputStream = new FileInputStream(new File(rptFileName));
		    //addActionMessage(getText("report.success"));
		    return "success";
		}catch(RuntimeException re) {
			log.error("generate a report Prospective Borrowers action failed", re);
			return "fail";
		}

	}

	/**
	 * To generate a report users .
	 * @return String
	 * @throws IOException 
	 */
	@SuppressWarnings("rawtypes")
	public String listAllUsers() throws IOException{
		try
		{
			userList = userManager.listUser();
		    FileWriter writer = new FileWriter(rptFileName);
		    String add1;
		    String add2;
		    
		    //Header Part
		    writer.append("Id"); writer.append(',');
		    writer.append("First Name"); writer.append(',');
		    writer.append("Last Name"); writer.append(',');
		    writer.append("Middle Name"); writer.append(',');
		    writer.append("Address Line 1"); writer.append(',');
		    writer.append("Address Line 2"); writer.append(',');
		    writer.append("City"); writer.append(',');
		    writer.append("State"); writer.append(',');
		    writer.append("ZIP"); writer.append(',');
		    writer.append("Country"); writer.append(',');
		    writer.append("Email"); writer.append(',');
		    writer.append("Home Phone"); writer.append(',');
		    writer.append("Mobile Phone"); writer.append(',');
		    writer.append("Other Phone"); writer.append(',');
		    writer.append("Institution Name"); writer.append(',');
		    writer.append("Is Active"); writer.append(',');
		    writer.append("Userid"); 
		    writer.append('\n');

		    //Detail Part
		    for(Iterator iterator= userList.iterator();iterator.hasNext();){
		    	User user = (User) iterator.next();
		    	int id = (int) user.getUid();
		        char isActive = user.getIsactive();
		        String FInstitution = "";
		        String stsActive;
		        
		        if(isActive == 'Y'){
		        	stsActive = "Yes";
		        }else{
		        	stsActive = "No";
		        }
		        if(user.getInstitution() != null){
		        	FInstitution = user.getInstitution().getInstitutionname();
		        }
		        
		        add1 = user.getAdd1();
		        add2 = user.getAdd2();
		        if(add1 != null){
				    if(add1.indexOf(" ")>0){
				    	add1 = add1.trim();
				    }
				    if(add1.indexOf(",")>0){
				    	add1 = add1.replace(",", "");
				    }
		        }else{
		        	add1="";
		        }

		        if(add2 != null){
		        	if(add2.indexOf(" ")>0){
		        		add2 = add2.trim();
		        	}
		        	if(add2.indexOf(",")>0){
		        		add2 = add2.replace(",", "");
		        	}
		        }else{
		        	add2="";
		        }
		        writer.append(String.valueOf(id)); writer.append(',');
			    writer.append(String.valueOf(user.getFname())); writer.append(',');
			    writer.append(String.valueOf(user.getLname())); writer.append(',');
			    writer.append(String.valueOf(user.getMname())); writer.append(',');
			    writer.append(String.valueOf(add1)); writer.append(',');
			    writer.append(String.valueOf(add2)); writer.append(',');
			    writer.append(String.valueOf(user.getCity())); writer.append(',');
			    writer.append(String.valueOf(user.getState())); writer.append(',');
			    writer.append(String.valueOf(user.getZip())); writer.append(',');
			    writer.append(String.valueOf(user.getCountry())); writer.append(',');
			    writer.append(String.valueOf(user.getEmail())); writer.append(',');
			    writer.append(String.valueOf(user.getHphone())); writer.append(',');
			    writer.append(String.valueOf(user.getMphone())); writer.append(',');
			    writer.append(String.valueOf(user.getOphone())); writer.append(',');
				writer.append(String.valueOf(FInstitution)); writer.append(',');
			    writer.append(String.valueOf(stsActive));writer.append(',');
			    writer.append(String.valueOf(user.getUserid())); 
	            writer.append('\n');
		    }
	 
		    writer.flush();
		    writer.close();
		    
	    	fileInputStream = new FileInputStream(new File(rptFileName));
		    //addActionMessage(getText("report.success"));
		    return "success";
		}catch(RuntimeException re) {
			log.error("generate a report users action failed", re);
			return "fail";
		}
	}

	/**
	 * To generate a report Loan details .
	 * @return String
	 * @throws IOException 
	 */
	@SuppressWarnings("rawtypes")
	public String listAllLoans() throws IOException{
		try
		{
			loanApplnList = loanApplicationManager.listLoanApplication();
		    FileWriter writer = new FileWriter(rptFileName);
	 
		    //Header Part
		    writer.append("Loan Id"); writer.append(',');
		    writer.append("Loan Product Name"); writer.append(',');
		    writer.append("Institution Name"); writer.append(',');
		    writer.append("Borrower Names"); writer.append(',');
		    writer.append("Creation Date"); writer.append(',');
		    writer.append("Application Verification Date"); writer.append(',');
		    writer.append("Payment Completed"); writer.append(',');
		    writer.append("Credit Report Completed"); writer.append(',');
		    writer.append("Loan Approved Date"); writer.append(',');
		    writer.append("Loan Disbursed Date"); writer.append(',');
		    writer.append("Loan Amount"); writer.append(',');
		    writer.append("Interest Rate"); writer.append(',');
		    writer.append("Loan Discontinued Date"); 
		    writer.append('\n');

		    //Detail Part
		    for(Iterator iterator= loanApplnList.iterator();iterator.hasNext();){
		    	LoanApplication loan = (LoanApplication) iterator.next();
				lnProduct = lnPrdtManager.findLoanProductById(loan.getLnprdt().getLnprdtid());
				institution = institutionManager.findInstitutionById(lnProduct.getInstitution().getInstitutionid());
				brwrList = borrowerManager.listBorrowerByLid(loan.getLid());
				lnstsList = loanStatusManager.listLoanStatusByLid(loan.getLid());

				int id = (int) loan.getLid();
		    	double lnamt = 0;
		    	double intRate=0;
		    	String FInstitution = institution.getInstitutionname();
		        String LoanProduct = lnProduct.getLnprdtname();
				String names = "";
				String createdDate="";
				String verifiedDate="";
				String approvalDate="";
				String disburseDate="";
				String discontinueDate="";
		        String paycompleted;
		        String crcompleted;

				for(Iterator it = brwrList.iterator();it.hasNext();){
					Borrower b = (Borrower) it.next();
					if(!StringUtil.isNullOrBlank(b.getFname()))
					{
						if(!StringUtil.isNullOrBlank(b.getLname())){
							names = names + b.getFname()+" "+b.getLname();
						}else{
							names = names + b.getFname();
						}
					}
					if(it.hasNext()){
						names =names +"/";
					}
				}
				
				for(Iterator it = lnstsList.iterator();it.hasNext();){
					LoanStatus status = (LoanStatus) it.next();
					int step= status.getStep();
					if(step == LoanApplicationStatus.CREATED.getCode()){
						createdDate = TIMESTAMP_FORMATTER.format(status.getDtassigned());
					}
					if(step == LoanApplicationStatus.APPLICATION_VERIFICATION.getCode()){
						verifiedDate = TIMESTAMP_FORMATTER.format(status.getDtassigned());
					}
					if(step == LoanApplicationStatus.LOAN_APPROVED.getCode()){
						approvalDate = TIMESTAMP_FORMATTER.format(status.getDtassigned());
					}
					if(step == LoanApplicationStatus.LOAN_DISBURSED.getCode()){
						disburseDate = TIMESTAMP_FORMATTER.format(status.getDtassigned());
					}
					if(step == LoanApplicationStatus.DISCONTINUED.getCode()){
						discontinueDate = TIMESTAMP_FORMATTER.format(status.getDtassigned());
					}
				}

		        
		        if(loan.getPaymentConfirm() == 'Y'){
		        	paycompleted = "Yes";
		        }else{
		        	paycompleted = "No";
		        }
		        if(loan.getCreditReport() != null){
		        	crcompleted = "Yes";
		        }else{
		        	crcompleted = "No";
		        }

		        if(loan.getLnamount() != null){
					lnamt = loan.getLnamount();
				}
				if(loan.getLnamount() != null){
					intRate = loan.getInterestRate();
				}
		        
			    writer.append(String.valueOf(id)); writer.append(',');
			    writer.append(String.valueOf(LoanProduct)); writer.append(',');
			    writer.append(String.valueOf(FInstitution)); writer.append(',');
			    writer.append(String.valueOf(names)); writer.append(',');
			    writer.append(String.valueOf(createdDate)); writer.append(',');
			    writer.append(String.valueOf(verifiedDate)); writer.append(',');
			    writer.append(String.valueOf(paycompleted)); writer.append(',');
			    writer.append(String.valueOf(crcompleted)); writer.append(',');
			    writer.append(String.valueOf(approvalDate)); writer.append(',');
			    writer.append(String.valueOf(disburseDate)); writer.append(',');
			    writer.append(String.valueOf(lnamt)); writer.append(',');
			    writer.append(String.valueOf(intRate)); writer.append(',');
			    writer.append(String.valueOf(discontinueDate)); 
	            writer.append('\n');
		    }
	 
		    writer.flush();
		    writer.close();

	    	fileInputStream = new FileInputStream(new File(rptFileName));
	    	//addActionMessage(getText("report.success"));
		    return "success";
		}catch(RuntimeException re) {
			log.error("generate a report users action failed", re);
			return "fail";
		}

	}

	public String none() {
		return "success";
	}
	
	/**
	 * @return the pborrower
	 */
	public ProspectiveBorrower getPborrower() {
		return pborrower;
	}

	/**
	 * @param pborrower the pborrower to set
	 */
	public void setPborrower(ProspectiveBorrower pborrower) {
		this.pborrower = pborrower;
	}

	/**
	 * @return the pbList
	 */
	public List<ProspectiveBorrower> getPbList() {
		return pbList;
	}

	/**
	 * @param pbList the pbList to set
	 */
	public void setPbList(List<ProspectiveBorrower> pbList) {
		this.pbList = pbList;
	}

	/**
	 * @return the pbManager
	 */
	public PBorrowerManager getPbManager() {
		return pbManager;
	}

	/**
	 * @param pbManager the pbManager to set
	 */
	public void setPbManager(PBorrowerManager pbManager) {
		this.pbManager = pbManager;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * @return the loanApplication
	 */
	public LoanApplication getLoanApplication() {
		return loanApplication;
	}

	/**
	 * @param loanApplication the loanApplication to set
	 */
	public void setLoanApplication(LoanApplication loanApplication) {
		this.loanApplication = loanApplication;
	}

	/**
	 * @return the loanApplnList
	 */
	public List<LoanApplication> getLoanApplnList() {
		return loanApplnList;
	}

	/**
	 * @param loanApplnList the loanApplnList to set
	 */
	public void setLoanApplnList(List<LoanApplication> loanApplnList) {
		this.loanApplnList = loanApplnList;
	}

	/**
	 * @return the loanApplicationManager
	 */
	public LoanApplicationManager getLoanApplicationManager() {
		return loanApplicationManager;
	}

	/**
	 * @param loanApplicationManager the loanApplicationManager to set
	 */
	public void setLoanApplicationManager(
			LoanApplicationManager loanApplicationManager) {
		this.loanApplicationManager = loanApplicationManager;
	}

	/**
	 * @return the lnProduct
	 */
	public LoanProduct getLnProduct() {
		return lnProduct;
	}

	/**
	 * @param lnProduct the lnProduct to set
	 */
	public void setLnProduct(LoanProduct lnProduct) {
		this.lnProduct = lnProduct;
	}

	/**
	 * @return the lnPrdtList
	 */
	public List<LoanProduct> getLnPrdtList() {
		return lnPrdtList;
	}

	/**
	 * @param lnPrdtList the lnPrdtList to set
	 */
	public void setLnPrdtList(List<LoanProduct> lnPrdtList) {
		this.lnPrdtList = lnPrdtList;
	}

	/**
	 * @return the lnPrdtManager
	 */
	public LoanProductManager getLnPrdtManager() {
		return lnPrdtManager;
	}

	/**
	 * @param lnPrdtManager the lnPrdtManager to set
	 */
	public void setLnPrdtManager(LoanProductManager lnPrdtManager) {
		this.lnPrdtManager = lnPrdtManager;
	}

	/**
	 * @return the loanStatus
	 */
	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	/**
	 * @param loanStatus the loanStatus to set
	 */
	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	/**
	 * @return the lnstsList
	 */
	public List<LoanStatus> getLnstsList() {
		return lnstsList;
	}

	/**
	 * @param lnstsList the lnstsList to set
	 */
	public void setLnstsList(List<LoanStatus> lnstsList) {
		this.lnstsList = lnstsList;
	}

	/**
	 * @return the loanStatusManager
	 */
	public LoanStatusManager getLoanStatusManager() {
		return loanStatusManager;
	}

	/**
	 * @param loanStatusManager the loanStatusManager to set
	 */
	public void setLoanStatusManager(LoanStatusManager loanStatusManager) {
		this.loanStatusManager = loanStatusManager;
	}

	/**
	 * @return the borrower
	 */
	public Borrower getBorrower() {
		return borrower;
	}

	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	/**
	 * @return the brwrList
	 */
	public List<Borrower> getBrwrList() {
		return brwrList;
	}

	/**
	 * @param brwrList the brwrList to set
	 */
	public void setBrwrList(List<Borrower> brwrList) {
		this.brwrList = brwrList;
	}

	/**
	 * @return the borrowerManager
	 */
	public BorrowerManager getBorrowerManager() {
		return borrowerManager;
	}

	/**
	 * @param borrowerManager the borrowerManager to set
	 */
	public void setBorrowerManager(BorrowerManager borrowerManager) {
		this.borrowerManager = borrowerManager;
	}

	/**
	 * @return the loanBorrower
	 */
	public LoanBorrower getLoanBorrower() {
		return loanBorrower;
	}

	/**
	 * @param loanBorrower the loanBorrower to set
	 */
	public void setLoanBorrower(LoanBorrower loanBorrower) {
		this.loanBorrower = loanBorrower;
	}

	/**
	 * @return the loanBorrowers
	 */
	public List<LoanBorrower> getLoanBorrowers() {
		return loanBorrowers;
	}

	/**
	 * @param loanBorrowers the loanBorrowers to set
	 */
	public void setLoanBorrowers(List<LoanBorrower> loanBorrowers) {
		this.loanBorrowers = loanBorrowers;
	}

	/**
	 * @return the loanBorrowerManager
	 */
	public LoanBorrowerManager getLoanBorrowerManager() {
		return loanBorrowerManager;
	}

	/**
	 * @param loanBorrowerManager the loanBorrowerManager to set
	 */
	public void setLoanBorrowerManager(LoanBorrowerManager loanBorrowerManager) {
		this.loanBorrowerManager = loanBorrowerManager;
	}

	/**
	 * @return the institution
	 */
	public Institution getInstitution() {
		return institution;
	}

	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	/**
	 * @return the institutionList
	 */
	public List<Institution> getInstitutionList() {
		return institutionList;
	}

	/**
	 * @param institutionList the institutionList to set
	 */
	public void setInstitutionList(List<Institution> institutionList) {
		this.institutionList = institutionList;
	}

	/**
	 * @return the institutionManager
	 */
	public InstitutionManager getInstitutionManager() {
		return institutionManager;
	}

	/**
	 * @param institutionManager the institutionManager to set
	 */
	public void setInstitutionManager(InstitutionManager institutionManager) {
		this.institutionManager = institutionManager;
	}


	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return ContentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		ContentType = contentType;
	}

	/**
	 * @return the bufferSize
	 */
	public String getBufferSize() {
		return BufferSize;
	}

	/**
	 * @param bufferSize the bufferSize to set
	 */
	public void setBufferSize(String bufferSize) {
		BufferSize = bufferSize;
	}
}
