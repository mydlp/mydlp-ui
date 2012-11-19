package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ApplicationName extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6300138692122666467L;
	
	protected String destinationString;

	@Column(nullable=false)
	public String getDestinationString() {
		return destinationString;
	}

	public void setDestinationString(String destinationString) {
		this.destinationString = destinationString;
	}

}
