package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

@Inheritance
@Entity
public abstract class InventoryBase extends AbstractNamedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1052614637774630127L;
	
	protected InventoryCategory category;
	
	@ManyToOne
	public InventoryCategory getCategory() {
		return category;
	}
	public void setCategory(InventoryCategory category) {
		this.category = category;
	}

}
