package com.mfic.dao;

// Generated Jun 17, 2010 11:09:48 AM by Hibernate Tools 3.2.4.GA

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mfic.data.Attribute;


/**
 * Home object for domain model class Attrb.
 * @see com.mfic.dao.Attrb
 * @author Hibernate Tools
 */
public class AttributeHome extends BaseHome {

	private static final Log log = LogFactory.getLog(AttributeHome.class);

	/**
	 * Used to save or update a Attribute.
	 */	
	public void saveOrUpdate(Attribute attribute){
		log.debug("persisting Attribute instance");
		try {
			if (attribute.getAttrbid() == 0)
			{
				getSession().save(attribute);
			}else{
				getSession().update(attribute);
			}
		} catch(RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	/**
	 * Used to delete a Attribute.
	 */
	public void deleteAttribute(Long id)
	{
		log.debug("removing Attribute instance");
		try{
			Attribute attribute= (Attribute) getSession().load(Attribute.class, id);
		if (attribute.getAttrbid() > 0)
		{
			getSession().delete(attribute);
		}
		}catch(RuntimeException re) {
			log.error("remove failed", re);
			throw re;
			
		}
	}	

	/**
	 * Used to list all the users.
	 */
	@SuppressWarnings("unchecked")
	public List<Attribute> listAttribute()
	{
		log.debug("List Attribute instance");
		try
		{
			List<Attribute> attribute=null;
			//String SQL_QUERY = "select i.attrbname,i.attrbtype from Attribute i";
			String SQL_QUERY = "select i from Attribute i";
			attribute = (List<Attribute>)getSession().createQuery(SQL_QUERY).list();
			return attribute;
		}
		catch(RuntimeException re) {
			log.error("list all Attribute failed in Attr Home", re);
			System.out.println("list action failed in Attr Home");
			throw re;
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Attribute> listAllAttribute()
	{
		log.debug("List Attribute instance");
		try
		{
			List<Attribute> attribute=null;
			attribute = (List<Attribute>)getSession().createQuery("from Attribute").list();
			return attribute;
		}
		catch(RuntimeException re) {
			log.error("list all Attribute failed in Attr Home", re);
			System.out.println("list action failed in Attr Home");
			throw re;
		}
		
		
	}
	
	/**
	 * Used to list a single Attribute by Id.
	 */
	public Attribute findAttributeById(int attrbId) {
		log.debug("Find by Attribute id");
		try {
			Attribute attribute=null;
			attribute = (Attribute) getSession().get(Attribute.class, attrbId);
			return attribute;
		}catch(RuntimeException re) {
			log.error("find Attribute failed", re);
			throw re;
		}
	}
		
}
