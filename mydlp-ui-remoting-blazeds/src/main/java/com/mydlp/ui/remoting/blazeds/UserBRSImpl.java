package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.UserDAO;
import com.mydlp.ui.domain.AuthSecurityRole;
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
		AuthUser authUser = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
			String username = userDetails.getUsername();
			authUser = userDAO.findByName(username);
		}
		return authUser;
	}

	@Override
	public List<AuthSecurityRole> getRoles() {
		return userDAO.getRoles();
	}	
	
	public AuthUser save(AuthUser user) {
		return userDAO.save(user);
	}
	
	@Override
	public Boolean isPasswordTrue(String username, String passwd){
		AuthUser authUser = getCurrentUser();
		if(authUser == null) return false;
		ShaPasswordEncoder spe =new ShaPasswordEncoder();
		return spe.isPasswordValid(authUser.getPassword(), passwd, null);
	}

	@Override
	public AuthUser save(AuthUser authUser, String passwd) {
		ShaPasswordEncoder spe = new ShaPasswordEncoder();
		String encodedPasswd = spe.encodePassword(passwd, null);
		authUser.setPassword(encodedPasswd);
		return userDAO.save(authUser);
	}

	@Override
	public Boolean isEmailValid(Integer userId, String email) {
		int emailHashCode = email.hashCode();
		AuthUser authUser = userDAO.findByEmailHashCode(emailHashCode);
		if(authUser == null || authUser.getId().equals(userId))
			return true;
		else
			return false;
	}
	
}