package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class InventoryItem extends InventoryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1106546679623873266L;

	protected Item item;
	
	protected InventoryGroup group;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=false)
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@ManyToOne
	public InventoryGroup getGroup() {
		return group;
	}

	public void setGroup(InventoryGroup group) {
		if (group != null)
		{
			setCategory(null);
		}
		this.group = group;
	}

	@Override
	public void setCategory(InventoryCategory category) {
		if (category != null)
		{
			setGroup(null);
		}
		super.setCategory(category);
	}

}
