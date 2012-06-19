package com.mydlp.ui.domain;

import javax.persistence.Entity;

import org.hibernate.annotations.Index;

@Entity
public class DocumentDatabaseRDBMSEntry extends Document {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2743644406250091384L;
	
	protected String originalId;

	@Index(name="originalIdIndex")
	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}
	
}
