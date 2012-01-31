package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Argument extends AbstractNamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7760766285444319497L;

}
