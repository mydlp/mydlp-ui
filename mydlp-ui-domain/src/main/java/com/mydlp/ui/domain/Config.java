package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Config extends AbstractNamedEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3080678915948882090L;

	protected String key;
	
	protected String value;

	@Column(name="configKey", unique=true, nullable=false, length=127)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Lob
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
