package com.mydlp.ui.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class LicenseInformation extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -482277069235831278L;
	
	public static final String COMMUNITY_LICENSE = "community_license";
	public static final String ENTERPRISE_LICENSE = "enterprise_license";
	public static final String TRIAL_LICENSE = "trial_license";
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date issueDate;
	
	@Temporal(TemporalType.TIMESTAMP)	
	protected Date expirationDate;
	
	protected int userCount;
	protected int administrativeUserCount;
	protected String licenseType;
	protected String licenseKey;
	
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public int getAdministrativeUserCount() {
		return administrativeUserCount;
	}
	public void setAdministrativeUserCount(int administrativeUserCount) {
		this.administrativeUserCount = administrativeUserCount;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public String getLicenseKey() {
		return licenseKey;
	}
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

}
