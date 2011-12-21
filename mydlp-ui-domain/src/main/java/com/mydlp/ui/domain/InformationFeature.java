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
	private static final long serialVersionUID = -5842428509062650962L;

	protected Long weight;
	
	protected Matcher matcher;
	
	@Column(nullable=false)
	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
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
