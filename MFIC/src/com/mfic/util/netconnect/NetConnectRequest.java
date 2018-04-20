package com.mfic.util.netconnect;

public class NetConnectRequest {
	
	private String EAI;
	private String DBHost;
	private String ReferenceId;
	private Request Request;
	
	public NetConnectRequest() {
		// TODO Auto-generated constructor stub
	}

	public void setEAI(String eAI) {
		EAI = eAI;
	}

	public String getEAI() {
		return EAI;
	}

	public void setDBHost(String dBHost) {
		DBHost = dBHost;
	}

	public String getDBHost() {
		return DBHost;
	}

	public void setReferenceId(String referenceId) {
		ReferenceId = referenceId;
	}

	public String getReferenceId() {
		return ReferenceId;
	}

	public void setRequest(Request request) {
		Request = request;
	}

	public Request getRequest() {
		return Request;
	}

}
