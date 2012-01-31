package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RegularExpression extends Argument {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6611153637957069210L;
	
	protected String regex;

	@Column(nullable=false)
	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
	
}
