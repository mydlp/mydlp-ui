package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
public class TemporaryAccessToken extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4136345075419757470L;
	
	protected String ipAddress;
	protected String username;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date expirationDate;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastUpdate;
	protected String tokenKey;
	protected String serviceName;
	protected String serviceParam;
	
	@Index(name = "ipAddressIndex")
	@Column(nullable=false)
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	@Column(nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(nullable=false)
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	@Column(nullable=false)
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Index(name = "tokenKeyIndex")
	@Column(nullable=false, unique=true)
	public String getTokenKey() {
		return tokenKey;
	}
	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}
	@Column(nullable=false)
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceParam() {
		return serviceParam;
	}
	public void setServiceParam(String serviceParam) {
		this.serviceParam = serviceParam;
	}
	
}
