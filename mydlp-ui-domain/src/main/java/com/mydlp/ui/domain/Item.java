package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Item extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 576253609821429052L;
	
	protected InventoryItem coupledInventoryItem;

	protected List<RuleItem> coupledRuleItems;
	
	@OneToOne(mappedBy="item")
	public InventoryItem getCoupledInventoryItem() {
		return coupledInventoryItem;
	}

	public void setCoupledInventoryItem(InventoryItem coupledInventoryItem) {
		this.coupledInventoryItem = coupledInventoryItem;
	}

	@OneToMany(mappedBy="item")
	public List<RuleItem> getCoupledRuleItems() {
		return coupledRuleItems;
	}

	public void setCoupledRuleItems(List<RuleItem> coupledRuleItems) {
		this.coupledRuleItems = coupledRuleItems;
	}
}
