package com.mydlp.ui.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class ADDomainUser extends ADDomainItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 106777670135499806L;
	
	protected String displayName;
	protected String sAMAccountName;
	
	protected List<ADDomainUserAlias> aliases;
	protected Set<ADDomainGroup> groups;
	
	@Column(nullable=false)
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@Column(nullable=false)
	public String getsAMAccountName() {
		return sAMAccountName;
	}
	public void setsAMAccountName(String sAMAccountName) {
		this.sAMAccountName = sAMAccountName;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	public List<ADDomainUserAlias> getAliases() {
		return aliases;
	}
	public void setAliases(List<ADDomainUserAlias> aliases) {
		this.aliases = aliases;
	}
	
	@ManyToMany
	public Set<ADDomainGroup> getGroups() {
		return groups;
	}
	public void setGroups(Set<ADDomainGroup> groups) {
		this.groups = groups;
	}
	
	
}
