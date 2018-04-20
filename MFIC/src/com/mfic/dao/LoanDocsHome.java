package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mfic.data.LoanDocs;

/**
 * Home object for domain model class Lndocs.
 * @see com.mfic.dao.Lndocs
 * @author Hibernate Tools
 */

public class LoanDocsHome extends BaseHome  {

	private static final Log log = LogFactory.getLog(LoanDocsHome.class);

	/**
	 * Used to save or update a LoanDocs.
	 */	
	public void saveOrUpdate(LoanDocs loanDocs){
		log.debug("persisting LoanDocs instance");
		try {
			if (null != loanDocs)
			{
				getSession().save(loanDocs);
			}else{
				getSession().update(loanDocs);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}		

	/**
	 * Used to delete a LoanDocs.
	 */
	public void deleteLoanDocs(Long id)
	{
		log.debug("removing LoanDocs instance");
		try{
			LoanDocs loanDocs= (LoanDocs) getSession().load(LoanDocs.class, id);
		if (null != loanDocs)
		{
			getSession().delete(loanDocs);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}		

	/**
	 * Used to list all the LoanDocs.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanDocs> listLoanDocs()
	{
		log.debug("List LoanDocs instance");
		try
		{
			List<LoanDocs> loanDocs=null;
			loanDocs = getSession().createQuery("from LoanDocs").list();

			return loanDocs;
		}
		catch(RuntimeException re) {
			log.error("list all LoanDocs failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single LoanDocs by Id.
	 */
	public LoanDocs findLoanDocsById(int lndocId) {
		try {
			LoanDocs loanDocs=null;
			loanDocs = (LoanDocs) getSession().get(LoanDocs.class, lndocId);
			return loanDocs;
		}catch(RuntimeException re) {
			log.error("find LoanDocs failed", re);
			throw re;
		}
	}				
	
	
/*
	private EntityManager entityManager;

	public void persist(LoanDocs transientInstance) {
		log.debug("persisting LoanDocs instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(LoanDocs persistentInstance) {
		log.debug("removing LoanDocs instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public LoanDocs merge(LoanDocs detachedInstance) {
		log.debug("merging LoanDocs instance");
		try {
			LoanDocs result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LoanDocs findById(Long id) {
		log.debug("getting LoanDocs instance with id: " + id);
		try {
			LoanDocs instance = entityManager.find(LoanDocs.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
*/	
}
