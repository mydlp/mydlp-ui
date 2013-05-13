package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(name="ruleItemGroupDetailIndex", columnNames={"group_id", "rule_id"})})
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((rule == null) ? 0 : rule.hashCode());
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
		RuleItemGroup other = (RuleItemGroup) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (rule == null) {
			if (other.rule != null)
				return false;
		} else if (!rule.equals(other.rule))
			return false;
		return true;
	}
}
