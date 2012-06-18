package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ADDomain extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4064500948953317680L;
	
	protected String domainName;

	protected String serverIp;
	
	protected String loginUsername;
	
	protected String loginPassword;
	
	protected String netbiosName;
	
	protected Boolean currentlyEnumerating;
	
	protected ADDomainRoot root;
	
	protected List<ADDomainAlias> aliases;

	@Column(nullable=false, unique=true)
	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getNetbiosName() {
		return netbiosName;
	}

	public void setNetbiosName(String netbiosName) {
		this.netbiosName = netbiosName;
	}

	@Column(nullable=false)
	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	@Column(nullable=false)
	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	@Column(nullable=false)
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@OneToOne(mappedBy="domain", cascade={CascadeType.ALL})
	public ADDomainRoot getRoot() {
		return root;
	}

	public void setRoot(ADDomainRoot root) {
		this.root = root;
	}

	public Boolean getCurrentlyEnumerating() {
		return currentlyEnumerating;
	}

	public void setCurrentlyEnumerating(Boolean currentlyEnumerating) {
		this.currentlyEnumerating = currentlyEnumerating;
	}

	@OneToMany(cascade={CascadeType.ALL})
	public List<ADDomainAlias> getAliases() {
		return aliases;
	}

	public void setAliases(List<ADDomainAlias> aliases) {
		this.aliases = aliases;
	}
}
