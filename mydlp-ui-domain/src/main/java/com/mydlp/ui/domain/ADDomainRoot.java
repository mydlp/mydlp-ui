package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ADDomainRoot extends ADDomainItemGroup {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3687442330525194709L;
	
	protected ADDomain domain;

	@OneToOne
	public ADDomain getDomain() {
		return domain;
	}

	@Override
	public void setDomain(ADDomain domain) {
		this.domain = domain;
		this.domainId = domain.getId();
	}
	
	
}
