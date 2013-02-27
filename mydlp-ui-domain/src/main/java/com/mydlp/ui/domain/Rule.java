package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	public static final String ACTION_CUSTOM = "CUSTOM";
	
	protected Long priority;
	
	protected Boolean enabled;
	
	protected String description;
	
	protected String action = Rule.ACTION_PASS;
	
	protected String userMessage;
	
	protected CustomAction customAction;
	
	protected RuleSchedule ruleSchedule;
	
	protected List<RuleItem> ruleItems;
	
	protected List<NotificationItem> notificationItems;
	
	protected Boolean notificationEnabled;
	
	@OneToMany(mappedBy="rule", cascade={CascadeType.ALL})
	public List<NotificationItem> getNotificationItems() {
		return notificationItems;
	}

	public void setNotificationItems(List<NotificationItem> notificationItems) {
		this.notificationItems = notificationItems;
	}

	public Boolean getNotificationEnabled() {
		return notificationEnabled;
	}

	public void setNotificationEnabled(Boolean notificationEnabled) {
		this.notificationEnabled = notificationEnabled;
	}

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
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return this.description;
	}

	@Column(nullable=false)
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@OneToMany(mappedBy="rule", cascade={CascadeType.ALL})
	public List<RuleItem> getRuleItems() {
		return ruleItems;
	}

	public void setRuleItems(List<RuleItem> ruleItems) {
		this.ruleItems = ruleItems;
	}

	@ManyToOne(cascade={})
	@JoinColumn(nullable=true)
	public CustomAction getCustomAction() {
		return customAction;
	}

	public void setCustomAction(CustomAction customAction) {
		this.customAction = customAction;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	@OneToOne(cascade={CascadeType.ALL}, mappedBy="rule")
	@JoinColumn(nullable=true)
	public RuleSchedule getRuleSchedule() {
		return ruleSchedule;
	}

	public void setRuleSchedule(RuleSchedule ruleSchedule) {
		this.ruleSchedule = ruleSchedule;
	}
}
