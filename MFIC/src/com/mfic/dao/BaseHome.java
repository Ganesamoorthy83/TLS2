/**
 * 
 */
package com.mfic.dao;

import org.hibernate.classic.Session;

import com.mfic.util.HibernateUtil;

/**
 * @author Great
 *
 */
public class BaseHome {
public static Session getSession(){
	return HibernateUtil.getSessionFactory().getCurrentSession();
}
}
