package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class RemoteStorageWindowsShare extends RemoteStorage {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3325261606210877942L;
	
	protected String uncPath;

	protected String username;

	protected String password;
	

	public String getUncPath() {
		return uncPath;
	}

	public void setUncPath(String uncPath) {
		this.uncPath = uncPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
