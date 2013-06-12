package com.mydlp.ui.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class ADDomainGroup extends ADDomainItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6070729159417924766L;
	
	protected String name;
	protected Set<ADDomainUser> users;
	
	@Column(nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy="groups", fetch=FetchType.LAZY)
	public Set<ADDomainUser> getUsers() {
		return users;
	}

	public void setUsers(Set<ADDomainUser> users) {
		this.users = users;
	}
}
