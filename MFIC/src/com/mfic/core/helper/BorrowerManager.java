package com.mfic.core.helper;

//import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.BorrowerHome;
import com.mfic.dao.LoanApplicationHome;
import com.mfic.dao.LoanBorrowerHome;
import com.mfic.dao.LoanStatusHome;
import com.mfic.data.Borrower;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanBorrower;
import com.mfic.data.LoanStatus;


public class BorrowerManager {
	private static final Log log = LogFactory.getLog(BorrowerManager.class);
	BorrowerHome borrowerHome = new BorrowerHome();
	LoanApplicationHome loanApplicationHome = new LoanApplicationHome();
	LoanBorrowerHome loanBorrowerHome = new LoanBorrowerHome();
	LoanStatusHome loanStatusHome = new LoanStatusHome();
	
	/**
	 * Used to save or update a Borrower.
	 */
	public void saveOrUpdateBorrower(Borrower borrower)
	{
		log.debug("save Or Updating Borrower instance");
		Session session = BorrowerHome.getSession();
		try{
		session.beginTransaction();
		borrower.setBrwrid(borrowerHome.findMaxBrwrid());
		borrowerHome.saveOrUpdate(borrower);
		
		/*
		Date currentDate = new Date();

		LoanApplication lnapp = new LoanApplication();
		lnapp.setLid(loanApplicationHome.findMaxLid());
		lnapp.setLnprdt(borrower.getLoanProduct());
		lnapp.setBrwrnbr(borrower.getBrwrnos());
		lnapp.setDtlstupdt(currentDate);
		lnapp.setLstupdtuid(borrower.getLstupdtuid());
		lnapp.setAssigneduid(1);
		lnapp.setStep(1);
		lnapp.setReferenceuid((long) 1);
		lnapp.setRecordCode(borrower.getRecordCode());
		lnapp.addLoanBorrower(borrower);
		//lnapp.addLoanStatus();
		this.saveLoanApplication(lnapp);
		
		
		
		LoanBorrower lnbrwr = lnapp.getLnbrwr();
		//LoanStatus lnsts = lnapp.getLnstatus();

		lnbrwr.getId().setLid(lnapp.getLid());
		//lnsts.getId().setLid(lnapp.getLid());

		LoanStatus lnsts = new LoanStatus();
		lnsts.addLoanStatus(lnapp);
		
		this.saveLoanBorrower(lnbrwr);
		this.saveLoanStatus(lnsts);
		*/
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		

	public void saveLoanApplication(LoanApplication loanApplication){
		loanApplicationHome.saveOrUpdate(loanApplication);
	}
	
	public void saveLoanBorrower(LoanBorrower loanBorrower ){
		loanBorrowerHome.saveOrUpdate(loanBorrower);
	}

	public void saveLoanStatus(LoanStatus loanStatus){
		loanStatusHome.saveOrUpdate(loanStatus);
	}
	
	/**
	 * Used to save or update a Borrower.
	 */
	public void updateBorrowerOnly(Borrower borrower)
	{
		log.debug("save Or Updating Borrower instance");
		Session session = BorrowerHome.getSession();
		try{
		session.beginTransaction();
		borrowerHome.saveOrUpdate(borrower);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		

	/**
	 * Used to delete a Borrower.
	 */
	public void deleteBorrower(Long id)
	{
		log.debug("delete Borrower");
		Session session = BorrowerHome.getSession();
		try{
		session.beginTransaction();
		borrowerHome.deleteBorrower(id);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
		
	/**
	 * Used to list all the Borrower.
	 */
	public List<Borrower> listBorrower()
	{
		log.debug("list all Borrower");
		Session session = BorrowerHome.getSession();
		List<Borrower> borrowerList=null;
		try
		{
		session.beginTransaction();
		borrowerList = borrowerHome.listBorrower();
		session.getTransaction().commit();
		return borrowerList;
		}
		catch(RuntimeException re) {
			log.error("list all Borrower failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	
	/**
	 * Used to list a single Borrower by Id.
	 */
	public Borrower findBorrowerById(long brwrId) {
		log.debug("select single Borrower");
		Session session = BorrowerHome.getSession();
		Borrower borrower=null;
		try
		{
			session.beginTransaction();
			borrower=borrowerHome.findBorrowerById(brwrId);
			session.getTransaction().commit();
		return borrower;
		} catch(RuntimeException re) {
			log.error("select single Borrower failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	
	
	/**
	 * Used to list Borrowers by First Name or Last Name.
	 */
	public List<Borrower> searchBorrower(String bFname,String bLname) {
		log.debug("select Borrowers by First Name or Last Name");
		Session session = BorrowerHome.getSession();
		List<Borrower> brwrList=null;
		try
		{
			session.beginTransaction();
			brwrList=borrowerHome.searchBorrower(bFname, bLname);
			session.getTransaction().commit();
			return brwrList;
		} catch(RuntimeException re) {
			log.error("select Borrower by First Name or Last Name failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	
	
	/**
	 * Used to list a single Borrower by user Id.
	 */
	public Borrower findBorrowerByUserId(long uid) {
		log.debug("select single Borrower");
		Session session = BorrowerHome.getSession();
		Borrower borrower=null;
		try
		{
			session.beginTransaction();
			borrower=borrowerHome.findBorrowerByUserId(uid);
			session.getTransaction().commit();
		return borrower;
		} catch(RuntimeException re) {
			log.error("select single Borrower failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	
	
	/**
	 * Used to list all the Borrower.
	 */
	public List<Borrower> listBorrowerByLid(long lid)
	{
		log.debug("list all Borrower");
		Session session = BorrowerHome.getSession();
		List<Borrower> borrowerList=null;
		try
		{
		session.beginTransaction();
		borrowerList = borrowerHome.listBorrowerByLid(lid);
		session.getTransaction().commit();
		return borrowerList;
		}
		catch(RuntimeException re) {
			log.error("list all Borrower failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	
	
	
}
