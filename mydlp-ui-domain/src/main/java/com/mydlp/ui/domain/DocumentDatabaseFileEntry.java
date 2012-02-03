package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class DocumentDatabaseFileEntry extends AbstractEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 852682231373800353L;
	
	protected String md5Hash;
	protected String filename; 
	
	public String getMd5Hash() {
		return md5Hash;
	}
	public void setMd5Hash(String md5Hash) {
		this.md5Hash = md5Hash;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	

}
