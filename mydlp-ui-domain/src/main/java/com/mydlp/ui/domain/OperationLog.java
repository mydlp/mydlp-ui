package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
public class OperationLog extends AbstractEntity {
	
	public static final String CONTEXT_DISCOVERY = "discovery";
	public static final String CONTEXT_GENERAL = "general";
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -7163156889449607L;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;
	protected Boolean visible;
	protected Long ruleId;
	protected String context;
	protected Byte severity;
	protected String message;
	protected String messageKey;
	
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
	
	@Column(nullable=false, length=16)
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	@Column(nullable=false)
	public Byte getSeverity() {
		return severity;
	}
	public void setSeverity(Byte severity) {
		this.severity = severity;
	}
	
	@Column(length=1000)
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	
	@Column(nullable=true)
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	
}
