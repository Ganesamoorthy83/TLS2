package com.mfic.util.netconnect;

public class Subscriber {
	
	private String Preamble;
	private String OpInitials;
	private String SubCode;
	public void setPreamble(String preamble) {
		Preamble = preamble;
	}
	public String getPreamble() {
		return Preamble;
	}
	public void setOpInitials(String opInitials) {
		OpInitials = opInitials;
	}
	public String getOpInitials() {
		return OpInitials;
	}
	public void setSubCode(String subCode) {
		SubCode = subCode;
	}
	public String getSubCode() {
		return SubCode;
	}
	
}
