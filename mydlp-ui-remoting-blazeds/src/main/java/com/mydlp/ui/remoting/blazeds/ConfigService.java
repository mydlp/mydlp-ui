package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.Config;

@Secured(AuthSecurityRole.ROLE_ADMIN)
public interface ConfigService {

	public List<Config> getConfigs();
	
	public void saveAll(List<Config> configs);

}