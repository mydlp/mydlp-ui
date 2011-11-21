package com.mydlp.ui.dao;

import com.mydlp.ui.domain.AuthUser;

public interface UserDAO {

	public AuthUser findByName(String username);
	
}
