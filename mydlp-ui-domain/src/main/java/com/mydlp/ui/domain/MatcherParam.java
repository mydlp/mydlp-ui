package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MatcherParam extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7769507344355346426L;
	
	protected String param;
	
	protected Matcher matcher;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@ManyToOne
	public Matcher getMatcher() {
		return matcher;
	}

	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

}
