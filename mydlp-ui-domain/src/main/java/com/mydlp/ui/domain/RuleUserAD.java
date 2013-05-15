package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RuleUserAD extends RuleUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1236821493164538106L;
	
	protected ADDomainItem domainItem;

	@ManyToOne
	@JoinColumn(nullable=false)
	public ADDomainItem getDomainItem() {
		return domainItem;
	}

	public void setDomainItem(ADDomainItem domainItem) {
		this.domainItem = domainItem;
	}
	
}
