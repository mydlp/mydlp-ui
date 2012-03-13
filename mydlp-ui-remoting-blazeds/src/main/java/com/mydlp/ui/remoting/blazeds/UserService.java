package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.AuthUser;

@Secured(AuthSecurityRole.ROLE_ADMIN)
public interface UserService {

	public List<AuthUser> getUsers();	
	
	public void remove(AuthUser item);

	@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_AUDITOR})
	public AuthUser getCurrentUser();
	
	public List<AuthSecurityRole> getRoles();
	
	public AuthUser save(AuthUser user);
	
	@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_AUDITOR})
	public Boolean isPasswordTrue(String username, String passwd);
	
	@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_AUDITOR})
	public AuthUser save(AuthUser user, String passwd);
	
	
}