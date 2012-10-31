package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Domain extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3817078775013482909L;

	protected String destinationString;

	@Column(nullable=false)
	public String getDestinationString() {
		return destinationString;
	}

	public void setDestinationString(String destinationString) {
		this.destinationString = destinationString;
	}

}
