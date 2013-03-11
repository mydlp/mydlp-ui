package com.mydlp.ui.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.dao.UserDAO;
import com.mydlp.ui.domain.AuthUser;

@Service("userDetailsService") 
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private Assembler assembler;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		AuthUser userEntity = userDAO.findByName(username);
		if (userEntity == null)
			throw new UsernameNotFoundException("user not found");
		
		if (userEntity.getRole() == null)
			throw new UsernameNotFoundException("user is not authorized, has no role");

		return assembler.buildUserFromUserEntity(userEntity);
	}
}
