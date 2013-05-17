package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EndpointItem extends Item {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7745227313942773729L;
	
	protected Endpoint endpoint;

	@ManyToOne
	@JoinColumn(nullable=false)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}
}
