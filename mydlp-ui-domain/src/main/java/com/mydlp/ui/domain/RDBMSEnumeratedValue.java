package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class RDBMSEnumeratedValue extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6733384972114436758L;
	
	protected RDBMSInformationTarget informationTarget;
	protected int hashCode;
	protected String string;
	protected String originalId;

	@ManyToOne
	@JoinColumn(nullable=false)
	public RDBMSInformationTarget getInformationTarget() {
		return informationTarget;
	}

	public void setInformationTarget(RDBMSInformationTarget informationTarget) {
		this.informationTarget = informationTarget;
	}

	@Column(nullable=false)
	@Index(name="hasCodeIndex")
	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	@Lob
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Index(name="orginalIdIndex")
	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

}
