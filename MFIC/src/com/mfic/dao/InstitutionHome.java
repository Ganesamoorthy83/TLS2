package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mfic.data.Institution;


/**
 * Home object for domain model class Institution.
 * @see com.mfic.dao.Institution
 * @author Hibernate Tools
 */
public class InstitutionHome  extends BaseHome {

	private static final Log log = LogFactory.getLog(InstitutionHome.class);
	
	/**
	 * Used to save or update a Institution.
	 */	
	public void saveOrUpdate(Institution institution){
		log.debug("persisting Institution instance");
		try {
			if (institution.getInstitutionid() == 0)
			{
				getSession().save(institution);
			}else{
				getSession().update(institution);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}		

	/**
	 * Used to delete a Institution.
	 */
	public void deleteInstitution(Long id)
	{
		log.debug("removing Institution instance");
		try{
			Institution institution= (Institution) getSession().load(Institution.class, id);
		if (null != institution)
		{
			getSession().delete(institution);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}		
	
	/**
	 * Used to list all the Institution.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Institution> listInstitution()
	{
		log.debug("List Institution instance");
		try
		{
			List<Institution> institution=null;
			String SQL_QUERY ="Select i.institutionname,i.institutionid from Institution i";
			institution = getSession().createQuery(SQL_QUERY).list();
			return institution;
		}
		catch(RuntimeException re) {
			log.error("list all Institution failed", re);
			throw re;
		}
	}	
		
	
	/**
	 * Used to list all the Institution.
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Institution> listAllInstitution()
	{
		log.debug("List Institution instance");
		try
		{
			List<Institution> institution=null;
			institution = getSession().createQuery("from Institution").list();
			return institution;
		}
		catch(RuntimeException re) {
			log.error("list all Institution failed", re);
			throw re;
		}
	}	
	
	@SuppressWarnings({ "unchecked" })
	public List<Institution> listCountry()
	{
		log.debug("List InstitutionCountry instance");
		try
		{
			List<Institution> country=null;
			String SQL_QUERY1 ="Select distinct i.country from Institution i";
			country =  getSession().createQuery(SQL_QUERY1).list();

			return country;
		}
		catch(RuntimeException re) {
			log.error("list all Country failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single Institution by Id.
	 */
	public Institution findInstitutionById(int insId) {
		try {
			Institution institution=null;
			institution = (Institution) getSession().get(Institution.class, insId);
			return institution;
		}catch(RuntimeException re) {
			log.error("find Institution failed", re);
			throw re;
		}
	}		
	
	/**
	 * Used to list a single Institution by Name.
	 */
	public Institution findInstitutionByName(String name) {
		try {
			Institution institution=null;
			String SQL_QUERY ="select lns from Institution lns  where lns.institutionname = :insName";
			institution = (Institution) getSession().createQuery(SQL_QUERY).setParameter("insName", name).uniqueResult();
			return institution;
		}catch(RuntimeException re) {
			log.error("find Institution failed", re);
			throw re;
		}
	}		
	
	
	@SuppressWarnings({ "unchecked" })
	public List<Institution> listInstitutionByCountry(String country)
	{
		log.debug("List Institution By Country");
		try
		{
			List<Institution> institutions=null;
			String SQL_QUERY ="select lns.institutionname from Institution lns  where lns.country = :cuntry";
			institutions = (List<Institution>) getSession().createQuery(SQL_QUERY)
			.setParameter("cuntry", country)
			.list();
			return institutions;
		}
		catch(RuntimeException re) {
			log.error("list Institution by Country failed", re);
			throw re;
		}
	}	
	
	
	
	
	
	
/*
	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Institution transientInstance) {
		log.debug("persisting Institution instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Institution persistentInstance) {
		log.debug("removing Institution instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Institution merge(Institution detachedInstance) {
		log.debug("merging Institution instance");
		try {
			Institution result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Institution findById(int id) {
		log.debug("getting Institution instance with id: " + id);
		try {
			Institution instance = entityManager.find(Institution.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
*/	
}
