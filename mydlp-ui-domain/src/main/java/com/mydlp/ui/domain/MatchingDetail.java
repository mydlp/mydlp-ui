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
	private static final long serialVersionUID = 5811373128233949994L;
	
	protected IncidentLog incidentLog;
	protected String matchingData;
	protected String matcherFunc;
	
	public String getMatcherFunc() {
		return matcherFunc;
	}
	public void setMatcherFunc(String matcherFunc) {
		this.matcherFunc = matcherFunc;
	}
	
	@ManyToOne
	@JoinColumn(nullable=false)
	public IncidentLog getIncidentLog() {
		return incidentLog;
	}
	public void setIncidentLog(IncidentLog incidentLog) {
		this.incidentLog = incidentLog;
	}
	
	@Lob
	public String getMatchingData() {
		return matchingData;
	}
	public void setMatchingData(String matchingData) {
		this.matchingData = matchingData;
	}

}
