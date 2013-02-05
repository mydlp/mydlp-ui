package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class RemoteStorageNFS extends RemoteStorage {



	/**
	 * 
	 */
	private static final long serialVersionUID = 6014388774855640392L;

	protected String address;
	
	protected String path;

	public String getIpAddress() {
		return address;
	}

	public void setIpAddress(String ipAddress) {
		this.address = ipAddress;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


}
