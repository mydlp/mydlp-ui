package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class IncidentLogFile extends AbstractLogFile {

	/**
	 * 
	 */
	private static final long serialVersionUID = -457563277543387368L;
	
	protected IncidentLog incidentLog;
	protected IncidentLogFileContent content;

	@Index(name="logIndex")
	@ManyToOne
	@JoinColumn(nullable=false)
	public IncidentLog getIncidentLog() {
		return incidentLog;
	}

	public void setIncidentLog(IncidentLog incidentLog) {
		this.incidentLog = incidentLog;
	}

	@Index(name="contentIndex")
	@ManyToOne
	public IncidentLogFileContent getContent() {
		return content;
	}

	public void setContent(IncidentLogFileContent content) {
		this.content = content;
	}

}
