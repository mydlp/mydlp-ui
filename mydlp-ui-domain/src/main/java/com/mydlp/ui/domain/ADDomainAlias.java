package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ADDomainAlias extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1858382982897240249L;

	protected String domainAlias;

	@Column(nullable=false)
	public String getDomainAlias() {
		return domainAlias;
	}
	
	public void setDomainAlias(String domainAlias) {
		this.domainAlias = domainAlias.trim();
	}

}
