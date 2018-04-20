package com.mfic.core.helper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.ProspectiveBorrowerHome;
import com.mfic.data.ProspectiveBorrower;


public class PBorrowerManager
{
	private static final Log log = LogFactory.getLog(PBorrowerManager.class);
	ProspectiveBorrowerHome pbHome = new ProspectiveBorrowerHome();

	/**
	 * Used to save or update a ProspectiveBorrower.
	 */
	public void saveOrUpdatePBorrower(ProspectiveBorrower pBorrower)
	{
		log.debug("save Or Updating ProspectiveBorrower instance");
		Session session = ProspectiveBorrowerHome.getSession();
		try{
		session.beginTransaction();
		pbHome.saveOrUpdate(pBorrower);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to delete a ProspectiveBorrower.
	 */
	public void deletePBorrower(Long id)
	{
		log.debug("delete ProspectiveBorrower");
		Session session = ProspectiveBorrowerHome.getSession();
		try{
		session.beginTransaction();
		pbHome.deletePBorrower(id);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to list all the ProspectiveBorrower.
	 */
	public List<ProspectiveBorrower> listAllPborrower()
	{
		log.debug("list all ProspectiveBorrower");
		Session session = ProspectiveBorrowerHome.getSession();
		List<ProspectiveBorrower> pbList=null;
		try
		{
			session.beginTransaction();
			pbList = pbHome.listAllPBorrower();
			session.getTransaction().commit();
			return pbList;
		}
		catch(RuntimeException re) {
			log.error("list all ProspectiveBorrower failed", re);
			session.getTransaction().rollback();
			throw re;

		}

	}	
	
	/**
	 * Used to list all the ProspectiveBorrower.
	 */
	public List<ProspectiveBorrower> listPborrower()
	{
		log.debug("list all ProspectiveBorrower");
		Session session = ProspectiveBorrowerHome.getSession();
		List<ProspectiveBorrower> pbList=null;
		try
		{
			session.beginTransaction();
			pbList = pbHome.listPBorrower();
			session.getTransaction().commit();
			return pbList;
		}
		catch(RuntimeException re) {
			log.error("list all ProspectiveBorrower failed", re);
			session.getTransaction().rollback();
			throw re;

		}

	}	

	/**
	 * Used to list a single ProspectiveBorrower by Id.
	 */
	public ProspectiveBorrower findPborrowerById(Long pbId) {
		log.debug("select single ProspectiveBorrower");
		Session session = ProspectiveBorrowerHome.getSession();
		ProspectiveBorrower pb=null;
		try
		{
			session.beginTransaction();
			pb=pbHome.findPborrowerById(pbId);
			session.getTransaction().commit();
		return pb;
		} catch(RuntimeException re) {
			log.error("select single ProspectiveBorrower failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to list ProspectiveBorrowers by First Name or Last Name.
	 */
	public List<ProspectiveBorrower> searchPBorrower(String pbFname,String pbLname) {
		log.debug("select ProspectiveBorrowers by First Name or Last Name");
		Session session = ProspectiveBorrowerHome.getSession();
		List<ProspectiveBorrower> pb=null;
		try
		{
			session.beginTransaction();
			pb=pbHome.searchPBorrower(pbFname, pbLname);
			session.getTransaction().commit();
			return pb;
		} catch(RuntimeException re) {
			log.error("select ProspectiveBorrower by First Name or Last Name failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	


}