package com.mydlp.ui.remoting.blazeds;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN,AuthSecurityRole.ROLE_CLASSIFIER})
public interface TokenService {

	public String generateToolsUploaderToken(String serviceParam);
	
	public Boolean hasAnyValidToken(String serviceName, String serviceParam);

	public void revokateAllTokens(String serviceName, String serviceParam);
}
