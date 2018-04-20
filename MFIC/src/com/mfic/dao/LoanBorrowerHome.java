package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.mfic.data.LoanBorrower;

/**
 * Home object for domain model class Lnbrwr.
 * @see com.mfic.dao.Lnbrwr
 * @author Hibernate Tools
 */

public class LoanBorrowerHome extends BaseHome {

	private static final Log log = LogFactory.getLog(LoanBorrowerHome.class);

	/**
	 * Used to save or update a LoanBorrower.
	 */	
	public void saveOrUpdate(LoanBorrower loanBorrower){
		log.debug("persisting LoanBorrower instance");
		try {
			if (null != loanBorrower)
			{
				getSession().save(loanBorrower);
			}else{
				getSession().update(loanBorrower);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}		

	/**
	 * Used to delete a LoanBorrower.
	 */
	public void deleteLoanBorrower(Long id)
	{
		log.debug("removing LoanBorrower instance");
		try{
			LoanBorrower loanBorrower= (LoanBorrower) getSession().load(LoanBorrower.class, id);
		if (null != loanBorrower)
		{
			getSession().delete(loanBorrower);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}		

	/**
	 * Used to list all the LoanBorrower.
	 */
	@SuppressWarnings({ "unchecked"})
	public List<LoanBorrower> listLoanBorrower()
	{
		log.debug("List LoanBorrower instance");
		try
		{
			List<LoanBorrower> loanBorrower=null;
			Criteria criteria = getSession().createCriteria(LoanBorrower.class);
			criteria.createAlias("brwr", "lnbrwr");
			criteria.createAlias("lnapp", "lnapp");
			loanBorrower = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

	return loanBorrower;
		}
		catch(RuntimeException re) {
			log.error("list all LoanBorrower failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list all the LoanBorrower.
	 */
	@SuppressWarnings({ "rawtypes"})
	public List<LoanDetail> listLoanBorrowers()
	{
		log.debug("List LoanBorrower instance");
		try
		{
			String sqlQuery="select lid,brwrid from lnbrwr";
			List brwrs = getSession().createSQLQuery(sqlQuery).list();
			
			List<LoanDetail> list = new ArrayList<LoanDetail>();
			
			for (int i=0; i< brwrs.size(); i++)
			{
				Object[] values = (Object[]) brwrs.get(i);
					LoanDetail loanDetail = new LoanDetail(values[0].toString(),values[1].toString());
					list.add(loanDetail);
			}


			return list;
		}
		catch(RuntimeException re) {
			log.error("list all LoanBorrower failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single LoanBorrower by Id.
	 */
	
	@SuppressWarnings({ "static-access" })
	public LoanBorrower findLoanBorrowerById(long lid) {
		try {
			LoanBorrower loanBorrower=null;
			String SQL_QUERY ="select lnbrwr from LoanBorrower lnbrwr left join fetch lnbrwr.lnapp where lnbrwr.lnapp.lid = :loanId";
			loanBorrower =  (LoanBorrower) getSession().createQuery(SQL_QUERY)
			.setParameter("loanId", lid)
			.uniqueResult();

			Criteria criteria = getSession().createCriteria(LoanBorrower.class);
			criteria.createAlias("lnapp", "la");
			criteria.createAlias("brwr", "lb");
			criteria.add(Restrictions.eq("la.lid", lid));
			loanBorrower = (LoanBorrower) criteria.setResultTransformer(criteria.ROOT_ENTITY).uniqueResult();
			
			
			return loanBorrower;
		}catch(RuntimeException re) {
			log.error("find LoanBorrower failed", re);
			throw re;
		}
	}				
	
}
