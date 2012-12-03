package com.mydlp.ui.remoting.blazeds;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN})
public interface TokenService {

	public String generateToken(String serviceName, String serviceParam);

	@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN,AuthSecurityRole.ROLE_CLASSIFIER})
	public String generateToolsUploaderToken(String serviceParam);
			
}