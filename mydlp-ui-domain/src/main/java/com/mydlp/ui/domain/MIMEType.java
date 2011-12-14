package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MIMEType extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8181065162525513534L;

	protected String mimeType;

	@Column(nullable=false)
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
}
