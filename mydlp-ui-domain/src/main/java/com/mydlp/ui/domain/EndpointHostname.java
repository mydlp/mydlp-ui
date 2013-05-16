package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class EndpointHostname extends Item {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3794056587970934697L;
	
	
	protected String hostname;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	

}
