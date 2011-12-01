package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class InventoryItem extends InventoryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1106546679623873266L;

	protected Item item;
	
	@OneToOne(cascade={CascadeType.ALL})
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
