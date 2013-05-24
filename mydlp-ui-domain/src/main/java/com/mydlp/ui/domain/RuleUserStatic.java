package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RuleUserStatic extends RuleUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1194049237046301392L;
	
	protected String username;

	@Column(nullable=false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.trim();
	}
	
}
