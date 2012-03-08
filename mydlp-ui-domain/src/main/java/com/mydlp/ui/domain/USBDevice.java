package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class USBDevice extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4517406573273768523L;
	
	protected String deviceId;
	protected String uniqId;
	protected String action;
	protected String comment;
	
	@Column(nullable=false)
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Column(nullable=false)
	public String getUniqId() {
		return uniqId;
	}
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}
	
	@Column(nullable=false)
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
		
}
