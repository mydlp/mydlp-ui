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
	public static final String STATUS_PAUSED_SYSTEM = "paused_system";
	public static final String STATUS_PAUSED_USER = "paused_user";
	public static final String STATUS_WAITING = "waiting";
	public static final String STATUS_ERROR = "error";

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
	
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
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
