package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class DocumentFingerprint extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8734489086102500965L;
	
	protected Long fingerprint;

	public Long getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(Long fingerprint) {
		this.fingerprint = fingerprint;
	}

}
