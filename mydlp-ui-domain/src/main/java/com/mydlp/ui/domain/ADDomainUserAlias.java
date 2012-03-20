package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ADDomainUserAlias extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -125963454226234464L;
	
	protected String userAlias;

	@Column(nullable=false)
	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	
}
