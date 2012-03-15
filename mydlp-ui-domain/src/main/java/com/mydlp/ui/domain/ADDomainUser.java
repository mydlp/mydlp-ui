package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ADDomainUser extends ADDomainItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 106777670135499806L;
	
	protected String displayName;
	protected String sAMAccountName;
	protected String userPrincipalName;
	
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
	
	@Column(nullable=false)
	public String getUserPrincipalName() {
		return userPrincipalName;
	}
	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}
		
}
