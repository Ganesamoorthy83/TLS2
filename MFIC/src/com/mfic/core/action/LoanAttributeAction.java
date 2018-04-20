package com.mfic.core.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mfic.core.helper.AttributeManager;
import com.mfic.core.helper.BorrowerManager;
import com.mfic.core.helper.InstitutionManager;
import com.mfic.core.helper.LoanApplicationManager;
import com.mfic.core.helper.LoanAttributeManager;
import com.mfic.core.helper.LoanBorrowerManager;
import com.mfic.core.helper.LoanProductManager;
import com.mfic.core.helper.LoanStatusManager;
import com.mfic.core.helper.ProgramAttributeManager;
import com.mfic.data.Attribute;
import com.mfic.data.Borrower;
import com.mfic.data.Institution;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanAttribute;
import com.mfic.data.LoanBorrower;
import com.mfic.data.LoanProduct;
import com.mfic.data.LoanStatus;
import com.mfic.data.ProgramAttribute;
import com.mfic.util.AppProperty;
import com.mfic.util.AttributeType;
import com.mfic.util.LoanApplicationStatus;
import com.mfic.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class LoanAttributeAction  extends ActionSupport implements ModelDriven<LoanAttribute> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1969207856318172409L;
	private static final Log log = LogFactory.getLog(LoanProductAction.class);	

	private File doc;
	private String contentType;
	private String docFileName;
	private String dataDir = AppProperty.dataDir;
	private ServletContext servletContext; 


	private long lid;
	private LoanProduct lnProduct = new LoanProduct();
	private List<LoanProduct> lnPrdtList = new ArrayList<LoanProduct>();
	private LoanProductManager lnPrdtManager = new LoanProductManager();

	private Attribute attrb = new Attribute();
	private List<Attribute> attrbList = new ArrayList<Attribute>();
	private AttributeManager attrbManager = new AttributeManager();

	private ProgramAttributeManager prgmAttributeManager = new ProgramAttributeManager();
	private List<ProgramAttribute> prgmAttrbList = new ArrayList<ProgramAttribute>();
	
	private LoanAttribute loanAttribute = new LoanAttribute();
	private List<LoanAttribute> loanAttrbList = new ArrayList<LoanAttribute>();
	private LoanAttributeManager loanAttributeManager = new LoanAttributeManager();

	private LoanApplication application = new LoanApplication();
	private LoanApplicationManager applicationManager = new LoanApplicationManager();
	
	private LoanStatus loanStatus = new LoanStatus();
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
	
	@SuppressWarnings("rawtypes")
	private Map session;
	private long lstudtuid ;
	private Date currentDate = new Date();
	private int attrbId;
	private String description;
	private InputStream fileInputStream;
	private String loanStatusDescription;
	private String uploadLabelName;
	private String value;
	private String docName;
	private String creditReport = AppProperty.creditReport;
	private String paymentFile = AppProperty.paymentFile;
	private String invoiceFile = AppProperty.invoiceFile;
	private String attrbType;
	
	
	public LoanAttribute getModel(){
		return loanAttribute;
	
	}

	
	public void docUpload(){
		try {
		dataDir= dataDir + "LID-" + application.getLid() + "-" + attrb.getDescription() + "-" + docFileName;
		File savedFile = new File(dataDir);
		
			FileUtils.copyFile(this.doc,savedFile);
		} catch (IOException e) {
			log.debug("File Uploand failed");
		}
	}

	public void fileUpload(){
		try {
			if(session.get("fileUploadLabel") != null){
				uploadLabelName = (String) session.get("fileUploadLabel");
			}
		dataDir= dataDir + "LID-" + application.getLid() + "-" + this.uploadLabelName + "-" + docFileName;
		File savedFile = new File(dataDir);
		
			FileUtils.copyFile(this.doc,savedFile);
		} catch (IOException e) {
			log.debug("File Uploand failed");
		}
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	
	public String docDownload(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			fileInputStream = new FileInputStream(new File(request.getParameter("docName")));
			String path = request.getParameter("docName");
			String[] dirs = path.split("/");
			for(String dir : dirs){
				docName = dir;
			}
		} catch (FileNotFoundException e) {
			log.error("File download action failed", e);
		}
	    return SUCCESS;
	}	
	
	public String getDocName(){
		return docName;
	}
	/**
	 * To save or update Loan Attributes.
	 * @return String
	 */
	public String saveOrUpdate()
	{
		log.debug("saveOrUpdate action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			attrb = attrbManager.findAttribute((int) Long.parseLong(request.getParameter("attrbid")));
			application= applicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			loanStatus = loanStatusManager.findLatestLoanStatusById(Long.parseLong(request.getParameter("lid")));
			loanAttribute = loanAttributeManager.findLoanAttributeByLoanId(lid, attrb.getAttrbid());
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			
			attrbType = request.getParameter("attrbType");
			if(attrbType.equals(AttributeType.DOC)){
				if(doc != null){
					docUpload();
				}else if(doc == null){
					addActionError(getText("uploadFile.required"));
					return "fail";
				}
			}

			String val = "";
			lstudtuid = Long.parseLong(request.getParameter("lstupdtuid"));
			if(request.getParameter("value") == null){
				val = "";
			}else{
				val = request.getParameter("value");
			}
			if (loanAttribute == null){
				LoanAttribute nloanAttribute = new LoanAttribute();
				nloanAttribute.setLnattrbid((long)0);
				nloanAttribute.setLstupdtuid(lstudtuid);
				nloanAttribute.addLoanAttribute(attrb, application, dataDir, val);
				loanAttributeManager.saveOrUpdateLoanAttribute(nloanAttribute,application);
			}else{
				//update
				loanAttribute.setRecordCode('N');
				loanAttributeManager.saveOrUpdateLoanAttribute(loanAttribute,application);
				
				//insert
				LoanAttribute nloanAttribute = new LoanAttribute();
				nloanAttribute.setLnattrbid((long)0);
				nloanAttribute.setLstupdtuid(lstudtuid);
				nloanAttribute.addLoanAttribute(attrb, application, dataDir, val);
				loanAttributeManager.saveOrUpdateLoanAttribute(nloanAttribute,application);
			}

			// To update loan application
			if (application != null){
				if(application.getStep() == LoanApplicationStatus.CREATED.getCode()){
					application.setLnprdt(lnProduct);
					application.setDtlstupdt(currentDate);
					application.setLstupdtuid(lstudtuid);
					application.setStep(LoanApplicationStatus.INFORMATION_GATHERING.getCode());
					applicationManager.updateLoanApplication(application);
				}else{
					log.debug("already update status");
				}
			}else{
				log.debug("Loan Application object not found action failed");
			}


			// To update loan status
			if (loanStatus.getLstid() > 0){
				if(loanStatus.getStep() == (int)LoanApplicationStatus.CREATED.getCode()){
					// To update loan status in old record
					loanStatus.setDtcompleted(currentDate);
					loanStatus.setRecordCode('N');
					loanStatusManager.saveOrUpdateLoanStatus(loanStatus);
					
					// To insert new record loan status
					LoanStatus nloanStatus = new LoanStatus();
					nloanStatus.addLoanStatus(application);
					loanStatusManager.saveOrUpdateLoanStatus(nloanStatus);
				}else{
					log.debug("already update status");
				}

			}else{
				log.error("Loan Status object not found action failed");
			}
			
			session = ActionContext.getContext().getSession();
			if(session.get("attrbId") != null){
			    session.remove("attrbDescription");
			}
			if(session.get("attrbDescription") != null){
			    session.remove("attrbDescription");
			}
			if(session.get("value") != null){
			    session.remove("value");
			}
			
		return "success";
		}catch(RuntimeException re) {
			log.error("saveOrUpdate action failed", re);
			addActionError(getText("loanAttribute.saveOrUpdate.fail"));
			return "fail";
		}
	}
		
	/**
	 * To find all LoanProduct.
	 * @return String
	 */
	@SkipValidation
	public String findByLoanId()
	{
		log.debug("list action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			application= applicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			attrbList = attrbManager.listAttribute();
			prgmAttrbList =  prgmAttributeManager.findProgramAttributeByLoanProduct(lnProduct);
			loanAttrbList = loanAttributeManager.listLoanAttributeByLoanId(application.getLid());
			loanStatus = loanStatusManager.findLatestLoanStatusById(Long.parseLong(request.getParameter("lid")));
			institution = institutionManager.findInstitutionById(lnProduct.getInstitution().getInstitutionid());

			brwrList = borrowerManager.listBorrowerByLid(Long.parseLong(request.getParameter("lid")));

			loanStatusDescription = LoanApplicationStatus.getDescription(application.getStep());
			
			session = ActionContext.getContext().getSession();
			if(session.get("attrbId") != null){
			    session.remove("attrbDescription");
			}
			if(session.get("attrbDescription") != null){
			    session.remove("attrbDescription");
			}
			if(session.get("value") != null){
			    session.remove("value");
			}
		
			return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed", re);
			return "fail";
		}
	}		

	
	@SuppressWarnings("unchecked")
	public String getAttribute(){
		log.debug("getAttribute action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			attrbId = Integer.parseInt(request.getParameter("attrbid"));
			description = request.getParameter("description");
			lid = Long.parseLong(request.getParameter("lid"));
			loanAttribute = loanAttributeManager.findLoanAttributeByLoanId(lid, attrbId);
			
			session = ActionContext.getContext().getSession();
			session.put("attrbId", attrbId);
			session.put("attrbDescription", description);
			if(loanAttribute != null){
				if(StringUtil.isNullOrBlank(loanAttribute.getValue())){
					session.put("value", "");
				}else{
					session.put("value", loanAttribute.getValue());
				}
			}else{
				session.put("value", "");
			}
			
		return "success";
		}catch(RuntimeException re) {
			log.error("getAttribute action failed", re);
			return "fail";
		}
		
	}

	@SuppressWarnings("unchecked")
	public String getFileLabelName(){
		log.debug("list action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			uploadLabelName = request.getParameter("labelName");
			
			session = ActionContext.getContext().getSession();
			session.put("fileUploadLabel", uploadLabelName);
		return "success";
		}catch(RuntimeException re) {
			log.error("get File Label Name action failed", re);
			return "fail";
		}
		
	}
	

	/**
	 * To saveUploadFile.
	 * @return String
	 */
	public String saveUploadFile()
	{
		log.debug("saveUploadFile action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			application= applicationManager.findLoanApplicationById(Long.parseLong(request.getParameter("lid")));
			lnProduct = lnPrdtManager.findLoanProductById(application.getLnprdt().getLnprdtid());
			session = ActionContext.getContext().getSession();
			String uploadFileType = (String) session.get("fileUploadLabel");

			if(doc != null){
				fileUpload();
			}else if(doc == null){
				addActionError(getText("uploadFile.required"));
				return "fail";
			}

			lstudtuid = Long.parseLong(request.getParameter("lstupdtuid"));

			// To update loan application
			if (application != null){
				if(application.getStep() == LoanApplicationStatus.APPLICATION_VERIFICATION.getCode()){
					application.setLnprdt(lnProduct);
					application.setDtlstupdt(currentDate);
					application.setLstupdtuid(lstudtuid);
					if(uploadFileType.trim().equalsIgnoreCase(creditReport)){
						application.setCreditReport(dataDir);
					}
					if(uploadFileType.trim().equalsIgnoreCase(paymentFile)){
						application.setPaymentConfirm('Y');
						application.setPayDoclink(dataDir);
					}
					applicationManager.updateLoanApplication(application);
				}else if(application.getStep() == LoanApplicationStatus.LOAN_DISBURSED.getCode()){
					if(uploadFileType.trim().equalsIgnoreCase(invoiceFile)){
						application.setLnprdt(lnProduct);
						application.setDtlstupdt(currentDate);
						application.setLstupdtuid(lstudtuid);
						if(uploadFileType.trim().equalsIgnoreCase(invoiceFile)){
							application.setInvoiceDoclink(dataDir);
						}
						applicationManager.updateLoanApplication(application);
					}
				}else if(application.getStep() < LoanApplicationStatus.APPLICATION_VERIFICATION.getCode()){
					if(uploadFileType.trim().equalsIgnoreCase(paymentFile)){
						log.error("payment confirmation set to complete  action failed");
						addActionError(getText("appComplete.Fail"));
						return "fail";
					}
				}else{
					log.debug("already update status");
				}
			}else{
				log.debug("Loan Application object not found action failed");
			}
			
			if(session.get("fileUploadLabel") != null){
			    session.remove("fileUploadLabel");
			}
			
		return "success";
		}catch(RuntimeException re) {
			log.error("saveUploadFile action failed", re);
			addActionError(getText("uploadFail"));
			return "fail";
		}
	}

	/**
	 * @return description loan status.
	 */
	public String getStatusDescription(int step){
		return LoanApplicationStatus.getDescription(step);
	}
	
	/**
	 * @return the doc
	 */
	public File getDoc() {
		return doc;
	}

	/**
	 * @param doc the doc to set
	 */
	public void setDoc(File doc) {
		this.doc = doc;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the docFileName
	 */
	public String getDocFileName() {
		return docFileName;
	}

	/**
	 * @param docFileName the docFileName to set
	 */
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}


	/**
	 * @return the servletContext
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * @param servletContext the servletContext to set
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
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
	 * @return the attrbList
	 */
	public List<Attribute> getAttrbList() {
		return attrbList;
	}



	/**
	 * @param attrbList the attrbList to set
	 */
	public void setAttrbList(List<Attribute> attrbList) {
		this.attrbList = attrbList;
	}



	/**
	 * @return the attrbManager
	 */
	public AttributeManager getAttrbManager() {
		return attrbManager;
	}



	/**
	 * @param attrbManager the attrbManager to set
	 */
	public void setAttrbManager(AttributeManager attrbManager) {
		this.attrbManager = attrbManager;
	}



	/**
	 * @return the prgmAttributeManager
	 */
	public ProgramAttributeManager getPrgmAttributeManager() {
		return prgmAttributeManager;
	}



	/**
	 * @param prgmAttributeManager the prgmAttributeManager to set
	 */
	public void setPrgmAttributeManager(ProgramAttributeManager prgmAttributeManager) {
		this.prgmAttributeManager = prgmAttributeManager;
	}



	/**
	 * @return the prgmAttrbList
	 */
	public List<ProgramAttribute> getPrgmAttrbList() {
		return prgmAttrbList;
	}



	/**
	 * @param prgmAttrbList the prgmAttrbList to set
	 */
	public void setPrgmAttrbList(List<ProgramAttribute> prgmAttrbList) {
		this.prgmAttrbList = prgmAttrbList;
	}

	/**
	 * @return the loanAttribute
	 */
	public LoanAttribute getLoanAttribute() {
		return loanAttribute;
	}

	/**
	 * @param loanAttribute the loanAttribute to set
	 */
	public void setLoanAttribute(LoanAttribute loanAttribute) {
		this.loanAttribute = loanAttribute;
	}

	/**
	 * @return the loanAttributeManager
	 */
	public LoanAttributeManager getLoanAttributeManager() {
		return loanAttributeManager;
	}

	/**
	 * @param loanAttributeManager the loanAttributeManager to set
	 */
	public void setLoanAttributeManager(LoanAttributeManager loanAttributeManager) {
		this.loanAttributeManager = loanAttributeManager;
	}

	/**
	 * @return the loanAttrbList
	 */
	public List<LoanAttribute> getLoanAttrbList() {
		return loanAttrbList;
	}

	/**
	 * @param loanAttrbList the loanAttrbList to set
	 */
	public void setLoanAttrbList(List<LoanAttribute> loanAttrbList) {
		this.loanAttrbList = loanAttrbList;
	}

	/**
	 * @return the application
	 */
	public LoanApplication getApplication() {
		return application;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(LoanApplication application) {
		this.application = application;
	}

	/**
	 * @return the applicationManager
	 */
	public LoanApplicationManager getApplicationManager() {
		return applicationManager;
	}

	/**
	 * @param applicationManager the applicationManager to set
	 */
	public void setApplicationManager(LoanApplicationManager applicationManager) {
		this.applicationManager = applicationManager;
	}
	/**
	 * @return the attrb
	 */
	public Attribute getAttrb() {
		return attrb;
	}


	/**
	 * @param attrb the attrb to set
	 */
	public void setAttrb(Attribute attrb) {
		this.attrb = attrb;
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
	 * @return the lstudtuid
	 */
	public long getLstudtuid() {
		return lstudtuid;
	}

	/**
	 * @param lstudtuid the lstudtuid to set
	 */
	public void setLstudtuid(long lstudtuid) {
		this.lstudtuid = lstudtuid;
	}

	/**
	 * @return the attrbId
	 */
	public int getAttrbId() {
		return attrbId;
	}

	/**
	 * @param attrbId the attrbId to set
	 */
	public void setAttrbId(int attrbId) {
		this.attrbId = attrbId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the loanStatusDescription
	 */
	public String getLoanStatusDescription() {
		return loanStatusDescription;
	}


	/**
	 * @param loanStatusDescription the loanStatusDescription to set
	 */
	public void setLoanStatusDescription(String loanStatusDescription) {
		this.loanStatusDescription = loanStatusDescription;
	}


	/**
	 * @return the uploadLabelName
	 */
	public String getUploadLabelName() {
		return uploadLabelName;
	}


	/**
	 * @param uploadLabelName the uploadLabelName to set
	 */
	public void setUploadLabelName(String uploadLabelName) {
		this.uploadLabelName = uploadLabelName;
	}





	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}


	/**
	 * @return the attrbType
	 */
	public String getAttrbType() {
		return attrbType;
	}


	/**
	 * @param attrbType the attrbType to set
	 */
	public void setAttrbType(String attrbType) {
		this.attrbType = attrbType;
	}


}
