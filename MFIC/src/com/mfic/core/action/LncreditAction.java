package com.mfic.core.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.mfic.core.helper.LncreditManager;
import com.mfic.data.Lncredit;
import com.mfic.util.XmlHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LncreditAction extends ActionSupport implements
		ModelDriven<Lncredit> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7115485203835276063L;
	private long id;

	private Lncredit lncredit = new Lncredit();
	private List<Lncredit> creditList = new ArrayList<Lncredit>();
	private LncreditManager lncreditManager = new LncreditManager();
	private static final Log log = LogFactory.getLog(LncreditAction.class);

	public Lncredit getModel() {
		return lncredit;
	}

	public String processCredit() {
		log.debug("get Credit Reports");
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			lncreditManager.getCreditReports(Long.parseLong((request
					.getParameter("lid"))));
			creditList = lncreditManager.listCreditReports(Long
					.parseLong((request.getParameter("lid"))));
			return "success";
		} catch (RuntimeException re) {
			log.error("edit action failed", re);
			return "fail";
		}
	}

	/**
	 * To list all Attribute.
	 * 
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public String list() {
		log.debug("list action");
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			creditList = lncreditManager.listCreditReports(Long
					.parseLong((request.getParameter("lid"))));
			Iterator it = creditList.iterator();
			while (it.hasNext()){
				Lncredit lc = (Lncredit)it.next();
				String cString = new String(lc.getCreport());
				lc.setRpString(XmlHelper.format(cString));
			}
			return "populate";
		} catch (RuntimeException re) {
			log.error("list action failed in Attr Action", re);
			return "fail";
		} catch (Exception e){
			log.error("failure to parse xml", e);
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
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public void setCreditList(List<Lncredit> creditList) {
		this.creditList = creditList;
	}

	public List<Lncredit> getCreditList() {
		return creditList;
	}

}
