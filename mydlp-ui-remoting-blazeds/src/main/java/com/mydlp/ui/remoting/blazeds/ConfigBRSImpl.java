package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.ConfigDAO;
import com.mydlp.ui.domain.Config;

@Service("configBRS")
@RemotingDestination
public class ConfigBRSImpl implements ConfigService
{
	@Autowired
	protected ConfigDAO configDAO;

	@Override
	public List<Config> getConfigs() {
		return configDAO.getConfigs();
	}

	@Override
	public void saveAll(List<Config> configs) {
		configDAO.saveAll(configs);
	}
}