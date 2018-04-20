package com.mfic.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;


public final class Encryption {

	private static Encryption instance;

	private Encryption() {
	}

	public synchronized String encrypt(String plainText) {
		if(StringUtil.isNullOrBlank(plainText)){
			return plainText;
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA"); // step 2
		} catch (NoSuchAlgorithmException e) {
		}
		try {
			md.update(plainText.getBytes("UTF-8")); // step 3
		} catch (UnsupportedEncodingException e) {
		}
		byte raw[] = md.digest(); // step 4

		String hash = null;
		try{
			Base64 base64 = new Base64(76, new byte[0]); 
			hash = new String(base64.encode(raw)); // step 5
		}catch(Exception e){
			
		}
		return hash; // step 6
	}

	public static synchronized Encryption getInstance() // step 1
	{
		if (instance == null) {
			instance = new Encryption();
		}
		return instance;
	}
}
