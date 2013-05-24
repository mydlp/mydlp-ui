package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Hostname extends Item {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7419227550172209412L;
	
	protected String hostname;

	@Column(nullable=false)
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname.trim();
	}
}
