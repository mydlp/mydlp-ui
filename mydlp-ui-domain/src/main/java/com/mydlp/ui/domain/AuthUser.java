package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.dphibernate.serialization.annotations.NeverSerialize;

@Entity
public class AuthUser extends AbstractEntity {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2277869102174042066L;
	
	
	protected String username;
	protected String password;
	protected String email;
	protected Boolean isActive;
	protected List<AuthSecurityRole> roles;
	protected Boolean hasAuthorityScope;
	protected List<ADDomainItem> authorityScopeADItems;
	protected List<DocumentDatabase> documentDatabases;
	
	@ManyToMany
	public List<DocumentDatabase> getDocumentDatabases() {
		return documentDatabases;
	}
	public void setDocumentDatabases(List<DocumentDatabase> documentDatabases) {
		this.documentDatabases = documentDatabases;
	}

	protected UserSettings settings;

	@Column(unique=true, length=127)
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
	
	@Column(nullable=false)
	public Boolean getHasAuthorityScope() {
		return hasAuthorityScope;
	}
	public void setHasAuthorityScope(Boolean hasAuthorityScope) {
		this.hasAuthorityScope = hasAuthorityScope;
	}
	
	@ManyToMany
	public List<ADDomainItem> getAuthorityScopeADItems() {
		return authorityScopeADItems;
	}
	public void setAuthorityScopeADItems(
			List<ADDomainItem> authorityScopeADItems) {
		this.authorityScopeADItems = authorityScopeADItems;
	}
	
	@OneToOne(mappedBy="user", cascade={CascadeType.ALL})
	public UserSettings getSettings() {
		return settings;
	}
	public void setSettings(UserSettings settings) {
		this.settings = settings;
	}
	
	@Transient
	public Boolean hasRole(String roleName) {
		if (roles == null || roles.size() == 0)
			return false;
		for (AuthSecurityRole role: roles)
			if (role.getRoleName().equals(roleName))
				return true;
		
		return false;
	}
}
