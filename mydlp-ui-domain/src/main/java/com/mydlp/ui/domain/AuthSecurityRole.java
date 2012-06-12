package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class AuthSecurityRole extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5081535852433329936L;
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static final String ROLE_AUDITOR = "ROLE_AUDITOR";
	
	public static final String ROLE_CLASSIFIER = "ROLE_CLASSIFIER";

	protected String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
		
}
