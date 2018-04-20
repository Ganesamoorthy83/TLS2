package com.mfic.util;

import java.util.ArrayList;
import java.util.List;

public final class AttributePhysicalType {
	
	public static final String TEXT = new String("Text");
	
	public static final String NUMBER = new String ("Number");
	
	public static final String CURRENCY = new String ("Currency");
	
	public static final String DATE = new String ("Date");
	
	
	private static final List<String> AttributePhysicalTypeList = new ArrayList<String>();
	static {
		AttributePhysicalTypeList.add(AttributeType.DOC);
		AttributePhysicalTypeList.add(TEXT);
		AttributePhysicalTypeList.add(NUMBER);
		AttributePhysicalTypeList.add(CURRENCY);
		AttributePhysicalTypeList.add(DATE);
	}

	public static List<String> getAttributephysicaltypelist() {
		return AttributePhysicalTypeList;
	}
	
	private static final List<String> AttributePhysicalTypeListForDoc = new ArrayList<String>();
	static {
		AttributePhysicalTypeListForDoc.add(AttributeType.DOC);
	}
	
	public static List<String> getDocAttributephysicaltypelist() {
		return AttributePhysicalTypeListForDoc;
	}

	private static final List<String> AttributePhysicalTypeListForData = new ArrayList<String>();
	static {
		AttributePhysicalTypeListForData.add(TEXT);
		AttributePhysicalTypeListForData.add(NUMBER);
		AttributePhysicalTypeListForData.add(CURRENCY);
		AttributePhysicalTypeListForData.add(DATE);
	}
	
	public static List<String> getDataAttributephysicaltypelist() {
		return AttributePhysicalTypeListForData;
	}
	
}
