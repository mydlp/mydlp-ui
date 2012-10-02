package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.CustomAction;

public interface CustomActionDAO {

	public List<CustomAction> getCustomActions();
	
	public List<CustomAction> getCustomActions(String searchStr);
	
}
