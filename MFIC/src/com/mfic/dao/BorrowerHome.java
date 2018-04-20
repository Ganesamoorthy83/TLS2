package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import com.mfic.data.Borrower;
import com.mfic.util.StringUtil;


/**
 * Home object for domain model class Borrower.
 * @see com.mfic.dao.Borrower
 * @author Hibernate Tools
 */

public class BorrowerHome  extends BaseHome {

	private static final Log log = LogFactory.getLog(BorrowerHome.class);

	
	
	/**
	 * Used to save or update a Borrower.
	 */	
	public void saveOrUpdate(Borrower borrower){
		log.debug("persisting Borrower instance");
		try {
			if (borrower.getBaid() == 0)
			{
				getSession().save(borrower);
			}else{
				getSession().update(borrower);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}		
	
	/**
	 * Used to delete a Borrower.
	 */
	public void deleteBorrower(Long id)
	{
		log.debug("removing Borrower instance");
		try{
			Borrower borrower= (Borrower) getSession().load(Borrower.class, id);
		if (null != borrower)
		{
			getSession().delete(borrower);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}		
		
	/**
	 * Used to list all the Borrower.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Borrower> listBorrower()
	{
		log.debug("List Borrower instance");
		try
		{
			List<Borrower> borrower=null;
			borrower = getSession().createQuery("from Borrower").list();

			return borrower;
		}
		catch(RuntimeException re) {
			log.error("list all Borrower failed", re);
			throw re;
		}
	}	

	
	/**
	 * Used to list a single Borrower by Id.
	 */
	public Borrower findBorrowerById(long brwrId) {
		try {
			Borrower borrower=null;
			String SQL_QUERY ="select b from Borrower b  where b.brwrid = :brwrId";
			borrower =  (Borrower) getSession().createQuery(SQL_QUERY)
						.setParameter("brwrId", brwrId)
						.uniqueResult();
			return borrower;
		}catch(RuntimeException re) {
			log.error("find Borrower failed", re);
			throw re;
		}
	}		

	/**
	 * Used to list a single Borrower by First Name.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Borrower> searchBorrower(String fname,String lname) {
		try {
			List<Borrower> brwrList=null;
			if(!StringUtil.isNullOrBlank(fname) && !StringUtil.isNullOrBlank(lname)){
				String SQL_QUERY ="select b from Borrower b  where b.fname = :firstName and b.lname = :lastName";
				brwrList =  (List<Borrower>) getSession().createQuery(SQL_QUERY)
							.setParameter("firstName", fname)
							.setParameter("lastName", lname)
							.list();
			}else if(!StringUtil.isNullOrBlank(fname) && StringUtil.isNullOrBlank(lname)){
				String SQL_QUERY ="select b from Borrower b  where b.fname = :firstName";
				brwrList =  (List<Borrower>) getSession().createQuery(SQL_QUERY)
							.setParameter("firstName", fname)
							.list();
			}else if(!StringUtil.isNullOrBlank(lname) && StringUtil.isNullOrBlank(fname)){
				String SQL_QUERY ="select b from Borrower b  where b.lname = :lastName";
				brwrList =  (List<Borrower>) getSession().createQuery(SQL_QUERY)
							.setParameter("lastName", lname)
							.list();
			}else if(StringUtil.isNullOrBlank(fname) && StringUtil.isNullOrBlank(lname)){
				brwrList =  getSession().createQuery("from Borrower").list();
				
			}

			
			return brwrList;
		}catch(RuntimeException re) {
			log.error("find Borrower failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single Borrower by Id.
	 */
	public Borrower findBorrowerByUserId(long uid) {
		try {
			Borrower borrower=null;
			String SQL_QUERY ="select b from Borrower b join fetch b.user where b.user.uid = :userId";
			borrower = (Borrower) getSession().createQuery(SQL_QUERY)
			.setParameter("userId", uid)
			.uniqueResult();
			
			return borrower;
		}catch(RuntimeException re) {
			log.error("find Borrower failed", re);
			throw re;
		}
	}		

	/**
	 * Used to find max of brwrid.
	 * @return brwrid type of long
	 */
	public long findMaxBrwrid() {
		try {
			Criteria criteria = getSession() 
			.createCriteria(Borrower.class) 
			.setProjection(Projections.max("brwrid")); 

			Long maxBrwrid = (Long)criteria.uniqueResult(); 
			if(maxBrwrid == null){
				maxBrwrid = (long) 1;
			}else{
				maxBrwrid = maxBrwrid + 1;
			}
			return maxBrwrid;
		}catch(RuntimeException re) {
			log.error("find max of brwrid failed", re);
			throw re;
		}
	}		

	/**
	 * Used to list all the Borrower.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Borrower> listBorrowerByLid(long lid)
	{
		log.debug("List Borrower instance");
		try
		{
			String sqlQuery="select brwrid from lnbrwr where lid = "+String.valueOf(lid)+"";
			List bids = getSession().createSQLQuery(sqlQuery).list();
			
			List<Borrower> borrower=null;
			String SQL_QUERY ="select b from Borrower b join fetch b.user where b.brwrid in (:bwrid)";
			borrower = getSession().createQuery(SQL_QUERY)
			.setParameterList("bwrid", bids)
			.list();
			
			
			return borrower;
		}
		catch(RuntimeException re) {
			log.error("list all Borrower failed", re);
			throw re;
		}
	}	


}
