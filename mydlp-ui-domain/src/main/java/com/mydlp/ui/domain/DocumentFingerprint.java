package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DocumentFingerprint extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2977787333861382468L;
	
	protected Document document;
	protected Long fingerprint;

	@ManyToOne
	@JoinColumn(nullable=false)
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Column(nullable=false)
	public Long getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(Long fingerprint) {
		this.fingerprint = fingerprint;
	}

}
