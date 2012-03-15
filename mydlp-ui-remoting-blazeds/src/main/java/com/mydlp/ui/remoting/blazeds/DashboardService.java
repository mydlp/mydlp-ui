package com.mydlp.ui.remoting.blazeds;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.DashboardItem;
import com.mydlp.ui.domain.UserSettings;

@Secured({AuthSecurityRole.ROLE_ADMIN,AuthSecurityRole.ROLE_AUDITOR})
public interface DashboardService {

	public UserSettings getUserSettings();
	
	public Object get(String itemKey);
	
	public void register(String itemKey);
	
	public void remove(DashboardItem dashboardItem);
	
	public DashboardItem saveDashboardItem(DashboardItem dashboardItem);
	
}