package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MatcherArgument extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1690541926905061659L;
	
	protected Matcher coupledMatcher;
	protected Argument coupledArgument;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	public Matcher getCoupledMatcher() {
		return coupledMatcher;
	}
	public void setCoupledMatcher(Matcher coupledMatcher) {
		this.coupledMatcher = coupledMatcher;
	}
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=false)
	public Argument getCoupledArgument() {
		return coupledArgument;
	}
	public void setCoupledArgument(Argument coupledArgument) {
		this.coupledArgument = coupledArgument;
	}
	
}
