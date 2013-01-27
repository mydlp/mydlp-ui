package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.domain.DashboardItem;
import com.mydlp.ui.domain.UserSettings;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00003_Users extends AbstractGranule {

	@Override
	protected void callback() {
		AuthSecurityRole roleSuperAdmin = new AuthSecurityRole();
		roleSuperAdmin.setRoleName(AuthSecurityRole.ROLE_SUPER_ADMIN);
		AuthSecurityRole roleAdmin = new AuthSecurityRole();
		roleAdmin.setRoleName(AuthSecurityRole.ROLE_ADMIN);
		AuthSecurityRole roleAuditor = new AuthSecurityRole();
		roleAuditor.setRoleName(AuthSecurityRole.ROLE_AUDITOR);
		AuthSecurityRole roleClassifier = new AuthSecurityRole();
		roleClassifier.setRoleName(AuthSecurityRole.ROLE_CLASSIFIER);
		
		getHibernateTemplate().saveOrUpdate(roleAdmin);
		getHibernateTemplate().saveOrUpdate(roleAuditor);
		getHibernateTemplate().saveOrUpdate(roleClassifier);
		getHibernateTemplate().saveOrUpdate(roleSuperAdmin);
		
		AuthUser user = new AuthUser();
		user.setUsername("mydlp");
		user.setEmail("user@mydlp.com");
		user.setPassword("8947320cee61087e89fa734c2a3baf64cf46083d"); // sha1 for 'mydlp'
		user.setIsActive(true);
		user.setHasAuthorityScope(false);
		//List<AuthSecurityRole> roles = new ArrayList<AuthSecurityRole>();
		//roles.add(roleSuperAdmin);
		user.setRole(roleSuperAdmin);
		
		getHibernateTemplate().saveOrUpdate(user);
	
		UserSettings settings = new UserSettings();
		settings.setUser(user);
		
		getHibernateTemplate().saveOrUpdate(settings);

		DashboardItem di = new DashboardItem();
		di.setDasboardItemKey(DashboardItem.INCIDENT_BY_ACTIONS_24H);
		di.setUserSettings(settings);
		DashboardItem di2 = new DashboardItem();
		di2.setDasboardItemKey(DashboardItem.INCIDENTS_BY_PROTOCOLS_24H);
		di2.setUserSettings(settings);
		DashboardItem di3 = new DashboardItem();
		di3.setDasboardItemKey(DashboardItem.TOP_5_USERS_24H);
		di3.setUserSettings(settings);
		DashboardItem di4 = new DashboardItem();
		di4.setDasboardItemKey(DashboardItem.TOP_5_RULES_24H);
		di4.setUserSettings(settings);
		
		getHibernateTemplate().saveOrUpdate(di3);
		getHibernateTemplate().saveOrUpdate(di4);
		getHibernateTemplate().saveOrUpdate(di);
		getHibernateTemplate().saveOrUpdate(di2);
		
	}

}
