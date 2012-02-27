package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UserSettings extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7543548390684397261L;
	
	protected AuthUser user;
	protected List<DashboardItem> dashboardItems;
	
	@OneToMany(mappedBy="userSettings")
	public List<DashboardItem> getDashboardItems() {
		return dashboardItems;
	}
	public void setDashboardItems(List<DashboardItem> dashboardItems) {
		this.dashboardItems = dashboardItems;
	}
	
	@OneToOne
	@JoinColumn(nullable=false)
	public AuthUser getUser() {
		return user;
	}
	public void setUser(AuthUser user) {
		this.user = user;
	}

}
