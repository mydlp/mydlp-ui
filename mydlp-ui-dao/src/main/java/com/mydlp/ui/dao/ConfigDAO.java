package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.Config;

public interface ConfigDAO {

	public List<Config> getConfigs();
	
	public void saveAll(List<Config> configs);
	
}
