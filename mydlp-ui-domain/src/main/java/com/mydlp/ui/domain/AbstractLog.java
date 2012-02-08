package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractLog extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2531927084553631314L;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;
	protected Boolean visible;
	protected Long ruleId;
	protected String action;
	protected String channel;
	protected Long sourceIp;
	protected String sourceUser;
	protected String destination;
	protected Long informationTypeId;
	protected String matcherMessage;
	
	@Column(nullable=false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	@Column(nullable=false)
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	@Column(nullable=false)
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Column(nullable=false)
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Long getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(Long sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getSourceUser() {
		return sourceUser;
	}
	public void setSourceUser(String sourceUser) {
		this.sourceUser = sourceUser;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Long getInformationTypeId() {
		return informationTypeId;
	}
	public void setInformationTypeId(Long informationTypeId) {
		this.informationTypeId = informationTypeId;
	}
	public String getMatcherMessage() {
		return matcherMessage;
	}
	public void setMatcherMessage(String matcherMessage) {
		this.matcherMessage = matcherMessage;
	}
}
