package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(name="ruleItemDetailIndex", columnNames={"item_id", "rule_id", "ruleColumn"})})
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

	@Column(length=16)
	public String getRuleColumn() {
		return ruleColumn;
	}

	public void setRuleColumn(String ruleColumn) {
		this.ruleColumn = ruleColumn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((rule == null) ? 0 : rule.hashCode());
		result = prime * result
				+ ((ruleColumn == null) ? 0 : ruleColumn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleItem other = (RuleItem) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (rule == null) {
			if (other.rule != null)
				return false;
		} else if (!rule.equals(other.rule))
			return false;
		if (ruleColumn == null) {
			if (other.ruleColumn != null)
				return false;
		} else if (!ruleColumn.equals(other.ruleColumn))
			return false;
		return true;
	}

}
