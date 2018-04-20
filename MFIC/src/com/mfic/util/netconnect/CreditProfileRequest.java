/**
 * 
 */
package com.mfic.util.netconnect;

/**
 * @author kumargna
 *
 */
public class CreditProfileRequest {
	
	private Subscriber Subscriber;
	
	private Applicant PrimaryApplicant;
	
	private OutputType OutputType;
	
	private Vendor Vendor;

	public void setSubscriber(Subscriber subscriber) {
		this.Subscriber = subscriber;
	}

	public Subscriber getSubscriber() {
		return Subscriber;
	}
	
	public CreditProfileRequest(){
		this.Subscriber = new Subscriber();
		this.PrimaryApplicant = new Applicant();
		this.setOutputType(new OutputType());
		this.setVendor(new Vendor());
	}

	public void setPrimaryApplicant(Applicant primaryApplicant) {
		PrimaryApplicant = primaryApplicant;
	}

	public Applicant getPrimaryApplicant() {
		return PrimaryApplicant;
	}

	public void setOutputType(OutputType outputType) {
		OutputType = outputType;
	}

	public OutputType getOutputType() {
		return OutputType;
	}

	public void setVendor(Vendor vendor) {
		Vendor = vendor;
	}

	public Vendor getVendor() {
		return Vendor;
	}

}
