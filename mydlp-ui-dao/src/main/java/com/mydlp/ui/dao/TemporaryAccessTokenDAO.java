package com.mydlp.ui.dao;

import com.mydlp.ui.domain.TemporaryAccessToken;



public interface TemporaryAccessTokenDAO {

	public String generateTokenKey(String ipAddress, String username, String serviceName, String serviceParam);
	
	public TemporaryAccessToken getTokenObj(String tokenKey);
	
	public void cleanupExpiredTokens();
	
	public void cleanupIdleTokens();
	
	public Boolean hasAnyValidToken(String serviceName, String serviceParam);

	public void revokateAllTokens(String serviceName, String serviceParam);
	
}
