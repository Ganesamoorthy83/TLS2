package com.mfic.util.netconnect;

public class ARFXml {
	
	private String ARFVersion;
	
	public ARFXml() {
		this.setARFVersion("07");
	}

	public void setARFVersion(String aRFVersion) {
		ARFVersion = aRFVersion;
	}

	public String getARFVersion() {
		return ARFVersion;
	}

}
