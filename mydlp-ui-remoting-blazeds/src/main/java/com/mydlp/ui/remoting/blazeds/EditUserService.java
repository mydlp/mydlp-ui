package com.mydlp.ui.remoting.blazeds;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.AuthUser;

@Secured(AuthSecurityRole.ROLE_USER)
public interface EditUserService {
	
	public Boolean isPasswordTrue(String username, String passwd);
	
	public AuthUser save(AuthUser a, String passwd);
	
}