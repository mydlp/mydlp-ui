package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.AuthUser;

@Secured(AuthSecurityRole.ROLE_USER)
public interface UserService {

	public List<AuthUser> getUsers();	
	
	public void remove(AuthUser item);
	
	public AuthUser getCurrentUser();
	
	public List<AuthSecurityRole> getRoles();
	
}