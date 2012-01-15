package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.UserDAO;
import com.mydlp.ui.domain.AuthUser;

@Service("userBRS")
@RemotingDestination
public class UserBRSImpl implements UserService
{
	@Autowired
	protected UserDAO userDAO;

	@Override
	public List<AuthUser> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	public void remove(AuthUser item) {
		userDAO.remove(item);
	}

	@Override
	public AuthUser getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AuthUser authUser = null;
		if (principal instanceof AuthUser)
			authUser = (AuthUser) principal;
		return authUser;
	}
	
	
	
}