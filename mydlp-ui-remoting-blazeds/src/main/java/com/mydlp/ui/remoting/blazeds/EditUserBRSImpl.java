package com.mydlp.ui.remoting.blazeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.UserDAO;
import com.mydlp.ui.domain.AuthUser;

@Service("EditUserBRS")
@RemotingDestination
public class EditUserBRSImpl implements EditUserService
{
	@Autowired
	protected UserDAO userDAO;
	
	@Autowired
	protected UserService userService;
	
	public AuthUser authUser = null;

	@Override
	public Boolean isPasswordTrue(String username, String passwd){
		authUser = userService.getCurrentUser();
		if(authUser == null) return false;
		System.out.println("gelen: " +passwd);
		System.out.println("username " + authUser.getUsername());
		System.out.println("org passwd " + authUser.getPassword());
		ShaPasswordEncoder spe =new ShaPasswordEncoder();
		
		return spe.isPasswordValid(authUser.getPassword(), passwd, null);
	}

	@Override
	public AuthUser save(AuthUser a, String passwd) {
		ShaPasswordEncoder spe = new ShaPasswordEncoder();
		System.out.println(a.getPassword());
		String encodedPasswd = spe.encodePassword(passwd, null);
		a.setPassword(encodedPasswd);
		System.out.println(a.getPassword());
		return userDAO.save(a);
	}
}