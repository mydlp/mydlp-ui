package com.mydlp.ui.dao;

import com.mydlp.ui.domain.TemporaryAccessToken;


public interface TemporaryAccessTokenDAO {

	public String generateTokenKey(String ipAddress, String username, String serviceName, String serviceParam);
	
	public TemporaryAccessToken getTokenObj(String tokenKey);
	
}
