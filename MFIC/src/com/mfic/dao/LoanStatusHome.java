package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.mfic.data.LoanApplication;
import com.mfic.data.LoanStatus;

/**
 * Home object for domain model class Lnstatus.
 * @see com.mfic.dao.Lnstatus
 * @author Hibernate Tools
 */

public class LoanStatusHome  extends BaseHome {

	private static final Log log = LogFactory.getLog(LoanStatusHome.class);

	/**
	 * Used to save or update a LoanStatus.
	 */	
	public void saveOrUpdate(LoanStatus loanStatus){
		log.debug("persisting LoanStatus instance");
		try {
			if (loanStatus.getLstid() == 0)
			{
				getSession().save(loanStatus);
			}else{
				getSession().update(loanStatus);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}		

	/**
	 * Used to delete a LoanStatus.
	 */
	public void deleteLoanStatus(Long id)
	{
		log.debug("removing LoanStatus instance");
		try{
			LoanStatus loanStatus= (LoanStatus) getSession().load(LoanStatus.class, id);
		if (null != loanStatus)
		{
			getSession().delete(loanStatus);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}		

	/**
	 * Used to list all the LoanStatus.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanStatus> listLoanStatus()
	{
		log.debug("List LoanStatus instance");
		try
		{
			List<LoanStatus> loanStatus=null;
			loanStatus = getSession().createQuery("from LoanStatus").list();
			return loanStatus;
		}
		catch(RuntimeException re) {
			log.error("list all LoanStatus failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single LoanStatus by Id.
	 */
	public LoanStatus findLoanStatusById(long lid) {
		try {
			char recordCode = 'Y';
			String SQL_QUERY ="select lnsts from LoanStatus lnsts left join fetch lnsts.lnapp where lnsts.lnapp.lid = :loanId and lnsts.recordCode = :rc";
			LoanStatus loanStatus = (LoanStatus) getSession().createQuery(SQL_QUERY)
			.setParameter("loanId", lid)
			.setParameter("rc", recordCode)
			.uniqueResult();

			return loanStatus;
		}catch(RuntimeException re) {
			log.error("find LoanStatus failed", re);
			throw re;
		}
	}			

	/**
	 * Used to list all the LoanStatus.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanStatus> listLoanStatusByLid(long lid)
	{
		log.debug("List LoanStatus instance");
		try
		{
			List<LoanStatus> loanStatus=null;
			String SQL_QUERY ="select lnsts from LoanStatus lnsts left join fetch lnsts.lnapp where lnsts.lnapp.lid = :loanId order by lnsts.step";
			loanStatus = (List<LoanStatus>) getSession().createQuery(SQL_QUERY)
			.setParameter("loanId", lid)
			.list();
			return loanStatus;
		}
		catch(RuntimeException re) {
			log.error("list all LoanStatus failed", re);
			throw re;
		}
	}	
	
	/**
	 * Used to list a single LoanStatus by Id.
	 */
	public LoanStatus findLatestLoanStatusById(long lid) {
		try {
			char recordCode = 'Y';
			Criteria criteria = getSession().createCriteria(LoanApplication.class);
			criteria.add(Restrictions.eq("lid", lid));
		    criteria.setProjection(Projections.max("step")); 
			@SuppressWarnings("static-access")
			Integer maxLnStatus = (Integer) criteria.setResultTransformer(criteria.ROOT_ENTITY).uniqueResult();

			String SQL_QUERY ="select lnsts from LoanStatus lnsts left join fetch lnsts.lnapp where lnsts.lnapp.lid = :loanId and lnsts.step = :step and lnsts.recordCode = :rc";
			LoanStatus loanStatus =  (LoanStatus) getSession().createQuery(SQL_QUERY)
			.setParameter("loanId", lid)
			.setParameter("step", maxLnStatus)
			.setParameter("rc", recordCode)
			.uniqueResult();
			
			return loanStatus;
		}catch(RuntimeException re) {
			log.error("find LoanStatus failed", re);
			throw re;
		}
	}	
	
/*
	private EntityManager entityManager;

	public void persist(LoanStatus transientInstance) {
		log.debug("persisting LoanStatus instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(LoanStatus persistentInstance) {
		log.debug("removing LoanStatus instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public LoanStatus merge(LoanStatus detachedInstance) {
		log.debug("merging LoanStatus instance");
		try {
			LoanStatus result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LoanStatus findById(Long id) {
		log.debug("getting LoanStatus instance with id: " + id);
		try {
			LoanStatus instance = entityManager.find(LoanStatus.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
*/	
}
