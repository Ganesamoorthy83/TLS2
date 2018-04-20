package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA


import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mfic.data.LoanProduct;
import com.mfic.data.ProgramAttribute;

/**
 * Home object for domain model class Prgrmattrb.
 * @see com.mfic.dao.Prgrmattrb
 * @author Hibernate Tools
 */
public class ProgramAttributeHome extends BaseHome  {

	private static final Log log = LogFactory.getLog(ProgramAttributeHome.class);

	/**
	 * Used to save or update a Loan Program Attribute.
	 */	
	public void saveOrUpdate(ProgramAttribute programAttribute, boolean isInsert){
		log.debug("persisting ProgramAttribute instance");
		try {
				if(isInsert){
					getSession().save(programAttribute);
				}else {
					getSession().update(programAttribute);
				}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * Used to delete a user.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteProgramAttribute(LoanProduct lnPrdt)
	{
		log.debug("removing User instance");
		try{
			List<ProgramAttribute> prgmAttrbList=null;
			long prdtId =lnPrdt.getLnprdtid();
			String SQL_QUERY ="select lnprgrm from ProgramAttribute lnprgrm join fetch lnprgrm.lnprdt where lnprgrm.lnprdt.lnprdtid = :prgrmId";
			prgmAttrbList =  getSession().createQuery(SQL_QUERY)
			.setParameter("prgrmId", prdtId).list();
			
			Iterator it=prgmAttrbList.iterator();
	        
	        while(it.hasNext()){
	        	getSession().delete((ProgramAttribute)it.next());
	        }
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}	

	/**
	 * Used to list a single ProgramAttribute by Loan Product.
	 */
	@SuppressWarnings("unchecked")
	public List<ProgramAttribute> findProgramAttributeByLoanProduct(LoanProduct lnPrdt) {
		try {
			List<ProgramAttribute> prgmAttrbList=null;
			int prdtId =(int) lnPrdt.getLnprdtid();
			String SQL_QUERY ="select lnprgrm from ProgramAttribute lnprgrm join fetch lnprgrm.lnprdt where lnprgrm.lnprdt.lnprdtid = :prgrmId";
			prgmAttrbList =  getSession().createQuery(SQL_QUERY)
			.setParameter("prgrmId", prdtId).list();

		return prgmAttrbList;
		}catch(RuntimeException re) {
			log.error("find ProgramAttribute failed", re);
			throw re;
		}
	}		
	
/*
	private EntityManager entityManager;

	public void persist(ProgramAttribute transientInstance) {
		log.debug("persisting ProgramAttribute instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ProgramAttribute persistentInstance) {
		log.debug("removing ProgramAttribute instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ProgramAttribute merge(ProgramAttribute detachedInstance) {
		log.debug("merging ProgramAttribute instance");
		try {
			ProgramAttribute result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProgramAttribute findById(ProgramAttributeId id) {
		log.debug("getting ProgramAttribute instance with id: " + id);
		try {
			ProgramAttribute instance = entityManager.find(ProgramAttribute.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
*/
}
