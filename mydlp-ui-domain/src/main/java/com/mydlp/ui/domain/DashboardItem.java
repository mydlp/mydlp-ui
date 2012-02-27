package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class DashboardItem extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3260361311775030863L;
	
	public static final String INCIDENTS_BY_PROTOCOLS_1H = "incident_by_protocols__1h";
	public static final String INCIDENTS_BY_PROTOCOLS_24H = "incident_by_protocols__24h";
	public static final String INCIDENT_BY_ACTIONS_1H = "incident_by_actions__1h";
	public static final String INCIDENT_BY_ACTIONS_24H = "incident_by_actions__24h";
	public static final String TOP_5_ADDRESS_1H = "top_5_address__1h";
	public static final String TOP_5_ADDRESS_24H = "top_5_address__24h";
	public static final String TOP_5_USERS_1H = "top_5_users__1h";
	public static final String TOP_5_USERS_24H = "top_5_users__24h";
	public static final String TOP_5_FILE_TYPE_1H = "top_5_file_type__1h";
	public static final String TOP_5_FILE_TYPE_24H = "top_5_file_type__24h";
	public static final String TOP_5_RULES_1H = "top_5_rules__1h";
	public static final String TOP_5_RULES_24H = "top_5_rules__24h";
	public static final String TOP_5_ITYPES_1H = "top_5_itypes__1h";
	public static final String TOP_5_ITYPES_24H = "top_5_itypes__24h";
	public static final String TOP_5_FILES_1H = "top_5_files__1h";
	public static final String TOP_5_FILES_24H = "top_5_files__24h";
	
	
	protected UserSettings userSettings;
	protected String dasboardItemKey;
	
	@ManyToOne
	public UserSettings getUserSettings() {
		return userSettings;
	}
	public void setUserSettings(UserSettings userSettings) {
		this.userSettings = userSettings;
	}
	
	@Column(nullable=false)
	public String getDasboardItemKey() {
		return dasboardItemKey;
	}
	public void setDasboardItemKey(String dasboardItemKey) {
		this.dasboardItemKey = dasboardItemKey;
	}

}
