package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SourceDomainName extends Item {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3293617107443481191L;
	
	protected String sourceDomain;

	@Column(nullable=false)
	public String getSourceDomain() {
		return sourceDomain;
	}


	public void setSourceDomain(String sourceDomain) {
		this.sourceDomain = sourceDomain;
	}

	



}
