package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(uniqueConstraints={@UniqueConstraint(name="domainItemDetailIndex", columnNames={"domainId", "distinguishedNameHash"})})
public class ADDomainItem extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6086598167106929988L;
	
	protected String distinguishedName;
	protected ADDomainItemGroup parent;
	protected Integer domainId;
	
	@Column(nullable=false, length=1024)
	public String getDistinguishedName() {
		return distinguishedName;
	}
	
	@Index(name="distinguishedNameIndex")
	@Column(nullable=false)
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

	@Index(name="domainIdIndex")
	@Column(nullable=false)
	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}
	
	@Transient
	public void setDomain(ADDomain domain) {
		if (domain == null) return;
		
		this.domainId = domain.getId();
	}
	
	

}
