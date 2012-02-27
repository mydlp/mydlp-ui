package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.domain.UserSettings;

public interface UserSettingsDAO {
	
	public UserSettings findByUser(AuthUser user);

	public List<String> getDashboardItems();
	
}
