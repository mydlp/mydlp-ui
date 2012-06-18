package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@MappedSuperclass
public class OperationLog extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7163156889449607L;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;
	protected Boolean visible;
	protected String context;
	protected String severity;
	protected String message;
	
	@Index(name="dateIndex")
	@Column(nullable=false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Index(name="visibleIndex")
	@Column(nullable=false)
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	@Column(nullable=false)
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	@Column(nullable=false)
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	
	@Column(nullable=false, length=1000)
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
