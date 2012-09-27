package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BundledKeywordGroup extends Argument {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4328604394427949426L;
	
	protected String filename;
	protected String descriptionKey;
	
	@Column(nullable=false)
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Column(nullable=false)
	public String getDescriptionKey() {
		return descriptionKey;
	}
	public void setDescriptionKey(String descriptionKey) {
		this.descriptionKey = descriptionKey;
	}

}
