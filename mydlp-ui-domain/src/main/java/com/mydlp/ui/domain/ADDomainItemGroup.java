package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ADDomainItemGroup extends ADDomainItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1104867190513596145L;
	
	protected List<ADDomainItem> children;
	
	@OneToMany(mappedBy="parent", cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@OrderBy("distinguishedName")
	public List<ADDomainItem> getChildren() {
		return children;
	}

	public void setChildren(List<ADDomainItem> children) {
		this.children = children;
	}
	
}
