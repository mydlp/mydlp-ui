package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class InformationFeature extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3968983054132222633L;
	
	protected Long score;
	
	protected Matcher matcher;
	
	protected InformationDescription informationDescription;

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	@OneToOne(cascade={CascadeType.ALL})
	public Matcher getMatcher() {
		return matcher;
	}

	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	@ManyToOne
	public InformationDescription getInformationDescription() {
		return informationDescription;
	}

	public void setInformationDescription(
			InformationDescription informationDescription) {
		this.informationDescription = informationDescription;
	}

}
