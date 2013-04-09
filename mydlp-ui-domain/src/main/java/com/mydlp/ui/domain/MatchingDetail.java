package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Index;

@Entity
public class MatchingDetail extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5811373128233949994L;
	
	
	protected int matcherId;
	protected int incidentLogId;
	protected String matchingData;
	
	
	public int getMatcherId() {
		return matcherId;
	}
	public void setMatcherId(int matcherId) {
		this.matcherId = matcherId;
	}
	
	@Index(name="incidentLogIndex")
	@Column(nullable=false)
	public int getIncidentLogId() {
		return incidentLogId;
	}
	public void setIncidentLogId(int incidentLogId) {
		this.incidentLogId = incidentLogId;
	}
	public String getMatchingData() {
		return matchingData;
	}
	public void setMatchingData(String matchingData) {
		this.matchingData = matchingData;
	}

}
