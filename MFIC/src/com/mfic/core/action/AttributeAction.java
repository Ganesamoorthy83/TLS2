package com.mfic.core.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mfic.core.helper.AttributeManager;
import com.mfic.data.Attribute;
import com.mfic.util.AttributePhysicalType;
import com.mfic.util.AttributeType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class AttributeAction  extends ActionSupport implements ModelDriven<Attribute> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7115485203835276063L;
	private long id;

	private Attribute attrb = new Attribute();
	private List<Attribute> attrbList = new ArrayList<Attribute>();
	private AttributeManager attrbManager = new AttributeManager();
	private static final Log log = LogFactory.getLog(AttributeAction.class);	
	public List<String> aTypeList = AttributeType.getAttributetypelist();
	public List<String> aPhyList = AttributePhysicalType.getAttributephysicaltypelist();
	public List<String> aPhyListForDoc = AttributePhysicalType.getDocAttributephysicaltypelist();
	public List<String> aPhyListForData = AttributePhysicalType.getDataAttributephysicaltypelist();
	private String attrbtype;
	
	public Attribute getModel()
	{
		return attrb;
	}


	/**
	 * To save or update Attribute.
	 * @return String
	 */
	public String saveOrUpdate()
	{
		log.debug("saveOrUpdate action");
		try{
			attrbManager.saveOrUpdateAttribute(attrb);
		return "success";
		}catch(RuntimeException re) {
			log.error("saveOrUpdate action failed", re);
			return "fail";
		}
	}
		
	/**
	 * To delete a Attribute.
	 * @return String
	 */
	public String delete()
	{
		log.debug("delete action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			attrbManager.deleteAttribute(Long.parseLong(request.getParameter("id")));
			return "success";
		}catch(RuntimeException re) {
			log.error("delete action failed", re);
			return "fail";
		}
	}

	
	/**
	 * To list a single Attribute by Id.
	 * @return String
	 */
    @SkipValidation
	public String edit()
	{
		log.debug("edit action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			attrb = attrbManager.findAttribute(Integer.parseInt(request.getParameter("attrbid")));
			listPhysicalType();
			return "success";
		}catch(RuntimeException re) {
			log.error("edit action failed", re);
			return "fail";
		}
	}	
	
	/**
	 * To list all Attribute.
	 * @return String
	 */
    @SkipValidation
	public String list()
	{
		log.debug("list action");
		try{
			attrbList = attrbManager.listAttribute();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed in Attr Action", re);
			return "fail";
		}
	}	
	
    @SkipValidation
	public String listAll()
	{
		log.debug("list action");
		try{
			attrbList = attrbManager.listAllAttribute();
		return "populate";
		}catch(RuntimeException re) {
			log.error("list action failed in Attr Action", re);
			return "fail";
		}
	}	

	/**
	 * To list a single Attribute by Id.
	 * @return String
	 */
    @SkipValidation
	public String listByAttributeId()
	{
		log.debug("list action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			attrb = (Attribute) attrbManager.findAttributeById(Integer.parseInt(request.getParameter("id")));
			return "success";
		}catch(RuntimeException re) {
			log.error("list By AttributeId action failed", re);
			return "fail";
		}
	}	
    
    
	/**
	 * To list a Attribute Physical Type by Attribute Type.
	 */
	@SkipValidation
	public String listPhysicalType(){
		log.debug("list by Attribute Physical Type action");
		try{
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			attrbtype = request.getParameter("attrbtype");

			if(attrbtype != null && attrbtype.equals(AttributeType.DOC)){
				aPhyList = AttributePhysicalType.getDocAttributephysicaltypelist();
				return "populate";
			}else if(attrbtype != null && attrbtype.equals(AttributeType.DATA)){
				aPhyList = AttributePhysicalType.getDataAttributephysicaltypelist();
				return "populate";
			}else{
				aPhyList = AttributePhysicalType.getAttributephysicaltypelist();
				return "populate";
	        }
		}catch(RuntimeException re) {
			log.error("list By Attribute Physical Type action failed", re);
			return "fail";
		}
		
	}
    
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the attrb
	 */
	public Attribute getAttrb() {
		return attrb;
	}

	/**
	 * @param attrb the attrb to set
	 */
	public void setAttrb(Attribute attrb) {
		this.attrb = attrb;
	}

	/**
	 * @return the attrbList
	 */
	public List<Attribute> getAttrbList() {
		return attrbList;
	}

	/**
	 * @param attrbList the attrbList to set
	 */
	public void setAttrbList(List<Attribute> attrList) {
		this.attrbList = attrList;
	}

	/**
	 * @return the attrbManager
	 */
	public AttributeManager getAttrbManager() {
		return attrbManager;
	}

	/**
	 * @param attrbManager the attrbManager to set
	 */
	public void setAttrbManager(AttributeManager attrbManager) {
		this.attrbManager = attrbManager;
	}


	/**
	 * @return the attrbtype
	 */
	public String getAttrbtype() {
		return attrbtype;
	}


	/**
	 * @param attrbtype the attrbtype to set
	 */
	public void setAttrbtype(String attrbtype) {
		this.attrbtype = attrbtype;
	}
}
