package com.mfic.data;


import java.util.Date;
public class LoanDocsId implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2676073811570972829L;
	private long lid;
	private Date dtlstupdt;

	public LoanDocsId() {
	}

	public LoanDocsId(long lid, Date dtlstupdt) {
		this.lid = lid;
		this.dtlstupdt = dtlstupdt;
	}


	/**
	 * @return the lid
	 */
	public long getLid() {
		return lid;
	}

	/**
	 * @param lid the lid to set
	 */
	public void setLid(long lid) {
		this.lid = lid;
	}

	/**
	 * @return the dtlstupdt
	 */
	public Date getDtlstupdt() {
		return dtlstupdt;
	}

	/**
	 * @param dtlstupdt the dtlstupdt to set
	 */
	public void setDtlstupdt(Date dtlstupdt) {
		this.dtlstupdt = dtlstupdt;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LoanDocsId))
			return false;
		LoanDocsId castOther = (LoanDocsId) other;

		return (this.getLid() == castOther.getLid())
				&& ((this.getDtlstupdt() == castOther.getDtlstupdt()) || (this
						.getDtlstupdt() != null
						&& castOther.getDtlstupdt() != null && this
						.getDtlstupdt().equals(castOther.getDtlstupdt())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getLid();
		result = 37 * result
				+ (getDtlstupdt() == null ? 0 : this.getDtlstupdt().hashCode());
		return result;
	}

}
