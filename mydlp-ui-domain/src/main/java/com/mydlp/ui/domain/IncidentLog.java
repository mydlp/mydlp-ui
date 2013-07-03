package com.mydlp.ui.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
public class IncidentLog extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1419852102247938129L;
	
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
	protected List<IncidentLogFile> files;
	protected IncidentLogRequeueStatus requeueStatus;
	protected String groupId;
	
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
	@Column(length=16384)
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
	
	@Column(nullable=true)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
