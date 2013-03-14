package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
public class DiscoveryReport extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5932983358890048714L;
	
	public static final String STATUS_DISCOVERING = "running";
	public static final String STATUS_STOPPED = "stopped";
	public static final String STATUS_PAUSED = "paused";

	@Temporal(TemporalType.TIMESTAMP)
	protected Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finishDate;

	protected String groupId;
	protected Long ruleId;
	protected String status;
	
	@Column(nullable=false)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getFinisDate() {
		return finishDate;
	}
	public void setFinisDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	@Column(nullable=false)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Index(name="ruleIdIndex")
	@Column(nullable=false)
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	
	@Column(nullable=true)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
