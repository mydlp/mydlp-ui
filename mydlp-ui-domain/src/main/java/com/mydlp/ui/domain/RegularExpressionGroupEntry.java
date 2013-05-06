package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RegularExpressionGroupEntry extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -449541461894641839L;
	
	protected String regex;
	
	@Column(nullable=false)
	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
}
