package com.mfic.data;



public class LoanDocs  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3874823590018465598L;
	private LoanDocsId id;
	private String doctype;
	private String doclink;
	private String notes;
	private Character recordCode;

	public LoanDocs() {
	}

	public LoanDocs(LoanDocsId id) {
		this.id = id;
	}

	public LoanDocs(LoanDocsId id, String doctype, String doclink, String notes,
			Character recordCode) {
		this.id = id;
		this.doctype = doctype;
		this.doclink = doclink;
		this.notes = notes;
		this.recordCode = recordCode;
	}

	/**
	 * @return the id
	 */
	public LoanDocsId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(LoanDocsId id) {
		this.id = id;
	}

	/**
	 * @return the doctype
	 */
	public String getDoctype() {
		return doctype;
	}

	/**
	 * @param doctype the doctype to set
	 */
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	/**
	 * @return the doclink
	 */
	public String getDoclink() {
		return doclink;
	}

	/**
	 * @param doclink the doclink to set
	 */
	public void setDoclink(String doclink) {
		this.doclink = doclink;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the recordCode
	 */
	public Character getRecordCode() {
		return recordCode;
	}

	/**
	 * @param recordCode the recordCode to set
	 */
	public void setRecordCode(Character recordCode) {
		this.recordCode = recordCode;
	}


}
