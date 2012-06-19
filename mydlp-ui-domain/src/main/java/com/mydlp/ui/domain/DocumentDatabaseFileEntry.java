package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DocumentDatabaseFileEntry extends Document {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3237890365051128950L;
	
	protected String filename; 
	protected String md5Hash;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getMd5Hash() {
		return md5Hash;
	}
	public void setMd5Hash(String md5Hash) {
		this.md5Hash = md5Hash;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
