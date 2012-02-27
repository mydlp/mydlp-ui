package com.mydlp.ui.remoting.blazeds;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.UserSettings;

@Secured(AuthSecurityRole.ROLE_USER)
public interface DashboardService {

	public UserSettings getUserSettings();
	
	public Object get(String itemKey);
	
	public void register(String itemKey);
	
}