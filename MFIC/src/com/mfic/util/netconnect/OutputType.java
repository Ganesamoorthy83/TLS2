package com.mfic.util.netconnect;

public class OutputType {
	
	private ARFXml XML;

	public OutputType() {
		setXML(new ARFXml());
	}

	public void setXML(ARFXml xML) {
		XML = xML;
	}

	public ARFXml getXML() {
		return XML;
	}

}
