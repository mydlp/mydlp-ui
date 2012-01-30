package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class RegularExpressionGroup extends MatcherParam {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6091346470897557773L;
	
	
	protected List<RegularExpressionGroupEntry> entries;

	@OneToMany(cascade={CascadeType.ALL})
	public List<RegularExpressionGroupEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<RegularExpressionGroupEntry> entries) {
		this.entries = entries;
	}

}
