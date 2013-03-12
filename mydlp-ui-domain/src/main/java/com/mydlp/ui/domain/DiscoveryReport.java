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

	@Temporal(TemporalType.TIMESTAMP)
	protected Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finisDate;

	protected String groupId;
	protected Long ruleId;
	
	@Column(nullable=false)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getFinisDate() {
		return finisDate;
	}
	public void setFinisDate(Date finisDate) {
		this.finisDate = finisDate;
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
	
	
}
