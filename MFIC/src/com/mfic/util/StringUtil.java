package com.mfic.util;

import java.security.SecureRandom;
import java.util.Random;

public class StringUtil {
    
	public static boolean isNullOrBlank(String str) {
    	return str == null || str.trim().equals("");
    }
    
    public static boolean isEqual(String str1, String str2) {
    	boolean isEqual = false;
    	if(str1 == str2) {
    		isEqual = true;
    	}else if(str1!=null && str1.equals(str2)) {
    		isEqual=true;
    	}
    	return isEqual;
    }

    private static final Random RANDOM = new SecureRandom();
    /** Length of password. @see #generateRandomPassword() */
    public static final int PASSWORD_LENGTH = 8;
    /**
     * Generate a random String suitable for use as a temporary password.
     *
     * @return String suitable for use as a temporary password
     * @since 2.4
     */
    public static String generateRandomPassword()
    {
        // Pick from some letters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, 1 l and L.
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

        String pw = "";
        for (int i=0; i<PASSWORD_LENGTH; i++)
        {
            int index = (int)(RANDOM.nextDouble()*letters.length());
            pw += letters.substring(index, index+1);
        }
        return pw;
    }
    
    public static double convertStringToDouble(String str){
    	Float f = new Float(str);
    	return f.doubleValue();
    }
}
