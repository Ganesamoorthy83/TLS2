package com.mfic.core.helper;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.AttributeHome;
import com.mfic.data.Attribute;
import com.mfic.util.AttributeType;
import com.mfic.util.StringUtil;

public class AttributeManager {
	private static final Log log = LogFactory.getLog(AttributeManager.class);
	AttributeHome attributeHome = new AttributeHome();
	
	/**
	 * Used to save or update a Attribute.
	 */
	public void saveOrUpdateAttribute(Attribute attribute)
	{
		log.debug("save Or Updating Attribute instance");
		Session session = AttributeHome.getSession();
		try{
		session.beginTransaction();
		Date currentDate = new Date();
		attribute.setDtlstupdt(currentDate);
		if (StringUtil.isEqual(attribute.getAttrbtype(), AttributeType.DOC)){
			attribute.setIsDoc('Y');
			attribute.setAttrbphysicaltype(AttributeType.DOC);
		} else {
			attribute.setIsDoc('N');
		}	
		attributeHome.saveOrUpdate(attribute);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("Attributes saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	

	/**
	 * Used to delete a Attribute.
	 */
	public void deleteAttribute(Long id)
	{
		log.debug("delete Attribute");
		Session session = AttributeHome.getSession();
		try{
		session.beginTransaction();
		attributeHome.deleteAttribute(id);
		session.getTransaction().commit();
		}catch(RuntimeException re) {
			log.error("Attribute delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to list all the Attribute.
	 */
	public List<Attribute> listAttribute()
	{
		log.debug("list all Attribute");
		Session session = AttributeHome.getSession();
		List<Attribute> attrbList=null;
		try
		{
		session.beginTransaction();
		attrbList = attributeHome.listAttribute();
		session.getTransaction().commit();
		return attrbList;
		}
		catch(RuntimeException re) {
			log.error("list all Attribute failed in Attr Manager", re);
			System.out.println("list action failed in Attr Manager");
			session.getTransaction().rollback();
			throw re;
			
		}
	}
	
	public List<Attribute> listAllAttribute()
	{
		log.debug("list all Attribute");
		Session session = AttributeHome.getSession();
		List<Attribute> attrbList=null;
		try
		{
		session.beginTransaction();
		attrbList = attributeHome.listAllAttribute();
		session.getTransaction().commit();
		return attrbList;
		}
		catch(RuntimeException re) {
			log.error("list all Attribute failed in Attr Manager", re);
			System.out.println("list action failed in Attr Manager");
			session.getTransaction().rollback();
			throw re;
			
		}
	}
	
	/**
	 * Used to list a single Attribute by Id.
	 */
	public Attribute findAttributeById(int attrbId) {
		log.debug("select single Attribute");
		Attribute attribute=null;
		try
		{
			attribute=attributeHome.findAttributeById(attrbId);
		return attribute;
		} catch(RuntimeException re) {
			log.error("select single Attribute failed", re);
			throw re;
		}
	}	

	/**
	 * Used to list a single Attribute by Id.
	 */
	public Attribute findAttribute(int attrbId) {
		log.debug("select single Attribute");
		Session session = AttributeHome.getSession();
		Attribute attribute=null;
		try
		{
			session.beginTransaction();
			attribute=attributeHome.findAttributeById(attrbId);
			session.getTransaction().commit();
		return attribute;
		} catch(RuntimeException re) {
			log.error("select single Attribute failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}	
		
	
}
