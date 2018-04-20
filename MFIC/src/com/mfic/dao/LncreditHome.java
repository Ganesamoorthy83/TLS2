package com.mfic.dao;
// default package
// Generated Sep 24, 2010 7:47:17 PM by Hibernate Tools 3.3.0.GA

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mfic.data.Lncredit;

/**
 * Home object for domain model class Lncredit.
 * @see .Lncredit
 * @author Hibernate Tools
 */
public class LncreditHome extends BaseHome {

	private static final Log log = LogFactory.getLog(LncreditHome.class);
	
	/**
	 * Used to save or update a Institution.
	 */	
	public void saveOrUpdate(Lncredit lncredit){
		log.debug("persisting Institution instance");
		try {
			if (lncredit.getIdlnCredit() == 0)
			{
				getSession().save(lncredit);
			}else{
				getSession().update(lncredit);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}		

	@SuppressWarnings("unchecked")
	public List<Lncredit> findByLoanId(long lid) {
		log.debug("finding Lncredit instance by example");
		try {
			List<Lncredit> lncredit=null;
			String SQL_QUERY ="select lnc from Lncredit lnc  where lnc.lid = :lid";
			lncredit = (List<Lncredit>) getSession().createQuery(SQL_QUERY).setParameter("lid", lid).list();
			return lncredit;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
