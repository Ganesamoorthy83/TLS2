/**  
  * UserLang.java.
  * Java Bean class for language selection 
 */

package com.mfic.core.action;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.mfic.util.UTF8ResourceBundle;

public class UserLang
{
	private ResourceBundle rb;
	String sUserLang;
    String language;
    String country;
    Locale locale;
	
	/**  
	  * Set the value of Language.  
	  * @param value A variable of type String.  
	 */  
    public void setUserlang( String value )
    {
    	sUserLang = value;
    }

	/**  
	  * Retrieve the value of Language.  
	  * @return A String data type.  
	 */ 
    public String getUserlang() { return sUserLang; }

  
	/**  
	  * Retrieve the value of dictionary word.
	  * select Resource Bundle for selected language.  
	  * @return A String data type.  
	 */ 
    public String getLocalString(String sWord)
    {
    	try
    	{
    		 if(sUserLang == null || sUserLang.trim().equals(""))
     		{
 	            language = new String("en");
 	            country = new String("US");
 	            sUserLang = "English";
     		} else if(sUserLang.equalsIgnoreCase("English"))
	    	{
	            language = new String("en");
	            country = new String("US");
	        }
			else if(sUserLang.equalsIgnoreCase("Spanish"))
			{ 
	            language = new String("es");
	            country = new String("ES");
	    	}
	    	
    	}
    	catch(MissingResourceException e)
    	{
    		System.out.println("Language selected" + sWord);
    		e.printStackTrace();
    	}
    	
    	locale = new Locale(language, country);
    	rb = UTF8ResourceBundle.getBundle("resources.MessageResources", locale);
    	return rb.getString(sWord);
    	
    }
}


