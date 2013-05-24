package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class RemoteStorageSSHFS extends RemoteStorage {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4204930745361136244L;
	
	protected String address;
	
	protected int port;
	
	protected String path;
	
	protected String username;

	protected String password;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address.trim();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path.trim();
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

	
	
}
