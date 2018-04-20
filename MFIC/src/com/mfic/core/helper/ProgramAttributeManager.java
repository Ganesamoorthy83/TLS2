package com.mfic.core.helper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.ProgramAttributeHome;
import com.mfic.data.LoanProduct;
import com.mfic.data.ProgramAttribute;


public class ProgramAttributeManager {
	private static final Log log = LogFactory.getLog(ProgramAttributeManager.class);
	ProgramAttributeHome programAttributeHome = new ProgramAttributeHome();
	
	/**
	 * Used to list a single ProgramAttribute by Id.
	 */
	public List<ProgramAttribute> findProgramAttributeByLoanProduct(LoanProduct lnPrdt) {
		log.debug("select single ProgramAttribute");
		Session session = ProgramAttributeHome.getSession();
		List<ProgramAttribute> prgmAttrbList=null;
		try
		{
			session.beginTransaction();
			prgmAttrbList=programAttributeHome.findProgramAttributeByLoanProduct(lnPrdt);
			session.getTransaction().commit();
		return prgmAttrbList;
		} catch(RuntimeException re) {
			log.error("select single ProgramAttribute failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		

	/**
	 * Used to delete a LoanProduct.
	 */
	public void deleteProgramAttribute(LoanProduct lnprdt)
	{
		log.debug("delete LoanProduct");
		Session session = ProgramAttributeHome.getSession();
		try{
		session.beginTransaction();
		//programAttributeHome.deleteProgramAttribute(lnprdt);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
}
