package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class NotificationItem extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6413568726411467321L;
	protected AuthUser authUser;
	
	protected Rule rule;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	@ManyToOne
	@JoinColumn(nullable=false)
	public AuthUser getAuthUser() {
		return authUser;
	}

	public void setAuthUser(AuthUser authUser) {
		this.authUser = authUser;
	}
	
}
