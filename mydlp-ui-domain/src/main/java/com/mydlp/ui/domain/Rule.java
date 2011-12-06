package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;

@Inheritance
@Entity
public abstract class Rule extends AbstractNamedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4969264988292826786L;

	
	protected RuleContainer container;
	
	@OneToOne(cascade={CascadeType.ALL})
	public RuleContainer getCategory() {
		return container;
	}
	
	public void setCategory(RuleContainer container) {
		this.container = container;
	}

}
