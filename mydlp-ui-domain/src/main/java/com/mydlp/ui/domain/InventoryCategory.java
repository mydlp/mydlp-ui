package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class InventoryCategory extends InventoryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7173991108220189552L;
	
	protected List<InventoryBase> children;
	protected Boolean editable;
	
	@OneToMany(mappedBy="category", cascade={CascadeType.ALL})
	public List<InventoryBase> getChildren() {
		return children;
	}
	
	public void setChildren(List<InventoryBase> children) {
		this.children = children;
	}
	
	@Column(nullable=false)
	public Boolean getEditable() {
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

}
