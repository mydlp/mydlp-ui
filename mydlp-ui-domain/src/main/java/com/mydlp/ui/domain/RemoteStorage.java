package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class RemoteStorage extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -967941349732134687L;

}
