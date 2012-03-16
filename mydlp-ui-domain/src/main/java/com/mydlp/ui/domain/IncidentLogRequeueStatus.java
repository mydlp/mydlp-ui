package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class IncidentLogRequeueStatus extends AbstractLogRequeueStatus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6485495443955924452L;
	
	protected IncidentLog incidentLog;

	@OneToOne
	public IncidentLog getIncidentLog() {
		return incidentLog;
	}

	public void setIncidentLog(IncidentLog incidentLog) {
		this.incidentLog = incidentLog;
	}
	
}
