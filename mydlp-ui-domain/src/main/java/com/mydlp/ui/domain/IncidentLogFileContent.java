package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class IncidentLogFileContent extends AbstractLogFileContent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5029382075774429057L;
	
	protected String contentText;

	@Lob
	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
}
