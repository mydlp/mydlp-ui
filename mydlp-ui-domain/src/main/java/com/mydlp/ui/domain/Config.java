package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Config extends AbstractNamedEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3080678915948882090L;

	protected String key;
	
	protected String value;

	@Column(unique=true)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
