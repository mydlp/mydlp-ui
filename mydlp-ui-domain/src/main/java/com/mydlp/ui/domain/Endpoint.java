package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Endpoint extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1434909041362541932L;
	
	protected String endpointId;
	protected String endpointSecret;
	protected String endpointAlias;
	
	@Column(nullable=false, unique=true, length=32)
	public String getEndpointId() {
		return endpointId;
	}
	public void setEndpointId(String endpointId) {
		this.endpointId = endpointId;
	}
	@Column(nullable=false, unique=true, length=32)
	public String getEndpointSecret() {
		return endpointSecret;
	}
	public void setEndpointSecret(String endpointSecret) {
		this.endpointSecret = endpointSecret;
	}
	@Column(nullable=false, length=32)
	public String getEndpointAlias() {
		return endpointAlias;
	}
	public void setEndpointAlias(String endpointAlias) {
		this.endpointAlias = endpointAlias;
	}
	
}
