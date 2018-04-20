package com.mfic.core.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.classic.Session;

import com.mfic.dao.LncreditHome;
import com.mfic.data.Borrower;
import com.mfic.data.Lncredit;
import com.mfic.data.LoanApplication;
import com.mfic.data.LoanBorrower;
import com.mfic.util.netconnect.NetConnectClient;

public class LncreditManager {

	private static final Log log = LogFactory.getLog(LncreditManager.class);

	LncreditHome lncreditHome = new LncreditHome();

	/**
	 * Used to save or update a Attribute.
	 */
	public void saveOrUpdateCredit(Lncredit lncredit) {
		log.debug("save Or Updating Attribute instance");
		Session session = LncreditHome.getSession();
		try {
			session.beginTransaction();
			lncreditHome.saveOrUpdate(lncredit);
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			log.error("LnCredit saveOrUpdate failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	/**
	 * Used to list all the Attribute.
	 */
	public List<Lncredit> listCreditReports(long lid) {
		log.debug("list all Attribute");
		Session session = LncreditHome.getSession();
		List<Lncredit> creditList = null;
		try {
			session.beginTransaction();
			creditList = lncreditHome.findByLoanId(lid);
			session.getTransaction().commit();
			return creditList;
		} catch (RuntimeException re) {
			log.error("list all Attribute failed in Attr Manager", re);
			System.out.println("list action failed in Attr Manager");
			session.getTransaction().rollback();
			throw re;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getCreditReports(long lid) {
		
		try {
			LoanApplicationManager lnAppMgr = new LoanApplicationManager();
			LoanApplication lnApp = lnAppMgr.findLoanApplicationById(lid);
			NetConnectClient nClient = new NetConnectClient();
			
			List<LoanBorrower> lnbrwrs = new ArrayList(lnApp.getLnbrwrs());
			
			Iterator brwrIt = lnbrwrs.iterator();
			while (brwrIt.hasNext()) {
				// get credit report for each borrower
				LoanBorrower lbr = (LoanBorrower) brwrIt.next();
				Borrower brwr = lbr.getBrwr();
				log.info("Requesting Credit Report using Net Connect");
				String cReport = nClient.processRequest(brwr.getFname(), brwr
						.getLname(), brwr.getMname(), brwr.getSsn(), brwr
						.getAdd1()
						+ " " + brwr.getAdd2(), brwr.getCity(),
						brwr.getState(), brwr.getZip());
				if (cReport == null) {
					log.error("Credit Report is empty for Borrower"
							+ brwr.getBrwrid());
				} else {
					log.info("Response from Net Connect Obtained");
					Lncredit lncredit = new Lncredit();
					lncredit.setCreport(cReport.getBytes());
					lncredit.setLid(lid);
					lncredit.setBrwrid(brwr.getBrwrid());
					saveOrUpdateCredit(lncredit);
				}
			}
		} catch (IOException e) {
			log.error("Could not obtain Credit Reports");
			throw new RuntimeException(e);
		}

	}

}
