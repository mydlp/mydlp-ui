package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class IncidentLog extends AbstractLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1190971123070164401L;
	
	protected List<IncidentLogFile> files;
	
	protected IncidentLogRequeueStatus requeueStatus;
	
	@OneToMany(mappedBy="incidentLog", cascade={CascadeType.ALL})
	public List<IncidentLogFile> getFiles() {
		return files;
	}

	public void setFiles(List<IncidentLogFile> files) {
		this.files = files;
	}

	@OneToOne(mappedBy="incidentLog", cascade={CascadeType.ALL})
	public IncidentLogRequeueStatus getRequeueStatus() {
		return requeueStatus;
	}

	public void setRequeueStatus(IncidentLogRequeueStatus requeueStatus) {
		this.requeueStatus = requeueStatus;
	}
	
}
