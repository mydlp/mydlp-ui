package com.mydlp.ui.domain;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Index;

@MappedSuperclass
public abstract class AbstractLogFileBlueprint extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8865569417665841765L;
	
	protected String md5Hash;
	protected String mimeType;
	protected Long size;

	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}

	@Index(name="hashIndex")
	public String getMd5Hash() {
		return md5Hash;
	}
	public void setMd5Hash(String md5Hash) {
		this.md5Hash = md5Hash;
	}
	
}
