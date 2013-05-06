package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RuleItemGroup extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3366232983225405648L;

	protected InventoryGroup group;
	
	protected Rule rule;

	@ManyToOne
	@JoinColumn(nullable=false)
	public InventoryGroup getGroup() {
		return group;
	}

	public void setGroup(InventoryGroup group) {
		this.group = group;
	}

	@ManyToOne
	@JoinColumn(nullable=false)
	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

}
