package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.Config;

public interface ConfigDAO {

	public List<Config> getConfigs();
	
	public void saveAll(List<Config> configs);
	
	public String getValue(String key);
	
	public void setValue(String key, String value);
}
