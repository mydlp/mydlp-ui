package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ADDomainItem extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6086598167106929988L;
	
	protected String distinguishedName;
	protected ADDomainItemGroup parent;
	
	@Column(nullable=false, length=1024)
	public String getDistinguishedName() {
		return distinguishedName;
	}
	
	@Column(nullable=false, unique=true)
	public Integer getDistinguishedNameHash() {
		if (distinguishedName != null)
			return distinguishedName.hashCode();
		else
			return 0;
	}

	public void setDistinguishedNameHash(Integer distinguishedNameHash) {
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
