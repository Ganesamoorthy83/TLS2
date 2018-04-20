package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.mfic.data.ProspectiveBorrower;
import com.mfic.util.StringUtil;


/**
 * Home object for domain model class Prospectiveborrower.
 * @see com.mfic.dao.Prospectiveborrower
 * @author Hibernate Tools
 */

public class ProspectiveBorrowerHome extends BaseHome {

	private static final Log log = LogFactory.getLog(ProspectiveBorrowerHome.class);

	/**
	 * Used to save or update a ProspectiveBorrower.
	 */	
	public void saveOrUpdate(ProspectiveBorrower pborrower){
		log.debug("persisting ProspectiveBorrower instance");
		try {
			if (pborrower.getId() == 0)
			{
				getSession().save(pborrower);
			}else{
				getSession().update(pborrower);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}	
	
	/**
	 * Used to delete a ProspectiveBorrower.
	 */
	public void deletePBorrower(Long id)
	{
		log.debug("removing ProspectiveBorrower instance");
		try{
			ProspectiveBorrower pborrower= (ProspectiveBorrower) getSession().load(ProspectiveBorrower.class, id);
		if (pborrower.getId() > 0)
		{
			getSession().delete(pborrower);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}	
	
	/**
	 * Used to list all the ProspectiveBorrower.
	 */
	@SuppressWarnings("unchecked")
	public List<ProspectiveBorrower> listAllPBorrower()
	{
		log.debug("List ProspectiveBorrower instance");
		try
		{
			List<ProspectiveBorrower> pborrower=null;
			pborrower = getSession().createQuery("from ProspectiveBorrower")
			.list();
			return pborrower;
		}
		catch(RuntimeException re) {
			log.error("list all ProspectiveBorrower failed", re);
			throw re;
		}
	}	
	
	/**
	 * Used to list all the ProspectiveBorrower by status not converted to borrower.
	 */
	@SuppressWarnings("unchecked")
	public List<ProspectiveBorrower> listPBorrower()
	{
		log.debug("List ProspectiveBorrower instance");
		try
		{
			char isConverted = 'N';
			List<ProspectiveBorrower> pborrower=null;
			String SQL_QUERY ="select pb from ProspectiveBorrower pb  where pb.isConverted = :conv order by id desc";
			pborrower = getSession().createQuery(SQL_QUERY)
			.setParameter("conv", isConverted)
			.list();
			return pborrower;
		}
		catch(RuntimeException re) {
			log.error("list all ProspectiveBorrower failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single ProspectiveBorrower by Id.
	 */
	public ProspectiveBorrower findPborrowerById(Long pbId) {
		log.debug("Find by ProspectiveBorrower id");
		try {
			ProspectiveBorrower pborrower=null;
			pborrower = (ProspectiveBorrower) getSession().get(ProspectiveBorrower.class, pbId);
			return pborrower;
		}catch(RuntimeException re) {
			log.error("find ProspectiveBorrower failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single ProspectiveBorrower by First Name.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<ProspectiveBorrower> searchPBorrower(String fname,String lname) {
		try {
			List<ProspectiveBorrower> pborrower=null;
			if(!StringUtil.isNullOrBlank(fname) && !StringUtil.isNullOrBlank(lname)){
				Criteria crit = getSession().createCriteria(ProspectiveBorrower.class); 
				crit.add(Restrictions.ilike("fname","%"+ fname+"%")); 
				crit.add(Restrictions.ilike("lname", "%"+lname+"%"));
				pborrower = crit.list(); 

			}else if(!StringUtil.isNullOrBlank(fname) && StringUtil.isNullOrBlank(lname)){
				Criteria crit = getSession().createCriteria(ProspectiveBorrower.class); 
				crit.add(Restrictions.ilike("fname","%"+ fname+"%")); 
				pborrower = crit.list(); 

			}else if(!StringUtil.isNullOrBlank(lname) && StringUtil.isNullOrBlank(fname)){
				Criteria crit = getSession().createCriteria(ProspectiveBorrower.class); 
				crit.add(Restrictions.ilike("lname", "%"+lname+"%"));
				pborrower = crit.list(); 

			}else if(StringUtil.isNullOrBlank(fname) && StringUtil.isNullOrBlank(lname)){
				pborrower =  getSession().createQuery("from ProspectiveBorrower").list();
				
			}

			
			return pborrower;
		}catch(RuntimeException re) {
			log.error("find ProspectiveBorrower failed", re);
			throw re;
		}
	}	

}
