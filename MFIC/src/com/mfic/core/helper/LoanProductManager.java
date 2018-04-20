package com.mfic.core.helper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.LoanProductHome;
import com.mfic.dao.ProgramAttributeHome;
import com.mfic.data.LoanProduct;
import com.mfic.data.ProgramAttribute;

public class LoanProductManager {
	private static final Log log = LogFactory.getLog(LoanProductManager.class);
	LoanProductHome lnPrdtHome = new LoanProductHome();
	ProgramAttributeHome prgrmAttributeHome = new ProgramAttributeHome();
	AttributeManager attrbManager = new AttributeManager();
	
	/**
	 * Used to save or update a LoanProduct.
	 */
	public void saveOrUpdateLoanProduct(LoanProduct lnPrdt, HashMap<Integer, String> selectedProdAttrs)
	{
		log.debug("save Or Updating LoanProduct instance");
		Session session = LoanProductHome.getSession();
		try{
		session.beginTransaction();
		boolean isInsert =(lnPrdt.getLnprdtid()==0);
		
		lnPrdtHome.saveOrUpdate(lnPrdt);

		Iterator<Integer> prodAttrKeyItr = selectedProdAttrs.keySet().iterator();
		char attrScope;
		int attrId;
		while(prodAttrKeyItr.hasNext()){
			attrId = prodAttrKeyItr.next();
			attrScope = selectedProdAttrs.get(attrId).charAt(0);
			lnPrdt.addNewProgramAttribute(attrbManager.findAttributeById(attrId), attrScope);
		}

		if(isInsert){
		for(ProgramAttribute programAttribute : lnPrdt.getPrgrmattrbs()){
			saveLoanProgram(programAttribute);
		}
		}else{
			deleteLoanProgram(lnPrdt);
			for(ProgramAttribute programAttribute : lnPrdt.getPrgrmattrbs()){
				saveLoanProgram(programAttribute);
				//updateLoanProgram(programAttribute);
			}
			
		}
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		
	
	/**
	 * Used to save Loan Program.
	 */	
	public void saveLoanProgram(ProgramAttribute programAttribute){
		prgrmAttributeHome.saveOrUpdate(programAttribute, true);
	}

	/**
	 * Used to update Loan Program.
	 */	
	public void updateLoanProgram(ProgramAttribute programAttribute){
		prgrmAttributeHome.saveOrUpdate(programAttribute, false);
	}
	
	/**
	 * Used to Delete Loan Program.
	 */	
	public void deleteLoanProgram(LoanProduct loanProduct){
		prgrmAttributeHome.deleteProgramAttribute(loanProduct);
	}

	/**
	 * Used to Enable Or Disable a LoanProduct.
	 */
	public void enableOrDisableLoanProduct(LoanProduct lnPrdt)
	{
		log.debug("Enable Or Disable LoanProduct instance");
		Session session = LoanProductHome.getSession();
		try{
		session.beginTransaction();
		lnPrdtHome.updateLnprdtOnly(lnPrdt);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("enableOrDisableLoanProduct failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		

	/**
	 * Used to delete a LoanProduct.
	 */
	public void deleteLoanProduct(Long id)
	{
		log.debug("delete LoanProduct");
		Session session = LoanProductHome.getSession();
		try{
		session.beginTransaction();
		lnPrdtHome.deleteLoanProduct(id);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
		
	/**
	 * Used to list all the LoanProduct.
	 */
	public List<LoanProduct> listLoanProduct()
	{
		log.debug("list all LoanProduct");
		Session session = LoanProductHome.getSession();
		List<LoanProduct> lnPrdtList=null;
		try
		{
		session.beginTransaction();
		lnPrdtList = lnPrdtHome.listLoanProduct();
		session.getTransaction().commit();
		return lnPrdtList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanProduct failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}		

	
	/**
	 * Used to list a single LoanProduct by Id.
	 */
	public LoanProduct findLoanProductById(Long lnPrdtId) {
		log.debug("select single LoanProduct");
		Session session = LoanProductHome.getSession();
		LoanProduct lnPrdtList=null;
		try
		{
			session.beginTransaction();
			lnPrdtList=lnPrdtHome.findLoanProductById(lnPrdtId);
			session.getTransaction().commit();
		return lnPrdtList;
		} catch(RuntimeException re) {
			log.error("select single LoanProduct failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}		
	
	/**
	 * Used to list a single LoanProduct by Name.
	 */
	public LoanProduct findLoanProductByName(String lnPrdtName) {
		log.debug("select single LoanProduct by Name");
		Session session = LoanProductHome.getSession();
		LoanProduct lnPrdtList=null;
		try
		{
			session.beginTransaction();
			lnPrdtList=lnPrdtHome.findLoanProductByName(lnPrdtName);
			session.getTransaction().commit();
		return lnPrdtList;
		} catch(RuntimeException re) {
			log.error("select single LoanProduct by Name failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	

	/**
	 * Used to list a single LoanProduct by Id.
	 */
	public List<LoanProduct> findLoanProductByInstitutionId(Long lnsId) {
		log.debug("select single LoanProduct");
		Session session = LoanProductHome.getSession();
		List<LoanProduct> lnPrdtList=null;
		try
		{
			session.beginTransaction();
			lnPrdtList=lnPrdtHome.findLoanProductByInstitutionId(lnsId);
			session.getTransaction().commit();
		return lnPrdtList;
		} catch(RuntimeException re) {
			log.error("select single LoanProduct failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}			

	/**
	 * Used to list LoanProducts by Institution Id.
	 */
	public List<LoanProduct> findLoanProductNameByInstitutionId(Long lnsId) {
		log.debug("list LoanProducts by Institution Id");
		Session session = LoanProductHome.getSession();
		List<LoanProduct> lnPrdtList=null;
		try
		{
			session.beginTransaction();
			lnPrdtList=lnPrdtHome.findLoanProductNameByInstitutionId(lnsId);
			session.getTransaction().commit();
		return lnPrdtList;
		} catch(RuntimeException re) {
			log.error("list LoanProducts by Institution Id failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}				
}
