package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class InventoryCategory extends InventoryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7173991108220189552L;
	
	protected List<InventoryBase> children;
	
	@OneToMany(mappedBy="category", cascade={CascadeType.ALL})
	public List<InventoryBase> getChildren() {
		return children;
	}
	
	public void setChildren(List<InventoryBase> children) {
		this.children = children;
	}

}
