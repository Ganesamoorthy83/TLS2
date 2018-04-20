package com.mfic.util;

import java.util.ArrayList;
import java.util.List;

public final class AttributeType {
	public static final String DOC = new String("Doc");
	
	public static final String DATA = new String ("Data");
	
	private static final List<String> AttributeTypeList = new ArrayList<String>();
	static {
		AttributeTypeList.add(DOC);
		AttributeTypeList.add(DATA);
	}

	public static List<String> getAttributetypelist() {
		return AttributeTypeList;
	}
	

}
