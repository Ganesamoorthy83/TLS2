package com.mfic.util;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class CountryTest extends TestCase {
    
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.mfic.util.Country#getCountryName(String)}.
	 * This Test case tests whether the method GetCountryName successfully returns a country by passing the country code
	 */
	public void testGetCountryName() {
		System.out.println("Testing Get CountryName function");
		String result = Country.getCountryName("IAD");
        String expresult = null;

    assertEquals(result,expresult);
		//fail("Not yet implemented");
		
	}

	/**
	 * Test method for {@link com.mfic.util.Country#getAllCountries()}.
	 * This Test case tests whether the method GetAllCountries successfully returns a list of countries
	 */
	public void testGetAllCountries() {
				//fail("Not yet implemented");
		System.out.println("Testing Get all countries function");
		Map<String, String> result = Country.getAllCountries();
    //List <String> expresult = new ArrayList<String>("a");

    assertEquals(result.size(),246);
	}

	/**
	 * Test method for {@link com.mfic.util.Country#getCountryNames()}.
	 * This Test case tests whether the method GetCountryNames successfully returns an appropriate size of country list
	 */
	public void testGetCountryNames() {
		//fail("Not yet implemented");
		System.out.println("Testing Get CountryNames function");
		List<String> result = Country.getCountryNames();
    //List <String> expresult = new ArrayList<String>("a");

    assertEquals(result.size(),246);
	}

	/**
	 * Test method for {@link com.mfic.util.Country#getCountryCodes()}.
	 * This Test case tests whether the method GetCountryNames successfully returns an appropriate size of country code list
	 */
	public void testGetCountryCodes() {
		//fail("Not yet implemented");
		System.out.println("Testing Get CountryCodes function");
		List<String> result = Country.getCountryCodes();
    //List <String> expresult = new ArrayList<String>("a");

    assertEquals(result.size(),246);
		
	}

}
