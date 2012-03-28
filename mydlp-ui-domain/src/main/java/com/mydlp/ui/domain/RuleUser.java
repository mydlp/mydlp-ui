package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class RuleUser extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 257221661999507384L;
	
}
