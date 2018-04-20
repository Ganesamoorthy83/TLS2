package com.mfic.core.helper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.LoanApplicationHome;
import com.mfic.dao.LoanAttributeHome;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanAttribute;


public class LoanAttributeManager {
	
	private static final Log log = LogFactory.getLog(LoanApplicationManager.class);
	LoanAttributeHome loanAttributeHome = new LoanAttributeHome();
	LoanApplicationHome loanApplicationHome = new LoanApplicationHome();
	
	/**
	 * Used to save or update a LoanAttribute.
	 */
	public void saveOrUpdateLoanAttribute(LoanAttribute loanAttribute, LoanApplication loanApplication)
	{
		log.debug("save Or Updating LoanAttribute instance");
		Session session = LoanAttributeHome.getSession();
		try{
		session.beginTransaction();
		
		loanAttributeHome.saveOrUpdate(loanAttribute);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		

	/**
	 * Used to delete a LoanAttribute.
	 */
	public void deleteLoanAttribute(Long id)
	{
		log.debug("delete LoanAttribute");
		Session session = LoanAttributeHome.getSession();
		try{
		session.beginTransaction();
		loanAttributeHome.deleteLoanAttribute(id);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
		
	/**
	 * Used to list all the LoanAttribute by Loan Id.
	 */
	public List<LoanAttribute> listLoanAttributeByLoanId(long lid)
	{
		log.debug("list all LoanAttribute");
		Session session = LoanAttributeHome.getSession();
		List<LoanAttribute> lnattrbList=null;
		try
		{
		session.beginTransaction();
		lnattrbList = loanAttributeHome.listLoanAttributeByLoanId(lid);
		session.getTransaction().commit();
		return lnattrbList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanAttribute failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	/**
	 * Used to list a single LoanAttribute by Id.
	 */
	public List<LoanAttribute> findLoanAttributeById(long lid) {
		log.debug("select single LoanAttribute");
		Session session = LoanAttributeHome.getSession();
		List<LoanAttribute> lnattrbList=null;
		try
		{
			session.beginTransaction();
			lnattrbList=loanAttributeHome.findLoanAttributeById(lid);
			session.getTransaction().commit();
		return lnattrbList;
		} catch(RuntimeException re) {
			log.error("select single LoanAttribute failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	

	/**
	 * Used to list a single LoanAttribute by Id.
	 */
	public LoanAttribute findLoanAttributeByLoanId(long lid, int attbId) {
		log.debug("select single LoanAttribute");
		Session session = LoanAttributeHome.getSession();
		LoanAttribute lnattrb=null;
		try
		{
			session.beginTransaction();
			lnattrb=loanAttributeHome.findLoanAttributeByLoanId(lid, attbId);
			session.getTransaction().commit();
		return lnattrb;
		} catch(RuntimeException re) {
			log.error("select single LoanAttribute failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	

}
