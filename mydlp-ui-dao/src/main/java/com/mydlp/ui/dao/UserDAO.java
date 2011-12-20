package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.AuthUser;

public interface UserDAO {

	public AuthUser findByName(String username);
	
	public List<AuthUser> getUsers();
	
}
