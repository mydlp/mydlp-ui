package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Item extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8242570014953319497L;
	
	protected InventoryItem coupledInventoryItem;

	@OneToOne(mappedBy="item")
	public InventoryItem getCoupledInventoryItem() {
		return coupledInventoryItem;
	}

	public void setCoupledInventoryItem(InventoryItem coupledInventoryItem) {
		this.coupledInventoryItem = coupledInventoryItem;
	}

}
