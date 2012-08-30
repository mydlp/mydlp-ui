package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class IncidentLogRequeueStatus extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3144916272923255623L;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;
	protected Boolean isRequeued;
	protected IncidentLog incidentLog;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(nullable=false)
	public Boolean getIsRequeued() {
		return isRequeued;
	}
	public void setIsRequeued(Boolean isRequeued) {
		this.isRequeued = isRequeued;
	}
	
	@OneToOne
	public IncidentLog getIncidentLog() {
		return incidentLog;
	}

	public void setIncidentLog(IncidentLog incidentLog) {
		this.incidentLog = incidentLog;
	}
	
}
