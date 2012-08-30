package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IncidentLogFile extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5890925685794211279L;
	
	protected String filename;
	protected IncidentLog incidentLog;
	protected IncidentLogFileContent content;
	protected IncidentLogFileBlueprint blueprint;
	
	@Column(nullable=false)
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	@ManyToOne
	@JoinColumn(nullable=false)
	public IncidentLog getIncidentLog() {
		return incidentLog;
	}

	public void setIncidentLog(IncidentLog incidentLog) {
		this.incidentLog = incidentLog;
	}

	@ManyToOne
	public IncidentLogFileContent getContent() {
		return content;
	}

	public void setContent(IncidentLogFileContent content) {
		this.content = content;
	}
	
	@ManyToOne
	public IncidentLogFileBlueprint getBlueprint() {
		return blueprint;
	}
	public void setBlueprint(IncidentLogFileBlueprint blueprint) {
		this.blueprint = blueprint;
	}

}
