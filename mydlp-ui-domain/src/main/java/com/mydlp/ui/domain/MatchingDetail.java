package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class MatchingDetail extends AbstractEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8833171841101279634L;
	
	
	protected IncidentLogFile incidentLogFile;
	protected String matchingData;
	protected String matcherFunc;
	
	
	@ManyToOne
	@JoinColumn(nullable=false)
	public IncidentLogFile getIncidentLogFile() {
		return incidentLogFile;
	}
	public void setIncidentLogFile(IncidentLogFile incidentLogFile) {
		this.incidentLogFile = incidentLogFile;
	}
	
	public String getMatcherFunc() {
		return matcherFunc;
	}
	public void setMatcherFunc(String matcherFunc) {
		this.matcherFunc = matcherFunc;
	}
	
	@Lob
	public String getMatchingData() {
		return matchingData;
	}
	public void setMatchingData(String matchingData) {
		this.matchingData = matchingData;
	}

}
