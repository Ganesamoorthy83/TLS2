package com.mfic.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public abstract class UTF8ResourceBundle {

	public static final ResourceBundle getBundle(String baseName) {
		ResourceBundle bundle = ResourceBundle.getBundle(baseName);
		return createUtf8PropertyResourceBundle(bundle);
	}

	public static final ResourceBundle getBundle(String baseName, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
		return createUtf8PropertyResourceBundle(bundle);
	}

	public static ResourceBundle getBundle(String baseName, Locale locale,
			ClassLoader loader) {
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale,
				loader);
		return createUtf8PropertyResourceBundle(bundle);
	}

	private static ResourceBundle createUtf8PropertyResourceBundle(
			ResourceBundle bundle) {
		if (!(bundle instanceof PropertyResourceBundle))
			return bundle;

		return new Utf8PropertyResourceBundle((PropertyResourceBundle) bundle);
	}

	private static class Utf8PropertyResourceBundle extends ResourceBundle {
		PropertyResourceBundle bundle;

		private Utf8PropertyResourceBundle(PropertyResourceBundle bundle) {
			this.bundle = bundle;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ResourceBundle#getKeys()
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Enumeration getKeys() {
			return bundle.getKeys();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ResourceBundle#handleGetObject(java.lang.String)
		 */
		protected Object handleGetObject(String key) {
			String value = (String) bundle.getString(key);
			if (value == null)
				return null;
			try {
				//System.out.println(key + "=" + value);
				String retString = new String(value.getBytes("UTF-8"));
				//System.out.println("returning as =" + retString);
				//String encoding = System.getProperty("file.encoding"); 
				//System.out.println("Encoding" + encoding);
				return retString;
			} catch (UnsupportedEncodingException e) {
				// Shouldn't fail - but should we still add logging message?
				return null;
			}
		}

	}
}