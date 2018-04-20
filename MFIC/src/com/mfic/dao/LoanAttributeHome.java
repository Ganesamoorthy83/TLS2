package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.mfic.data.LoanAttribute;

/**
 * Home object for domain model class Lnattrb.
 * @see com.mfic.dao.Lnattrb
 * @author Hibernate Tools
 */

public class LoanAttributeHome extends BaseHome{

	private static final Log log = LogFactory.getLog(LoanAttributeHome.class);

	/**
	 * Used to save or update a LoanAttribute.
	 */	
	public void saveOrUpdate(LoanAttribute loanAttribute){
		log.debug("persisting LoanAttribute instance");
		try {
			if (loanAttribute.getLnattrbid() == 0){
				getSession().save(loanAttribute);
			}else {
				getSession().update(loanAttribute);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}		

	/**
	 * Used to delete a LoanAttribute.
	 */
	public void deleteLoanAttribute(Long id)
	{
		log.debug("removing LoanAttribute instance");
		try{
			LoanAttribute loanAttribute= (LoanAttribute) getSession().load(LoanAttribute.class, id);
		if (null != loanAttribute)
		{
			getSession().delete(loanAttribute);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}		

	/**
	 * Used to list all the LoanAttribute.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<LoanAttribute> listLoanAttributeByLoanId(long lid)
	{
		log.debug("List LoanAttribute instance");
		try
		{
			List<LoanAttribute> loanAttribute=null;
			char recordCode ='Y';
			String SQL_QUERY ="select lnattrb from LoanAttribute lnattrb join fetch lnattrb.lnapp where lnattrb.lnapp.lid = :loanId and lnattrb.recordCode = :rc";
			loanAttribute =  getSession().createQuery(SQL_QUERY)
			.setParameter("loanId", lid)
			.setParameter("rc", recordCode)
			.list();

			return loanAttribute;
		}
		catch(RuntimeException re) {
			log.error("list all LoanAttribute failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single LoanAttribute by Id.
	 */
	@SuppressWarnings("unchecked")
	public List<LoanAttribute> findLoanAttributeById(long lid) {
		try {
			List<LoanAttribute> loanAttributeList=null;
			char recordCode ='Y';
			String SQL_QUERY ="select lnattrb from LoanAttribute lnattrb join fetch lnattrb.lnapp left outer join fetch lnattrb.attrb where lnattrb.lnapp.lid = :loanId and lnattrb.recordCode = :rc";
			loanAttributeList = getSession().createQuery(SQL_QUERY)
			.setParameter("loanId", lid)
			.setParameter("rc", recordCode)
			.list();
			return loanAttributeList;
		}catch(RuntimeException re) {
			log.error("find LoanAttribute failed", re);
			throw re;
		}
	}				
	
	/**
	 * Used to list a single LoanAttribute by Id.
	 */
	@SuppressWarnings("static-access")
	public LoanAttribute findLoanAttributeByLoanId(long lid, int attrbId) {
		try {
			LoanAttribute loanAttribute=null;
			char recordCode ='Y';
			/*
			String SQL_QUERY ="select lnattrb from LoanAttribute lnattrb join fetch lnattrb.lnapp left outer join fetch lnattrb.attrb where lnattrb.lnapp.lid = :loanId and lnattrb.attrb.attrbid = :attrbId and lnattrb.recordCode = :rc";
			loanAttribute = (LoanAttribute) getSession().createQuery(SQL_QUERY)
			.setParameter("loanId", lid)
			.setParameter("attrbId", attrbId)
			.setParameter("rc", recordCode)
			.uniqueResult();
			*/
			Criteria criteria = getSession().createCriteria(LoanAttribute.class);
			criteria.createAlias("lnapp", "la");
			criteria.createAlias("attrb", "atrb");
			criteria.add(Restrictions.eq("la.lid", lid));
			criteria.add(Restrictions.eq("atrb.attrbid", attrbId));
			criteria.add(Restrictions.eq("recordCode", recordCode));
			loanAttribute = (LoanAttribute) criteria.setResultTransformer(criteria.ROOT_ENTITY).uniqueResult();
			return loanAttribute;
		}catch(RuntimeException re) {
			log.error("find LoanAttribute failed", re);
			throw re;
		}
	}		

}
