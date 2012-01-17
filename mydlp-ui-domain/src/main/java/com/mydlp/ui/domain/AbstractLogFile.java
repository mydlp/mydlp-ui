package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractLogFile extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8993670886260403445L;

	protected String filename;
	
	@Column(nullable=false)
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
