package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class InformationFeature extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3781602205466140955L;

	protected Long score;
	
	protected Matcher matcher;
	
	protected InformationDescription informationDescription;

	
	@Column(nullable=false)
	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=false)
	public Matcher getMatcher() {
		return matcher;
	}

	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	@ManyToOne
	@JoinColumn(nullable=false)
	public InformationDescription getInformationDescription() {
		return informationDescription;
	}

	public void setInformationDescription(
			InformationDescription informationDescription) {
		this.informationDescription = informationDescription;
	}
	
}
