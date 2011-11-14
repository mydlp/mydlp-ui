package com.mydlp.ui.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.dphibernate.core.HibernateProxy;

@MappedSuperclass
public abstract class AbstractEntity extends HibernateProxy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6550878188381550205L;

	protected Long id;	

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
