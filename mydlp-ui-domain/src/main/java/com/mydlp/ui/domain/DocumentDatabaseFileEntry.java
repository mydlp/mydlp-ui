package com.mydlp.ui.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DocumentDatabaseFileEntry extends AbstractEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 852682231373800353L;
	
	protected String filename; 
	protected String md5Hash;
	protected List<DocumentFingerprint> fingerprints;
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
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	public List<DocumentFingerprint> getFingerprints() {
		return fingerprints;
	}
	public void setFingerprints(List<DocumentFingerprint> fingerprints) {
		this.fingerprints = fingerprints;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
