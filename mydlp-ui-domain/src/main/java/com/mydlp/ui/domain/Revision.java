package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Revision extends AbstractNamedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6492669705269371783L;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;

	protected Boolean isInstalled = false;
	
	protected String dumpPath;

	@Column(nullable=false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(nullable=false)
	public Boolean getIsInstalled() {
		return isInstalled;
	}

	public void setIsInstalled(Boolean isInstalled) {
		this.isInstalled = isInstalled;
	}

	@Column(nullable=false)
	public String getDumpPath() {
		return dumpPath;
	}

	public void setDumpPath(String dumpPath) {
		this.dumpPath = dumpPath;
	}
	
}
