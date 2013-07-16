package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class LicenseInformation extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -482277069235831278L;
	
	public static final String COMMUNITY_LICENSE = "community";
	public static final String ENTERPRISE_LICENSE = "enterprise";
	public static final String TRIAL_LICENSE = "trial";
	
	protected long expirationDate;
	protected long userCount;
	protected long administrativeUserCount;
	protected String licenseType;
	protected long numberOfAllocatedSeats;
	protected String userEmail;

	public long getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(long expirationDate) {
		this.expirationDate = expirationDate;
	}
	public long getUserCount() {
		return userCount;
	}
	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}
	public long getAdministrativeUserCount() {
		return administrativeUserCount;
	}
	public void setAdministrativeUserCount(long administrativeUserCount) {
		this.administrativeUserCount = administrativeUserCount;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public long getNumberOfAllocatedSeats() {
		return numberOfAllocatedSeats;
	}
	public void setNumberOfAllocatedSeats(long numberOfAllocatedSeats) {
		this.numberOfAllocatedSeats = numberOfAllocatedSeats;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
	
