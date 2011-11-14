package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class InventoryItem extends InventoryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1106546679623873266L;
	
	protected String itemType;
	protected Long itemId;
	
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
