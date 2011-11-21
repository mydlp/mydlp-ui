package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class AuthSecurityRole extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5081535852433329936L;
	
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	protected String roleName;
	
	protected List<AuthUser> users;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany
	@JoinTable
	public List<AuthUser> getUsers() {
		return users;
	}

	public void setUsers(List<AuthUser> users) {
		this.users = users;
	}
		
}
