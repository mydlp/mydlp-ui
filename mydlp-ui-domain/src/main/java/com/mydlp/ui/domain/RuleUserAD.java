package com.mydlp.ui.domain;

import java.util.ArrayList;
import java.util.HashSet;

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

	@ManyToOne(cascade={})
	@JoinColumn(nullable=false)
	public ADDomainItem getDomainItem() {
		return domainItem;
	}

	// we don't want bulk items to be serialized to client
	public void setDomainItem(ADDomainItem domainItem) {
		if (domainItem instanceof ADDomainItemGroup)
		{
			((ADDomainItemGroup) domainItem).setChildren(new ArrayList<ADDomainItem>());
		}
		else if (domainItem instanceof ADDomainGroup)
		{
			((ADDomainGroup) domainItem).setUsers(new HashSet<ADDomainUser>());
		}
		this.domainItem = domainItem;
	}
	
}
