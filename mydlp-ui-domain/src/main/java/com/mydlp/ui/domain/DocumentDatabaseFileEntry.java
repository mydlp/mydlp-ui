package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class DocumentDatabaseFileEntry extends AbstractEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 852682231373800353L;
	protected DocumentDatabase documentDatabase;
	protected String md5Hash;
	
	public String getMd5Hash() {
		return md5Hash;
	}
	public void setMd5Hash(String md5Hash) {
		this.md5Hash = md5Hash;
	}

}
