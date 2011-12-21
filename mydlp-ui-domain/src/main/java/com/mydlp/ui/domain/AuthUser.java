package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.dphibernate.serialization.annotations.NeverSerialize;

@Entity
public class AuthUser extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2085839593764490259L;

	protected String username;
	protected String password;
	protected String email;
	protected Boolean isActive;
	protected List<AuthSecurityRole> roles;

	@Column(unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@NeverSerialize
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@ManyToMany
	public List<AuthSecurityRole> getRoles() {
		return roles;
	}
	public void setRoles(List<AuthSecurityRole> roles) {
		this.roles = roles;
	}
	
	
}
