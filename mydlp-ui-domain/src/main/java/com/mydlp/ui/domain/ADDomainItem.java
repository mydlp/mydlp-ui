package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class ADDomainItem extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6086598167106929988L;
	
	protected String distinguishedName;
	protected ADDomainItemGroup parent;
	
	@Column(nullable=false, unique=true)
	public String getDistinguishedName() {
		return distinguishedName;
	}

	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}

	@ManyToOne
	public ADDomainItemGroup getParent() {
		return parent;
	}

	public void setParent(ADDomainItemGroup parent) {
		this.parent = parent;
	}
	
}
