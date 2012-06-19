package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Document extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1023102283572760364L;
	
}
