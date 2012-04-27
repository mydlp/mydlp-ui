package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.domain.UserSettings;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00003_Users extends AbstractGranule {

	@Override
	protected void callback() {
		AuthSecurityRole roleAdmin = new AuthSecurityRole();
		roleAdmin.setRoleName(AuthSecurityRole.ROLE_ADMIN);
		AuthSecurityRole roleAuditor = new AuthSecurityRole();
		roleAuditor.setRoleName(AuthSecurityRole.ROLE_AUDITOR);
		
		getHibernateTemplate().saveOrUpdate(roleAdmin);
		getHibernateTemplate().saveOrUpdate(roleAuditor);
		
		AuthUser user = new AuthUser();
		user.setUsername("mydlp");
		user.setEmail("user@mydlp.com");
		user.setPassword("8947320cee61087e89fa734c2a3baf64cf46083d"); // sha1 for 'mydlp'
		user.setIsActive(true);
		user.setHasAuthorityScope(false);
		List<AuthSecurityRole> roles = new ArrayList<AuthSecurityRole>();
		roles.add(roleAdmin);
		user.setRoles(roles);
		
		AuthUser user2 = new AuthUser();
		user2.setUsername("user2");
		user2.setEmail("user@mydlp.com");
		user2.setPassword("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3"); // sha1 for 'test'
		user2.setIsActive(true);
		user2.setHasAuthorityScope(false);
		List<AuthSecurityRole> roles2 = new ArrayList<AuthSecurityRole>();
		roles2.add(roleAuditor);
		user2.setRoles(roles2);
		
		getHibernateTemplate().saveOrUpdate(user);
		getHibernateTemplate().saveOrUpdate(user2);
		
		UserSettings settings = new UserSettings();
		settings.setUser(user);
		getHibernateTemplate().saveOrUpdate(settings);
		
		UserSettings settings2 = new UserSettings();
		settings2.setUser(user2);
		getHibernateTemplate().saveOrUpdate(settings2);
	}

}
