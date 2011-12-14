package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

@Entity
@Inheritance
public abstract class Rule extends AbstractNamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8358653537603558110L;
	
	public static final String ACTION_PASS = "PASS";
	public static final String ACTION_BLOCK = "BLOCK";
	public static final String ACTION_LOG = "LOG";
	public static final String ACTION_QUARANTINE = "QUARANTINE";
	public static final String ACTION_ARCHIVE = "ARCHIVE";
	
	protected Long priority;
	
	protected Boolean enabled;
	
	protected String action = Rule.ACTION_PASS;
	
	protected List<RuleItem> ruleItems;
	
	@Column(nullable=false)
	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	@Column(nullable=false)
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(nullable=false)
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@OneToMany(mappedBy="rule")
	public List<RuleItem> getRuleItems() {
		return ruleItems;
	}

	public void setRuleItems(List<RuleItem> ruleItems) {
		this.ruleItems = ruleItems;
	}

}
