package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractLogRequeueStatus extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -280739640180017144L;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;
	protected Boolean isRequeued;
	
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
	
	
}
