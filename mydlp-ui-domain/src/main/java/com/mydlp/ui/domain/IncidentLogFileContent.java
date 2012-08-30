package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class IncidentLogFileContent extends AbstractLogFileBlueprint {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7649044685920345089L;
	
	protected String localPath;

	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	
}
