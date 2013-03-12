package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00210_RoleNone extends AbstractGranule {
	
	
	@Override
	protected void callback() {
		AuthSecurityRole roleNone = new AuthSecurityRole();
		roleNone.setRoleName(AuthSecurityRole.ROLE_NONE);
		
		getHibernateTemplate().saveOrUpdate(roleNone);
	}
	
}
