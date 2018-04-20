package com.mfic.core.helper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.InstitutionHome;
import com.mfic.data.Institution;


public class InstitutionManager {
	private static final Log log = LogFactory.getLog(InstitutionManager.class);
	InstitutionHome institutionHome = new InstitutionHome();
	
	/**
	 * Used to save or update a Institution.
	 */
	public void saveOrUpdateInstitution(Institution institution)
	{
		log.debug("save Or Updating Institution instance");
		Session session = InstitutionHome.getSession();
		try{
		session.beginTransaction();
		institutionHome.saveOrUpdate(institution);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	

	/**
	 * Used to delete a Institution.
	 */
	public void deleteInstitution(Long id)
	{
		log.debug("delete Institution");
		Session session = InstitutionHome.getSession();
		try{
		session.beginTransaction();
		institutionHome.deleteInstitution(id);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
	
	/**
	 * Used to list all the Institution.
	 */
	public List<Institution> listInstitution()
	{
		log.debug("list all Institution");
		Session session = InstitutionHome.getSession();
		List<Institution> institutionList=null;
		try
		{
		session.beginTransaction();
		institutionList = institutionHome.listInstitution();
		session.getTransaction().commit();
		return institutionList;
		}
		catch(RuntimeException re) {
			log.error("list all Institution failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	
	
	public List<Institution> listAllInstitution()
	{
		log.debug("list all Institution");
		Session session = InstitutionHome.getSession();
		List<Institution> institutionList=null;
		try
		{
		session.beginTransaction();
		institutionList = institutionHome.listAllInstitution();
		session.getTransaction().commit();
		return institutionList;
		}
		catch(RuntimeException re) {
			log.error("list all Institution failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	public List<Institution> listCountry()
	{
		log.debug("list all Country");
		Session session = InstitutionHome.getSession();
		List<Institution> countryList=null;
		try
		{
		session.beginTransaction();
		//countryList = institutionHome.listCountry(institutename);
		countryList = institutionHome.listCountry();
		session.getTransaction().commit();
		return countryList;
		}
		catch(RuntimeException re) {
			log.error("list all Country failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	

	
	/**
	 * Used to list a single Institution by Id.
	 */
	public Institution findInstitutionById(int insId) {
		log.debug("select single Institution");
		Session session = InstitutionHome.getSession();
		Institution institution=null;
		try
		{
			session.beginTransaction();
			institution=institutionHome.findInstitutionById(insId);
			session.getTransaction().commit();
		return institution;
		} catch(RuntimeException re) {
			log.error("select single Institution failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	

	
	/**
	 * Used to list a single Institution by Name.
	 */
	public Institution findInstitutionByName(String name) {
		log.debug("select single Institution by Name");
		Session session = InstitutionHome.getSession();
		Institution institution=null;
		try
		{
			session.beginTransaction();
			institution=institutionHome.findInstitutionByName(name);
			session.getTransaction().commit();
		return institution;
		} catch(RuntimeException re) {
			log.error("select single Institution by Name failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	

	/**
	 * Used to list Institution by country.
	 */
	public List<Institution> listInstitutionByCountry(String country)
	{
		log.debug("list Institution by Country");
		Session session = InstitutionHome.getSession();
		List<Institution> institutions=null;
		try
		{
		session.beginTransaction();
		institutions = institutionHome.listInstitutionByCountry(country);
		session.getTransaction().commit();
		return institutions;
		}
		catch(RuntimeException re) {
			log.error("list Institution by Country failed", re);
			session.getTransaction().rollback();
			throw re;
			
		}
	}	
	
}
