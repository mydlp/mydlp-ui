package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.AuthUser;

public interface UserDAO {

	public AuthUser findByName(String username);
	
	public List<AuthUser> getUsers();
	
	public void remove(AuthUser i);
	
	public List<AuthSecurityRole> getRoles();
	
	public AuthUser save(AuthUser r);
	
	public AuthUser findByEmailHashCode(int emailHashCode);
}
