package com.mfic.core.helper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.LoanBorrowerHome;
import com.mfic.dao.LoanDetail;
import com.mfic.data.LoanBorrower;

public class LoanBorrowerManager {
	private static final Log log = LogFactory.getLog(LoanApplicationManager.class);
	LoanBorrowerHome loanBorrowerHome = new  LoanBorrowerHome(); 
	
	/**
	 * Used to save or update a LoanBorrower.
	 */
	public void saveOrUpdateLoanBorrower(LoanBorrower loanBorrower)
	{
		log.debug("save Or Updating LoanBorrower instance");
		Session session = LoanBorrowerHome.getSession();
		try{
		session.beginTransaction();
		loanBorrowerHome.saveOrUpdate(loanBorrower);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		

	/**
	 * Used to delete a LoanBorrower.
	 */
	public void deleteLoanBorrower(Long id)
	{
		log.debug("delete LoanBorrower");
		Session session = LoanBorrowerHome.getSession();
		try{
		session.beginTransaction();
		loanBorrowerHome.deleteLoanBorrower(id);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
		
	/**
	 * Used to list all the LoanBorrower.
	 */
	public List<LoanBorrower> listLoanBorrower()
	{
		log.debug("list all LoanBorrower");
		Session session = LoanBorrowerHome.getSession();
		List<LoanBorrower> lnbrwrList=null;
		try
		{
		session.beginTransaction();
		lnbrwrList = loanBorrowerHome.listLoanBorrower();
		session.getTransaction().commit();
		return lnbrwrList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanBorrower failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}

	/**
	 * Used to list all the LoanBorrower.
	 */
	public List<LoanDetail> listLoanBorrowers()
	{
		log.debug("list all LoanBorrower");
		Session session = LoanBorrowerHome.getSession();
		List<LoanDetail> lnbrwrList=null;
		try
		{
		session.beginTransaction();
		lnbrwrList = loanBorrowerHome.listLoanBorrowers();
		session.getTransaction().commit();
		return lnbrwrList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanBorrower failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}
	
	/**
	 * Used to list a single LoanBorrower by Id.
	 */
	public LoanBorrower findLoanBorrowerById(long lnbrwrId) {
		log.debug("select single LoanBorrower");
		Session session = LoanBorrowerHome.getSession();
		LoanBorrower lnbrwr=null;
		try
		{
			session.beginTransaction();
			lnbrwr=loanBorrowerHome.findLoanBorrowerById(lnbrwrId);
			session.getTransaction().commit();
		return lnbrwr;
		} catch(RuntimeException re) {
			log.error("select single LoanBorrower failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	
	

}
