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

	protected String endpointAlias;
	protected String ipAddress;
	protected Boolean isUpToDate;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date firstAppeared;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastUpdate;
	protected String username;
	protected String version;
	protected String osName;
	protected String computerName;
	protected Boolean discoverInProg;
	
	@Index(name="endpointAliasIndex")
	@Column(nullable=false, length=32)
	public String getEndpointAlias() {
		return endpointAlias;
	}
	public void setEndpointAlias(String endpointAlias) {
		this.endpointAlias = endpointAlias;
	}
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
	@Column(nullable=false)
	public Date getFirstAppeared() {
		return firstAppeared;
	}
	public void setFirstAppeared(Date firstAppeared) {
		this.firstAppeared = firstAppeared;
	}
	@Column(nullable=false)
	@Index(name="lastUpdateIndex")
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public Boolean getDiscoverInProg() {
		return discoverInProg;
	}
	public void setDiscoverInProg(Boolean discoverInProg) {
		this.discoverInProg = discoverInProg;
	}
}
