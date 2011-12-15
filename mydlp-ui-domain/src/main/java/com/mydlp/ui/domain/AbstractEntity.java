package com.mydlp.ui.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.dphibernate.core.HibernateProxy;

@MappedSuperclass
public abstract class AbstractEntity extends HibernateProxy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6550878188381550205L;

	protected Integer id;

	protected Integer optimisticLockVersion;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Version
	public Integer getOptimisticLockVersion() {
		return optimisticLockVersion;
	}

	public void setOptimisticLockVersion(Integer optimisticLockVersion) {
		this.optimisticLockVersion = optimisticLockVersion;
	}

}
