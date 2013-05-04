package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class StringArgument extends Argument {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5042158795062235374L;
	
	protected String argument;

	@Column(nullable=false)
	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

}
