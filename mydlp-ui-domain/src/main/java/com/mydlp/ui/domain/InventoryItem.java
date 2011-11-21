package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.MetaValue;

@Entity
public class InventoryItem extends InventoryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1106546679623873266L;

	protected AbstractEntity item;

	@Any(metaColumn = @Column(name = "itemType"))
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = {
			@MetaValue(targetEntity = Network.class, value = "Network")})
	@JoinColumn(name = "itemId")
	@Cascade(value = {CascadeType.ALL})
	public AbstractEntity getItem() {
		return item;
	}

	public void setItem(AbstractEntity item) {
		this.item = item;
	}

}
