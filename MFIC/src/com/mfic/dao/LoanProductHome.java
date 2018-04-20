package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mfic.data.LoanProduct;

/**
 * Home object for domain model class Lnprdt.
 * @see com.mfic.dao.Lnprdt
 * @author Hibernate Tools
 */
public class LoanProductHome extends BaseHome {

	private static final Log log = LogFactory.getLog(LoanProductHome.class);

	/**
	 * Used to save or update a LoanProduct.
	 */	
	public void saveOrUpdate(LoanProduct lnProduct){
		log.debug("persisting LoanProduct instance");
		try {
			if (lnProduct.getLnprdtid() == 0 )
			{
				getSession().save(lnProduct);
			}else{
				getSession().update(lnProduct);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}			
	
	/**
	 * Used to update a LoanProduct.
	 */	
	public void updateLnprdtOnly(LoanProduct lnProduct){
		log.debug("persisting LoanProduct instance");
		try {
				getSession().update(lnProduct);
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	/**
	 * Used to delete a LoanProduct.
	 */
	public void deleteLoanProduct(Long id)
	{
		log.debug("removing LoanProduct instance");
		try{
			LoanProduct lnProduct= (LoanProduct) getSession().load(LoanProduct.class, id);
		if (null != lnProduct)
		{
			getSession().delete(lnProduct);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}		
		
	/**
	 * Used to list all the LoanProduct.
	 */
	@SuppressWarnings("unchecked")
	public List<LoanProduct> listLoanProduct()
	{
		log.debug("List LoanProduct instance");
		try
		{
			List<LoanProduct> lnProduct=null;
			String SQL_QUERY ="select lnp from LoanProduct lnp left join fetch lnp.institution";
			lnProduct = getSession().createQuery(SQL_QUERY).list();
			return lnProduct;
		}
		catch(RuntimeException re) {
			log.error("list all LoanProduct failed", re);
			throw re;
		}
	}		
	
	/**
	 * Used to list a single LoanProduct by Id.
	 */
	public LoanProduct findLoanProductById(Long lnPrdtId) {
		try {
			LoanProduct lnProduct=null;
			lnProduct = (LoanProduct) getSession().get(LoanProduct.class, lnPrdtId);
			return lnProduct;
		}catch(RuntimeException re) {
			log.error("find LoanProduct failed", re);
			throw re;
		}
	}		
	
	/**
	 * Used to list a single LoanProduct by Name.
	 */
	public LoanProduct findLoanProductByName(String lnprdtname) {
		try {
			LoanProduct lnProduct=null;
			String SQL_QUERY ="select lnp from LoanProduct lnp where lnp.lnprdtname = :name";
			lnProduct = (LoanProduct) getSession().createQuery(SQL_QUERY)
			.setParameter("name", lnprdtname).uniqueResult();
			return lnProduct;
		}catch(RuntimeException re) {
			log.error("find LoanProduct failed", re);
			throw re;
		}
	}		
		
	
	/**
	 * Used to list a single LoanProduct by InstitutionId.
	 */
	@SuppressWarnings("unchecked")
	public List<LoanProduct> findLoanProductByInstitutionId(Long lnsId) {
		try {
			List<LoanProduct> lnProduct= new ArrayList<LoanProduct>();
			String SQL_QUERY ="select lnp from LoanProduct lnp left join fetch lnp.institution where lnp.institution.institutionid = :insid";
			lnProduct =  getSession().createQuery(SQL_QUERY)
			.setParameter("insid", lnsId).list();
			
			return lnProduct;
		}catch(RuntimeException re) {
			log.error("find LoanProduct by InstitutionId failed", re);
			throw re;
		}
	}		
	
	/**
	 * Used to list a single LoanProduct by InstitutionId.
	 */
	@SuppressWarnings("unchecked")
	public List<LoanProduct> findLoanProductNameByInstitutionId(Long lnsId) {
		try {
			List<LoanProduct> lnProduct= new ArrayList<LoanProduct>();
			if(lnsId == null){
				String SQL_QUERY ="select lnp.lnprdtname from LoanProduct lnp ";
				lnProduct =  getSession().createQuery(SQL_QUERY).list();
			}else {
				String SQL_QUERY ="select lnp.lnprdtname from LoanProduct lnp left join fetch lnp.institution where lnp.institution.institutionid = :insid";
				lnProduct =  getSession().createQuery(SQL_QUERY)
				.setParameter("insid", lnsId).list();
			}
			return lnProduct;
		}catch(RuntimeException re) {
			log.error("find LoanProduct by InstitutionId failed", re);
			throw re;
		}
	}			
	
	
	
	
	
/*	
	@PersistenceContext
	private EntityManager entityManager;

	public void persist(LoanProduct transientInstance) {
		log.debug("persisting LoanProduct instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(LoanProduct persistentInstance) {
		log.debug("removing LoanProduct instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public LoanProduct merge(LoanProduct detachedInstance) {
		log.debug("merging LoanProduct instance");
		try {
			LoanProduct result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LoanProduct findById(long id) {
		log.debug("getting LoanProduct instance with id: " + id);
		try {
			LoanProduct instance = entityManager.find(LoanProduct.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
*/	
}
