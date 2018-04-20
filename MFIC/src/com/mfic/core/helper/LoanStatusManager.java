package com.mfic.core.helper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.LoanStatusHome;
import com.mfic.data.LoanStatus;

public class LoanStatusManager {
	private static final Log log = LogFactory.getLog(LoanApplicationManager.class);
	LoanStatusHome loanStatusHome = new  LoanStatusHome(); 
	
	/**
	 * Used to save or update a LoanStatus.
	 */
	public void saveOrUpdateLoanStatus(LoanStatus loanStatus)
	{
		log.debug("save Or Updating LoanStatus instance");
		Session session = LoanStatusHome.getSession();
		try{
		session.beginTransaction();
		loanStatusHome.saveOrUpdate(loanStatus);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		

	/**
	 * Used to delete a LoanStatus.
	 */
	public void deleteLoanStatus(Long id)
	{
		log.debug("delete LoanStatus");
		Session session = LoanStatusHome.getSession();
		try{
		session.beginTransaction();
		loanStatusHome.deleteLoanStatus(id);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
		
	/**
	 * Used to list all the LoanStatus.
	 */
	public List<LoanStatus> listLoanStatus()
	{
		log.debug("list all LoanStatus");
		Session session = LoanStatusHome.getSession();
		List<LoanStatus> lnstsList=null;
		try
		{
		session.beginTransaction();
		lnstsList = loanStatusHome.listLoanStatus();
		session.getTransaction().commit();
		return lnstsList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanStatus failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	
	/**
	 * Used to list all the LoanStatus.
	 */
	public List<LoanStatus> listLoanStatusByLid(long lid)
	{
		log.debug("list all LoanStatus");
		Session session = LoanStatusHome.getSession();
		List<LoanStatus> lnstsList=null;
		try
		{
		session.beginTransaction();
		lnstsList = loanStatusHome.listLoanStatusByLid(lid);
		session.getTransaction().commit();
		return lnstsList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanStatus failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	/**
	 * Used to list a single LoanStatus by Id.
	 */
	public LoanStatus findLoanStatusById(long lnstsId) {
		log.debug("select single LoanStatus");
		Session session = LoanStatusHome.getSession();
		LoanStatus lnsts=null;
		try
		{
			session.beginTransaction();
			lnsts=loanStatusHome.findLoanStatusById(lnstsId);
			session.getTransaction().commit();
		return lnsts;
		} catch(RuntimeException re) {
			log.error("select single LoanStatus failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	
	

	public LoanStatus findLatestLoanStatusById(long lnstsId) {
		log.debug("select single LoanStatus");
		Session session = LoanStatusHome.getSession();
		LoanStatus lnsts=null;
		try
		{
			session.beginTransaction();
			lnsts=loanStatusHome.findLatestLoanStatusById(lnstsId);
			session.getTransaction().commit();
		return lnsts;
		} catch(RuntimeException re) {
			log.error("select single LoanStatus failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

}
