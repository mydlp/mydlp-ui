package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class IncidentLogFile extends AbstractEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5686370533405662869L;
	
	
	protected String filename;
	protected IncidentLog incidentLog;
	protected IncidentLogFileContent content;
	protected IncidentLogFileBlueprint blueprint;
	protected List<MatchingDetail> matchingDetails;
	
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
	
	@OneToMany(mappedBy="incidentLogFile", cascade={CascadeType.ALL})
	public List<MatchingDetail> getMatchingDetails() {
		return matchingDetails;
	}
	public void setMatchingDetails(List<MatchingDetail> matchingDetails) {
		this.matchingDetails = matchingDetails;
	}

}
