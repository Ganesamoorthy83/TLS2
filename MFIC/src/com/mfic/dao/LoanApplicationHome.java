package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import com.mfic.data.LoanApplication;
import com.mfic.data.LoanProduct;

/**
 * Home object for domain model class Lnapp.
 * @see com.mfic.dao.Lnapp
 * @author Hibernate Tools
 */

public class LoanApplicationHome   extends BaseHome {

	private static final Log log = LogFactory.getLog(LoanApplicationHome.class);

	
	/**
	 * Used to save or update a LoanApplication.
	 */	
	public void saveOrUpdate(LoanApplication loanApplication){
		log.debug("persisting LoanApplication instance");
		try {
			if (loanApplication.getLaid() == 0)
			{
				getSession().save(loanApplication);
			}else{
				getSession().update(loanApplication);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}		

	/**
	 * Used to save a LoanApplication.
	 */	
	public void save(LoanApplication loanApplication){
		log.debug("persisting LoanApplication instance");
		try {
			if (loanApplication.getLid() > 0)
			{
				getSession().save(loanApplication);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}			

	/**
	 * Used to update a LoanApplication.
	 */	
	public void update(LoanApplication loanApplication){
		log.debug("persisting LoanApplication instance");
		try {
			if (loanApplication != null)
			{
				getSession().update(loanApplication);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}			

	/**
	 * Used to delete a LoanApplication.
	 */
	public void deleteLoanApplication(Long id)
	{
		log.debug("removing LoanApplication instance");
		try{
			LoanApplication loanApplication= (LoanApplication) getSession().load(LoanApplication.class, id);
		if (null != loanApplication)
		{
			getSession().delete(loanApplication);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}		
			
	/**
	 * Used to list all the LoanApplication.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanApplication> listLoanApplication()
	{
		log.debug("List LoanApplication instance");
		try
		{
			List<LoanApplication> loanApplication=null;
			loanApplication = getSession().createQuery("from LoanApplication").list();

			return loanApplication;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list all the LoanApplication by Institution id.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanApplication> listLoanApplicationByInstitutionId(long lnsId)
	{
		log.debug("List LoanApplication instance");
		try
		{
			List<LoanProduct> lnProducts= new ArrayList<LoanProduct>();
			String SQL_QUERY ="select lnp.lnprdtid from LoanProduct lnp left join fetch lnp.institution where lnp.institution.institutionid = :insid";
			lnProducts =  getSession().createQuery(SQL_QUERY)
			.setParameter("insid", lnsId).list();

			
			List<LoanApplication> lnappList=null;
			String SQL_QUERY1 ="select la from LoanApplication la join fetch la.lnprdt where la.lnprdt.lnprdtid in (:prdtids)";
			lnappList = getSession().createQuery(SQL_QUERY1)
			.setParameterList("prdtids", lnProducts)
			.list();

			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}	
	
	/**
	 * Used to list all the LoanApplication by Institution id.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanApplication> listLoanApplicationByInstIdAndStatus(long lnsId,int step)
	{
		log.debug("List LoanApplication instance");
		try
		{
			List<LoanProduct> lnProducts= new ArrayList<LoanProduct>();
			String SQL_QUERY ="select lnp.lnprdtid from LoanProduct lnp left join fetch lnp.institution where lnp.institution.institutionid = :insid";
			lnProducts =  getSession().createQuery(SQL_QUERY)
			.setParameter("insid", lnsId).list();

			
			List<LoanApplication> lnappList=null;
			String SQL_QUERY1 ="select la from LoanApplication la join fetch la.lnprdt where la.lnprdt.lnprdtid in (:prdtids) and la.step <= :step";
			lnappList = getSession().createQuery(SQL_QUERY1)
			.setParameterList("prdtids", lnProducts)
			.setParameter("step", step)
			.list();

			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single LoanApplication by Id.
	 */
	public LoanApplication findLoanApplicationById(long lnappId) {
		try {
			LoanApplication loanApplication=null;
			loanApplication = (LoanApplication) getSession().get(LoanApplication.class, lnappId);
			return loanApplication;
		}catch(RuntimeException re) {
			log.error("find LoanApplication failed", re);
			throw re;
		}
	}		
	
	/**
	 * Used to list all the LoanApplication By User Id.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<LoanApplication> listLoanApplicationByUser(int uid)
	{
		log.debug("List LoanApplication instance");
		try
		{
			String sqlQuery="select lid from lnbrwr where brwrid in(select brwrid from brwr where uid="+String.valueOf(uid)+")";
			List lids = getSession().createSQLQuery(sqlQuery).list();

			String sqlQuery1="select lnprdtid from lnapp where lid in (select lid from lnbrwr where brwrid in(select brwrid from brwr where uid="+String.valueOf(uid)+"))";
			List lps = getSession().createSQLQuery(sqlQuery1).list();

			List<LoanApplication> lnappList=null;
			String SQL_QUERY ="select la from LoanApplication la join fetch la.lnprdt where la.lnprdt.lnprdtid in (:prdtids) and la.lid in (:lids)";
			lnappList = getSession().createQuery(SQL_QUERY)
			.setParameterList("lids", lids)
			.setParameterList("prdtids", lps)
			.list();

			/*
					String sqlQuery = "select lid, step, lp.lnprdtid,lnprdtname from lnapp la , lnprdt lp "+
						"where la.lnprdtid = lp.lnprdtid and lp.lnprdtid in "+
						"(select la1.lnprdtid from lnapp la1 where la1.lid in "+
						"(select lnbr.lid from lnbrwr lnbr where lnbr.brwrid in "+
						"(select bwr.brwrid from brwr bwr where bwr.uid ="+String.valueOf(uid)+"))) "+
						"and la.lid in "+
						"(select lnbr.lid from lnbrwr lnbr where lnbr.brwrid in"+ 
						"(select bwr.brwrid from brwr bwr where bwr.uid ="+String.valueOf(uid)+"))";
					List lids = getSession().createSQLQuery(sqlQuery).list();

			
					List<LoanAppDetail> lnAppInfos = new ArrayList<LoanAppDetail>();
					for(int i=0; i<lids.size(); i++){
						Object[] values = (Object[])lids.get(i);
						LoanAppDetail loanAppDetail = new LoanAppDetail((String)values[0], (String)values[1], (String)values[3]);
						//for(int j=0; j<values.length; j++){
						//}
					}

			List<LoanApplication> lnappList=null;
			String SQL_QUERY ="select la from LoanApplication la join fetch la.lnprdt where la.lnprdt.lnprdtid in " +
					"(select la1.lnprdt.lnprdtid from LoanApplication la1 where la1.lid in (select lnbr.lnapp.lid from LoanBorrower lnbr where lnbr.brwr.brwrid in (select bwr.brwrid from Borrower bwr where bwr.user.uid =:userId))) " +
					"and la.lid in (select lnbr.lnapp.lid from LoanBorrower lnbr where lnbr.brwr.brwrid in (select bwr.brwrid from Borrower bwr where bwr.user.uid =:userId))";
			lnappList = getSession().createQuery(SQL_QUERY)
			.setParameter("userId", uid).list();
			 */			
			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}		

	/**
	 * Used to list all the LoanApplication By Borrwer Id.
	 */
	@SuppressWarnings({ "unchecked"})
	public List<LoanApplication> listLoanApplicationByBorrwerId(long bid)
	{
		log.debug("List LoanApplication instance");
		try
		{
			
			List<LoanApplication> lnappList=null;
			String SQL_QUERY ="select la from LoanApplication la join fetch la.lnprdt where la.lnprdt.lnprdtid in " +
					"(select la1.lnprdt.lnprdtid from LoanApplication la1 where la1.lid in (select lnbr.lnapp.lid from LoanBorrower lnbr where lnbr.brwr.brwrid = :brwrId)) " +
					"and la.lid in (select lnbr.lnapp.lid from LoanBorrower lnbr where lnbr.brwr.brwrid  = :brwrId) and la.step > 1";
			lnappList = getSession().createQuery(SQL_QUERY)
			.setParameter("brwrId", bid)
			.list();
			
			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}		

	/**
	 * Used to list all the LoanApplication by status.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanApplication> listLoanApplicationByStatus(int step)
	{
		log.debug("List LoanApplication instance");
		try
		{
			List<LoanApplication> lnappList=null;
			lnappList = getSession().createQuery("from LoanApplication lnapp join fetch lnapp.lnprdt where lnapp.step = :step")
			.setParameter("step", step)
			.list();

			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list all the LoanApplication by status.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanApplication> listLoanApplicationByStatusBelowStep(int step)
	{
		log.debug("List LoanApplication instance");
		try
		{
			List<LoanApplication> lnappList=null;
			lnappList = getSession().createQuery("from LoanApplication lnapp where lnapp.step <= :step")
			.setParameter("step", step)
			.list();

			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list all the LoanApplication by status.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanApplication> listLoanApplicationByStatusAboveStep(int step)
	{
		log.debug("List LoanApplication instance");
		try
		{
			List<LoanApplication> lnappList=null;
			lnappList = getSession().createQuery("from LoanApplication lnapp where lnapp.step >= :step")
			.setParameter("step", step)
			.list();

			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}	


	/**
	 * Used to list all the LoanApplication by status.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanApplication> listLoanApplicationByStatusBetweenSteps(int fstep,int tstep)
	{
		log.debug("List LoanApplication instance");
		try
		{
			List<LoanApplication> lnappList=null;
			lnappList = getSession().createQuery("from LoanApplication lnapp where lnapp.step between :fstep and :tstep")
			.setParameter("fstep", fstep)
			.setParameter("tstep", tstep)
			.list();

			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list all the LoanApplication by status And Institution Id.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanApplication> listLoanApplicationByStatusAndInstitutionId(int step, int insId)
	{
		log.debug("List LoanApplication instance");
		try
		{
			List<LoanProduct> lnProducts= new ArrayList<LoanProduct>();
			String SQL_QUERY ="select lnp.lnprdtid from LoanProduct lnp left join fetch lnp.institution where lnp.institution.institutionid = :insid";
			lnProducts =  getSession().createQuery(SQL_QUERY)
			.setParameter("insid", insId).list();
			
			List<LoanApplication> lnappList=null;
			lnappList = getSession().createQuery("from LoanApplication la join fetch la.lnprdt where la.lnprdt.lnprdtid in (:prdtids) and la.step = :step")
			.setParameterList("prdtids", lnProducts)
			.setParameter("step", step)
			.list();

			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}	
	
	/**
	 * Used to list all the LoanApplication by Payment Confirmation.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanApplication> listLoanApplicationByPaymentComplete(int step)
	{
		log.debug("List LoanApplication instance");
		try
		{
			List<LoanApplication> lnappList=null;
			char payConfirm = 'Y';
			lnappList = getSession().createQuery("from LoanApplication lnapp where lnapp.step = :step and lnapp.paymentConfirm = :pc")
			.setParameter("step", step)
			.setParameter("pc", payConfirm)
			.list();

			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single LoanApplication by  LoanProduct Id.
	 */
	
	@SuppressWarnings("unchecked")
	public List<LoanApplication> findLoanApplicationByLoanProductId(long lnprdtId) {
		try {
			List<LoanApplication> loanApplications=null;
			loanApplications = getSession().createQuery("select lnapp from LoanApplication lnapp where lnapp.lnprdt.lnprdtid = :prdtId")
			.setParameter("prdtId", lnprdtId)
			.list();
			return loanApplications;
		}catch(RuntimeException re) {
			log.error("find LoanApplication failed", re);
			throw re;
		}
	}		

	/**
	 * Used to find max of lid.
	 * @return lid type of long
	 */
	public long findMaxLid() {
		try {
			Criteria criteria = getSession() 
		    .createCriteria(LoanApplication.class) 
		    .setProjection(Projections.max("lid")); 
			
			Long maxLid = (Long)criteria.uniqueResult(); 
			if(maxLid == null){
				maxLid = (long) 1;
			}else{
				maxLid = maxLid + 1;
			}

			return maxLid;
		}catch(RuntimeException re) {
			log.error("find max of lid failed", re);
			throw re;
		}
	}		

	/**
	 * Used to list all the LoanApplication.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanApplication> listJointLoanApplications(int step)
	{
		log.debug("List LoanApplication instance");
		try
		{
			List<LoanApplication> lnappList=null;
			String SQL_QUERY ="select lnapp.lid from LoanApplication lnapp where lnapp.step <= :step";
			lnappList = getSession().createQuery(SQL_QUERY)
			.setParameter("step", step)
			.list();
			return lnappList;
		}
		catch(RuntimeException re) {
			log.error("list all LoanApplication failed", re);
			throw re;
		}
	}	
}
