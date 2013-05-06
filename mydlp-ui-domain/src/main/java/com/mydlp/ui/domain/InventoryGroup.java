package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class InventoryGroup extends InventoryBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3013400579560726074L;
	
	public static final String TYPE_ITYPE = "ITYPE";
	
	protected List<InventoryItem> children;
	protected String itemType;
	
	@OneToMany(mappedBy="group", cascade={CascadeType.ALL})
	public List<InventoryItem> getChildren() {
		return children;
	}
	
	public void setChildren(List<InventoryItem> children) {
		this.children = children;
	}

	@Column(nullable=false)
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
}
