package com.mydlp.ui.framework.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.AuthUser;

@Service("assembler")
public class Assembler {

	@Transactional(readOnly = true)
	User buildUserFromUserEntity(AuthUser userEntity) {

		String username = userEntity.getUsername();
		String password = userEntity.getPassword();
		boolean enabled = userEntity.getIsActive();
		boolean accountNonExpired = userEntity.getIsActive();
		boolean credentialsNonExpired = userEntity.getIsActive();
		boolean accountNonLocked = userEntity.getIsActive();

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		AuthSecurityRole role = userEntity.getRole();
		authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
		//for (AuthSecurityRole role : userEntity.getRole()) {
		//	authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
		//}
		
		if (password == null) password = "";

		User user = new User(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}
}