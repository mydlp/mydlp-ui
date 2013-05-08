package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RuleItem extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6559167695021060308L;
	
	public static final String COLUMN_NAME_SOURCE = "SOURCE";
	public static final String COLUMN_NAME_DESTINATION = "DESTINATION";

	protected Item item;

	protected Rule rule;

	protected String ruleColumn;

	@ManyToOne
	@JoinColumn(nullable = false)
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		if (item != null && getRuleColumn() == null)   // Workaround for earlier versions
		{
			if (item instanceof Domain)
			{
				setRuleColumn(COLUMN_NAME_DESTINATION);
			}
			else if (item instanceof RuleUser)
			{
				setRuleColumn(COLUMN_NAME_SOURCE);
			}
		}
		
		this.item = item;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public String getRuleColumn() {
		return ruleColumn;
	}

	public void setRuleColumn(String ruleColumn) {
		this.ruleColumn = ruleColumn;
	}

}
