package com.mydlp.ui.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NonCascadingArgument extends Argument {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8084610196409637344L;
	
	protected Argument argument;

	@ManyToOne
	@JoinColumn(nullable=false)
	public Argument getArgument() {
		return argument;
	}

	public void setArgument(Argument argument) {
		this.argument = argument;
	}
	
}
