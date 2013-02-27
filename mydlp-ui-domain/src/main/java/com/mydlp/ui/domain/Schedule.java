package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Schedule extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6205369334681244693L;
	
	protected int hour;

	@Column(nullable=false)
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
}
