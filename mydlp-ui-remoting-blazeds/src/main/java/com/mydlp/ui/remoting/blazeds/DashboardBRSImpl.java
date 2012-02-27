package com.mydlp.ui.remoting.blazeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.UserSettingsDAO;
import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.domain.UserSettings;
import com.mydlp.ui.service.DashboardItemService;

@Service("dashboardBRS")
@RemotingDestination
public class DashboardBRSImpl implements DashboardService
{
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected DashboardItemService dashboardItemService;
	
	@Autowired
	protected UserSettingsDAO userSettingsDAO;
	
	@Override
	public UserSettings getUserSettings() {
		AuthUser user = userService.getCurrentUser();
		return userSettingsDAO.findByUser(user);
	}

	@Override
	public Object get(String itemKey) {
		return dashboardItemService.get(itemKey);
	}

	@Override
	public void register(String itemKey) {
		dashboardItemService.registerForGeneration(itemKey);
	}
	
}