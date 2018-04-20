package com.mfic.core.helper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.LoanApplicationHome;
import com.mfic.dao.LoanBorrowerHome;
import com.mfic.dao.LoanStatusHome;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanBorrower;
import com.mfic.data.LoanStatus;

public class LoanApplicationManager {
	private static final Log log = LogFactory.getLog(LoanApplicationManager.class);
	LoanApplicationHome loanApplicationHome = new  LoanApplicationHome(); 
	LoanBorrowerHome loanBorrowerHome = new LoanBorrowerHome();
	LoanStatusHome loanStatusHome = new LoanStatusHome();
	/**
	 * Used to save or update a LoanApplication.
	 */
	public void saveOrUpdateLoanApplication(LoanApplication loanApplication)
	{
		log.debug("save Or Updating LoanApplication instance");
		Session session = LoanApplicationHome.getSession();
		try{
		session.beginTransaction();
		loanApplication.setLid(loanApplicationHome.findMaxLid());
		LoanBorrower loanBorrower = loanApplication.getLnbrwr();
		boolean isInsert = (loanApplication.getLaid()==0);
		loanApplicationHome.saveOrUpdate(loanApplication);

		if(isInsert){
			loanBorrower.getId().setLid(loanApplication.getLid());
			this.saveLoanBorrower(loanBorrower);
			
		/*	LoanStatus loanStatus = new LoanStatus();
			loanStatus.addLoanStatus(loanApplication);
			this.saveLoanStatus(loanStatus);*/
		}

		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		

	public void saveLoanBorrower(LoanBorrower loanBorrower ){
		loanBorrowerHome.saveOrUpdate(loanBorrower);
	}

	public void saveLoanStatus(LoanStatus loanStatus){
		loanStatusHome.saveOrUpdate(loanStatus);
	}

	/**
	 * Used to save or update a LoanApplication.
	 */
	public void saveLoanApplication(LoanApplication loanApplication)
	{
		log.debug("save Or Updating LoanApplication instance");
		Session session = LoanApplicationHome.getSession();
		try{
		session.beginTransaction();
		loanApplicationHome.save(loanApplication);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		

	/**
	 * Used to update a LoanApplication.
	 */
	public void updateLoanApplication(LoanApplication loanApplication)
	{
		log.debug("save Or Updating LoanApplication instance");
		Session session = LoanApplicationHome.getSession();
		try{
		session.beginTransaction();
		loanApplicationHome.update(loanApplication);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		
	
	/**
	 * Used to delete a LoanApplication.
	 */
	public void deleteLoanApplication(Long id)
	{
		log.debug("delete LoanApplication");
		Session session = LoanApplicationHome.getSession();
		try{
		session.beginTransaction();
		loanApplicationHome.deleteLoanApplication(id);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
		
	/**
	 * Used to list all the LoanApplication.
	 */
	public List<LoanApplication> listLoanApplication()
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplication();
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	

	/**
	 * Used to list all the LoanApplication.
	 */
	public List<LoanApplication> listLoanApplicationInstitutionId(long lnsId)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplicationByInstitutionId(lnsId);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	
	
	/**
	 * Used to list all the LoanApplication.
	 */
	public List<LoanApplication> listLoanApplicationByInstIdAndStatus(long lnsId,int step)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplicationByInstIdAndStatus(lnsId,step);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	
	
	/**
	 * Used to list a single LoanApplication by Id.
	 */
	public LoanApplication findLoanApplicationById(long lid) {
		log.debug("select single LoanApplication");
		Session session = LoanApplicationHome.getSession();
		LoanApplication lnapp=null;
		try
		{
			session.beginTransaction();
			lnapp=loanApplicationHome.findLoanApplicationById(lid);
			session.getTransaction().commit();
		return lnapp;
		} catch(RuntimeException re) {
			log.error("select single LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	

	/**
	 * Used to list all the LoanApplication By User. Id
	 */
	public List<LoanApplication> listLoanApplicationByUser(int uid)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplicationByUser(uid);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}		
	
	/**
	 * Used to list all the LoanApplication By Borrower Id.
	 */
	public List<LoanApplication> listLoanApplicationByBorrowerId(long bid)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplicationByBorrwerId(bid);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	
	
	/**
	 * Used to list all the LoanApplication by status.
	 */
	public List<LoanApplication> listLoanApplicationByStatus(int step)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplicationByStatus(step);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	/**
	 * Used to list all the LoanApplication by status.
	 */
	public List<LoanApplication> listLoanApplicationByStatusBelowStep(int step)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplicationByStatusBelowStep(step);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	/**
	 * Used to list all the LoanApplication by status.
	 */
	public List<LoanApplication> listLoanApplicationByStatusAboveStep(int step)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplicationByStatusAboveStep(step);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	/**
	 * Used to list all the LoanApplication by status.
	 */
	public List<LoanApplication> listLoanApplicationByStatusBetweenSteps(int fstep, int tstep)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplicationByStatusBetweenSteps(fstep,tstep);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	/**
	 * Used to list all the LoanApplication by status.
	 */
	public List<LoanApplication> listLoanApplicationByStatusAndInstitutionId(int step, int insId)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplicationByStatusAndInstitutionId(step, insId);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	
	
	/**
	 * Used to list all the LoanApplication by Payment Confirmation.
	 */
	public List<LoanApplication> listLoanApplicationByPaymentComplete(int step)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listLoanApplicationByPaymentComplete(step);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	
	/**
	 * Used to list a single LoanApplication by Id.
	 */
	public List<LoanApplication> findLoanApplicationByLoanProductId(long lnprdtId) {
		log.debug("select single LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnapp=null;
		try
		{
			session.beginTransaction();
			lnapp=loanApplicationHome.findLoanApplicationByLoanProductId(lnprdtId);
			session.getTransaction().commit();
		return lnapp;
		} catch(RuntimeException re) {
			log.error("select single LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	

	/**
	 * Used to list all the LoanApplication.
	 */
	public List<LoanApplication> listJointLoanApplications(int step)
	{
		log.debug("list all LoanApplication");
		Session session = LoanApplicationHome.getSession();
		List<LoanApplication> lnappList=null;
		try
		{
		session.beginTransaction();
		lnappList = loanApplicationHome.listJointLoanApplications(step);
		session.getTransaction().commit();
		return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	
	
}
