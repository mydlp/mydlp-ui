package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class RemoteStorageFTPFS extends RemoteStorage {




	/**
	 * 
	 */
	private static final long serialVersionUID = 5983755200164506389L;

	protected String address;
	
	protected String username;

	protected String password;
	
	protected String path;


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path.trim();
	}
}
