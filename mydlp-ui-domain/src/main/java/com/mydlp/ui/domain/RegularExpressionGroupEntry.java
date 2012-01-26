package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RegularExpressionGroupEntry extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3842182546740221803L;
	
	protected String regex;
	protected RegularExpressionGroup group;

	@Column(nullable=false)
	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	@ManyToOne
	@JoinColumn(nullable=false)
	public RegularExpressionGroup getGroup() {
		return group;
	}

	public void setGroup(RegularExpressionGroup group) {
		this.group = group;
	}

}
