package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
public class EndpointStatus extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7622243528531930685L;
	
	protected String ipAddress;
	protected Boolean isUpToDate;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date firstAppeared;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastUpdate;
	
	@Index(name="ipAddressIndex")
	@Column(nullable=false)
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Boolean getIsUpToDate() {
		return isUpToDate;
	}
	public void setIsUpToDate(Boolean isUpToDate) {
		this.isUpToDate = isUpToDate;
	}
	@Index(name="firstAppearedIndex")
	@Column(nullable=false)
	public Date getFirstAppeared() {
		return firstAppeared;
	}
	public void setFirstAppeared(Date firstAppeared) {
		this.firstAppeared = firstAppeared;
	}
	@Column(nullable=false)
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
