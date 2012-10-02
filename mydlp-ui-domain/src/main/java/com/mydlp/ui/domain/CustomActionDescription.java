package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class CustomActionDescription extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5026664442676481388L;
	
	protected CustomAction coupledCustomAction;

	@OneToOne
	@JoinColumn(nullable = false)
	public CustomAction getCoupledCustomAction() {
		return coupledCustomAction;
	}

	public void setCoupledCustomAction(CustomAction coupledCustomAction) {
		this.coupledCustomAction = coupledCustomAction;
	}
	
}
