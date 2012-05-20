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

	protected Integer id;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getClass() == null) ? 0 : this.getClass().hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (this.getClass() == null) {
			if (other.getClass() != null)
				return false;
		} else if (!this.getClass().equals(other.getClass()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
