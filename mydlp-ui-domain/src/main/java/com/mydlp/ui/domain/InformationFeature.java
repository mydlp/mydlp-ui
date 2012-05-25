package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class InformationFeature extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4010714139618324129L;

	protected Long threshold;
	
	protected Matcher matcher;

	@Column(nullable=false)
	public Long getThreshold() {
		return threshold;
	}

	public void setThreshold(Long threshold) {
		this.threshold = threshold;
	}

	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=false)
	public Matcher getMatcher() {
		return matcher;
	}

	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}
}
