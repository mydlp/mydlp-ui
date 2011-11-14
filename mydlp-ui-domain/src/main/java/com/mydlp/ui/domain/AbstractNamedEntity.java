package com.mydlp.ui.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3276236592300960001L;

	protected String name;
	protected String nameKey;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameKey() {
		return nameKey;
	}
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}
	
}
