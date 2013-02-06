package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class RemoteStorageNFS extends RemoteStorage {



	/**
	 * 
	 */
	private static final long serialVersionUID = 24738691905132351L;

	protected String address;
	
	protected String path;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


}
