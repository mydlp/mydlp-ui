package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ADDomainOU extends ADDomainItemGroup {

	/**
	 * 
	 */
	private static final long serialVersionUID = 909492338670314509L;
	protected String name;
	
	@Column(nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
