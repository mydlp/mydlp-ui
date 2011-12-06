package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class RuleItem extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6559167695021060308L;
	
	protected Item item;
	
	protected Rule rule;

	@ManyToOne
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@ManyToOne
	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

}
