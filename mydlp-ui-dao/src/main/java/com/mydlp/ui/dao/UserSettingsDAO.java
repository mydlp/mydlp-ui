package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.domain.DashboardItem;
import com.mydlp.ui.domain.UserSettings;

public interface UserSettingsDAO {
	
	public UserSettings findByUser(AuthUser user);

	public List<String> getDashboardItems();
	
	public void removeDashboardItem(DashboardItem dashboardItem);
	
	public AbstractEntity save(AbstractEntity entity);
}
