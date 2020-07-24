/**
 * 
 */
package com.trade.store;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ramangarg
 *
 */
public final class Trade implements ITrade{ //immutable class
	/**
	 * 
	 */
	private String tradeId;
	private int version;
	private String counterpartyId;
	private String bookId;
	private Date maturityDt;
	private Date createdDt;
	private boolean isExpired;
	private boolean deleted = false;

	//@Deprecated //Ideally incase of actual db tradeid and version will be auto populated
	public Trade(String tradeId, int version, String counterpartyId, String bookId, Date maturityDt) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterpartyId = counterpartyId;
		this.bookId = bookId;
		this.maturityDt = maturityDt;
		this.createdDt = Calendar.getInstance().getTime();
	}

	@Override
	public String getId() {
		return tradeId;
	}
	
	@Override
	public int getVersion() {
		return version;
	}
	public String getCounterpartyId() {
		return counterpartyId;
	}
	public String getBookId() {
		return bookId;
	}
	public Date getMaturityDt() {
		return maturityDt;
	}
	public Date getCreatedDt() {
		return createdDt;
	}
	public boolean isExpired() {
		//if maturity date has passed.
		return this.maturityDt.before(Calendar.getInstance().getTime());
	}
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tradeId == null) ? 0 : tradeId.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (tradeId == null) {
			if (other.tradeId != null)
				return false;
		} else if (!tradeId.equals(other.tradeId))
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
}
